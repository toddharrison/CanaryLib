package net.canarymod.plugin.impl

import java.io.InputStreamReader

import net.canarymod.CanaryClassLoader
import net.canarymod.exceptions.InvalidPluginException
import net.canarymod.lua.LuaAPI
import net.canarymod.plugin.PluginDescriptor
import net.canarymod.scala.ScalaPlugin
import org.luaj.vm2.lib.jse.JsePlatform
import org.luaj.vm2.{Globals, LuaValue}

import scala.collection.mutable

/**
 * Lua plugin base - handles all lua calls from here
 *
 * @author Pwootage
 */
class LuaPlugin(val desc: PluginDescriptor, val cl: CanaryClassLoader) extends ScalaPlugin {
  private val mainFile: String = desc.getCanaryInf.getString("main-file", "").trim
  if (mainFile == "") {
    throw new InvalidPluginException("Main file for lua plugin not defined!")
  }
  private val luaAPI: LuaAPI = new LuaAPI(this)
  private val tlGlobals = new ThreadLocal[Globals] {
    override def initialValue = {
      val ret: Globals = JsePlatform.standardGlobals
      ret.load(luaAPI.canaryLib)
      val lv: LuaValue = ret.load(new InputStreamReader(cl.getResourceAsStream(mainFile)), mainFile)
      lv.call
      ret
    }
  }
  private val _globalStore: mutable.Map[String, String] = mutable.Map()

  def setGlobal(key: String, value: String) = _globalStore.synchronized {
    _globalStore.put(key, value)
  } match {
    case Some(x) => x
    case None => null
  }

  def getGlobal(key: String) = _globalStore.synchronized {
    _globalStore.get(key)
  } match {
    case Some(x) => x
    case None => null
  }

  def getThreadLocalGlobals: Globals = tlGlobals.get

  override def enable: Boolean = {
    val enableFunc = tlGlobals.get().get("canary").get("enable")
    if (enableFunc.isfunction()) {
      val res = enableFunc.call()
      if (res.isboolean()) {
        res.toboolean()
      } else {
        true
      }
    } else {
      getLogman.error("Lua plugins must define canary.enable")
      false
    }
  }

  override def disable(): Unit = {
    val enableFunc = tlGlobals.get().get("canary").get("disable")
    if (enableFunc.isfunction()) {
      enableFunc.call()
    }
  }
}
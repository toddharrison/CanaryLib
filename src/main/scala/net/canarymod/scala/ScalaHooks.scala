package net.canarymod.scala

import net.canarymod.hook.{Hook, HookHandler}
import net.canarymod.plugin.PluginListener

/**
 * Defines implicit conversions and similar utilities that work well for registering hooks
 * in a more scala/functional way. Used mostly internally by [[ScalaPlugin]]
 *
 * @author Pwootage
 */
object ScalaHooks {

  class ScalaHook[T <: Hook](hook: T => Unit) extends PluginListener {
    def run(h: T) = hook(h)
  }

  implicit def funcToHook[T <: Hook](hook: T => Unit) = new ScalaHook[T](hook)
}

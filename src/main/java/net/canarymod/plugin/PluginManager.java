package net.canarymod.plugin;

import net.canarymod.Canary;
import net.canarymod.CanaryClassLoader;
import net.canarymod.chat.Colors;
import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.hook.system.PluginDisableHook;
import net.canarymod.hook.system.PluginEnableHook;
import net.canarymod.tasks.ServerTaskManager;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static net.canarymod.Canary.log;

import org.apache.logging.log4j.Logger;

/**
 * Plugin Loading and Management Class
 * <p/>
 * Handles loading, unloading, enabling, disabling, and dependency resolving
 *
 * @author Jason (darkdiplomat)
 * @author Chris (damagefilter)
 * @author Jos
 */
public final class PluginManager implements IPluginManager {
    private final Map<String, PluginDescriptor> plugins; // This is keyed to set Plugin name
    private final Map<String, IPluginLoader> loaders;
    private final PropertiesFile pluginPriorities;
    private static final Object lock = new Object();

    public PluginManager() {
        plugins = new LinkedHashMap<String, PluginDescriptor>();
        loaders = new LinkedHashMap<String, IPluginLoader>();
        this.pluginPriorities = new PropertiesFile("config" + File.separator + "plugin_priorities.cfg");
    }

    @Override
    public boolean enablePlugin(String name) throws PluginLoadFailedException {
        PluginDescriptor descriptor = getPluginDescriptor(name);
        if (descriptor != null) {
            Plugin plugin = descriptor.getOrLoadPlugin();
            return descriptor.getPluginLifecycle().enable(plugin, this);
        } else {
            return false;
        }
    }

    @Override
    public void enableAllPlugins() {
        for (String plugin : plugins.keySet()) {
            try {
                if (!enablePlugin(plugin)) {
                    log.error("Failed to disable plugin: " + plugin);
                }
            } catch (Exception e) {
                log.error("Exception while disabling plugin: " + plugin, e);
            }
        }
    }

    @Override
    public boolean disablePlugin(String name) {
        PluginDescriptor descriptor = getPluginDescriptor(name);
        if (descriptor != null) {
            Plugin plugin = descriptor.getPlugin();
            //No need to disable a plugin that isn't loaded
            if (plugin != null) {
                return descriptor.getPluginLifecycle().disable(plugin, this);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void disableAllPlugins() {
        disableAllPlugins(log);
    }

    @Override
    public void disableAllPlugins(Logger log) {
        for (String plugin : plugins.keySet()) {
            try {
                if (!disablePlugin(plugin)) {
                    log.error("Failed to disable plugin: " + plugin);
                }
            } catch (Exception e) {
                log.error("Exception while disabling plugin: " + plugin, e);
            }
        }
    }

    @Override
    public boolean reloadPlugin(String name) throws PluginLoadFailedException, InvalidPluginException {
        PluginDescriptor descriptor = getPluginDescriptor(name);
        if (descriptor != null) {
            disablePlugin(name);
            descriptor.unloadPlugin();
            descriptor.reloadInf();
            return enablePlugin(name);
        }
        return false;
    }

    @Override
    public Plugin getPlugin(String name) {
        PluginDescriptor desc = getPluginDescriptor(name);
        if (desc != null) {
            return desc.getPlugin();
        }
        return null;
    }

    @Override
    public Collection<Plugin> getPlugins() {
        List<Plugin> res = new ArrayList<Plugin>();
        for (PluginDescriptor desc : getPluginDescriptors()) {
            Plugin p = desc.getPlugin();
            if (p != null) {
                res.add(p);
            }
        }
        return Collections.unmodifiableCollection(res);
    }

    @Override
    public Collection<PluginDescriptor> getPluginDescriptors() {
        return Collections.unmodifiableCollection(plugins.values());
    }

    @Override
    public PluginDescriptor getPluginDescriptor(Plugin plugin) {
        return plugins.get(plugin.getName());
    }

    @Override
    public PluginDescriptor getPluginDescriptor(String plugin) {
        return plugins.get(plugin);
    }

    @Override
    public void scanForPlugins() {
        File pluginDir = new File("plugins/");
        if (!pluginDir.exists()) {
            log.warn("Failed to scan for plugins. 'plugins/' is not a directory. Creating...");
            pluginDir.mkdir();
            return;
        }
        if (!pluginDir.isDirectory()) {
            log.error("Failed to scan for plugins. 'plugins/' is not a directory but a file...");
            return;
        }
        File[] pluginFiles = pluginDir.listFiles();
        List<PluginDescriptor> loadedDescriptors = new ArrayList<PluginDescriptor>();
        for (File pluginFile : pluginFiles) {
            try {
                PluginDescriptor desc = new PluginDescriptor(pluginFile.getAbsolutePath());
                plugins.put(desc.getName(), desc);
                loadedDescriptors.add(desc);
            } catch (InvalidPluginException e) {
                log.warn("Found invalid plugin at " + pluginFile.getName() + ", moving on.", e);
            }
        }
        log.info("Found " + loadedDescriptors.size() + " plugins; total: " + plugins.size());
    }
}

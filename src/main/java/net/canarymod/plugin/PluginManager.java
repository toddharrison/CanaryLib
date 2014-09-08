package net.canarymod.plugin;

import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.plugin.dependencies.DependencyGraph;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;
import java.util.*;

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
 * @author Pwootage
 */
public final class PluginManager implements IPluginManager {
    private final Map<String, PluginDescriptor> plugins; // This is keyed to set Plugin name
    private final DependencyGraph dependencies;
    private final PropertiesFile pluginPriorities;
    private static final Object lock = new Object();

    public PluginManager() {
        plugins = new LinkedHashMap<String, PluginDescriptor>();
        dependencies = new DependencyGraph();
        //TODO: Use this
        this.pluginPriorities = new PropertiesFile("config" + File.separator + "plugin_priorities.cfg");
    }

    @Override
    public boolean enablePlugin(String name) throws PluginLoadFailedException {
        PluginDescriptor descriptor = getPluginDescriptor(name);
        if (descriptor == null) {
            return false;
        }
        if (descriptor.getCurrentState() == PluginState.ENABLED) {
            return true;
        }
        Set<String> deps = dependencies.getDependencies(descriptor.getName());
        for (String s : deps) {
            PluginDescriptor dep = getPluginDescriptor(s);
            if (dep == null) {
                log.warn("Dependency " + s + " of " + descriptor.getName() + " is unsatisfied; cannot enable.");
                return false;
            }
            if (dep.getCurrentState() == PluginState.ENABLED) {
                continue;
            }
            if (!enablePlugin(s)) {
                log.warn("Dependency " + s + " of " + descriptor.getName() + " cannot be enabled; cannot enable.");
                return false;
            }
        }
        if (descriptor.getCurrentState() == PluginState.KNOWN) {
            descriptor.getPluginLifecycle().load(this);
        }
        Plugin plugin = descriptor.getPlugin();
        boolean enabled = descriptor.getPluginLifecycle().enable(this);
        if (!enabled) {
            log.warn("Unable to enable plugin " + descriptor.getName());
        }
        return enabled;
    }

    @Override
    public void enableAllPlugins() {
        synchronized (lock) {
            for (String plugin : plugins.keySet()) {
                try {
                    if (!enablePlugin(plugin)) {
                        log.error("Failed to enable plugin: " + plugin);
                    }
                } catch (Exception e) {
                    log.error("Exception while enabling plugin: " + plugin, e);
                }
            }
        }
    }

    @Override
    public boolean disablePlugin(String name) {
        PluginDescriptor descriptor = getPluginDescriptor(name);
        if (descriptor == null) {
            return false;
        }
        if (descriptor.getCurrentState() == PluginState.DISABLED) {
            return true;
        }
        if (descriptor.getCurrentState() == PluginState.KNOWN) {
            return true;
        }
        Set<String> dependants = dependencies.getDependants(descriptor.getName());
        for (String s : dependants) {
            disablePlugin(s);
        }
        return descriptor.getPluginLifecycle().disable(this);
    }

    @Override
    public void disableAllPlugins() {
        disableAllPlugins(log);
    }

    @Override
    public void disableAllPlugins(Logger log) {
        synchronized (lock) {
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
    }

    @Override
    public boolean reloadPlugin(String name) throws PluginLoadFailedException, InvalidPluginException {
        PluginDescriptor descriptor = getPluginDescriptor(name);
        if (descriptor != null) {
            disablePlugin(name);
            descriptor.getPluginLifecycle().unload(this);
            descriptor.reloadInf();
            updateDependencies(descriptor);
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
    public Collection<String> getPluginNames() {
        List<String> res = new ArrayList<String>();
        for (PluginDescriptor desc : getPluginDescriptors()) {
            res.add(desc.getName());
        }
        return Collections.unmodifiableCollection(res);
    }

    @Override
    public Collection<PluginDescriptor> getPluginDescriptors() {
        synchronized (lock) {
            //Returns a copy of the collection to avoid concurrentModificationExceptions
            return Collections.unmodifiableCollection(new ArrayList<PluginDescriptor>(plugins.values()));
        }
    }

    @Override
    public PluginDescriptor getPluginDescriptor(Plugin plugin) {
        return plugins.get(plugin.getName());
    }

    @Override
    public PluginDescriptor getPluginDescriptor(String plugin) {
        PluginDescriptor desc = plugins.get(plugin);
        if (desc == null) {
            //Try to find this plugin (and perhaps others)
            scanForPlugins();
            desc = plugins.get(plugin);
        }
        return desc;
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
                PluginDescriptor desc = loadPluginDescriptorAndInsertInGraph(pluginFile);
                if (desc == null) {
                    continue;
                }
                loadedDescriptors.add(desc);
            } catch (InvalidPluginException e) {
                log.warn("Found invalid plugin at " + pluginFile.getName() + ", moving on.", e);
            }
        }
        synchronized (lock) {
            log.info("Found " + loadedDescriptors.size() + " plugins; total: " + plugins.size());
        }
    }

    private PluginDescriptor loadPluginDescriptorAndInsertInGraph(File pluginFile) throws InvalidPluginException {
        PluginDescriptor desc = new PluginDescriptor(pluginFile.getAbsolutePath());
        if (plugins.containsKey(desc.getName())) {
            return null;
        }
        int priority = pluginPriorities.getInt(desc.getName(), 10);
        desc.setPriority(priority);
        synchronized (lock) {
            plugins.put(desc.getName(), desc);
        }
        updateDependencies(desc);
        return desc;
    }

    private void updateDependencies(PluginDescriptor desc) {
        dependencies.removeNode(desc.getName());
        dependencies.addDependencies(desc.getName(), desc.getDependencies());
    }
}

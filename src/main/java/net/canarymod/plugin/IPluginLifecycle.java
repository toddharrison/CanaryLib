package net.canarymod.plugin;

import net.canarymod.exceptions.PluginLoadFailedException;

/**
 * Defines lifecycle methods for a plugin - load, enable, disable. Implemented by individual {@link net.canarymod.plugin.IPluginLoader}s
 *
 * @author Pwootage
 */
public interface IPluginLifecycle {
    /**
     * Enables the plugin.
     *
     * @param plugin Plugin to enable
     * @param  manager Plugin manager to use (e.g. for dependency checking)
     *
     * @return True if sucessfully enabled, false otherwise.
     */
    public boolean enable(Plugin plugin, IPluginManager manager);

    /**
     * Disables the plugin.
     *
     * @param plugin Plugin to disable
     * @param  manager Plugin manager to use (e.g. for dependency checking)
     *
     * @return True if successfully disabled, false otherwise.
     */
    public boolean disable(Plugin plugin, IPluginManager manager);

    /**
     * Loads the plugin.
     *
     * @return Newly loaded plugin, or null if loading failed.
     */
    public Plugin load() throws PluginLoadFailedException;
}

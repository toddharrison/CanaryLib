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
     * @param  manager Plugin manager to use (e.g. for dependencies)
     *
     * @return True if sucessfully enabled, false otherwise.
     */
    public boolean enable(IPluginManager manager);

    /**
     * Disables the plugin.
     *
     * @param  manager Plugin manager to use (e.g. for dependencies)
     *
     * @return True if successfully disabled, false otherwise.
     */
    public boolean disable(IPluginManager manager);

    /**
     * Loads the plugin.
     *
     * @return Newly loaded plugin.
     * @throws net.canarymod.exceptions.PluginLoadFailedException If an error occurred loading the plugin.
     */
    public Plugin load(IPluginManager manager) throws PluginLoadFailedException;

    /**
     * Unloads the plugin.
     */
    void unload(IPluginManager manager);
}

package net.canarymod.plugin;

import net.canarymod.exceptions.PluginLoadFailedException;

/**
 * Defines lifecycle methods for a plugin - load, enable, disable. Implemented by individual {@link net.canarymod.plugin.PluginManager}s
 *
 * @author Pwootage
 */
public interface PluginLifecycle {
    /**
     * Enables the plugin.
     *
     * @return True if sucessfully enabled, false otherwise.
     */
    public boolean enable();

    /**
     * Disables the plugin.
     *
     * @return True if successfully disabled, false otherwise.
     */
    public boolean disable();

    /**
     * Loads the plugin.
     *
     * @return Newly loaded plugin.
     * @throws net.canarymod.exceptions.PluginLoadFailedException If an error occurred loading the plugin.
     */
    public Plugin load() throws PluginLoadFailedException;

    /**
     * Unloads the plugin.
     */
    void unload();
}

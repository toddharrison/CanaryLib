package net.canarymod.plugin;

/**
 * Indicates a plugin's current state.
 *
 * @author Pwootage
 */
public enum PluginState {
    /**
     * Indicates the plugin is known, but not yet loaded
     */
    KNOWN,
    /**
     * Indicates the plugin is loaded and enabled
     */
    ENABLED,
    /**
     * Inidcates the plugin is loaded and disabled
     */
    DISABLED
}

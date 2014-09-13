package net.canarymod.exceptions;

/**
 * Indicates that a plugin has failed to load. Look at cause for more information.
 *
 * @author Pwootage
 */
public class PluginLoadFailedException extends Exception {
    public PluginLoadFailedException() {
    }

    public PluginLoadFailedException(String message) {
        super(message);
    }

    public PluginLoadFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginLoadFailedException(Throwable cause) {
        super(cause);
    }
}

package net.canarymod.plugin.lifecycle;

/**
 * Indicates a plugin lifecycle is invalid. See message and cause for more information.
 *
 * @author Jason (darkdiplomat)
 */
public class InvalidPluginLifecycleException extends Exception {
    public InvalidPluginLifecycleException() {
    }

    public InvalidPluginLifecycleException(String message) {
        super(message);
    }

    public InvalidPluginLifecycleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPluginLifecycleException(Throwable cause) {
        super(cause);
    }
}

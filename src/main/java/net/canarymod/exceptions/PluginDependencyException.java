package net.canarymod.exceptions;

/**
 * Indicates that a plugin is missing some dependencies. See message for more information.
 *
 * @author Pwootage
 */
public class PluginDependencyException extends Exception {
    public PluginDependencyException() {
    }

    public PluginDependencyException(String message) {
        super(message);
    }

    public PluginDependencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginDependencyException(Throwable cause) {
        super(cause);
    }
}

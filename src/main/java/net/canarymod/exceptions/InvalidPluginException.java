package net.canarymod.exceptions;

/**
 * Indicates a plugin is invalid. See message and cause for more information.
 *
 * @author Pwoootage
 */
public class InvalidPluginException extends Throwable {
    public InvalidPluginException() {
    }

    public InvalidPluginException(String message) {
        super(message);
    }

    public InvalidPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPluginException(Throwable cause) {
        super(cause);
    }
}

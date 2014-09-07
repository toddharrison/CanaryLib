package net.canarymod.exceptions;

/**
 * Created by Christopher on 9/7/2014.
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

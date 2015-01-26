package net.canarymod.exceptions;

/**
 * Indicates a interface casting is invalid. See message and cause for more information.
 *
 * @author Jason Jones (darkdiplomat)
 */
public class InvalidInstanceException extends RuntimeException {
    public InvalidInstanceException() {
    }

    public InvalidInstanceException(String message) {
        super(message);
    }

    public InvalidInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInstanceException(Throwable cause) {
        super(cause);
    }
}

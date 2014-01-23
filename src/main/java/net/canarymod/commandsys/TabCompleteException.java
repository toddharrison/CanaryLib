package net.canarymod.commandsys;

/**
 * Exception thrown if a {@link net.canarymod.commandsys.TabComplete} method fails
 *
 * @author Jason (darkdiplomat)
 */
public class TabCompleteException extends Exception {
    public TabCompleteException(String msg, Throwable thrown) {
        super(msg, thrown);
    }
}

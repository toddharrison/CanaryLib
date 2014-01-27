package net.canarymod.api.chat;

/**
 * Wrapper interface for Minecraft's native HoverEvent.Action
 *
 * @author Jason (darkdiplomat)
 */
public interface HoverEventAction {

    /**
     * Checks if the action is allowed in chat
     *
     * @return {@code true} if allowed; {@code false} if not
     */
    public boolean allowedInChat();

    /**
     * Gets the name of this action
     *
     * @return the name
     */
    public String getName();
}

package net.canarymod.api.chat;

/**
 * Wrapper interface for Minecraft's native ClickEvent
 *
 * @author Jason (darkdiplomat)
 */
public interface ClickEvent {

    /**
     * Gets the {@link ClickEventAction} of this {@link ClickEvent}
     *
     * @return {@link ClickEventAction}
     */
    public ClickEventAction getAction();

    /**
     * Gets the value of this {@code ClickEvent}
     *
     * @return the value
     */
    public String getValue();
}

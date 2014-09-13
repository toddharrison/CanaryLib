package net.canarymod.api.chat;

/**
 * Wrapper interface for Minecraft native ChatStyle
 *
 * @author Jason (darkdiplomat)
 */
public interface ChatStyle extends Cloneable {

    /**
     * Gets the {@link net.canarymod.api.chat.ChatFormatting} for this {@code ChatStyle}
     *
     * @return {@link net.canarymod.api.chat.ChatFormatting}
     */
    public ChatFormatting getColor();

    /**
     * Checks if this {@code ChatStyle} is bold
     *
     * @return {@code true} if bold; {@code false} if not
     */
    public boolean isBold();

    /**
     * Checks if this {@code ChatStyle} is italic
     *
     * @return {@code true} if italic; {@code false} if not
     */
    public boolean isItalic();

    /**
     * Checks if this {@code ChatStyle} is strikethrough
     *
     * @return {@code true} if strikethrough; {@code false} if not
     */
    public boolean isStrikethrough();

    /**
     * Checks if this {@code ChatStyle} is underlined
     *
     * @return {@code true} if underlined; {@code false} if not
     */
    public boolean isUnderlined();

    /**
     * Checks if this {@code ChatStyle} is obfuscated (random)
     *
     * @return {@code true} if obfuscated; {@code false} if not
     */
    public boolean isObfuscated();

    /**
     * Checks if this {@code ChatStyle} is empty
     *
     * @return {@code true} if empty; {@code false} if not
     */
    public boolean isEmpty();

    /**
     * Gets the assigned {@link net.canarymod.api.chat.ClickEvent}
     *
     * @return {@link net.canarymod.api.chat.ClickEvent}
     */
    public ClickEvent getChatClickEvent();

    /**
     * Gets the assigned {@link net.canarymod.api.chat.HoverEvent}
     *
     * @return {@link net.canarymod.api.chat.HoverEvent}
     */
    public HoverEvent getChatHoverEvent();

    /**
     * Sets the {@link net.canarymod.api.chat.ChatFormatting} of this {@code ChatStyle}
     *
     * @param color
     *         the {@link net.canarymod.api.chat.ChatFormatting} color
     *
     * @return {@code this}
     */
    public ChatStyle setColor(ChatFormatting color);

    /**
     * Sets if this {@code ChatStyle} is bold
     *
     * @param bold
     *         {@code true} if bold; {@code false} if not
     *
     * @return {@code this}
     */
    public ChatStyle setBold(boolean bold);

    /**
     * Sets if this {@code ChatStyle} is italic
     *
     * @param italic
     *         {@code true} if bold; {@code false} if not
     *
     * @return {@code this}
     */
    public ChatStyle setItalic(boolean italic);

    /**
     * Sets if this {@code ChatStyle} is strikethrough
     *
     * @param strikethrough
     *         {@code true} if strikethrough; {@code false} if not
     *
     * @return {@code this}
     */
    public ChatStyle setStrikethrough(boolean strikethrough);

    /**
     * Sets if this {@code ChatStyle} is bold
     *
     * @param underlined
     *         {@code true} if underlined; {@code false} if not
     *
     * @return {@code this}
     */
    public ChatStyle setUnderlined(boolean underlined);

    /**
     * Sets if this {@code ChatStyle} is bold
     *
     * @param obfuscated
     *         {@code true} if obfuscated; {@code false} if not
     *
     * @return {@code this}
     */
    public ChatStyle setObfuscated(boolean obfuscated);

    /**
     * Sets the {@link net.canarymod.api.chat.ClickEvent} of this {@code ChatStyle}
     *
     * @param clickEvent
     *         the {@link net.canarymod.api.chat.ClickEvent} to be set
     *
     * @return {@code this}
     */
    public ChatStyle setChatClickEvent(ClickEvent clickEvent);

    /**
     * Sets the {@link net.canarymod.api.chat.HoverEvent} of this {@code ChatStyle}
     *
     * @param clickEvent
     *         the {@link net.canarymod.api.chat.HoverEvent} to be set
     *
     * @return {@code this}
     */
    public ChatStyle setChatHoverEvent(HoverEvent hoverEvent);

    /**
     * Sets the {@code ChatStyle} parent of this {@code ChatStyle}
     *
     * @param chatStyle
     *         the {@code ChatStyle} to set as parent
     *
     * @return {@code this}
     */
    public ChatStyle setParentStyle(ChatStyle chatStyle);

    /**
     * Gets the parent {@code ChatStyle} of this {@code ChatStyle}
     *
     * @return the parent {@code ChatStyle}
     */
    public ChatStyle getParentStyle();

    /**
     * Clones this {@code ChatStyle}
     *
     * @return {@code ChatStyle} clone
     */
    public ChatStyle clone();
}

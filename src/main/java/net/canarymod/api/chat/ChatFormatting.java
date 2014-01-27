package net.canarymod.api.chat;

/**
 * Wrapper interface for EnumChatFormatting
 *
 * @author Jason (darkdiplomat)
 */
public interface ChatFormatting {

    /**
     * Gets the {@code character} code
     *
     * @return {@code character}
     */
    public char getFormattingCode();

    /**
     * Checks if this is a formatting code (ie: Bold, Italic, Strikethrough, Obfuscated, Underline)
     *
     * @return {@code true} if formatting code; {@code false} if not
     */
    public boolean isFormat();

    /**
     * Checks if this is a color code (ie: DARK_GREEN)
     *
     * @return {@code true} if color code; {@code false} if not
     */
    public boolean isColor();

    /**
     * Gets the name of this {@code ChatFormatting} (ie: DARK_GREEN)
     *
     * @return name
     */
    public String getName();

}

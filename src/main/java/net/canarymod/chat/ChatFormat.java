package net.canarymod.chat;

/**
 * Enum containing all chat styles and colors.
 * 
 * @author Jason (darkdiplomat)
 */

public enum ChatFormat {

    /** <b>MARKER §</b> */
    MARKER('\u00A7'),

    /** <FONT COLOR=000000><b>BLACK</b></FONT> */
    BLACK('0'),

    /** <font color="000066"><b>DARK_BLUE</b></font> */
    DARK_BLUE('1'),

    /** <font color="006600"><b>GREEN</b></font> */
    GREEN('2'),

    /** <font color="006666"><b>TURQUOISE</b></font> */
    TURQUOISE('3'),

    /** <font color="990000"><b>RED</b></font> */
    RED('4'),

    /** <font color="540054"><b>PURPLE</b></font> */
    PURPLE('5'),

    /** <font color="FF9933"><b>ORANGE</b></font> */
    ORANGE('6'),

    /** <font color="CCCCCC"><b>LIGHT_GRAY</b></font> */
    LIGHT_GRAY('7'),

    /** <font color="333333"><b>GRAY</b></font> */
    GRAY('8'),

    /** <font color="2A2A7F"><b>BLUE</b></font> */
    BLUE('9'),

    /** <font color="33FF33"><b>LIGHT_GREEN</b></font> */
    LIGHT_GREEN('A'),

    /** <font color="00FFFF"><b>CYAN</b></font> */
    CYAN('B'),

    /** <font color="FF0022"><b>LIGHT_RED</b></font> */
    LIGHT_RED('C'),

    /** <font color="FF00FF"><b>PINK</b></font> */
    PINK('D'),

    /** <font color="FFFF00"><b>YELLOW</b></font> */
    YELLOW('E'),

    /** <font color="000000"><b>WHITE</b></font> */
    WHITE('F'),

    /** <b>OBFUSCATED</b> */
    OBFUSCATED('K'),

    /** <b>BOLD</b> */
    BOLD('L'),

    /** <s>STRIKED</s> */
    STRIKED('M'),

    /** <u>UNDERLINED</u> */
    UNDERLINED('N'),

    /** <i>ITALIC</i> */
    ITALIC('O'),

    /** RESET */
    RESET('R');

    private final char code;

    private ChatFormat(char code) {
        this.code = code;
    }

    public final String concat(String str) {
        if (this == MARKER) {
            return stringValue().concat(str);
        }
        else {
            return toString().concat(str);
        }
    }

    /**
     * Returns the char value of the {@code ChatFormat}
     *
     * @return char value of the {@code ChatFormat}
     */
    public final char charValue() {
        return code;
    }

    /**
     * Returns the {@code ChatFormat} as a {@link String}
     *
     * @return String value of {@code ChatFormat}
     */
    public final String stringValue() {
        return String.valueOf(code);
    }

    /**
     * Returns a string of the {@code ChatFormat}<br>
     * If the {@code ChatFormat} is that other than {@code MARKER} then {@code MARKER} is appended to the front.
     *
     * @return {@code MARKER} or {@code MARKER} + code
     */
    public final String toString() {
        if (this == MARKER) {
            return String.valueOf(code);
        }
        else {
            return MARKER.concat(stringValue());
        }
    }

    /**
     * removes all color formatting from a line
     *
     * @param str
     *            the string to remove formatting from
     * @return str with formatting removed
     */
    public static String removeFormatting(String str) {
        return str.replaceAll("(?i)\u00A7[a-fk-nr0-9]", "");
    }

    /**
     * Formats a given {@link String} by replacing the specified marker with the Section Symbol (§)
     *
     * @param str
     *            the {@link String} to format
     * @param marker
     *            the specified marker to replace
     * @return the formatted {@link String}
     */
    public static String formatString(String str, String marker) {
        marker = marker.replaceAll("(\\$|\\^|\\.|\\*|\\?|\\+)", "\\\\$1");// Clean the string of anything that could disrupt the regex
        return str.replaceAll("(?i)" + marker + "([a-fk-nr0-9])", MARKER.concat("$1"));
    }
}
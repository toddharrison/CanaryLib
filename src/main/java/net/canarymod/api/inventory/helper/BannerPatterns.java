package net.canarymod.api.inventory.helper;

/**
 * Banner Patterns
 *
 * @author Jason Jones
 */
public enum BannerPatterns {

    BASE,
    SQUARE_BOTTOM_LEFT,
    SQUARE_BOTTOM_RIGHT,
    SQUARE_TOP_LEFT,
    SQUARE_TOP_RIGHT,
    STRIPE_BOTTOM,
    STRIPE_TOP,
    STRIPE_LEFT,
    STRIPE_RIGHT,
    STRIPE_CENTER,
    STRIPE_MIDDLE,
    STRIPE_DOWNRIGHT,
    STRIPE_DOWNLEFT,
    STRIPE_SMALL,
    CROSS,
    STRAIGHT_CROSS,
    TRIANGLE_BOTTOM,
    TRIANGLE_TOP,
    TRIANGLES_BOTTOM,
    TRIANGLES_TOP,
    DIAGONAL_LEFT,
    DIAGONAL_RIGHT,
    DIAGONAL_LEFT_MIRROR,
    DIAGONAL_RIGHT_MIRROR,
    CIRCLE_MIDDLE,
    RHOMBUS_MIDDLE,
    HALF_VERTICAL,
    HALF_HORIZONTAL,
    HALF_VERTICAL_MIRROR,
    HALF_HORIZONTAL_MIRROR,
    BORDER,
    CURLY_BORDER,
    CREEPER,
    GRADIENT,
    GRADIENT_UP,
    BRICKS,
    SKULL,
    FLOWER,
    MOJANG;

    /**
     * Gets a BannerPatterns from a String
     *
     * @param pattern
     *         the pattern to get
     *
     * @return the BannerPatterns if found; {@code null} if not found
     */
    public static BannerPatterns fromString(String pattern) {
        try {
            return valueOf(pattern.toUpperCase());
        }
        catch (IllegalArgumentException iaex) {
            return null;
        }
    }
}

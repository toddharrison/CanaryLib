package net.canarymod.api.world.blocks;

/**
 * A BlockFace is a side of a block
 *
 * @author Chris (damagefilter)
 * @author Jason Jones (darkdiplomat)
 */
public enum BlockFace {
    BOTTOM,
    TOP,
    NORTH,
    SOUTH,
    WEST,
    EAST,
    UNKNOWN;

    /**
     * Return this faces normal direction (The byte value to this face)
     *
     * @return the face byte value
     */
    public byte getByte() {
        return (byte) ordinal();
    }

    /**
     * Get a BlockFace from byte
     *
     * @param normal
     *         the facing byte value
     *
     * @return the {@link BlockFace}
     */
    public static BlockFace fromByte(byte normal) {
        if (normal >= 6 || normal <= -1) {
            return UNKNOWN;
        }
        return values()[normal];
    }
}

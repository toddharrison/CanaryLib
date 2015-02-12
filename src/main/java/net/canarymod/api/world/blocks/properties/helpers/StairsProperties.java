package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.CobbleStair;

/**
 * Stairs properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class StairsProperties extends BlockProperties {

    /**
     * Stairs facing property, Values: {@link net.canarymod.api.world.blocks.BlockFace}
     */
    public static final BlockDirectionProperty facing = getInstanceFor(CobbleStair, "facing");
    public static final BlockEnumProperty
            half = getInstanceFor(CobbleStair, "half"),
            shape = getInstanceFor(CobbleStair, "shape");

    /**
     * Stairs half
     */
    public enum Half {
        UPPER,
        LOWER;

        public static Half valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Stair shapes
     */
    public enum Shape {
        STRAIGHT,
        INNER_LEFT,
        INNER_RIGHT,
        OUTER_LEFT,
        OUTER_RIGHT;

        public static Shape valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Applies a {@link net.canarymod.api.world.blocks.BlockFace} facing property to the {@code Stairs}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@link net.canarymod.api.world.blocks.BlockFace} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyHalf(Block block, Half value) {
        return apply(block, half, value);
    }

    public static Block applyShape(Block block, Shape value) {
        return apply(block, shape, value);
    }
}

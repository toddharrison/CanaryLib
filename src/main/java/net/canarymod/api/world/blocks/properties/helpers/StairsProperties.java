package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.CobbleStair;

/**
 * Stairs properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class StairsProperties extends BlockProperties {
    public static final BlockEnumProperty
            facing = getInstanceFor(CobbleStair, "facing"),
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

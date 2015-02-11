package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Lever;

/**
 * Lever properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class LeverProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(Lever, "facing");
    public static final BlockBooleanProperty powered = getInstanceFor(Lever, "powered");

    /**
     * Lever orientation
     */
    public enum Orientation {
        DOWN_X,
        EAST,
        WEST,
        SOUTH,
        NORTH,
        UP_Z,
        UP_X,
        DOWN_Z;

        public static Orientation valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyFacing(Block block, Orientation value) {
        return apply(block, facing, value);
    }

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }
}

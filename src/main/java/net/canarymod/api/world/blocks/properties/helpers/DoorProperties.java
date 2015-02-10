package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

/**
 * Door properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DoorProperties extends BlockProperties {
    private static final BlockEnumProperty facing = getInstanceFor(BlockType.WoodenDoor, "facing");
    private static final BlockBooleanProperty open = getInstanceFor(BlockType.WoodenDoor, "open");
    private static final BlockEnumProperty hinge = getInstanceFor(BlockType.WoodenDoor, "hinge");
    private static final BlockBooleanProperty powered = getInstanceFor(BlockType.WoodenDoor, "powered");
    private static final BlockEnumProperty half = getInstanceFor(BlockType.WoodenDoor, "half");

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
     * Hinge Position
     */
    public enum HingePosition {
        LEFT,
        RIGHT;

        public static HingePosition valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyOpen(Block block, boolean value) {
        return apply(block, open, value);
    }

    public static Block applyHinge(Block block, HingePosition value) {
        return apply(block, hinge, value);
    }

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }

    public static Block applyHalf(Block block, Half value) {
        return apply(block, half, value);
    }
}

package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.RedstoneComparator;

/**
 * Redstone Comparator properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class RedstoneComparatorProperties extends BlockProperties {
    public static final BlockBooleanProperty
            powered = getInstanceFor(RedstoneComparator, "powered"),
            locked = getInstanceFor(RedstoneComparator, "locked");
    public static final BlockEnumProperty mode = getInstanceFor(RedstoneComparator, "mode");
    public static final BlockIntegerProperty delay = getInstanceFor(RedstoneComparator, "delay");

    public enum Mode {
        COMPARE,
        SUBTRACT;

        public static Mode valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }

    public static Block applyLocked(Block block, boolean value) {
        return apply(block, locked, value);
    }

    public static Block applyMode(Block block, Mode value) {
        return apply(block, mode, value);
    }

    public static Block applyDelay(Block block, int value) {
        return apply(block, delay, value);
    }
}

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

    /**
     * Applies whether the {@code Redstone Comparator} is powered or not
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code boolean} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }

    /**
     * Applies whether the {@code Redstone Comparator} is locked or not
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code boolean} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyLocked(Block block, boolean value) {
        return apply(block, locked, value);
    }

    public static Block applyMode(Block block, Mode value) {
        return apply(block, mode, value);
    }

    /**
     * Applies delay to the {@code Redstone Comparator}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code int} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyDelay(Block block, int value) {
        return apply(block, delay, value);
    }
}

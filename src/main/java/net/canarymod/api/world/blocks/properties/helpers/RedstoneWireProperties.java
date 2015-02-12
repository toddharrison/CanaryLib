package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.RedstoneWire;

/**
 * Redstone Wire properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class RedstoneWireProperties extends BlockProperties {
    public static final BlockIntegerProperty power = getInstanceFor(RedstoneWire, "power");
    public static final BlockEnumProperty
            north = getInstanceFor(RedstoneWire, "north"),
            south = getInstanceFor(RedstoneWire, "south"),
            east = getInstanceFor(RedstoneWire, "east"),
            west = getInstanceFor(RedstoneWire, "west");

    public enum AttachPosition {
        UP,
        SIDE,
        NONE;

        public static AttachPosition valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Applies power to the {@code Redstone Wire}
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
    public static Block applyPower(Block block, int value) {
        return apply(block, power, value);
    }

    public static Block applyNorth(Block block, AttachPosition value) {
        return apply(block, north, value);
    }

    public static Block applySouth(Block block, AttachPosition value) {
        return apply(block, south, value);
    }

    public static Block applyEast(Block block, AttachPosition value) {
        return apply(block, east, value);
    }

    public static Block applyWest(Block block, AttachPosition value) {
        return apply(block, west, value);
    }
}

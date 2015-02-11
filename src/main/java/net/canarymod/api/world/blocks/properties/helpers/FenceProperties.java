package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.Fence;

/**
 * Fence properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FenceProperties extends BlockProperties {
    public static final BlockBooleanProperty
            north = getInstanceFor(Fence, "north"),
            east = getInstanceFor(Fence, "east"),
            south = getInstanceFor(Fence, "south"),
            west = getInstanceFor(Fence, "west");

    public static Block applyNorth(Block block, boolean value) {
        return apply(block, north, value);
    }

    public static Block applyEast(Block block, boolean value) {
        return apply(block, east, value);
    }

    public static Block applySouth(Block block, boolean value) {
        return apply(block, south, value);
    }

    public static Block applyWest(Block block, boolean value) {
        return apply(block, west, value);
    }
}

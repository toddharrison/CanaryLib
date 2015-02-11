package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.Vines;

/**
 * Vine properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class VineProperties extends BlockProperties {
    public static final BlockBooleanProperty
            up = getInstanceFor(Vines, "up"),
            north = getInstanceFor(Vines, "north"),
            east = getInstanceFor(Vines, "east"),
            south = getInstanceFor(Vines, "south"),
            west = getInstanceFor(Vines, "west");

    public static Block applyUp(Block block, boolean value) {
        return apply(block, up, value);
    }

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

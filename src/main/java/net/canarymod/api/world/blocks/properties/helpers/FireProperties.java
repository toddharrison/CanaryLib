package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.FireBlock;

/**
 * Fire properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FireProperties extends BlockProperties {
    public static final BlockIntegerProperty
            age = getInstanceFor(FireBlock, "age"),
            upper = getInstanceFor(FireBlock, "upper");
    public static final BlockBooleanProperty
            alt = getInstanceFor(FireBlock, "alt"),
            north = getInstanceFor(FireBlock, "north"),
            east = getInstanceFor(FireBlock, "east"),
            south = getInstanceFor(FireBlock, "south"),
            west = getInstanceFor(FireBlock, "west");

    public static Block applyAge(Block block, int value) {
        return apply(block, age, value);
    }

    public static Block applyAlt(Block block, boolean value) {
        return apply(block, alt, value);
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

    public static Block applyUpper(Block block, int value) {
        return apply(block, upper, value);
    }
}

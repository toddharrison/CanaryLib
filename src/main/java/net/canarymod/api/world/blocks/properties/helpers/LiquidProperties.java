package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.Water;

/**
 * Liquid properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class LiquidProperties extends BlockProperties {
    public static final BlockIntegerProperty level = getInstanceFor(Water, "level");

    public static Block applyLevel(Block block, int value) {
        return apply(block, level, value);
    }
}

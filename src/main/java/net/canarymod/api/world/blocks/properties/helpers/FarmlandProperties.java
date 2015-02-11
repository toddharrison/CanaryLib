package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.Soil;

/**
 * Farmland properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FarmlandProperties extends BlockProperties {
    public static final BlockIntegerProperty mositure = getInstanceFor(Soil, "mositure");

    public static Block applyMositure(Block block, int value) {
        return apply(block, mositure, value);
    }
}

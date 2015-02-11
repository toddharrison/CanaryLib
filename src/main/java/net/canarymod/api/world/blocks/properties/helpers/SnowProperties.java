package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.Snow;

/**
 * Snow properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SnowProperties extends BlockProperties {
    public static final BlockIntegerProperty layers = getInstanceFor(Snow, "layers");

    public static Block applyLayers(Block block, int value) {
        return apply(block, layers, value);
    }
}

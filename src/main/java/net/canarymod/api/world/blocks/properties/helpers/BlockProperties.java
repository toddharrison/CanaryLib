package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockProperty;

/**
 * Block properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class BlockProperties {

    static <T extends BlockProperty> T getInstanceFor(BlockType type, String property) {
        return Canary.factory().getObjectFactory().getPropertyInstance(type, property);
    }

    public static Block apply(Block block, BlockProperty property, Comparable value) throws IllegalArgumentException {
        block.setPropertyValue(property, value);
        return block;
    }
}

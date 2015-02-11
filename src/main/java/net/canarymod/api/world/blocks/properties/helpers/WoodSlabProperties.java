package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.OakWoodSlab;

/**
 * Wood Slab properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class WoodSlabProperties extends SlabProperties {
    public static final BlockEnumProperty variant = getInstanceFor(OakWoodSlab, "variant");

    public static Block applyVariant(Block block, WoodProperties.Variant value) {
        return apply(block, variant, value);
    }
}

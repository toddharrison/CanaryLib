package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.OakWood;

/**
 * Planks properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PlanksProperties extends WoodProperties {
    public static final BlockEnumProperty variant = getInstanceFor(OakWood, "variant");

    public static Block applyVariant(Block block, Variant value) {
        return apply(block, variant, value);
    }
}

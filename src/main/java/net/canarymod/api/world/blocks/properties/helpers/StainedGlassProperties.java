package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.DyeColor;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.WhiteGlass;

/**
 * Stained Glass properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class StainedGlassProperties extends BlockProperties {
    public static final BlockEnumProperty color = getInstanceFor(WhiteGlass, "color");

    public static Block applyColor(Block block, DyeColor value) {
        return apply(block, color, value);
    }
}

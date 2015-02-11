package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Furnace;

/**
 * Furnace properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FurnaceProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(Furnace, "facing");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }
}

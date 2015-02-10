package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

/**
 * Ender Chest properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class EnderChestProperties extends BlockProperties {
    private static final BlockEnumProperty facing = getInstanceFor(BlockType.EnderChest, "facing");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }
}

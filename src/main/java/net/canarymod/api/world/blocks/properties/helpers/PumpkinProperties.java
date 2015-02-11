package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Pumpkin;

/**
 * Pumpkin properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PumpkinProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(Pumpkin, "facing");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }
}

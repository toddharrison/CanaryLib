package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;

import static net.canarymod.api.world.blocks.BlockType.WallSign;

/**
 * Wall Sign properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class WallSignProperties extends BlockProperties {
    public static final BlockDirectionProperty facing = getInstanceFor(WallSign, "facing");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }
}

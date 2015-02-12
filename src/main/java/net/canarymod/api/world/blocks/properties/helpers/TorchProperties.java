package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;

import static net.canarymod.api.world.blocks.BlockType.Torch;

/**
 * Torch properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TorchProperties extends BlockProperties {
    public static final BlockDirectionProperty facing = getInstanceFor(Torch, "facing");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }
}

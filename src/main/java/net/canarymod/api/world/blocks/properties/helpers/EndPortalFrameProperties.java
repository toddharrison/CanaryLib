package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

/**
 * End Portal Frame properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public class EndPortalFrameProperties extends BlockProperties {
    private static BlockEnumProperty facing = getInstanceFor(BlockType.EndPortalFrame, "facing");
    private static BlockBooleanProperty eye = getInstanceFor(BlockType.EndPortalFrame, "eye");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyEye(Block block, boolean value) {
        return apply(block, eye, value);
    }
}

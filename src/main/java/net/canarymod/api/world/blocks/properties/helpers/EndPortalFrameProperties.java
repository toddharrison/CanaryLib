package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;

import static net.canarymod.api.world.blocks.BlockType.EndPortalFrame;

/**
 * End Portal Frame properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public class EndPortalFrameProperties extends BlockProperties {
    public static BlockDirectionProperty facing = getInstanceFor(EndPortalFrame, "facing");
    public static BlockBooleanProperty eye = getInstanceFor(EndPortalFrame, "eye");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyEye(Block block, boolean value) {
        return apply(block, eye, value);
    }
}

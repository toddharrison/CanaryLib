package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.SkeletonHead;

/**
 * Skull properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SkullProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(SkeletonHead, "facing");
    public static final BlockBooleanProperty noDrop = getInstanceFor(SkeletonHead, "nodrop");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyNoDrop(Block block, boolean value) {
        return apply(block, noDrop, value);
    }
}

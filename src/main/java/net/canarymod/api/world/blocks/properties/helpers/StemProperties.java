package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.MelonStem;

/**
 * Stem properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class StemProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(MelonStem, "facing");
    public static final BlockIntegerProperty age = getInstanceFor(MelonStem, "age");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyAge(Block block, int value) {
        return apply(block, age, value);
    }
}

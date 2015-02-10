package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

/**
 * Dispenser/Dropper properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DispenserProperties extends BlockProperties {
    private static final BlockEnumProperty facing = getInstanceFor(BlockType.Dispenser, "facing");
    private static final BlockEnumProperty triggered = getInstanceFor(BlockType.Dispenser, "triggered");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyTriggered(Block block, boolean value) {
        return apply(block, triggered, value);
    }
}

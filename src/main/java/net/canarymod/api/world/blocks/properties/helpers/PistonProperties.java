package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Piston;

/**
 * Piston properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PistonProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(Piston, "facing");
    public static final BlockBooleanProperty extended = getInstanceFor(Piston, "extended");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyExtended(Block block, boolean value) {
        return apply(block, extended, value);
    }
}

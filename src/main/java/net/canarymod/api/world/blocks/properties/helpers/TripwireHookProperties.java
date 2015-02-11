package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.TripwireHook;

/**
 * Tripwire Hook properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TripwireHookProperties extends BlockProperties {
    public static final BlockEnumProperty facing = getInstanceFor(TripwireHook, "facing");
    public static final BlockBooleanProperty
            powered = getInstanceFor(TripwireHook, "powered"),
            attached = getInstanceFor(TripwireHook, "attached"),
            suspended = getInstanceFor(TripwireHook, "suspended");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }

    public static Block applyAttached(Block block, boolean value) {
        return apply(block, attached, value);
    }

    public static Block applySuspended(Block block, boolean value) {
        return apply(block, suspended, value);
    }
}

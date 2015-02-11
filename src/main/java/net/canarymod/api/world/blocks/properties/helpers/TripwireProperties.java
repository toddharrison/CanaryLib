package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.Tripwire;

/**
 * Tripwire properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TripwireProperties extends BlockProperties {
    public static final BlockBooleanProperty
            powered = getInstanceFor(Tripwire, "powered"),
            suspended = getInstanceFor(Tripwire, "suspended"),
            attached = getInstanceFor(Tripwire, "attached"),
            disarmed = getInstanceFor(Tripwire, "disarmed"),
            north = getInstanceFor(Tripwire, "north"),
            east = getInstanceFor(Tripwire, "east"),
            south = getInstanceFor(Tripwire, "south"),
            west = getInstanceFor(Tripwire, "west");

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }

    public static Block applySuspended(Block block, boolean value) {
        return apply(block, suspended, value);
    }

    public static Block applyAttached(Block block, boolean value) {
        return apply(block, attached, value);
    }

    public static Block applyDisarmed(Block block, boolean value) {
        return apply(block, disarmed, value);
    }

    public static Block applyNorth(Block block, boolean value) {
        return apply(block, north, value);
    }

    public static Block applyEast(Block block, boolean value) {
        return apply(block, east, value);
    }

    public static Block applySouth(Block block, boolean value) {
        return apply(block, south, value);
    }

    public static Block applyWest(Block block, boolean value) {
        return apply(block, west, value);
    }
}

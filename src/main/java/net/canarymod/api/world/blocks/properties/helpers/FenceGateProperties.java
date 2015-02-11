package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.FenceGate;

/**
 * Fence Gate properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FenceGateProperties extends BlockProperties {
    private static final BlockBooleanProperty
            open = getInstanceFor(FenceGate, "open"),
            powered = getInstanceFor(FenceGate, "powered"),
            inWall = getInstanceFor(FenceGate, "in_wall");

    public static Block applyOpen(Block block, boolean value) {
        return apply(block, open, value);
    }

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }

    public static Block applyInWall(Block block, boolean value) {
        return apply(block, inWall, value);
    }
}

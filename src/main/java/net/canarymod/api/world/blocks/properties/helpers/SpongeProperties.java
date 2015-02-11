package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.Sponge;

/**
 * Sponge properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SpongeProperties extends BlockProperties {
    public static final BlockBooleanProperty wet = getInstanceFor(Sponge, "wet");

    public static Block applyWet(Block block, boolean value) {
        return apply(block, wet, value);
    }
}

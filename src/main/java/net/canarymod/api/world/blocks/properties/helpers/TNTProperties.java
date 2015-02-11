package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.Tnt;

/**
 * TNT properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TNTProperties extends BlockProperties {
    public static final BlockBooleanProperty explode = getInstanceFor(Tnt, "explode");

    public static Block applyExplode(Block block, boolean value) {
        return apply(block, explode, value);
    }
}

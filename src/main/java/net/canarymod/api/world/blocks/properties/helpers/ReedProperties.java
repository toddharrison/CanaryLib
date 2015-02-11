package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.Reed;

/**
 * Reed (Sugarcane) properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class ReedProperties extends BlockProperties {
    public static final BlockIntegerProperty age = getInstanceFor(Reed, "age");

    public static Block applyAge(Block block, int value) {
        return apply(block, age, value);
    }
}

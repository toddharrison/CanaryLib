package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.NetherWart;

/**
 * NetherWart properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class NetherWartProperties extends BlockProperties {
    public static final BlockIntegerProperty age = getInstanceFor(NetherWart, "age");

    public static Block applyAge(Block block, int value) {
        return apply(block, age, value);
    }
}

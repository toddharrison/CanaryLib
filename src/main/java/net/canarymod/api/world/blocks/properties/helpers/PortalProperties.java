package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Portal;

/**
 * Portal properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PortalProperties extends BlockProperties {
    public static final BlockEnumProperty axis = getInstanceFor(Portal, "axis");

    public static Block applyAxis(Block block, BlockFace.Axis value) {
        return apply(block, axis, value);
    }
}

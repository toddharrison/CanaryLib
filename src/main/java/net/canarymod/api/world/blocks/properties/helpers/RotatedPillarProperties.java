package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.HayBale;

/**
 * Rotated Pillar properties helper<br/>
 * Applies to<br/>
 * <ul>Hay</ul>
 * <ul>Log</ul>
 */
public abstract class RotatedPillarProperties extends BlockProperties {
    public static final BlockEnumProperty axis = getInstanceFor(HayBale, "axis");

    public static Block applyAxis(Block block, BlockFace.Axis value) {
        return apply(block, axis, value);
    }
}

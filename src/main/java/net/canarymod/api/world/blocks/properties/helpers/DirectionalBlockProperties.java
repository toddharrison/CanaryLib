package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;

import static net.canarymod.api.world.blocks.BlockType.BedBlock;

/**
 * Direction Block properties helper<br/>
 * Applies to<br/>
 * <ul>Bed</ul>
 * <ul>Cocoa</ul>
 * <ul>FenceGate</ul>
 * <ul>Pumpkin</ul>
 * <ul>Jack-o-Lantern</ul>
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class DirectionalBlockProperties extends BlockProperties {
    public static final BlockDirectionProperty facing = getInstanceFor(BedBlock, "facing");

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }
}

package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.LightWeightedPressurePlate;

/**
 * Weighted Pressure Plate properties helper<br/>
 * Applies to<br/>
 * <ul>Light (Gold) Pressure Plate</ul>
 * <ul>Heavy (Iron) Pressure Plate</ul>
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class WeightedPressurePlateProperties extends BlockProperties {
    public static final BlockIntegerProperty powered = getInstanceFor(LightWeightedPressurePlate, "powered");

    public static Block applyPowered(Block block, int value) {
        return apply(block, powered, value);
    }
}

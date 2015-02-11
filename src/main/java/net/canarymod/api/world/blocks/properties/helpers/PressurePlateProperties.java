package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.WoodPlate;

/**
 * Pressure Plate properties helper<br/>
 * Applies to<br/>
 * <ul>Wooden Pressure Plate</ul>
 * <ul>Stone Pressure Plate</ul>
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PressurePlateProperties extends BlockProperties {
    public static final BlockBooleanProperty powered = getInstanceFor(WoodPlate, "powered");

    public static Block applyPowered(Block block, boolean value) {
        return apply(block, powered, value);
    }
}

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

    /**
     * Weighted Pressure Plate powered property, Values: 0 - 15
     */
    public static final BlockIntegerProperty powered = getInstanceFor(LightWeightedPressurePlate, "powered");

    /**
     * Applies powered to the {@code Weighted Pressure Plate}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code int} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyPowered(Block block, int value) {
        return apply(block, powered, value);
    }
}

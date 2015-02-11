package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.StoneSlab;

/**
 * Slab properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class SlabProperties extends BlockProperties {
    public static final BlockEnumProperty half = getInstanceFor(StoneSlab, "half");

    /**
     * Slab half
     */
    public enum Half {
        UPPER,
        LOWER;

        public static Half valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyHalf(Block block, Half value) {
        return apply(block, half, value);
    }
}

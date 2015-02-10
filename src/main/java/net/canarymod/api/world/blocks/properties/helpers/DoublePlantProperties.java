package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

/**
 * Double Plant properties helper<br/>
 * Applies to<br/>
 * <ul>SUNFLOWER</ul>
 * <ul>SYRINGA</ul>
 * <ul>GRASS</ul>
 * <ul>FERN</ul>
 * <ul>ROSE</ul>
 * <ul>PAEONIA</ul>
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DoublePlantProperties extends BlockProperties {
    private static final BlockEnumProperty variant = getInstanceFor(BlockType.DoubleGrass, "variant");
    private static final BlockEnumProperty half = getInstanceFor(BlockType.DoubleGrass, "half");

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

    /**
     * Variant
     */
    public enum Variant {
        SUNFLOWER,
        SYRINGA,
        GRASS,
        FERN,
        ROSE,
        PAEONIA;

        public static Variant valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyVariant(Block block, Variant value) {
        return apply(block, variant, value);
    }

    public static Block applyHalf(Block block, Half value) {
        return apply(block, half, value);
    }
}

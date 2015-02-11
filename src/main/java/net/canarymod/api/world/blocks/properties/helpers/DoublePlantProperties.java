package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.DoubleGrass;

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
    public static final BlockEnumProperty
            variant = getInstanceFor(DoubleGrass, "variant"),
            half = getInstanceFor(DoubleGrass, "half");

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

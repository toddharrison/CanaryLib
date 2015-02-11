package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Prismarine;

/**
 * Prismarine properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PrismarineProperties extends BlockProperties {
    public static final BlockEnumProperty variant = getInstanceFor(Prismarine, "variant");

    /**
     * Prismarine variants
     */
    public enum Variant {
        ROUGH,
        BRICKS,
        DARK;

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
}

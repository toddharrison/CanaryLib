package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.StoneBrick;

/**
 * Stone Brick properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class StoneBrickProperties extends BlockProperties {
    public static final BlockEnumProperty variant = getInstanceFor(StoneBrick, "variant");

    /**
     * StoneBricks types
     */
    public enum Variant {
        DEFAULT,
        MOSSY,
        CRACKED,
        CHISELED;

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

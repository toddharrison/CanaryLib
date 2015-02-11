package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.QuartzBlock;

/**
 * Quartz properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class QuartzProperties extends BlockProperties {
    public static final BlockEnumProperty variant = getInstanceFor(QuartzBlock, "variant");

    /**
     * Quartz variants
     */
    public enum Variant {
        DEFAULT,
        CHISELED,
        LINES_Y,
        LINES_X,
        LINES_Z;

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

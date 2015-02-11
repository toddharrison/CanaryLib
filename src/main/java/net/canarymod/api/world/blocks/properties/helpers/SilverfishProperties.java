package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.StoneSilverFishBlock;

/**
 * Silverfish block properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SilverfishProperties extends BlockProperties {
    public static final BlockEnumProperty variant = getInstanceFor(StoneSilverFishBlock, "variant");

    /**
     * Silverfish block variants
     */
    public enum Variant {
        STONE,
        COBBLESTONE,
        STONEBRICK,
        MOSSY_STONEBRICK,
        CRACKED_STONEBRICK,
        CHISELED_STONEBRICK;

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

package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Poppy;

/**
 * Flower properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FlowerProperties extends BlockProperties {
    private static final BlockEnumProperty type = getInstanceFor(Poppy, "type");

    /**
     * Flower types
     */
    public enum Type {
        /**
         * Dandelion can't actually be applied, its included for consistance with native enumerations
         */
        DANDELION,
        POPPY,
        BLUE_ORCHID,
        ALLIUM,
        HOUSTONIA,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY;

        public static Type valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyType(Block block, Type value) {
        return apply(block, type, value);
    }
}

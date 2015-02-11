package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Sandstone;

/**
 * Sandstone (Not Red) properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SandstoneProperties extends BlockProperties {
    public static final BlockEnumProperty type = getInstanceFor(Sandstone, "type");

    /**
     * Sandstone types
     */
    public enum Type {
        DEFAULT,
        CHISELED,
        SMOOTH;

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

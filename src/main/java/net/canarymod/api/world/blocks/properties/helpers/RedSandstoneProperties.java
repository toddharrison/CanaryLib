package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.RedSandstone;

/**
 * Red Sandstone properties helper<br/>
 * (Cause Mojang didn't make a common type enum for sandstone)
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class RedSandstoneProperties extends BlockProperties {
    public static final BlockEnumProperty type = getInstanceFor(RedSandstone, "type");

    /**
     * Red Sandstone types
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

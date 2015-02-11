package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.TallGrass;

/**
 * Tall Grass properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TallGrassProperties extends BlockProperties {
    public static final BlockEnumProperty type = getInstanceFor(TallGrass, "type");

    /**
     * Tall grass variants
     */
    public enum Variant {
        DEAD_BUSH,
        GRASS,
        FERN;

        public static Variant valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyType(Block block, Variant value) {
        return apply(block, type, value);
    }
}

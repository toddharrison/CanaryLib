package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.StoneSlab;

/**
 * Stone Slab properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class StoneSlabProperties extends SlabProperties {
    public static final BlockBooleanProperty seamless = getInstanceFor(StoneSlab, "seamless");
    public static final BlockEnumProperty variant = getInstanceFor(StoneSlab, "variant");

    /**
     * Stone slab variants
     */
    public enum Variant {
        STONE,
        SAND,
        WOOD,
        COBBLESTONE,
        BRICK,
        SMOOTHBRICK,
        NETHERBRICK,
        QUARTZ;

        public static Variant valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applySeamless(Block block, boolean value) {
        return apply(block, seamless, value);
    }

    public static Block applyVariant(Block block, Variant value) {
        return apply(block, variant, value);
    }
}

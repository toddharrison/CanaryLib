package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

/**
 * Dirt properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DirtProperties extends BlockProperties {
    private static final BlockEnumProperty variant = getInstanceFor(BlockType.Dirt, "variant");
    private static final BlockBooleanProperty snowy = getInstanceFor(BlockType.Dirt, "snowy");

    public enum Variant {
        DIRT,
        COARSE_DIRT,
        PODZOL;

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

    public static Block applySnowy(Block block, boolean value) {
        return apply(block, snowy, value);
    }
}

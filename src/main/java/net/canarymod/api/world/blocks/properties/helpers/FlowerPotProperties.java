package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.Flowerpot;

/**
 * Flower Pot properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class FlowerPotProperties extends BlockProperties {
    public static final BlockIntegerProperty legacyData = getInstanceFor(Flowerpot, "legacy_data");
    public static final BlockEnumProperty contents = getInstanceFor(Flowerpot, "contents");

    /**
     * FlowerPot contents
     */
    public enum Contents {
        EMPTY,
        POPPY,
        BLUE_ORCHID,
        ALLIUM,
        HOUSTONIA,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY,
        DANDELION,
        OAK_SAPLING,
        SPRUCE_SAPLING,
        BIRCH_SAPLING,
        JUNGLE_SAPLING,
        ACACIA_SAPLING,
        DARK_OAK_SAPLING,
        MUSHROOM_RED,
        MUSHROOM_BROWN,
        DEAD_BUSH,
        FERN,
        CACTUS;

        public static Contents valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Applies legacy data to the {@code Flower Pot}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code int} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public Block applyLegacyData(Block block, int value) {
        return apply(block, legacyData, value);
    }

    public Block applyContents(Block block, Contents value) {
        return apply(block, contents, value);
    }
}

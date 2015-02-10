package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

/**
 * Brewing Stand properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class BrewingStandProperties extends BlockProperties {
    private static final BlockBooleanProperty hasBottle0 = getInstanceFor(BlockType.BrewingStand, "has_bottle_0");
    private static final BlockBooleanProperty hasBottle1 = getInstanceFor(BlockType.BrewingStand, "has_bottle_1");
    private static final BlockBooleanProperty hasBottle2 = getInstanceFor(BlockType.BrewingStand, "has_bottle_2");

    /**
     * Applies whether the {@code Brewing Stand}'s slot 0 is occupied or not
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code boolean} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyBottle0(Block block, boolean value) {
        return apply(block, hasBottle0, value);
    }

    /**
     * Applies whether the {@code Brewing Stand}'s slot 1 is occupied or not
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code boolean} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyBottle1(Block block, boolean value) {
        return apply(block, hasBottle1, value);
    }

    /**
     * Applies whether the {@code Brewing Stand}'s slot 2 is occupied or not
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code boolean} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyBottle2(Block block, boolean value) {
        return apply(block, hasBottle2, value);
    }
}

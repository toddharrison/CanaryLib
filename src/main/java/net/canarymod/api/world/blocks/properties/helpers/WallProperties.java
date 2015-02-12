package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.CobblestoneWall;

/**
 * Wall properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class WallProperties extends BlockProperties {
    public static final BlockBooleanProperty
            up = getInstanceFor(CobblestoneWall, "up"),
            north = getInstanceFor(CobblestoneWall, "north"),
            east = getInstanceFor(CobblestoneWall, "east"),
            south = getInstanceFor(CobblestoneWall, "south"),
            west = getInstanceFor(CobblestoneWall, "west");
    public static final BlockEnumProperty variant = getInstanceFor(CobblestoneWall, "variant");

    /**
     * Wall variants
     */
    public enum Variant {
        NORMAL,
        MOSSY;

        public static Variant valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Applies whether the {@code Wall} extends from the center post up or not
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
    public static Block applyUp(Block block, boolean value) {
        return apply(block, up, value);
    }

    /**
     * Applies whether the {@code Wall} extends from the center post north or not
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
    public static Block applyNorth(Block block, boolean value) {
        return apply(block, north, value);
    }

    /**
     * Applies whether the {@code Wall} extends from the center post east or not
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
    public static Block applyEast(Block block, boolean value) {
        return apply(block, east, value);
    }

    /**
     * Applies whether the {@code Wall} extends from the center post south or not
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
    public static Block applySouth(Block block, boolean value) {
        return apply(block, south, value);
    }

    /**
     * Applies whether the {@code Wall} extends from the center post west or not
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
    public static Block applyWest(Block block, boolean value) {
        return apply(block, west, value);
    }

    public static Block applyVariant(Block block, Variant value) {
        return apply(block, variant, value);
    }
}

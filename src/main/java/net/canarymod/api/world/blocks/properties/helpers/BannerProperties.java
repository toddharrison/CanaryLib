package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.StandingBanner;

/**
 * Banner properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class BannerProperties extends BlockProperties {
    private static final BlockIntegerProperty rotation = getInstanceFor(StandingBanner, "rotation");
    private static final BlockDirectionProperty facing = getInstanceFor(StandingBanner, "facing");

    /**
     * Rotation helper
     *
     * @author Jason Jones (darkdiplomat)
     */
    public enum Rotation {
        SOUTH,
        SOUTHBYSOUTHWEST,
        SOUTHWEST,
        WESTBYSOUTHWEST,
        WEST,
        WESTBYNORTHWEST,
        NORTHWEST,
        NORTHBYNORTHWEST,
        NORTH,
        NORTHBYNORTHEAST,
        NORTHEAST,
        EASTBYNORTHEAST,
        EAST,
        EASTBYSOUTHEAST,
        SOUTHEAST,
        SOUTHBYSOUTHEAST
    }

    /**
     * Applies a {@link net.canarymod.api.world.blocks.BlockFace} facing to the {@code Banner (Wall Banner)}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@link net.canarymod.api.world.blocks.BlockFace} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    /**
     * Applies rotation to the {@code Banner (Standing Banner)}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code int} value to apply (0 - 15)
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyRotation(Block block, int value) {
        return apply(block, rotation, value);
    }

    /**
     * Applies rotation to the {@code Banner (Standing Banner)}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@link net.canarymod.api.world.blocks.properties.helpers.BannerProperties.Rotation} value to apply
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyRoation(Block block, Rotation value) {
        return applyRotation(block, value.ordinal());
    }
}

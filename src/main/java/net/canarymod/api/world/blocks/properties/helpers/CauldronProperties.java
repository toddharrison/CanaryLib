package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

/**
 * Cauldron properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class CauldronProperties extends BlockProperties {
    private static final BlockIntegerProperty level = getInstanceFor(BlockType.Cauldron, "level");

    /**
     * Applies rotation to the {@code Banner}
     *
     * @param block
     *         the {@link net.canarymod.api.world.blocks.Block} to be modified
     * @param value
     *         the {@code int} value to apply (0 - 3)
     *
     * @return the Block with adjusted state (NOTE: Original Block object is also modified, using the return is unnecessary)
     *
     * @throws java.lang.NullPointerException
     *         Should {@code block} or {@code value} be null
     * @throws java.lang.IllegalArgumentException
     *         Should an invalid property be applied
     */
    public static Block applyLevel(Block block, int value) {
        return apply(block, level, value);
    }
}

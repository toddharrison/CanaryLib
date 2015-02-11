package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;

import static net.canarymod.api.world.blocks.BlockType.OakSapling;

/**
 * Sapling properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SaplingProperties extends WoodProperties {
    public static final BlockEnumProperty type = getInstanceFor(OakSapling, "type");
    public static final BlockIntegerProperty stage = getInstanceFor(OakSapling, "stage");

    public static Block applyType(Block block, Variant value) {
        return apply(block, type, value);
    }

    public static Block applyStage(Block block, int value) {
        return apply(block, stage, value);
    }
}

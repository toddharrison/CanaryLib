package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.OakLeaves;

/**
 * Leaves properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class LeavesProperties extends WoodProperties {
    public static final BlockBooleanProperty decayable = getInstanceFor(OakLeaves, "decayable"),
            checkDecay = getInstanceFor(OakLeaves, "check_decay");
    public static final BlockEnumProperty variant = getInstanceFor(OakLeaves, "variant");

    public static Block applyDecayable(Block block, boolean value) {
        return apply(block, decayable, value);
    }

    public static Block applyCheckDecay(Block block, boolean value) {
        return apply(block, checkDecay, value);
    }

    public static Block applyVariant(Block block, Variant value) {
        return apply(block, variant, value);
    }
}

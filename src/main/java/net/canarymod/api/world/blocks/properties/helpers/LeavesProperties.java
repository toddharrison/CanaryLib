package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.visualillusionsent.utils.Verify;

import static net.canarymod.api.world.blocks.BlockType.AcaciaLeaves;
import static net.canarymod.api.world.blocks.BlockType.OakLeaves;

/**
 * Leaves properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class LeavesProperties extends WoodProperties {
    public static final BlockBooleanProperty
            decayable = getInstanceFor(OakLeaves, "decayable"),
            checkDecay = getInstanceFor(OakLeaves, "check_decay");
    public static final BlockEnumProperty
            variantOld = getInstanceFor(OakLeaves, "variant"),
            variantNew = getInstanceFor(AcaciaLeaves, "variant");

    public static Block applyDecayable(Block block, boolean value) {
        return apply(block, decayable, value);
    }

    public static Block applyCheckDecay(Block block, boolean value) {
        return apply(block, checkDecay, value);
    }

    public static Block applyVariant(Block block, Variant value) {
        Verify.notNull(block, "Block block");
        if (block.getType().getMachineName().equals(AcaciaLeaves.getMachineName())) {
            return apply(block, variantNew, value);
        }
        return apply(block, variantOld, value);
    }
}

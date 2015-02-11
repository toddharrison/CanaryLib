package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.visualillusionsent.utils.Verify;

import static net.canarymod.api.world.blocks.BlockType.AcaciaLeaves;
import static net.canarymod.api.world.blocks.BlockType.AcaciaLog;
import static net.canarymod.api.world.blocks.BlockType.OakLog;

/**
 * Log properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class LogProperties extends WoodProperties {
    public static final BlockEnumProperty
            axis = getInstanceFor(OakLog, "axis"),
            variantOld = getInstanceFor(OakLog, "variant"),
            variantNew = getInstanceFor(AcaciaLog, "variant");

    /**
     * Log Axis
     */
    public enum Axis {
        X,
        Y,
        Z,
        NONE;

        public static Axis valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyAxis(Block block, Axis value) {
        return apply(block, axis, value);
    }

    public static Block applyVariant(Block block, Variant value) {
        Verify.notNull(block, "Block block");
        if (block.getType().getMachineName().equals(AcaciaLeaves.getMachineName())) {
            return apply(block, variantNew, value);
        }
        return apply(block, variantOld, value);
    }
}

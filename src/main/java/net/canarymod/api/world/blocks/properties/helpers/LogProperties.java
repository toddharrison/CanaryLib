package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.visualillusionsent.utils.Verify;

import static net.canarymod.api.world.blocks.BlockType.*;

/**
 * Log properties helper<p/>
 * NOTE: There are potentially 2 different axis properties for Log
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class LogProperties extends RotatedPillarProperties {
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

    public static Block applyVariant(Block block, WoodProperties.Variant value) {
        Verify.notNull(block, "Block block");
        if (block.getType().getMachineName().equals(AcaciaLeaves.getMachineName())) {
            return apply(block, variantNew, value);
        }
        return apply(block, variantOld, value);
    }
}

package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.Trapdoor;

/**
 * TrapDoor properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TrapDoorProperties extends BlockProperties {
    public static final BlockDirectionProperty facing = getInstanceFor(Trapdoor, "facing");
    public static final BlockEnumProperty half = getInstanceFor(Trapdoor, "half");
    public static final BlockBooleanProperty open = getInstanceFor(Trapdoor, "open");

    /**
     * TrapDoor half
     */
    public enum Half {
        UPPER,
        LOWER;

        public static Half valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyHalf(Block block, Half value) {
        return apply(block, half, value);
    }

    public static Block applyOpen(Block block, boolean value) {
        return apply(block, open, value);
    }
}

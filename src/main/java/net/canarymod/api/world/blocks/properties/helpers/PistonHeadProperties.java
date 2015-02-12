package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockDirectionProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;

import static net.canarymod.api.world.blocks.BlockType.PistonHead;

/**
 * Piston Head properties<br/>
 * NOT TO BE CONFUSED WITH THE ACTUAL PISTON BASE
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PistonHeadProperties extends BlockProperties {
    public static final BlockDirectionProperty facing = getInstanceFor(PistonHead, "facing");
    public static final BlockEnumProperty type = getInstanceFor(PistonHead, "type");
    public static final BlockBooleanProperty short_ = getInstanceFor(PistonHead, "short");

    /**
     * Piston Head types
     *
     * @author Jason Jones (darkdiplomat)
     */
    public enum Type {
        DEFAULT,
        STICKY;

        public static Type valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyFacing(Block block, BlockFace value) {
        return apply(block, facing, value);
    }

    public static Block applyType(Block block, Type value) {
        return apply(block, type, value);
    }

    public static Block applyShort(Block block, boolean value) {
        return apply(block, short_, value);
    }
}

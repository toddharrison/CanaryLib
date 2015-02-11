package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockEnumProperty;
import net.visualillusionsent.utils.Verify;

import static net.canarymod.api.world.blocks.BlockType.DetectorRail;
import static net.canarymod.api.world.blocks.BlockType.PoweredRail;
import static net.canarymod.api.world.blocks.BlockType.Rail;

/**
 * Rail properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class RailProperties extends BlockProperties {
    public static final BlockEnumProperty
            shapeNormalRail = getInstanceFor(Rail, "shape"),
            shapePoweredRail = getInstanceFor(PoweredRail, "shape"),
            shapeDetectorRail = getInstanceFor(DetectorRail, "shape");
    public static final BlockBooleanProperty
            poweredPoweredRail = getInstanceFor(PoweredRail, "powered"),
            poweredDectectorRail = getInstanceFor(DetectorRail, "powered");

    /**
     * Rail directions
     */
    public enum Direction {
        NORTH_SOUTH,
        EAST_WEST,
        ASCENDING_EAST,
        ASCENDING_WEST,
        ASCENDING_NORTH,
        ASCENDING_SOUTH,
        SOUTH_EAST,
        SOUTH_WEST,
        NORTH_WEST,
        NORTH_EAST;

        public static Direction valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    public static Block applyShape(Block block, Direction value) {
        Verify.notNull(block, "Block block");
        if (block.getType().equals(PoweredRail)) {
            return apply(block, shapePoweredRail, value);
        }
        else if (block.getType().equals(DetectorRail)) {
            return apply(block, shapeDetectorRail, value);
        }
        else {
            return apply(block, shapeNormalRail, value);
        }
    }

    public static Block applyPowered(Block block, boolean value) {
        Verify.notNull(block, "Block block");
        if (block.getType().equals(PoweredRail)) {
            return apply(block, poweredPoweredRail, value);
        }
        else {
            return apply(block, poweredDectectorRail, value);
        }
    }
}

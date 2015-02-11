package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.BlockProperty;
import net.visualillusionsent.utils.Verify;

/**
 * Block properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class BlockProperties {

    static <T extends BlockProperty> T getInstanceFor(BlockType type, String property) {
        return Canary.factory().getObjectFactory().getPropertyInstance(type, property);
    }

    public static Block apply(Block block, BlockProperty property, Comparable value) throws IllegalArgumentException {
        Verify.notNull(block, "Block block");
        Verify.notNull(property, "BlockProperty property");
        Verify.notNull(value, "Comparable value");
        block.setPropertyValue(property, value);
        return block;
    }
/*
SAPLING | type | TreeType
SAPLING | stage | int 0, 1
SILVERFISH | variant | SilverfishBlockType
SKULL | facing | BlockFace
SKULL | nodrop | boolean
SLAB (ALL) | half | BlockVerticalHalf
SNOW | layers | int (1 - 8)
SPONGE | wet | boolean
STAINEDGLASS | color | DyeColor
STAINEDGLASSPANE | color | DyeColor
STAIRS | facing | BlockFace (excluding TOP & BOTTOM)
STAIRS | half | BlockVerticalHalf
STAIRS | shape | StairsShape
STANDINGSIGN | rotation | int (0 - 15)
STEM | age | int (0 - 7)
STEM | facing | BlockFace (excluding BOTTOM)
STONE | variant | StoneType
STONEBRICK | variant | StoneBrickType
STONESLAB | seamless | boolean
STONESLAB | variant | StoneSlabType
STONESLAB (New [at time only covers RED_SANDSTONE]) | variant | StoneSlabNewType
TALLGRASS (Dead Bush, Grass, Fern) | type | TallGrassType
TNT | explode | boolean
TORCH | facing | BlockFace (excluding BOTTOM)
TRAPDOOR | facing | BlockFace (excluding TOP & BOTTOM)
TRAPDOOR | open | boolean
TRAPDOOR | half | BlockVerticalHalf
TRIPWIRE | powered | boolean
TRIPWIRE | suspended | boolean
TRIPWIRE | attached | boolean
TRIPWIRE | disarmed | boolean
TRIPWIRE | north | boolean
TRIPWIRE | east | boolean
TRIPWIRE | south | boolean
TRIPWIRE | west | boolean
TRIPWIREHOOK | facing | BlockFace (excluding TOP & BOTTOM)
TRIPWIREHOOK | powered | boolean
TRIPWIREHOOK | attached | boolean
TRIPWIREHOOK | suspended | boolean
VINE | up | boolean
VINE | north | boolean
VINE | east | boolean
VINE | south | boolean
VINE | west | boolean
WALL | up | boolean
WALL | north | boolean
WALL | east | boolean
WALL | south | boolean
WALL | west | boolean
WALL | variant | WallType
WALLSIGN | facing | BlockFace (excluding TOP & BOTTOM)
WOODSLAB | variant | TreeType
*/
}

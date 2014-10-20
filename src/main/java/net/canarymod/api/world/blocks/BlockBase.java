package net.canarymod.api.world.blocks;

import com.google.common.annotations.Beta;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Position;

import java.util.Random;

/**
 * Block wrapper
 *
 * @author Jason (darkdiplomat)
 */
@Beta
public interface BlockBase {

    boolean isFullBlock();

    /**
     * Gets how much light is subtracted for going through this block
     *
     * @return light opacity
     */
    int getLightOpacity();

    /**
     * Gets the amount of light emitted by the Block
     *
     * @return light value
     */
    int getLightValue();

    /**
     * Gets if the block should use the brightest neighbor light value as its own
     *
     * @return {@code true} if using neighbor; {@code false} if not
     */
    boolean getUseNeighborBrightness();

    /**
     * Gets the material of the Block
     *
     * @return material
     */
    BlockMaterial getMaterial();

    /**
     * Gets the map color code
     *
     * @param block
     *         the {@link Block} instance (used in cases where block changes colors)
     *
     * @return map color
     */
    MapColor getMapColor(Block block);

    /**
     * Gets if a material is a normal solid opaque cube
     *
     * @return boolean
     */
    boolean isSolidFullCube();

    boolean isNormalCube();

    boolean isVisuallyOpaque();

    boolean isFullCube();

    /**
     * Checks if the block can be passed through (like tall grass or an open door)
     *
     * @param block
     *         the {@link Block} instance (used for checking Door states, etc...)
     * @param pos
     *         the position in the world the block is at
     *
     * @return {@code true} if it can be passed through; {@code false} otherwise
     */
    boolean isPassable(Block block, Position pos);

    int getRenderType();

    /**
     * Gets if the Block can be replaced by another block (like tall grass)
     *
     * @param worldIn
     *         the {@link World} the block is in
     * @param pos
     *         the position in the world the block is at
     *
     * @return {@code true} if replaceable; {@code false} if not
     */
    boolean isReplaceable(World worldIn, Position pos);

    /**
     * Gets how many hits it takes to break a block.
     *
     * @param worldIn
     *         the {@link World} the block is in
     * @param pos
     *         the position in the world the block is at
     *
     * @return hardness
     */
    float getBlockHardness(World worldIn, Position pos);

    /**
     * Gets whether or not this block is of a type that needs random ticking. (like playing effects or outputting power changes)
     *
     * @return boolean
     */
    boolean ticksRandomly();

    /**
     * Gets whether or not this block is of a type that has a TileEntity container associated with it (like chests, dispenser, etc...)
     *
     * @return {@code true} if tile entity; {@code false} if not
     */
    boolean hasTileEntity();

    boolean isOpaqueCube();

    /**
     * Gets if the block is collidable
     *
     * @return {@code true} if collidable; {@code false} otherwise
     */
    boolean isCollidable();

    /**
     * Gets how many world ticks should pass before ticking
     */
    int tickRate(World worldIn);

    /**
     * Gets the amount that would be dropped when the Block is harvested.<br>
     * NOTE: Some blocks are random on the amount so returns may differ for those each call
     *
     * @return the quantity that would be dropped
     */
    int quantityDropped(Random random);

    /**
     * Gets the damage of the Block as it would be dropped as an {@link net.canarymod.api.inventory.Item}
     *
     * @return the damage
     */
    int damageDropped(Block block);

    float getExplosionResistance(Entity exploder);

    /**
     * Gets the block boundaries minX value
     *
     * @return boundary minX
     */
    double getBlockBoundsMinX();

    /**
     * Gets the block boundaries maxX value
     *
     * @return boundary maxX
     */
    double getBlockBoundsMaxX();

    /**
     * Gets the block boundaries minY value
     *
     * @return boundary minY
     */
    double getBlockBoundsMinY();

    /**
     * Gets the block boundaries maxY value
     *
     * @return boundary maxY
     */
    double getBlockBoundsMaxY();

    /**
     * Gets the block boundaries minZ value
     *
     * @return boundary minZ
     */
    double getBlockBoundsMinZ();

    /**
     * Gets the block boundaries maxZ value
     *
     * @return boundary maxZ
     */
    double getBlockBoundsMaxZ();

    /**
     * Gets if the Block can provide power<br/>
     * NOTE: Redstone wire may return either true or false depending on if its receiving power
     *
     * @return {@code true} if can provide power; {@code false} if not
     */
    boolean canProvidePower();

    /**
     * Gets the localized name of this block.
     *
     * @return localized name
     */
    String getLocalizedName();

    /**
     * Gets if the block is counted for mined and placed in the Statistics
     *
     * @return {@code true} if enabled; {@code false} otherwise
     */
    boolean getEnableStats();

    int getMobilityFlag();

    /**
     * Gets whether the block requires update calls
     *
     * @return {@code true} if require updates; {@code false} otherwise
     */
    boolean requiresUpdates();

    boolean hasComparatorInputOverride();

    int getComparatorInputOverride(World worldIn, Position pos);
}

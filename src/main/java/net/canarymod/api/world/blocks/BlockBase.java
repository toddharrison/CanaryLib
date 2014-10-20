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

    int getLightOpacity();

    int getLightValue();

    boolean getUseNeighborBrightness();

    BlockMaterial getMaterial();

    MapColor getMapColor();

    boolean isSolidFullCube();

    boolean isNormalCube();

    boolean isVisuallyOpaque();

    boolean isFullCube();

    boolean isPassable(Block block, Position pos);

    int getRenderType();

    boolean isReplaceable(World worldIn, Position pos);

    float getBlockHardness(World worldIn, Position pos);

    boolean ticksRandomly();

    boolean hasTileEntity();

    boolean isOpaqueCube();

    boolean isCollidable();

    int tickRate(World worldIn);

    int quantityDropped(Random random);

    int damageDropped(Block block);

    float getExplosionResistance(Entity exploder);

    double getBlockBoundsMinX();

    double getBlockBoundsMaxX();

    double getBlockBoundsMinY();

    double getBlockBoundsMaxY();

    double getBlockBoundsMinZ();

    double getBlockBoundsMaxZ();

    boolean canProvidePower();

    String getLocalizedName();

    boolean getEnableStats();

    int getMobilityFlag();

    boolean requiresUpdates();

    boolean hasComparatorInputOverride();

    int getComparatorInputOverride(World worldIn, Position pos);
}

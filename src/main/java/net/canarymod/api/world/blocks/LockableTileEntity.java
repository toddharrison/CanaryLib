package net.canarymod.api.world.blocks;

import com.google.common.annotations.Beta;
import net.canarymod.api.inventory.Inventory;

/**
 * TileEntityLockable wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface LockableTileEntity extends TileEntity, Inventory {

    String getCode();

    @Beta
    void setCode(String code);// Unknown yet if changing a code will work

    boolean hasCodeSet();
}

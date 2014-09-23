package net.canarymod.api.inventory;

import com.google.common.annotations.Beta;
import net.canarymod.api.nbt.CompoundTag;

/**
 * Map Data API
 *
 * @author Jason (darkdiplomat)
 */
@Beta
public interface MapData {

    /**
     * Gets the name of the Map (typically map_#)
     *
     * @return the name of the Map
     */
    public String getMapName();

    /**
     * Sets the name of the Map
     *
     * @param name
     *         the maps new name
     */
    public void setMapName(String name);

    /**
     * Gets the X-wise (Block Coordinates) center for the Map
     *
     * @return center x
     */
    public int getXCenter();

    /**
     * Gets the Z-wise (Block Coordinates) center for the Map
     *
     * @return center z
     */
    public int getZCenter();

    /**
     * Sets the X-wise (Block Coordinates) center for the Map
     *
     * @param xCenter
     *         the new x-wise center
     */
    public void setXCenter(int xCenter);

    /**
     * Sets the Z-wise (Block Coordinates) center for the Map
     *
     * @param zCenter
     *         the new z-wise center
     */
    public void setZCenter(int zCenter);

    /**
     * Gets the scale of the map (1 to 4)
     *
     * @return map scale
     */
    public byte getScale();

    /**
     * Sets the scale of the map (1 to 4)
     *
     * @param scale
     *         the new map scale
     */
    public void setScale(byte scale);

    /**
     * Gets the colors on the map
     *
     * @return the map colors
     */
    public byte[] getColors();

    /**
     * Sets the colors on the map
     *
     * @param colors
     *         the map colors
     */
    public void setColors(byte[] colors);

    /**
     * Sends an update to holders of the Map
     */
    public void update();

    /**
     * Marks a vertical range of pixels as being modified so they will be resent to clients.
     *
     * @param x
     * @param yLowest
     * @param yHighest
     */
    public void setColumnDirty(int x, int yLowest, int yHighest);

    /**
     * Sets the data values of this map from the NBTData
     *
     * @param compoundTag
     *         the NBT Data to set to the Map
     */
    public void setNBTData(CompoundTag compoundTag);

    /**
     * Gets the data values of this map and stores them in the given NBTData
     *
     * @param compoundTag
     *         the CompoundTag to store the Map data into
     */
    public void getNBTData(CompoundTag compoundTag);
}

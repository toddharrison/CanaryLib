package net.canarymod.api.world.position;

import net.canarymod.Canary;
import net.canarymod.MathHelp;
import net.canarymod.api.factory.NBTFactory;
import net.canarymod.api.nbt.FloatTag;
import net.canarymod.api.nbt.ListTag;

/**
 * @author Jason Jones (darkdiplomat)
 */
public class Rotations {
    private static final NBTFactory nbtFactory = Canary.factory().getNBTFactory();
    private float x, y, z;

    public Rotations(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Gets the X-axis rotation
     *
     * @return X-axis rotation
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the X-axis rotation
     *
     * @param x
     *         x axis, value between -180 and 180
     */
    public void setX(float x) {
        this.x = MathHelp.setInRange(x, -180, 180);
    }

    /**
     * Gets the Y-axis rotation
     *
     * @return Y-axis rotation
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the Y-axis rotation
     *
     * @param y
     *         y axis, value between -180 and 180
     */
    public void setY(float y) {
        this.y = MathHelp.setInRange(y, -180, 180);
    }

    /**
     * Gets the Z-axis rotation
     *
     * @return Z-axis rotation
     */
    public float getZ() {
        return z;
    }

    /**
     * Sets the Z-axis rotation
     *
     * @param z
     *         z axis, value between -180 and 180
     */
    public void setZ(float z) {
        this.z = MathHelp.setInRange(z, -180, 180);
    }

    /**
     * Serializes into a {@link net.canarymod.api.nbt.ListTag}
     *
     * @return ListTag
     */
    public ListTag<FloatTag> toNBT() {
        ListTag<FloatTag> tag = nbtFactory.newListTag();
        tag.add(nbtFactory.newFloatTag(x));
        tag.add(nbtFactory.newFloatTag(y));
        tag.add(nbtFactory.newFloatTag(z));
        return tag;
    }

    /**
     * Deserialized from a {@link net.canarymod.api.nbt.ListTag}
     *
     * @param rotations
     *         the list tag to deserialize
     *
     * @return a new {@link net.canarymod.api.world.position.Rotations} object
     */
    public static Rotations fromNBT(ListTag<FloatTag> rotations) {
        return new Rotations(rotations.get(0).getValue(), rotations.get(1).getValue(), rotations.get(2).getValue());
    }
}

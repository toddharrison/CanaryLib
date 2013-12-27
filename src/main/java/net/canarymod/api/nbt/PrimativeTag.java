package net.canarymod.api.nbt;

/**
 * @author Jason (darkdiplomat)
 */
public interface PrimativeTag extends BaseTag {

    public abstract long getLongValue();

    public abstract int getIntValue();

    public abstract short getShortValue();

    public abstract byte getByteValue();

    public abstract double getDoubleValue();

    public abstract float getFloatValue();

}

package net.canarymod.api.nbt;

/**
 * Base for all NBT storage tags.
 *
 * @author gregthegeek
 */
public interface BaseTag<T> {

    /**
     * Returns the ID number of the type of tag this is.
     *
     * @return the tag id
     */
    public byte getTypeId();

    /**
     * Gets the Enum version of the id
     *
     * @return The {@link NBTTagType} for the given id
     */
    public NBTTagType getType();

    /**
     * Copies the Tag
     *
     * @return a new instance of the tag being copied
     */
    public T copy();

}

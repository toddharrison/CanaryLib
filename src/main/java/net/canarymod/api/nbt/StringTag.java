package net.canarymod.api.nbt;

/**
 * An NBT tag that stores a string.
 *
 * @author gregthegeek
 */
public interface StringTag extends BaseTag<StringTag> {

    /**
     * Returns the value associated with this tag.
     *
     * @return the String value
     */
    public String getValue();

    /**
     * Sets the value associated with this tag.
     *
     * @param value
     *         the String value
     */
    public void setValue(String value);

}

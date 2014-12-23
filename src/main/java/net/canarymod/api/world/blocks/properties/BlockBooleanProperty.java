package net.canarymod.api.world.blocks.properties;

/**
 * PropertyBool wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface BlockBooleanProperty extends BlockProperty {

    /**
     * Tests if a given value can be applied to the Property
     *
     * @param value
     *         the value to test
     *
     * @return {@code true} if can apply; {@code false} if not
     */
    boolean canApply(Boolean value);
}

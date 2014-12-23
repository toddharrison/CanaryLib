package net.canarymod.api.world.blocks.properties;

import java.util.Collection;

/**
 * Block IProperty/PropertyHelper wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface BlockProperty {

    /**
     * Gets the Property's name
     *
     * @return property name
     */
    String getName();

    /**
     * Gets the collection of allowed values
     *
     * @return allowed values
     */
    Collection<Comparable> getAllowedValues();

    /**
     * Gets the Class type of the allowed values
     *
     * @return class type
     */
    Class getValueClass();

    /**
     * Tests if a given value can be applied to the Property
     *
     * @param value
     *         the value to test
     *
     * @return {@code true} if can apply; {@code false} if not
     */
    boolean canApply(Comparable value);
}

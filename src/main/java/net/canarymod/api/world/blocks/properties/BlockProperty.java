package net.canarymod.api.world.blocks.properties;

import java.util.Collection;

/**
 * Block IProperty/PropertyHelper wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface BlockProperty {

    String getName();

    Collection<?> getAllowedValues();

    Class getValueClass();

    String getName(Comparable value);

}

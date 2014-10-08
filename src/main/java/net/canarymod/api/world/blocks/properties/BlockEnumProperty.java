package net.canarymod.api.world.blocks.properties;

/**
 * PropertyEnum wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface BlockEnumProperty extends BlockProperty {

    String getName(Enum value);
}

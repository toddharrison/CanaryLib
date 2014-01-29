package net.canarymod.api.attributes;

import com.google.common.annotations.Beta;

import java.util.UUID;

/**
 * @author Jason (darkdiplomat)
 */
@Beta
public interface ModifiedAttribute {

    Attribute getAttribute();

    double getBaseValue();

    void setBaseValue(double value);

    AttributeModifier getModifier(UUID uuid);

    void apply(AttributeModifier attributeModifier);

    void remove(AttributeModifier attributeModifier);

    double getValue();
}

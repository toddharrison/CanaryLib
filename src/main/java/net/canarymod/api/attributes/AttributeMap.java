package net.canarymod.api.attributes;

import com.google.common.annotations.Beta;
import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * @author Jason (darkdiplomat)
 */
@Beta
public interface AttributeMap {

    ModifiedAttribute getModifiedAttribute(Attribute attribute);

    ModifiedAttribute getModifiedAttributeByName(String name);

    ModifiedAttribute registerAttribute(Attribute attribute);

    Collection<ModifiedAttribute> getAllAttributes();

    void addModifier(ModifiedAttribute attribute);

    void removeModifiers(Multimap<String, AttributeModifier> map);

    void applyModifiers(Multimap<String, AttributeModifier> map);
}

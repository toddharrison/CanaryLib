package net.canarymod.api.factory;

import com.google.common.annotations.Beta;
import net.canarymod.api.attributes.Attribute;

/**
 * @author Jason (darkdiplomat)
 */
@Beta
public interface AttributeFactory {

    /**
     * Gets a Generic Attribute, as seen in SharedMonsterAttributes
     * <p/>
     * Names can be seen in {@link net.canarymod.api.attributes.GenericAttribute}
     *
     * @param nativeName
     *         the name of the generic attribute
     *
     * @return generic Attribute
     */
    Attribute getGenericAttribute(String nativeName);
}

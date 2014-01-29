package net.canarymod.api.attributes;

import com.google.common.annotations.Beta;

import java.util.UUID;

/**
 * @author Jason (darkdiplomat)
 */
@Beta
public interface AttributeModifier {

    UUID getUUID();

    String getName();

    int getOperation();

    double getAmount();

    boolean isSaved();

    AttributeModifier setSaved(boolean saved);
}

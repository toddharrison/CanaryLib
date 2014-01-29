package net.canarymod.api.attributes;

import com.google.common.annotations.Beta;

/**
 * @author Jason (darkdiplomat)
 */
@Beta
public interface RangedAttribute extends Attribute {

    RangedAttribute setDescription(String description);

    String getDescription();

    double setValue(double value);

    double getMaxValue();

    double getMinValue();
}

package net.canarymod.api.entity;

import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.inventory.Item;

/**
 * ArmorStand wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface ArmorStand extends LivingBase {

    public enum Slot {
        // TODO: This may not be accurate

        HOLDING,
        FEET,
        LEGS,
        BODY,
        HEAD

    }

    Item[] getAllEquipment();

    void setAllEquipment(Item[] item);

    Item getEquipment(Slot slot);

    void setEquipment(Slot slot, Item item);

    boolean isSmall();

    void setSmall(boolean small);

    boolean showArms();

    void setShowArms(boolean set);

    boolean isSlotDiabled(Slot slot);

    void disableSlot(Slot slot);

    void enableSlot(Slot slot);

    boolean hasBasePlate();

    void setBasePlate(boolean basePlate);

    boolean gravity();

    void setGravity(boolean gravity);

    //TODO: Pose
}

package net.canarymod.api.entity.living.humanoid.npc.ai;

import net.canarymod.api.inventory.Item;

/**
 * @author Jason (darkdiplomat)
 */
public final class PickupItem extends NPCAI {
    private final Item pickedUp;

    public PickupItem(Item pickedUp) {
        this.pickedUp = pickedUp;
    }

    public final Item getItemPickedUp() {
        return pickedUp;
    }

    public final String toString() {
        return String.format("NPCAI.PickupItem[Item=%s]", pickedUp);
    }
}

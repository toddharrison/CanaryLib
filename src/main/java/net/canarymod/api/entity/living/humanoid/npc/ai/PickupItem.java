package net.canarymod.api.entity.living.humanoid.npc.ai;

import net.canarymod.api.inventory.Item;

/**
 * AI Container for NPC item pickup
 *
 * @author Jason (darkdiplomat)
 */
public final class PickupItem extends NPCAI {
    private final Item pickedUp;

    /**
     * Constructs a new PickupItem AI Container
     *
     * @param pickedUp
     *         the {@link net.canarymod.api.inventory.Item} being picked up
     */
    public PickupItem(Item pickedUp) {
        this.pickedUp = pickedUp;
    }

    /**
     * Gets the {@link net.canarymod.api.inventory.Item} being picked up
     *
     * @return picked up item
     */
    public final Item getItemPickedUp() {
        return pickedUp;
    }

    /**
     * {@inheritDoc}
     */
    public final String toString() {
        return String.format("NPCAI.PickupItem[Item=%s]", pickedUp);
    }
}

package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.Hook;

/**
 * Called when a piece of Armor has been destroyed
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ArmorBrokenHook extends Hook {
    private final Player player;
    private final Item armor;

    public ArmorBrokenHook(Player player, Item armor) {
        this.player = player;
        this.armor = armor;
    }

    /**
     * Gets the {@link net.canarymod.api.entity.living.humanoid.Player} who's armor has broken
     *
     * @return player who's armor has broken
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the armor that was broken<p/>
     * NOTE: Amount will be 0; to restore the item set amount back to 1
     *
     * @return the tool that was broken
     */
    public Item getArmor() {
        return armor;
    }

    public String toString() {
        return String.format("%s[Player: %s, Armor: %s]", getHookName(), player, armor);
    }
}

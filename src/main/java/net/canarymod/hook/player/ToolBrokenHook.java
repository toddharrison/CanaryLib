package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.hook.Hook;

/**
 * Called when a tool/sword runs out of uses and is destroyed
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ToolBrokenHook extends Hook {
    private final Player player;
    private final Item tool;

    public ToolBrokenHook(Player player, Item tool) {
        this.player = player;
        this.tool = tool;
    }

    /**
     * Gets the {@link net.canarymod.api.entity.living.humanoid.Player} who's tool has broken
     *
     * @return player who's tool has broken
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the tool that was broken<p/>
     * NOTE: Amount will be 0; to restore the item set amount back to 1<br/>
     * Also note the damage value will be 0
     *
     * @return the tool that was broken
     */
    public Item getTool() {
        return tool;
    }

    public String toString() {
        return String.format("%s[Player: %s, Tool: %s]", getHookName(), player, tool);
    }
}

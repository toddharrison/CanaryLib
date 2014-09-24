package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

/**
 * Called when the Player is no longer idle
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ReturnFromIdleHook extends Hook {
    private final Player player;
    private final long idle;

    public ReturnFromIdleHook(Player player, long idle) {
        this.player = player;
        this.idle = idle;
    }

    public Player getPlayer() {
        return this.player;
    }

    public long getTimeIdle() {
        return idle;
    }

    public String toString() {
        return String.format("%s[Player: %s, Idle: %d]", getHookName(), player, idle);
    }
}

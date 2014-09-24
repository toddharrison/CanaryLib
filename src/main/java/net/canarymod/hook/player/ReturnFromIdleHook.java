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

    /**
     * Get the {@link net.canarymod.api.entity.living.humanoid.Player} that was Idle
     *
     * @return the idle player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the amount of time the {@link net.canarymod.api.entity.living.humanoid.Player} was idle
     *
     * @return time idle
     */
    public long getTimeIdle() {
        return idle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s[Player: %s, Idle: %d]", getHookName(), player, idle);
    }
}

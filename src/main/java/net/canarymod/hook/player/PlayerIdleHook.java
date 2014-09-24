package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Player Idle Hook<p/>
 * Called when a Player is idle for too long and just before them being kicked for idling.<br/>
 * Canceling this hook will stop the kicking process.<br/>
 * NOTE: This hook only fires if PlayerIdle time is set above 0 in the sever.cfg
 * and for {@link net.canarymod.api.entity.living.humanoid.Player}(s) who aren't set to ignoresRestrictions
 *
 * @author Jason Jones (darkdiplomat)
 */
public class PlayerIdleHook extends CancelableHook {
    private final Player player;
    private final long millisIdle;

    /**
     * Constructs a new PlayerIdleHook
     *
     * @param player
     *         the {@link net.canarymod.api.entity.living.humanoid.Player} who is idle
     * @param millisIdle
     *         the number of milliseconds the player has been idle
     */
    public PlayerIdleHook(Player player, long millisIdle) {
        this.player = player;
        this.millisIdle = millisIdle;
    }

    /**
     * Gets the idle {@link net.canarymod.api.entity.living.humanoid.Player}
     *
     * @return idle {@link net.canarymod.api.entity.living.humanoid.Player}
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the number of milliseconds the player has been idle
     *
     * @return idle time in milliseconds
     */
    public long getTimeIdle() {
        return millisIdle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s[Player: %s]", getHookName(), player);
    }
}

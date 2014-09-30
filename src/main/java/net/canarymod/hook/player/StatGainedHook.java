package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.statistics.Stat;
import net.canarymod.hook.CancelableHook;

/**
 * StatGainedHook<br>
 * Called when a {@link Player} gains a Stat
 *
 * @author Jason (darkdiplomat)
 */
public final class StatGainedHook extends CancelableHook {
    private final Player player;
    private final Stat stat;
    private int gain;

    /**
     * Constructs a new StatGainedHook
     *
     * @param player
     *         the {@link Player} gaining a stat
     * @param stat
     *         the {@link Stat} being gained
     */
    public StatGainedHook(Player player, Stat stat, int gain) {
        this.player = player;
        this.stat = stat;
    }

    /**
     * Gets the {@link Player} gaining a {@link Stat}
     *
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Stat} being gained
     *
     * @return the {@link Stat}
     */
    public Stat getStat() {
        return stat;
    }

    /**
     * Gets the amount of the stat gained
     *
     * @return gain amount
     */
    public int getGain() {
        return gain;
    }

    @Override
    public final String toString() {
        return String.format("%s[Player=%s, Stat=%s]", getHookName(), player, stat);
    }
}

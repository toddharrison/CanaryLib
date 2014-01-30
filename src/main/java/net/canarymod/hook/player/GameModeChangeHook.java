package net.canarymod.hook.player;

import net.canarymod.api.GameMode;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;

/**
 * Called when a {@link Player} changes GameModes
 *
 * @author Jason (darkdiplomat)
 */
public class GameModeChangeHook extends CancelableHook {
    private final GameMode newMode;
    private final Player player;

    public GameModeChangeHook(Player player, GameMode newMode) {
        this.player = player;
        this.newMode = newMode;
    }

    /**
     * Gets the {@link Player} changing modes
     *
     * @return the {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the old (current) {@link net.canarymod.api.GameMode}
     *
     * @return old {@link net.canarymod.api.GameMode}
     */
    public GameMode getOldMode() {
        return player.getMode();
    }

    /**
     * Gets the new (changing to) {@link net.canarymod.api.GameMode}
     *
     * @return new {@link net.canarymod.api.GameMode}
     */
    public GameMode getNewMode() {
        return newMode;
    }

    public final String toString() {
        return String.format("GameModeChangeHook[Player: %s, OldMode: %s, NewMode: %s]", player.getName(), getOldMode(), getNewMode());
    }
}

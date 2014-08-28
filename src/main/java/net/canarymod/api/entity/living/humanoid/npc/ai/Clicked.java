package net.canarymod.api.entity.living.humanoid.npc.ai;

import net.canarymod.api.entity.living.humanoid.Player;

/**
 * @author Jason (darkdiplomat)
 */
public final class Clicked extends NPCAI {
    private final Player clicker;

    public Clicked(Player clicker) {
        this.clicker = clicker;
    }

    public final Player getClicker() {
        return this.clicker;
    }

    public final String toString() {
        return String.format("NPCAI.Clicked[Clicker=%s]", clicker);
    }

}

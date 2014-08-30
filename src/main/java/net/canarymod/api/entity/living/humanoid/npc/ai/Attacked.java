package net.canarymod.api.entity.living.humanoid.npc.ai;

import net.canarymod.api.entity.Entity;

/**
 * AI Container for Attacked NPC
 *
 * @author Jason (darkdiplomat)
 */
public final class Attacked extends NPCAI {
    public final Entity attacker;

    /**
     * Constructs a new Attacked AI Container
     *
     * @param attacker
     *         the {@link net.canarymod.api.entity.Entity} attacking the NPC
     */
    public Attacked(Entity attacker) {
        this.attacker = attacker;
    }

    /**
     * Gets the {@link net.canarymod.api.entity.Entity} attacker
     *
     * @return attacker
     */
    public final Entity getAttacker() {
        return attacker;
    }

    /**
     * {@inheritDoc}
     */
    public final String toString() {
        return String.format("NPCAI.Attacked[Attacker=%s]", attacker);
    }
}

package net.canarymod.api.entity.living.humanoid.npc.ai;

import net.canarymod.api.entity.living.LivingBase;

/**
 * @author Jason (darkdiplomat)
 */
public final class Attacked extends NPCAI {
    public final LivingBase attacker;

    public Attacked(LivingBase attacker) {
        this.attacker = attacker;
    }

    public final LivingBase getAttacker() {
        return attacker;
    }

    public final String toString() {
        return String.format("NPCAI.Attacked[Attacker=%s]", attacker);
    }
}

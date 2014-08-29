package net.canarymod.api.entity.living.humanoid.npc.ai;

/**
 * @author Jason (darkdiplomat)
 */
public abstract class NPCAI {

    public final String getName() {
        return "NPCAI." + this.getClass().getSimpleName();
    }

}

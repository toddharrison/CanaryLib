package net.canarymod.api.entity.living.humanoid.npc.ai;

import net.canarymod.api.entity.living.humanoid.NonPlayableCharacter;
import net.canarymod.api.entity.living.humanoid.npc.NPCBehaviorRegistry;

/**
 * @author Jason (darkdiplomat)
 */
public abstract class NPCAI {

    public final String getName() {
        return "NPCAI." + this.getClass().getSimpleName();
    }

    public final void call(NonPlayableCharacter npc) {
        NPCBehaviorRegistry.execute(npc, this);
    }

}

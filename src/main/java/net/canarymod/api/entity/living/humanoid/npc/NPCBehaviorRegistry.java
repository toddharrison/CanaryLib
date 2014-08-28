package net.canarymod.api.entity.living.humanoid.npc;

import com.google.common.collect.ArrayListMultimap;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.NonPlayableCharacter;
import net.canarymod.api.entity.living.humanoid.npc.ai.NPCAI;

import java.lang.reflect.Method;
import java.util.Iterator;

import static net.canarymod.Canary.log;

/**
 * @author Jason (darkdiplomat)
 */
public final class NPCBehaviorRegistry {
    private final static ArrayListMultimap<Class<? extends NPCAI>, NPCBehaviorRegisteredListener> registered = ArrayListMultimap.create();

    /**
     * Registers a new {@link net.canarymod.motd.MessageOfTheDayListener} to be used with the MessageOfTheDay
     *
     * @param listner
     *         the {@link net.canarymod.motd.MessageOfTheDayListener} to be added
     * @param owner
     *         the {@link net.canarymod.motd.MOTDOwner} (either the server or a plugin)
     * @param force
     *         {@code true} to override existing keys if existant; {@code false} to error out on duplicate keys (recommended)
     */
    public static void registerNPCListener(final NPCBehaviorListener listener, final NonPlayableCharacter npc, final boolean force) {
        synchronized (registered) {
            Method[] methods = ToolBox.safeArrayMerge(listener.getClass().getMethods(), listener.getClass().getDeclaredMethods(), new Method[1]);
            for (final Method method : methods) {
                // Check if the method is a NPCBehavior handling method
                final NPCBehavior handler = method.getAnnotation(NPCBehavior.class);

                if (handler == null) {
                    continue; // Next, not one of our things
                }
                // Check the parameters for number and type and decide if it's one
                // that is really a handler method
                Class<?>[] parameters = method.getParameterTypes();

                if (parameters.length != 1) {
                    throw new NpcAiListenerMethodConsistancyException("Amount of parameters for " + method.getName() + " is invalid. Expected 1, was " + parameters.length);
                }
                Class<?> npcAICls = parameters[0];

                if (!NPCAI.class.isAssignableFrom(npcAICls)) {
                    throw new NpcAiListenerMethodConsistancyException("NPCAI is not assignable from " + npcAICls.getName());
                }

                // We checked the class above, ignore unchecked warnings.
                registered.put((Class<? extends NPCAI>) npcAICls, new NPCBehaviorRegisteredListener(listener, npc, method));
            }
        }
    }

    public static void unregister(NPCBehaviorListener listener) {
        synchronized (registered) {
            Iterator<NPCBehaviorRegisteredListener> itr = registered.values().iterator();
            while (itr.hasNext()) {
                NPCBehaviorRegisteredListener nbrl = itr.next();
                if (nbrl.equals(listener)) {
                    itr.remove();
                    return;
                }
            }
        }
    }

    public static void unregister(NonPlayableCharacter npc) {
        synchronized (registered) {
            Iterator<NPCBehaviorRegisteredListener> itr = registered.values().iterator();
            while (itr.hasNext()) {
                NPCBehaviorRegisteredListener nbrl = itr.next();
                if (nbrl.isFor(npc)) {
                    itr.remove();
                }
            }
        }
    }

    public static void execute(NonPlayableCharacter npc, NPCAI npcai) {
        synchronized (registered) {
            if (!registered.containsKey(npcai.getClass())) {
                return;
            }

            Iterator<NPCBehaviorRegisteredListener> iter = registered.get(npcai.getClass()).iterator();
            while (iter.hasNext()) {
                NPCBehaviorRegisteredListener listener = iter.next();
                if (listener.isFor(npc)) {
                    try {
                        listener.execute(npcai);
                    }
                    catch (NPCAIExcutionException npcaieex) {
                        log.error(npcaieex.getCause());
                    }
                }
            }
        }
    }
}

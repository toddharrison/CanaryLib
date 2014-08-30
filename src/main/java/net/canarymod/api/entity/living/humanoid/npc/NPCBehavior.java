package net.canarymod.api.entity.living.humanoid.npc;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * NPC Behavior method annotation for the NPCBehavior Listener
 *
 * @author Jason (darkdiplomat)
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface NPCBehavior {
}

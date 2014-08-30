package net.canarymod.api.entity.living.humanoid.npc;

/**
 * NPC AI Excution Exception
 * Thrown if an exception occurs while executing a NPC AI Event
 *
 * @author Jason (darkdiplomat)
 */
public class NPCAIExcutionException extends RuntimeException {

    private static final long serialVersionUID = 201408281814L;

    /**
     * {@inheritDoc}
     */
    public NPCAIExcutionException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NPCAIExcutionException(String msg, Throwable t) {
        super(msg, t);
    }
}

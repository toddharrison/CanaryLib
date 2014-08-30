package net.canarymod.api.entity.living.humanoid.npc;

/**
 * @author Jason (darkdiplomat)
 */
public final class NpcAiListenerMethodConsistancyException extends RuntimeException {

    private static final long serialVersionUID = 201408281759L;

    /**
     * {@inheritDoc}
     */
    public NpcAiListenerMethodConsistancyException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NpcAiListenerMethodConsistancyException(String msg, Throwable t) {
        super(msg, t);
    }
}

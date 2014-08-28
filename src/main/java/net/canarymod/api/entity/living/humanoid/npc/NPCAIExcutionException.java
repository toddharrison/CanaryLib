package net.canarymod.api.entity.living.humanoid.npc;

/**
 * @author Jason (darkdiplomat)
 */
public class NPCAIExcutionException extends RuntimeException {

    private static final long serialVersionUID = 201408281814L;

    public NPCAIExcutionException(String message) {
        super(message);
    }

    public NPCAIExcutionException(String msg, Throwable t) {
        super(msg, t);
    }
}

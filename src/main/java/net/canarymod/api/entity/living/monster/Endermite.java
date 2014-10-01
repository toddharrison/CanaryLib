package net.canarymod.api.entity.living.monster;

/**
 * Endermite wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface Endermite extends EntityMob {

    /**
     * Gets whether a {@link net.canarymod.api.entity.living.humanoid.Player} has spawned the {@link Endermite}
     *
     * @return {@code true} if player spawned; {@code false} if not
     */
    boolean isPlayerSpanwed();
}

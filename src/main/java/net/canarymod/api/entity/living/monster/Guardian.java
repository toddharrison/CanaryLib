package net.canarymod.api.entity.living.monster;

/**
 * Guardian wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface Guardian extends EntityMob {

    /**
     * Gets whether this {@code Guardian} is an elder
     *
     * @return {@code true} if elder; {@code false} if not
     */
    boolean isElder();
}

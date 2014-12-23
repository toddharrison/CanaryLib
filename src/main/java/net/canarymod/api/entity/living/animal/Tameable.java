package net.canarymod.api.entity.living.animal;

import net.canarymod.api.entity.living.LivingBase;

/**
 * Tameable wrapper
 *
 * @author Jason (darkdiplomat)
 */
public interface Tameable extends EntityAnimal {

    /**
     * If the entity is tamed, this returns its owner
     *
     * @return owner
     */
    public LivingBase getOwner();

    /**
     * Gets the name of the owner <br/>
     * NOTE: As of Minecraft 1.8, this returns UUID string.
     *
     * @return the owner's name
     *
     * @deprecated use {@link net.canarymod.api.entity.living.animal.Tameable#getOwnerID()} instead
     */
    @Deprecated
    public String getOwnerName();

    /**
     * Return the identity of this Tameabe's Owner
     *
     * @return owner id
     */
    public String getOwnerID();

    /**
     * Set the owner of this entity
     *
     * @param entity
     *         the {@link LivingBase} to set as Owner
     */
    public void setOwner(LivingBase entity);

    /**
     * Sets the owner's name
     *
     * @param name
     *         the name of the owner
     */
    public void setOwner(String name);

    /**
     * Check if that animal is tamed
     *
     * @return {@code true} if tame; {@code false} otherwise
     */
    public boolean isTamed();

    /**
     * Set this entity tamed or not
     *
     * @param tamed
     *         {@code true} for tame; {@code false} for not tame
     */
    public void setTamed(boolean tamed);

    /**
     * Check if this animal is currently sitting
     *
     * @return {@code true} if sitting; {@code false} otherwise
     */
    public boolean isSitting();

    /**
     * Set this entity sitting or not
     *
     * @param sitting
     *         {@code true} for sitting; {@code false} for not sitting
     */
    public void setSitting(boolean sitting);
}

package net.canarymod.api.factory;

import com.google.common.base.Predicate;
import net.canarymod.api.ai.*;
import net.canarymod.api.entity.*;
import net.canarymod.api.entity.living.*;
import net.canarymod.api.entity.living.animal.*;
import net.canarymod.api.entity.living.humanoid.Villager;
import net.canarymod.api.entity.living.monster.*;

/**
 *
 * @author Aaron
 */
public interface AIFactory {

    /**
     * Returns a new {@link AIArrowAttack} Instance
     * @param mob mob being assigned to this ai
     * @param moveSpeed move speed towards the target
     * @param attackTimeModifier higher numbers means longer attack times
     * @param maxRangedAttackTime longest wait between attacks
     * @param maxAttackDistance farthest distance the mob will attack from
     * @return a new {@link AIArrowAttack} Instance
     */
    public AIArrowAttack newAIArrowAttack(RangedAttackMob mob, double moveSpeed, int attackTimeModifier, int maxRangedAttackTime, int maxAttackDistance);

    /**
     * Returns a new {@link AIAttackOnCollide} Instance
     * @param creature creature this AI belongs to.
     * @param targetClass The class type of the target of this AI.
     * @param moveSpeed speed the entity will move at when engaged in this activity.
     * @param persistant Whether or not the mob will attempt to find the target again after
     *                   it is out of sight or unable to reach the target because it cannot 
     *                   find any path to take.
     * @return A new {@link AIAttackOnCollide} Instance
     */
    public AIAttackOnCollide newAIAttackOnCollide(EntityMob creature, Class<? extends LivingBase> targetClass, double moveSpeed, boolean persistant);

    /**
     * Returns A new {@link AIAvoidEntity} Instance
     * @param mob The mob this ai belongs to
     * @param predicate A very specific {@link Predicate} implementation. <br><br>
     * The Predicate implementation of {@link Predicate#apply(java.lang.Object)} 
     * will be passed type of {@link net.canarymod.api.entity.Entity}.  Your method 
     * will need to assert true if the AI should avoid the given entity type. Or 
     * false if it does not need to avoid the entityType.
     * @param radius Radius of area to check around mob
     * @param farSpeed movement speed when far from avoided entity.
     * @param nearSpeed movement speed when near avoided entity
     * @return A new {@link AIAvoidEntity} Instance
     */
    public AIAvoidEntity newAIAvoidEntity(EntityMob mob, Predicate predicate, float radius, double farSpeed, double nearSpeed);

    /**
     * Returns A new {@link AIBeg} Instance
     * @param wolf Wolf that owns this AI.
     * @param minBegDistance distance threshold for a wolf to start begging. 
     *                          If a player is farther away, it won't beg.
     * @return A new {@link AIBeg} Instance
     */
    public AIBeg newAIBeg(Wolf wolf, float minBegDistance);

    /**
     * Returns A new {@link AIBreakDoor} Instance.
     * This AI class will allow the owner to break down doors in its way.
     * @param entity The entity that owns this AI
     * @return A new {@link AIBreakDoor} Instance
     */
    public AIBreakDoor newAIBreakDoor(EntityLiving entity);

    /**
     * Returns A new {@link AIControlledByPlayer} Instance.
     * @param entity The entity that owns this AI.
     * @param speed The speed at which the entity will move.
     * @return A new {@link AIControlledByPlayer} Instance.
     */
    public AIControlledByPlayer newAIControlledByPlayer(EntityLiving entity, float speed);

    /**
     * Returns A new {@link AICreeperSwell} Instance.
     * @param creeper The {@link Creeper} that owns this AI.
     * @return A new {@link AICreeperSwell} Instance.
     */
    public AICreeperSwell newAICreeperSwell(Creeper creeper);

    /**
     * Returns A new {@link AIDefendVillage} Instance.
     * @param ironGolem The {@link IronGolem} this AI belongs to.
     * @return A new {@link AIDefendVillage} Instance.
     */
    public AIDefendVillage newAIDefendVillage(IronGolem ironGolem);

    /**
     * Returns A new {@link AIEatGrass} Instance.
     * @param entity The entity that owns this ai.
     * @return A new {@link AIEatGrass} Instance.
     */
    public AIEatGrass newAIEatGrass(EntityLiving entity);

    /**
     * Returns A new {@link AIEntityNearest} Instance.
     * @param entityLiving THe Entity that owns this ai.
     * @param entityClass The class type this entity should target.
     * @return A new {@link AIEntityNearest} Instance.
     */
    public AIFindEntityNearest newAIFindEntityNearest(EntityLiving entityLiving, Class<? extends Entity> entityClass);

    /**
     * Returns A new {@link AIEntityNearestPlayer} Instance.
     * @param entityLiving THe Entity that owns this ai.
     * @return A new {@link AIEntityNearestPlayer} Instance.
     */
    public AIFindEntityNearestPlayer newAIFindEntityNearestPlayer(EntityLiving entityLiving);

    /**
     * Returns A new {@link AIFleeSun} Instance.
     * @param mob the entity that owns this AI.
     * @param speed the speed at which the mob will move.
     * @return A new {@link AIFleeSun} Instance.
     */
    public AIFleeSun newAIFleeSun(EntityMob mob, double speed);

    /**
     * Returns A new {@link AIFollowGolem} Instance.
     * @param villager The {@link Villager} that owns this AI.
     * @return A new {@link AIFollowGolem} Instance.
     */
    public AIFollowGolem newAIFollowGolem(Villager villager);

    /**
     * Returns A new {@link AIFollowOwner} Instance.
     * @param entity the {@link Tameable} entity this AI belongs to.
     * @param speed Speed at which the entity will move
     * @param minDistance how close the entity will try to get to its owner.
     * @param maxDistance distance from owner the entity will try to stay in.
     * @return A new {@link AIFollowOwner} Instance.
     */
    public AIFollowOwner newAIFollowOwner(Tameable entity, double speed, float minDistance, float maxDistance);

    /**
     * Returns A new {@link AIFollowParent} Instance.
     * @param animal the {@link EntityAnimal} this AI belongs to.
     * @param speed the speed the animal will move at.
     * @return A new {@link AIFollowParent} Instance.
     */
    public AIFollowParent newAIFollowParent(EntityAnimal animal, double speed);

    /**
     * Returns A new {@link AIHarvestFarmland} Instance.
     * @param villager The {@link VIllager} this AI belongs to
     * @param speed speed the villager will walk at.
     * @return A new {@link AIHarvestFarmland} Instance.
     */
    public AIHarvestFarmland newAIHarvestFarmland(Villager villager, double speed);

    /**
     * Returns A new {@link AIHurtByTarget} Instance.
     * @param entity The {@link EntityMob} this AI belongs to.
     * @param callForHelp whether or not this mob should rally allies.
     * @param targets entity classy types to target wiht this logic.
     * @return A new {@link AIHurtByTarget} Instance.
     */
    public AIHurtByTarget newAIHurtByTarget(EntityMob entity, boolean callForHelp, Class<? extends Entity>... targets);

    /**
     * Returns A new {@link AILeapAtTarget} Instance.
     * @param entity The entity this AI belongs to.
     * @param leapMotionY Y motion the leap will be.
     * @return A new {@link AILeapAtTarget} Instance.
     */
    public AILeapAtTarget newAILeapAtTarget(EntityLiving entity, float leapMotionY);

    /**
     * Returns A new {@link AILookAtTradePlayer} Instance.
     * @param villager The {@link Villager} This AI belongs to.
     * @return A new {@link AILookAtTradePlayer} Instance.
     */
    public AILookAtTradePlayer newAILookAtTradePlayer(Villager villager);

    /**
     * Returns A new {@link AILookAtVillager} Instance.
     * @param golem the {@link IronGolem} that owns this AI.
     * @return A new {@link AILookAtVillager} Instance.
     */
    public AILookAtVillager newAILookAtVillager(IronGolem golem);

    /**
     * Returns A new {@link AILookIdle} Instance.
     * @param entity The entity this AI belongs to.
     * @return A new {@link AILookIdle} Instance.
     */
    public AILookIdle newAILookIdle(EntityLiving entity);

    /**
     * Returns A new {@link AIMate} Instance.
     * @param animal the {@link EntityAnimal} this AI belongs to.
     * @param speed the speed the animal will move at.
     * @return A new {@link AIMate} Instance.
     */
    public AIMate newAIMate(EntityAnimal animal, double speed);

    /**
     * Returns A new {@link AIMoveIndoors} Instance.
     * @param entity The {@link EntityMob} that owns this AI.
     * @return A new {@link AIMoveIndoors} Instance.
     */
    public AIMoveIndoors newAIMoveIndoors(EntityMob entity);

    /**
     * Returns A new {@link AIMoveThroughVillage} Instance.
     * @param entity The {@link EntityMob} that owns this AI.
     * @param speed the speed the animal will move at.
     * @param isNoctournal whether or not this creature is active at night time.
     * @return A new {@link AIMoveThroughVillage} Instance.
     */
    public AIMoveThroughVillage newAIMoveThroughVillage(EntityMob entity, double speed, boolean isNoctournal);

    /**
     * Returns A new {@link AIMoveTowardsRestriction} Instance.
     * @param entity The {@link EntityMob} that owns this AI.
     * @param speed the speed the animal will move at.
     * @return A new {@link AIMoveTowardsRestriction} Instance.
     */
    public AIMoveTowardsRestriction newAIMoveTowardsRestriction(EntityMob entity, double speed);

    /**
     * Returns A new {@link AIMoveTowardsRestriction} Instance.
     * @param entity The {@link EntityMob} that owns this AI.
     * @param speed the speed the animal will move at.
     * @param maxDistance the max distance away targets can be
     * @return A new {@link AIMoveTowardsRestriction} Instance.
     */
    public AIMoveTowardsTarget newAIMoveTowardsTarget(EntityMob entity, double speed, float maxDistance);

    /*public AINearestAttackableTarget newAINearestAttackableTarget();

    public AIOcelotAttack newAIOcelotAttack();

    public AIOcelotSit newAIOcelotSit();

    public AIOpenDoor newAIOpenDoor();

    public AIOwnerHurtByTarget newAIOwnerHurtByTarget();

    public AIOwnerHurtTarget newAIOwnerHurtTarget();

    public AIPanic newAIPanic();

    public AIPlay newAIPlay();

    public AIRestrictOpenDoor newAIRestrictOpenDoor();

    public AIRestrictSun newAIRestrictSun();

    public AIRunAroundLikeCrazy newAIRunAroundLikeCrazy();

    public AISit newAISit();

    public AISwimming newAISwimming();

    public AITarget newAITarget();

    public AITargetNonTamed newAITargetNonTamed();

    public AITempt newAITempt();

    public AITradePlayer newAITradePlayer();

    public AIVillagerInteract newAIVillagerInteract();

    public AIVillagerMate newAIVillagerMate();

    public AIWander newAIWander();

    public AIWatchClosest newAIWatchClosest();

    public AIWatchClosest2 newAIWatchClosest2();
    /**/
}
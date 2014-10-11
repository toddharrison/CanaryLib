package net.canarymod.api.factory;

import com.google.common.base.Predicate;
import net.canarymod.api.ai.*;
import net.canarymod.api.entity.living.*;
import net.canarymod.api.entity.living.animal.*;
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

    /*public AICreeperSwell newAICreeperSwell();

    public AIDefendVillage newAIDefendVillage();

    public AIDoorInteract newAIDoorInteract();

    public AIEatGrass newAIEatGrass();

    public AIFindEntityNearest newAIFindEntityNearest();

    public AIFindEntityNearestPlayer newAIFindEntityNearestPlayer();

    public AIFleeSun newAIFleeSun();

    public AIFollowGolem newAIFollowGolem();

    public AIFollowOwner newAIFollowOwner();

    public AIFollowParent newAIFollowParent();

    public AIHarvestFarmland newAIHarvestFarmland();

    public AIHurtByTarget newAIHurtByTarget();

    public AILeapAtTarget newAILeapAtTarget();

    public AILookAtTradePlayer newAILookAtTradePlayer();

    public AILookAtVillager newAILookAtVillager();

    public AILookIdle newAILookIdle();

    public AIManager newAIManager();

    public AIMate newAIMate();

    public AIMoveIndoors newAIMoveIndoors();

    public AIMoveThroughVillage newAIMoveThroughVillage();

    public AIMoveToBlock newAIMoveToBlock();

    public AIMoveTowardsRestriction newAIMoveTowardsRestriction();

    public AIMoveTowardsTarget newAIMoveTowardsTarget();

    public AINearestAttackableTarget newAINearestAttackableTarget();

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
    */
}
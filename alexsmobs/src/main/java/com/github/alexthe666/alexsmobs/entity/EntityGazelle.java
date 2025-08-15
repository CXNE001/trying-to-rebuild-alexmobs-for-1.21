/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.IHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class EntityGazelle
extends Animal
implements IAnimatedEntity,
IHerdPanic {
    private int animationTick;
    private Animation currentAnimation;
    public static final Animation ANIMATION_FLICK_EARS = Animation.create((int)20);
    public static final Animation ANIMATION_FLICK_TAIL = Animation.create((int)14);
    public static final Animation ANIMATION_EAT_GRASS = Animation.create((int)30);
    private boolean hasSpedUp = false;
    private int revengeCooldown = 0;
    private static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.m_135353_(EntityGazelle.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);

    protected EntityGazelle(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new AnimalAIHerdPanic((PathfinderMob)this, 1.1));
        this.f_21345_.m_25352_(2, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(4, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_204132_(AMTagRegistry.GAZELLE_BREEDABLES), false));
        this.f_21345_.m_25352_(5, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 100, 1.0, 25, 7));
        this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 15.0f));
        this.f_21345_.m_25352_(7, (Goal)new RandomLookAroundGoal((Mob)this));
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.GAZELLE_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.GAZELLE_HURT.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.gazelleSpawnRolls, this.m_217043_(), spawnReasonIn) && super.m_5545_(worldIn, spawnReasonIn);
    }

    public int m_5792_() {
        return 8;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev) {
            int fleeTime;
            double range = 15.0;
            this.revengeCooldown = fleeTime = 100 + this.m_217043_().m_188503_(150);
            List list = this.m_9236_().m_45976_(this.getClass(), this.m_20191_().m_82377_(range, range / 2.0, range));
            for (EntityGazelle gaz : list) {
                gaz.revengeCooldown = fleeTime;
            }
        }
        return prev;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(RUNNING, (Object)false);
    }

    public boolean isRunning() {
        return (Boolean)this.f_19804_.m_135370_(RUNNING);
    }

    public void setRunning(boolean running) {
        this.f_19804_.m_135381_(RUNNING, (Object)running);
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.GAZELLE_BREEDABLES);
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    public void m_8119_() {
        super.m_8119_();
        if (!this.m_9236_().f_46443_) {
            if (this.getAnimation() == NO_ANIMATION && this.m_217043_().m_188503_(70) == 0 && (this.m_21188_() == null || this.m_20270_((Entity)this.m_21188_()) > 30.0f)) {
                if (this.m_9236_().m_8055_(this.m_20183_().m_7495_()).m_60713_(Blocks.f_50440_) && this.m_217043_().m_188503_(3) == 0) {
                    this.setAnimation(ANIMATION_EAT_GRASS);
                } else {
                    this.setAnimation(this.m_217043_().m_188499_() ? ANIMATION_FLICK_EARS : ANIMATION_FLICK_TAIL);
                }
            }
            if (this.revengeCooldown >= 0) {
                --this.revengeCooldown;
            }
            if (this.revengeCooldown == 0 && this.m_21188_() != null) {
                this.m_6703_(null);
            }
            this.setRunning(this.revengeCooldown > 0);
            if (this.isRunning() && !this.hasSpedUp) {
                this.hasSpedUp = true;
                this.m_6858_(true);
                this.m_21051_(Attributes.f_22279_).m_22100_((double)0.475f);
            }
            if (!this.isRunning() && this.hasSpedUp) {
                this.hasSpedUp = false;
                this.m_6858_(false);
                this.m_21051_(Attributes.f_22279_).m_22100_(0.25);
            }
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("GazelleRunning", this.isRunning());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setRunning(compound.m_128471_("GazelleRunning"));
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_FLICK_EARS, ANIMATION_FLICK_TAIL, ANIMATION_EAT_GRASS};
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 8.0).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22279_, 0.25);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return (AgeableMob)((EntityType)AMEntityRegistry.GAZELLE.get()).m_20615_((Level)p_241840_1_);
    }

    @Override
    public void onPanic() {
    }

    @Override
    public boolean canPanic() {
        return true;
    }
}

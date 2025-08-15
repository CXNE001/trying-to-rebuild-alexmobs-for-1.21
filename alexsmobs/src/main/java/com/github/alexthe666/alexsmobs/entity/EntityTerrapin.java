/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.BreathAirGoal
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MoveToBlockGoal
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.ProjectileUtil
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.block.BlockTerrapinEgg;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeaveWater;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalSwimMoveControllerSink;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticAIRandomSwimming;
import com.github.alexthe666.alexsmobs.entity.ai.SemiAquaticPathNavigator;
import com.github.alexthe666.alexsmobs.entity.util.TerrapinTypes;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityTerrapinEgg;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityTerrapin
extends Animal
implements ISemiAquatic,
Bucketable {
    private static final EntityDataAccessor<Integer> TURTLE_TYPE = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> SHELL_TYPE = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> TURTLE_COLOR = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> SHELL_COLOR = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> SKIN_COLOR = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> RETREATED = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SPINNING = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.m_135353_(EntityTerrapin.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float clientSpin = 0.0f;
    public int spinCounter = 0;
    public float prevSwimProgress;
    public float swimProgress;
    public float prevRetreatProgress;
    public float retreatProgress;
    public float prevSpinProgress;
    public float spinProgress;
    private int maxRollTime = 50;
    private boolean isLandNavigator;
    private int swimTimer = -1000;
    private int hideInShellTimer = 0;
    private Vec3 spinDelta;
    private float spinYRot;
    private int changeSpinAngleCooldown = 0;
    private LivingEntity lastLauncher = null;
    private TileEntityTerrapinEgg.ParentData partnerData;

    protected EntityTerrapin(EntityType animal, Level level) {
        super(animal, level);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.switchNavigator(true);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 10.0).m_22268_(Attributes.f_22284_, 10.0).m_22268_(Attributes.f_22279_, (double)0.1f);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.TERRAPIN_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.TERRAPIN_HURT.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.terrapinSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canTerrapinSpawn(EntityType<EntityTerrapin> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return reason == MobSpawnType.SPAWNER || iServerWorld.m_8055_(pos).m_60819_().m_192917_((Fluid)Fluids.f_76193_);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new BreathAirGoal((PathfinderMob)this));
        this.f_21345_.m_25352_(1, (Goal)new MateGoal(this, 1.0));
        this.f_21345_.m_25352_(1, (Goal)new LayEggGoal(this, 1.0));
        this.f_21345_.m_25352_(2, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_204132_(AMTagRegistry.TERRAPIN_BREEDABLES), false));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAIFindWater((PathfinderMob)this));
        this.f_21345_.m_25352_(3, (Goal)new AnimalAILeaveWater((PathfinderMob)this));
        this.f_21345_.m_25352_(4, (Goal)new SemiAquaticAIRandomSwimming(this, 1.0, 30));
        this.f_21345_.m_25352_(6, (Goal)new PanicGoal((PathfinderMob)this, 1.1));
        this.f_21345_.m_25352_(7, (Goal)new RandomStrollGoal((PathfinderMob)this, 1.0, 60));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
    }

    public void m_8119_() {
        super.m_8119_();
        this.prevSwimProgress = this.swimProgress;
        this.prevRetreatProgress = this.retreatProgress;
        this.prevSpinProgress = this.spinProgress;
        boolean inWaterOrBubble = this.m_20072_();
        boolean spinning = this.isSpinning();
        boolean retreated = this.hasRetreated();
        if (inWaterOrBubble) {
            if (this.swimProgress < 5.0f) {
                this.swimProgress += 1.0f;
            }
        } else if (this.swimProgress > 0.0f) {
            this.swimProgress -= 1.0f;
        }
        if (spinning) {
            if (this.spinProgress < 5.0f) {
                this.spinProgress += 1.0f;
            }
        } else if (this.spinProgress > 0.0f) {
            this.spinProgress -= 1.0f;
        }
        if (retreated) {
            if (this.retreatProgress < 5.0f) {
                this.retreatProgress += 1.0f;
            }
        } else if (this.retreatProgress > 0.0f) {
            this.retreatProgress -= 1.0f;
        }
        if (spinning) {
            this.handleSpin();
            if (this.m_6084_() && this.spinCounter > 5 && !this.m_6162_()) {
                for (Entity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82400_((double)0.3f))) {
                    if (this.m_7307_(entity) || entity instanceof EntityTerrapin) continue;
                    entity.m_6469_(this.m_269291_().m_269333_((LivingEntity)(this.lastLauncher == null ? this : this.lastLauncher)), 4.0f + this.f_19796_.m_188501_() * 4.0f);
                }
            }
            if (!this.m_6084_()) {
                this.setSpinning(false);
            }
            if (this.f_19862_) {
                if (this.changeSpinAngleCooldown == 0) {
                    this.changeSpinAngleCooldown = 10;
                    float f = this.collideDirectionAndSound().m_122434_() == Direction.Axis.X ? this.spinYRot - 180.0f : 180.0f - this.spinYRot;
                    this.m_146922_(f += (float)(this.f_19796_.m_188503_(40) - 20));
                    this.copySpinDelta(f, Vec3.f_82478_);
                } else {
                    this.maxRollTime -= 30;
                }
            }
            if (this.changeSpinAngleCooldown > 0) {
                --this.changeSpinAngleCooldown;
            }
        }
        if (!this.m_9236_().f_46443_) {
            if (this.m_20072_() && this.isLandNavigator) {
                this.switchNavigator(false);
            }
            if (!this.m_20072_() && !this.isLandNavigator) {
                this.switchNavigator(true);
            }
            if (this.m_20069_()) {
                this.swimTimer = Math.max(0, this.swimTimer + 1);
            } else {
                this.swimTimer = Math.min(0, this.swimTimer - 1);
                List list = this.m_9236_().m_45976_(Player.class, this.m_20191_().m_82377_(0.0, (double)0.15f, 0.0));
                for (Player player : list) {
                    int spin;
                    if (!player.f_20899_ && player.m_20096_() || !(player.m_20186_() > this.m_20188_())) continue;
                    if (!this.hasRetreated()) {
                        this.hideInShellTimer += 40 + this.f_19796_.m_188503_(40);
                        continue;
                    }
                    if (this.isSpinning()) continue;
                    this.lastLauncher = player;
                    this.hideInShellTimer = spin = 100 + this.f_19796_.m_188503_(100);
                    this.m_146922_(player.m_6080_());
                    this.spinFor(spin);
                }
            }
            if (this.swimProgress > 0.0f) {
                this.m_274367_(1.0f);
            } else {
                this.m_274367_(0.6f);
            }
            if (this.hideInShellTimer > 0) {
                --this.hideInShellTimer;
            }
            this.setRetreated(this.hideInShellTimer > 0 && !this.isSpinning());
        }
    }

    private Direction collideDirectionAndSound() {
        HitResult raytraceresult = ProjectileUtil.m_278158_((Entity)this, entity -> false);
        if (raytraceresult instanceof BlockHitResult) {
            BlockState state = this.m_9236_().m_8055_(((BlockHitResult)raytraceresult).m_82425_());
            if (state == null || !this.m_20067_()) {
                // empty if block
            }
            return ((BlockHitResult)raytraceresult).m_82434_();
        }
        return Direction.DOWN;
    }

    private boolean isMoving() {
        return this.m_20184_().m_82556_() > 0.02;
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = new GroundPathNavigation((Mob)this, this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new AnimalSwimMoveControllerSink((PathfinderMob)this, 2.5f, 1.15f);
            this.f_21344_ = new SemiAquaticPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(TURTLE_TYPE, (Object)0);
        this.f_19804_.m_135372_(SHELL_TYPE, (Object)0);
        this.f_19804_.m_135372_(SKIN_TYPE, (Object)0);
        this.f_19804_.m_135372_(SHELL_COLOR, (Object)0);
        this.f_19804_.m_135372_(SKIN_COLOR, (Object)0);
        this.f_19804_.m_135372_(TURTLE_COLOR, (Object)0);
        this.f_19804_.m_135372_(RETREATED, (Object)false);
        this.f_19804_.m_135372_(SPINNING, (Object)false);
        this.f_19804_.m_135372_(HAS_EGG, (Object)false);
        this.f_19804_.m_135372_(FROM_BUCKET, (Object)false);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("TurtleType", this.getTurtleTypeOrdinal());
        compound.m_128405_("ShellType", this.getShellType());
        compound.m_128405_("SkinType", this.getSkinType());
        compound.m_128405_("TurtleColor", this.getTurtleColor());
        compound.m_128405_("ShellColor", this.getShellColor());
        compound.m_128405_("SkinColor", this.getSkinColor());
        compound.m_128379_("HasEgg", this.hasEgg());
        compound.m_128379_("Bucketed", this.m_27487_());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setTurtleTypeOrdinal(compound.m_128451_("TurtleType"));
        this.setShellType(compound.m_128451_("ShellType"));
        this.setSkinType(compound.m_128451_("SkinType"));
        this.setTurtleColor(compound.m_128451_("TurtleColor"));
        this.setShellColor(compound.m_128451_("ShellColor"));
        this.setSkinColor(compound.m_128451_("SkinColor"));
        this.setHasEgg(compound.m_128471_("HasEgg"));
        this.m_27497_(compound.m_128471_("Bucketed"));
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
        if (!this.isSpinning()) {
            super.m_7355_(pos, state);
        }
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.TERRAPIN_BREEDABLES);
    }

    public boolean m_27487_() {
        return (Boolean)this.f_19804_.m_135370_(FROM_BUCKET);
    }

    public void m_27497_(boolean p_203706_1_) {
        this.f_19804_.m_135381_(FROM_BUCKET, (Object)p_203706_1_);
    }

    @Nonnull
    public SoundEvent m_142623_() {
        return SoundEvents.f_11782_;
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_27487_() || this.m_8077_();
    }

    public boolean m_6785_(double d) {
        return !this.m_27487_() && !this.m_8077_();
    }

    private int getTurtleTypeOrdinal() {
        return Mth.m_14045_((int)((Integer)this.f_19804_.m_135370_(TURTLE_TYPE)), (int)0, (int)(TerrapinTypes.values().length - 1));
    }

    private void setTurtleTypeOrdinal(int i) {
        this.f_19804_.m_135381_(TURTLE_TYPE, (Object)i);
    }

    public int getShellType() {
        return (Integer)this.f_19804_.m_135370_(SHELL_TYPE);
    }

    public void setShellType(int i) {
        this.f_19804_.m_135381_(SHELL_TYPE, (Object)i);
    }

    public int getSkinType() {
        return (Integer)this.f_19804_.m_135370_(SKIN_TYPE);
    }

    public void setSkinType(int i) {
        this.f_19804_.m_135381_(SKIN_TYPE, (Object)i);
    }

    public int getShellColor() {
        return (Integer)this.f_19804_.m_135370_(SHELL_COLOR);
    }

    public void setShellColor(int i) {
        this.f_19804_.m_135381_(SHELL_COLOR, (Object)i);
    }

    public int getSkinColor() {
        return (Integer)this.f_19804_.m_135370_(SKIN_COLOR);
    }

    public void setSkinColor(int i) {
        this.f_19804_.m_135381_(SKIN_COLOR, (Object)i);
    }

    public int getTurtleColor() {
        return (Integer)this.f_19804_.m_135370_(TURTLE_COLOR);
    }

    public void setTurtleColor(int i) {
        this.f_19804_.m_135381_(TURTLE_COLOR, (Object)i);
    }

    public TerrapinTypes getTurtleType() {
        return TerrapinTypes.values()[this.getTurtleTypeOrdinal()];
    }

    public void setTurtleType(TerrapinTypes type) {
        this.setTurtleTypeOrdinal(type.ordinal());
    }

    public boolean isSpinning() {
        return (Boolean)this.f_19804_.m_135370_(SPINNING);
    }

    public void setSpinning(boolean b) {
        this.f_19804_.m_135381_(SPINNING, (Object)b);
    }

    public boolean hasRetreated() {
        return (Boolean)this.f_19804_.m_135370_(RETREATED);
    }

    public void setRetreated(boolean b) {
        this.f_19804_.m_135381_(RETREATED, (Object)b);
    }

    public boolean hasEgg() {
        return (Boolean)this.f_19804_.m_135370_(HAS_EGG);
    }

    private void setHasEgg(boolean hasEgg) {
        this.f_19804_.m_135381_(HAS_EGG, (Object)hasEgg);
    }

    public int m_6062_() {
        return 4800;
    }

    protected int m_7305_(int currentAir) {
        return this.m_6062_();
    }

    public void m_7334_(Entity entity) {
        if (this.m_20072_() || entity instanceof EntityTerrapin) {
            super.m_7334_(entity);
        } else {
            entity.m_20256_(entity.m_20184_().m_82549_(this.m_20184_()));
        }
    }

    public boolean m_5829_() {
        return this.m_20072_() ? super.m_5829_() : this.m_6084_();
    }

    private void spinFor(int time) {
        this.maxRollTime = time;
        this.setSpinning(true);
    }

    private void copySpinDelta(float spinRot, Vec3 motionIn) {
        float f = spinRot * ((float)Math.PI / 180);
        float f1 = this.m_6162_() ? 0.3f : 0.5f;
        this.spinYRot = spinRot;
        this.spinDelta = new Vec3(motionIn.f_82479_ + (double)(-Mth.m_14031_((float)f) * f1), 0.0, motionIn.f_82481_ + (double)(Mth.m_14089_((float)f) * f1));
        this.m_20256_(this.spinDelta.m_82520_(0.0, 0.0, 0.0));
    }

    private void handleSpin() {
        this.setRetreated(true);
        ++this.spinCounter;
        if (!this.m_9236_().f_46443_) {
            if (this.spinCounter > this.maxRollTime) {
                this.setSpinning(false);
                this.hideInShellTimer = 10 + this.f_19796_.m_188503_(30);
                this.spinCounter = 0;
            } else {
                Vec3 vec3 = this.m_20184_();
                if (this.spinCounter == 1) {
                    this.copySpinDelta(this.m_146908_(), vec3);
                } else {
                    this.m_146922_(this.spinYRot);
                    this.m_5616_(this.spinYRot);
                    this.m_5618_(this.spinYRot);
                    this.m_20334_(this.spinDelta.f_82479_, vec3.f_82480_, this.spinDelta.f_82481_);
                }
            }
        }
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.m_20301_(this.m_6062_());
        this.setTurtleType(TerrapinTypes.getRandomType(this.f_19796_));
        this.setShellType(this.f_19796_.m_188503_(7));
        this.setSkinType(this.f_19796_.m_188503_(4));
        this.setTurtleColor(TerrapinTypes.generateRandomColor(this.f_19796_));
        this.setShellColor(TerrapinTypes.generateRandomColor(this.f_19796_));
        this.setSkinColor(TerrapinTypes.generateRandomColor(this.f_19796_));
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public AgeableMob m_142606_(ServerLevel p_146743_, AgeableMob p_146744_) {
        return (AgeableMob)((EntityType)AMEntityRegistry.TERRAPIN.get()).m_20615_((Level)p_146743_);
    }

    @Override
    public boolean shouldStopMoving() {
        return this.isSpinning() || this.hasRetreated();
    }

    @Override
    public boolean shouldEnterWater() {
        return this.m_5448_() == null && !this.shouldLeaveWater() && this.swimTimer <= -1000;
    }

    @Override
    public boolean shouldLeaveWater() {
        return this.swimTimer > 600 || this.hasEgg();
    }

    @Override
    public int getWaterSearchRange() {
        return 10;
    }

    public boolean m_6063_() {
        return false;
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.shouldStopMoving()) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            travelVector = Vec3.f_82478_;
            super.m_7023_(travelVector);
            return;
        }
        if (this.m_21515_() && this.m_20069_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(0.9));
            if (this.m_5448_() == null) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.005, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    @Nonnull
    public ItemStack m_28282_() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.TERRAPIN_BUCKET.get());
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    public void m_6872_(@Nonnull ItemStack bucket) {
        if (this.m_8077_()) {
            bucket.m_41714_(this.m_7770_());
        }
        CompoundTag platTag = new CompoundTag();
        this.m_7380_(platTag);
        CompoundTag compound = bucket.m_41784_();
        compound.m_128365_("TerrapinData", (Tag)platTag);
    }

    public void m_142278_(@Nonnull CompoundTag compound) {
        if (compound.m_128441_("TerrapinData")) {
            this.m_7378_(compound.m_128469_("TerrapinData"));
        }
    }

    @Nonnull
    public InteractionResult m_6071_(@Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        if (itemstack.m_204117_(AMTagRegistry.TERRAPIN_BREEDABLES)) {
            this.m_21530_();
        }
        return Bucketable.m_148828_((Player)player, (InteractionHand)hand, (LivingEntity)this).orElse(super.m_6071_(player, hand));
    }

    public void m_267651_(boolean flying) {
        float f1 = (float)Mth.m_184648_((double)(this.m_20185_() - this.f_19854_), (double)0.0, (double)(this.m_20189_() - this.f_19856_));
        float f2 = Math.min(f1 * (this.isSpinning() ? 4.0f : 32.0f), 1.0f);
        this.f_267362_.m_267566_(f2, 0.4f);
    }

    public boolean isKoopa() {
        String s = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        return s != null && s.toLowerCase().contains("koopa");
    }

    public MobType m_6336_() {
        return MobType.f_21644_;
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    static class MateGoal
    extends BreedGoal {
        private final EntityTerrapin turtle;

        MateGoal(EntityTerrapin turtle, double speedIn) {
            super((Animal)turtle, speedIn);
            this.turtle = turtle;
        }

        public boolean m_8036_() {
            return super.m_8036_() && !this.turtle.hasEgg();
        }

        protected void m_8026_() {
            Animal animal;
            ServerPlayer serverplayerentity = this.f_25113_.m_27592_();
            if (serverplayerentity == null && this.f_25115_.m_27592_() != null) {
                serverplayerentity = this.f_25115_.m_27592_();
            }
            if (serverplayerentity != null) {
                serverplayerentity.m_36220_(Stats.f_12937_);
                CriteriaTriggers.f_10581_.m_147278_(serverplayerentity, this.f_25113_, this.f_25115_, (AgeableMob)this.f_25113_);
            }
            if ((animal = this.f_25115_) instanceof EntityTerrapin) {
                EntityTerrapin terrapin = (EntityTerrapin)animal;
                this.turtle.partnerData = new TileEntityTerrapinEgg.ParentData(terrapin.getTurtleType(), terrapin.getShellType(), terrapin.getSkinType(), terrapin.getTurtleColor(), terrapin.getShellColor(), terrapin.getSkinColor());
            }
            this.turtle.setHasEgg(true);
            this.f_25113_.m_27594_();
            this.f_25115_.m_27594_();
            this.f_25113_.m_146762_(6000);
            this.f_25115_.m_146762_(6000);
            RandomSource random = this.f_25113_.m_217043_();
            if (this.f_25114_.m_46469_().m_46207_(GameRules.f_46135_)) {
                this.f_25114_.m_7967_((Entity)new ExperienceOrb(this.f_25114_, this.f_25113_.m_20185_(), this.f_25113_.m_20186_(), this.f_25113_.m_20189_(), random.m_188503_(7) + 1));
            }
        }
    }

    static class LayEggGoal
    extends MoveToBlockGoal {
        private final EntityTerrapin turtle;
        private int digTime;

        LayEggGoal(EntityTerrapin turtle, double speedIn) {
            super((PathfinderMob)turtle, speedIn, 16);
            this.turtle = turtle;
        }

        public void m_8041_() {
            this.digTime = 0;
        }

        public boolean m_8036_() {
            return this.turtle.hasEgg() && super.m_8036_();
        }

        public boolean m_8045_() {
            return super.m_8045_() && this.turtle.hasEgg();
        }

        public double m_8052_() {
            return (double)this.turtle.m_20205_() + 0.5;
        }

        public void m_8037_() {
            super.m_8037_();
            BlockPos blockpos = this.turtle.m_20183_();
            this.turtle.swimTimer = 1000;
            if (!this.turtle.m_20069_() && this.m_25625_()) {
                Level world = this.turtle.m_9236_();
                this.turtle.m_146850_(GameEvent.f_157797_);
                world.m_5594_(null, blockpos, SoundEvents.f_12486_, SoundSource.BLOCKS, 0.3f, 0.9f + world.f_46441_.m_188501_() * 0.2f);
                world.m_7731_(this.f_25602_.m_7494_(), (BlockState)((Block)AMBlockRegistry.TERRAPIN_EGG.get()).m_49966_().m_61124_((Property)BlockTerrapinEgg.EGGS, (Comparable)Integer.valueOf(this.turtle.f_19796_.m_188503_(1) + 3)), 3);
                BlockEntity blockEntity = world.m_7702_(this.f_25602_.m_7494_());
                if (blockEntity instanceof TileEntityTerrapinEgg) {
                    TileEntityTerrapinEgg eggTe = (TileEntityTerrapinEgg)blockEntity;
                    eggTe.parent1 = new TileEntityTerrapinEgg.ParentData(this.turtle.getTurtleType(), this.turtle.getShellType(), this.turtle.getSkinType(), this.turtle.getTurtleColor(), this.turtle.getShellColor(), this.turtle.getSkinColor());
                    eggTe.parent2 = this.turtle.partnerData == null ? eggTe.parent1 : this.turtle.partnerData;
                }
                this.turtle.setHasEgg(false);
                this.turtle.m_27601_(600);
            }
        }

        protected boolean m_6465_(LevelReader worldIn, BlockPos pos) {
            return worldIn.m_46859_(pos.m_7494_()) && BlockTerrapinEgg.isProperHabitat((BlockGetter)worldIn, pos);
        }
    }
}

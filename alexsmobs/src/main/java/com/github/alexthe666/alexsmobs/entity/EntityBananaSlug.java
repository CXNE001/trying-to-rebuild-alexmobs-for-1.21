/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.navigation.WallClimberNavigation
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class EntityBananaSlug
extends Animal {
    private static final EntityDataAccessor<Direction> ATTACHED_FACE = SynchedEntityData.m_135353_(EntityBananaSlug.class, (EntityDataSerializer)EntityDataSerializers.f_135040_);
    private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.m_135353_(EntityBananaSlug.class, (EntityDataSerializer)EntityDataSerializers.f_135027_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityBananaSlug.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final Direction[] POSSIBLE_DIRECTIONS = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    public float trailYaw;
    public float prevTrailYaw;
    public float trailVisability;
    public float prevTrailVisability;
    public float attachChangeProgress = 0.0f;
    public float prevAttachChangeProgress = 0.0f;
    public Direction prevAttachDir = Direction.DOWN;
    public int timeUntilSlime = this.f_19796_.m_188503_(12000) + 24000;

    protected EntityBananaSlug(EntityType<? extends Animal> animal, Level level) {
        super(animal, level);
        this.prevTrailYaw = this.f_20883_;
        this.trailYaw = this.f_20883_;
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new WallClimberNavigation((Mob)this, worldIn);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.BANANA_SLUG_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.BANANA_SLUG_HURT.get();
    }

    public static boolean checkBananaSlugSpawnRules(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return !worldIn.m_8055_(pos.m_7495_()).m_60795_();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.bananaSlugSpawnRolls, this.m_217043_(), spawnReasonIn) && super.m_5545_(worldIn, spawnReasonIn);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CLIMBING, (Object)0);
        this.f_19804_.m_135372_(ATTACHED_FACE, (Object)Direction.DOWN);
        this.f_19804_.m_135372_(VARIANT, (Object)0);
    }

    public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
        return false;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected void m_6763_(BlockState state) {
    }

    public boolean m_6040_() {
        return true;
    }

    @javax.annotation.Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @javax.annotation.Nullable CompoundTag dataTag) {
        this.setVariant(this.f_19796_.m_188503_(4));
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public boolean m_6147_() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return ((Byte)this.f_19804_.m_135370_(CLIMBING) & 1) != 0 && this.getAttachmentFacing() != Direction.DOWN;
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.BANANA_SLUG_BREEDABLES);
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = (Byte)this.f_19804_.m_135370_(CLIMBING);
        b0 = climbing ? (byte)(b0 | 1) : (byte)(b0 & 0xFFFFFFFE);
        this.f_19804_.m_135381_(CLIMBING, (Object)b0);
    }

    public Direction getAttachmentFacing() {
        return (Direction)this.f_19804_.m_135370_(ATTACHED_FACE);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(2, (Goal)new TemptGoal((PathfinderMob)this, 1.0, Ingredient.m_204132_(AMTagRegistry.BANANA_SLUG_BREEDABLES), false));
        this.f_21345_.m_25352_(3, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 40, 1.0, 10, 7));
        this.f_21345_.m_25352_(5, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 5.0f));
        this.f_21345_.m_25352_(6, (Goal)new RandomLookAroundGoal((Mob)this));
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 4.0).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22279_, (double)0.1f);
    }

    public void m_8119_() {
        boolean flag;
        boolean showTrail;
        super.m_8119_();
        this.prevTrailYaw = this.trailYaw;
        this.f_20883_ = Mth.m_14148_((float)this.f_20884_, (float)this.f_20883_, (float)this.m_8085_());
        this.trailYaw = Mth.m_14148_((float)this.trailYaw, (float)this.f_20883_, (float)2.0f);
        this.prevTrailVisability = this.trailVisability;
        this.prevAttachChangeProgress = this.attachChangeProgress;
        boolean bl = showTrail = this.isTrailVisible() && this.m_20184_().m_82553_() > (double)0.03f;
        if (this.prevAttachDir != this.getAttachmentFacing()) {
            if (this.attachChangeProgress < 5.0f) {
                this.attachChangeProgress += 1.0f;
                this.trailYaw = this.f_20883_;
            } else if (this.attachChangeProgress >= 5.0f) {
                this.prevAttachDir = this.getAttachmentFacing();
            }
        } else {
            this.attachChangeProgress = 5.0f;
        }
        if (this.trailVisability < 1.0f && showTrail) {
            this.trailVisability = Math.min(1.0f, this.trailVisability + 0.1f);
        }
        if (this.trailVisability > 0.0f && !showTrail) {
            float dec = this.m_20184_().m_82553_() > (double)0.03f ? 1.0f : 0.1f;
            this.trailVisability = Math.max(0.0f, this.trailVisability - dec);
        }
        Vec3 vector3d = this.m_20184_();
        if (!this.m_9236_().f_46443_) {
            this.setBesideClimbableBlock(this.f_19862_);
            this.setBesideClimbableBlock(this.f_19862_ || this.f_19863_ && !this.m_20096_());
            if (this.m_20096_() || this.m_20072_() || this.m_20077_()) {
                this.f_19804_.m_135381_(ATTACHED_FACE, (Object)Direction.DOWN);
            } else if (this.f_19863_) {
                this.f_19804_.m_135381_(ATTACHED_FACE, (Object)Direction.UP);
            } else {
                flag = false;
                Direction closestDirection = Direction.DOWN;
                double closestDistance = 100.0;
                for (Direction dir : POSSIBLE_DIRECTIONS) {
                    BlockPos antPos = new BlockPos(Mth.m_14107_((double)this.m_20185_()), Mth.m_14107_((double)this.m_20186_()), Mth.m_14107_((double)this.m_20189_()));
                    BlockPos offsetPos = antPos.m_121945_(dir);
                    Vec3 offset = Vec3.m_82512_((Vec3i)offsetPos);
                    if (!(closestDistance > this.m_20182_().m_82554_(offset)) || !this.m_9236_().m_46578_(offsetPos, (Entity)this, dir.m_122424_())) continue;
                    closestDistance = this.m_20182_().m_82554_(offset);
                    closestDirection = dir;
                }
                this.f_19804_.m_135381_(ATTACHED_FACE, (Object)closestDirection);
            }
        }
        flag = false;
        if (this.getAttachmentFacing() != Direction.DOWN) {
            if (this.getAttachmentFacing() == Direction.UP) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, 1.0, 0.0));
            } else {
                if (!this.f_19862_ && this.getAttachmentFacing() != Direction.UP) {
                    Vec3 vec = Vec3.m_82528_((Vec3i)this.getAttachmentFacing().m_122436_());
                    this.m_20256_(this.m_20184_().m_82549_(vec.m_82541_().m_82542_((double)0.1f, (double)0.1f, (double)0.1f)));
                }
                if (!this.m_20096_() && vector3d.f_82480_ < 0.0) {
                    this.m_20256_(this.m_20184_().m_82542_(1.0, 0.5, 1.0));
                    flag = true;
                }
            }
        }
        if (this.getAttachmentFacing() == Direction.UP) {
            this.m_20242_(true);
            this.m_20256_(vector3d.m_82542_(0.7, 1.0, 0.7));
        } else {
            this.m_20242_(false);
        }
        if (!flag && this.m_6147_()) {
            this.m_20256_(vector3d.m_82542_(1.0, 0.4, 1.0));
        }
        if (!this.m_9236_().f_46443_ && this.m_6084_() && !this.m_6162_() && --this.timeUntilSlime <= 0) {
            this.m_19998_((ItemLike)AMItemRegistry.BANANA_SLUG_SLIME.get());
            this.timeUntilSlime = this.f_19796_.m_188503_(12000) + 24000;
        }
    }

    protected float m_6118_() {
        return super.m_6118_();
    }

    public int m_8132_() {
        return 1;
    }

    public int m_8085_() {
        return 4;
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
    }

    private boolean isTrailVisible() {
        if (this.m_20072_()) {
            return false;
        }
        if (this.m_20096_()) {
            Vec3 modelBack = new Vec3(0.0, (double)-0.1f, this.m_6162_() ? (double)-0.35f : (double)-0.7f).m_82524_(-this.trailYaw * ((float)Math.PI / 180));
            Vec3 slugBack = this.m_20182_().m_82549_(modelBack);
            BlockPos backPos = AMBlockPos.fromVec3(slugBack);
            BlockState state = this.m_9236_().m_8055_(backPos);
            VoxelShape shape = state.m_60812_((BlockGetter)this.m_9236_(), backPos);
            if (shape.m_83281_()) {
                return false;
            }
            Optional closest = shape.m_166067_(modelBack.m_82520_(0.0, 1.0, 0.0));
            return closest.isPresent() && Math.min((float)((Vec3)closest.get()).f_82480_, 1.0f) >= 0.8f;
        }
        if (this.getAttachmentFacing().m_122434_() != Direction.Axis.Y) {
            BlockPos pos = this.m_20183_().m_121945_(this.getAttachmentFacing()).m_6630_(this.m_20184_().f_82480_ <= (double)-0.001f ? 1 : -1);
            BlockState state = this.m_9236_().m_8055_(pos);
            VoxelShape shape = state.m_60812_((BlockGetter)this.m_9236_(), pos);
            return !shape.m_83281_();
        }
        return this.getAttachmentFacing() != Direction.DOWN;
    }

    public void m_7350_(EntityDataAccessor<?> entityDataAccessor) {
        super.m_7350_(entityDataAccessor);
        if (ATTACHED_FACE.equals(entityDataAccessor)) {
            this.prevAttachChangeProgress = 0.0f;
            this.attachChangeProgress = 0.0f;
        }
    }

    public void m_267651_(boolean flying) {
        float f1 = (float)Mth.m_184648_((double)(this.m_20185_() - this.f_19854_), (double)(0.5 * (this.m_20186_() - this.f_19855_)), (double)(this.m_20189_() - this.f_19856_));
        float f2 = Math.min(f1 * 16.0f, 1.0f);
        this.f_267362_.m_267566_(f2, 0.4f);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel level, AgeableMob mob) {
        EntityBananaSlug slug = (EntityBananaSlug)((EntityType)AMEntityRegistry.BANANA_SLUG.get()).m_20615_(this.m_9236_());
        slug.setVariant(this.getVariant());
        return slug;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("Variant", this.getVariant());
        compound.m_128405_("SlimeTime", this.timeUntilSlime);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128441_("SlimeTime")) {
            this.timeUntilSlime = compound.m_128451_("SlimeTime");
        }
        this.setVariant(compound.m_128451_("Variant"));
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int i) {
        this.f_19804_.m_135381_(VARIANT, (Object)i);
    }
}

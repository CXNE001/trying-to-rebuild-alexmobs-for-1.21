/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RandomStrollGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.ProjectileUtil
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.PathComputationType
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ToolActions
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityStraddler;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AquaticMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.BoneSerpentPathNavigator;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityStradpole
extends WaterAnimal
implements Bucketable {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.m_135353_(EntityStradpole.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> DESPAWN_SOON = SynchedEntityData.m_135353_(EntityStradpole.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> LAUNCHED = SynchedEntityData.m_135353_(EntityStradpole.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.m_135353_(EntityStradpole.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    public float swimPitch = 0.0f;
    public float prevSwimPitch = 0.0f;
    private int despawnTimer = 0;
    private int ricochetCount = 0;

    protected EntityStradpole(EntityType type, Level world) {
        super(type, world);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.LAVA, 0.0f);
        this.f_21342_ = new AquaticMoveController((PathfinderMob)this, 1.4f);
    }

    protected SoundEvent m_5592_() {
        return SoundEvents.f_11759_;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return SoundEvents.f_11761_;
    }

    public int m_5792_() {
        return 2;
    }

    @Nonnull
    public ItemStack m_28282_() {
        ItemStack stack = new ItemStack((ItemLike)AMItemRegistry.STRADPOLE_BUCKET.get());
        if (this.m_8077_()) {
            stack.m_41714_(this.m_7770_());
        }
        return stack;
    }

    public void m_6872_(@Nonnull ItemStack bucket) {
        if (this.m_8077_()) {
            bucket.m_41714_(this.m_7770_());
        }
        Bucketable.m_148822_((Mob)this, (ItemStack)bucket);
    }

    public void m_142278_(@Nonnull CompoundTag compound) {
        Bucketable.m_148825_((Mob)this, (CompoundTag)compound);
    }

    @Nonnull
    protected InteractionResult m_6071_(@Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        if (itemstack.m_204117_(AMTagRegistry.STRADPOLE_GROWABLES)) {
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
            if (this.f_19796_.m_188501_() < 0.45f) {
                EntityStraddler straddler = (EntityStraddler)((EntityType)AMEntityRegistry.STRADDLER.get()).m_20615_(this.m_9236_());
                straddler.m_20359_((Entity)this);
                if (!this.m_9236_().f_46443_ && this.m_9236_().m_7967_((Entity)straddler)) {
                    this.m_142687_(Entity.RemovalReason.DISCARDED);
                }
            }
            return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
        }
        if (itemstack.m_41720_() == Items.f_42448_ && this.m_6084_()) {
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(this.m_142623_(), 1.0f, 1.0f);
            ItemStack itemstack1 = this.m_28282_();
            this.m_6872_(itemstack1);
            ItemStack itemstack2 = ItemUtils.m_41817_((ItemStack)itemstack, (Player)player, (ItemStack)itemstack1, (boolean)false);
            player.m_21008_(hand, itemstack2);
            Level level = this.m_9236_();
            if (!level.f_46443_) {
                CriteriaTriggers.f_10576_.m_38772_((ServerPlayer)player, itemstack1);
            }
            this.m_146870_();
            return InteractionResult.m_19078_((boolean)level.f_46443_);
        }
        return super.m_6071_(player, hand);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 4.0).m_22268_(Attributes.f_22279_, (double)0.3f);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(PARENT_UUID, Optional.empty());
        this.f_19804_.m_135372_(DESPAWN_SOON, (Object)false);
        this.f_19804_.m_135372_(LAUNCHED, (Object)false);
        this.f_19804_.m_135372_(FROM_BUCKET, (Object)false);
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

    @Nullable
    public UUID getParentId() {
        return ((Optional)this.f_19804_.m_135370_(PARENT_UUID)).orElse(null);
    }

    public void setParentId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(PARENT_UUID, Optional.ofNullable(uniqueId));
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getParentId() != null) {
            compound.m_128362_("ParentUUID", this.getParentId());
        }
        compound.m_128379_("FromBucket", this.m_27487_());
        compound.m_128379_("DespawnSoon", this.isDespawnSoon());
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_27487_();
    }

    public boolean m_6785_(double p_27492_) {
        return !this.m_27487_() && !this.m_8077_();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.stradpoleSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canStradpoleSpawn(EntityType<EntityStradpole> p_234314_0_, LevelAccessor p_234314_1_, MobSpawnType p_234314_2_, BlockPos p_234314_3_, RandomSource p_234314_4_) {
        if (p_234314_1_.m_6425_(p_234314_3_).m_205070_(FluidTags.f_13132_) && !p_234314_1_.m_6425_(p_234314_3_.m_7495_()).m_205070_(FluidTags.f_13132_)) {
            return p_234314_1_.m_46859_(p_234314_3_.m_7494_());
        }
        return false;
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("ParentUUID")) {
            this.setParentId(compound.m_128342_("ParentUUID"));
        }
        this.m_27497_(compound.m_128471_("FromBucket"));
        this.setDespawnSoon(compound.m_128471_("DespawnSoon"));
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new StradpoleAISwim(this, 1.0, 10));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(5, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        if (!worldIn.m_8055_(pos).m_60819_().m_76178_()) {
            return 15.0f;
        }
        return Float.NEGATIVE_INFINITY;
    }

    public boolean isDespawnSoon() {
        return (Boolean)this.f_19804_.m_135370_(DESPAWN_SOON);
    }

    public void setDespawnSoon(boolean despawnSoon) {
        this.f_19804_.m_135381_(DESPAWN_SOON, (Object)despawnSoon);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new BoneSerpentPathNavigator((Mob)this, worldIn);
    }

    public void m_8119_() {
        float f = 1.0f;
        if (((Boolean)this.f_19804_.m_135370_(LAUNCHED)).booleanValue()) {
            this.f_20883_ = this.m_146908_();
            HitResult raytraceresult = ProjectileUtil.m_278158_((Entity)this, this::canHitEntity);
            if (raytraceresult != null && raytraceresult.m_6662_() != HitResult.Type.MISS) {
                this.onImpact(raytraceresult);
            }
            f = 0.1f;
        }
        super.m_8119_();
        boolean liquid = this.m_20069_() || this.m_20077_();
        this.prevSwimPitch = this.swimPitch;
        this.swimPitch = (float)(-((double)((float)this.m_20184_().f_82480_ * (liquid ? 2.5f : f)) * 57.2957763671875));
        if (this.m_20096_() && !this.m_20069_() && !this.m_20077_()) {
            this.m_20256_(this.m_20184_().m_82520_((double)((this.f_19796_.m_188501_() * 2.0f - 1.0f) * 0.2f), 0.5, (double)((this.f_19796_.m_188501_() * 2.0f - 1.0f) * 0.2f)));
            this.m_146922_(this.f_19796_.m_188501_() * 360.0f);
            this.m_6853_(false);
            this.f_19812_ = true;
        }
        this.m_20242_(false);
        if (liquid) {
            this.m_20242_(true);
        }
        if (this.isDespawnSoon()) {
            ++this.despawnTimer;
            if (this.despawnTimer > 100) {
                this.despawnTimer = 0;
                this.m_21373_();
                this.m_142687_(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    private void onImpact(HitResult raytraceresult) {
        HitResult.Type raytraceresult$type = raytraceresult.m_6662_();
        if (raytraceresult$type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)raytraceresult);
        } else if (raytraceresult$type == HitResult.Type.BLOCK) {
            BlockHitResult traceResult = (BlockHitResult)raytraceresult;
            BlockState blockstate = this.m_9236_().m_8055_(traceResult.m_82425_());
            if (!blockstate.m_60816_((BlockGetter)this.m_9236_(), traceResult.m_82425_()).m_83281_()) {
                Direction face = traceResult.m_82434_();
                Vec3 prevMotion = this.m_20184_();
                double motionX = prevMotion.m_7096_();
                double motionY = prevMotion.m_7098_();
                double motionZ = prevMotion.m_7094_();
                switch (face) {
                    case EAST: 
                    case WEST: {
                        motionX = -motionX;
                        break;
                    }
                    case SOUTH: 
                    case NORTH: {
                        motionZ = -motionZ;
                        break;
                    }
                    default: {
                        motionY = -motionY;
                    }
                }
                this.m_20334_(motionX, motionY, motionZ);
                if (this.f_19797_ > 200 || this.ricochetCount > 20) {
                    this.f_19804_.m_135381_(LAUNCHED, (Object)false);
                } else {
                    ++this.ricochetCount;
                }
            }
        }
    }

    public Entity getParent() {
        UUID id = this.getParentId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    private void onEntityHit(EntityHitResult raytraceresult) {
        Entity entity;
        Entity entity2 = this.getParent();
        if (entity2 instanceof LivingEntity && !this.m_9236_().f_46443_ && (entity = raytraceresult.m_82443_()) instanceof LivingEntity) {
            LivingEntity target = (LivingEntity)entity;
            if (!target.m_21254_()) {
                target.m_6469_(this.m_269291_().m_269299_((Entity)this, (LivingEntity)entity2), 3.0f);
                target.m_147240_((double)0.7f, entity2.m_20185_() - this.m_20185_(), entity2.m_20189_() - this.m_20189_());
            } else if (this.m_5448_() instanceof Player) {
                this.damageShieldFor((Player)this.m_5448_(), 3.0f);
            }
            this.f_19804_.m_135381_(LAUNCHED, (Object)false);
        }
    }

    protected void damageShieldFor(Player holder, float damage) {
        if (holder.m_21211_().canPerformAction(ToolActions.SHIELD_BLOCK)) {
            if (!this.m_9236_().f_46443_) {
                holder.m_36246_(Stats.f_12982_.m_12902_((Object)holder.m_21211_().m_41720_()));
            }
            if (damage >= 3.0f) {
                int i = 1 + Mth.m_14143_((float)damage);
                InteractionHand hand = holder.m_7655_();
                holder.m_21211_().m_41622_(i, (LivingEntity)holder, p_213833_1_ -> {
                    p_213833_1_.m_21190_(hand);
                    ForgeEventFactory.onPlayerDestroyItem((Player)holder, (ItemStack)holder.m_21211_(), (InteractionHand)hand);
                });
                if (holder.m_21211_().m_41619_()) {
                    if (hand == InteractionHand.MAIN_HAND) {
                        holder.m_8061_(EquipmentSlot.MAINHAND, ItemStack.f_41583_);
                    } else {
                        holder.m_8061_(EquipmentSlot.OFFHAND, ItemStack.f_41583_);
                    }
                    holder.m_5496_(SoundEvents.f_12347_, 0.8f, 0.8f + this.m_9236_().f_46441_.m_188501_() * 0.4f);
                }
            }
        }
    }

    protected boolean canHitEntity(Entity p_230298_1_) {
        return !p_230298_1_.m_5833_() && !(p_230298_1_ instanceof EntityStraddler) && !(p_230298_1_ instanceof EntityStradpole);
    }

    public boolean m_6060_() {
        return false;
    }

    public boolean canStandOnFluid(Fluid p_230285_1_) {
        return p_230285_1_.m_205067_(FluidTags.f_13132_);
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_() && (this.m_20069_() || this.m_20077_())) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(0.9));
            if (this.m_5448_() == null) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, -0.05, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    protected void m_6229_(int p_209207_1_) {
    }

    public void shoot(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
        Vec3 lvt_9_1_ = new Vec3(p_70186_1_, p_70186_3_, p_70186_5_).m_82541_().m_82520_(this.f_19796_.m_188583_() * (double)0.0075f * (double)p_70186_8_, this.f_19796_.m_188583_() * (double)0.0075f * (double)p_70186_8_, this.f_19796_.m_188583_() * (double)0.0075f * (double)p_70186_8_).m_82490_((double)p_70186_7_);
        this.m_20256_(lvt_9_1_);
        float lvt_10_1_ = (float)lvt_9_1_.m_165925_();
        this.m_146922_((float)(Mth.m_14136_((double)lvt_9_1_.f_82479_, (double)lvt_9_1_.f_82481_) * 57.2957763671875));
        this.m_146926_((float)(Mth.m_14136_((double)lvt_9_1_.f_82480_, (double)lvt_10_1_) * 57.2957763671875));
        this.f_19860_ = this.m_146909_();
        this.f_20883_ = this.m_146908_();
        this.f_20885_ = this.m_146908_();
        this.f_20886_ = this.m_146908_();
        this.f_19859_ = this.m_146908_();
        this.setDespawnSoon(true);
        this.f_19804_.m_135381_(LAUNCHED, (Object)true);
    }

    static class StradpoleAISwim
    extends RandomStrollGoal {
        public StradpoleAISwim(EntityStradpole creature, double speed, int chance) {
            super((PathfinderMob)creature, speed, chance, false);
        }

        public boolean m_8036_() {
            if (!this.f_25725_.m_20077_() && !this.f_25725_.m_20069_() || this.f_25725_.m_20159_() || this.f_25725_.m_5448_() != null || !this.f_25725_.m_20069_() && !this.f_25725_.m_20077_() && this.f_25725_ instanceof ISemiAquatic && !((ISemiAquatic)this.f_25725_).shouldEnterWater()) {
                return false;
            }
            if (!this.f_25731_ && this.f_25725_.m_217043_().m_188503_(this.f_25730_) != 0) {
                return false;
            }
            Vec3 vector3d = this.m_7037_();
            if (vector3d == null) {
                return false;
            }
            this.f_25726_ = vector3d.f_82479_;
            this.f_25727_ = vector3d.f_82480_;
            this.f_25728_ = vector3d.f_82481_;
            this.f_25731_ = false;
            return true;
        }

        @Nullable
        protected Vec3 m_7037_() {
            Vec3 vector3d;
            if (this.f_25725_.m_217043_().m_188501_() < 0.3f && (vector3d = this.findSurfaceTarget(this.f_25725_, 15, 7)) != null) {
                return vector3d;
            }
            vector3d = LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)7, (int)3);
            int i = 0;
            while (vector3d != null && !this.f_25725_.m_9236_().m_6425_(AMBlockPos.fromVec3(vector3d)).m_205070_(FluidTags.f_13132_) && !this.f_25725_.m_9236_().m_8055_(AMBlockPos.fromVec3(vector3d)).m_60647_((BlockGetter)this.f_25725_.m_9236_(), AMBlockPos.fromVec3(vector3d), PathComputationType.WATER) && i++ < 15) {
                vector3d = LandRandomPos.m_148488_((PathfinderMob)this.f_25725_, (int)10, (int)7);
            }
            return vector3d;
        }

        private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
            BlockPos blockpos = pos.m_7918_(dx * scale, 0, dz * scale);
            return this.f_25725_.m_9236_().m_6425_(blockpos).m_205070_(FluidTags.f_13132_) || this.f_25725_.m_9236_().m_6425_(blockpos).m_205070_(FluidTags.f_13131_) && !this.f_25725_.m_9236_().m_8055_(blockpos).m_280555_();
        }

        private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
            return this.f_25725_.m_9236_().m_8055_(pos.m_7918_(dx * scale, 1, dz * scale)).m_60795_() && this.f_25725_.m_9236_().m_8055_(pos.m_7918_(dx * scale, 2, dz * scale)).m_60795_();
        }

        private Vec3 findSurfaceTarget(PathfinderMob creature, int i, int i1) {
            BlockPos upPos = creature.m_20183_();
            while (creature.m_9236_().m_6425_(upPos).m_205070_(FluidTags.f_13131_) || creature.m_9236_().m_6425_(upPos).m_205070_(FluidTags.f_13132_)) {
                upPos = upPos.m_7494_();
            }
            if (this.isAirAbove(upPos.m_7495_(), 0, 0, 0) && this.canJumpTo(upPos.m_7495_(), 0, 0, 0)) {
                return new Vec3((double)((float)upPos.m_123341_() + 0.5f), (double)((float)upPos.m_123342_() - 1.0f), (double)((float)upPos.m_123343_() + 0.5f));
            }
            return null;
        }
    }
}

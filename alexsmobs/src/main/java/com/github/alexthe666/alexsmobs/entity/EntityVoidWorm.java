/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.BossEvent$BossBarColor
 *  net.minecraft.world.BossEvent$BossBarOverlay
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.block.BlockEnderResidue;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityVoidPortal;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormShot;
import com.github.alexthe666.alexsmobs.entity.ai.DirectPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.EntityAINearestTarget3D;
import com.github.alexthe666.alexsmobs.entity.ai.FlightMoveController;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityVoidWorm
extends Monster {
    public static final ResourceLocation SPLITTER_LOOT = new ResourceLocation("alexsmobs", "entities/void_worm_splitter");
    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Optional<UUID>> SPLIT_FROM_UUID = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Integer> SEGMENT_COUNT = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> JAW_TICKS = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Float> WORM_ANGLE = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> SPEEDMOD = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> SPLITTER = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> PORTAL_TICKS = SynchedEntityData.m_135353_(EntityVoidWorm.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private final ServerBossEvent bossInfo = (ServerBossEvent)new ServerBossEvent(this.m_5446_(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS).m_7003_(true);
    public float prevWormAngle;
    public float prevJawProgress;
    public float jawProgress;
    public Vec3 teleportPos = null;
    public EntityVoidPortal portalTarget = null;
    public boolean fullyThrough = true;
    public boolean updatePostSummon = false;
    private int makePortalCooldown = 0;
    private int stillTicks = 0;
    private int blockBreakCounter;
    private int makeIdlePortalCooldown = 200 + this.f_19796_.m_188503_(800);

    protected EntityVoidWorm(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        this.f_21364_ = 10;
        this.f_21342_ = new FlightMoveController((Mob)this, 1.0f, false, true);
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.VOID_WORM_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.VOID_WORM_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.VOID_WORM_HURT.get();
    }

    protected float m_6121_() {
        return this.m_20067_() ? 0.0f : 5.0f;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.voidWormSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canVoidWormSpawn(EntityType animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return true;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, AMConfig.voidWormMaxHealth).m_22268_(Attributes.f_22284_, 4.0).m_22268_(Attributes.f_22277_, 256.0).m_22268_(Attributes.f_22279_, (double)0.3f).m_22268_(Attributes.f_22281_, 5.0);
    }

    @Nullable
    protected ResourceLocation m_7582_() {
        return this.isSplitter() ? SPLITTER_LOOT : super.m_7582_();
    }

    public void m_6074_() {
        this.m_142687_(Entity.RemovalReason.DISCARDED);
    }

    public void m_6667_(DamageSource cause) {
        super.m_6667_(cause);
        if (!this.m_9236_().f_46443_ && !this.isSplitter() && cause != null && cause.m_7639_() instanceof ServerPlayer) {
            AMAdvancementTriggerRegistry.VOID_WORM_SLAY_HEAD.trigger((ServerPlayer)cause.m_7639_());
        }
    }

    public ItemEntity m_19983_(ItemStack stack) {
        ItemEntity itementity = this.m_5552_(stack, 0.0f);
        if (itementity != null) {
            itementity.m_20242_(true);
            itementity.m_146915_(true);
            itementity.m_32064_();
        }
        return itementity;
    }

    protected void m_6668_(DamageSource source) {
    }

    private void placeDropsSafely(Collection<ItemEntity> drops) {
        BlockPos pos = this.m_20183_();
        while (!this.m_9236_().m_8055_(pos).m_247087_() && pos.m_123342_() < this.m_9236_().m_151558_() - 2) {
            pos = pos.m_7494_();
        }
        int radius = 2;
        BlockState residue = (BlockState)((Block)AMBlockRegistry.ENDER_RESIDUE.get()).m_49966_().m_61124_((Property)BlockEnderResidue.SLOW_DECAY, (Comparable)Boolean.valueOf(true));
        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    double sq = x * x + y * y + z * z;
                    BlockPos pos1 = pos.m_7918_(x, y, z);
                    BlockState state = this.m_9236_().m_8055_(pos1);
                    if (!(sq <= (double)(radius * radius)) || !(sq >= (double)((float)(radius * radius) - 2.0f)) || !state.m_247087_() && !state.m_60713_((Block)AMBlockRegistry.ENDER_RESIDUE.get())) continue;
                    this.m_9236_().m_46597_(pos1, residue);
                }
            }
        }
        this.m_9236_().m_46597_(pos, Blocks.f_50016_.m_49966_());
        for (ItemEntity drop : drops) {
            drop.m_146884_(Vec3.m_82539_((Vec3i)pos));
            drop.m_146915_(true);
            drop.m_20242_(true);
            drop.m_32060_();
            drop.m_149678_();
            drop.m_20256_(Vec3.f_82478_);
            this.m_9236_().m_7967_((Entity)drop);
        }
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268671_) || source.m_276093_(DamageTypes.f_268722_) || source.m_276093_(DamageTypes.f_268612_) || source.m_276093_(DamageTypes.f_268546_) || source.m_276093_(DamageTypes.f_268724_) || source.m_269533_(DamageTypeTags.f_268745_) || super.m_6673_(source);
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return false;
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_25352_(1, (Goal)new AIEnterPortal());
        this.f_21345_.m_25352_(2, (Goal)new AIAttack());
        this.f_21345_.m_25352_(3, (Goal)new AIFlyIdle());
        this.f_21346_.m_25352_(1, new EntityAINearestTarget3D<Player>((Mob)this, Player.class, 10, false, true, null));
        this.f_21346_.m_25352_(2, new EntityAINearestTarget3D<EnderDragon>((Mob)this, EnderDragon.class, 10, false, true, null));
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new DirectPathNavigator((Mob)this, this.m_9236_());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128403_("ChildUUID")) {
            this.setChildId(compound.m_128342_("ChildUUID"));
        }
        this.setWormSpeed(compound.m_128457_("WormSpeed"));
        this.setSplitter(compound.m_128471_("Splitter"));
        this.setPortalTicks(compound.m_128451_("PortalTicks"));
        this.makeIdlePortalCooldown = compound.m_128451_("MakePortalTime");
        this.makePortalCooldown = compound.m_128451_("MakePortalCooldown");
        if (this.m_8077_()) {
            this.bossInfo.m_6456_(this.m_5446_());
        }
    }

    public void m_6593_(@Nullable Component name) {
        super.m_6593_(name);
        this.bossInfo.m_6456_(this.m_5446_());
    }

    public boolean m_20068_() {
        return true;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getChildId() != null) {
            compound.m_128362_("ChildUUID", this.getChildId());
        }
        compound.m_128405_("PortalTicks", this.getPortalTicks());
        compound.m_128405_("MakePortalTime", this.makeIdlePortalCooldown);
        compound.m_128405_("MakePortalCooldown", this.makePortalCooldown);
        compound.m_128350_("WormSpeed", this.getWormSpeed());
        compound.m_128379_("Splitter", this.isSplitter());
    }

    public Entity getChild() {
        UUID id = this.getChildId();
        if (id != null && !this.m_9236_().f_46443_) {
            return ((ServerLevel)this.m_9236_()).m_8791_(id);
        }
        return null;
    }

    public boolean m_6573_(Player player) {
        return true;
    }

    public int m_213860_() {
        return this.isSplitter() ? 8 : 50;
    }

    public void m_8119_() {
        Entity child;
        super.m_8119_();
        this.prevWormAngle = this.getWormAngle();
        this.prevJawProgress = this.jawProgress;
        float threshold = 0.05f;
        this.f_21364_ = this.isSplitter() ? 10 : 70;
        if (this.f_19859_ - this.m_146908_() > threshold) {
            this.setWormAngle(this.getWormAngle() + 15.0f);
        } else if (this.f_19859_ - this.m_146908_() < -threshold) {
            this.setWormAngle(this.getWormAngle() - 15.0f);
        } else if (this.getWormAngle() > 0.0f) {
            this.setWormAngle(Math.max(this.getWormAngle() - 20.0f, 0.0f));
        } else if (this.getWormAngle() < 0.0f) {
            this.setWormAngle(Math.min(this.getWormAngle() + 20.0f, 0.0f));
        }
        if (!this.m_9236_().f_46443_) {
            if (!this.fullyThrough) {
                this.m_20256_(this.m_20184_().m_82542_((double)0.9f, (double)0.9f, (double)0.9f).m_82520_(0.0, -0.01, 0.0));
            } else {
                this.m_20256_(this.m_20184_().m_82520_(0.0, 0.01, 0.0));
            }
        }
        this.stillTicks = Math.abs(this.f_19854_ - this.m_20185_()) < (double)0.01f && Math.abs(this.f_19855_ - this.m_20186_()) < (double)0.01f && Math.abs(this.f_19856_ - this.m_20189_()) < (double)0.01f ? ++this.stillTicks : 0;
        if (this.stillTicks > 40 && this.makePortalCooldown == 0) {
            this.createStuckPortal();
        }
        if (this.makePortalCooldown > 0) {
            --this.makePortalCooldown;
        }
        if (this.makeIdlePortalCooldown > 0) {
            --this.makeIdlePortalCooldown;
        }
        if (this.makeIdlePortalCooldown == 0 && this.f_19796_.m_188503_(100) == 0) {
            this.createPortalRandomDestination();
            this.makeIdlePortalCooldown = 200 + this.f_19796_.m_188503_(1000);
        }
        if ((Integer)this.f_19804_.m_135370_(JAW_TICKS) > 0) {
            if (this.jawProgress < 5.0f) {
                this.jawProgress += 1.0f;
            }
            this.f_19804_.m_135381_(JAW_TICKS, (Object)((Integer)this.f_19804_.m_135370_(JAW_TICKS) - 1));
        } else if (this.jawProgress > 0.0f) {
            this.jawProgress -= 1.0f;
        }
        if (this.m_6084_()) {
            for (Entity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82400_(2.0))) {
                if (entity.m_7306_((Entity)this) || entity instanceof EntityVoidWormPart || entity.m_7307_((Entity)this) || entity == this) continue;
                this.launch(entity, false);
            }
            this.m_274367_(2.0f);
        } else {
            this.m_20256_(new Vec3(0.0, (double)0.03f, 0.0));
        }
        this.f_20883_ = this.m_146908_();
        float f2 = (float)(-((double)((float)this.m_20184_().f_82480_) * 57.2957763671875));
        this.m_146926_(f2);
        this.m_274367_(2.0f);
        if (!this.m_9236_().f_46443_ && (child = this.getChild()) == null) {
            Object partParent = this;
            int tailstart = Math.min(3 + this.f_19796_.m_188503_(2), this.getSegmentCount());
            int segments = this.getSegmentCount();
            for (int i = 0; i < segments; ++i) {
                float scale = 1.0f + (float)i / (float)segments * 0.5f;
                boolean tail = false;
                if (i >= segments - tailstart) {
                    tail = true;
                    scale *= 0.85f;
                }
                EntityVoidWormPart part = new EntityVoidWormPart((EntityType)AMEntityRegistry.VOID_WORM_PART.get(), (LivingEntity)partParent, 1.0f + scale * (tail ? 0.65f : 0.3f) + (i == 0 ? 0.8f : 0.0f), 180.0f, i == 0 ? -0.0f : (i == segments - tailstart ? -0.3f : 0.0f));
                part.m_20331_(partParent.m_20147_());
                part.setParent((Entity)partParent);
                if (this.updatePostSummon) {
                    part.setPortalTicks(i * 2);
                }
                part.setBodyIndex(i);
                part.setTail(tail);
                part.setWormScale(scale);
                if (partParent == this) {
                    this.setChildId(part.m_20148_());
                } else if (partParent instanceof EntityVoidWormPart) {
                    ((EntityVoidWormPart)partParent).setChildId(part.m_20148_());
                }
                part.setInitialPartPos((Entity)this);
                partParent = part;
                this.m_9236_().m_7967_((Entity)part);
            }
        }
        if (this.getPortalTicks() > 0) {
            this.setPortalTicks(this.getPortalTicks() - 1);
            if (this.getPortalTicks() == 2 && this.teleportPos != null) {
                this.m_6034_(this.teleportPos.f_82479_, this.teleportPos.f_82480_, this.teleportPos.f_82481_);
                this.teleportPos = null;
            }
        }
        if (this.portalTarget != null && this.portalTarget.getLifespan() < 5) {
            this.portalTarget = null;
        }
        this.bossInfo.m_142711_(this.m_21223_() / this.m_21233_());
        this.breakBlock();
        if (this.updatePostSummon) {
            this.updatePostSummon = false;
        }
        if (!this.m_20067_() && !this.m_9236_().f_46443_) {
            this.m_9236_().m_7605_((Entity)this, (byte)67);
        }
    }

    public double getBaseMaxHealth() {
        return this.m_21172_(Attributes.f_22276_);
    }

    public void setBaseMaxHealth(double maxHealth, boolean heal) {
        this.m_21051_(Attributes.f_22276_).m_22100_(maxHealth);
        if (heal) {
            this.m_5634_(this.m_21233_());
        }
    }

    protected void m_6153_() {
        ++this.f_20919_;
        if (this.f_20919_ == (this.isSplitter() ? 20 : 80) && !this.m_9236_().m_5776_()) {
            boolean flag;
            DamageSource source = this.m_21225_() == null ? this.m_269291_().m_269264_() : this.m_21225_();
            Entity entity = source.m_7639_();
            int i = ForgeHooks.getLootingLevel((Entity)this, (Entity)entity, (DamageSource)source);
            this.captureDrops(new ArrayList());
            boolean bl = flag = this.f_20889_ > 0;
            if (this.m_6125_() && this.m_9236_().m_46469_().m_46207_(GameRules.f_46135_)) {
                this.m_7625_(source, flag);
                this.m_7472_(source, i, flag);
            }
            this.m_5907_();
            this.m_21226_();
            Collection drops = this.captureDrops(null);
            if (!ForgeHooks.onLivingDrops((LivingEntity)this, (DamageSource)source, (Collection)drops, (int)i, (this.f_20889_ > 0 ? 1 : 0) != 0) && !drops.isEmpty()) {
                this.placeDropsSafely(drops);
            }
            this.m_9236_().m_7605_((Entity)this, (byte)60);
            this.m_142687_(Entity.RemovalReason.KILLED);
        }
    }

    public void m_6457_(ServerPlayer player) {
        super.m_6457_(player);
        this.bossInfo.m_6543_(player);
    }

    public void m_6452_(ServerPlayer player) {
        super.m_6452_(player);
        this.bossInfo.m_6539_(player);
    }

    public void teleportTo(Vec3 vec) {
        this.setPortalTicks(10);
        this.teleportPos = vec;
        this.fullyThrough = false;
        if (this.getChild() instanceof EntityVoidWormPart) {
            ((EntityVoidWormPart)this.getChild()).teleportTo(this.m_20182_(), this.teleportPos);
        }
    }

    private void launch(Entity e, boolean huge) {
        if (e.m_20096_()) {
            double d0 = e.m_20185_() - this.m_20185_();
            double d1 = e.m_20189_() - this.m_20189_();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001);
            float f = huge ? 2.0f : 0.5f;
            e.m_5997_(d0 / d2 * (double)f, huge ? 0.5 : (double)0.2f, d1 / d2 * (double)f);
        }
    }

    public void resetWormScales() {
        Entity child;
        if (!this.m_9236_().f_46443_ && (child = this.getChild()) == null) {
            Object nextPart = this;
            int tailstart = Math.min(3 + this.f_19796_.m_188503_(2), this.getSegmentCount());
            int segments = this.getSegmentCount();
            int i = 0;
            while (nextPart instanceof EntityVoidWormPart) {
                EntityVoidWormPart part = (EntityVoidWormPart)((EntityVoidWormPart)nextPart).getChild();
                float scale = 1.0f + (float)(++i) / (float)segments * 0.5f;
                boolean tail = i >= segments - tailstart;
                part.setTail(tail);
                part.setWormScale(scale);
                part.radius = 1.0f + scale * (tail ? 0.65f : 0.3f) + (i == 0 ? 0.8f : 0.0f);
                part.offsetY = i == 0 ? -0.0f : (i == segments - tailstart ? -0.3f : 0.0f);
                nextPart = part;
            }
        }
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setSegmentCount(25 + this.f_19796_.m_188503_(15));
        this.m_146926_(0.0f);
        this.setBaseMaxHealth(AMConfig.voidWormMaxHealth, true);
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(SPLIT_FROM_UUID, Optional.empty());
        this.f_19804_.m_135372_(CHILD_UUID, Optional.empty());
        this.f_19804_.m_135372_(SEGMENT_COUNT, (Object)10);
        this.f_19804_.m_135372_(JAW_TICKS, (Object)0);
        this.f_19804_.m_135372_(WORM_ANGLE, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(SPEEDMOD, (Object)Float.valueOf(1.0f));
        this.f_19804_.m_135372_(SPLITTER, (Object)false);
        this.f_19804_.m_135372_(PORTAL_TICKS, (Object)0);
    }

    public float getWormAngle() {
        return ((Float)this.f_19804_.m_135370_(WORM_ANGLE)).floatValue();
    }

    public void setWormAngle(float progress) {
        this.f_19804_.m_135381_(WORM_ANGLE, (Object)Float.valueOf(progress));
    }

    public float getWormSpeed() {
        return ((Float)this.f_19804_.m_135370_(SPEEDMOD)).floatValue();
    }

    public void setWormSpeed(float progress) {
        if (this.getWormSpeed() != progress) {
            this.f_21342_ = new FlightMoveController((Mob)this, progress, false, true);
        }
        this.f_19804_.m_135381_(SPEEDMOD, (Object)Float.valueOf(progress));
    }

    public boolean isSplitter() {
        return (Boolean)this.f_19804_.m_135370_(SPLITTER);
    }

    public void setSplitter(boolean splitter) {
        this.f_19804_.m_135381_(SPLITTER, (Object)splitter);
    }

    public void openMouth(int time) {
        this.f_19804_.m_135381_(JAW_TICKS, (Object)time);
    }

    public boolean isMouthOpen() {
        return (float)((Integer)this.f_19804_.m_135370_(JAW_TICKS)).intValue() >= 5.0f;
    }

    @Nullable
    public UUID getChildId() {
        return ((Optional)this.f_19804_.m_135370_(CHILD_UUID)).orElse(null);
    }

    public void setChildId(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(CHILD_UUID, Optional.ofNullable(uniqueId));
    }

    @Nullable
    public UUID getSplitFromUUID() {
        return ((Optional)this.f_19804_.m_135370_(SPLIT_FROM_UUID)).orElse(null);
    }

    public void setSplitFromUuid(@Nullable UUID uniqueId) {
        this.f_19804_.m_135381_(SPLIT_FROM_UUID, Optional.ofNullable(uniqueId));
    }

    public int getPortalTicks() {
        return (Integer)this.f_19804_.m_135370_(PORTAL_TICKS);
    }

    public void setPortalTicks(int ticks) {
        this.f_19804_.m_135381_(PORTAL_TICKS, (Object)ticks);
    }

    public int getSegmentCount() {
        return (Integer)this.f_19804_.m_135370_(SEGMENT_COUNT);
    }

    public void setSegmentCount(int command) {
        this.f_19804_.m_135381_(SEGMENT_COUNT, (Object)command);
    }

    public void m_6138_() {
        List entities = this.m_9236_().m_45933_((Entity)this, this.m_20191_().m_82363_((double)0.2f, 0.0, (double)0.2f));
        entities.stream().filter(entity -> !(entity instanceof EntityVoidWormPart) && entity.m_6094_()).forEach(entity -> entity.m_7334_((Entity)this));
    }

    public void m_7334_(Entity entityIn) {
    }

    public void createStuckPortal() {
        if (this.m_5448_() != null) {
            this.createPortal(this.m_5448_().m_20182_().m_82520_((double)(this.f_19796_.m_188503_(8) - 4), (double)(2 + this.f_19796_.m_188503_(3)), (double)(this.f_19796_.m_188503_(8) - 4)));
        } else {
            Vec3 vec = Vec3.m_82512_((Vec3i)this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING, this.m_20183_().m_6630_(this.f_19796_.m_188503_(10) + 10)));
            this.createPortal(vec);
        }
    }

    public void createPortal(Vec3 to) {
        this.createPortal(this.m_20182_().m_82549_(this.m_20154_().m_82490_(20.0)), to, null);
    }

    public void createPortalRandomDestination() {
        Vec3 vec = null;
        for (int i = 0; i < 15; ++i) {
            BlockPos pos = AMBlockPos.fromCoords(this.m_20185_() + (double)this.f_19796_.m_188503_(60) - 30.0, 0.0, this.m_20189_() + (double)this.f_19796_.m_188503_(60) - 30.0);
            BlockPos height = this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING, pos);
            height = height.m_123342_() < 10 ? height.m_6630_(50 + this.f_19796_.m_188503_(50)) : height.m_6630_(this.f_19796_.m_188503_(30));
            if (!this.m_9236_().m_46859_(height)) continue;
            vec = Vec3.m_82539_((Vec3i)height);
        }
        if (vec != null) {
            this.createPortal(this.m_20182_().m_82549_(this.m_20154_().m_82490_(20.0)), vec, null);
        }
    }

    public void createPortal(Vec3 from, Vec3 to, @Nullable Direction outDir) {
        if (!this.m_9236_().f_46443_ && this.portalTarget == null) {
            Vec3 vec;
            Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
            BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(Vector3d, from, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
            Vec3 vec3 = vec = result.m_82450_() != null ? result.m_82450_() : this.m_20182_();
            if (result instanceof BlockHitResult) {
                BlockHitResult result1 = result;
                vec = vec.m_82549_(Vec3.m_82528_((Vec3i)result1.m_82434_().m_122436_()));
            }
            EntityVoidPortal portal = (EntityVoidPortal)((EntityType)AMEntityRegistry.VOID_PORTAL.get()).m_20615_(this.m_9236_());
            portal.m_6034_(vec.f_82479_, vec.f_82480_, vec.f_82481_);
            Vec3 dirVec = vec.m_82546_(this.m_20182_());
            Direction dir = Direction.m_122366_((double)dirVec.f_82479_, (double)dirVec.f_82480_, (double)dirVec.f_82481_);
            portal.setAttachmentFacing(dir);
            portal.setLifespan(10000);
            if (!this.m_9236_().f_46443_) {
                this.m_9236_().m_7967_((Entity)portal);
            }
            this.portalTarget = portal;
            portal.setDestination(AMBlockPos.fromCoords(to.f_82479_, to.f_82480_, to.f_82481_), outDir);
            this.makePortalCooldown = 300;
        }
    }

    public void resetPortalLogic() {
        this.portalTarget = null;
        this.stillTicks = 0;
    }

    public boolean m_6094_() {
        return false;
    }

    public void breakBlock() {
        if (this.blockBreakCounter > 0) {
            --this.blockBreakCounter;
            return;
        }
        boolean flag = false;
        if (!this.m_9236_().f_46443_ && this.blockBreakCounter == 0 && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
            for (int a = (int)Math.round(this.m_20191_().f_82288_); a <= (int)Math.round(this.m_20191_().f_82291_); ++a) {
                for (int b = (int)Math.round(this.m_20191_().f_82289_) - 1; b <= (int)Math.round(this.m_20191_().f_82292_) + 1 && b <= 127; ++b) {
                    for (int c = (int)Math.round(this.m_20191_().f_82290_); c <= (int)Math.round(this.m_20191_().f_82293_); ++c) {
                        BlockPos pos = new BlockPos(a, b, c);
                        BlockState state = this.m_9236_().m_8055_(pos);
                        FluidState fluidState = this.m_9236_().m_6425_(pos);
                        Block block = state.m_60734_();
                        if (state.m_60795_() || state.m_60808_((BlockGetter)this.m_9236_(), pos).m_83281_() || !state.m_204336_(AMTagRegistry.VOID_WORM_BREAKABLES) || !fluidState.m_76178_() || block == Blocks.f_50016_) continue;
                        this.m_20256_(this.m_20184_().m_82542_((double)0.6f, 1.0, (double)0.6f));
                        flag = true;
                        this.m_9236_().m_46961_(pos, true);
                        if (!state.m_204336_(BlockTags.f_13047_)) continue;
                        this.m_9236_().m_46597_(pos, Blocks.f_49990_.m_49966_());
                    }
                }
            }
        }
        if (flag) {
            this.blockBreakCounter = 10;
        }
    }

    public boolean isTargetBlocked(Vec3 target) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        return this.m_9236_().m_45547_(new ClipContext(Vector3d, target, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this)).m_6662_() != HitResult.Type.MISS;
    }

    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
        float radius = (-9.45f - (float)this.m_217043_().m_188503_(24)) * radiusAdd;
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, 0.0, fleePos.m_7094_() + extraZ);
        BlockPos ground = this.getGround(radialPos);
        int distFromGround = (int)this.m_20186_() - ground.m_123342_();
        int flightHeight = 10 + this.m_217043_().m_188503_(20);
        BlockPos newPos = ground.m_6630_(distFromGround > 8 ? flightHeight : this.m_217043_().m_188503_(10) + 15);
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 1.0) {
            return Vec3.m_82512_((Vec3i)newPos);
        }
        return null;
    }

    public Vec3 getBlockInViewAwaySlam(Vec3 fleePos, int slamHeight) {
        float radius = 3 + this.f_19796_.m_188503_(3);
        float neg = this.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, 0.0, fleePos.m_7094_() + extraZ);
        BlockPos ground = this.getHeighestAirAbove(radialPos, slamHeight);
        if (!this.isTargetBlocked(Vec3.m_82512_((Vec3i)ground)) && this.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 1.0) {
            return Vec3.m_82512_((Vec3i)ground);
        }
        return null;
    }

    private BlockPos getHeighestAirAbove(BlockPos radialPos, int limit) {
        BlockPos position = AMBlockPos.fromCoords(radialPos.m_123341_(), this.m_20186_(), radialPos.m_123343_());
        while (position.m_123342_() < 256 && (double)position.m_123342_() < this.m_20186_() + (double)limit && this.m_9236_().m_46859_(position)) {
            position = position.m_7494_();
        }
        return position;
    }

    private BlockPos getGround(BlockPos in) {
        BlockPos position = AMBlockPos.fromCoords(in.m_123341_(), this.m_20186_(), in.m_123343_());
        while (position.m_123342_() > -63 && !this.m_9236_().m_8055_(position).m_280296_()) {
            position = position.m_7495_();
        }
        if (position.m_123342_() < -62) {
            return position.m_6630_(120 + this.f_19796_.m_188503_(5));
        }
        return position;
    }

    public boolean m_7307_(Entity entityIn) {
        return super.m_7307_(entityIn) || this.getSplitFromUUID() != null && this.getSplitFromUUID().equals(entityIn.m_20148_()) || entityIn instanceof EntityVoidWorm && ((EntityVoidWorm)entityIn).getSplitFromUUID() != null && ((EntityVoidWorm)entityIn).getSplitFromUUID().equals(entityIn.m_20148_());
    }

    private void spit(Vec3 shotAt, boolean portal) {
        shotAt = shotAt.m_82524_(-this.m_146908_() * ((float)Math.PI / 180));
        EntityVoidWormShot shot = new EntityVoidWormShot(this.m_9236_(), this);
        double d0 = shotAt.f_82479_;
        double d1 = shotAt.f_82480_;
        double d2 = shotAt.f_82481_;
        float f = Mth.m_14116_((float)((float)(d0 * d0 + d2 * d2))) * 0.35f;
        shot.shoot(d0, d1 + (double)f, d2, 0.5f, 3.0f);
        if (!this.m_20067_()) {
            this.m_146850_(GameEvent.f_157778_);
            this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_11821_, this.m_5720_(), 1.0f, 1.0f + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f);
        }
        this.openMouth(5);
        this.m_9236_().m_7967_((Entity)shot);
    }

    private boolean wormAttack(Entity entity, DamageSource source, float dmg) {
        dmg = (float)((double)dmg * AMConfig.voidWormDamageModifier);
        return entity instanceof EnderDragon ? ((EnderDragon)entity).m_31161_(source, dmg * 0.5f) : entity.m_6469_(source, dmg);
    }

    public void playHurtSoundWorm(DamageSource source) {
        this.m_6677_(source);
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 67) {
            AlexsMobs.PROXY.onEntityStatus((Entity)this, id);
        } else {
            super.m_7822_(id);
        }
    }

    public class AIEnterPortal
    extends Goal {
        public AIEnterPortal() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return EntityVoidWorm.this.portalTarget != null;
        }

        public void m_8037_() {
            if (EntityVoidWorm.this.portalTarget != null) {
                double d2;
                double d1;
                EntityVoidWorm.this.f_19794_ = true;
                double centerX = EntityVoidWorm.this.portalTarget.m_20185_();
                double centerY = EntityVoidWorm.this.portalTarget.m_20227_(0.5);
                double centerZ = EntityVoidWorm.this.portalTarget.m_20189_();
                double d0 = centerX - EntityVoidWorm.this.m_20185_();
                Vec3 vec = new Vec3(d0, d1 = centerY - EntityVoidWorm.this.m_20227_(0.5), d2 = centerZ - EntityVoidWorm.this.m_20189_());
                if (vec.m_82553_() > 1.0) {
                    vec = vec.m_82541_();
                }
                vec = vec.m_82490_((double)0.4f);
                EntityVoidWorm.this.m_20256_(EntityVoidWorm.this.m_20184_().m_82549_(vec));
            }
        }

        public void m_8041_() {
            EntityVoidWorm.this.f_19794_ = false;
        }
    }

    public class AIAttack
    extends Goal {
        private AttackMode mode = AttackMode.CIRCLE;
        private int modeTicks = 0;
        private int maxCircleTime = 500;
        private Vec3 moveTo = null;

        public AIAttack() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean m_8036_() {
            return EntityVoidWorm.this.m_5448_() != null && EntityVoidWorm.this.m_5448_().m_6084_();
        }

        public void m_8041_() {
            this.mode = AttackMode.CIRCLE;
            this.modeTicks = 0;
        }

        public void m_8056_() {
            this.mode = AttackMode.CIRCLE;
            this.maxCircleTime = 60 + EntityVoidWorm.this.f_19796_.m_188503_(200);
        }

        public void m_8037_() {
            LivingEntity target = EntityVoidWorm.this.m_5448_();
            boolean flag = false;
            float speed = 1.0f;
            for (Entity entity : EntityVoidWorm.this.m_9236_().m_45976_(LivingEntity.class, EntityVoidWorm.this.m_20191_().m_82400_(2.0))) {
                if (entity.m_7306_((Entity)EntityVoidWorm.this) || entity instanceof EntityVoidWormPart || entity.m_7307_((Entity)EntityVoidWorm.this) || entity == EntityVoidWorm.this) continue;
                if (EntityVoidWorm.this.isMouthOpen()) {
                    EntityVoidWorm.this.launch(entity, true);
                    flag = true;
                    EntityVoidWorm.this.wormAttack(entity, EntityVoidWorm.this.m_269291_().m_269333_((LivingEntity)EntityVoidWorm.this), 8.0f + EntityVoidWorm.this.f_19796_.m_188501_() * 8.0f);
                    continue;
                }
                EntityVoidWorm.this.openMouth(15);
            }
            if (target != null) {
                if (this.mode == AttackMode.CIRCLE) {
                    int interval;
                    if (this.moveTo == null || EntityVoidWorm.this.m_20238_(this.moveTo) < 16.0 || EntityVoidWorm.this.f_19862_) {
                        this.moveTo = EntityVoidWorm.this.getBlockInViewAway(target.m_20182_(), 0.4f + EntityVoidWorm.this.f_19796_.m_188501_() * 0.2f);
                    }
                    int n = interval = EntityVoidWorm.this.m_21223_() < EntityVoidWorm.this.m_21233_() && !EntityVoidWorm.this.isSplitter() ? 15 : 40;
                    if (this.modeTicks % interval == 0) {
                        EntityVoidWorm.this.spit(new Vec3(3.0, 3.0, 0.0), false);
                        EntityVoidWorm.this.spit(new Vec3(-3.0, 3.0, 0.0), false);
                        EntityVoidWorm.this.spit(new Vec3(3.0, -3.0, 0.0), false);
                        EntityVoidWorm.this.spit(new Vec3(-3.0, -3.0, 0.0), false);
                    }
                    ++this.modeTicks;
                    if (this.modeTicks > this.maxCircleTime) {
                        this.maxCircleTime = 60 + EntityVoidWorm.this.f_19796_.m_188503_(200);
                        this.mode = AttackMode.SLAM_RISE;
                        this.modeTicks = 0;
                        this.moveTo = null;
                    }
                } else if (this.mode == AttackMode.SLAM_RISE) {
                    if (this.moveTo == null) {
                        this.moveTo = EntityVoidWorm.this.getBlockInViewAwaySlam(target.m_20182_(), 20 + EntityVoidWorm.this.f_19796_.m_188503_(20));
                    }
                    if (this.moveTo != null && EntityVoidWorm.this.m_20186_() > target.m_20186_() + 15.0) {
                        this.moveTo = null;
                        this.modeTicks = 0;
                        this.mode = AttackMode.SLAM_FALL;
                    }
                } else if (this.mode == AttackMode.SLAM_FALL) {
                    speed = 2.0f;
                    EntityVoidWorm.this.m_21391_((Entity)target, 360.0f, 360.0f);
                    this.moveTo = target.m_20182_();
                    if (EntityVoidWorm.this.f_19862_) {
                        this.moveTo = new Vec3(target.m_20185_(), EntityVoidWorm.this.m_20186_() + 3.0, target.m_20189_());
                    }
                    EntityVoidWorm.this.openMouth(20);
                    if (EntityVoidWorm.this.m_20238_(this.moveTo) < 4.0 || flag) {
                        this.mode = AttackMode.CIRCLE;
                        this.moveTo = null;
                        this.modeTicks = 0;
                    }
                }
            }
            if (!EntityVoidWorm.this.m_142582_((Entity)target) && EntityVoidWorm.this.f_19796_.m_188503_(100) == 0 && EntityVoidWorm.this.makePortalCooldown == 0) {
                Vec3 to = new Vec3(target.m_20185_(), target.m_20191_().f_82292_ + 0.1, target.m_20189_());
                EntityVoidWorm.this.createPortal(EntityVoidWorm.this.m_20182_().m_82549_(EntityVoidWorm.this.m_20154_().m_82490_(20.0)), to, Direction.UP);
                EntityVoidWorm.this.makePortalCooldown = 50;
                this.mode = AttackMode.SLAM_FALL;
            }
            if (this.moveTo != null && EntityVoidWorm.this.portalTarget == null) {
                EntityVoidWorm.this.m_21566_().m_6849_(this.moveTo.f_82479_, this.moveTo.f_82480_, this.moveTo.f_82481_, (double)speed);
            }
        }
    }

    private class AIFlyIdle
    extends Goal {
        protected final EntityVoidWorm voidWorm;
        protected double x;
        protected double y;
        protected double z;

        public AIFlyIdle() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
            this.voidWorm = EntityVoidWorm.this;
        }

        public boolean m_8036_() {
            if (this.voidWorm.m_20160_() || this.voidWorm.portalTarget != null || this.voidWorm.m_5448_() != null && this.voidWorm.m_5448_().m_6084_() || this.voidWorm.m_20159_()) {
                return false;
            }
            Vec3 lvt_1_1_ = this.getPosition();
            if (lvt_1_1_ == null) {
                return false;
            }
            this.x = lvt_1_1_.f_82479_;
            this.y = lvt_1_1_.f_82480_;
            this.z = lvt_1_1_.f_82481_;
            return true;
        }

        public void m_8037_() {
            this.voidWorm.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vector3d = this.voidWorm.m_20182_();
            return this.voidWorm.getBlockInViewAway(vector3d, 1.0f);
        }

        public boolean m_8045_() {
            return this.voidWorm.m_20275_(this.x, this.y, this.z) > 20.0 && this.voidWorm.portalTarget == null && !this.voidWorm.f_19862_ && (this.voidWorm.m_5448_() == null || !this.voidWorm.m_5448_().m_6084_());
        }

        public void m_8056_() {
            this.voidWorm.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        }

        public void m_8041_() {
            this.voidWorm.m_21573_().m_26573_();
            super.m_8041_();
        }
    }

    private static enum AttackMode {
        CIRCLE,
        SLAM_RISE,
        SLAM_FALL,
        PORTAL;

    }
}

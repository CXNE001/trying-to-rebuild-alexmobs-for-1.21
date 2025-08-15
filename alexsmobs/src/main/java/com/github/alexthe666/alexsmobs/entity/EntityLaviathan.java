/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MoveFunction
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.vehicle.DismountHelper
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$TagValue
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.CollisionGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.common.ForgeMod
 *  net.minecraftforge.entity.PartEntity
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fluids.FluidType
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.EntityLaviathanPart;
import com.github.alexthe666.alexsmobs.entity.IHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFindWaterLava;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHerdPanic;
import com.github.alexthe666.alexsmobs.entity.ai.BoneSerpentPathNavigator;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.LaviathanAIRandomSwimming;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.collect.Sets;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidType;

public class EntityLaviathan
extends Animal
implements ISemiAquatic,
IHerdPanic {
    private static final EntityDataAccessor<Boolean> OBSIDIAN = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> HEAD_HEIGHT = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> HEAD_YROT = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> CHILL_TIME = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> HAS_BODY_GEAR = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> HAS_HEAD_GEAR = SynchedEntityData.m_135353_(EntityLaviathan.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final Predicate<EntityCrimsonMosquito> HEALTHY_MOSQUITOES = mob -> mob.m_6084_() && mob.m_21223_() > 0.0f && !mob.isSick();
    public static final ResourceLocation OBSIDIAN_LOOT = new ResourceLocation("alexsmobs", "entities/laviathan_obsidian");
    public final EntityLaviathanPart headPart;
    public final EntityLaviathanPart neckPart1;
    public final EntityLaviathanPart neckPart2;
    public final EntityLaviathanPart neckPart3;
    public final EntityLaviathanPart neckPart4;
    public final EntityLaviathanPart neckPart5;
    public final EntityLaviathanPart seat1;
    public final EntityLaviathanPart seat2;
    public final EntityLaviathanPart seat3;
    public final EntityLaviathanPart seat4;
    public final EntityLaviathanPart[] theEntireNeck;
    public final EntityLaviathanPart[] allParts;
    public final EntityLaviathanPart[] seatParts;
    private final UUID[] riderPositionMap = new UUID[4];
    public float prevHeadHeight = 0.0f;
    public float swimProgress = 0.0f;
    public float prevSwimProgress = 0.0f;
    public float biteProgress;
    public float prevBiteProgress;
    public int revengeCooldown = 0;
    private boolean isLandNavigator;
    private int conversionTime = 0;
    private int dismountCooldown = 0;
    private int headPeakCooldown = 0;
    private boolean hasObsidianArmor;
    private int blockBreakCounter;
    private double lastX = 0.0;
    private double lastZ = 0.0;

    protected EntityLaviathan(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.m_274367_(1.3f);
        this.m_21441_(BlockPathTypes.WATER, 0.0f);
        this.m_21441_(BlockPathTypes.WATER_BORDER, 0.0f);
        this.m_21441_(BlockPathTypes.LAVA, 0.0f);
        this.m_21441_(BlockPathTypes.DANGER_FIRE, 0.0f);
        this.m_21441_(BlockPathTypes.DAMAGE_FIRE, 0.0f);
        this.headPart = new EntityLaviathanPart(this, 1.2f, 0.9f);
        this.neckPart1 = new EntityLaviathanPart(this, 0.9f, 0.9f);
        this.neckPart2 = new EntityLaviathanPart(this, 0.9f, 0.9f);
        this.neckPart3 = new EntityLaviathanPart(this, 0.9f, 0.9f);
        this.neckPart4 = new EntityLaviathanPart(this, 0.9f, 0.9f);
        this.neckPart5 = new EntityLaviathanPart(this, 0.9f, 0.9f);
        this.seat1 = new EntityLaviathanPart(this, 0.9f, 0.4f);
        this.seat2 = new EntityLaviathanPart(this, 0.9f, 0.4f);
        this.seat3 = new EntityLaviathanPart(this, 0.9f, 0.4f);
        this.seat4 = new EntityLaviathanPart(this, 0.9f, 0.4f);
        this.theEntireNeck = new EntityLaviathanPart[]{this.neckPart1, this.neckPart2, this.neckPart3, this.neckPart4, this.neckPart5, this.headPart};
        this.allParts = new EntityLaviathanPart[]{this.neckPart1, this.neckPart2, this.neckPart3, this.neckPart4, this.neckPart5, this.headPart, this.seat1, this.seat2, this.seat3, this.seat4};
        this.seatParts = new EntityLaviathanPart[]{this.seat1, this.seat2, this.seat3, this.seat4};
        this.switchNavigator(true);
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.laviathanSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public static boolean canLaviathanSpawn(EntityType<EntityLaviathan> p_234314_0_, LevelAccessor p_234314_1_, MobSpawnType p_234314_2_, BlockPos p_234314_3_, RandomSource p_234314_4_) {
        BlockPos.MutableBlockPos blockpos$mutable = p_234314_3_.m_122032_();
        do {
            blockpos$mutable.m_122173_(Direction.UP);
        } while (p_234314_1_.m_6425_((BlockPos)blockpos$mutable).m_205070_(FluidTags.f_13132_));
        return p_234314_1_.m_8055_((BlockPos)blockpos$mutable).m_60795_();
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.LAVIATHAN_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.LAVIATHAN_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.LAVIATHAN_HURT.get();
    }

    @Nullable
    protected ResourceLocation m_7582_() {
        return this.isObsidian() ? OBSIDIAN_LOOT : super.m_7582_();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 60.0).m_22268_(Attributes.f_22281_, 1.0).m_22268_(Attributes.f_22284_, 10.0).m_22268_(Attributes.f_22279_, (double)0.3f).m_22268_(Attributes.f_22278_, 1.0);
    }

    public boolean m_5829_() {
        return this.m_6084_();
    }

    protected boolean m_7310_(Entity p_38390_) {
        return this.m_20197_().size() < 4 && !this.m_204029_(FluidTags.f_13132_) && !this.m_204029_(FluidTags.f_13131_);
    }

    public void m_7334_(Entity entity) {
        if (!entity.m_20365_((Entity)this)) {
            entity.m_20256_(entity.m_20184_().m_82549_(this.m_20184_()));
        }
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.LAVIATHAN_BREEDABLES);
    }

    public boolean m_6094_() {
        return false;
    }

    public float m_6143_() {
        return 0.0f;
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        Item item = itemstack.m_41720_();
        if (itemstack.m_204117_(AMTagRegistry.LAVIATHAN_FOODSTUFFS) && this.m_21223_() < this.m_21233_()) {
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
            this.m_5634_(10.0f);
            return InteractionResult.SUCCESS;
        }
        if (item == AMItemRegistry.STRADDLE_HELMET.get() && !this.hasHeadGear() && !this.m_6162_()) {
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
            this.setHeadGear(true);
            return InteractionResult.SUCCESS;
        }
        if (item == AMItemRegistry.STRADDLE_SADDLE.get() && !this.hasBodyGear() && !this.m_6162_()) {
            if (!player.m_7500_()) {
                itemstack.m_41774_(1);
            }
            this.setBodyGear(true);
            return InteractionResult.SUCCESS;
        }
        InteractionResult type = super.m_6071_(player, hand);
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && !this.m_6898_(itemstack) && this.hasBodyGear() && !this.m_6162_()) {
            if (!player.m_6144_()) {
                if (!this.m_9236_().f_46443_) {
                    player.m_20329_((Entity)this);
                }
            } else {
                this.m_20153_();
            }
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public int getClosestOpenSeat(Vec3 entityPos) {
        int closest = -1;
        double closestDistance = Double.MAX_VALUE;
        for (int i = 0; i < this.seatParts.length; ++i) {
            double dist = entityPos.m_82554_(this.seatParts[i].m_20182_());
            if (closest != -1 && !(closestDistance > dist) || this.riderPositionMap[i] != null) continue;
            closest = i;
            closestDistance = dist;
        }
        return closest;
    }

    @Nullable
    public LivingEntity m_6688_() {
        int playerPosition = -1;
        Player player = null;
        if (this.hasHeadGear() && this.hasBodyGear()) {
            for (Entity passenger : this.m_20197_()) {
                if (!(passenger instanceof Player)) continue;
                Player player2 = (Player)passenger;
                int player2Position = this.getRiderPosition(passenger);
                if (player != null && playerPosition <= player2Position) continue;
                player = player2;
                playerPosition = player2Position;
            }
        }
        return player;
    }

    public int getSeatRaytrace(Entity player) {
        HitResult result = player.m_19907_((double)player.m_20270_((Entity)this), 0.0f, false);
        if (result != null) {
            Vec3 vec = result.m_82450_();
            return this.getClosestOpenSeat(vec);
        }
        return -1;
    }

    public void m_20351_(Entity entity) {
        super.m_20351_(entity);
        this.dismountCooldown = 40 + this.f_19796_.m_188503_(40);
        if (entity != null && entity.m_20148_() != null) {
            for (int i = 0; i < this.riderPositionMap.length; ++i) {
                if (this.riderPositionMap[i] == null || !this.riderPositionMap[i].equals(entity.m_20148_())) continue;
                this.riderPositionMap[i] = null;
            }
        }
    }

    public int getRiderPosition(Entity passenger) {
        int posit = -1;
        for (int i = 0; i < this.riderPositionMap.length; ++i) {
            if (this.riderPositionMap[i] == null || passenger == null || !passenger.m_20148_().equals(this.riderPositionMap[i])) continue;
            posit = i;
        }
        return posit;
    }

    protected void m_20348_(Entity entity) {
        int rayTrace = this.getSeatRaytrace(entity);
        if (rayTrace >= 0 && rayTrace < 4) {
            if (this.riderPositionMap[rayTrace] != null && !this.m_9236_().f_46443_ && this.m_9236_() instanceof ServerLevel) {
                Entity kickOff = ((ServerLevel)this.m_9236_()).m_8791_(this.riderPositionMap[rayTrace]);
                this.riderPositionMap[rayTrace] = null;
                if (kickOff != null) {
                    kickOff.m_8127_();
                }
            }
            this.riderPositionMap[rayTrace] = entity.m_20148_();
            super.m_20348_(entity);
        }
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Obsidian", this.isObsidian());
        compound.m_128379_("HeadGear", this.hasHeadGear());
        compound.m_128379_("BodyGear", this.hasBodyGear());
        compound.m_128405_("ChillTime", this.getChillTime());
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setObsidian(compound.m_128471_("Obsidian"));
        this.setHeadGear(compound.m_128471_("HeadGear"));
        this.setBodyGear(compound.m_128471_("BodyGear"));
        this.setChillTime(compound.m_128451_("ChillTime"));
    }

    public void m_19956_(Entity passenger, Entity.MoveFunction moveFunc) {
        if (this.m_20363_(passenger)) {
            int posit = this.getRiderPosition(passenger);
            if (posit < 0 || posit > 3) {
                passenger.m_8127_();
            } else {
                EntityLaviathanPart seat = this.seatParts[posit];
                passenger.m_6034_(seat.m_20185_(), this.m_20186_() + this.m_6048_() + passenger.m_6049_(), seat.m_20189_());
            }
        }
    }

    public double m_6048_() {
        float f = this.f_267362_.m_267756_();
        float f1 = this.f_267362_.m_267731_();
        float f2 = 0.0f;
        return (double)this.m_20206_() - (double)0.4f;
    }

    protected void m_5907_() {
        super.m_5907_();
        if (this.hasBodyGear() && !this.m_9236_().f_46443_) {
            this.m_19998_((ItemLike)AMItemRegistry.STRADDLE_SADDLE.get());
        }
        if (this.hasHeadGear() && !this.m_9236_().f_46443_) {
            this.m_19998_((ItemLike)AMItemRegistry.STRADDLE_HELMET.get());
        }
        this.setBodyGear(false);
        this.setHeadGear(false);
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.f_21342_ = new MoveControl((Mob)this);
            this.f_21344_ = this.m_6037_(this.m_9236_());
            this.isLandNavigator = true;
        } else {
            this.f_21342_ = new MoveController(this);
            this.f_21344_ = new BoneSerpentPathNavigator((Mob)this, this.m_9236_());
            this.isLandNavigator = false;
        }
    }

    protected PathNavigation m_6037_(Level level) {
        return new GroundPathNavigatorWide((Mob)this, level);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new AnimalAIHerdPanic((PathfinderMob)this, 1.0){

            @Override
            public boolean m_8036_() {
                return super.m_8036_() && !EntityLaviathan.this.hasHeadGear();
            }
        });
        this.f_21345_.m_25352_(1, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new TemptGoal((PathfinderMob)this, 1.1, Ingredient.m_43938_(Stream.of(new Ingredient.TagValue(AMTagRegistry.LAVIATHAN_BREEDABLES), new Ingredient.TagValue(AMTagRegistry.LAVIATHAN_FOODSTUFFS))), false));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAIFindWaterLava((PathfinderMob)this, 1.0));
        this.f_21345_.m_25352_(5, (Goal)new LaviathanAIRandomSwimming((PathfinderMob)this, 1.0, 22){

            @Override
            public boolean m_8036_() {
                return super.m_8036_() && !EntityLaviathan.this.hasHeadGear() && !EntityLaviathan.this.hasBodyGear();
            }
        });
        this.f_21345_.m_25352_(6, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21345_.m_25352_(7, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
    }

    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return true;
    }

    protected float m_6041_() {
        return this.shouldSwim() || this.m_6046_() ? 1.0f : super.m_6041_();
    }

    public float m_5610_(BlockPos pos, LevelReader worldIn) {
        if (worldIn.m_8055_(pos).m_60819_().m_205070_(FluidTags.f_13131_) || worldIn.m_8055_(pos).m_60819_().m_205070_(FluidTags.f_13132_)) {
            return 10.0f;
        }
        return this.m_20077_() ? -1.0f : 0.0f;
    }

    public int m_6056_() {
        return 256;
    }

    public int m_5792_() {
        return 1;
    }

    public boolean m_7296_(int sizeIn) {
        return false;
    }

    protected Vec3 m_274312_(Player player, Vec3 deltaIn) {
        if (player.f_20902_ != 0.0f) {
            float half;
            float f = player.f_20902_ < 0.0f ? 0.5f : 1.0f;
            Vec3 lookVec = player.m_20154_();
            float y = (float)lookVec.f_82480_ * 0.1f;
            float waterAt = (float)this.getMaxFluidHeight();
            if (waterAt > (half = this.m_20206_() * 0.5f)) {
                y = Mth.m_14036_((float)(waterAt - half), (float)0.0f, (float)0.15f);
            } else if (waterAt < half) {
                y = Mth.m_14036_((float)(waterAt - half), (float)-0.15f, (float)0.0f);
            }
            if (this.f_19862_) {
                y += 0.4f;
            }
            Vec3 vec3 = new Vec3((double)(player.f_20900_ * 0.25f), (double)y, (double)(player.f_20902_ * (this.shouldSwim() ? 0.75f : 0.25f) * f));
            this.m_6858_(true);
            return vec3;
        }
        this.m_6858_(false);
        return Vec3.f_82478_;
    }

    protected void m_274498_(Player player, Vec3 vec3) {
        super.m_274498_(player, vec3);
        this.m_19915_(player.m_146908_(), player.m_146909_() * 0.5f);
        this.m_5616_(player.m_6080_());
        this.m_274367_(1.3f);
        this.m_6710_(null);
    }

    protected float m_245547_(Player rider) {
        return (float)this.m_21133_(Attributes.f_22279_);
    }

    public double getFluidMotionScale(FluidType type) {
        return type == ForgeMod.WATER_TYPE.get() || type == ForgeMod.LAVA_TYPE.get() ? 1.0 : super.getFluidMotionScale(type);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev && source.m_7639_() != null) {
            int fleeTime;
            this.revengeCooldown = fleeTime = 100 + this.m_217043_().m_188503_(150);
            this.setChillTime(0);
        }
        return prev;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(OBSIDIAN, (Object)false);
        this.f_19804_.m_135372_(HAS_BODY_GEAR, (Object)false);
        this.f_19804_.m_135372_(HAS_HEAD_GEAR, (Object)false);
        this.f_19804_.m_135372_(HEAD_HEIGHT, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(HEAD_YROT, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(CHILL_TIME, (Object)0);
        this.f_19804_.m_135372_(ATTACK_TICK, (Object)0);
    }

    public void m_7023_(Vec3 travelVector) {
        boolean liquid;
        boolean bl = liquid = this.m_20077_() || this.m_20069_();
        if (this.m_6109_() && liquid) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82490_(this.m_20160_() ? 0.5 : 0.9));
            this.m_267651_(false);
            if (!this.m_20160_() && !this.isChilling()) {
                this.m_20256_(this.m_20184_().m_82520_(0.0, (double)-0.01f, 0.0));
            }
        } else {
            super.m_7023_(travelVector);
        }
    }

    public void m_267651_(boolean flying) {
        float f1 = (float)Mth.m_184648_((double)(this.m_20185_() - this.lastX), (double)0.0, (double)(this.m_20189_() - this.lastZ));
        float walkSpeed = 4.0f;
        float f2 = Math.min(f1 * walkSpeed, 1.0f);
        this.f_267362_.m_267566_(f2, 0.4f);
    }

    public int m_8132_() {
        return 50;
    }

    public int m_8085_() {
        return 50;
    }

    public int m_21529_() {
        return 4;
    }

    public boolean m_6040_() {
        return true;
    }

    public boolean m_6063_() {
        return false;
    }

    public MobType m_6336_() {
        return MobType.f_21644_;
    }

    public void m_8119_() {
        List list;
        super.m_8119_();
        this.f_20883_ = Mth.m_14148_((float)this.f_20884_, (float)this.f_20883_, (float)this.m_21529_());
        this.prevSwimProgress = this.swimProgress;
        this.prevBiteProgress = this.biteProgress;
        this.prevHeadHeight = this.getHeadHeight();
        if (this.shouldSwim()) {
            if (this.swimProgress < 5.0f) {
                this.swimProgress += 1.0f;
            }
        } else if (this.swimProgress > 0.0f) {
            this.swimProgress -= 1.0f;
        }
        if (this.isObsidian()) {
            if (!this.hasObsidianArmor) {
                this.hasObsidianArmor = true;
                this.m_21051_(Attributes.f_22284_).m_22100_(30.0);
            }
        } else if (this.hasObsidianArmor) {
            this.hasObsidianArmor = false;
            this.m_21051_(Attributes.f_22284_).m_22100_(10.0);
        }
        if (!this.m_9236_().f_46443_) {
            if (!this.isObsidian() && this.m_20072_()) {
                if (this.conversionTime < 300) {
                    ++this.conversionTime;
                } else {
                    this.setObsidian(true);
                }
            }
            if (this.shouldSwim()) {
                this.f_19789_ = 0.0f;
            }
        }
        float neckBase = 0.8f;
        if (!this.m_21525_()) {
            int l;
            Vec3[] avector3d = new Vec3[this.allParts.length];
            for (int j = 0; j < this.allParts.length; ++j) {
                this.allParts[j].collideWithNearbyEntities();
                avector3d[j] = new Vec3(this.allParts[j].m_20185_(), this.allParts[j].m_20186_(), this.allParts[j].m_20189_());
            }
            float yaw = this.f_20883_ * ((float)Math.PI / 180);
            float neckContraction = 2.0f * Math.abs(this.getHeadHeight() / 3.0f) + 0.5f * Math.abs(this.getHeadYaw(0.0f) / 50.0f);
            for (l = 0; l < this.theEntireNeck.length; ++l) {
                float f = (float)l / (float)this.theEntireNeck.length;
                float f1 = -(2.2f + (float)l - f * neckContraction);
                float f2 = Mth.m_14031_((float)(yaw + Maths.rad(f * this.getHeadYaw(0.0f)))) * (1.0f - Math.abs(this.m_146909_() / 90.0f));
                float f3 = Mth.m_14089_((float)(yaw + Maths.rad(f * this.getHeadYaw(0.0f)))) * (1.0f - Math.abs(this.m_146909_() / 90.0f));
                this.setPartPosition(this.theEntireNeck[l], f2 * f1, (double)neckBase + Math.sin((double)f * Math.PI * 0.5) * (double)(this.getHeadHeight() * 1.1f), -f3 * f1);
            }
            this.setPartPosition(this.seat1, this.getXForPart(yaw, 145.0f) * 0.75f, 2.0, this.getZForPart(yaw, 145.0f) * 0.75f);
            this.setPartPosition(this.seat2, this.getXForPart(yaw, -145.0f) * 0.75f, 2.0, this.getZForPart(yaw, -145.0f) * 0.75f);
            this.setPartPosition(this.seat3, this.getXForPart(yaw, 35.0f) * 0.95f, 2.0, this.getZForPart(yaw, 35.0f) * 0.95f);
            this.setPartPosition(this.seat4, this.getXForPart(yaw, -35.0f) * 0.95f, 2.0, this.getZForPart(yaw, -35.0f) * 0.95f);
            if (this.m_9236_().f_46443_ && this.isChilling()) {
                if (!this.m_6162_()) {
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.m_20185_() + (double)(this.getXForPart(yaw, 158.0f) * 1.75f), this.m_20227_(1.0), this.m_20189_() + (double)(this.getZForPart(yaw, 158.0f) * 1.75f), 0.0, this.f_19796_.m_188500_() / 5.0, 0.0);
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.m_20185_() + (double)(this.getXForPart(yaw, -166.0f) * 1.48f), this.m_20227_(1.0), this.m_20189_() + (double)(this.getZForPart(yaw, -166.0f) * 1.48f), 0.0, this.f_19796_.m_188500_() / 5.0, 0.0);
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.m_20185_() + (double)(this.getXForPart(yaw, 14.0f) * 1.78f), this.m_20227_(0.9), this.m_20189_() + (double)(this.getZForPart(yaw, 14.0f) * 1.78f), 0.0, this.f_19796_.m_188500_() / 5.0, 0.0);
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.m_20185_() + (double)(this.getXForPart(yaw, -14.0f) * 1.6f), this.m_20227_(1.1), this.m_20189_() + (double)(this.getZForPart(yaw, -14.0f) * 1.6f), 0.0, this.f_19796_.m_188500_() / 5.0, 0.0);
                }
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.headPart.m_20208_(0.6), this.headPart.m_20227_(0.9), this.headPart.m_20262_(0.6), 0.0, this.f_19796_.m_188500_() / 5.0, 0.0);
            }
            for (l = 0; l < this.allParts.length; ++l) {
                this.allParts[l].f_19854_ = avector3d[l].f_82479_;
                this.allParts[l].f_19855_ = avector3d[l].f_82480_;
                this.allParts[l].f_19856_ = avector3d[l].f_82481_;
                this.allParts[l].f_19790_ = avector3d[l].f_82479_;
                this.allParts[l].f_19791_ = avector3d[l].f_82480_;
                this.allParts[l].f_19792_ = avector3d[l].f_82481_;
            }
        }
        if ((this.m_20077_() || this.m_20072_()) && this.isLandNavigator) {
            this.switchNavigator(false);
        }
        if (!(this.m_20077_() || this.m_20072_() || this.isLandNavigator)) {
            this.switchNavigator(true);
        }
        if (!this.m_9236_().f_46443_) {
            if (this.getChillTime() > 0) {
                this.setChillTime(this.getChillTime() - 1);
            } else if (this.shouldSwim() && this.f_19796_.m_188503_(this.m_20160_() ? 200 : 2000) == 0 && this.revengeCooldown == 0) {
                this.setChillTime(100 + this.f_19796_.m_188503_(500));
            }
            if (this.revengeCooldown > 0) {
                --this.revengeCooldown;
            }
            if (this.headPeakCooldown > 0) {
                --this.headPeakCooldown;
            }
            if (this.revengeCooldown == 0 && this.m_21188_() != null) {
                this.m_6703_(null);
            }
        }
        if (!this.m_9236_().f_46443_) {
            if (this.m_6688_() == null && (this.getChillTime() > 0 || this.hasHeadGear() || this.dismountCooldown > 0)) {
                this.floatLaviathan();
            }
            if (!this.isChilling() && this.headPeakCooldown == 0) {
                float low = this.getLowHeadHeight();
                this.setHeadHeight(this.getHeadHeight() + (0.5f + (this.getLowHeadHeight() + this.getHighHeadHeight(low)) / 2.0f - this.getHeadHeight()) * 0.2f);
            } else if (this.getMaxFluidHeight() <= (double)(this.m_20206_() * 0.5f) && this.getMaxFluidHeight() >= (double)(this.m_20206_() * 0.25f)) {
                float mot = (float)this.m_20184_().m_82556_();
                this.setHeadHeight(Mth.m_14036_((float)(this.getHeadHeight() + 0.1f - 0.2f * mot), (float)0.0f, (float)2.0f));
                this.headPeakCooldown = 5;
            }
        }
        if (this.isChilling()) {
            boolean keepChillin = false;
            boolean startBiting = false;
            for (EntityCrimsonMosquito entity : this.m_9236_().m_6443_(EntityCrimsonMosquito.class, this.m_20191_().m_82400_(30.0), HEALTHY_MOSQUITOES)) {
                entity.setLuringLaviathan(this.m_19879_());
                keepChillin = true;
            }
            if (keepChillin) {
                this.setChillTime(Math.max(20, this.getChillTime()));
            }
            for (EntityCrimsonMosquito entity : this.m_9236_().m_6443_(EntityCrimsonMosquito.class, this.headPart.m_20191_().m_82400_(1.0), HEALTHY_MOSQUITOES)) {
                startBiting = true;
                if (this.biteProgress != 5.0f) continue;
                entity.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), 1000.0f);
                entity.setShrink(true);
                this.setChillTime(0);
            }
            if (startBiting && (Integer)this.f_19804_.m_135370_(ATTACK_TICK) <= 0 && this.biteProgress == 0.0f) {
                this.f_19804_.m_135381_(ATTACK_TICK, (Object)7);
            }
        }
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) > 0) {
            this.f_19804_.m_135381_(ATTACK_TICK, (Object)((Integer)this.f_19804_.m_135370_(ATTACK_TICK) - 1));
        }
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) > 0 && this.biteProgress < 5.0f) {
            this.biteProgress += 1.0f;
        }
        if ((Integer)this.f_19804_.m_135370_(ATTACK_TICK) <= 0 && this.biteProgress > 0.0f) {
            this.biteProgress -= 1.0f;
        }
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        if (this.hasBodyGear() && !(list = this.m_9236_().m_6249_((Entity)this, this.m_20191_().m_82377_((double)0.2f, (double)-0.01f, (double)0.2f), EntitySelector.m_20421_((Entity)this))).isEmpty()) {
            boolean flag2 = !this.m_9236_().f_46443_;
            for (int j = 0; j < list.size(); ++j) {
                Entity entity = (Entity)list.get(j);
                if (entity.m_20363_((Entity)this)) continue;
                if (flag2 && !(entity instanceof Player) && !entity.m_20159_() && entity.m_20205_() < this.m_20205_() && !(entity instanceof EntityLaviathan) && !(entity instanceof Enemy) && entity instanceof Mob && this.m_7310_(entity) && !(entity instanceof WaterAnimal)) {
                    entity.m_20329_((Entity)this);
                    continue;
                }
                this.m_7334_(entity);
            }
        }
        if (this.m_20160_() && !this.m_9236_().f_46443_ && this.f_19797_ % 40 == 0 && this.m_20197_().size() > 3) {
            for (Entity entity : this.m_20197_()) {
                if (!(entity instanceof ServerPlayer)) continue;
                AMAdvancementTriggerRegistry.LAVIATHAN_FOUR_PASSENGERS.trigger((ServerPlayer)entity);
            }
        }
        this.lastX = this.m_20185_();
        this.lastZ = this.m_20189_();
    }

    public void m_8024_() {
        super.m_8024_();
        this.breakBlock();
    }

    public void breakBlock() {
        if (this.blockBreakCounter > 0) {
            --this.blockBreakCounter;
            return;
        }
        boolean flag = false;
        if (!this.m_9236_().f_46443_ && this.m_20160_() && this.blockBreakCounter == 0 && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
            for (int a = (int)Math.round(this.m_20191_().f_82288_); a <= (int)Math.round(this.m_20191_().f_82291_); ++a) {
                for (int b = (int)Math.round(this.m_20191_().f_82289_) - 1; b <= (int)Math.round(this.m_20191_().f_82292_) + 1 && b <= 127; ++b) {
                    for (int c = (int)Math.round(this.m_20191_().f_82290_); c <= (int)Math.round(this.m_20191_().f_82293_); ++c) {
                        BlockPos pos = new BlockPos(a, b, c);
                        BlockState state = this.m_9236_().m_8055_(pos);
                        FluidState fluidState = this.m_9236_().m_6425_(pos);
                        Block block = state.m_60734_();
                        if (state.m_60795_() || state.m_60808_((BlockGetter)this.m_9236_(), pos).m_83281_() || !state.m_204336_(AMTagRegistry.LAVIATHAN_BREAKABLES) || !fluidState.m_76178_() || block == Blocks.f_50016_) continue;
                        this.m_20256_(this.m_20184_().m_82542_((double)0.6f, 1.0, (double)0.6f));
                        flag = true;
                        this.m_9236_().m_46961_(pos, true);
                    }
                }
            }
        }
        if (flag) {
            this.blockBreakCounter = 10;
        }
    }

    public float getLowHeadHeight() {
        float checkAt;
        for (checkAt = 0.0f; checkAt > -3.0f && !this.isHeadInWall((float)this.m_20186_() + checkAt) && !this.isHeadInLava((float)this.m_20186_() + checkAt); checkAt -= 0.2f) {
        }
        return checkAt;
    }

    public float getHighHeadHeight(float low) {
        float checkAt;
        for (checkAt = 3.0f; checkAt > 0.0f && (!this.isHeadInWall((float)this.m_20186_() + checkAt) || this.isHeadInLava((float)this.m_20186_() + checkAt)); checkAt -= 0.2f) {
        }
        return checkAt;
    }

    public boolean isHeadInWall(float offset) {
        if (this.f_19794_) {
            return false;
        }
        float f = 0.8f;
        Vec3 vec3 = new Vec3(this.headPart.m_20185_(), (double)offset, this.headPart.m_20189_());
        AABB axisalignedbb = AABB.m_165882_((Vec3)vec3, (double)f, (double)1.0E-6, (double)f);
        return this.m_9236_().m_45556_(axisalignedbb).filter(Predicate.not(BlockBehaviour.BlockStateBase::m_60795_)).anyMatch(p_185969_ -> {
            BlockPos blockpos = AMBlockPos.fromVec3(vec3);
            return p_185969_.m_60828_((BlockGetter)this.m_9236_(), blockpos) && Shapes.m_83157_((VoxelShape)p_185969_.m_60812_((BlockGetter)this.m_9236_(), blockpos).m_83216_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_), (VoxelShape)Shapes.m_83064_((AABB)axisalignedbb), (BooleanOp)BooleanOp.f_82689_);
        });
    }

    public boolean isHeadInLava(float offset) {
        if (this.f_19794_) {
            return false;
        }
        float f = 0.8f;
        BlockPos pos = AMBlockPos.fromCoords(this.headPart.m_20185_(), offset, this.headPart.m_20189_());
        return !this.m_9236_().m_6425_(pos).m_76178_();
    }

    private void floatLaviathan() {
        if (this.shouldSwim()) {
            if (this.getMaxFluidHeight() >= (double)this.m_20206_()) {
                this.m_20334_(this.m_20184_().f_82479_, 0.12f, this.m_20184_().f_82481_);
            } else if (this.getMaxFluidHeight() >= (double)(this.m_20206_() * 0.5f)) {
                this.m_20334_(this.m_20184_().f_82479_, 0.08f, this.m_20184_().f_82481_);
            } else {
                this.m_20334_(this.m_20184_().f_82479_, 0.0, this.m_20184_().f_82481_);
            }
        }
    }

    public Vec3 m_7688_(LivingEntity livingEntity) {
        float expand = this.m_20205_() + 1.0f;
        Vec3[] avector3d = new Vec3[]{EntityLaviathan.m_19903_((double)expand, (double)livingEntity.m_20205_(), (float)livingEntity.m_146908_()), EntityLaviathan.m_19903_((double)expand, (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() - 22.5f)), EntityLaviathan.m_19903_((double)expand, (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() + 22.5f)), EntityLaviathan.m_19903_((double)expand, (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() - 45.0f)), EntityLaviathan.m_19903_((double)expand, (double)livingEntity.m_20205_(), (float)(livingEntity.m_146908_() + 45.0f))};
        LinkedHashSet set = Sets.newLinkedHashSet();
        double d0 = this.m_20191_().f_82292_;
        double d1 = this.m_20191_().f_82289_ - 0.5;
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        for (Vec3 vector3d : avector3d) {
            blockpos$mutable.m_122169_(this.m_20185_() + vector3d.f_82479_, d0, this.m_20189_() + vector3d.f_82481_);
            for (double d2 = d0; d2 > d1; d2 -= 1.0) {
                set.add(blockpos$mutable.m_7949_());
                blockpos$mutable.m_122173_(Direction.DOWN);
            }
        }
        for (BlockPos blockpos : set) {
            double d3;
            if (this.m_9236_().m_6425_(blockpos).m_205070_(FluidTags.f_13132_) || !DismountHelper.m_38439_((double)(d3 = this.m_9236_().m_45573_(blockpos)))) continue;
            Vec3 vector3d1 = Vec3.m_82514_((Vec3i)blockpos, (double)d3);
            for (Pose pose : livingEntity.m_7431_()) {
                AABB axisalignedbb = livingEntity.m_21270_(pose);
                if (!DismountHelper.m_38456_((CollisionGetter)this.m_9236_(), (LivingEntity)livingEntity, (AABB)axisalignedbb.m_82383_(vector3d1))) continue;
                livingEntity.m_20124_(pose);
                return vector3d1;
            }
        }
        return new Vec3(this.m_20185_(), this.m_20191_().f_82292_, this.m_20189_());
    }

    public float getWaterLevelAbove() {
        AABB axisalignedbb = this.m_20191_();
        int i = Mth.m_14107_((double)axisalignedbb.f_82288_);
        int j = Mth.m_14165_((double)axisalignedbb.f_82291_);
        int k = Mth.m_14107_((double)axisalignedbb.f_82292_);
        int l = Mth.m_14165_((double)axisalignedbb.f_82292_);
        int i1 = Mth.m_14107_((double)axisalignedbb.f_82290_);
        int j1 = Mth.m_14165_((double)axisalignedbb.f_82293_);
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        block0: for (int k1 = k; k1 < l; ++k1) {
            float f = 0.0f;
            for (int l1 = i; l1 < j; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.m_122178_(l1, k1, i2);
                    FluidState fluidstate = this.m_9236_().m_6425_((BlockPos)blockpos$mutable);
                    if (fluidstate.m_205070_(FluidTags.f_13131_) || fluidstate.m_205070_(FluidTags.f_13132_)) {
                        f = Math.max(f, fluidstate.m_76155_((BlockGetter)this.m_9236_(), (BlockPos)blockpos$mutable));
                    }
                    if (f >= 1.0f) continue block0;
                }
            }
            if (!(f < 1.0f)) continue;
            return (float)blockpos$mutable.m_123342_() + f;
        }
        return l + 1;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean m_6914_(LevelReader worldIn) {
        return worldIn.m_45784_((Entity)this);
    }

    public boolean shouldSwim() {
        return this.getMaxFluidHeight() >= (double)0.1f || this.m_20077_() || this.m_20072_();
    }

    private float getXForPart(float yaw, float degree) {
        return Mth.m_14031_((float)((float)((double)yaw + Math.toRadians(degree))));
    }

    private float getZForPart(float yaw, float degree) {
        return -Mth.m_14089_((float)((float)((double)yaw + Math.toRadians(degree))));
    }

    public float getHeadHeight() {
        return Mth.m_14036_((float)((Float)this.f_19804_.m_135370_(HEAD_HEIGHT)).floatValue(), (float)-3.0f, (float)3.0f);
    }

    public void setHeadHeight(float height) {
        this.f_19804_.m_135381_(HEAD_HEIGHT, (Object)Float.valueOf(Mth.m_14036_((float)height, (float)-3.0f, (float)3.0f)));
    }

    public boolean isObsidian() {
        return (Boolean)this.f_19804_.m_135370_(OBSIDIAN);
    }

    public void setObsidian(boolean obsidian) {
        this.f_19804_.m_135381_(OBSIDIAN, (Object)obsidian);
    }

    public boolean hasHeadGear() {
        return (Boolean)this.f_19804_.m_135370_(HAS_HEAD_GEAR);
    }

    public void setHeadGear(boolean headGear) {
        this.f_19804_.m_135381_(HAS_HEAD_GEAR, (Object)headGear);
    }

    public boolean hasBodyGear() {
        return (Boolean)this.f_19804_.m_135370_(HAS_BODY_GEAR);
    }

    public void setBodyGear(boolean bodyGear) {
        this.f_19804_.m_135381_(HAS_BODY_GEAR, (Object)bodyGear);
    }

    public int getChillTime() {
        return (Integer)this.f_19804_.m_135370_(CHILL_TIME);
    }

    public void setChillTime(int chillTime) {
        this.f_19804_.m_135381_(CHILL_TIME, (Object)chillTime);
    }

    public float getHeadYaw(float interp) {
        float f;
        if (interp == 0.0f) {
            f = this.m_6080_() - this.f_20883_;
        } else {
            float yBodyRot1 = this.f_20884_ + (this.f_20883_ - this.f_20884_) * interp;
            float yHeadRot1 = this.f_20886_ + (this.m_6080_() - this.f_20886_) * interp;
            f = yHeadRot1 - yBodyRot1;
        }
        return Mth.m_14036_((float)Mth.m_14177_((float)f), (float)-50.0f, (float)50.0f);
    }

    private void setPartPosition(EntityLaviathanPart part, double offsetX, double offsetY, double offsetZ) {
        part.m_6034_(this.m_20185_() + offsetX * (double)part.scale, this.m_20186_() + offsetY * (double)part.scale, this.m_20189_() + offsetZ * (double)part.scale);
    }

    public boolean isMultipartEntity() {
        return true;
    }

    public PartEntity<?>[] getParts() {
        return this.allParts;
    }

    public boolean attackEntityPartFrom(EntityLaviathanPart part, DamageSource source, float amount) {
        return this.m_6469_(source, amount);
    }

    @Override
    public boolean shouldEnterWater() {
        return !this.m_20160_();
    }

    @Override
    public boolean shouldLeaveWater() {
        return this.m_20160_();
    }

    @Override
    public boolean shouldStopMoving() {
        return this.m_20160_();
    }

    @Override
    public int getWaterSearchRange() {
        return 15;
    }

    private double getMaxFluidHeight() {
        return Math.max(this.m_204036_(FluidTags.f_13132_), this.m_204036_(FluidTags.f_13131_));
    }

    public boolean isChilling() {
        return this.getChillTime() > 0 && this.getMaxFluidHeight() <= (double)(this.m_20206_() * 0.5f);
    }

    public void scaleParts() {
        for (EntityLaviathanPart parts : this.allParts) {
            float prev = parts.scale;
            float f = parts.scale = this.m_6162_() ? 0.5f : 1.0f;
            if (prev == parts.scale) continue;
            parts.m_6210_();
        }
    }

    public void m_8107_() {
        super.m_8107_();
        this.scaleParts();
    }

    public Vec3 getLureMosquitoPos() {
        return new Vec3(this.headPart.m_20185_(), this.headPart.m_20227_(0.4f), this.headPart.m_20189_());
    }

    @Override
    public void onPanic() {
    }

    @Override
    public boolean canPanic() {
        return true;
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return (AgeableMob)((EntityType)AMEntityRegistry.LAVIATHAN.get()).m_20615_((Level)serverWorld);
    }

    static class MoveController
    extends MoveControl {
        private final EntityLaviathan laviathan;

        public MoveController(EntityLaviathan dolphinIn) {
            super((Mob)dolphinIn);
            this.laviathan = dolphinIn;
        }

        public void m_8126_() {
            float speed = (float)(this.f_24978_ * 3.0 * this.laviathan.m_21133_(Attributes.f_22279_));
            if (!(this.f_24981_ != MoveControl.Operation.MOVE_TO || this.laviathan.m_21573_().m_26571_() && this.laviathan.m_6688_() == null)) {
                double lvt_5_1_;
                double lvt_3_1_;
                double lvt_1_1_ = this.f_24975_ - this.laviathan.m_20185_();
                double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + (lvt_3_1_ = this.f_24976_ - this.laviathan.m_20186_()) * lvt_3_1_ + (lvt_5_1_ = this.f_24977_ - this.laviathan.m_20189_()) * lvt_5_1_;
                if (lvt_7_1_ < 2.5) {
                    this.laviathan.m_21564_(0.0f);
                } else {
                    float lvt_9_1_ = (float)(Mth.m_14136_((double)lvt_5_1_, (double)lvt_1_1_) * 57.2957763671875) - 90.0f;
                    this.laviathan.m_146922_(this.m_24991_(this.laviathan.m_146908_(), lvt_9_1_, 5.0f));
                    this.laviathan.m_5616_(this.m_24991_(this.laviathan.m_6080_(), lvt_9_1_, 90.0f));
                    if (this.laviathan.shouldSwim()) {
                        this.laviathan.m_7910_(speed * 0.03f);
                        float lvt_11_1_ = -((float)(Mth.m_14136_((double)lvt_3_1_, (double)Mth.m_14116_((float)((float)(lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_)))) * 57.2957763671875));
                        lvt_11_1_ = Mth.m_14036_((float)Mth.m_14177_((float)lvt_11_1_), (float)-85.0f, (float)85.0f);
                        this.laviathan.m_146926_(this.m_24991_(this.laviathan.m_146909_(), lvt_11_1_, 25.0f));
                        float lvt_12_1_ = Mth.m_14089_((float)(this.laviathan.m_146909_() * ((float)Math.PI / 180)));
                        float lvt_13_1_ = Mth.m_14031_((float)(this.laviathan.m_146909_() * ((float)Math.PI / 180)));
                        this.laviathan.f_20902_ = lvt_12_1_ * speed;
                        this.laviathan.f_20901_ = -lvt_13_1_ * speed;
                    } else {
                        this.laviathan.m_7910_(speed * 0.1f);
                    }
                }
            } else if (!this.laviathan.m_9236_().m_8055_(this.laviathan.m_20183_().m_7494_()).m_60819_().m_76178_() && this.laviathan.getChillTime() <= 0) {
                this.laviathan.m_20256_(this.laviathan.m_20184_().m_82520_(0.0, -0.05, 0.0));
            }
        }
    }
}

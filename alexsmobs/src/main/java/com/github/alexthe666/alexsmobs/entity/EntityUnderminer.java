/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.StructureTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.common.Tags$Blocks
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.ai.EtherealMoveController;
import com.github.alexthe666.alexsmobs.entity.ai.MonsterAIWalkThroughHallsOfStructure;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class EntityUnderminer
extends PathfinderMob {
    protected static final EntityDataAccessor<Optional<BlockPos>> TARGETED_BLOCK_POS = SynchedEntityData.m_135353_(EntityUnderminer.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    private static final EntityDataAccessor<Boolean> DWARF = SynchedEntityData.m_135353_(EntityUnderminer.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> MINING_PROGRESS = SynchedEntityData.m_135353_(EntityUnderminer.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityUnderminer.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> HIDING = SynchedEntityData.m_135353_(EntityUnderminer.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> VISUALLY_MINING = SynchedEntityData.m_135353_(EntityUnderminer.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private int mineCooldown = 100;
    private int resetStackTime = 0;
    private ItemStack lastGivenStack = null;
    public float hidingProgress = 0.0f;
    public float prevHidingProgress = 0.0f;
    private boolean mineAIFlag = false;
    private BlockPos lastPosition = this.m_20183_();

    public EntityUnderminer(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.f_21342_ = new EtherealMoveController((Mob)this, 1.0f);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0).m_22268_(Attributes.f_22281_, 3.0).m_22268_(Attributes.f_22279_, (double)0.2f).m_22268_(Attributes.f_22277_, 64.0);
    }

    protected PathNavigation m_6037_(Level level) {
        return new PathNavigator(this, this.m_9236_());
    }

    public static <T extends Mob> boolean checkUnderminerSpawnRules(EntityType<EntityUnderminer> entityType, ServerLevelAccessor iServerWorld, MobSpawnType reason, BlockPos pos, RandomSource random) {
        if (reason == MobSpawnType.SPAWNER) {
            return true;
        }
        int j = 3;
        if (pos.m_123342_() >= iServerWorld.m_5736_()) {
            return false;
        }
        if (AlexsMobs.isHalloween()) {
            j = 7;
        } else if (random.m_188499_()) {
            return false;
        }
        int i = iServerWorld.m_46803_(pos);
        return i > random.m_188503_(j) ? false : EntityUnderminer.m_217057_(entityType, (LevelAccessor)iServerWorld, (MobSpawnType)reason, (BlockPos)pos, (RandomSource)random);
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return !this.m_8023_() && !this.m_8077_();
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.m_8077_() || this.lastGivenStack != null;
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.underminerSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(DWARF, (Object)true);
        this.f_19804_.m_135372_(HIDING, (Object)false);
        this.f_19804_.m_135372_(VISUALLY_MINING, (Object)false);
        this.f_19804_.m_135372_(TARGETED_BLOCK_POS, Optional.empty());
        this.f_19804_.m_135372_(MINING_PROGRESS, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(VARIANT, (Object)0);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Dwarf", this.isDwarf());
        compound.m_128379_("Hiding", this.isHiding());
        compound.m_128405_("Variant", this.getVariant());
        compound.m_128405_("ResetItemTime", this.resetStackTime);
        compound.m_128405_("MineCooldown", this.mineCooldown);
        if (this.lastGivenStack != null) {
            compound.m_128365_("MineStack", (Tag)this.lastGivenStack.serializeNBT());
        }
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setDwarf(compound.m_128471_("Dwarf"));
        this.setHiding(compound.m_128471_("Hiding"));
        this.setVariant(compound.m_128451_("Variant"));
        this.resetStackTime = compound.m_128451_("ResetItemTime");
        this.mineCooldown = compound.m_128451_("MineCooldown");
        if (compound.m_128441_("MineStack")) {
            this.lastGivenStack = ItemStack.m_41712_((CompoundTag)compound.m_128469_("MineStack"));
        }
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.UNDERMINER_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.UNDERMINER_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.UNDERMINER_HURT.get();
    }

    protected void m_7355_(BlockPos pos, BlockState blockIn) {
    }

    public boolean isDwarf() {
        return (Boolean)this.f_19804_.m_135370_(DWARF) != false && !this.isExtraSpooky();
    }

    public void setDwarf(boolean phasing) {
        this.f_19804_.m_135381_(DWARF, (Object)phasing);
    }

    public int getVariant() {
        return this.isExtraSpooky() ? 1 : (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int i) {
        this.f_19804_.m_135381_(VARIANT, (Object)i);
    }

    public boolean isHiding() {
        return (Boolean)this.f_19804_.m_135370_(HIDING);
    }

    public void setHiding(boolean phasing) {
        this.f_19804_.m_135381_(HIDING, (Object)phasing);
    }

    @Nullable
    public BlockPos getMiningPos() {
        return ((Optional)this.m_20088_().m_135370_(TARGETED_BLOCK_POS)).orElse(null);
    }

    public void setMiningPos(@Nullable BlockPos beamTarget) {
        this.m_20088_().m_135381_(TARGETED_BLOCK_POS, Optional.ofNullable(beamTarget));
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new MeleeAttackGoal((PathfinderMob)this, (double)1.2f, true));
        this.f_21345_.m_25352_(2, (Goal)new MineGoal());
        this.f_21345_.m_25352_(3, (Goal)new MonsterAIWalkThroughHallsOfStructure(this, 0.5, 60, (TagKey<Structure>)StructureTags.f_215890_, 50.0));
        this.f_21345_.m_25352_(4, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]).m_26044_(new Class[0]));
    }

    public boolean m_6673_(DamageSource source) {
        return !source.m_276093_(DamageTypes.f_268515_) && source.m_276093_(DamageTypes.f_268724_) && !source.m_19390_() || super.m_6673_(source);
    }

    private float calculateDistanceToFloor() {
        BlockPos floor = AMBlockPos.fromCoords(this.m_20185_(), this.m_20191_().f_82292_, this.m_20189_());
        while (!this.m_9236_().m_8055_(floor).m_60783_((BlockGetter)this.m_9236_(), floor, Direction.UP) && floor.m_123342_() > this.m_9236_().m_141937_()) {
            floor = floor.m_7495_();
        }
        return (float)(this.m_20191_().f_82289_ - (double)(floor.m_123342_() + 1));
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.m_21515_()) {
            this.m_19920_(this.m_6113_(), travelVector);
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82542_(0.9, 0.6, 0.9));
        } else {
            super.m_7023_(travelVector);
        }
    }

    protected void m_213945_(RandomSource p_218949_, DifficultyInstance p_218950_) {
        super.m_213945_(p_218949_, p_218950_);
        this.m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)AMItemRegistry.GHOSTLY_PICKAXE.get()));
    }

    protected float m_21519_(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return 0.5f;
        }
        return super.m_21519_(slot);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor level, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        spawnData = super.m_6518_(level, difficultyInstance, mobSpawnType, spawnData, tag);
        RandomSource randomsource = level.m_213780_();
        this.m_213945_(randomsource, difficultyInstance);
        if (this.f_19796_.m_188501_() < 0.3f) {
            this.setVariant(this.f_19796_.m_188503_(2));
            this.setDwarf(false);
        } else {
            this.setDwarf(true);
        }
        return spawnData;
    }

    public boolean isFullyHidden() {
        return this.isHiding() && this.hidingProgress >= 10.0f;
    }

    public void m_8119_() {
        this.f_19794_ = true;
        super.m_8119_();
        this.prevHidingProgress = this.hidingProgress;
        this.f_19794_ = false;
        if (this.isHiding() && this.hidingProgress < 10.0f) {
            this.hidingProgress += 1.0f;
        }
        if (!this.isHiding() && this.hidingProgress > 0.0f) {
            this.hidingProgress -= 1.0f;
        }
        if (!this.m_9236_().f_46443_) {
            Player player;
            double xzSpeed = this.m_20184_().m_165924_();
            double distToFloor = Mth.m_14036_((float)this.calculateDistanceToFloor(), (float)-1.0f, (float)1.0f);
            if (Math.abs(distToFloor) > 0.01 && xzSpeed < 0.05 && !this.isActuallyInAWall()) {
                if (distToFloor < 0.0) {
                    this.m_20256_(this.m_20184_().m_82520_(0.0, -Math.min(distToFloor * (double)0.1f, 0.0), 0.0));
                } else if (distToFloor > 0.0) {
                    this.m_20256_(this.m_20184_().m_82520_(0.0, -Math.max(distToFloor * (double)0.1f, 0.0), 0.0));
                }
            }
            if (this.lastPosition != null && this.lastPosition.m_123331_((Vec3i)this.m_20183_()) > 2.5 && Math.abs(distToFloor) < 0.5) {
                this.m_5496_((SoundEvent)AMSoundRegistry.UNDERMINER_STEP.get(), 1.0f, 0.75f + this.f_19796_.m_188501_() * 0.25f);
                this.lastPosition = this.m_20183_();
                if (this.f_19796_.m_188501_() < 0.015f && !this.m_9236_().m_45527_(this.lastPosition)) {
                    this.m_5496_((SoundEvent)SoundEvents.f_11689_.get(), 3.0f, 0.75f + this.f_19796_.m_188501_() * 0.25f);
                }
            }
            if (!((player = this.m_9236_().m_45924_(this.m_20185_(), this.m_20186_(), this.m_20189_(), AMConfig.underminerDisappearDistance, true)) == null || this.lastGivenStack != null || this.m_5448_() != null && this.m_5448_().m_6084_())) {
                this.setHiding(true);
                this.m_21391_((Entity)player, 360.0f, 360.0f);
            } else {
                this.setHiding(false);
            }
        }
        this.m_5618_(this.m_146908_());
        if (this.mineCooldown > 0) {
            --this.mineCooldown;
        }
        if (this.resetStackTime > 0) {
            --this.resetStackTime;
            if (this.resetStackTime == 0) {
                this.lastGivenStack = null;
            }
        }
        if (((Boolean)this.f_19804_.m_135370_(VISUALLY_MINING)).booleanValue()) {
            this.m_6674_(InteractionHand.MAIN_HAND);
        }
    }

    public boolean m_6063_() {
        return false;
    }

    public boolean m_21531_() {
        return true;
    }

    public boolean m_7243_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.UNDERMINER_ORES);
    }

    protected void m_7581_(ItemEntity itemEntity) {
        ItemStack itemstack = itemEntity.m_32055_();
        if (itemstack.m_204117_(AMTagRegistry.UNDERMINER_ORES)) {
            this.m_21053_(itemEntity);
            this.m_7938_((Entity)itemEntity, itemstack.m_41613_());
            itemEntity.m_146870_();
            this.mineAIFlag = this.lastGivenStack == null || !ItemStack.m_41656_((ItemStack)this.lastGivenStack, (ItemStack)itemEntity.m_32055_());
            this.lastGivenStack = itemEntity.m_32055_();
            this.resetStackTime = 2000 + this.f_19796_.m_188503_(1200);
            this.mineCooldown = 0;
        } else {
            super.m_7581_(itemEntity);
        }
    }

    protected void m_6135_() {
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean isExtraSpooky() {
        String s = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        return AlexsMobs.isAprilFools() || AlexsMobs.isHalloween() || s != null && s.toLowerCase().contains("herobrine");
    }

    private boolean isActuallyInAWall() {
        float f = this.m_6972_((Pose)this.m_20089_()).f_20377_ * 0.1f;
        AABB aabb = AABB.m_165882_((Vec3)this.m_146892_(), (double)f, (double)1.0E-6, (double)f);
        return BlockPos.m_121921_((AABB)aabb).anyMatch(p_201942_ -> {
            BlockState blockstate = this.m_9236_().m_8055_(p_201942_);
            return !blockstate.m_60795_() && blockstate.m_60828_((BlockGetter)this.m_9236_(), p_201942_) && Shapes.m_83157_((VoxelShape)blockstate.m_60812_((BlockGetter)this.m_9236_(), p_201942_).m_83216_((double)p_201942_.m_123341_(), (double)p_201942_.m_123342_(), (double)p_201942_.m_123343_()), (VoxelShape)Shapes.m_83064_((AABB)aabb), (BooleanOp)BooleanOp.f_82689_);
        });
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public float getBrightness() {
        return 1.0f;
    }

    public float getMiningProgress() {
        return ((Float)this.f_19804_.m_135370_(MINING_PROGRESS)).floatValue();
    }

    public void setMiningProgress(float f) {
        this.f_19804_.m_135381_(MINING_PROGRESS, (Object)Float.valueOf(f));
    }

    private List<BlockPos> getNearbyObscuredOres(int range, int maxOres) {
        ArrayList<BlockPos> obscuredBlocks = new ArrayList<BlockPos>();
        BlockPos blockpos = this.m_20183_();
        int half = range / 2;
        int i = 0;
        while (i <= half && i >= -half) {
            int j = 0;
            while (j <= range && j >= -range) {
                int k = 0;
                while (k <= range && k >= -range) {
                    BlockPos offset = blockpos.m_7918_(j, i, k);
                    BlockState state = this.m_9236_().m_8055_(offset);
                    if (this.isValidMiningBlock(state)) {
                        if (obscuredBlocks.size() >= maxOres) break;
                        BlockPos obscured = this.getObscuringBlockOf(offset);
                        if (obscured != null) {
                            obscuredBlocks.add(obscured);
                        }
                    }
                    k = (k <= 0 ? 1 : 0) - k;
                }
                j = (j <= 0 ? 1 : 0) - j;
            }
            i = (i <= 0 ? 1 : 0) - i;
        }
        return obscuredBlocks;
    }

    private boolean isValidMiningBlock(BlockState state) {
        if (this.lastGivenStack != null) {
            return this.lastGivenStack.m_41720_() == state.m_60734_().m_5456_();
        }
        return state.m_204336_(Tags.Blocks.ORES);
    }

    public void m_8107_() {
        this.m_21203_();
        super.m_8107_();
    }

    public boolean m_6097_() {
        return !this.isFullyHidden() && super.m_6097_();
    }

    public boolean m_7313_(Entity entity) {
        return this.isFullyHidden() || super.m_7313_(entity);
    }

    private BlockPos getObscuringBlockOf(BlockPos target) {
        Vec3 eyes = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        BlockHitResult hitResult = this.m_9236_().m_45547_(new ClipContext(eyes, Vec3.m_82512_((Vec3i)target), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        if (hitResult instanceof BlockHitResult && !hitResult.m_82425_().equals((Object)target)) {
            BlockPos pos = hitResult.m_82425_();
            return pos.m_123331_((Vec3i)target) > 4.0 ? null : pos;
        }
        return null;
    }

    private boolean hasPick() {
        return this.m_21120_(InteractionHand.MAIN_HAND).m_150930_((Item)AMItemRegistry.GHOSTLY_PICKAXE.get());
    }

    private class PathNavigator
    extends GroundPathNavigation {
        public PathNavigator(EntityUnderminer underminer, Level level) {
            super((Mob)underminer, EntityUnderminer.this.m_9236_());
        }

        protected boolean m_7632_() {
            return !this.f_26494_.m_20159_();
        }

        protected Vec3 m_7475_() {
            return this.f_26494_.m_20182_();
        }
    }

    private class MineGoal
    extends Goal {
        private BlockPos minePretendPos = null;
        private BlockState minePretendStartState = null;
        private int mineTime = 0;

        public MineGoal() {
            this.m_7021_(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            if (EntityUnderminer.this.mineCooldown == 0 && EntityUnderminer.this.hasPick() && !EntityUnderminer.this.isHiding() && !EntityUnderminer.this.isActuallyInAWall() && EntityUnderminer.this.m_217043_().m_188503_(30) == 0) {
                List<BlockPos> obscuredOres = EntityUnderminer.this.getNearbyObscuredOres(16, 8);
                BlockPos nearest = null;
                double nearestDist = Double.MAX_VALUE;
                if (!obscuredOres.isEmpty()) {
                    for (BlockPos obscuredPos : obscuredOres) {
                        double dist = EntityUnderminer.this.m_20182_().m_82554_(Vec3.m_82512_((Vec3i)obscuredPos));
                        if (!(nearestDist > dist)) continue;
                        nearest = obscuredPos;
                        nearestDist = dist;
                    }
                }
                EntityUnderminer.this.mineAIFlag = false;
                this.minePretendPos = nearest;
                return this.minePretendPos != null;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.minePretendPos != null && EntityUnderminer.this.hasPick() && !EntityUnderminer.this.isHiding() && !EntityUnderminer.this.mineAIFlag && this.minePretendStartState != null && this.minePretendStartState.equals(EntityUnderminer.this.m_9236_().m_8055_(this.minePretendPos)) && this.mineTime < 200;
        }

        public void m_8056_() {
            if (this.minePretendPos != null) {
                this.minePretendStartState = EntityUnderminer.this.m_9236_().m_8055_(this.minePretendPos);
            }
        }

        public void m_8041_() {
            if (this.minePretendPos != null && this.minePretendStartState != null && !this.minePretendStartState.equals(EntityUnderminer.this.m_9236_().m_8055_(this.minePretendPos))) {
                for (ServerPlayer serverplayerentity : EntityUnderminer.this.m_9236_().m_45976_(ServerPlayer.class, EntityUnderminer.this.m_20191_().m_82377_(12.0, 12.0, 12.0))) {
                    AMAdvancementTriggerRegistry.UNDERMINE_UNDERMINER.trigger(serverplayerentity);
                }
            }
            this.minePretendPos = null;
            this.minePretendStartState = null;
            this.mineTime = 0;
            EntityUnderminer.this.f_19804_.m_135381_(VISUALLY_MINING, (Object)false);
            EntityUnderminer.this.setMiningPos(null);
            EntityUnderminer.this.setMiningProgress(0.0f);
            EntityUnderminer.this.mineCooldown = EntityUnderminer.this.resetStackTime > 0 ? 40 : 200 + EntityUnderminer.this.f_19796_.m_188503_(200);
        }

        public void m_8037_() {
            if (this.minePretendPos != null && this.minePretendStartState != null) {
                ++this.mineTime;
                double distSqr = EntityUnderminer.this.m_20275_((float)this.minePretendPos.m_123341_() + 0.5f, (float)this.minePretendPos.m_123342_() + 0.5f, (float)this.minePretendPos.m_123343_() + 0.5f);
                if (distSqr < 6.5) {
                    EntityUnderminer.this.m_21573_().m_26573_();
                    if (EntityUnderminer.this.m_21573_().m_26571_()) {
                        EntityUnderminer.this.setMiningPos(this.minePretendPos);
                        EntityUnderminer.this.setMiningProgress((1.0f + (float)Math.cos((double)((float)this.mineTime * 0.1f) + Math.PI)) * 0.5f);
                        double d1 = (double)((float)this.minePretendPos.m_123343_() + 0.5f) - EntityUnderminer.this.m_20189_();
                        double d3 = (double)((float)this.minePretendPos.m_123342_() + 0.5f) - EntityUnderminer.this.m_20186_();
                        double d2 = (double)((float)this.minePretendPos.m_123341_() + 0.5f) - EntityUnderminer.this.m_20185_();
                        float f = Mth.m_14116_((float)((float)(d2 * d2 + d1 * d1)));
                        EntityUnderminer.this.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                        EntityUnderminer.this.m_146926_((float)(Mth.m_14136_((double)d3, (double)f) * 57.2957763671875) + (float)Math.sin((float)EntityUnderminer.this.f_19797_ * 0.1f));
                        EntityUnderminer.this.f_19804_.m_135381_(VISUALLY_MINING, (Object)true);
                        if (this.mineTime % 10 == 0) {
                            SoundType soundType = this.minePretendStartState.m_60734_().getSoundType(this.minePretendStartState, (LevelReader)EntityUnderminer.this.m_9236_(), this.minePretendPos, (Entity)EntityUnderminer.this);
                            EntityUnderminer.this.m_216990_(soundType.m_56778_());
                        }
                    }
                } else {
                    EntityUnderminer.this.f_19804_.m_135381_(VISUALLY_MINING, (Object)false);
                    EntityUnderminer.this.setMiningPos(null);
                    EntityUnderminer.this.m_21573_().m_26519_((double)((float)this.minePretendPos.m_123341_() + 0.5f), (double)((float)this.minePretendPos.m_123342_() + 0.5f), (double)((float)this.minePretendPos.m_123343_() + 0.5f), 1.0);
                }
            }
        }
    }
}

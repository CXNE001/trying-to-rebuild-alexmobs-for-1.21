/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ShovelItem
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityRattlesnake;
import com.github.alexthe666.alexsmobs.entity.IDancingMob;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.message.MessageStartDancing;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EntityRainFrog
extends Animal
implements ITargetsDroppedItems,
IDancingMob {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.m_135353_(EntityRainFrog.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> STANCE_TIME = SynchedEntityData.m_135353_(EntityRainFrog.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> ATTACK_TIME = SynchedEntityData.m_135353_(EntityRainFrog.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> DANCE_TIME = SynchedEntityData.m_135353_(EntityRainFrog.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> BURROWED = SynchedEntityData.m_135353_(EntityRainFrog.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> DISTURBED = SynchedEntityData.m_135353_(EntityRainFrog.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float burrowProgress;
    public float prevBurrowProgress;
    public float danceProgress;
    public float prevDanceProgress;
    public float attackProgress;
    public float prevAttackProgress;
    public float stanceProgress;
    public float prevStanceProgress;
    private int burrowCooldown = 0;
    private int weatherCooldown = 0;
    private boolean isJukeboxing;
    private BlockPos jukeboxPosition;

    protected EntityRainFrog(EntityType<? extends Animal> rainFrog, Level lvl) {
        super(rainFrog, lvl);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 6.0).m_22268_(Attributes.f_22279_, (double)0.2f);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new TemptGoal((PathfinderMob)this, 1.0, Ingredient.m_204132_(AMTagRegistry.RAIN_FROG_BREEDABLES), false));
        this.f_21345_.m_25352_(2, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new AvoidEntityGoal((PathfinderMob)this, EntityRattlesnake.class, 9.0f, 1.3, 1.0));
        this.f_21345_.m_25352_(5, (Goal)new AIBurrow());
        this.f_21345_.m_25352_(6, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 20, 1.0, 10, 7));
        this.f_21345_.m_25352_(7, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 10.0f));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, new CreatureAITargetItems((PathfinderMob)this, false));
    }

    public static boolean canRainFrogSpawn(EntityType animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.RAIN_FROG_SPAWNS);
        return spawnBlock && worldIn.m_6106_() != null && (worldIn.m_6106_().m_6534_() || worldIn.m_6106_().m_6533_());
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.rainFrogSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    public boolean isBurrowed() {
        return (Boolean)this.f_19804_.m_135370_(BURROWED);
    }

    public void setBurrowed(boolean burrowed) {
        this.f_19804_.m_135381_(BURROWED, (Object)burrowed);
    }

    public boolean isDisturbed() {
        return (Boolean)this.f_19804_.m_135370_(DISTURBED);
    }

    public void setDisturbed(boolean burrowed) {
        this.f_19804_.m_135381_(DISTURBED, (Object)burrowed);
    }

    public int getVariant() {
        return (Integer)this.f_19804_.m_135370_(VARIANT);
    }

    public void setVariant(int variant) {
        this.f_19804_.m_135381_(VARIANT, (Object)variant);
    }

    public int getStanceTime() {
        return (Integer)this.f_19804_.m_135370_(STANCE_TIME);
    }

    public void setStanceTime(int stanceTime) {
        this.f_19804_.m_135381_(STANCE_TIME, (Object)stanceTime);
    }

    public int getAttackTime() {
        return (Integer)this.f_19804_.m_135370_(ATTACK_TIME);
    }

    public void setAttackTime(int attackTime) {
        this.f_19804_.m_135381_(ATTACK_TIME, (Object)attackTime);
    }

    public int getDanceTime() {
        return (Integer)this.f_19804_.m_135370_(DANCE_TIME);
    }

    public void setDanceTime(int danceTime) {
        this.f_19804_.m_135381_(DANCE_TIME, (Object)danceTime);
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.RAIN_FROG_BREEDABLES);
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        EntityRainFrog frog = (EntityRainFrog)((EntityType)AMEntityRegistry.RAIN_FROG.get()).m_20615_((Level)p_241840_1_);
        frog.setVariant(this.getVariant());
        frog.setDisturbed(true);
        return frog;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(VARIANT, (Object)0);
        this.f_19804_.m_135372_(STANCE_TIME, (Object)0);
        this.f_19804_.m_135372_(ATTACK_TIME, (Object)0);
        this.f_19804_.m_135372_(DANCE_TIME, (Object)0);
        this.f_19804_.m_135372_(BURROWED, (Object)false);
        this.f_19804_.m_135372_(DISTURBED, (Object)false);
    }

    public void m_8119_() {
        boolean dancing;
        super.m_8119_();
        this.prevBurrowProgress = this.burrowProgress;
        this.prevDanceProgress = this.danceProgress;
        this.prevAttackProgress = this.attackProgress;
        this.prevStanceProgress = this.stanceProgress;
        if (this.isBurrowed()) {
            if (this.burrowProgress < 5.0f) {
                this.burrowProgress += 0.5f;
            }
        } else if (this.burrowProgress > 0.0f) {
            this.burrowProgress -= 0.5f;
        }
        if (this.burrowCooldown > 0) {
            --this.burrowCooldown;
        }
        if (this.getStanceTime() > 0) {
            this.setStanceTime(this.getStanceTime() - 1);
            if (this.stanceProgress < 5.0f) {
                this.stanceProgress += 1.0f;
            }
        } else if (this.stanceProgress > 0.0f) {
            this.stanceProgress -= 1.0f;
        }
        if (this.getAttackTime() > 0) {
            this.setAttackTime(this.getAttackTime() - 1);
            if (this.attackProgress < 5.0f) {
                this.attackProgress += 2.5f;
            }
        } else if (this.attackProgress > 0.0f) {
            this.attackProgress -= 0.5f;
        }
        boolean bl = dancing = this.getDanceTime() > 0 || this.isJukeboxing;
        if (dancing) {
            if (this.danceProgress < 5.0f) {
                this.danceProgress += 1.0f;
            }
        } else if (this.danceProgress > 0.0f) {
            this.danceProgress -= 1.0f;
        }
        if (this.getDanceTime() > 0) {
            this.setBurrowed(false);
            this.setDanceTime(this.getDanceTime() - 1);
            if (this.getDanceTime() == 1 && this.weatherCooldown <= 0 && this.m_9236_().m_46469_().m_46207_(GameRules.f_46150_)) {
                this.changeWeather();
            }
        }
        if (this.weatherCooldown > 0) {
            --this.weatherCooldown;
        }
        if (this.jukeboxPosition == null || !this.jukeboxPosition.m_203195_((Position)this.m_20182_(), 15.0) || !this.m_9236_().m_8055_(this.jukeboxPosition).m_60713_(Blocks.f_50131_)) {
            this.isJukeboxing = false;
            this.setDanceTime(0);
            this.jukeboxPosition = null;
        }
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev && source.m_7640_() instanceof LivingEntity) {
            if (this.getStanceTime() <= 0) {
                this.setStanceTime(30 + this.f_19796_.m_188503_(20));
            }
            this.setBurrowed(false);
        }
        return prev;
    }

    public boolean m_6785_(double distanceToClosestPlayer) {
        return !this.m_8023_();
    }

    public boolean m_8023_() {
        return super.m_8023_() || this.isDisturbed();
    }

    public boolean m_6673_(DamageSource source) {
        return source.m_276093_(DamageTypes.f_268612_) || super.m_6673_(source);
    }

    public boolean m_5803_() {
        return this.isBurrowed();
    }

    public void calculateEntityAnimation(LivingEntity mob, boolean flying) {
        float f1 = (float)Mth.m_184648_((double)(this.m_20185_() - this.f_19854_), (double)0.0, (double)(this.m_20189_() - this.f_19856_));
        float f2 = Math.min(f1 * 128.0f, 1.0f);
        this.f_267362_.m_267566_(f2, 0.4f);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setVariant(this.f_19796_.m_188503_(3));
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.setDisturbed(compound.m_128471_("Disturbed"));
        this.setVariant(compound.m_128451_("Variant"));
        this.weatherCooldown = compound.m_128451_("WeatherCooldown");
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("Disturbed", this.isDisturbed());
        compound.m_128405_("Variant", this.getVariant());
        compound.m_128405_("WeatherCooldown", this.weatherCooldown);
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        Item item = itemstack.m_41720_();
        InteractionResult type = super.m_6071_(player, hand);
        if (item instanceof ShovelItem && (this.isBurrowed() || !this.isDisturbed()) && !this.m_9236_().f_46443_) {
            this.f_21363_ = 1000;
            if (!player.m_7500_()) {
                itemstack.m_220157_(1, this.m_217043_(), player instanceof ServerPlayer ? (ServerPlayer)player : null);
            }
            this.setStanceTime(20 + this.f_19796_.m_188503_(30));
            this.setBurrowed(false);
            this.setDisturbed(true);
            this.burrowCooldown += 150 + this.f_19796_.m_188503_(120);
            this.m_146850_(GameEvent.f_223708_);
            this.m_5496_(SoundEvents.f_12331_, this.m_6121_(), this.m_6100_());
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public void m_7023_(Vec3 travelVector) {
        if (this.isBurrowed() || this.getDanceTime() > 0) {
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

    @Override
    public void onFindTarget(ItemEntity e) {
        this.setBurrowed(false);
        this.burrowCooldown += 50;
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.INSECT_ITEMS);
    }

    @Override
    public void onGetItem(ItemEntity e) {
        this.setAttackTime(10);
        this.m_5634_(2.0f);
    }

    private void changeWeather() {
        Level level;
        int time = 24000 + 1200 * this.f_19796_.m_188503_(10);
        int type = 0;
        if (!this.m_9236_().m_46471_()) {
            type = this.f_19796_.m_188503_(1) + 1;
        }
        if ((level = this.m_9236_()) instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            if (type == 0) {
                serverLevel.m_8606_(time, 0, false, false);
            } else {
                serverLevel.m_8606_(0, time, true, type == 2);
            }
        }
        this.weatherCooldown = time + 24000;
    }

    public void m_6818_(BlockPos pos, boolean isPartying) {
        AlexsMobs.sendMSGToServer(new MessageStartDancing(this.m_19879_(), isPartying, pos));
        if (isPartying) {
            this.setJukeboxPos(pos);
        } else {
            this.setJukeboxPos(null);
        }
    }

    @Override
    public void setDancing(boolean dancing) {
        this.setDanceTime(dancing && this.weatherCooldown == 0 ? 240 + this.f_19796_.m_188503_(200) : 0);
    }

    @Override
    public void setJukeboxPos(BlockPos pos) {
        this.jukeboxPosition = pos;
    }

    protected SoundEvent m_7515_() {
        return this.getStanceTime() > 0 ? (SoundEvent)AMSoundRegistry.RAIN_FROG_HURT.get() : (SoundEvent)AMSoundRegistry.RAIN_FROG_IDLE.get();
    }

    public int m_8100_() {
        return this.getStanceTime() > 0 ? 10 : 80;
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.RAIN_FROG_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.RAIN_FROG_HURT.get();
    }

    private class AIBurrow
    extends Goal {
        private BlockPos sand = null;
        private int burrowedTime = 0;

        public AIBurrow() {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean m_8036_() {
            if (!EntityRainFrog.this.isBurrowed() && EntityRainFrog.this.burrowCooldown == 0 && EntityRainFrog.this.f_19796_.m_188503_(200) == 0) {
                this.burrowedTime = 0;
                this.sand = this.findSand();
                return this.sand != null;
            }
            return false;
        }

        public boolean m_8045_() {
            return this.burrowedTime < 300;
        }

        public BlockPos findSand() {
            BlockPos blockpos = null;
            for (BlockPos blockpos1 : BlockPos.m_121976_((int)Mth.m_14107_((double)(EntityRainFrog.this.m_20185_() - 4.0)), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20186_() - 1.0)), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20189_() - 4.0)), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20185_() + 4.0)), (int)EntityRainFrog.this.m_146904_(), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20189_() + 4.0)))) {
                if (!EntityRainFrog.this.m_9236_().m_8055_(blockpos1).m_204336_(BlockTags.f_13029_)) continue;
                blockpos = blockpos1;
                break;
            }
            return blockpos;
        }

        public void m_8037_() {
            if (EntityRainFrog.this.isBurrowed()) {
                ++this.burrowedTime;
                if (!EntityRainFrog.this.m_20075_().m_204336_(BlockTags.f_13029_)) {
                    EntityRainFrog.this.setBurrowed(false);
                }
            } else if (this.sand != null) {
                EntityRainFrog.this.m_21573_().m_26519_((double)((float)this.sand.m_123341_() + 0.5f), (double)((float)this.sand.m_123342_() + 1.0f), (double)((float)this.sand.m_123343_() + 0.5f), 1.0);
                if (EntityRainFrog.this.m_20075_().m_204336_(BlockTags.f_13029_)) {
                    EntityRainFrog.this.setBurrowed(true);
                    EntityRainFrog.this.m_21573_().m_26573_();
                    this.sand = null;
                } else {
                    EntityRainFrog.this.setBurrowed(false);
                }
            }
        }

        public void m_8041_() {
            EntityRainFrog.this.setBurrowed(false);
            EntityRainFrog.this.burrowCooldown = 120 + EntityRainFrog.this.f_19796_.m_188503_(1200);
            this.sand = null;
        }
    }
}

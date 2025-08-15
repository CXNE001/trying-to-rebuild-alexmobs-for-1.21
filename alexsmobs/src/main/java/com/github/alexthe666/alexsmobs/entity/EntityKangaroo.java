/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.AnimationHandler
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.ContainerListener
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.EquipmentSlot$Type
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.JumpControl
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.DispenserMenu
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.pathfinder.Path
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.network.NetworkHooks
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.IFollower;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHurtByTargetNotBaby;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIRideParent;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import com.github.alexthe666.alexsmobs.entity.ai.KangarooAIMelee;
import com.github.alexthe666.alexsmobs.entity.ai.TameableAIFollowOwner;
import com.github.alexthe666.alexsmobs.message.MessageKangarooEat;
import com.github.alexthe666.alexsmobs.message.MessageKangarooInventorySync;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.NetworkHooks;

public class EntityKangaroo
extends TamableAnimal
implements ContainerListener,
IAnimatedEntity,
IFollower {
    public static final Animation ANIMATION_EAT_GRASS = Animation.create((int)30);
    public static final Animation ANIMATION_KICK = Animation.create((int)15);
    public static final Animation ANIMATION_PUNCH_R = Animation.create((int)13);
    public static final Animation ANIMATION_PUNCH_L = Animation.create((int)13);
    private static final EntityDataAccessor<Boolean> STANDING = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> VISUAL_FLAG = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> POUCH_TICK = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> HELMET_INDEX = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> SWORD_INDEX = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> CHEST_INDEX = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> FORCED_SIT = SynchedEntityData.m_135353_(EntityKangaroo.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float prevPouchProgress;
    public float pouchProgress;
    public float sitProgress;
    public float prevSitProgress;
    public float standProgress;
    public float prevStandProgress;
    public float totalMovingProgress;
    public float prevTotalMovingProgress;
    public int maxStandTime = 75;
    public SimpleContainer kangarooInventory;
    private int animationTick;
    private Animation currentAnimation;
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private int standingTime = 0;
    private int sittingTime = 0;
    private int maxSitTime = 75;
    private int eatCooldown = 0;
    private int carrotFeedings = 0;
    private int clientArmorCooldown = 0;

    protected EntityKangaroo(EntityType type, Level world) {
        super(type, world);
        this.initKangarooInventory();
        this.f_21343_ = new JumpHelperController(this);
        this.f_21342_ = new MoveHelperController(this);
    }

    public static <T extends Mob> boolean canKangarooSpawn(EntityType<? extends Animal> animal, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
        boolean spawnBlock = worldIn.m_8055_(pos.m_7495_()).m_204336_(AMTagRegistry.KANGAROO_SPAWNS);
        return spawnBlock && worldIn.m_45524_(pos, 0) > 8;
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 22.0).m_22268_(Attributes.f_22277_, 32.0).m_22268_(Attributes.f_22279_, 0.5).m_22268_(Attributes.f_22281_, 4.0);
    }

    @Nullable
    public LivingEntity m_6688_() {
        return null;
    }

    protected void m_6119_() {
        super.m_6119_();
        Entity lvt_1_1_ = this.m_21524_();
        if (lvt_1_1_ != null && lvt_1_1_.m_9236_() == this.m_9236_()) {
            this.m_21446_(lvt_1_1_.m_20183_(), 5);
            float lvt_2_1_ = this.m_20270_(lvt_1_1_);
            if (this.isSitting()) {
                if (lvt_2_1_ > 10.0f) {
                    this.m_21455_(true, true);
                }
                return;
            }
            this.m_7880_(lvt_2_1_);
            if (lvt_2_1_ > 10.0f) {
                this.m_21455_(true, true);
                this.f_21345_.m_25355_(Goal.Flag.MOVE);
            } else if (lvt_2_1_ > 6.0f) {
                double lvt_3_1_ = (lvt_1_1_.m_20185_() - this.m_20185_()) / (double)lvt_2_1_;
                double lvt_5_1_ = (lvt_1_1_.m_20186_() - this.m_20186_()) / (double)lvt_2_1_;
                double lvt_7_1_ = (lvt_1_1_.m_20189_() - this.m_20189_()) / (double)lvt_2_1_;
                this.m_20256_(this.m_20184_().m_82520_(Math.copySign(lvt_3_1_ * lvt_3_1_ * 0.4, lvt_3_1_), Math.copySign(lvt_5_1_ * lvt_5_1_ * 0.4, lvt_5_1_), Math.copySign(lvt_7_1_ * lvt_7_1_ * 0.4, lvt_7_1_)));
            } else {
                this.f_21345_.m_25374_(Goal.Flag.MOVE);
                float lvt_3_2_ = 2.0f;
                try {
                    Vec3 lvt_4_1_ = new Vec3(lvt_1_1_.m_20185_() - this.m_20185_(), lvt_1_1_.m_20186_() - this.m_20186_(), lvt_1_1_.m_20189_() - this.m_20189_()).m_82541_().m_82490_((double)Math.max(lvt_2_1_ - 2.0f, 0.0f));
                    this.m_21573_().m_26519_(this.m_20185_() + lvt_4_1_.f_82479_, this.m_20186_() + lvt_4_1_.f_82480_, this.m_20189_() + lvt_4_1_.f_82481_, this.m_5823_());
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    public boolean forcedSit() {
        return (Boolean)this.f_19804_.m_135370_(FORCED_SIT);
    }

    public boolean isRoger() {
        String s = ChatFormatting.m_126649_((String)this.m_7755_().getString());
        return s != null && s.equalsIgnoreCase("roger");
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.kangarooSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.KANGAROO_IDLE.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.KANGAROO_IDLE.get();
    }

    private void initKangarooInventory() {
        SimpleContainer animalchest = this.kangarooInventory;
        this.kangarooInventory = new SimpleContainer(9){

            public void m_5785_(Player player) {
                EntityKangaroo.this.f_19804_.m_135381_(POUCH_TICK, (Object)10);
                EntityKangaroo.this.resetKangarooSlots();
            }

            public boolean m_6542_(Player player) {
                return EntityKangaroo.this.m_6084_() && !EntityKangaroo.this.f_19817_;
            }
        };
        this.kangarooInventory.m_19164_((ContainerListener)this);
        if (animalchest != null) {
            int i = Math.min(animalchest.m_6643_(), this.kangarooInventory.m_6643_());
            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = animalchest.m_8020_(j);
                if (itemstack.m_41619_()) continue;
                this.kangarooInventory.m_6836_(j, itemstack.m_41777_());
            }
            this.resetKangarooSlots();
        }
    }

    protected void m_5907_() {
        super.m_5907_();
        for (int i = 0; i < this.kangarooInventory.m_6643_(); ++i) {
            this.m_19983_(this.kangarooInventory.m_8020_(i));
        }
        this.kangarooInventory.m_6211_();
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        Item item = itemstack.m_41720_();
        InteractionResult type = super.m_6071_(player, hand);
        if (!this.m_21824_() && itemstack.m_204117_(AMTagRegistry.KANGAROO_TAMEABLES)) {
            this.m_142075_(player, hand, itemstack);
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_11976_, this.m_6121_(), this.m_6100_());
            ++this.carrotFeedings;
            if (this.carrotFeedings > 10 && this.m_217043_().m_188503_(2) == 0 || this.carrotFeedings > 15) {
                this.m_21828_(player);
                this.m_9236_().m_7605_((Entity)this, (byte)7);
            } else {
                this.m_9236_().m_7605_((Entity)this, (byte)6);
            }
            return InteractionResult.SUCCESS;
        }
        if (this.m_21824_() && this.m_21223_() < this.m_21233_() && item.m_41472_() && item.m_41473_() != null && !item.m_41473_().m_38746_()) {
            this.m_142075_(player, hand, itemstack);
            this.m_146850_(GameEvent.f_157806_);
            this.m_5496_(SoundEvents.f_11976_, this.m_6121_(), this.m_6100_());
            this.m_5634_(item.m_41473_().m_38744_());
            return InteractionResult.SUCCESS;
        }
        InteractionResult interactionresult = itemstack.m_41647_(player, (LivingEntity)this, hand);
        if (interactionresult != InteractionResult.SUCCESS && type != InteractionResult.SUCCESS && this.m_21824_() && this.m_21830_((LivingEntity)player) && !this.m_6898_(itemstack)) {
            boolean sit;
            if (player.m_6144_()) {
                if (!this.m_6162_()) {
                    this.openGUI(player);
                    this.m_20153_();
                    this.f_19804_.m_135381_(POUCH_TICK, (Object)-1);
                }
                return InteractionResult.SUCCESS;
            }
            this.setCommand(this.getCommand() + 1);
            if (this.getCommand() == 3) {
                this.setCommand(0);
            }
            player.m_5661_((Component)Component.m_237110_((String)("entity.alexsmobs.all.command_" + this.getCommand()), (Object[])new Object[]{this.m_7755_()}), true);
            boolean bl = sit = this.getCommand() == 2;
            if (sit) {
                this.f_19804_.m_135381_(FORCED_SIT, (Object)true);
                this.m_21839_(true);
                return InteractionResult.SUCCESS;
            }
            this.f_19804_.m_135381_(FORCED_SIT, (Object)false);
            this.maxSitTime = 0;
            this.m_21839_(false);
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128379_("KangarooSitting", this.isSitting());
        compound.m_128379_("KangarooSittingForced", this.forcedSit());
        compound.m_128379_("Standing", this.isStanding());
        compound.m_128405_("Command", this.getCommand());
        compound.m_128405_("HelmetInvIndex", ((Integer)this.f_19804_.m_135370_(HELMET_INDEX)).intValue());
        compound.m_128405_("SwordInvIndex", ((Integer)this.f_19804_.m_135370_(SWORD_INDEX)).intValue());
        compound.m_128405_("ChestInvIndex", ((Integer)this.f_19804_.m_135370_(CHEST_INDEX)).intValue());
        if (this.kangarooInventory != null) {
            ListTag nbttaglist = new ListTag();
            for (int i = 0; i < this.kangarooInventory.m_6643_(); ++i) {
                ItemStack itemstack = this.kangarooInventory.m_8020_(i);
                if (itemstack.m_41619_()) continue;
                CompoundTag CompoundNBT = new CompoundTag();
                CompoundNBT.m_128344_("Slot", (byte)i);
                itemstack.m_41739_(CompoundNBT);
                nbttaglist.add((Object)CompoundNBT);
            }
            compound.m_128365_("Items", (Tag)nbttaglist);
        }
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        this.m_21839_(compound.m_128471_("KangarooSitting"));
        this.f_19804_.m_135381_(FORCED_SIT, (Object)compound.m_128471_("KangarooSittingForced"));
        this.setStanding(compound.m_128471_("Standing"));
        this.setCommand(compound.m_128451_("Command"));
        this.f_19804_.m_135381_(HELMET_INDEX, (Object)compound.m_128451_("HelmetInvIndex"));
        this.f_19804_.m_135381_(SWORD_INDEX, (Object)compound.m_128451_("SwordInvIndex"));
        this.f_19804_.m_135381_(CHEST_INDEX, (Object)compound.m_128451_("ChestInvIndex"));
        if (this.kangarooInventory != null) {
            ListTag nbttaglist = compound.m_128437_("Items", 10);
            this.initKangarooInventory();
            for (int i = 0; i < nbttaglist.size(); ++i) {
                CompoundTag CompoundNBT = nbttaglist.m_128728_(i);
                int j = CompoundNBT.m_128445_("Slot") & 0xFF;
                this.kangarooInventory.m_6836_(j, ItemStack.m_41712_((CompoundTag)CompoundNBT));
            }
        } else {
            ListTag nbttaglist = compound.m_128437_("Items", 10);
            this.initKangarooInventory();
            for (int i = 0; i < nbttaglist.size(); ++i) {
                CompoundTag CompoundNBT = nbttaglist.m_128728_(i);
                int j = CompoundNBT.m_128445_("Slot") & 0xFF;
                this.initKangarooInventory();
                this.kangarooInventory.m_6836_(j, ItemStack.m_41712_((CompoundTag)CompoundNBT));
            }
        }
        this.resetKangarooSlots();
    }

    public void openGUI(Player playerEntity) {
        if (!this.m_9236_().f_46443_ && !this.m_20363_((Entity)playerEntity)) {
            NetworkHooks.openScreen((ServerPlayer)((ServerPlayer)playerEntity), (MenuProvider)new MenuProvider(){

                public AbstractContainerMenu m_7208_(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
                    return new DispenserMenu(p_createMenu_1_, p_createMenu_2_, (Container)EntityKangaroo.this.kangarooInventory);
                }

                public Component m_5446_() {
                    return Component.m_237115_((String)"entity.alexsmobs.kangaroo.pouch");
                }
            });
        }
    }

    public boolean isSitting() {
        return (Boolean)this.f_19804_.m_135370_(SITTING);
    }

    public void m_21839_(boolean sit) {
        this.f_19804_.m_135381_(SITTING, (Object)sit);
    }

    public boolean isStanding() {
        return (Boolean)this.f_19804_.m_135370_(STANDING);
    }

    public void setStanding(boolean standing) {
        this.f_19804_.m_135381_(STANDING, (Object)standing);
    }

    public int getCommand() {
        return (Integer)this.f_19804_.m_135370_(COMMAND);
    }

    public void setCommand(int command) {
        this.f_19804_.m_135381_(COMMAND, (Object)command);
    }

    public int getVisualFlag() {
        return (Integer)this.f_19804_.m_135370_(VISUAL_FLAG);
    }

    public void setVisualFlag(int visualFlag) {
        this.f_19804_.m_135381_(VISUAL_FLAG, (Object)visualFlag);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(STANDING, (Object)false);
        this.f_19804_.m_135372_(SITTING, (Object)false);
        this.f_19804_.m_135372_(FORCED_SIT, (Object)false);
        this.f_19804_.m_135372_(COMMAND, (Object)0);
        this.f_19804_.m_135372_(VISUAL_FLAG, (Object)0);
        this.f_19804_.m_135372_(POUCH_TICK, (Object)0);
        this.f_19804_.m_135372_(CHEST_INDEX, (Object)-1);
        this.f_19804_.m_135372_(HELMET_INDEX, (Object)-1);
        this.f_19804_.m_135372_(SWORD_INDEX, (Object)-1);
    }

    protected PathNavigation m_6037_(Level worldIn) {
        return new GroundPathNavigatorWide((Mob)this, worldIn, 2.0f);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new SitWhenOrderedToGoal((TamableAnimal)this));
        this.f_21345_.m_25352_(1, (Goal)new KangarooAIMelee(this, 1.2, false));
        this.f_21345_.m_25352_(2, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(2, (Goal)new TameableAIFollowOwner(this, 1.2, 5.0f, 2.0f, false));
        this.f_21345_.m_25352_(3, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(4, (Goal)new AnimalAIRideParent((Animal)this, 1.25));
        this.f_21345_.m_25352_(4, (Goal)new TemptGoal((PathfinderMob)this, 1.2, Ingredient.m_204132_(AMTagRegistry.KANGAROO_TAMEABLES), false));
        this.f_21345_.m_25352_(5, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 110, 1.2, 10, 7));
        this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 10.0f));
        this.f_21345_.m_25352_(7, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new OwnerHurtByTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(2, (Goal)new OwnerHurtTargetGoal((TamableAnimal)this));
        this.f_21346_.m_25352_(3, (Goal)new AnimalAIHurtByTargetNotBaby((Animal)this, new Class[0]));
    }

    protected boolean m_7310_(Entity passenger) {
        return super.m_7310_(passenger) && (Integer)this.f_19804_.m_135370_(POUCH_TICK) == 0;
    }

    public double m_6048_() {
        return (double)this.m_20206_() * (double)0.35f;
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.updateClientInventory();
    }

    public void m_8119_() {
        super.m_8119_();
        boolean moving = this.m_20184_().m_82556_() > 0.03;
        int pouchTick = (Integer)this.f_19804_.m_135370_(POUCH_TICK);
        this.prevTotalMovingProgress = this.totalMovingProgress;
        this.prevPouchProgress = this.pouchProgress;
        this.prevSitProgress = this.sitProgress;
        this.prevStandProgress = this.standProgress;
        if (this.isSitting()) {
            if (this.sitProgress < 5.0f) {
                this.sitProgress += 1.0f;
            }
        } else if (this.sitProgress > 0.0f) {
            this.sitProgress -= 1.0f;
        }
        if (this.eatCooldown > 0) {
            --this.eatCooldown;
        }
        if (this.isStanding()) {
            if (this.standProgress < 5.0f) {
                this.standProgress += 1.0f;
            }
        } else if (this.standProgress > 0.0f) {
            this.standProgress -= 1.0f;
        }
        if (moving) {
            if (this.totalMovingProgress < 5.0f) {
                this.totalMovingProgress += 1.0f;
            }
        } else if (this.totalMovingProgress > 0.0f) {
            this.totalMovingProgress -= 1.0f;
        }
        if (pouchTick != 0 && this.pouchProgress < 5.0f) {
            this.pouchProgress += 1.0f;
        }
        if (pouchTick == 0 && this.pouchProgress > 0.0f) {
            this.pouchProgress -= 1.0f;
        }
        if (pouchTick > 0) {
            this.f_19804_.m_135381_(POUCH_TICK, (Object)(pouchTick - 1));
        }
        if (this.isStanding() && ++this.standingTime > this.maxStandTime) {
            this.setStanding(false);
            this.standingTime = 0;
            this.maxStandTime = 75 + this.f_19796_.m_188503_(50);
        }
        if (this.isSitting() && !this.forcedSit() && ++this.sittingTime > this.maxSitTime) {
            this.m_21839_(false);
            this.sittingTime = 0;
            this.maxSitTime = 75 + this.f_19796_.m_188503_(50);
        }
        if (!(this.m_9236_().f_46443_ || this.getAnimation() != NO_ANIMATION || this.getCommand() == 1 || this.isStanding() || this.isSitting() || this.f_19796_.m_188503_(1500) != 0)) {
            this.maxSitTime = 500 + this.f_19796_.m_188503_(350);
            this.m_21839_(true);
        }
        if (!this.forcedSit() && this.isSitting() && (this.m_5448_() != null || this.isStanding())) {
            this.m_21839_(false);
        }
        if (this.getAnimation() == NO_ANIMATION && !this.isStanding() && !this.isSitting() && this.f_19796_.m_188503_(1500) == 0) {
            this.maxStandTime = 75 + this.f_19796_.m_188503_(50);
            this.setStanding(true);
        }
        if (this.forcedSit() && !this.m_20160_() && this.m_21824_()) {
            this.m_21839_(true);
        }
        if (!this.m_9236_().f_46443_) {
            if (this.f_19797_ == 1) {
                this.updateClientInventory();
            }
            if (!moving && this.getAnimation() == NO_ANIMATION && !this.isSitting() && !this.isStanding() && (this.m_217043_().m_188503_(180) == 0 || this.m_21223_() < this.m_21233_() && this.m_217043_().m_188503_(40) == 0) && this.m_9236_().m_8055_(this.m_20183_().m_7495_()).m_60713_(Blocks.f_50440_)) {
                this.setAnimation(ANIMATION_EAT_GRASS);
            }
            if (this.getAnimation() == ANIMATION_EAT_GRASS && this.getAnimationTick() == 20 && this.m_21223_() < this.m_21233_() && this.m_9236_().m_8055_(this.m_20183_().m_7495_()).m_60713_(Blocks.f_50440_)) {
                this.m_5634_(6.0f);
                this.m_9236_().m_46796_(2001, this.m_20183_().m_7495_(), Block.m_49956_((BlockState)Blocks.f_50440_.m_49966_()));
                this.m_9236_().m_7731_(this.m_20183_().m_7495_(), Blocks.f_50493_.m_49966_(), 2);
            }
            if (this.m_21223_() < this.m_21233_() && this.m_21824_() && this.eatCooldown == 0) {
                this.eatCooldown = 20 + this.f_19796_.m_188503_(40);
                if (!this.kangarooInventory.m_7983_()) {
                    ItemStack foodStack = ItemStack.f_41583_;
                    for (int i = 0; i < this.kangarooInventory.m_6643_(); ++i) {
                        ItemStack stack = this.kangarooInventory.m_8020_(i);
                        if (!stack.m_41720_().m_41472_() || stack.m_41720_().m_41473_() == null || stack.m_41720_().m_41473_().m_38746_()) continue;
                        foodStack = stack;
                    }
                    if (!foodStack.m_41619_() && foodStack.m_41720_().m_41473_() != null) {
                        AlexsMobs.sendMSGToAll(new MessageKangarooEat(this.m_19879_(), foodStack));
                        this.m_5634_(foodStack.m_41720_().m_41473_().m_38744_() * 2);
                        foodStack.m_41774_(1);
                        this.m_146850_(GameEvent.f_157806_);
                        this.m_5496_(SoundEvents.f_11912_, this.m_6121_(), this.m_6100_());
                    }
                }
            }
        }
        if (this.jumpTicks < this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.m_6862_(false);
        }
        LivingEntity attackTarget = this.m_5448_();
        if (attackTarget != null && this.m_142582_((Entity)attackTarget)) {
            if (this.m_20270_((Entity)attackTarget) < attackTarget.m_20205_() + this.m_20205_() + 1.0f) {
                if (this.getAnimation() == ANIMATION_KICK && this.getAnimationTick() == 8) {
                    attackTarget.m_147240_((double)1.3f, (double)Mth.m_14031_((float)(this.m_146908_() * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(this.m_146908_() * ((float)Math.PI / 180)))));
                    this.m_7327_((Entity)this.m_5448_());
                }
                if (this.getAnimation() == ANIMATION_PUNCH_L && this.getAnimationTick() == 6) {
                    float rot = this.m_146908_() + 90.0f;
                    attackTarget.m_147240_((double)0.85f, (double)Mth.m_14031_((float)(rot * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(rot * ((float)Math.PI / 180)))));
                    this.m_7327_((Entity)this.m_5448_());
                }
                if (this.getAnimation() == ANIMATION_PUNCH_R && this.getAnimationTick() == 6) {
                    float rot = this.m_146908_() - 90.0f;
                    attackTarget.m_147240_((double)0.85f, (double)Mth.m_14031_((float)(rot * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(rot * ((float)Math.PI / 180)))));
                    this.m_7327_((Entity)this.m_5448_());
                }
            }
            this.m_21391_((Entity)attackTarget, 360.0f, 360.0f);
        }
        if (this.m_6162_() && attackTarget != null) {
            this.m_6710_(null);
        }
        if (this.m_20160_()) {
            this.f_19804_.m_135381_(POUCH_TICK, (Object)10);
            this.setStanding(true);
            this.maxStandTime = 25;
        }
        if (this.m_20159_()) {
            if (this.m_6162_() && this.m_20202_() instanceof EntityKangaroo) {
                EntityKangaroo mount = (EntityKangaroo)this.m_20202_();
                this.m_146922_(mount.f_20883_);
                this.f_20885_ = mount.f_20883_;
                this.f_20883_ = mount.f_20883_;
            }
            if (this.m_20202_() instanceof EntityKangaroo && !this.m_6162_()) {
                this.m_6038_();
            }
        }
        if (this.clientArmorCooldown > 0) {
            --this.clientArmorCooldown;
        }
        if (this.f_19797_ > 5 && !this.m_9236_().f_46443_ && this.clientArmorCooldown == 0 && this.m_21824_()) {
            this.updateClientInventory();
            this.clientArmorCooldown = 20;
        }
        AnimationHandler.INSTANCE.updateAnimations((Entity)this);
    }

    public boolean m_7327_(Entity entityIn) {
        boolean prev = super.m_7327_(entityIn);
        if (prev && !this.m_21205_().m_41619_()) {
            this.damageItem(this.m_21205_());
        }
        return prev;
    }

    public boolean m_6469_(DamageSource src, float amount) {
        boolean prev = super.m_6469_(src, amount);
        if (prev) {
            if (!this.m_6844_(EquipmentSlot.HEAD).m_41619_()) {
                this.damageItem(this.m_6844_(EquipmentSlot.HEAD));
            }
            if (!this.m_6844_(EquipmentSlot.CHEST).m_41619_()) {
                this.damageItem(this.m_6844_(EquipmentSlot.CHEST));
            }
        }
        return prev;
    }

    private void damageItem(ItemStack stack) {
        if (stack != null) {
            stack.m_220157_(1, this.m_217043_(), null);
            if (stack.m_41773_() <= 0) {
                stack.m_41774_(1);
            }
        }
    }

    public boolean m_6673_(DamageSource source) {
        return super.m_6673_(source) || source.m_276093_(DamageTypes.f_268612_);
    }

    public boolean m_7307_(Entity entityIn) {
        if (this.m_21824_()) {
            LivingEntity livingentity = this.m_269323_();
            if (entityIn == livingentity) {
                return true;
            }
            if (entityIn instanceof TamableAnimal) {
                return ((TamableAnimal)entityIn).m_21830_(livingentity);
            }
            if (livingentity != null) {
                return livingentity.m_7307_(entityIn);
            }
        }
        return super.m_7307_(entityIn);
    }

    public MoveControl m_21566_() {
        return this.f_21342_;
    }

    public void m_7023_(Vec3 vec3d) {
        if (this.isSitting() || this.getAnimation() == ANIMATION_EAT_GRASS) {
            if (this.m_21573_().m_26570_() != null) {
                this.m_21573_().m_26573_();
            }
            vec3d = Vec3.f_82478_;
        }
        super.m_7023_(vec3d);
    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    public PathNavigation m_21573_() {
        return this.f_21344_;
    }

    @Nullable
    public Entity m_275832_() {
        return this.m_20202_() instanceof EntityKangaroo ? null : super.m_275832_();
    }

    private void enableJumpControl() {
        if (this.f_21343_ instanceof JumpHelperController) {
            ((JumpHelperController)this.f_21343_).setCanJump(true);
        }
    }

    private void disableJumpControl() {
        if (this.f_21343_ instanceof JumpHelperController) {
            ((JumpHelperController)this.f_21343_).setCanJump(false);
        }
    }

    private void updateMoveTypeDuration() {
        this.currentMoveTypeDuration = this.f_21342_.m_24999_() < 2.0 ? 2 : 1;
    }

    private void calculateRotationYaw(double x, double z) {
        this.m_146922_((float)(Mth.m_14136_((double)(z - this.m_20189_()), (double)(x - this.m_20185_())) * 57.2957763671875) - 90.0f);
    }

    public boolean m_5843_() {
        return false;
    }

    public void m_8024_() {
        super.m_8024_();
        if (this.currentMoveTypeDuration > 0) {
            --this.currentMoveTypeDuration;
        }
        if (this.m_20096_()) {
            LivingEntity livingentity;
            if (!this.wasOnGround) {
                this.m_6862_(false);
                this.checkLandingDelay();
            }
            if (this.currentMoveTypeDuration == 0 && (livingentity = this.m_5448_()) != null && this.m_20280_((Entity)livingentity) < 16.0) {
                this.calculateRotationYaw(livingentity.m_20185_(), livingentity.m_20189_());
                this.f_21342_.m_6849_(livingentity.m_20185_(), livingentity.m_20186_(), livingentity.m_20189_(), this.f_21342_.m_24999_());
                this.startJumping();
                this.wasOnGround = true;
            }
            if (this.f_21343_ instanceof JumpHelperController) {
                JumpHelperController rabbitController = (JumpHelperController)this.f_21343_;
                if (!rabbitController.getIsJumping()) {
                    if (this.f_21342_.m_24995_() && this.currentMoveTypeDuration == 0) {
                        Path path = this.f_21344_.m_26570_();
                        Vec3 vector3d = new Vec3(this.f_21342_.m_25000_(), this.f_21342_.m_25001_(), this.f_21342_.m_25002_());
                        if (path != null && !path.m_77392_()) {
                            vector3d = path.m_77380_((Entity)this);
                        }
                        this.startJumping();
                    }
                } else if (!rabbitController.canJump()) {
                    this.enableJumpControl();
                }
            }
        }
        this.wasOnGround = this.m_20096_();
    }

    public float getJumpCompletion(float partialTicks) {
        return this.jumpDuration == 0 ? 0.0f : ((float)this.jumpTicks + partialTicks) / (float)this.jumpDuration;
    }

    public int getAnimationTick() {
        return this.animationTick;
    }

    public void setAnimationTick(int i) {
        this.animationTick = i;
    }

    public Animation getAnimation() {
        return this.currentAnimation;
    }

    public void setAnimation(Animation animation) {
        this.currentAnimation = animation;
        if (animation == ANIMATION_KICK) {
            this.setStanding(true);
            this.maxStandTime = 30;
        } else if (animation == ANIMATION_PUNCH_R) {
            this.setStanding(true);
            this.maxStandTime = 15;
        } else if (animation == ANIMATION_PUNCH_L) {
            this.setStanding(true);
            this.maxStandTime = 15;
        }
    }

    public Animation[] getAnimations() {
        return new Animation[]{ANIMATION_EAT_GRASS, ANIMATION_KICK, ANIMATION_PUNCH_L, ANIMATION_PUNCH_R};
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return (AgeableMob)((EntityType)AMEntityRegistry.KANGAROO.get()).m_20615_((Level)serverWorld);
    }

    public void setMovementSpeed(double newSpeed) {
        this.m_21573_().m_26517_(newSpeed);
        this.f_21342_.m_6849_(this.f_21342_.m_25000_(), this.f_21342_.m_25001_(), this.f_21342_.m_25002_(), newSpeed);
    }

    protected float m_6118_() {
        return 0.5f;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void m_7840_(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public boolean m_6898_(ItemStack stack) {
        Item item = stack.m_41720_();
        return stack.m_204117_(AMTagRegistry.KANGAROO_BREEDABLES);
    }

    public void resetKangarooSlots() {
        if (!this.m_9236_().f_46443_) {
            int swordIndex = -1;
            double swordDamage = 0.0;
            int helmetIndex = -1;
            double helmetArmor = 0.0;
            int chestplateIndex = -1;
            double chestplateArmor = 0.0;
            for (int i = 0; i < this.kangarooInventory.m_6643_(); ++i) {
                double prot;
                ItemStack stack = this.kangarooInventory.m_8020_(i);
                if (stack.m_41619_()) continue;
                double dmg = this.getDamageForItem(stack);
                if (dmg > 0.0 && dmg > swordDamage) {
                    swordDamage = dmg;
                    swordIndex = i;
                }
                if (stack.m_41720_().canEquip(stack, EquipmentSlot.HEAD, (Entity)this) && !this.m_6162_() && helmetIndex == -1) {
                    helmetIndex = i;
                }
                if (!(stack.m_41720_() instanceof ArmorItem) || this.m_6162_()) continue;
                ArmorItem armorItem = (ArmorItem)stack.m_41720_();
                if (armorItem.m_40402_() == EquipmentSlot.HEAD && (prot = this.getProtectionForItem(stack, EquipmentSlot.HEAD)) > 0.0 && prot > helmetArmor) {
                    helmetArmor = prot;
                    helmetIndex = i;
                }
                if (armorItem.m_40402_() != EquipmentSlot.CHEST || !((prot = this.getProtectionForItem(stack, EquipmentSlot.CHEST)) > 0.0) || !(prot > chestplateArmor)) continue;
                chestplateArmor = prot;
                chestplateIndex = i;
            }
            this.f_19804_.m_135381_(SWORD_INDEX, (Object)swordIndex);
            this.f_19804_.m_135381_(CHEST_INDEX, (Object)chestplateIndex);
            this.f_19804_.m_135381_(HELMET_INDEX, (Object)helmetIndex);
            this.updateClientInventory();
        }
    }

    private void updateClientInventory() {
        if (!this.m_9236_().f_46443_) {
            for (int i = 0; i < 9; ++i) {
                AlexsMobs.sendMSGToAll(new MessageKangarooInventorySync(this.m_19879_(), i, this.kangarooInventory.m_8020_(i)));
            }
        }
    }

    @Nullable
    private Map<EquipmentSlot, ItemStack> m_21319_() {
        EnumMap map = null;
        block4: for (EquipmentSlot equipmentslottype : EquipmentSlot.values()) {
            ItemStack itemstack;
            switch (equipmentslottype.m_20743_()) {
                case HAND: {
                    itemstack = this.getItemInHand(equipmentslottype);
                    break;
                }
                case ARMOR: {
                    itemstack = this.getArmorInSlot(equipmentslottype);
                    break;
                }
                default: {
                    continue block4;
                }
            }
            ItemStack itemstack1 = this.m_6844_(equipmentslottype);
            if (ItemStack.m_41728_((ItemStack)itemstack1, (ItemStack)itemstack)) continue;
            MinecraftForge.EVENT_BUS.post((Event)new LivingEquipmentChangeEvent((LivingEntity)this, equipmentslottype, itemstack, itemstack1));
            if (map == null) {
                map = Maps.newEnumMap(EquipmentSlot.class);
            }
            map.put(equipmentslottype, itemstack1);
            if (!itemstack.m_41619_()) {
                this.m_21204_().m_22161_(itemstack.m_41638_(equipmentslottype));
            }
            if (itemstack1.m_41619_()) continue;
            this.m_21204_().m_22178_(itemstack1.m_41638_(equipmentslottype));
        }
        return map;
    }

    public ItemStack m_6844_(EquipmentSlot slotIn) {
        return switch (slotIn.m_20743_()) {
            case EquipmentSlot.Type.HAND -> this.getItemInHand(slotIn);
            case EquipmentSlot.Type.ARMOR -> this.getArmorInSlot(slotIn);
            default -> ItemStack.f_41583_;
        };
    }

    private ItemStack getArmorInSlot(EquipmentSlot slot) {
        int helmIndex = (Integer)this.f_19804_.m_135370_(HELMET_INDEX);
        int chestIndex = (Integer)this.f_19804_.m_135370_(CHEST_INDEX);
        return slot == EquipmentSlot.HEAD && helmIndex >= 0 ? this.kangarooInventory.m_8020_(helmIndex) : (slot == EquipmentSlot.CHEST && chestIndex >= 0 ? this.kangarooInventory.m_8020_(chestIndex) : ItemStack.f_41583_);
    }

    private ItemStack getItemInHand(EquipmentSlot slot) {
        int index = (Integer)this.f_19804_.m_135370_(SWORD_INDEX);
        return slot == EquipmentSlot.MAINHAND && index >= 0 ? this.kangarooInventory.m_8020_(index) : ItemStack.f_41583_;
    }

    public double getDamageForItem(ItemStack itemStack) {
        Multimap map = itemStack.m_41638_(EquipmentSlot.MAINHAND);
        if (!map.isEmpty()) {
            double d = 0.0;
            for (AttributeModifier mod : map.get((Object)Attributes.f_22281_)) {
                d += mod.m_22218_();
            }
            return d;
        }
        return 0.0;
    }

    public double getProtectionForItem(ItemStack itemStack, EquipmentSlot type) {
        Multimap map = itemStack.m_41638_(type);
        if (!map.isEmpty()) {
            double d = 0.0;
            for (AttributeModifier mod : map.get((Object)Attributes.f_22284_)) {
                d += mod.m_22218_();
            }
            return d;
        }
        return 0.0;
    }

    protected void m_6135_() {
        double d1;
        super.m_6135_();
        double d0 = this.f_21342_.m_24999_();
        if (!(d0 > 0.0) || (d1 = this.m_20184_().m_165925_()) < 0.01) {
            // empty if block
        }
        if (!this.m_9236_().f_46443_) {
            this.m_9236_().m_7605_((Entity)this, (byte)1);
        }
    }

    public boolean hasJumper() {
        return this.f_21343_ instanceof JumpHelperController;
    }

    public void startJumping() {
        if (!this.isSitting() || this.m_20069_()) {
            this.m_6862_(true);
            this.jumpDuration = 16;
            this.jumpTicks = 0;
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 1) {
            this.m_20076_();
            this.jumpDuration = 16;
            this.jumpTicks = 0;
        } else {
            super.m_7822_(id);
        }
    }

    @Override
    public boolean shouldFollow() {
        return this.getCommand() == 1;
    }

    public void m_5757_(Container iInventory) {
        this.resetKangarooSlots();
    }

    public static class JumpHelperController
    extends JumpControl {
        private final EntityKangaroo kangaroo;
        private boolean canJump;

        public JumpHelperController(EntityKangaroo kangaroo) {
            super((Mob)kangaroo);
            this.kangaroo = kangaroo;
        }

        public boolean getIsJumping() {
            return this.f_24897_;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn) {
            this.canJump = canJumpIn;
        }

        public void m_8124_() {
            if (this.f_24897_) {
                this.kangaroo.startJumping();
                this.f_24897_ = false;
            }
        }
    }

    static class MoveHelperController
    extends MoveControl {
        private final EntityKangaroo kangaroo;
        private double nextJumpSpeed;

        public MoveHelperController(EntityKangaroo kangaroo) {
            super((Mob)kangaroo);
            this.kangaroo = kangaroo;
        }

        public void m_8126_() {
            if (this.kangaroo.hasJumper() && this.kangaroo.m_20096_() && !this.kangaroo.f_20899_ && !((JumpHelperController)this.kangaroo.f_21343_).getIsJumping()) {
                this.kangaroo.setMovementSpeed(0.0);
            } else if (this.m_24995_()) {
                this.kangaroo.setMovementSpeed(this.nextJumpSpeed);
            }
            super.m_8126_();
        }

        public void m_6849_(double x, double y, double z, double speedIn) {
            if (this.kangaroo.m_20069_()) {
                speedIn = 1.5;
            }
            super.m_6849_(x, y, z, speedIn);
            if (speedIn > 0.0) {
                this.nextJumpSpeed = speedIn;
            }
        }
    }
}

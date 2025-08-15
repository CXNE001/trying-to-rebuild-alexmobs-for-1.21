/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MovementEmission
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PlayerRideableJumping
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.enchantment.AMEnchantmentRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import javax.annotation.Nullable;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntityStraddleboard
extends Entity
implements PlayerRideableJumping {
    private static final EntityDataAccessor<ItemStack> ITEMSTACK = SynchedEntityData.m_135353_(EntityStraddleboard.class, (EntityDataSerializer)EntityDataSerializers.f_135033_);
    private static final EntityDataAccessor<Integer> TIME_SINCE_HIT = SynchedEntityData.m_135353_(EntityStraddleboard.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.m_135353_(EntityStraddleboard.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> DEFAULT_COLOR = SynchedEntityData.m_135353_(EntityStraddleboard.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> BOARD_ROT = SynchedEntityData.m_135353_(EntityStraddleboard.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Boolean> REMOVE_SOON = SynchedEntityData.m_135353_(EntityStraddleboard.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public float prevBoardRot = 0.0f;
    private boolean rocking;
    private float rockingIntensity;
    private float rockingAngle;
    private float prevRockingAngle;
    private int extinguishTimer = 0;
    private int jumpFor = 0;
    private int lSteps;
    private double lx;
    private double ly;
    private double lz;
    private double lyr;
    private double lxr;
    private double lxd;
    private double lyd;
    private double lzd;
    private int rideForTicks = 0;
    private float boardForwards = 0.0f;
    private int removeIn;
    private Player returnToPlayer = null;

    public EntityStraddleboard(EntityType<?> p_i48580_1_, Level p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
        this.f_19850_ = true;
    }

    public EntityStraddleboard(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this((EntityType)AMEntityRegistry.STRADDLEBOARD.get(), world);
    }

    public EntityStraddleboard(Level worldIn, double x, double y, double z) {
        this((EntityType)AMEntityRegistry.STRADDLEBOARD.get(), worldIn);
        this.m_6034_(x, y, z);
        this.m_20256_(Vec3.f_82478_);
        this.f_19854_ = x;
        this.f_19855_ = y;
        this.f_19856_ = z;
    }

    public static boolean canVehicleCollide(Entity p_242378_0_, Entity entity) {
        return (entity.m_5829_() || entity.m_6094_()) && !p_242378_0_.m_20365_(entity);
    }

    protected float m_6380_(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.f_20378_;
    }

    protected void m_8097_() {
        this.f_19804_.m_135372_(TIME_SINCE_HIT, (Object)0);
        this.f_19804_.m_135372_(ITEMSTACK, (Object)new ItemStack((ItemLike)AMItemRegistry.STRADDLEBOARD.get()));
        this.f_19804_.m_135372_(DEFAULT_COLOR, (Object)true);
        this.f_19804_.m_135372_(COLOR, (Object)0);
        this.f_19804_.m_135372_(BOARD_ROT, (Object)Float.valueOf(0.0f));
        this.f_19804_.m_135372_(REMOVE_SOON, (Object)false);
    }

    public boolean shouldRiderSit() {
        return false;
    }

    public boolean m_7337_(Entity entity) {
        return EntityStraddleboard.canVehicleCollide(this, entity);
    }

    protected Vec3 m_7643_(Direction.Axis axis, BlockUtil.FoundRectangle result) {
        return LivingEntity.m_21289_((Vec3)super.m_7643_(axis, result));
    }

    public double m_6048_() {
        return 0.5;
    }

    public float getBoardRot() {
        return ((Float)this.f_19804_.m_135370_(BOARD_ROT)).floatValue();
    }

    public void setBoardRot(float f) {
        this.f_19804_.m_135381_(BOARD_ROT, (Object)Float.valueOf(f));
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_6673_(source)) {
            return false;
        }
        if (!this.m_9236_().f_46443_ && !this.m_213877_()) {
            this.f_19804_.m_135381_(REMOVE_SOON, (Object)true);
            return true;
        }
        return true;
    }

    private ItemStack getItemBoard() {
        return this.getItemStack();
    }

    public void m_7334_(Entity entityIn) {
        if (entityIn instanceof EntityStraddleboard) {
            if (entityIn.m_20191_().f_82289_ < this.m_20191_().f_82292_) {
                super.m_7334_(entityIn);
            }
        } else if (entityIn.m_20191_().f_82289_ <= this.m_20191_().f_82289_) {
            super.m_7334_(entityIn);
        }
    }

    public boolean isRemoveLogic() {
        return (Boolean)this.f_19804_.m_135370_(REMOVE_SOON) != false || this.m_213877_();
    }

    public boolean m_5829_() {
        return !this.isRemoveLogic();
    }

    public boolean m_6094_() {
        return !this.isRemoveLogic();
    }

    public boolean m_6087_() {
        return !this.isRemoveLogic();
    }

    public boolean m_142391_() {
        return !this.isRemoveLogic();
    }

    public boolean m_6097_() {
        return !this.isRemoveLogic();
    }

    public boolean isDefaultColor() {
        return (Boolean)this.f_19804_.m_135370_(DEFAULT_COLOR);
    }

    public void setDefaultColor(boolean bar) {
        this.f_19804_.m_135381_(DEFAULT_COLOR, (Object)bar);
    }

    public int getColor() {
        if (this.isDefaultColor()) {
            return 11387863;
        }
        return (Integer)this.f_19804_.m_135370_(COLOR);
    }

    public void setColor(int index) {
        this.f_19804_.m_135381_(COLOR, (Object)index);
    }

    public void m_8119_() {
        super.m_8119_();
        float boardRot = this.getBoardRot();
        if (this.jumpFor > 0) {
            --this.jumpFor;
        }
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if (this.extinguishTimer > 0) {
            --this.extinguishTimer;
        }
        if (((Boolean)this.f_19804_.m_135370_(REMOVE_SOON)).booleanValue()) {
            --this.removeIn;
            this.setBoardRot((float)Math.sin((double)((float)this.removeIn * 0.3f) * Math.PI) * 50.0f);
            if (this.removeIn <= 0 && !this.m_9236_().f_46443_) {
                this.removeIn = 0;
                boolean drop = this.getEnchant((Enchantment)AMEnchantmentRegistry.STRADDLE_BOARDRETURN.get()) > 0 ? this.returnToPlayer != null && !this.returnToPlayer.m_36356_(this.getItemBoard()) : true;
                if (drop) {
                    this.m_19983_(this.getItemStack().m_41777_());
                }
                this.m_146870_();
            }
        }
        Player controller = this.getControllingPlayer();
        if (this.m_9236_().f_46443_) {
            if (this.lSteps > 0) {
                double d5 = this.m_20185_() + (this.lx - this.m_20185_()) / (double)this.lSteps;
                double d6 = this.m_20186_() + (this.ly - this.m_20186_()) / (double)this.lSteps;
                double d7 = this.m_20189_() + (this.lz - this.m_20189_()) / (double)this.lSteps;
                this.m_146922_(Mth.m_14177_((float)((float)this.lyr)));
                this.m_146926_(this.m_146909_() + (float)(this.lxr - (double)this.m_146909_()) / (float)this.lSteps);
                --this.lSteps;
                this.m_6034_(d5, d6, d7);
                this.m_19915_(this.m_146908_(), this.m_146909_());
            } else {
                this.m_20090_();
                this.m_19915_(this.m_146908_(), this.m_146909_());
            }
        } else {
            this.m_20101_();
            float slowdown = this.m_20072_() || this.m_20096_() ? 0.05f : 0.98f;
            this.tickMovement();
            this.m_6478_(MoverType.SELF, this.m_20184_());
            this.m_20256_(this.m_20184_().m_82542_((double)slowdown, (double)slowdown, (double)slowdown));
            float f2 = (float)(-((double)((float)this.m_20184_().f_82480_ * 0.5f) * 57.2957763671875));
            this.m_146926_(Mth.m_14148_((float)this.m_146909_(), (float)f2, (float)5.0f));
            if (controller instanceof Player) {
                Player player;
                this.returnToPlayer = player = controller;
                ++this.rideForTicks;
                if (this.f_19797_ % 50 == 0 && this.getEnchant((Enchantment)AMEnchantmentRegistry.STRADDLE_LAVAWAX.get()) > 0) {
                    player.m_7292_(new MobEffectInstance(MobEffects.f_19607_, 100, 0, true, false));
                }
                if (player.m_20094_() > 0 && this.extinguishTimer == 0) {
                    player.m_20095_();
                }
                this.m_146922_(Mth.m_14148_((float)this.m_146908_(), (float)player.m_146908_(), (float)6.0f));
                Vec3 deltaMovement = this.m_20184_();
                if (deltaMovement.f_82480_ > -0.5) {
                    this.f_19789_ = 1.0f;
                }
                float slow = player.f_20902_ < 0.0f ? 0.0f : player.f_20902_ * 0.115f;
                float threshold = 3.0f;
                boolean flag = false;
                float boardRot1 = boardRot;
                if (this.f_19859_ - this.m_146908_() > threshold) {
                    boardRot1 += 10.0f;
                    flag = true;
                }
                if (this.f_19859_ - this.m_146908_() < -threshold) {
                    boardRot1 -= 10.0f;
                    flag = true;
                }
                if (!flag) {
                    if (boardRot1 > 0.0f) {
                        boardRot1 = Math.max(boardRot1 - 5.0f, 0.0f);
                    }
                    if (boardRot1 < 0.0f) {
                        boardRot1 = Math.min(boardRot1 + 5.0f, 0.0f);
                    }
                }
                this.setBoardRot(Mth.m_14148_((float)boardRot, (float)Mth.m_14036_((float)boardRot1, (float)-25.0f, (float)25.0f), (float)5.0f));
                this.boardForwards = slow;
                if (player.m_6144_() || !this.m_6084_() || ((Boolean)this.f_19804_.m_135370_(REMOVE_SOON)).booleanValue()) {
                    this.m_20153_();
                }
                if (player.m_5830_()) {
                    this.m_20153_();
                    this.m_6469_(this.m_269291_().m_269264_(), 100.0f);
                }
            } else {
                this.rideForTicks = 0;
            }
        }
        this.prevBoardRot = boardRot;
    }

    private void tickMovement() {
        this.f_19812_ = true;
        float moveForwards = Math.min(this.boardForwards, 1.0f);
        float yRot = this.m_146908_();
        Vec3 prev = this.m_20184_();
        float gravity = this.isOnLava() ? 0.0f : (this.m_20077_() ? 0.1f : -1.0f);
        float f1 = -Mth.m_14031_((float)(yRot * ((float)Math.PI / 180)));
        float f2 = Mth.m_14089_((float)(yRot * ((float)Math.PI / 180)));
        Vec3 moveVec = new Vec3((double)f1, 0.0, (double)f2).m_82490_((double)moveForwards);
        Vec3 vec31 = prev.m_82490_((double)0.975f).m_82549_(moveVec);
        float jumpGravity = gravity;
        if (this.jumpFor > 0) {
            float jumpRunsOutIn = this.jumpFor < 5 ? (float)this.jumpFor / 5.0f : 1.0f;
            jumpGravity += jumpRunsOutIn + jumpRunsOutIn * 1.0f;
        }
        this.m_20334_(vec31.f_82479_, jumpGravity, vec31.f_82481_);
    }

    private boolean isOnLava() {
        BlockPos ourPos = BlockPos.m_274561_((double)this.m_20185_(), (double)(this.m_20186_() + (double)0.4f), (double)this.m_20189_());
        BlockPos underPos = this.m_20097_();
        return this.m_9236_().m_6425_(underPos).m_205070_(FluidTags.f_13132_) && !this.m_9236_().m_6425_(ourPos).m_205070_(FluidTags.f_13132_);
    }

    public void m_6453_(double x, double y, double z, float yr, float xr, int steps, boolean b) {
        this.lx = x;
        this.ly = y;
        this.lz = z;
        this.lyr = yr;
        this.lxr = xr;
        this.lSteps = steps;
        this.m_20334_(this.lxd, this.lyd, this.lzd);
    }

    public void m_6001_(double lerpX, double lerpY, double lerpZ) {
        this.lxd = lerpX;
        this.lyd = lerpY;
        this.lzd = lerpZ;
        this.m_20334_(this.lxd, this.lyd, this.lzd);
    }

    public double m_20188_() {
        return this.m_20186_() + (double)0.3f;
    }

    @Nullable
    public LivingEntity m_6688_() {
        return this.getControllingPlayer();
    }

    @Nullable
    public boolean m_6109_() {
        return false;
    }

    @Nullable
    public Player getControllingPlayer() {
        for (Entity passenger : this.m_20197_()) {
            if (!(passenger instanceof Player)) continue;
            return (Player)passenger;
        }
        return null;
    }

    protected void m_20348_(Entity passenger) {
        super.m_20348_(passenger);
        if (this.m_6109_() && this.lSteps > 0) {
            this.lSteps = 0;
            this.m_19890_(this.lx, this.ly, this.lz, (float)this.lyr, (float)this.lxr);
        }
    }

    public void m_7350_(EntityDataAccessor<?> entityDataAccessor) {
        super.m_7350_(entityDataAccessor);
        if (REMOVE_SOON.equals(entityDataAccessor)) {
            this.removeIn = 5;
        }
    }

    public InteractionResult m_6096_(Player player, InteractionHand hand) {
        if (player.m_36341_()) {
            return InteractionResult.PASS;
        }
        if (!this.m_9236_().f_46443_) {
            return player.m_20329_((Entity)this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        }
        return InteractionResult.SUCCESS;
    }

    public int getTimeSinceHit() {
        return (Integer)this.f_19804_.m_135370_(TIME_SINCE_HIT);
    }

    public void setTimeSinceHit(int timeSinceHit) {
        this.f_19804_.m_135381_(TIME_SINCE_HIT, (Object)timeSinceHit);
    }

    @OnlyIn(value=Dist.CLIENT)
    public float getRockingAngle(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.prevRockingAngle, (float)this.rockingAngle);
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected Entity.MovementEmission m_142319_() {
        return Entity.MovementEmission.EVENTS;
    }

    protected void m_7378_(CompoundTag compound) {
        this.setDefaultColor(compound.m_128471_("IsDefColor"));
        if (compound.m_128441_("BoardStack")) {
            this.setItemStack(ItemStack.m_41712_((CompoundTag)compound.m_128469_("BoardStack")));
        }
        this.setColor(compound.m_128451_("Color"));
    }

    protected void m_7380_(CompoundTag compound) {
        compound.m_128379_("IsDefColor", this.isDefaultColor());
        compound.m_128405_("Color", this.getColor());
        if (!this.getItemStack().m_41619_()) {
            CompoundTag stackTag = new CompoundTag();
            this.getItemStack().m_41739_(stackTag);
            compound.m_128365_("BoardStack", (Tag)stackTag);
        }
    }

    public void m_7888_(int i) {
    }

    public boolean m_7132_() {
        return this.isOnLava();
    }

    public void m_7199_(int i) {
        this.f_19812_ = true;
        if (this.m_7132_()) {
            float f = 0.075f + (float)this.getEnchant((Enchantment)AMEnchantmentRegistry.STRADDLE_JUMP.get()) * 0.05f;
            this.jumpFor = 5 + (int)((float)i * f);
        }
    }

    private int getEnchant(Enchantment enchantment) {
        return EnchantmentHelper.m_44843_((Enchantment)enchantment, (ItemStack)this.getItemBoard());
    }

    public boolean shouldSerpentFriend() {
        return this.getEnchant((Enchantment)AMEnchantmentRegistry.STRADDLE_SERPENTFRIEND.get()) > 0;
    }

    public Vec3 m_7688_(LivingEntity entity) {
        return new Vec3(this.m_20185_(), this.m_20186_() + 2.0, this.m_20189_());
    }

    public void m_8012_() {
    }

    public ItemStack getItemStack() {
        return (ItemStack)this.f_19804_.m_135370_(ITEMSTACK);
    }

    public void setItemStack(ItemStack item) {
        this.f_19804_.m_135381_(ITEMSTACK, (Object)item);
    }
}

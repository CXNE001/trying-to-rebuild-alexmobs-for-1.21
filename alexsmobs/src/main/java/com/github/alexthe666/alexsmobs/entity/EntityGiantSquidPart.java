/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.entity.PartEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import com.github.alexthe666.alexsmobs.message.MessageHurtMultipart;
import com.github.alexthe666.alexsmobs.message.MessageInteractMultipart;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;

public class EntityGiantSquidPart
extends PartEntity<EntityGiantSquid>
implements IHurtableMultipart {
    private final EntityDimensions size;
    public float scale = 1.0f;
    private boolean collisionOnly = false;

    public EntityGiantSquidPart(EntityGiantSquid parent, float sizeX, float sizeY) {
        super((Entity)parent);
        this.size = EntityDimensions.m_20395_((float)sizeX, (float)sizeY);
        this.m_6210_();
    }

    public EntityGiantSquidPart(EntityGiantSquid parent, float sizeX, float sizeY, boolean collisionOnly) {
        this(parent, sizeX, sizeY);
        this.collisionOnly = collisionOnly;
    }

    public boolean m_5825_() {
        return true;
    }

    public Vec3 m_7939_() {
        return new Vec3(0.0, (double)this.m_20192_() * (double)0.15f, (double)(this.m_20205_() * 0.1f));
    }

    protected void collideWithNearbyEntities() {
    }

    public InteractionResult m_6096_(Player player, InteractionHand hand) {
        if (this.m_9236_().f_46443_ && this.getParent() != null) {
            AlexsMobs.sendMSGToServer(new MessageInteractMultipart(((EntityGiantSquid)this.getParent()).m_19879_(), hand == InteractionHand.OFF_HAND));
        }
        return this.getParent() == null ? InteractionResult.PASS : ((EntityGiantSquid)this.getParent()).m_6071_(player, hand);
    }

    public boolean m_5829_() {
        return !this.collisionOnly;
    }

    protected void collideWithEntity(Entity entityIn) {
        if (!this.collisionOnly) {
            entityIn.m_7334_((Entity)this);
        }
    }

    public boolean m_6087_() {
        return !this.collisionOnly;
    }

    @Nullable
    public ItemStack m_142340_() {
        Entity parent = this.getParent();
        return parent != null ? parent.m_142340_() : ItemStack.f_41583_;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (this.m_9236_().f_46443_ && this.getParent() != null && !((EntityGiantSquid)this.getParent()).m_6673_(source) && !this.collisionOnly) {
            AlexsMobs.sendMSGToServer(new MessageHurtMultipart(this.m_19879_(), ((EntityGiantSquid)this.getParent()).m_19879_(), amount, source.m_19385_()));
        }
        return !this.collisionOnly && !this.m_6673_(source) && ((EntityGiantSquid)this.getParent()).attackEntityPartFrom(this, source, amount);
    }

    public boolean m_7306_(Entity entityIn) {
        return this == entityIn || this.getParent() == entityIn;
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        throw new UnsupportedOperationException();
    }

    public EntityDimensions m_6972_(Pose poseIn) {
        return this.size == null ? EntityDimensions.m_20395_((float)0.0f, (float)0.0f) : this.size.m_20388_(this.scale);
    }

    protected void m_8097_() {
    }

    public void m_8119_() {
        super.m_8119_();
    }

    protected void m_7378_(CompoundTag compound) {
    }

    protected void m_7380_(CompoundTag compound) {
    }

    @Override
    public void onAttackedFromServer(LivingEntity parent, float damage, DamageSource damageSource) {
        parent.m_6469_(damageSource, damage);
    }
}

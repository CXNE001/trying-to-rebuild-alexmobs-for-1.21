/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.monster.Drowned
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.ToolActions
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntitySharkToothArrow
extends Arrow {
    public EntitySharkToothArrow(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    public EntitySharkToothArrow(EntityType type, double x, double y, double z, Level worldIn) {
        this(type, worldIn);
        this.m_6034_(x, y, z);
    }

    public EntitySharkToothArrow(Level worldIn, LivingEntity shooter) {
        this((EntityType)AMEntityRegistry.SHARK_TOOTH_ARROW.get(), shooter.m_20185_(), shooter.m_20188_() - (double)0.1f, shooter.m_20189_(), worldIn);
        this.m_5602_((Entity)shooter);
        if (shooter instanceof Player) {
            this.f_36705_ = AbstractArrow.Pickup.ALLOWED;
        }
    }

    protected void damageShield(Player player, float damage) {
        if (damage >= 3.0f && player.m_21211_().m_41720_().canPerformAction(player.m_21211_(), ToolActions.SHIELD_BLOCK)) {
            ItemStack copyBeforeUse = player.m_21211_().m_41777_();
            int i = 1 + Mth.m_14143_((float)damage);
            player.m_21211_().m_41622_(i, (LivingEntity)player, p_213360_0_ -> p_213360_0_.m_21166_(EquipmentSlot.CHEST));
            if (player.m_21211_().m_41619_()) {
                InteractionHand Hand = player.m_7655_();
                ForgeEventFactory.onPlayerDestroyItem((Player)player, (ItemStack)copyBeforeUse, (InteractionHand)Hand);
                if (Hand == InteractionHand.MAIN_HAND) {
                    this.m_8061_(EquipmentSlot.MAINHAND, ItemStack.f_41583_);
                } else {
                    this.m_8061_(EquipmentSlot.OFFHAND, ItemStack.f_41583_);
                }
                player.m_5810_();
                this.m_5496_(SoundEvents.f_12347_, 0.8f, 0.8f + this.m_9236_().f_46441_.m_188501_() * 0.4f);
            }
        }
    }

    protected void m_7761_(LivingEntity living) {
        if (living instanceof Player) {
            this.damageShield((Player)living, (float)this.m_36789_());
        }
        Entity entity1 = this.m_19749_();
        if (living.m_6336_() == MobType.f_21644_ || living instanceof Drowned || living.m_6336_() != MobType.f_21641_ && living.m_6040_()) {
            DamageSource damagesource = entity1 == null ? this.m_269291_().m_269418_((AbstractArrow)this, (Entity)this) : this.m_269291_().m_269418_((AbstractArrow)this, entity1);
            living.m_6469_(damagesource, 7.0f);
        }
    }

    public boolean m_20069_() {
        return false;
    }

    public EntitySharkToothArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this((EntityType)AMEntityRegistry.SHARK_TOOTH_ARROW.get(), world);
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    protected ItemStack m_7941_() {
        return new ItemStack((ItemLike)AMItemRegistry.SHARK_TOOTH_ARROW.get());
    }
}

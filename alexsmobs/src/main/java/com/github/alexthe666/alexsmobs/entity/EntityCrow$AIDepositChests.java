/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.capabilities.ForgeCapabilities
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.ItemHandlerHelper
 */
package com.github.alexthe666.alexsmobs.entity;

import com.google.common.base.Predicate;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

private class EntityCrow.AIDepositChests
extends Goal {
    protected final Sorter theNearestAttackableTargetSorter;
    protected final Predicate<ItemFrame> targetEntitySelector;
    protected int executionChance = 8;
    protected boolean mustUpdate;
    private ItemFrame targetEntity;
    private Vec3 flightTarget = null;
    private int cooldown = 0;

    EntityCrow.AIDepositChests() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.theNearestAttackableTargetSorter = new Sorter((Entity)EntityCrow.this);
        this.targetEntitySelector = new Predicate<ItemFrame>(){

            public boolean apply(@Nullable ItemFrame e) {
                LazyOptional handler;
                BlockPos hangingPosition = e.m_31748_().m_121945_(e.m_6350_().m_122424_());
                BlockEntity entity = e.m_9236_().m_7702_(hangingPosition);
                if (entity != null && (handler = entity.getCapability(ForgeCapabilities.ITEM_HANDLER, e.m_6350_().m_122424_())) != null && handler.isPresent()) {
                    return ItemStack.m_41656_((ItemStack)e.m_31822_(), (ItemStack)EntityCrow.this.m_21205_());
                }
                return false;
            }
        };
    }

    public boolean m_8036_() {
        List list;
        long worldTime;
        if (EntityCrow.this.m_20159_() || EntityCrow.this.aiItemFlag || EntityCrow.this.m_20160_() || EntityCrow.this.isSitting() || EntityCrow.this.getCommand() != 3) {
            return false;
        }
        if (EntityCrow.this.m_21205_().m_41619_()) {
            return false;
        }
        if (!this.mustUpdate && (worldTime = EntityCrow.this.m_9236_().m_46467_() % 10L) != 0L) {
            if (EntityCrow.this.m_21216_() >= 100) {
                return false;
            }
            if (EntityCrow.this.m_217043_().m_188503_(this.executionChance) != 0) {
                return false;
            }
        }
        if ((list = EntityCrow.this.m_9236_().m_6443_(ItemFrame.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
            return false;
        }
        list.sort(this.theNearestAttackableTargetSorter);
        this.targetEntity = (ItemFrame)list.get(0);
        this.mustUpdate = false;
        EntityCrow.this.aiItemFrameFlag = true;
        return true;
    }

    public boolean m_8045_() {
        return this.targetEntity != null && EntityCrow.this.getCommand() == 3 && !EntityCrow.this.m_21205_().m_41619_();
    }

    public void m_8041_() {
        this.flightTarget = null;
        this.targetEntity = null;
        EntityCrow.this.aiItemFrameFlag = false;
    }

    public void m_8037_() {
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        if (this.flightTarget != null) {
            EntityCrow.this.setFlying(true);
            if (EntityCrow.this.f_19862_) {
                EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, EntityCrow.this.m_20186_() + 1.0, this.flightTarget.f_82481_, 1.0);
            } else {
                EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, 1.0);
            }
        }
        if (this.targetEntity != null) {
            this.flightTarget = this.targetEntity.m_20182_();
            if (EntityCrow.this.m_20270_((Entity)this.targetEntity) < 2.0f) {
                try {
                    BlockPos hangingPosition = this.targetEntity.m_31748_().m_121945_(this.targetEntity.m_6350_().m_122424_());
                    BlockEntity entity = this.targetEntity.m_9236_().m_7702_(hangingPosition);
                    Direction deposit = this.targetEntity.m_6350_();
                    LazyOptional handler = entity.getCapability(ForgeCapabilities.ITEM_HANDLER, deposit);
                    if (handler.orElse(null) != null && this.cooldown == 0) {
                        ItemStack duplicate = EntityCrow.this.m_21120_(InteractionHand.MAIN_HAND).m_41777_();
                        ItemStack insertSimulate = ItemHandlerHelper.insertItem((IItemHandler)((IItemHandler)handler.orElse(null)), (ItemStack)duplicate, (boolean)true);
                        if (!insertSimulate.equals(duplicate)) {
                            ItemStack shrunkenStack = ItemHandlerHelper.insertItem((IItemHandler)((IItemHandler)handler.orElse(null)), (ItemStack)duplicate, (boolean)false);
                            if (shrunkenStack.m_41619_()) {
                                EntityCrow.this.m_21008_(InteractionHand.MAIN_HAND, ItemStack.f_41583_);
                            } else {
                                EntityCrow.this.m_21008_(InteractionHand.MAIN_HAND, shrunkenStack);
                            }
                            EntityCrow.this.peck();
                        } else {
                            this.cooldown = 20;
                        }
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.m_8041_();
            }
        }
    }

    protected double getTargetDistance() {
        return 4.0;
    }

    protected AABB getTargetableArea(double targetDistance) {
        Vec3 renderCenter = new Vec3(EntityCrow.this.m_20185_(), EntityCrow.this.m_20186_(), EntityCrow.this.m_20189_());
        AABB aabb = new AABB(-16.0, -16.0, -16.0, 16.0, 16.0, 16.0);
        return aabb.m_82383_(renderCenter);
    }

    public record Sorter(Entity theEntity) implements Comparator<Entity>
    {
        @Override
        public int compare(Entity p_compare_1_, Entity p_compare_2_) {
            double d0 = this.theEntity.m_20280_(p_compare_1_);
            double d1 = this.theEntity.m_20280_(p_compare_2_);
            return Double.compare(d0, d1);
        }
    }
}

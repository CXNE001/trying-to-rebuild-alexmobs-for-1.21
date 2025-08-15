/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.DispensibleContainerItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.BlockHitResult
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityFlutter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ItemFlutterPot
extends Item
implements DispensibleContainerItem {
    public ItemFlutterPot(Item.Properties builder) {
        super(builder.m_41487_(1));
    }

    public InteractionResult m_6225_(UseOnContext context) {
        Level world = context.m_43725_();
        BlockPos blockpos = context.m_8083_();
        if (!world.f_46443_) {
            if (this.placeFish((ServerLevel)world, context.m_43722_(), blockpos) && (context.m_43723_() == null || !context.m_43723_().m_7500_())) {
                context.m_43722_().m_41774_(1);
            }
            return InteractionResult.m_19078_((boolean)world.f_46443_);
        }
        return InteractionResult.PASS;
    }

    protected void playEmptySound(@javax.annotation.Nullable Player player, LevelAccessor worldIn, BlockPos pos) {
        worldIn.m_5594_(player, pos, SoundEvents.f_11779_, SoundSource.NEUTRAL, 1.0f, 1.0f);
    }

    private boolean placeFish(ServerLevel worldIn, ItemStack stack, BlockPos pos) {
        Entity entity = ((EntityType)AMEntityRegistry.FLUTTER.get()).m_20592_(worldIn, stack, (Player)null, pos, MobSpawnType.BUCKET, true, false);
        if (entity != null && entity instanceof EntityFlutter) {
            CompoundTag compoundnbt = stack.m_41784_();
            if (compoundnbt.m_128441_("FlutterData")) {
                ((EntityFlutter)entity).m_7378_(compoundnbt.m_128469_("FlutterData"));
            }
            return true;
        }
        return false;
    }

    public boolean m_142073_(@Nullable Player p_150821_, Level p_150822_, BlockPos p_150823_, @Nullable BlockHitResult p_150824_) {
        return false;
    }
}

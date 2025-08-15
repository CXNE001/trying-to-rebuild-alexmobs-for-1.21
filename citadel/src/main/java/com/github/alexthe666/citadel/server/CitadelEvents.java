/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingTickEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package com.github.alexthe666.citadel.server;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.block.CitadelLecternBlock;
import com.github.alexthe666.citadel.server.block.CitadelLecternBlockEntity;
import com.github.alexthe666.citadel.server.block.LecternBooks;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CitadelEvents {
    private int updateTimer;

    @SubscribeEvent
    public void onEntityUpdateDebug(LivingEvent.LivingTickEvent event) {
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().m_8055_(event.getPos()).m_60713_(Blocks.f_50624_) && LecternBooks.isLecternBook(event.getItemStack())) {
            LecternBlockEntity oldBe;
            event.getEntity().m_36335_().m_41524_(event.getItemStack().m_41720_(), 1);
            BlockState oldLectern = event.getLevel().m_8055_(event.getPos());
            BlockEntity blockEntity = event.getLevel().m_7702_(event.getPos());
            if (blockEntity instanceof LecternBlockEntity && !(oldBe = (LecternBlockEntity)blockEntity).m_59567_()) {
                BlockState newLectern = (BlockState)((BlockState)((BlockState)((Block)Citadel.LECTERN.get()).m_49966_().m_61124_((Property)CitadelLecternBlock.f_54465_, (Comparable)((Direction)oldLectern.m_61143_((Property)LecternBlock.f_54465_)))).m_61124_((Property)CitadelLecternBlock.f_54466_, (Comparable)((Boolean)oldLectern.m_61143_((Property)LecternBlock.f_54466_)))).m_61124_((Property)CitadelLecternBlock.f_54467_, (Comparable)Boolean.valueOf(true));
                event.getLevel().m_46597_(event.getPos(), newLectern);
                CitadelLecternBlockEntity newBe = new CitadelLecternBlockEntity(event.getPos(), newLectern);
                ItemStack bookCopy = event.getItemStack().m_41777_();
                bookCopy.m_41764_(1);
                newBe.setBook(bookCopy);
                if (!event.getEntity().m_7500_()) {
                    event.getItemStack().m_41774_(1);
                }
                event.getLevel().m_151523_((BlockEntity)newBe);
                event.getEntity().m_21011_(event.getHand(), true);
                event.getLevel().m_5594_(null, event.getPos(), SoundEvents.f_11714_, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getOriginal() != null && CitadelEntityData.getCitadelTag((LivingEntity)event.getOriginal()) != null) {
            CitadelEntityData.setCitadelTag((LivingEntity)event.getEntity(), CitadelEntityData.getCitadelTag((LivingEntity)event.getOriginal()));
        }
    }
}

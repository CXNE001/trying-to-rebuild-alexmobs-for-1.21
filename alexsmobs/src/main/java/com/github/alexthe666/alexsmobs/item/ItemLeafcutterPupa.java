/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityLeafcutterAnthill;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemLeafcutterPupa
extends Item {
    public ItemLeafcutterPupa(Item.Properties props) {
        super(props);
    }

    public InteractionResult m_6225_(UseOnContext context) {
        BlockPos blockpos;
        Level world = context.m_43725_();
        BlockState blockstate = world.m_8055_(blockpos = context.m_8083_());
        if (blockstate.m_204336_(AMTagRegistry.LEAFCUTTER_PUPA_USABLE_ON) && world.m_8055_(blockpos.m_7495_()).m_204336_(AMTagRegistry.LEAFCUTTER_PUPA_USABLE_ON)) {
            Player playerentity = context.m_43723_();
            if (playerentity != null) {
                playerentity.m_146850_(GameEvent.f_157797_);
            }
            world.m_5594_(playerentity, blockpos, SoundEvents.f_11688_, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (!world.f_46443_) {
                world.m_7731_(blockpos, ((Block)AMBlockRegistry.LEAFCUTTER_ANTHILL.get()).m_49966_(), 11);
                world.m_7731_(blockpos.m_7495_(), ((Block)AMBlockRegistry.LEAFCUTTER_ANT_CHAMBER.get()).m_49966_(), 11);
                BlockEntity tileentity = world.m_7702_(blockpos);
                if (tileentity instanceof TileEntityLeafcutterAnthill) {
                    TileEntityLeafcutterAnthill beehivetileentity = (TileEntityLeafcutterAnthill)tileentity;
                    int j = Math.min(3, AMConfig.leafcutterAntColonySize);
                    for (int k = 0; k < j; ++k) {
                        EntityLeafcutterAnt beeentity = new EntityLeafcutterAnt((EntityType)AMEntityRegistry.LEAFCUTTER_ANT.get(), world);
                        beeentity.setQueen(k == 0);
                        beehivetileentity.tryEnterHive(beeentity, false, 100);
                    }
                }
                if (playerentity != null && !playerentity.m_7500_()) {
                    context.m_43722_().m_41774_(1);
                }
            }
            return InteractionResult.m_19078_((boolean)world.f_46443_);
        }
        return InteractionResult.PASS;
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity$BeeReleaseStatus
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.entity.EntityManedWolf;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityLeafcutterAnthill;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class BlockLeafcutterAnthill
extends BaseEntityBlock {
    public BlockLeafcutterAnthill() {
        super(BlockBehaviour.Properties.m_284310_().m_60918_(SoundType.f_56739_).m_60978_(0.75f));
    }

    public InteractionResult m_6227_(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.m_7702_(pos) instanceof TileEntityLeafcutterAnthill) {
            TileEntityLeafcutterAnthill hill = (TileEntityLeafcutterAnthill)worldIn.m_7702_(pos);
            ItemStack heldItem = player.m_21120_(handIn);
            if (heldItem.m_41720_() == AMItemRegistry.GONGYLIDIA.get() && hill.hasQueen()) {
                hill.releaseQueens();
                if (!player.m_7500_()) {
                    heldItem.m_41774_(1);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public RenderShape m_7514_(BlockState p_149645_1_) {
        return RenderShape.MODEL;
    }

    public void m_5707_(Level worldIn, BlockPos pos, BlockState state, Player player) {
        BlockEntity tileentity;
        if (!worldIn.f_46443_ && player.m_7500_() && worldIn.m_46469_().m_46207_(GameRules.f_46136_) && (tileentity = worldIn.m_7702_(pos)) instanceof TileEntityLeafcutterAnthill) {
            boolean flag;
            TileEntityLeafcutterAnthill anthivetileentity = (TileEntityLeafcutterAnthill)tileentity;
            ItemStack itemstack = new ItemStack((ItemLike)this);
            boolean bl = flag = !anthivetileentity.hasNoAnts();
            if (!flag) {
                return;
            }
            if (flag) {
                CompoundTag compoundnbt = new CompoundTag();
                compoundnbt.m_128365_("Ants", (Tag)anthivetileentity.getAnts());
                itemstack.m_41700_("BlockEntityTag", (Tag)compoundnbt);
            }
            CompoundTag compoundnbt1 = new CompoundTag();
            itemstack.m_41700_("BlockStateTag", (Tag)compoundnbt1);
            ItemEntity itementity = new ItemEntity(worldIn, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), itemstack);
            itementity.m_32060_();
            worldIn.m_7967_((Entity)itementity);
        }
        super.m_5707_(worldIn, pos, state, player);
    }

    public void m_142072_(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        if (entityIn instanceof LivingEntity && !(entityIn instanceof EntityManedWolf)) {
            this.angerNearbyAnts(worldIn, (LivingEntity)entityIn, pos);
            if (!worldIn.f_46443_ && worldIn.m_7702_(pos) instanceof TileEntityLeafcutterAnthill) {
                TileEntityLeafcutterAnthill beehivetileentity = (TileEntityLeafcutterAnthill)worldIn.m_7702_(pos);
                beehivetileentity.angerAnts((LivingEntity)entityIn, worldIn.m_8055_(pos), BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY);
                if (entityIn instanceof ServerPlayer) {
                    AMAdvancementTriggerRegistry.STOMP_LEAFCUTTER_ANTHILL.trigger((ServerPlayer)entityIn);
                }
            }
        }
        super.m_142072_(worldIn, state, pos, entityIn, fallDistance);
    }

    public void m_6240_(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.m_6240_(worldIn, player, pos, state, te, stack);
        if (!worldIn.f_46443_ && te instanceof TileEntityLeafcutterAnthill) {
            TileEntityLeafcutterAnthill beehivetileentity = (TileEntityLeafcutterAnthill)te;
            if (EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44985_, (ItemStack)stack) == 0) {
                beehivetileentity.angerAnts((LivingEntity)player, state, BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY);
                worldIn.m_46717_(pos, (Block)this);
                this.angerNearbyAnts(worldIn, pos);
            }
        }
    }

    private void angerNearbyAnts(Level world, BlockPos pos) {
        List list = world.m_45976_(EntityLeafcutterAnt.class, new AABB(pos).m_82377_(20.0, 6.0, 20.0));
        if (!list.isEmpty()) {
            List list1 = world.m_45976_(Player.class, new AABB(pos).m_82377_(20.0, 6.0, 20.0));
            if (list1.isEmpty()) {
                return;
            }
            int i = list1.size();
            for (EntityLeafcutterAnt beeentity : list) {
                if (beeentity.m_5448_() != null) continue;
                beeentity.m_6710_((LivingEntity)list1.get(world.f_46441_.m_188503_(i)));
            }
        }
    }

    private void angerNearbyAnts(Level world, LivingEntity entity, BlockPos pos) {
        List list = world.m_45976_(EntityLeafcutterAnt.class, new AABB(pos).m_82377_(20.0, 6.0, 20.0));
        if (!list.isEmpty()) {
            for (EntityLeafcutterAnt beeentity : list) {
                if (beeentity.m_5448_() != null) continue;
                beeentity.m_6710_(entity);
            }
        }
    }

    @Nullable
    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new TileEntityLeafcutterAnthill(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return p_152180_.f_46443_ ? null : BlockLeafcutterAnthill.m_152132_(p_152182_, (BlockEntityType)((BlockEntityType)AMTileEntityRegistry.LEAFCUTTER_ANTHILL.get()), TileEntityLeafcutterAnthill::serverTick);
    }
}

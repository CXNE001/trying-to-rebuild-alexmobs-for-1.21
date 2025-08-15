/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.village.poi.PoiManager
 *  net.minecraft.world.entity.ai.village.poi.PoiManager$Occupancy
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity$BeeReleaseStatus
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMPointOfInterestRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityLeafcutterAnthill;
import com.google.common.base.Predicates;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class BlockLeafcutterAntChamber
extends Block {
    public static final IntegerProperty FUNGUS = IntegerProperty.m_61631_((String)"fungus", (int)0, (int)5);

    public BlockLeafcutterAntChamber() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283762_).m_60918_(SoundType.f_56739_).m_60978_(1.3f).m_60977_());
        this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FUNGUS, (Comparable)Integer.valueOf(0)));
    }

    public InteractionResult m_6227_(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int fungalLevel = (Integer)state.m_61143_((Property)FUNGUS);
        if (fungalLevel == 5) {
            boolean shroomlight = false;
            for (BlockPos blockpos : BlockPos.m_121940_((BlockPos)pos.m_7918_(-1, -1, -1), (BlockPos)pos.m_7918_(1, 1, 1))) {
                if (worldIn.m_8055_(blockpos).m_60734_() != Blocks.f_50701_) continue;
                shroomlight = true;
            }
            if (!shroomlight) {
                this.angerNearbyAnts(worldIn, pos);
            }
            worldIn.m_46597_(pos, (BlockState)state.m_61124_((Property)FUNGUS, (Comparable)Integer.valueOf(0)));
            if (!worldIn.f_46443_) {
                if (worldIn.f_46441_.m_188503_(2) == 0) {
                    BlockPos offset;
                    Direction dir = Direction.m_235672_((RandomSource)worldIn.f_46441_);
                    if (worldIn.m_8055_(pos.m_7494_()).m_60734_() == AMBlockRegistry.LEAFCUTTER_ANTHILL.get()) {
                        dir = Direction.DOWN;
                    }
                    if (worldIn.m_8055_(offset = pos.m_121945_(dir)).m_204336_(AMTagRegistry.LEAFCUTTER_PUPA_USABLE_ON) && !worldIn.m_45527_(offset)) {
                        worldIn.m_46597_(offset, this.m_49966_());
                    }
                }
                BlockLeafcutterAntChamber.m_49840_((Level)worldIn, (BlockPos)pos, (ItemStack)new ItemStack((ItemLike)AMItemRegistry.GONGYLIDIA.get()));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public void m_213898_(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (!worldIn.isAreaLoaded(pos, 3)) {
            return;
        }
        if (worldIn.m_45527_(pos.m_7494_())) {
            worldIn.m_46597_(pos, Blocks.f_50493_.m_49966_());
        }
    }

    public void m_6240_(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.m_6240_(worldIn, player, pos, state, te, stack);
        this.angerNearbyAnts(worldIn, pos);
    }

    private void angerNearbyAnts(Level world, BlockPos pos) {
        List list = world.m_45976_(EntityLeafcutterAnt.class, new AABB(pos).m_82377_(20.0, 6.0, 20.0));
        Player player = null;
        List list1 = world.m_45976_(Player.class, new AABB(pos).m_82377_(20.0, 6.0, 20.0));
        if (list1.isEmpty()) {
            return;
        }
        int i = list1.size();
        player = (Player)list1.get(world.f_46441_.m_188503_(i));
        if (!list.isEmpty()) {
            for (EntityLeafcutterAnt beeentity : list) {
                if (beeentity.m_5448_() != null) continue;
                beeentity.m_6710_((LivingEntity)player);
            }
        }
        if (!world.f_46443_) {
            PoiManager pointofinterestmanager = ((ServerLevel)world).m_8904_();
            Stream stream = pointofinterestmanager.m_27138_(poiTypeHolder -> poiTypeHolder.m_203565_(AMPointOfInterestRegistry.LEAFCUTTER_ANT_HILL.getKey()), (Predicate)Predicates.alwaysTrue(), pos, 50, PoiManager.Occupancy.ANY);
            List listOfHives = stream.collect(Collectors.toList());
            for (BlockPos pos2 : listOfHives) {
                if (!(world.m_7702_(pos2) instanceof TileEntityLeafcutterAnthill)) continue;
                TileEntityLeafcutterAnthill beehivetileentity = (TileEntityLeafcutterAnthill)world.m_7702_(pos2);
                beehivetileentity.angerAnts((LivingEntity)player, world.m_8055_(pos2), BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY);
            }
        }
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{FUNGUS});
    }
}

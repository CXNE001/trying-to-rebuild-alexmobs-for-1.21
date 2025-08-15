/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ambient.Bat
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.util.TerrapinTypes;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityTerrapinEgg;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockTerrapinEgg
extends BaseEntityBlock {
    public static final IntegerProperty HATCH = BlockStateProperties.f_61416_;
    public static final IntegerProperty EGGS = BlockStateProperties.f_61415_;
    private static final VoxelShape ONE_EGG_SHAPE = Block.m_49796_((double)3.0, (double)0.0, (double)3.0, (double)12.0, (double)7.0, (double)12.0);
    private static final VoxelShape MULTI_EGG_SHAPE = Block.m_49796_((double)1.0, (double)0.0, (double)1.0, (double)15.0, (double)7.0, (double)15.0);

    public BlockTerrapinEgg() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283761_).m_60978_(0.5f).m_60918_(SoundType.f_56743_).m_60977_().m_60955_());
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)HATCH, (Comparable)Integer.valueOf(0))).m_61124_((Property)EGGS, (Comparable)Integer.valueOf(1)));
    }

    public static boolean hasProperHabitat(BlockGetter reader, BlockPos blockReader) {
        return BlockTerrapinEgg.isProperHabitat(reader, blockReader.m_7495_());
    }

    public RenderShape m_7514_(BlockState p_149645_1_) {
        return RenderShape.MODEL;
    }

    public static boolean isProperHabitat(BlockGetter reader, BlockPos pos) {
        return reader.m_8055_(pos).m_204336_(BlockTags.f_13029_) || reader.m_8055_(pos).m_204336_(AMTagRegistry.CROCODILE_SPAWNS);
    }

    public void m_141947_(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        this.tryTrample(worldIn, pos, entityIn, 100);
        super.m_141947_(worldIn, pos, state, entityIn);
    }

    public void m_142072_(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        if (!(entityIn instanceof Zombie)) {
            this.tryTrample(worldIn, pos, entityIn, 3);
        }
        super.m_142072_(worldIn, state, pos, entityIn, fallDistance);
    }

    private void tryTrample(Level worldIn, BlockPos pos, Entity trampler, int chances) {
        if (this.canTrample(worldIn, trampler) && !worldIn.f_46443_ && worldIn.f_46441_.m_188503_(chances) == 0) {
            BlockState blockstate = worldIn.m_8055_(pos);
            this.removeOneEgg(worldIn, pos, blockstate);
        }
    }

    private void removeOneEgg(Level worldIn, BlockPos pos, BlockState state) {
        worldIn.m_5594_(null, pos, SoundEvents.f_12533_, SoundSource.BLOCKS, 0.7f, 0.9f + worldIn.f_46441_.m_188501_() * 0.2f);
        int i = (Integer)state.m_61143_((Property)EGGS);
        if (i <= 1) {
            worldIn.m_46961_(pos, false);
        } else {
            worldIn.m_7731_(pos, (BlockState)state.m_61124_((Property)EGGS, (Comparable)Integer.valueOf(i - 1)), 2);
            worldIn.m_220407_(GameEvent.f_157794_, pos, GameEvent.Context.m_223722_((BlockState)state));
            worldIn.m_46796_(2001, pos, Block.m_49956_((BlockState)state));
        }
    }

    public void m_213898_(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (this.canGrow((Level)worldIn) && BlockTerrapinEgg.hasProperHabitat((BlockGetter)worldIn, pos)) {
            int i = (Integer)state.m_61143_((Property)HATCH);
            if (i < 2) {
                worldIn.m_5594_(null, pos, SoundEvents.f_12534_, SoundSource.BLOCKS, 0.7f, 0.9f + random.m_188501_() * 0.2f);
                worldIn.m_220407_(GameEvent.f_157794_, pos, GameEvent.Context.m_223722_((BlockState)state));
                worldIn.m_7731_(pos, (BlockState)state.m_61124_((Property)HATCH, (Comparable)Integer.valueOf(i + 1)), 2);
            } else {
                worldIn.m_5594_(null, pos, SoundEvents.f_12535_, SoundSource.BLOCKS, 0.7f, 0.9f + random.m_188501_() * 0.2f);
                worldIn.m_220407_(GameEvent.f_157794_, pos, GameEvent.Context.m_223722_((BlockState)state));
                worldIn.m_7471_(pos, false);
                for (int j = 0; j < (Integer)state.m_61143_((Property)EGGS); ++j) {
                    worldIn.m_46796_(2001, pos, Block.m_49956_((BlockState)state));
                    EntityTerrapin turtleentity = (EntityTerrapin)((EntityType)AMEntityRegistry.TERRAPIN.get()).m_20615_((Level)worldIn);
                    turtleentity.m_146762_(-24000);
                    BlockEntity blockEntity = worldIn.m_7702_(pos);
                    if (blockEntity instanceof TileEntityTerrapinEgg) {
                        TileEntityTerrapinEgg eggTE = (TileEntityTerrapinEgg)blockEntity;
                        eggTE.addAttributesToOffspring(turtleentity, random);
                    }
                    turtleentity.m_27497_(true);
                    turtleentity.m_7678_((double)pos.m_123341_() + 0.3 + (double)j * 0.2, pos.m_123342_(), (double)pos.m_123343_() + 0.3, 0.0f, 0.0f);
                    worldIn.m_7967_((Entity)turtleentity);
                }
            }
        }
    }

    public void m_6807_(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (BlockTerrapinEgg.hasProperHabitat((BlockGetter)worldIn, pos) && !worldIn.f_46443_) {
            worldIn.m_46796_(2005, pos, 0);
        }
    }

    private boolean canGrow(Level worldIn) {
        float f = worldIn.m_46942_(1.0f);
        if ((double)f < 0.69 && (double)f > 0.65) {
            return true;
        }
        return worldIn.f_46441_.m_188503_(15) == 0;
    }

    public void m_6240_(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.m_6240_(worldIn, player, pos, state, te, stack);
        this.removeOneEgg(worldIn, pos, state);
    }

    public boolean m_6864_(BlockState state, BlockPlaceContext useContext) {
        return useContext.m_43722_().m_41720_() == this.m_5456_() && (Integer)state.m_61143_((Property)EGGS) < 4 || super.m_6864_(state, useContext);
    }

    @Nullable
    public BlockState m_5573_(BlockPlaceContext context) {
        BlockState blockstate = context.m_43725_().m_8055_(context.m_8083_());
        return blockstate.m_60734_() == this ? (BlockState)blockstate.m_61124_((Property)EGGS, (Comparable)Integer.valueOf(Math.min(4, (Integer)blockstate.m_61143_((Property)EGGS) + 1))) : super.m_5573_(context);
    }

    public VoxelShape m_5940_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return (Integer)state.m_61143_((Property)EGGS) > 1 ? MULTI_EGG_SHAPE : ONE_EGG_SHAPE;
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{HATCH, EGGS});
    }

    private boolean canTrample(Level worldIn, Entity trampler) {
        if (!(trampler instanceof EntityTerrapin) && !(trampler instanceof Bat)) {
            if (!(trampler instanceof LivingEntity)) {
                return false;
            }
            return trampler instanceof Player || ForgeEventFactory.getMobGriefingEvent((Level)worldIn, (Entity)trampler);
        }
        return false;
    }

    public List<ItemStack> m_49635_(BlockState state, LootParams.Builder builder) {
        ItemStack pickaxe = (ItemStack)builder.m_287159_(LootContextParams.f_81463_);
        BlockEntity blockentity = (BlockEntity)builder.m_287159_(LootContextParams.f_81462_);
        boolean silkTouch = false;
        if (pickaxe != null) {
            boolean bl = silkTouch = EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44985_, (ItemStack)pickaxe) > 0;
        }
        if (silkTouch && blockentity instanceof TileEntityTerrapinEgg) {
            ItemStack stack = new ItemStack((ItemLike)AMBlockRegistry.TERRAPIN_EGG.get());
            TileEntityTerrapinEgg egg = (TileEntityTerrapinEgg)blockentity;
            CompoundTag tag = stack.m_41698_("BlockEntityTag");
            CompoundTag parent1 = new CompoundTag();
            CompoundTag parent2 = new CompoundTag();
            boolean flag = false;
            if (egg.parent1 != null) {
                flag = true;
                egg.parent1.writeToNBT(parent1);
            }
            if (egg.parent2 != null) {
                flag = true;
                egg.parent2.writeToNBT(parent2);
            }
            if (flag) {
                tag.m_128365_("Parent1Data", (Tag)parent1);
                tag.m_128365_("Parent2Data", (Tag)parent2);
            }
            return List.of(stack);
        }
        return List.of();
    }

    public void m_5871_(ItemStack stack, @Nullable BlockGetter w, List<Component> list, TooltipFlag flags) {
        super.m_5871_(stack, w, list, flags);
        CompoundTag compoundtag = BlockItem.m_186336_((ItemStack)stack);
        if (compoundtag != null && compoundtag.m_128441_("Parent1Data") && compoundtag.m_128441_("Parent2Data")) {
            TerrapinTypes parent1Type = TerrapinTypes.values()[Mth.m_14045_((int)compoundtag.m_128469_("Parent1Data").m_128451_("TerrapinType"), (int)0, (int)(TerrapinTypes.values().length - 1))];
            TerrapinTypes parent2Type = TerrapinTypes.values()[Mth.m_14045_((int)compoundtag.m_128469_("Parent2Data").m_128451_("TerrapinType"), (int)0, (int)(TerrapinTypes.values().length - 1))];
            String s1 = Component.m_237115_((String)parent1Type.getTranslationName()).getString();
            String s2 = Component.m_237115_((String)parent2Type.getTranslationName()).getString();
            list.add((Component)Component.m_237110_((String)"block.alexsmobs.terrapin_egg.desc", (Object[])new Object[]{s1, s2}).m_130940_(ChatFormatting.GRAY));
        }
    }

    public void m_6810_(BlockState state, Level level, BlockPos pos, BlockState state2, boolean b) {
        if (state.m_60713_((Block)AMBlockRegistry.TERRAPIN_EGG.get()) && (Integer)state.m_61143_((Property)EGGS) <= 1) {
            super.m_6810_(state, level, pos, state2, b);
        }
    }

    @Nullable
    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new TileEntityTerrapinEgg(pos, state);
    }
}

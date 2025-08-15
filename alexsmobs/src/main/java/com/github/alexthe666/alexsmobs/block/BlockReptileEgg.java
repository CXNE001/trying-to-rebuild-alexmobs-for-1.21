/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ambient.Bat
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
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
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
import com.github.alexthe666.alexsmobs.entity.EntityPlatypus;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.RegistryObject;

public class BlockReptileEgg
extends Block {
    public static final IntegerProperty HATCH = BlockStateProperties.f_61416_;
    public static final IntegerProperty EGGS = BlockStateProperties.f_61415_;
    private static final VoxelShape ONE_EGG_SHAPE = Block.m_49796_((double)3.0, (double)0.0, (double)3.0, (double)12.0, (double)7.0, (double)12.0);
    private static final VoxelShape MULTI_EGG_SHAPE = Block.m_49796_((double)1.0, (double)0.0, (double)1.0, (double)15.0, (double)7.0, (double)15.0);
    private final RegistryObject<EntityType> births;

    public BlockReptileEgg(RegistryObject births) {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283761_).m_60978_(0.5f).m_60918_(SoundType.f_56743_).m_60977_().m_60955_());
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)HATCH, (Comparable)Integer.valueOf(0))).m_61124_((Property)EGGS, (Comparable)Integer.valueOf(1)));
        this.births = births;
    }

    public static boolean hasProperHabitat(BlockGetter reader, BlockPos blockReader) {
        return BlockReptileEgg.isProperHabitat(reader, blockReader.m_7495_());
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
            AABB bb = new AABB((double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), (double)(pos.m_123341_() + 1), (double)(pos.m_123342_() + 1), (double)(pos.m_123343_() + 1)).m_82377_(25.0, 25.0, 25.0);
            if (trampler instanceof LivingEntity) {
                List list = worldIn.m_6443_(Mob.class, bb, living -> living.m_6084_() && living.m_6095_() == this.births.get());
                for (Mob living2 : list) {
                    if (living2 instanceof TamableAnimal && ((TamableAnimal)living2).m_21824_() && ((TamableAnimal)living2).m_21830_((LivingEntity)trampler)) continue;
                    living2.m_6710_((LivingEntity)trampler);
                }
            }
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
        if (this.canGrow((Level)worldIn) && BlockReptileEgg.hasProperHabitat((BlockGetter)worldIn, pos)) {
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
                    Entity fromType = ((EntityType)this.births.get()).m_20615_((Level)worldIn);
                    if (fromType instanceof Animal) {
                        Animal animal = (Animal)fromType;
                        animal.m_146762_(-24000);
                        animal.m_21446_(pos, 20);
                    }
                    Holder biome = worldIn.m_204166_(pos);
                    fromType.m_7678_((double)pos.m_123341_() + 0.3 + (double)j * 0.2, (double)pos.m_123342_(), (double)pos.m_123343_() + 0.3, 0.0f, 0.0f);
                    if (worldIn.f_46443_) continue;
                    Player closest = worldIn.m_5788_((double)((float)pos.m_123341_() + 0.5f), (double)((float)pos.m_123342_() + 0.5f), (double)((float)pos.m_123343_() + 0.5f), 20.0, EntitySelector.f_20408_);
                    if (closest != null) {
                        if (fromType instanceof TamableAnimal) {
                            TamableAnimal tamableAnimal = (TamableAnimal)fromType;
                            tamableAnimal.m_7105_(true);
                            tamableAnimal.m_21839_(true);
                            tamableAnimal.m_21828_(closest);
                        }
                        if (fromType instanceof EntityCrocodile) {
                            EntityCrocodile crocodile = (EntityCrocodile)fromType;
                            crocodile.setDesert(biome.m_203656_(AMTagRegistry.SPAWNS_DESERT_CROCODILES));
                        }
                    }
                    worldIn.m_7967_(fromType);
                }
            }
        }
    }

    public void m_6807_(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (BlockReptileEgg.hasProperHabitat((BlockGetter)worldIn, pos) && !worldIn.f_46443_) {
            worldIn.m_46796_(2005, pos, 0);
        }
    }

    private boolean canGrow(Level worldIn) {
        float f = worldIn.m_46942_(1.0f);
        if ((double)f < 0.8 && (double)f > 0.65) {
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
        if (!(trampler instanceof EntityCrocodile || trampler instanceof EntityCaiman || trampler instanceof EntityPlatypus || trampler instanceof Bat)) {
            if (!(trampler instanceof LivingEntity)) {
                return false;
            }
            return trampler instanceof Player || ForgeEventFactory.getMobGriefingEvent((Level)worldIn, (Entity)trampler);
        }
        return false;
    }
}

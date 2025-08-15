/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.CreativeModeTab$Output
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.ShulkerBoxBlock
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.item.CustomTabBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;

public class AMBlockItem
extends BlockItem
implements CustomTabBehavior {
    private final RegistryObject<Block> blockSupplier;

    public AMBlockItem(RegistryObject<Block> blockSupplier, Item.Properties props) {
        super((Block)null, props);
        this.blockSupplier = blockSupplier;
    }

    public Block m_40614_() {
        return (Block)this.blockSupplier.get();
    }

    public boolean canFitInsideCraftingRemainingItems() {
        return !(this.blockSupplier.get() instanceof ShulkerBoxBlock);
    }

    public void m_142023_(ItemEntity p_150700_) {
        ItemStack itemstack;
        CompoundTag compoundtag;
        if (this.blockSupplier.get() instanceof ShulkerBoxBlock && (compoundtag = AMBlockItem.m_186336_((ItemStack)(itemstack = p_150700_.m_32055_()))) != null && compoundtag.m_128425_("Items", 9)) {
            ListTag listtag = compoundtag.m_128437_("Items", 10);
            ItemUtils.m_150952_((ItemEntity)p_150700_, listtag.stream().map(CompoundTag.class::cast).map(ItemStack::m_41712_));
        }
    }

    public boolean m_41386_(DamageSource damage) {
        return super.m_41386_(damage) && (this != ((Block)AMBlockRegistry.TRANSMUTATION_TABLE.get()).m_5456_() || !damage.m_269533_(DamageTypeTags.f_268415_));
    }

    @Override
    public void fillItemCategory(CreativeModeTab.Output contents) {
        if (!this.blockSupplier.equals(AMBlockRegistry.SAND_CIRCLE) && !this.blockSupplier.equals(AMBlockRegistry.RED_SAND_CIRCLE)) {
            contents.m_246326_((ItemLike)this);
        }
    }

    public InteractionResult m_6225_(UseOnContext context) {
        return this.blockSupplier.equals(AMBlockRegistry.TRIOPS_EGGS) ? InteractionResult.PASS : super.m_6225_(context);
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
        if (this.blockSupplier.equals(AMBlockRegistry.TRIOPS_EGGS)) {
            BlockHitResult blockhitresult = AMBlockItem.m_41435_((Level)level, (Player)player, (ClipContext.Fluid)ClipContext.Fluid.SOURCE_ONLY);
            BlockHitResult blockhitresult1 = blockhitresult.m_82430_(blockhitresult.m_82425_().m_7494_());
            InteractionResult interactionresult = super.m_6225_(new UseOnContext(player, hand, blockhitresult1));
            return new InteractionResultHolder(interactionresult, (Object)player.m_21120_(hand));
        }
        return super.m_7203_(level, player, hand);
    }
}

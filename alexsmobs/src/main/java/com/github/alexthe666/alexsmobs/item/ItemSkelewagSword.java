/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.SwordItem
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.Tiers
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  net.minecraftforge.common.ToolAction
 *  net.minecraftforge.common.ToolActions
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.function.Consumer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class ItemSkelewagSword
extends SwordItem {
    private final ImmutableMultimap<Attribute, AttributeModifier> skelewagModifiers;

    public ItemSkelewagSword(Item.Properties props) {
        super((Tier)Tiers.IRON, 2, 0.0f, props);
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)Attributes.f_22281_, (Object)new AttributeModifier(f_41374_, "Weapon modifier", 3.5, AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.f_22283_, (Object)new AttributeModifier(f_41375_, "Weapon modifier", 0.0, AttributeModifier.Operation.ADDITION));
        this.skelewagModifiers = builder.build();
    }

    public float m_43299_() {
        return 3.5f;
    }

    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
    }

    public UseAnim m_6164_(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    public int m_8105_(ItemStack stack) {
        return 72000;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getISTERProperties());
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
        ItemStack lvt_4_1_ = player.m_21120_(hand);
        player.m_6672_(hand);
        return InteractionResultHolder.m_19096_((Object)lvt_4_1_);
    }

    public boolean m_6832_(ItemStack stack, ItemStack repairStack) {
        return repairStack.m_150930_(Items.f_42500_);
    }

    public Multimap<Attribute, AttributeModifier> m_7167_(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.skelewagModifiers : super.m_7167_(slot);
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.ElytraItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.item.AMArmorMaterial;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemTarantulaHawkElytra
extends ArmorItem {
    public ItemTarantulaHawkElytra(Item.Properties props, AMArmorMaterial mat) {
        super((ArmorMaterial)mat, ArmorItem.Type.CHESTPLATE, props);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getArmorRenderProperties());
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.m_41773_() < stack.m_41776_() - 1;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        return super.m_7203_(worldIn, playerIn, handIn);
    }

    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return ElytraItem.m_41140_((ItemStack)stack);
    }

    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.m_9236_().f_46443_ && (flightTicks + 1) % 20 == 0) {
            stack.m_41622_(1, entity, e -> e.m_21166_(EquipmentSlot.CHEST));
        }
        return true;
    }

    public boolean m_6832_(ItemStack toRepair, ItemStack repair) {
        return repair.m_41720_() == AMItemRegistry.TARANTULA_HAWK_WING_FRAGMENT.get();
    }

    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "alexsmobs:textures/armor/tarantula_hawk_elytra.png";
    }
}

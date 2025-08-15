/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorItem$Type
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  net.minecraftforge.common.ForgeMod
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.item.AMArmorMaterial;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;

public class ItemModArmor
extends ArmorItem {
    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B")};
    private Multimap<Attribute, AttributeModifier> attributeMapCroc;
    private Multimap<Attribute, AttributeModifier> attributeMapMoose;
    private Multimap<Attribute, AttributeModifier> attributeMapFlyingFish;
    private Multimap<Attribute, AttributeModifier> attributeMapKimono;

    public ItemModArmor(AMArmorMaterial armorMaterial, ArmorItem.Type slot) {
        super((ArmorMaterial)armorMaterial, slot, new Item.Properties());
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getArmorRenderProperties());
    }

    public void m_7373_(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (this.f_40379_ == AMItemRegistry.CENTIPEDE_ARMOR_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.centipede_leggings.desc").m_130940_(ChatFormatting.GRAY));
        }
        if (this.f_40379_ == AMItemRegistry.EMU_ARMOR_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.emu_leggings.desc").m_130940_(ChatFormatting.GRAY));
        }
        super.m_7373_(stack, worldIn, tooltip, flagIn);
        if (this.f_40379_ == AMItemRegistry.ROADRUNNER_ARMOR_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.roadrunner_boots.desc").m_130940_(ChatFormatting.BLUE));
        }
        if (this.f_40379_ == AMItemRegistry.RACCOON_ARMOR_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.frontier_cap.desc").m_130940_(ChatFormatting.BLUE));
        }
        if (this.f_40379_ == AMItemRegistry.FROSTSTALKER_ARMOR_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.froststalker_helmet.desc").m_130940_(ChatFormatting.AQUA));
        }
        if (this.f_40379_ == AMItemRegistry.ROCKY_ARMOR_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.rocky_chestplate.desc").m_130940_(ChatFormatting.GRAY));
        }
        if (this.f_40379_ == AMItemRegistry.SOMBRERO_ARMOR_MATERIAL && AlexsMobs.isAprilFools()) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.sombrero.special_desc").m_130940_(ChatFormatting.GRAY));
        }
        if (this.f_40379_ == AMItemRegistry.FLYING_FISH_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.flying_fish_boots.desc").m_130940_(ChatFormatting.GRAY));
        }
        if (this.f_40379_ == AMItemRegistry.NOVELTY_HAT_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.novelty_hat.desc").m_130940_(ChatFormatting.GRAY));
        }
        if (this.f_40379_ == AMItemRegistry.KIMONO_MATERIAL) {
            tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.unsettling_kimono.desc").m_130940_(ChatFormatting.GRAY));
        }
    }

    private void buildCrocAttributes(AMArmorMaterial materialIn) {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[this.f_265916_.ordinal()];
        builder.put((Object)Attributes.f_22284_, (Object)new AttributeModifier(uuid, "Armor modifier", (double)materialIn.m_7366_(this.f_265916_), AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.f_22285_, (Object)new AttributeModifier(uuid, "Armor toughness", (double)materialIn.m_6651_(), AttributeModifier.Operation.ADDITION));
        builder.put((Object)((Attribute)ForgeMod.SWIM_SPEED.get()), (Object)new AttributeModifier(uuid, "Swim speed", 1.0, AttributeModifier.Operation.ADDITION));
        if (this.f_40378_ > 0.0f) {
            builder.put((Object)Attributes.f_22278_, (Object)new AttributeModifier(uuid, "Armor knockback resistance", (double)this.f_40378_, AttributeModifier.Operation.ADDITION));
        }
        this.attributeMapCroc = builder.build();
    }

    private void buildFlyingFishAttributes(AMArmorMaterial materialIn) {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[this.f_265916_.ordinal()];
        builder.put((Object)Attributes.f_22284_, (Object)new AttributeModifier(uuid, "Armor modifier", (double)materialIn.m_7366_(this.f_265916_), AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.f_22285_, (Object)new AttributeModifier(uuid, "Armor toughness", (double)materialIn.m_6651_(), AttributeModifier.Operation.ADDITION));
        builder.put((Object)((Attribute)ForgeMod.SWIM_SPEED.get()), (Object)new AttributeModifier(uuid, "Swim speed", 0.5, AttributeModifier.Operation.ADDITION));
        this.attributeMapFlyingFish = builder.build();
    }

    private void buildMooseAttributes(AMArmorMaterial materialIn) {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[this.f_265916_.ordinal()];
        builder.put((Object)Attributes.f_22284_, (Object)new AttributeModifier(uuid, "Armor modifier", (double)materialIn.m_7366_(this.f_265916_), AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.f_22285_, (Object)new AttributeModifier(uuid, "Armor toughness", (double)materialIn.m_6651_(), AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.f_22282_, (Object)new AttributeModifier(uuid, "Knockback", 2.0, AttributeModifier.Operation.ADDITION));
        if (this.f_40378_ > 0.0f) {
            builder.put((Object)Attributes.f_22278_, (Object)new AttributeModifier(uuid, "Armor knockback resistance", (double)this.f_40378_, AttributeModifier.Operation.ADDITION));
        }
        this.attributeMapMoose = builder.build();
    }

    private void buildKimonoAttributes(AMArmorMaterial materialIn) {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[this.f_265916_.ordinal()];
        builder.put((Object)Attributes.f_22284_, (Object)new AttributeModifier(uuid, "Armor modifier", (double)materialIn.m_7366_(this.f_265916_), AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.f_22285_, (Object)new AttributeModifier(uuid, "Armor toughness", (double)materialIn.m_6651_(), AttributeModifier.Operation.ADDITION));
        builder.put((Object)((Attribute)ForgeMod.BLOCK_REACH.get()), (Object)new AttributeModifier(uuid, "Block Reach distance", 2.0, AttributeModifier.Operation.ADDITION));
        builder.put((Object)((Attribute)ForgeMod.ENTITY_REACH.get()), (Object)new AttributeModifier(uuid, "Entity Reach distance", 2.0, AttributeModifier.Operation.ADDITION));
        this.attributeMapKimono = builder.build();
    }

    public Multimap<Attribute, AttributeModifier> m_7167_(EquipmentSlot equipmentSlot) {
        if (this.m_40401_() == AMItemRegistry.CROCODILE_ARMOR_MATERIAL && equipmentSlot == this.f_265916_.m_266308_()) {
            if (this.attributeMapCroc == null) {
                this.buildCrocAttributes(AMItemRegistry.CROCODILE_ARMOR_MATERIAL);
            }
            return this.attributeMapCroc;
        }
        if (this.m_40401_() == AMItemRegistry.MOOSE_ARMOR_MATERIAL && equipmentSlot == this.f_265916_.m_266308_()) {
            if (this.attributeMapMoose == null) {
                this.buildMooseAttributes(AMItemRegistry.MOOSE_ARMOR_MATERIAL);
            }
            return this.attributeMapMoose;
        }
        if (this.m_40401_() == AMItemRegistry.FLYING_FISH_MATERIAL && equipmentSlot == this.f_265916_.m_266308_()) {
            if (this.attributeMapFlyingFish == null) {
                this.buildFlyingFishAttributes(AMItemRegistry.FLYING_FISH_MATERIAL);
            }
            return this.attributeMapFlyingFish;
        }
        if (this.m_40401_() == AMItemRegistry.KIMONO_MATERIAL && equipmentSlot == this.f_265916_.m_266308_()) {
            if (this.attributeMapKimono == null) {
                this.buildKimonoAttributes(AMItemRegistry.KIMONO_MATERIAL);
            }
            return this.attributeMapKimono;
        }
        return super.m_7167_(equipmentSlot);
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (this.f_40379_ == AMItemRegistry.CROCODILE_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/crocodile_chestplate.png";
        }
        if (this.f_40379_ == AMItemRegistry.ROADRUNNER_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/roadrunner_boots.png";
        }
        if (this.f_40379_ == AMItemRegistry.CENTIPEDE_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/centipede_leggings.png";
        }
        if (this.f_40379_ == AMItemRegistry.MOOSE_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/moose_headgear.png";
        }
        if (this.f_40379_ == AMItemRegistry.RACCOON_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/frontier_cap.png";
        }
        if (this.f_40379_ == AMItemRegistry.SOMBRERO_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/sombrero.png";
        }
        if (this.f_40379_ == AMItemRegistry.SPIKED_TURTLE_SHELL_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/spiked_turtle_shell.png";
        }
        if (this.f_40379_ == AMItemRegistry.FEDORA_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/fedora.png";
        }
        if (this.f_40379_ == AMItemRegistry.EMU_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/emu_leggings.png";
        }
        if (this.f_40379_ == AMItemRegistry.FROSTSTALKER_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/froststalker_helmet.png";
        }
        if (this.f_40379_ == AMItemRegistry.ROCKY_ARMOR_MATERIAL) {
            return "alexsmobs:textures/armor/rocky_chestplate.png";
        }
        if (this.f_40379_ == AMItemRegistry.FLYING_FISH_MATERIAL) {
            return "alexsmobs:textures/armor/flying_fish_boots.png";
        }
        if (this.f_40379_ == AMItemRegistry.NOVELTY_HAT_MATERIAL) {
            return "alexsmobs:textures/armor/novelty_hat.png";
        }
        if (this.f_40379_ == AMItemRegistry.KIMONO_MATERIAL) {
            return "alexsmobs:textures/armor/unsettling_kimono.png";
        }
        return super.getArmorTexture(stack, entity, slot, type);
    }
}

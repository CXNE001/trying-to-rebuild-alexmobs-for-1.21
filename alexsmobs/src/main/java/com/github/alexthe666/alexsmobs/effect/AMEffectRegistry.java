/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.common.brewing.BrewingRecipeRegistry
 *  net.minecraftforge.common.brewing.IBrewingRecipe
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.effect;

import com.github.alexthe666.alexsmobs.effect.EffectBugPheromones;
import com.github.alexthe666.alexsmobs.effect.EffectClinging;
import com.github.alexthe666.alexsmobs.effect.EffectDebilitatingSting;
import com.github.alexthe666.alexsmobs.effect.EffectEarthquake;
import com.github.alexthe666.alexsmobs.effect.EffectEnderFlu;
import com.github.alexthe666.alexsmobs.effect.EffectExsanguination;
import com.github.alexthe666.alexsmobs.effect.EffectFear;
import com.github.alexthe666.alexsmobs.effect.EffectFleetFooted;
import com.github.alexthe666.alexsmobs.effect.EffectKnockbackResistance;
import com.github.alexthe666.alexsmobs.effect.EffectLavaVision;
import com.github.alexthe666.alexsmobs.effect.EffectMosquitoRepellent;
import com.github.alexthe666.alexsmobs.effect.EffectOiled;
import com.github.alexthe666.alexsmobs.effect.EffectOrcaMight;
import com.github.alexthe666.alexsmobs.effect.EffectPoisonResistance;
import com.github.alexthe666.alexsmobs.effect.EffectPowerDown;
import com.github.alexthe666.alexsmobs.effect.EffectSoulsteal;
import com.github.alexthe666.alexsmobs.effect.EffectSunbird;
import com.github.alexthe666.alexsmobs.effect.EffectTigersBlessing;
import com.github.alexthe666.alexsmobs.effect.ProperBrewingRecipe;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AMEffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECT_DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.MOB_EFFECTS, (String)"alexsmobs");
    public static final DeferredRegister<Potion> POTION_DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.POTIONS, (String)"alexsmobs");
    public static final RegistryObject<MobEffect> KNOCKBACK_RESISTANCE = EFFECT_DEF_REG.register("knockback_resistance", () -> new EffectKnockbackResistance());
    public static final RegistryObject<MobEffect> LAVA_VISION = EFFECT_DEF_REG.register("lava_vision", () -> new EffectLavaVision());
    public static final RegistryObject<MobEffect> SUNBIRD_BLESSING = EFFECT_DEF_REG.register("sunbird_blessing", () -> new EffectSunbird(false));
    public static final RegistryObject<MobEffect> SUNBIRD_CURSE = EFFECT_DEF_REG.register("sunbird_curse", () -> new EffectSunbird(true));
    public static final RegistryObject<MobEffect> POISON_RESISTANCE = EFFECT_DEF_REG.register("poison_resistance", () -> new EffectPoisonResistance());
    public static final RegistryObject<MobEffect> OILED = EFFECT_DEF_REG.register("oiled", () -> new EffectOiled());
    public static final RegistryObject<MobEffect> ORCAS_MIGHT = EFFECT_DEF_REG.register("orcas_might", () -> new EffectOrcaMight());
    public static final RegistryObject<MobEffect> BUG_PHEROMONES = EFFECT_DEF_REG.register("bug_pheromones", () -> new EffectBugPheromones());
    public static final RegistryObject<MobEffect> SOULSTEAL = EFFECT_DEF_REG.register("soulsteal", () -> new EffectSoulsteal());
    public static final RegistryObject<MobEffect> CLINGING = EFFECT_DEF_REG.register("clinging", () -> new EffectClinging());
    public static final RegistryObject<MobEffect> ENDER_FLU = EFFECT_DEF_REG.register("ender_flu", () -> new EffectEnderFlu());
    public static final RegistryObject<MobEffect> FEAR = EFFECT_DEF_REG.register("fear", () -> new EffectFear());
    public static final RegistryObject<MobEffect> TIGERS_BLESSING = EFFECT_DEF_REG.register("tigers_blessing", () -> new EffectTigersBlessing());
    public static final RegistryObject<MobEffect> DEBILITATING_STING = EFFECT_DEF_REG.register("debilitating_sting", () -> new EffectDebilitatingSting());
    public static final RegistryObject<MobEffect> EXSANGUINATION = EFFECT_DEF_REG.register("exsanguination", () -> new EffectExsanguination());
    public static final RegistryObject<MobEffect> EARTHQUAKE = EFFECT_DEF_REG.register("earthquake", () -> new EffectEarthquake());
    public static final RegistryObject<MobEffect> FLEET_FOOTED = EFFECT_DEF_REG.register("fleet_footed", () -> new EffectFleetFooted());
    public static final RegistryObject<MobEffect> POWER_DOWN = EFFECT_DEF_REG.register("power_down", () -> new EffectPowerDown());
    public static final RegistryObject<MobEffect> MOSQUITO_REPELLENT = EFFECT_DEF_REG.register("mosquito_repellent", () -> new EffectMosquitoRepellent());
    public static final RegistryObject<Potion> KNOCKBACK_RESISTANCE_POTION = POTION_DEF_REG.register("knockback_resistance", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)KNOCKBACK_RESISTANCE.get(), 3600)}));
    public static final RegistryObject<Potion> LONG_KNOCKBACK_RESISTANCE_POTION = POTION_DEF_REG.register("long_knockback_resistance", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)KNOCKBACK_RESISTANCE.get(), 9600)}));
    public static final RegistryObject<Potion> STRONG_KNOCKBACK_RESISTANCE_POTION = POTION_DEF_REG.register("strong_knockback_resistance", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)KNOCKBACK_RESISTANCE.get(), 1800, 1)}));
    public static final RegistryObject<Potion> LAVA_VISION_POTION = POTION_DEF_REG.register("lava_vision", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)LAVA_VISION.get(), 3600)}));
    public static final RegistryObject<Potion> LONG_LAVA_VISION_POTION = POTION_DEF_REG.register("long_lava_vision", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)LAVA_VISION.get(), 9600)}));
    public static final RegistryObject<Potion> SPEED_III_POTION = POTION_DEF_REG.register("speed_iii", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.f_19596_, 2200, 2)}));
    public static final RegistryObject<Potion> POISON_RESISTANCE_POTION = POTION_DEF_REG.register("poison_resistance", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)POISON_RESISTANCE.get(), 3600)}));
    public static final RegistryObject<Potion> LONG_POISON_RESISTANCE_POTION = POTION_DEF_REG.register("long_poison_resistance", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)POISON_RESISTANCE.get(), 9600)}));
    public static final RegistryObject<Potion> BUG_PHEROMONES_POTION = POTION_DEF_REG.register("bug_pheromones", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)BUG_PHEROMONES.get(), 3600)}));
    public static final RegistryObject<Potion> LONG_BUG_PHEROMONES_POTION = POTION_DEF_REG.register("long_bug_pheromones", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)BUG_PHEROMONES.get(), 9600)}));
    public static final RegistryObject<Potion> SOULSTEAL_POTION = POTION_DEF_REG.register("soulsteal", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)SOULSTEAL.get(), 3600)}));
    public static final RegistryObject<Potion> LONG_SOULSTEAL_POTION = POTION_DEF_REG.register("long_soulsteal", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)SOULSTEAL.get(), 9600)}));
    public static final RegistryObject<Potion> STRONG_SOULSTEAL_POTION = POTION_DEF_REG.register("strong_soulsteal", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)SOULSTEAL.get(), 1800, 1)}));
    public static final RegistryObject<Potion> CLINGING_POTION = POTION_DEF_REG.register("clinging", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)CLINGING.get(), 3600)}));
    public static final RegistryObject<Potion> LONG_CLINGING_POTION = POTION_DEF_REG.register("long_clinging", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)CLINGING.get(), 9600)}));

    public static ItemStack createPotion(RegistryObject<Potion> potion) {
        return PotionUtils.m_43549_((ItemStack)new ItemStack((ItemLike)Items.f_42589_), (Potion)((Potion)potion.get()));
    }

    public static ItemStack createPotion(Potion potion) {
        return PotionUtils.m_43549_((ItemStack)new ItemStack((ItemLike)Items.f_42589_), (Potion)potion);
    }

    public static void init() {
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(Potions.f_43590_)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.BEAR_FUR.get()}), AMEffectRegistry.createPotion(KNOCKBACK_RESISTANCE_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(KNOCKBACK_RESISTANCE_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42451_}), AMEffectRegistry.createPotion(LONG_KNOCKBACK_RESISTANCE_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(KNOCKBACK_RESISTANCE_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42525_}), AMEffectRegistry.createPotion(STRONG_KNOCKBACK_RESISTANCE_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.LAVA_BOTTLE.get()}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.BONE_SERPENT_TOOTH.get()}), AMEffectRegistry.createPotion(LAVA_VISION_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(LAVA_VISION_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42451_}), AMEffectRegistry.createPotion(LONG_LAVA_VISION_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(Potions.f_43584_)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.RATTLESNAKE_RATTLE.get()}), new ItemStack((ItemLike)AMItemRegistry.POISON_BOTTLE.get())));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.POISON_BOTTLE.get()}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.CENTIPEDE_LEG.get()}), AMEffectRegistry.createPotion(POISON_RESISTANCE_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.KOMODO_SPIT_BOTTLE.get()}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.CENTIPEDE_LEG.get()}), AMEffectRegistry.createPotion(POISON_RESISTANCE_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(POISON_RESISTANCE_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.KOMODO_SPIT.get()}), AMEffectRegistry.createPotion(LONG_POISON_RESISTANCE_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(Potions.f_43614_)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.GAZELLE_HORN.get()}), AMEffectRegistry.createPotion(SPEED_III_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(Potions.f_43602_)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.COCKROACH_WING.get()}), AMEffectRegistry.createPotion(BUG_PHEROMONES_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(BUG_PHEROMONES_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42451_}), AMEffectRegistry.createPotion(LONG_BUG_PHEROMONES_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(Potions.f_43602_)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.SOUL_HEART.get()}), AMEffectRegistry.createPotion(SOULSTEAL_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(SOULSTEAL_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42451_}), AMEffectRegistry.createPotion(LONG_SOULSTEAL_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(SOULSTEAL_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42525_}), AMEffectRegistry.createPotion(STRONG_SOULSTEAL_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(Potions.f_43602_)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.DROPBEAR_CLAW.get()}), AMEffectRegistry.createPotion(CLINGING_POTION)));
        BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new ProperBrewingRecipe(Ingredient.m_43927_((ItemStack[])new ItemStack[]{AMEffectRegistry.createPotion(CLINGING_POTION)}), Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42451_}), AMEffectRegistry.createPotion(LONG_CLINGING_POTION)));
    }
}

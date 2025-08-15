/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.structure.Structure
 */
package com.github.alexthe666.alexsmobs.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class AMTagRegistry {
    public static final TagKey<Item> INSECT_ITEMS = AMTagRegistry.registerItemTag("insect_items");
    public static final TagKey<Block> GRIZZLY_BEEHIVE = AMTagRegistry.registerBlockTag("grizzly_beehive");
    public static final TagKey<Item> GRIZZLY_FOODSTUFFS = AMTagRegistry.registerItemTag("grizzly_foodstuffs");
    public static final TagKey<Item> GRIZZLY_HONEY = AMTagRegistry.registerItemTag("grizzly_honey");
    public static final TagKey<EntityType<?>> FLY_TARGETS = AMTagRegistry.registerEntityTag("fly_hurt_targets");
    public static final TagKey<EntityType<?>> FLY_ANNOY_TARGETS = AMTagRegistry.registerEntityTag("fly_annoy_targets");
    public static final TagKey<Block> DROPS_BANANAS = AMTagRegistry.registerBlockTag("drops_bananas");
    public static final TagKey<Block> DROPS_ACACIA_BLOSSOMS = AMTagRegistry.registerBlockTag("drops_acacia_blossoms");
    public static final TagKey<Block> ORCA_BREAKABLES = AMTagRegistry.registerBlockTag("orca_breakables");
    public static final TagKey<EntityType<?>> SUNBIRD_SCORCH_TARGETS = AMTagRegistry.registerEntityTag("sunbird_scorch_targets");
    public static final TagKey<Block> GORILLA_BREAKABLES = AMTagRegistry.registerBlockTag("gorilla_breakables");
    public static final TagKey<Item> GORILLA_FOODSTUFFS = AMTagRegistry.registerItemTag("gorilla_foodstuffs");
    public static final TagKey<EntityType<?>> ORCA_TARGETS = AMTagRegistry.registerEntityTag("orca_targets");
    public static final TagKey<EntityType<?>> CRIMSON_MOSQUITO_TARGETS = AMTagRegistry.registerEntityTag("crimson_mosquito_targets");
    public static final TagKey<EntityType<?>> WARPED_TOAD_TARGETS = AMTagRegistry.registerEntityTag("warped_toad_targets");
    public static final TagKey<EntityType<?>> CROCODILE_TARGETS = AMTagRegistry.registerEntityTag("crocodile_targets");
    public static final TagKey<EntityType<?>> KOMODO_DRAGON_TARGETS = AMTagRegistry.registerEntityTag("komodo_dragon_targets");
    public static final TagKey<EntityType<?>> MANTIS_SHRIMP_TARGETS = AMTagRegistry.registerEntityTag("mantis_shrimp_targets");
    public static final TagKey<EntityType<?>> VOID_PORTAL_IGNORES = AMTagRegistry.registerEntityTag("void_portal_ignores");
    public static final TagKey<EntityType<?>> CATFISH_IGNORE_EATING = AMTagRegistry.registerEntityTag("catfish_ignore_eating");
    public static final TagKey<EntityType<?>> SKUNK_FEARS = AMTagRegistry.registerEntityTag("skunk_fears");
    public static final TagKey<EntityType<?>> CAIMAN_TARGETS = AMTagRegistry.registerEntityTag("caiman_targets");
    public static final TagKey<Item> BANANAS = AMTagRegistry.registerItemTag("bananas");
    public static final TagKey<Item> RACCOON_FOODSTUFFS = AMTagRegistry.registerItemTag("raccoon_foodstuffs");
    public static final TagKey<Block> SEAL_DIGABLES = AMTagRegistry.registerBlockTag("seal_digables");
    public static final TagKey<Item> SHOEBILL_FOODSTUFFS = AMTagRegistry.registerItemTag("shoebill_foodstuffs");
    public static final TagKey<Block> ELEPHANT_FOODBLOCKS = AMTagRegistry.registerBlockTag("elephant_foodblocks");
    public static final TagKey<Item> ELEPHANT_FOODSTUFFS = AMTagRegistry.registerItemTag("elephant_foodstuffs");
    public static final TagKey<Block> SOUL_VULTURE_PERCHES = AMTagRegistry.registerBlockTag("soul_vulture_perches");
    public static final TagKey<Block> SOUL_VULTURE_SPAWNS = AMTagRegistry.registerBlockTag("soul_vulture_spawns");
    public static final TagKey<EntityType<?>> SNOW_LEOPARD_TARGETS = AMTagRegistry.registerEntityTag("snow_leopard_targets");
    public static final TagKey<EntityType<?>> SCATTERS_CROWS = AMTagRegistry.registerEntityTag("scatters_crows");
    public static final TagKey<Block> CROW_FOODBLOCKS = AMTagRegistry.registerBlockTag("crow_foodblocks");
    public static final TagKey<Item> CROW_FOODSTUFFS = AMTagRegistry.registerItemTag("crow_foodstuffs");
    public static final TagKey<Block> MUNGUS_REPLACE_MUSHROOM = AMTagRegistry.registerBlockTag("mungus_replace_mushroom");
    public static final TagKey<Block> MUNGUS_REPLACE_NETHER = AMTagRegistry.registerBlockTag("mungus_replace_nether");
    public static final TagKey<Block> WARPED_MOSCO_BREAKABLES = AMTagRegistry.registerBlockTag("warped_mosco_breakables");
    public static final TagKey<Block> CROW_FEARS = AMTagRegistry.registerBlockTag("crow_fears");
    public static final TagKey<Item> PLATYPUS_FOODSTUFFS = AMTagRegistry.registerItemTag("platypus_foodstuffs");
    public static final TagKey<EntityType<?>> CACHALOT_WHALE_TARGETS = AMTagRegistry.registerEntityTag("cachalot_whale_targets");
    public static final TagKey<Block> CACHALOT_WHALE_BREAKABLES = AMTagRegistry.registerBlockTag("cachalot_whale_breakables");
    public static final TagKey<Block> LEAFCUTTER_ANT_BREAKABLES = AMTagRegistry.registerBlockTag("leafcutter_ant_breakables");
    public static final TagKey<EntityType<?>> TIGER_TARGETS = AMTagRegistry.registerEntityTag("tiger_targets");
    public static final TagKey<EntityType<?>> BALD_EAGLE_TARGETS = AMTagRegistry.registerEntityTag("bald_eagle_targets");
    public static final TagKey<Block> VOID_WORM_BREAKABLES = AMTagRegistry.registerBlockTag("void_worm_breakables");
    public static final TagKey<EntityType<?>> MIMIC_OCTOPUS_FEARS = AMTagRegistry.registerEntityTag("mimic_octopus_fears");
    public static final TagKey<Item> MIMIC_OCTOPUS_CREEPER_ITEMS = AMTagRegistry.registerItemTag("mimic_octopus_creeper_items");
    public static final TagKey<Item> MIMIC_OCTOPUS_GUARDIAN_ITEMS = AMTagRegistry.registerItemTag("mimic_octopus_guardian_items");
    public static final TagKey<Item> MIMIC_OCTOPUS_PUFFERFISH_ITEMS = AMTagRegistry.registerItemTag("mimic_octopus_pufferfish_items");
    public static final TagKey<Item> SHRIMP_RICE_FRYABLES = AMTagRegistry.registerItemTag("shrimp_rice_fryables");
    public static final TagKey<Item> TIGER_BREEDABLES = AMTagRegistry.registerItemTag("tiger_breedables");
    public static final TagKey<Item> BALD_EAGLE_TAMEABLES = AMTagRegistry.registerItemTag("bald_eagle_tameables");
    public static final TagKey<Item> VOID_WORM_DROPS = AMTagRegistry.registerItemTag("void_worm_drops");
    public static final TagKey<Item> UNDERMINER_ORES = AMTagRegistry.registerItemTag("underminer_ores");
    public static final TagKey<Item> BLUE_JAY_FOODSTUFFS = AMTagRegistry.registerItemTag("blue_jay_foodstuffs");
    public static final TagKey<Item> ALLIGATOR_SNAPPING_TURTLE_BREEDABLES = AMTagRegistry.registerItemTag("alligator_snapping_turtle_breedables");
    public static final TagKey<Item> ANACONDA_FOODSTUFFS = AMTagRegistry.registerItemTag("anaconda_foodstuffs");
    public static final TagKey<Item> ANTEATER_BREEDABLES = AMTagRegistry.registerItemTag("anteater_breedables");
    public static final TagKey<Item> ANTEATER_FOODSTUFFS = AMTagRegistry.registerItemTag("anteater_foodstuffs");
    public static final TagKey<Item> BALD_EAGLE_BREEDABLES = AMTagRegistry.registerItemTag("bald_eagle_breedables");
    public static final TagKey<Item> BALD_EAGLE_FOODSTUFFS = AMTagRegistry.registerItemTag("bald_eagle_foodstuffs");
    public static final TagKey<Item> BANANA_SLUG_BREEDABLES = AMTagRegistry.registerItemTag("banana_slug_breedables");
    public static final TagKey<Item> BISON_BREEDABLES = AMTagRegistry.registerItemTag("bison_breedables");
    public static final TagKey<Item> BLUE_JAY_TEAMING_FOODS = AMTagRegistry.registerItemTag("blue_jay_teaming_foods");
    public static final TagKey<Item> BLUE_JAY_BREEDABLES = AMTagRegistry.registerItemTag("blue_jay_breedables");
    public static final TagKey<Item> BLUE_JAY_ALERT_FOODS = AMTagRegistry.registerItemTag("blue_jay_alert_foods");
    public static final TagKey<Item> BUNFUNGUS_FOODSTUFFS = AMTagRegistry.registerItemTag("bunfungus_foodstuffs");
    public static final TagKey<Item> CAIMAN_BREEDABLES = AMTagRegistry.registerItemTag("caiman_breedables");
    public static final TagKey<Item> CAIMAN_FOODSTUFFS = AMTagRegistry.registerItemTag("caiman_foodstuffs");
    public static final TagKey<Item> CAPUCHIN_MONKEY_TAMEABLES = AMTagRegistry.registerItemTag("capuchin_monkey_tameables");
    public static final TagKey<Item> CAPUCHIN_MONKEY_BREEDABLES = AMTagRegistry.registerItemTag("capuchin_monkey_breedables");
    public static final TagKey<Item> CAPUCHIN_MONKEY_FOODSTUFFS = AMTagRegistry.registerItemTag("capuchin_monkey_foodstuffs");
    public static final TagKey<Item> CATFISH_ITEM_FASCINATIONS = AMTagRegistry.registerItemTag("catfish_item_fascinations");
    public static final TagKey<Item> COCKROACH_BREEDABLES = AMTagRegistry.registerItemTag("cockroach_breedables");
    public static final TagKey<Item> COCKROACH_FOODSTUFFS = AMTagRegistry.registerItemTag("cockroach_foodstuffs");
    public static final TagKey<Item> COSMAW_TAMEABLES = AMTagRegistry.registerItemTag("cosmaw_tameables");
    public static final TagKey<Item> COSMAW_BREEDABLES = AMTagRegistry.registerItemTag("cosmaw_breedables");
    public static final TagKey<Item> COSMAW_FOODSTUFFS = AMTagRegistry.registerItemTag("cosmaw_foodstuffs");
    public static final TagKey<Item> CROCODILE_BREEDABLES = AMTagRegistry.registerItemTag("crocodile_breedables");
    public static final TagKey<Item> CROW_TAMEABLES = AMTagRegistry.registerItemTag("crow_tameables");
    public static final TagKey<Item> CROW_BREEDABLES = AMTagRegistry.registerItemTag("crow_breedables");
    public static final TagKey<Item> ELEPHANT_TAMEABLES = AMTagRegistry.registerItemTag("elephant_tameables");
    public static final TagKey<Item> ELEPHANT_BREEDABLES = AMTagRegistry.registerItemTag("elephant_breedables");
    public static final TagKey<Item> EMU_BREEDABLES = AMTagRegistry.registerItemTag("emu_breedables");
    public static final TagKey<Item> ENDERGRADE_BREEDABLES = AMTagRegistry.registerItemTag("endergrade_breedables");
    public static final TagKey<Item> ENDERGRADE_FOLLOWS = AMTagRegistry.registerItemTag("endergrade_follows");
    public static final TagKey<Item> ENDERGRADE_FOODSTUFFS = AMTagRegistry.registerItemTag("endergrade_foodstuffs");
    public static final TagKey<Item> FLUTTER_BREEDABLES = AMTagRegistry.registerItemTag("flutter_breedables");
    public static final TagKey<Item> FLY_BREEDABLES = AMTagRegistry.registerItemTag("fly_breedables");
    public static final TagKey<Item> FLY_FOODSTUFFS = AMTagRegistry.registerItemTag("fly_foodstuffs");
    public static final TagKey<Item> FROSTSTALKER_BREEDABLES = AMTagRegistry.registerItemTag("froststalker_breedables");
    public static final TagKey<Item> GAZELLE_BREEDABLES = AMTagRegistry.registerItemTag("gazelle_breedables");
    public static final TagKey<Item> GELADA_MONKEY_BREEDABLES = AMTagRegistry.registerItemTag("gelada_monkey_breedables");
    public static final TagKey<Item> GELADA_MONKEY_LAND_CLEARING_FOODS = AMTagRegistry.registerItemTag("gelada_monkey_land_clearing_foods");
    public static final TagKey<Item> GORILLA_BREEDABLES = AMTagRegistry.registerItemTag("gorilla_breedables");
    public static final TagKey<Item> GORILLA_TAMEABLES = AMTagRegistry.registerItemTag("gorilla_tameables");
    public static final TagKey<Item> GRIZZLY_BREEDABLES = AMTagRegistry.registerItemTag("grizzly_breedables");
    public static final TagKey<Item> GRIZZLY_TAMEABLES = AMTagRegistry.registerItemTag("grizzly_tameables");
    public static final TagKey<Item> HUMMINGBIRD_BREEDABLES = AMTagRegistry.registerItemTag("hummingbird_breedables");
    public static final TagKey<Item> HUMMINGNBIRD_FEEDER_SWEETENERS = AMTagRegistry.registerItemTag("hummingnbird_feeder_sweeteners");
    public static final TagKey<Item> JERBOA_BREEDABLES = AMTagRegistry.registerItemTag("jerboa_breedables");
    public static final TagKey<Item> JERBOA_BEGS_FOR = AMTagRegistry.registerItemTag("jerboa_begs_for");
    public static final TagKey<Item> KANGAROO_BREEDABLES = AMTagRegistry.registerItemTag("kangaroo_breedables");
    public static final TagKey<Item> KANGAROO_TAMEABLES = AMTagRegistry.registerItemTag("kangaroo_tameables");
    public static final TagKey<Item> KOMODO_DRAGON_BREEDABLES = AMTagRegistry.registerItemTag("komodo_dragon_breedables");
    public static final TagKey<Item> KOMODO_DRAGON_TAMEABLES = AMTagRegistry.registerItemTag("komodo_dragon_tameables");
    public static final TagKey<Item> LAVIATHAN_BREEDABLES = AMTagRegistry.registerItemTag("laviathan_breedables");
    public static final TagKey<Item> LAVIATHAN_FOODSTUFFS = AMTagRegistry.registerItemTag("laviathan_foodstuffs");
    public static final TagKey<Item> LEAFCUTTER_ANT_FOODSTUFFS = AMTagRegistry.registerItemTag("leafcutter_ant_foodstuffs");
    public static final TagKey<Item> MANED_WOLF_BREEDABLES = AMTagRegistry.registerItemTag("maned_wolf_breedables");
    public static final TagKey<Item> MANED_WOLF_STENCH_FOODS = AMTagRegistry.registerItemTag("maned_wolf_stench_foods");
    public static final TagKey<Item> MANTIS_SHRIMP_BREEDABLES = AMTagRegistry.registerItemTag("mantis_shrimp_breedables");
    public static final TagKey<Item> MANTIS_SHRIMP_TAMEABLES = AMTagRegistry.registerItemTag("mantis_shrimp_tameables");
    public static final TagKey<Item> MIMIC_OCTOPUS_BREEDABLES = AMTagRegistry.registerItemTag("mimic_octopus_breedables");
    public static final TagKey<Item> MIMIC_OCTOPUS_TAMEABLES = AMTagRegistry.registerItemTag("mimic_octopus_tameables");
    public static final TagKey<Item> MIMIC_OCTOPUS_ATTACK_FOODS = AMTagRegistry.registerItemTag("mimic_octopus_attack_foods");
    public static final TagKey<Item> MIMIC_OCTOPUS_TOGGLES_MIMIC = AMTagRegistry.registerItemTag("mimic_octopus_toggles_mimic");
    public static final TagKey<Item> MIMIC_OCTOPUS_MOISTURIZES = AMTagRegistry.registerItemTag("mimic_octopus_moisturizes");
    public static final TagKey<Item> MOOSE_BREEDABLES = AMTagRegistry.registerItemTag("moose_breedables");
    public static final TagKey<Item> MUDSKIPPER_BREEDABLES = AMTagRegistry.registerItemTag("mudskipper_breedables");
    public static final TagKey<Item> MUDSKIPPER_TAMEABLES = AMTagRegistry.registerItemTag("mudskipper_tameables");
    public static final TagKey<Item> MUDSKIPPER_FOODSTUFFS = AMTagRegistry.registerItemTag("mudskipper_foodstuffs");
    public static final TagKey<Item> MUNGUS_BREEDABLES = AMTagRegistry.registerItemTag("mungus_breedables");
    public static final TagKey<Item> PLATYPUS_BREEDABLES = AMTagRegistry.registerItemTag("platypus_breedables");
    public static final TagKey<Item> PLATYPUS_CHARGEABLES = AMTagRegistry.registerItemTag("platypus_chargeables");
    public static final TagKey<Item> PLATYPUS_SUPER_CHARGEABLES = AMTagRegistry.registerItemTag("platypus_super_chargeables");
    public static final TagKey<Item> POTOO_BREEDABLES = AMTagRegistry.registerItemTag("potoo_breedables");
    public static final TagKey<Item> RACCOON_BREEDABLES = AMTagRegistry.registerItemTag("raccoon_breedables");
    public static final TagKey<Item> RACCOON_TAMEABLES = AMTagRegistry.registerItemTag("raccoon_tameables");
    public static final TagKey<Item> RACCOON_TEAMING_FOODS = AMTagRegistry.registerItemTag("raccoon_teaming_foods");
    public static final TagKey<Item> RAIN_FROG_BREEDABLES = AMTagRegistry.registerItemTag("rain_frog_breedables");
    public static final TagKey<Item> RHINOCEROS_BREEDABLES = AMTagRegistry.registerItemTag("rhinoceros_breedables");
    public static final TagKey<Item> RHINOCEROS_FOODSTUFFS = AMTagRegistry.registerItemTag("rhinoceros_foodstuffs");
    public static final TagKey<Item> ROADRUNNER_BREEDABLES = AMTagRegistry.registerItemTag("roadrunner_breedables");
    public static final TagKey<Item> SEAGULL_BREEDABLES = AMTagRegistry.registerItemTag("seagull_breedables");
    public static final TagKey<Item> SEAGULL_OFFERINGS = AMTagRegistry.registerItemTag("seagull_offerings");
    public static final TagKey<Item> SEAL_BREEDABLES = AMTagRegistry.registerItemTag("seal_breedables");
    public static final TagKey<Item> SEAL_OFFERINGS = AMTagRegistry.registerItemTag("seal_offerings");
    public static final TagKey<Item> SHOEBILL_LURE_FOODS = AMTagRegistry.registerItemTag("shoebill_lure_foods");
    public static final TagKey<Item> SHOEBILL_LUCK_FOODS = AMTagRegistry.registerItemTag("shoebill_luck_foods");
    public static final TagKey<Item> SKUNK_BREEDABLES = AMTagRegistry.registerItemTag("skunk_breedables");
    public static final TagKey<Item> SNOW_LEOPARD_BREEDABLES = AMTagRegistry.registerItemTag("snow_leopard_breedables");
    public static final TagKey<Item> STRADPOLE_GROWABLES = AMTagRegistry.registerItemTag("stradpole_growables");
    public static final TagKey<Item> SUGAR_GLIDER_BREEDABLES = AMTagRegistry.registerItemTag("sugar_glider_breedables");
    public static final TagKey<Item> SUGAR_GLIDER_TAMEABLES = AMTagRegistry.registerItemTag("sugar_glider_tameables");
    public static final TagKey<Item> TARANTULA_HAWK_BREEDABLES = AMTagRegistry.registerItemTag("tarantula_hawk_breedables");
    public static final TagKey<Item> TARANTULA_HAWK_TAMEABLES = AMTagRegistry.registerItemTag("tarantula_hawk_tameables");
    public static final TagKey<Item> TARANTULA_HAWK_FOODSTUFFS = AMTagRegistry.registerItemTag("tarantula_hawk_foodstuffs");
    public static final TagKey<Item> TASMANIAN_DEVIL_HOWLING_FOODS = AMTagRegistry.registerItemTag("tasmanian_devil_howling_foods");
    public static final TagKey<Item> TERRAPIN_BREEDABLES = AMTagRegistry.registerItemTag("terrapin_breedables");
    public static final TagKey<Item> TOUCAN_BREEDABLES = AMTagRegistry.registerItemTag("toucan_breedables");
    public static final TagKey<Item> TOUCAN_GOLDEN_FOODS = AMTagRegistry.registerItemTag("toucan_golden_foods");
    public static final TagKey<Item> TOUCAN_ENCHANTED_GOLDEN_FOODS = AMTagRegistry.registerItemTag("toucan_enchanted_golden_foods");
    public static final TagKey<Item> TRIOPS_BREEDABLES = AMTagRegistry.registerItemTag("triops_breedables");
    public static final TagKey<Item> TUSKLIN_BREEDABLES = AMTagRegistry.registerItemTag("tusklin_breedables");
    public static final TagKey<Item> TUSKLIN_FOODSTUFFS = AMTagRegistry.registerItemTag("tusklin_foodstuffs");
    public static final TagKey<Item> WARPED_TOAD_BREEDABLES = AMTagRegistry.registerItemTag("warped_toad_breedables");
    public static final TagKey<Item> WARPED_TOAD_TAMEABLES = AMTagRegistry.registerItemTag("warped_toad_tameables");
    public static final TagKey<Item> WARPED_TOAD_FOODSTUFFS = AMTagRegistry.registerItemTag("warped_toad_foodstuffs");
    public static final TagKey<Block> CATFISH_BLOCK_FASCINATIONS = AMTagRegistry.registerBlockTag("catfish_block_fascinations");
    public static final TagKey<Block> CROW_HOME_BLOCKS = AMTagRegistry.registerBlockTag("crow_home_blocks");
    public static final TagKey<Block> CAIMAN_SPAWNS = AMTagRegistry.registerBlockTag("caiman_spawns");
    public static final TagKey<Block> CAPUCHIN_MONKEY_SPAWNS = AMTagRegistry.registerBlockTag("capuchin_monkey_spawns");
    public static final TagKey<Block> ENDERGRADE_BREAKABLES = AMTagRegistry.registerBlockTag("endergrade_breakables");
    public static final TagKey<Block> FLUTTER_SPAWNS = AMTagRegistry.registerBlockTag("flutter_spawns");
    public static final TagKey<Block> FROSTSTALKER_SPAWNS = AMTagRegistry.registerBlockTag("froststalker_spawns");
    public static final TagKey<Block> GORILLA_SPAWNS = AMTagRegistry.registerBlockTag("gorilla_spawns");
    public static final TagKey<Block> HUMMINGBIRD_SPAWNS = AMTagRegistry.registerBlockTag("hummingbird_spawns");
    public static final TagKey<Block> HUMMINGBIRD_POLLINATES = AMTagRegistry.registerBlockTag("hummingbird_pollinates");
    public static final TagKey<Block> PLATYPUS_DIGABLES = AMTagRegistry.registerBlockTag("platypus_digables");
    public static final TagKey<Block> RAIN_FROG_SPAWNS = AMTagRegistry.registerBlockTag("rain_frog_spawns");
    public static final TagKey<Block> ROCKY_ROLLER_SPAWNS = AMTagRegistry.registerBlockTag("rocky_roller_spawns");
    public static final TagKey<Block> SNOW_LEOPARD_SPAWNS = AMTagRegistry.registerBlockTag("snow_leopard_spawns");
    public static final TagKey<Block> TARANTULA_HAWK_SPAWNS = AMTagRegistry.registerBlockTag("tarantula_hawk_spawns");
    public static final TagKey<Block> TUSKLIN_SPAWNS = AMTagRegistry.registerBlockTag("tusklin_spawns");
    public static final TagKey<EntityType<?>> FROSTSTALKER_TARGETS = AMTagRegistry.registerEntityTag("froststalker_targets");
    public static final TagKey<Block> FROSTSTALKER_FEARS = AMTagRegistry.registerBlockTag("froststalker_fears");
    public static final TagKey<EntityType<?>> ANACONDA_TARGETS = AMTagRegistry.registerEntityTag("anaconda_targets");
    public static final TagKey<Block> GELADA_MONKEY_GRASS = AMTagRegistry.registerBlockTag("gelada_monkey_grass");
    public static final TagKey<EntityType<?>> GIANT_SQUID_TARGETS = AMTagRegistry.registerEntityTag("giant_squid_targets");
    public static final TagKey<EntityType<?>> MONKEY_TARGET_WITH_DART = AMTagRegistry.registerEntityTag("monkey_target_with_dart");
    public static final TagKey<Item> RACOON_DISSOLVES = AMTagRegistry.registerItemTag("raccoon_dissolves");
    public static final TagKey<Block> ROADRUNNER_SPAWNS = AMTagRegistry.registerBlockTag("roadrunner_spawns");
    public static final TagKey<Block> LOBSTER_SPAWNS = AMTagRegistry.registerBlockTag("lobster_spawns");
    public static final TagKey<Block> MIMIC_OCTOPUS_SPAWNS = AMTagRegistry.registerBlockTag("mimic_octopus_spawns");
    public static final TagKey<Block> RATTLESNAKE_SPAWNS = AMTagRegistry.registerBlockTag("rattlesnake_spawns");
    public static final TagKey<Block> KOMODO_DRAGON_SPAWNS = AMTagRegistry.registerBlockTag("komodo_dragon_spawns");
    public static final TagKey<Block> CROCODILE_SPAWNS = AMTagRegistry.registerBlockTag("crocodile_spawns");
    public static final TagKey<Block> SEAL_SPAWNS = AMTagRegistry.registerBlockTag("seal_spawns");
    public static final TagKey<Block> ALLIGATOR_SNAPPING_TURTLE_SPAWNS = AMTagRegistry.registerBlockTag("alligator_snapping_turtle_spawns");
    public static final TagKey<Block> MANTIS_SHRIMP_SPAWNS = AMTagRegistry.registerBlockTag("mantis_shrimp_spawns");
    public static final TagKey<Block> EMU_SPAWNS = AMTagRegistry.registerBlockTag("emu_spawns");
    public static final TagKey<Block> KANGAROO_SPAWNS = AMTagRegistry.registerBlockTag("kangaroo_spawns");
    public static final TagKey<Block> PLATYPUS_SPAWNS = AMTagRegistry.registerBlockTag("platypus_spawns");
    public static final TagKey<Block> ANACONDA_SPAWNS = AMTagRegistry.registerBlockTag("anaconda_spawns");
    public static final TagKey<Block> FLY_SPAWNS = AMTagRegistry.registerBlockTag("fly_spawns");
    public static final TagKey<Block> LEAFCUTTER_PUPA_USABLE_ON = AMTagRegistry.registerBlockTag("leafcutter_pupa_usable_on");
    public static final TagKey<Block> PUPFISH_EATABLES = AMTagRegistry.registerBlockTag("pupfish_eatables");
    public static final TagKey<Block> POTOO_PERCHES = AMTagRegistry.registerBlockTag("potoo_perches");
    public static final TagKey<EntityType<?>> IGNORES_KIMONO = AMTagRegistry.registerEntityTag("ignores_kimono");
    public static final TagKey<Block> LAVIATHAN_BREAKABLES = AMTagRegistry.registerBlockTag("laviathan_breakables");
    public static final TagKey<EntityType<?>> BUNFUNGUS_IGNORES = AMTagRegistry.registerEntityTag("bunfungus_ignores");
    public static final TagKey<EntityType<?>> BUNFUNGUS_IGNORE_AOE_ATTACKS = AMTagRegistry.registerEntityTag("bunfungus_ignore_aoe_attacks");
    public static final TagKey<Biome> SPAWNS_DESERT_CROCODILES = AMTagRegistry.registerBiomeTag("spawns_desert_crocodiles");
    public static final TagKey<Biome> SPAWNS_RED_GUSTERS = AMTagRegistry.registerBiomeTag("spawns_red_gusters");
    public static final TagKey<Biome> SPAWNS_SOUL_GUSTERS = AMTagRegistry.registerBiomeTag("spawns_soul_gusters");
    public static final TagKey<Biome> SPAWNS_NETHER_TARANTULA_HAWKS = AMTagRegistry.registerBiomeTag("spawns_nether_tarantula_hawks");
    public static final TagKey<Biome> SPAWNS_WHITE_SEALS = AMTagRegistry.registerBiomeTag("spawns_white_seals");
    public static final TagKey<Biome> SPAWNS_HUGE_CATFISH = AMTagRegistry.registerBiomeTag("spawns_huge_catfish");
    public static final TagKey<Biome> SPAWNS_WHITE_MANTIS_SHRIMP = AMTagRegistry.registerBiomeTag("spawns_white_mantis_shrimp");
    public static final TagKey<Biome> SKREECHERS_CAN_SPAWN_WARDENS = AMTagRegistry.registerBiomeTag("skreechers_can_spawn_wardens");
    public static final TagKey<Biome> SPAWNS_MURMURS_IGNORE_HEIGHT = AMTagRegistry.registerBiomeTag("spawns_murmurs_ignore_height");
    public static final TagKey<Structure> SPAWNS_UNDERMINERS = AMTagRegistry.registerStructureTag("spawns_underminers");

    private static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("alexsmobs", name));
    }

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.m_203882_((ResourceKey)Registries.f_256913_, (ResourceLocation)new ResourceLocation("alexsmobs", name));
    }

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.m_203882_((ResourceKey)Registries.f_256747_, (ResourceLocation)new ResourceLocation("alexsmobs", name));
    }

    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.m_203882_((ResourceKey)Registries.f_256952_, (ResourceLocation)new ResourceLocation("alexsmobs", name));
    }

    private static TagKey<Structure> registerStructureTag(String name) {
        return TagKey.m_203882_((ResourceKey)Registries.f_256944_, (ResourceLocation)new ResourceLocation("alexsmobs", name));
    }
}

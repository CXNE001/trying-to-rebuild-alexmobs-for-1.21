/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.config.biome.SpawnBiomeConfig
 *  com.github.alexthe666.citadel.config.biome.SpawnBiomeData
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.commons.lang3.tuple.Pair
 */
package com.github.alexthe666.alexsmobs.config;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.DefaultBiomes;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeConfig;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

public class BiomeConfig {
    public static final Pair<String, SpawnBiomeData> grizzlyBear = Pair.of((Object)"alexsmobs:grizzly_bear_spawns", (Object)DefaultBiomes.ALL_FOREST);
    public static final Pair<String, SpawnBiomeData> roadrunner = Pair.of((Object)"alexsmobs:roadrunner_spawns", (Object)DefaultBiomes.ROADRUNNER);
    public static final Pair<String, SpawnBiomeData> boneSerpent = Pair.of((Object)"alexsmobs:bone_serpent_spawns", (Object)DefaultBiomes.ALL_NETHER_MONSTER);
    public static final Pair<String, SpawnBiomeData> gazelle = Pair.of((Object)"alexsmobs:gazelle_spawns", (Object)DefaultBiomes.GAZELLE);
    public static final Pair<String, SpawnBiomeData> crocodile = Pair.of((Object)"alexsmobs:crocodile_spawns", (Object)DefaultBiomes.CROCODILE);
    public static final Pair<String, SpawnBiomeData> fly = Pair.of((Object)"alexsmobs:fly_spawns", (Object)DefaultBiomes.FLY);
    public static final Pair<String, SpawnBiomeData> hummingbird = Pair.of((Object)"alexsmobs:hummingbird_spawns", (Object)DefaultBiomes.HUMMINGBIRD);
    public static final Pair<String, SpawnBiomeData> orca = Pair.of((Object)"alexsmobs:orca_spawns", (Object)DefaultBiomes.ORCA);
    public static final Pair<String, SpawnBiomeData> sunbird = Pair.of((Object)"alexsmobs:sunbird_spawns", (Object)DefaultBiomes.SUNBIRD);
    public static final Pair<String, SpawnBiomeData> gorilla = Pair.of((Object)"alexsmobs:gorilla_spawns", (Object)DefaultBiomes.GORILLA);
    public static final Pair<String, SpawnBiomeData> crimsonMosquito = Pair.of((Object)"alexsmobs:crimson_mosquito_spawns", (Object)DefaultBiomes.CRIMSON_MOSQUITO);
    public static final Pair<String, SpawnBiomeData> rattlesnake = Pair.of((Object)"alexsmobs:rattlesnake_spawns", (Object)DefaultBiomes.RATTLESNAKE);
    public static final Pair<String, SpawnBiomeData> endergrade = Pair.of((Object)"alexsmobs:endergrade_spawns", (Object)DefaultBiomes.ENDERGRADE);
    public static final Pair<String, SpawnBiomeData> hammerheadShark = Pair.of((Object)"alexsmobs:hammerhead_shark_spawns", (Object)DefaultBiomes.HAMMERHEAD);
    public static final Pair<String, SpawnBiomeData> lobster = Pair.of((Object)"alexsmobs:lobster_spawns", (Object)DefaultBiomes.LOBSTER);
    public static final Pair<String, SpawnBiomeData> komodoDragon = Pair.of((Object)"alexsmobs:komodo_dragon_spawns", (Object)DefaultBiomes.KOMODO_DRAGON);
    public static final Pair<String, SpawnBiomeData> capuchinMonkey = Pair.of((Object)"alexsmobs:capuchin_monkey_spawns", (Object)DefaultBiomes.CAPUCHIN_MONKEY);
    public static final Pair<String, SpawnBiomeData> caveCentipede = Pair.of((Object)"alexsmobs:cave_centipede_spawns", (Object)DefaultBiomes.CAVES_MONSTER);
    public static final Pair<String, SpawnBiomeData> warpedToad = Pair.of((Object)"alexsmobs:warped_toad_spawns", (Object)DefaultBiomes.WARPED_TOAD);
    public static final Pair<String, SpawnBiomeData> moose = Pair.of((Object)"alexsmobs:moose_spawns", (Object)DefaultBiomes.MOOSE);
    public static final Pair<String, SpawnBiomeData> mimicube = Pair.of((Object)"alexsmobs:mimicube_spawns", (Object)DefaultBiomes.MIMICUBE);
    public static final Pair<String, SpawnBiomeData> raccoon = Pair.of((Object)"alexsmobs:raccoon_spawns", (Object)DefaultBiomes.RACCOON);
    public static final Pair<String, SpawnBiomeData> blobfish = Pair.of((Object)"alexsmobs:blobfish_spawns", (Object)DefaultBiomes.DEEP_SEA);
    public static final Pair<String, SpawnBiomeData> seal = Pair.of((Object)"alexsmobs:seal_spawns", (Object)DefaultBiomes.SEAL);
    public static final Pair<String, SpawnBiomeData> cockroach = Pair.of((Object)"alexsmobs:cockroach_spawns", (Object)DefaultBiomes.COCKROACH);
    public static final Pair<String, SpawnBiomeData> shoebill = Pair.of((Object)"alexsmobs:shoebill_spawns", (Object)DefaultBiomes.SHOEBILL);
    public static final Pair<String, SpawnBiomeData> elephant = Pair.of((Object)"alexsmobs:elephant_spawns", (Object)DefaultBiomes.ELEPHANT);
    public static final Pair<String, SpawnBiomeData> soulVulture = Pair.of((Object)"alexsmobs:soul_vulture_spawns", (Object)DefaultBiomes.SOUL_VULTURE);
    public static final Pair<String, SpawnBiomeData> snowLeopard = Pair.of((Object)"alexsmobs:snow_leopard_spawns", (Object)DefaultBiomes.SNOW_LEOPARD);
    public static final Pair<String, SpawnBiomeData> spectre = Pair.of((Object)"alexsmobs:spectre_spawns", (Object)DefaultBiomes.SPECTRE);
    public static final Pair<String, SpawnBiomeData> crow = Pair.of((Object)"alexsmobs:crow_spawns", (Object)DefaultBiomes.CROW);
    public static final Pair<String, SpawnBiomeData> alligatorSnappingTurtle = Pair.of((Object)"alexsmobs:alligator_snapping_turtle_spawns", (Object)DefaultBiomes.ALLIGATOR_SNAPPING_TURTLE);
    public static final Pair<String, SpawnBiomeData> mungus = Pair.of((Object)"alexsmobs:mungus_spawns", (Object)DefaultBiomes.MUNGUS);
    public static final Pair<String, SpawnBiomeData> mantisShrimp = Pair.of((Object)"alexsmobs:mantis_shrimp_spawns", (Object)DefaultBiomes.MANTIS_SHRIMP);
    public static final Pair<String, SpawnBiomeData> guster = Pair.of((Object)"alexsmobs:guster_spawns", (Object)DefaultBiomes.GUSTER);
    public static final Pair<String, SpawnBiomeData> warpedMosco = Pair.of((Object)"alexsmobs:warped_mosco_spawns", (Object)DefaultBiomes.EMPTY);
    public static final Pair<String, SpawnBiomeData> straddler = Pair.of((Object)"alexsmobs:straddler_spawns", (Object)DefaultBiomes.STRADDLER);
    public static final Pair<String, SpawnBiomeData> stradpole = Pair.of((Object)"alexsmobs:stradpole_spawns", (Object)DefaultBiomes.STRADDLER);
    public static final Pair<String, SpawnBiomeData> emu = Pair.of((Object)"alexsmobs:emu_spawns", (Object)DefaultBiomes.SAVANNA_AND_MESA);
    public static final Pair<String, SpawnBiomeData> platypus = Pair.of((Object)"alexsmobs:platypus_spawns", (Object)DefaultBiomes.ICE_FREE_RIVER);
    public static final Pair<String, SpawnBiomeData> dropbear = Pair.of((Object)"alexsmobs:dropbear_spawns", (Object)DefaultBiomes.DROPBEAR);
    public static final Pair<String, SpawnBiomeData> tasmanianDevil = Pair.of((Object)"alexsmobs:tasmanian_devil_spawns", (Object)DefaultBiomes.TASMANIAN_DEVIL);
    public static final Pair<String, SpawnBiomeData> kangaroo = Pair.of((Object)"alexsmobs:kangaroo_spawns", (Object)DefaultBiomes.SAVANNA_AND_MESA);
    public static final Pair<String, SpawnBiomeData> cachalot_whale_spawns = Pair.of((Object)"alexsmobs:cachalot_whale_spawns", (Object)DefaultBiomes.CACHALOT_WHALE);
    public static final Pair<String, SpawnBiomeData> cachalot_whale_beached_spawns = Pair.of((Object)"alexsmobs:cachalot_whale_beached_spawns", (Object)DefaultBiomes.BEACHED_CACHALOT_WHALE);
    public static final Pair<String, SpawnBiomeData> leafcutter_anthill_spawns = Pair.of((Object)"alexsmobs:leafcutter_anthill_spawns", (Object)DefaultBiomes.LEAFCUTTER_ANTHILL);
    public static final Pair<String, SpawnBiomeData> enderiophage_spawns = Pair.of((Object)"alexsmobs:enderiophage_spawns", (Object)DefaultBiomes.ENDERIOPHAGE);
    public static final Pair<String, SpawnBiomeData> baldEagle = Pair.of((Object)"alexsmobs:bald_eagle_spawns", (Object)DefaultBiomes.BALD_EAGLE);
    public static final Pair<String, SpawnBiomeData> tiger = Pair.of((Object)"alexsmobs:tiger_spawns", (Object)DefaultBiomes.TIGER);
    public static final Pair<String, SpawnBiomeData> tarantula_hawk = Pair.of((Object)"alexsmobs:tarantula_hawk_spawns", (Object)DefaultBiomes.DESERT);
    public static final Pair<String, SpawnBiomeData> void_worm = Pair.of((Object)"alexsmobs:void_worm_spawns", (Object)DefaultBiomes.EMPTY);
    public static final Pair<String, SpawnBiomeData> frilled_shark = Pair.of((Object)"alexsmobs:frilled_shark_spawns", (Object)DefaultBiomes.DEEP_SEA);
    public static final Pair<String, SpawnBiomeData> mimic_octopus = Pair.of((Object)"alexsmobs:mimic_octopus_spawns", (Object)DefaultBiomes.MIMIC_OCTOPUS);
    public static final Pair<String, SpawnBiomeData> seagull = Pair.of((Object)"alexsmobs:seagull_spawns", (Object)DefaultBiomes.SEAGULL);
    public static final Pair<String, SpawnBiomeData> froststalker = Pair.of((Object)"alexsmobs:froststalker_spawns", (Object)DefaultBiomes.FROSTSTALKER);
    public static final Pair<String, SpawnBiomeData> tusklin = Pair.of((Object)"alexsmobs:tusklin_spawns", (Object)DefaultBiomes.TUSKLIN);
    public static final Pair<String, SpawnBiomeData> laviathan = Pair.of((Object)"alexsmobs:laviathan_spawns", (Object)DefaultBiomes.ALL_NETHER);
    public static final Pair<String, SpawnBiomeData> cosmaw = Pair.of((Object)"alexsmobs:cosmaw_spawns", (Object)DefaultBiomes.COSMAW);
    public static final Pair<String, SpawnBiomeData> toucan = Pair.of((Object)"alexsmobs:toucan_spawns", (Object)DefaultBiomes.TOUCAN);
    public static final Pair<String, SpawnBiomeData> maned_wolf = Pair.of((Object)"alexsmobs:maned_wolf_spawns", (Object)DefaultBiomes.MANED_WOLF);
    public static final Pair<String, SpawnBiomeData> anaconda = Pair.of((Object)"alexsmobs:anaconda_spawns", (Object)DefaultBiomes.ANACONDA);
    public static final Pair<String, SpawnBiomeData> anteater = Pair.of((Object)"alexsmobs:anteater_spawns", (Object)DefaultBiomes.ANTEATER);
    public static final Pair<String, SpawnBiomeData> rocky_roller = Pair.of((Object)"alexsmobs:rocky_roller_spawns", (Object)DefaultBiomes.ROCKY_ROLLER);
    public static final Pair<String, SpawnBiomeData> flutter = Pair.of((Object)"alexsmobs:flutter_spawns", (Object)DefaultBiomes.FLUTTER);
    public static final Pair<String, SpawnBiomeData> gelada_monkey = Pair.of((Object)"alexsmobs:gelada_monkey_spawns", (Object)DefaultBiomes.MEADOWS);
    public static final Pair<String, SpawnBiomeData> jerboa = Pair.of((Object)"alexsmobs:jerboa_spawns", (Object)DefaultBiomes.DESERT);
    public static final Pair<String, SpawnBiomeData> terrapin = Pair.of((Object)"alexsmobs:terrapin_spawns", (Object)DefaultBiomes.ICE_FREE_RIVER);
    public static final Pair<String, SpawnBiomeData> comb_jelly = Pair.of((Object)"alexsmobs:comb_jelly_spawns", (Object)DefaultBiomes.COMB_JELLY);
    public static final Pair<String, SpawnBiomeData> cosmic_cod = Pair.of((Object)"alexsmobs:cosmic_cod_spawns", (Object)DefaultBiomes.COSMIC_COD);
    public static final Pair<String, SpawnBiomeData> bunfungus = Pair.of((Object)"alexsmobs:bunfungus_spawns", (Object)DefaultBiomes.MUNGUS);
    public static final Pair<String, SpawnBiomeData> bison = Pair.of((Object)"alexsmobs:bison_spawns", (Object)DefaultBiomes.BISON);
    public static final Pair<String, SpawnBiomeData> giant_squid = Pair.of((Object)"alexsmobs:giant_squid_spawns", (Object)DefaultBiomes.GIANT_SQUID);
    public static final Pair<String, SpawnBiomeData> devils_hole_pupfish = Pair.of((Object)"alexsmobs:devils_hole_pupfish_spawns", (Object)DefaultBiomes.ALL_OVERWORLD);
    public static final Pair<String, SpawnBiomeData> catfish = Pair.of((Object)"alexsmobs:catfish_spawns", (Object)DefaultBiomes.CATFISH);
    public static final Pair<String, SpawnBiomeData> flying_fish = Pair.of((Object)"alexsmobs:flying_fish_spawns", (Object)DefaultBiomes.FLYING_FISH);
    public static final Pair<String, SpawnBiomeData> skelewag = Pair.of((Object)"alexsmobs:skelewag_spawns", (Object)DefaultBiomes.SKELEWAG);
    public static final Pair<String, SpawnBiomeData> rain_frog = Pair.of((Object)"alexsmobs:rain_frog_spawns", (Object)DefaultBiomes.DESERT);
    public static final Pair<String, SpawnBiomeData> potoo = Pair.of((Object)"alexsmobs:potoo_spawns", (Object)DefaultBiomes.POTOO);
    public static final Pair<String, SpawnBiomeData> mudskipper = Pair.of((Object)"alexsmobs:mudskipper_spawns", (Object)DefaultBiomes.MANGROVE);
    public static final Pair<String, SpawnBiomeData> rhinoceros = Pair.of((Object)"alexsmobs:rhinoceros_spawns", (Object)DefaultBiomes.RHINOCEROS);
    public static final Pair<String, SpawnBiomeData> sugar_glider = Pair.of((Object)"alexsmobs:sugar_glider_spawns", (Object)DefaultBiomes.SUGAR_GLIDER);
    public static final Pair<String, SpawnBiomeData> farseer = Pair.of((Object)"alexsmobs:farseer", (Object)DefaultBiomes.FARSEER);
    public static final Pair<String, SpawnBiomeData> skreecher = Pair.of((Object)"alexsmobs:skreecher", (Object)DefaultBiomes.SKREECHER);
    public static final Pair<String, SpawnBiomeData> underminer = Pair.of((Object)"alexsmobs:underminer", (Object)DefaultBiomes.CAVES);
    public static final Pair<String, SpawnBiomeData> murmur = Pair.of((Object)"alexsmobs:murmur", (Object)DefaultBiomes.CAVES_MONSTER);
    public static final Pair<String, SpawnBiomeData> skunk = Pair.of((Object)"alexsmobs:skunk_spawns", (Object)DefaultBiomes.SKUNK);
    public static final Pair<String, SpawnBiomeData> banana_slug = Pair.of((Object)"alexsmobs:banana_slug_spawns", (Object)DefaultBiomes.BANANA_SLUG);
    public static final Pair<String, SpawnBiomeData> blue_jay = Pair.of((Object)"alexsmobs:blue_jay_spawns", (Object)DefaultBiomes.ALL_FOREST);
    public static final Pair<String, SpawnBiomeData> caiman = Pair.of((Object)"alexsmobs:caiman_spawns", (Object)DefaultBiomes.MANGROVE);
    public static final Pair<String, SpawnBiomeData> triops = Pair.of((Object)"alexsmobs:triops_spawns", (Object)DefaultBiomes.DESERT);
    private static boolean init = false;
    private static final Map<String, SpawnBiomeData> biomeConfigValues = new HashMap<String, SpawnBiomeData>();

    public static void init() {
        try {
            for (Field f : BiomeConfig.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (!(obj instanceof Pair)) continue;
                String id = (String)((Pair)obj).getLeft();
                SpawnBiomeData data = (SpawnBiomeData)((Pair)obj).getRight();
                biomeConfigValues.put(id, SpawnBiomeConfig.create((ResourceLocation)new ResourceLocation(id), (SpawnBiomeData)data));
            }
        }
        catch (Exception e) {
            AlexsMobs.LOGGER.warn("Encountered error building alexsmobs biome config .json files");
            e.printStackTrace();
        }
        init = true;
    }

    public static boolean test(Pair<String, SpawnBiomeData> entry, Holder<Biome> biome, ResourceLocation name) {
        if (!init) {
            return false;
        }
        return biomeConfigValues.get(entry.getKey()).matches(biome, name);
    }

    public static boolean test(Pair<String, SpawnBiomeData> spawns, Holder<Biome> biome) {
        return BiomeConfig.test(spawns, biome, ForgeRegistries.BIOMES.getKey((Object)((Biome)biome.m_203334_())));
    }
}

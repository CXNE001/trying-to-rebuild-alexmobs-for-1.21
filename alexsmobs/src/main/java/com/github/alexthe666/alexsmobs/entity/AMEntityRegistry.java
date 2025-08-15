/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EntityType$Builder
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnPlacements
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.WaterAnimal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.NaturalSpawner
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraftforge.event.entity.EntityAttributeCreationEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityAlligatorSnappingTurtle;
import com.github.alexthe666.alexsmobs.entity.EntityAnaconda;
import com.github.alexthe666.alexsmobs.entity.EntityAnacondaPart;
import com.github.alexthe666.alexsmobs.entity.EntityAnteater;
import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.entity.EntityBananaSlug;
import com.github.alexthe666.alexsmobs.entity.EntityBison;
import com.github.alexthe666.alexsmobs.entity.EntityBlobfish;
import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpent;
import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpentPart;
import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotEcho;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotWhale;
import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import com.github.alexthe666.alexsmobs.entity.EntityCapuchinMonkey;
import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.github.alexthe666.alexsmobs.entity.EntityCentipedeBody;
import com.github.alexthe666.alexsmobs.entity.EntityCentipedeHead;
import com.github.alexthe666.alexsmobs.entity.EntityCentipedeTail;
import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.github.alexthe666.alexsmobs.entity.EntityCockroachEgg;
import com.github.alexthe666.alexsmobs.entity.EntityCombJelly;
import com.github.alexthe666.alexsmobs.entity.EntityCosmaw;
import com.github.alexthe666.alexsmobs.entity.EntityCosmicCod;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
import com.github.alexthe666.alexsmobs.entity.EntityCrow;
import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import com.github.alexthe666.alexsmobs.entity.EntityDropBear;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.github.alexthe666.alexsmobs.entity.EntityEmu;
import com.github.alexthe666.alexsmobs.entity.EntityEmuEgg;
import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import com.github.alexthe666.alexsmobs.entity.EntityEnderiophageRocket;
import com.github.alexthe666.alexsmobs.entity.EntityFarseer;
import com.github.alexthe666.alexsmobs.entity.EntityFart;
import com.github.alexthe666.alexsmobs.entity.EntityFlutter;
import com.github.alexthe666.alexsmobs.entity.EntityFly;
import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import com.github.alexthe666.alexsmobs.entity.EntityFrilledShark;
import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import com.github.alexthe666.alexsmobs.entity.EntityGazelle;
import com.github.alexthe666.alexsmobs.entity.EntityGeladaMonkey;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import com.github.alexthe666.alexsmobs.entity.EntityGrizzlyBear;
import com.github.alexthe666.alexsmobs.entity.EntityGust;
import com.github.alexthe666.alexsmobs.entity.EntityGuster;
import com.github.alexthe666.alexsmobs.entity.EntityHammerheadShark;
import com.github.alexthe666.alexsmobs.entity.EntityHemolymph;
import com.github.alexthe666.alexsmobs.entity.EntityHummingbird;
import com.github.alexthe666.alexsmobs.entity.EntityIceShard;
import com.github.alexthe666.alexsmobs.entity.EntityJerboa;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import com.github.alexthe666.alexsmobs.entity.EntityKomodoDragon;
import com.github.alexthe666.alexsmobs.entity.EntityLaviathan;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.entity.EntityLobster;
import com.github.alexthe666.alexsmobs.entity.EntityManedWolf;
import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import com.github.alexthe666.alexsmobs.entity.EntityMoose;
import com.github.alexthe666.alexsmobs.entity.EntityMosquitoSpit;
import com.github.alexthe666.alexsmobs.entity.EntityMudBall;
import com.github.alexthe666.alexsmobs.entity.EntityMudskipper;
import com.github.alexthe666.alexsmobs.entity.EntityMungus;
import com.github.alexthe666.alexsmobs.entity.EntityMurmur;
import com.github.alexthe666.alexsmobs.entity.EntityMurmurHead;
import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import com.github.alexthe666.alexsmobs.entity.EntityPlatypus;
import com.github.alexthe666.alexsmobs.entity.EntityPollenBall;
import com.github.alexthe666.alexsmobs.entity.EntityPotoo;
import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import com.github.alexthe666.alexsmobs.entity.EntityRainFrog;
import com.github.alexthe666.alexsmobs.entity.EntityRattlesnake;
import com.github.alexthe666.alexsmobs.entity.EntityRhinoceros;
import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import com.github.alexthe666.alexsmobs.entity.EntityRockyRoller;
import com.github.alexthe666.alexsmobs.entity.EntitySandShot;
import com.github.alexthe666.alexsmobs.entity.EntitySeaBear;
import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.github.alexthe666.alexsmobs.entity.EntitySeal;
import com.github.alexthe666.alexsmobs.entity.EntitySharkToothArrow;
import com.github.alexthe666.alexsmobs.entity.EntityShoebill;
import com.github.alexthe666.alexsmobs.entity.EntitySkelewag;
import com.github.alexthe666.alexsmobs.entity.EntitySkreecher;
import com.github.alexthe666.alexsmobs.entity.EntitySkunk;
import com.github.alexthe666.alexsmobs.entity.EntitySnowLeopard;
import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import com.github.alexthe666.alexsmobs.entity.EntitySquidGrapple;
import com.github.alexthe666.alexsmobs.entity.EntityStraddleboard;
import com.github.alexthe666.alexsmobs.entity.EntityStraddler;
import com.github.alexthe666.alexsmobs.entity.EntityStradpole;
import com.github.alexthe666.alexsmobs.entity.EntitySugarGlider;
import com.github.alexthe666.alexsmobs.entity.EntitySunbird;
import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import com.github.alexthe666.alexsmobs.entity.EntityTasmanianDevil;
import com.github.alexthe666.alexsmobs.entity.EntityTendonSegment;
import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.entity.EntityTossedItem;
import com.github.alexthe666.alexsmobs.entity.EntityToucan;
import com.github.alexthe666.alexsmobs.entity.EntityTriops;
import com.github.alexthe666.alexsmobs.entity.EntityTusklin;
import com.github.alexthe666.alexsmobs.entity.EntityUnderminer;
import com.github.alexthe666.alexsmobs.entity.EntityVineLasso;
import com.github.alexthe666.alexsmobs.entity.EntityVoidPortal;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormShot;
import com.github.alexthe666.alexsmobs.entity.EntityWarpedMosco;
import com.github.alexthe666.alexsmobs.entity.EntityWarpedToad;
import com.google.common.base.Predicates;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid="alexsmobs", bus=Mod.EventBusSubscriber.Bus.MOD)
public class AMEntityRegistry {
    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ENTITY_TYPES, (String)"alexsmobs");
    public static final RegistryObject<EntityType<EntityGrizzlyBear>> GRIZZLY_BEAR = DEF_REG.register("grizzly_bear", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGrizzlyBear::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.6f, 1.8f).setTrackingRange(10), "grizzly_bear"));
    public static final RegistryObject<EntityType<EntityRoadrunner>> ROADRUNNER = DEF_REG.register("roadrunner", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityRoadrunner::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.45f, 0.75f).setTrackingRange(10), "roadrunner"));
    public static final RegistryObject<EntityType<EntityBoneSerpent>> BONE_SERPENT = DEF_REG.register("bone_serpent", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBoneSerpent::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.2f, 1.15f).m_20719_().setTrackingRange(10), "bone_serpent"));
    public static final RegistryObject<EntityType<EntityBoneSerpentPart>> BONE_SERPENT_PART = DEF_REG.register("bone_serpent_part", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBoneSerpentPart::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.0f, 1.0f).m_20719_().setTrackingRange(10), "bone_serpent_part"));
    public static final RegistryObject<EntityType<EntityGazelle>> GAZELLE = DEF_REG.register("gazelle", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGazelle::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.85f, 1.25f).setTrackingRange(10), "gazelle"));
    public static final RegistryObject<EntityType<EntityCrocodile>> CROCODILE = DEF_REG.register("crocodile", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCrocodile::new, (MobCategory)MobCategory.CREATURE).m_20699_(2.15f, 0.75f).setTrackingRange(10), "crocodile"));
    public static final RegistryObject<EntityType<EntityFly>> FLY = DEF_REG.register("fly", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFly::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.35f, 0.35f).setTrackingRange(4), "fly"));
    public static final RegistryObject<EntityType<EntityHummingbird>> HUMMINGBIRD = DEF_REG.register("hummingbird", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityHummingbird::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.45f, 0.45f).setTrackingRange(5), "hummingbird"));
    public static final RegistryObject<EntityType<EntityOrca>> ORCA = DEF_REG.register("orca", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityOrca::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(3.75f, 1.75f).setTrackingRange(10), "orca"));
    public static final RegistryObject<EntityType<EntitySunbird>> SUNBIRD = DEF_REG.register("sunbird", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySunbird::new, (MobCategory)MobCategory.CREATURE).m_20699_(2.75f, 1.5f).m_20719_().setTrackingRange(12).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "sunbird"));
    public static final RegistryObject<EntityType<EntityGorilla>> GORILLA = DEF_REG.register("gorilla", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGorilla::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.15f, 1.35f).setTrackingRange(10), "gorilla"));
    public static final RegistryObject<EntityType<EntityCrimsonMosquito>> CRIMSON_MOSQUITO = DEF_REG.register("crimson_mosquito", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCrimsonMosquito::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.25f, 1.15f).m_20719_().setTrackingRange(8), "crimson_mosquito"));
    public static final RegistryObject<EntityType<EntityMosquitoSpit>> MOSQUITO_SPIT = DEF_REG.register("mosquito_spit", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMosquitoSpit::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityMosquitoSpit::new).m_20719_(), "mosquito_spit"));
    public static final RegistryObject<EntityType<EntityRattlesnake>> RATTLESNAKE = DEF_REG.register("rattlesnake", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityRattlesnake::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.95f, 0.35f).setTrackingRange(10), "rattlesnake"));
    public static final RegistryObject<EntityType<EntityEndergrade>> ENDERGRADE = DEF_REG.register("endergrade", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityEndergrade::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.95f, 0.85f).setTrackingRange(10), "endergrade"));
    public static final RegistryObject<EntityType<EntityHammerheadShark>> HAMMERHEAD_SHARK = DEF_REG.register("hammerhead_shark", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityHammerheadShark::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(2.4f, 1.25f).setTrackingRange(10), "hammerhead_shark"));
    public static final RegistryObject<EntityType<EntitySharkToothArrow>> SHARK_TOOTH_ARROW = DEF_REG.register("shark_tooth_arrow", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySharkToothArrow::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntitySharkToothArrow::new), "shark_tooth_arrow"));
    public static final RegistryObject<EntityType<EntityLobster>> LOBSTER = DEF_REG.register("lobster", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityLobster::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.7f, 0.4f).setTrackingRange(5), "lobster"));
    public static final RegistryObject<EntityType<EntityKomodoDragon>> KOMODO_DRAGON = DEF_REG.register("komodo_dragon", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityKomodoDragon::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.9f, 0.9f).setTrackingRange(10), "komodo_dragon"));
    public static final RegistryObject<EntityType<EntityCapuchinMonkey>> CAPUCHIN_MONKEY = DEF_REG.register("capuchin_monkey", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCapuchinMonkey::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.65f, 0.75f).setTrackingRange(10), "capuchin_monkey"));
    public static final RegistryObject<EntityType<EntityTossedItem>> TOSSED_ITEM = DEF_REG.register("tossed_item", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTossedItem::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityTossedItem::new).m_20719_(), "tossed_item"));
    public static final RegistryObject<EntityType<EntityCentipedeHead>> CENTIPEDE_HEAD = DEF_REG.register("centipede_head", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCentipedeHead::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 0.9f).setTrackingRange(8), "centipede_head"));
    public static final RegistryObject<EntityType<EntityCentipedeBody>> CENTIPEDE_BODY = DEF_REG.register("centipede_body", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCentipedeBody::new, (MobCategory)MobCategory.MISC).m_20699_(0.9f, 0.9f).m_20719_().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(8), "centipede_body"));
    public static final RegistryObject<EntityType<EntityCentipedeTail>> CENTIPEDE_TAIL = DEF_REG.register("centipede_tail", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCentipedeTail::new, (MobCategory)MobCategory.MISC).m_20699_(0.9f, 0.9f).m_20719_().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(8), "centipede_tail"));
    public static final RegistryObject<EntityType<EntityWarpedToad>> WARPED_TOAD = DEF_REG.register("warped_toad", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityWarpedToad::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.9f, 1.4f).m_20719_().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(10), "warped_toad"));
    public static final RegistryObject<EntityType<EntityMoose>> MOOSE = DEF_REG.register("moose", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMoose::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.7f, 2.4f).setTrackingRange(10), "moose"));
    public static final RegistryObject<EntityType<EntityMimicube>> MIMICUBE = DEF_REG.register("mimicube", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMimicube::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 0.9f).setTrackingRange(8), "mimicube"));
    public static final RegistryObject<EntityType<EntityRaccoon>> RACCOON = DEF_REG.register("raccoon", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityRaccoon::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 0.9f).setTrackingRange(10), "raccoon"));
    public static final RegistryObject<EntityType<EntityBlobfish>> BLOBFISH = DEF_REG.register("blobfish", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBlobfish::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.7f, 0.45f).setTrackingRange(5), "blobfish"));
    public static final RegistryObject<EntityType<EntitySeal>> SEAL = DEF_REG.register("seal", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySeal::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.45f, 0.9f).setTrackingRange(10), "seal"));
    public static final RegistryObject<EntityType<EntityCockroach>> COCKROACH = DEF_REG.register("cockroach", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCockroach::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.7f, 0.3f).setTrackingRange(5), "cockroach"));
    public static final RegistryObject<EntityType<EntityCockroachEgg>> COCKROACH_EGG = DEF_REG.register("cockroach_egg", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCockroachEgg::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityCockroachEgg::new).m_20719_(), "cockroach_egg"));
    public static final RegistryObject<EntityType<EntityShoebill>> SHOEBILL = DEF_REG.register("shoebill", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityShoebill::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 1.5f).setUpdateInterval(1).setTrackingRange(10), "shoebill"));
    public static final RegistryObject<EntityType<EntityElephant>> ELEPHANT = DEF_REG.register("elephant", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityElephant::new, (MobCategory)MobCategory.CREATURE).m_20699_(3.1f, 3.5f).setUpdateInterval(1).setTrackingRange(10), "elephant"));
    public static final RegistryObject<EntityType<EntitySoulVulture>> SOUL_VULTURE = DEF_REG.register("soul_vulture", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySoulVulture::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 1.3f).setUpdateInterval(1).m_20719_().setTrackingRange(8), "soul_vulture"));
    public static final RegistryObject<EntityType<EntitySnowLeopard>> SNOW_LEOPARD = DEF_REG.register("snow_leopard", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySnowLeopard::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.2f, 1.3f).m_20714_(new Block[]{Blocks.f_152499_}).setTrackingRange(10), "snow_leopard"));
    public static final RegistryObject<EntityType<EntitySpectre>> SPECTRE = DEF_REG.register("spectre", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySpectre::new, (MobCategory)MobCategory.CREATURE).m_20699_(3.15f, 0.8f).m_20719_().setTrackingRange(10).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "spectre"));
    public static final RegistryObject<EntityType<EntityCrow>> CROW = DEF_REG.register("crow", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCrow::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.45f, 0.45f).setTrackingRange(10), "crow"));
    public static final RegistryObject<EntityType<EntityAlligatorSnappingTurtle>> ALLIGATOR_SNAPPING_TURTLE = DEF_REG.register("alligator_snapping_turtle", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityAlligatorSnappingTurtle::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.25f, 0.65f).setTrackingRange(10), "alligator_snapping_turtle"));
    public static final RegistryObject<EntityType<EntityMungus>> MUNGUS = DEF_REG.register("mungus", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMungus::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.75f, 1.45f).setTrackingRange(10), "mungus"));
    public static final RegistryObject<EntityType<EntityMantisShrimp>> MANTIS_SHRIMP = DEF_REG.register("mantis_shrimp", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMantisShrimp::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(1.25f, 1.2f).setTrackingRange(10), "mantis_shrimp"));
    public static final RegistryObject<EntityType<EntityGuster>> GUSTER = DEF_REG.register("guster", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGuster::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.42f, 2.35f).m_20719_().setTrackingRange(8), "guster"));
    public static final RegistryObject<EntityType<EntitySandShot>> SAND_SHOT = DEF_REG.register("sand_shot", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySandShot::new, (MobCategory)MobCategory.MISC).m_20699_(0.95f, 0.65f).setCustomClientFactory(EntitySandShot::new).m_20719_(), "sand_shot"));
    public static final RegistryObject<EntityType<EntityGust>> GUST = DEF_REG.register("gust", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGust::new, (MobCategory)MobCategory.MISC).m_20699_(0.8f, 0.8f).setCustomClientFactory(EntityGust::new).m_20719_(), "gust"));
    public static final RegistryObject<EntityType<EntityWarpedMosco>> WARPED_MOSCO = DEF_REG.register("warped_mosco", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityWarpedMosco::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.99f, 3.25f).m_20719_().setTrackingRange(10), "warped_mosco"));
    public static final RegistryObject<EntityType<EntityHemolymph>> HEMOLYMPH = DEF_REG.register("hemolymph", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityHemolymph::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityHemolymph::new).m_20719_(), "hemolymph"));
    public static final RegistryObject<EntityType<EntityStraddler>> STRADDLER = DEF_REG.register("straddler", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityStraddler::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.65f, 3.0f).m_20719_().setTrackingRange(8), "straddler"));
    public static final RegistryObject<EntityType<EntityStradpole>> STRADPOLE = DEF_REG.register("stradpole", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityStradpole::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.5f, 0.5f).m_20719_().setTrackingRange(4), "stradpole"));
    public static final RegistryObject<EntityType<EntityStraddleboard>> STRADDLEBOARD = DEF_REG.register("straddleboard", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityStraddleboard::new, (MobCategory)MobCategory.MISC).m_20699_(1.5f, 0.35f).setCustomClientFactory(EntityStraddleboard::new).m_20719_().setUpdateInterval(1).m_20702_(10).setShouldReceiveVelocityUpdates(true), "straddleboard"));
    public static final RegistryObject<EntityType<EntityEmu>> EMU = DEF_REG.register("emu", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityEmu::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.1f, 1.8f).setTrackingRange(10), "emu"));
    public static final RegistryObject<EntityType<EntityEmuEgg>> EMU_EGG = DEF_REG.register("emu_egg", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityEmuEgg::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityEmuEgg::new).m_20719_(), "emu_egg"));
    public static final RegistryObject<EntityType<EntityPlatypus>> PLATYPUS = DEF_REG.register("platypus", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityPlatypus::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 0.5f).setTrackingRange(10), "platypus"));
    public static final RegistryObject<EntityType<EntityDropBear>> DROPBEAR = DEF_REG.register("dropbear", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityDropBear::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.65f, 1.5f).m_20719_().setTrackingRange(8), "dropbear"));
    public static final RegistryObject<EntityType<EntityTasmanianDevil>> TASMANIAN_DEVIL = DEF_REG.register("tasmanian_devil", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTasmanianDevil::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.7f, 0.8f).setTrackingRange(10), "tasmanian_devil"));
    public static final RegistryObject<EntityType<EntityKangaroo>> KANGAROO = DEF_REG.register("kangaroo", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityKangaroo::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.65f, 1.5f).setTrackingRange(10), "kangaroo"));
    public static final RegistryObject<EntityType<EntityCachalotWhale>> CACHALOT_WHALE = DEF_REG.register("cachalot_whale", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCachalotWhale::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(9.0f, 4.0f).setTrackingRange(10), "cachalot_whale"));
    public static final RegistryObject<EntityType<EntityCachalotEcho>> CACHALOT_ECHO = DEF_REG.register("cachalot_echo", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCachalotEcho::new, (MobCategory)MobCategory.MISC).m_20699_(2.0f, 2.0f).setCustomClientFactory(EntityCachalotEcho::new).m_20719_(), "cachalot_echo"));
    public static final RegistryObject<EntityType<EntityLeafcutterAnt>> LEAFCUTTER_ANT = DEF_REG.register("leafcutter_ant", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityLeafcutterAnt::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 0.5f).setTrackingRange(5), "leafcutter_ant"));
    public static final RegistryObject<EntityType<EntityEnderiophage>> ENDERIOPHAGE = DEF_REG.register("enderiophage", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityEnderiophage::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.85f, 1.95f).setUpdateInterval(1).setTrackingRange(8), "enderiophage"));
    public static final RegistryObject<EntityType<EntityEnderiophageRocket>> ENDERIOPHAGE_ROCKET = DEF_REG.register("enderiophage_rocket", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityEnderiophageRocket::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityEnderiophageRocket::new).m_20719_(), "enderiophage_rocket"));
    public static final RegistryObject<EntityType<EntityBaldEagle>> BALD_EAGLE = DEF_REG.register("bald_eagle", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBaldEagle::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.5f, 0.95f).setUpdateInterval(1).setTrackingRange(14), "bald_eagle"));
    public static final RegistryObject<EntityType<EntityTiger>> TIGER = DEF_REG.register("tiger", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTiger::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.45f, 1.2f).setTrackingRange(10), "tiger"));
    public static final RegistryObject<EntityType<EntityTarantulaHawk>> TARANTULA_HAWK = DEF_REG.register("tarantula_hawk", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTarantulaHawk::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.2f, 0.9f).setTrackingRange(10), "tarantula_hawk"));
    public static final RegistryObject<EntityType<EntityVoidWorm>> VOID_WORM = DEF_REG.register("void_worm", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityVoidWorm::new, (MobCategory)MobCategory.MONSTER).m_20699_(3.4f, 3.0f).m_20719_().setTrackingRange(20).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "void_worm"));
    public static final RegistryObject<EntityType<EntityVoidWormPart>> VOID_WORM_PART = DEF_REG.register("void_worm_part", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityVoidWormPart::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.2f, 1.35f).m_20719_().setTrackingRange(20).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1), "void_worm_part"));
    public static final RegistryObject<EntityType<EntityVoidWormShot>> VOID_WORM_SHOT = DEF_REG.register("void_worm_shot", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityVoidWormShot::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityVoidWormShot::new).m_20719_(), "void_worm_shot"));
    public static final RegistryObject<EntityType<EntityVoidPortal>> VOID_PORTAL = DEF_REG.register("void_portal", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityVoidPortal::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntityVoidPortal::new).m_20719_(), "void_portal"));
    public static final RegistryObject<EntityType<EntityFrilledShark>> FRILLED_SHARK = DEF_REG.register("frilled_shark", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFrilledShark::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(1.3f, 0.4f).setTrackingRange(8), "frilled_shark"));
    public static final RegistryObject<EntityType<EntityMimicOctopus>> MIMIC_OCTOPUS = DEF_REG.register("mimic_octopus", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMimicOctopus::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(0.9f, 0.6f).setTrackingRange(8), "mimic_octopus"));
    public static final RegistryObject<EntityType<EntitySeagull>> SEAGULL = DEF_REG.register("seagull", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySeagull::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.45f, 0.45f).setTrackingRange(10), "seagull"));
    public static final RegistryObject<EntityType<EntityFroststalker>> FROSTSTALKER = DEF_REG.register("froststalker", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFroststalker::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.95f, 1.15f).m_20714_(new Block[]{Blocks.f_152499_}), "froststalker"));
    public static final RegistryObject<EntityType<EntityIceShard>> ICE_SHARD = DEF_REG.register("ice_shard", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityIceShard::new, (MobCategory)MobCategory.MISC).m_20699_(0.45f, 0.45f).setCustomClientFactory(EntityIceShard::new).m_20719_(), "ice_shard"));
    public static final RegistryObject<EntityType<EntityTusklin>> TUSKLIN = DEF_REG.register("tusklin", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTusklin::new, (MobCategory)MobCategory.CREATURE).m_20699_(2.2f, 1.9f).m_20714_(new Block[]{Blocks.f_152499_}).setTrackingRange(10), "tusklin"));
    public static final RegistryObject<EntityType<EntityLaviathan>> LAVIATHAN = DEF_REG.register("laviathan", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityLaviathan::new, (MobCategory)MobCategory.CREATURE).m_20699_(3.3f, 2.4f).m_20719_().setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(10), "laviathan"));
    public static final RegistryObject<EntityType<EntityCosmaw>> COSMAW = DEF_REG.register("cosmaw", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCosmaw::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.95f, 1.8f).setTrackingRange(10), "cosmaw"));
    public static final RegistryObject<EntityType<EntityToucan>> TOUCAN = DEF_REG.register("toucan", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityToucan::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.45f, 0.45f).setTrackingRange(10), "toucan"));
    public static final RegistryObject<EntityType<EntityManedWolf>> MANED_WOLF = DEF_REG.register("maned_wolf", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityManedWolf::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.9f, 1.26f).setTrackingRange(10), "maned_wolf"));
    public static final RegistryObject<EntityType<EntityAnaconda>> ANACONDA = DEF_REG.register("anaconda", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityAnaconda::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 0.8f).setTrackingRange(10), "anaconda"));
    public static final RegistryObject<EntityType<EntityAnacondaPart>> ANACONDA_PART = DEF_REG.register("anaconda_part", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityAnacondaPart::new, (MobCategory)MobCategory.MISC).m_20699_(0.8f, 0.8f).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(10), "anaconda_part"));
    public static final RegistryObject<EntityType<EntityVineLasso>> VINE_LASSO = DEF_REG.register("vine_lasso", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityVineLasso::new, (MobCategory)MobCategory.MISC).m_20699_(0.85f, 0.2f).setCustomClientFactory(EntityVineLasso::new).m_20719_(), "vine_lasso"));
    public static final RegistryObject<EntityType<EntityAnteater>> ANTEATER = DEF_REG.register("anteater", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityAnteater::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.3f, 1.1f).setTrackingRange(10), "anteater"));
    public static final RegistryObject<EntityType<EntityRockyRoller>> ROCKY_ROLLER = DEF_REG.register("rocky_roller", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityRockyRoller::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.2f, 1.45f).setTrackingRange(8), "rocky_roller"));
    public static final RegistryObject<EntityType<EntityFlutter>> FLUTTER = DEF_REG.register("flutter", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFlutter::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.5f, 0.7f).setTrackingRange(6), "flutter"));
    public static final RegistryObject<EntityType<EntityPollenBall>> POLLEN_BALL = DEF_REG.register("pollen_ball", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityPollenBall::new, (MobCategory)MobCategory.MISC).m_20699_(0.35f, 0.35f).setCustomClientFactory(EntityPollenBall::new).m_20719_(), "pollen_ball"));
    public static final RegistryObject<EntityType<EntityGeladaMonkey>> GELADA_MONKEY = DEF_REG.register("gelada_monkey", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGeladaMonkey::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.2f, 1.2f).setTrackingRange(10), "gelada_monkey"));
    public static final RegistryObject<EntityType<EntityJerboa>> JERBOA = DEF_REG.register("jerboa", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityJerboa::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.5f, 0.5f).setTrackingRange(5), "jerboa"));
    public static final RegistryObject<EntityType<EntityTerrapin>> TERRAPIN = DEF_REG.register("terrapin", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTerrapin::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.75f, 0.45f).setTrackingRange(5), "terrapin"));
    public static final RegistryObject<EntityType<EntityCombJelly>> COMB_JELLY = DEF_REG.register("comb_jelly", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCombJelly::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.65f, 0.8f).setTrackingRange(5), "comb_jelly"));
    public static final RegistryObject<EntityType<EntityCosmicCod>> COSMIC_COD = DEF_REG.register("cosmic_cod", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCosmicCod::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.85f, 0.4f).setTrackingRange(5), "cosmic_cod"));
    public static final RegistryObject<EntityType<EntityBunfungus>> BUNFUNGUS = DEF_REG.register("bunfungus", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBunfungus::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.85f, 2.1f).setTrackingRange(10), "bunfungus"));
    public static final RegistryObject<EntityType<EntityBison>> BISON = DEF_REG.register("bison", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBison::new, (MobCategory)MobCategory.CREATURE).m_20699_(2.4f, 2.1f).setTrackingRange(10), "bison"));
    public static final RegistryObject<EntityType<EntityGiantSquid>> GIANT_SQUID = DEF_REG.register("giant_squid", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityGiantSquid::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(0.9f, 1.2f).setTrackingRange(10), "giant_squid"));
    public static final RegistryObject<EntityType<EntitySquidGrapple>> SQUID_GRAPPLE = DEF_REG.register("squid_grapple", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySquidGrapple::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 0.5f).setCustomClientFactory(EntitySquidGrapple::new).m_20719_(), "squid_grapple"));
    public static final RegistryObject<EntityType<EntitySeaBear>> SEA_BEAR = DEF_REG.register("sea_bear", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySeaBear::new, (MobCategory)MobCategory.WATER_CREATURE).m_20699_(2.4f, 1.99f).setTrackingRange(10), "sea_bear"));
    public static final RegistryObject<EntityType<EntityDevilsHolePupfish>> DEVILS_HOLE_PUPFISH = DEF_REG.register("devils_hole_pupfish", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityDevilsHolePupfish::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.6f, 0.4f).setTrackingRange(4), "devils_hole_pupfish"));
    public static final RegistryObject<EntityType<EntityCatfish>> CATFISH = DEF_REG.register("catfish", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCatfish::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.9f, 0.6f).setTrackingRange(5), "catfish"));
    public static final RegistryObject<EntityType<EntityFlyingFish>> FLYING_FISH = DEF_REG.register("flying_fish", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFlyingFish::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.6f, 0.4f).setTrackingRange(5), "flying_fish"));
    public static final RegistryObject<EntityType<EntitySkelewag>> SKELEWAG = DEF_REG.register("skelewag", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySkelewag::new, (MobCategory)MobCategory.MONSTER).m_20699_(2.0f, 1.2f).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(8), "skelewag"));
    public static final RegistryObject<EntityType<EntityRainFrog>> RAIN_FROG = DEF_REG.register("rain_frog", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityRainFrog::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.55f, 0.5f).setTrackingRange(5), "rain_frog"));
    public static final RegistryObject<EntityType<EntityPotoo>> POTOO = DEF_REG.register("potoo", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityPotoo::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.6f, 0.8f).setTrackingRange(10), "potoo"));
    public static final RegistryObject<EntityType<EntityMudskipper>> MUDSKIPPER = DEF_REG.register("mudskipper", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMudskipper::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.7f, 0.44f).setTrackingRange(10), "mudskipper"));
    public static final RegistryObject<EntityType<EntityMudBall>> MUD_BALL = DEF_REG.register("mud_ball", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMudBall::new, (MobCategory)MobCategory.MISC).m_20699_(0.35f, 0.35f).setCustomClientFactory(EntityMudBall::new).m_20719_(), "mud_ball"));
    public static final RegistryObject<EntityType<EntityRhinoceros>> RHINOCEROS = DEF_REG.register("rhinoceros", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityRhinoceros::new, (MobCategory)MobCategory.CREATURE).m_20699_(2.3f, 2.4f).setTrackingRange(10), "rhinoceros"));
    public static final RegistryObject<EntityType<EntitySugarGlider>> SUGAR_GLIDER = DEF_REG.register("sugar_glider", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySugarGlider::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 0.45f).setTrackingRange(10), "sugar_glider"));
    public static final RegistryObject<EntityType<EntityFarseer>> FARSEER = DEF_REG.register("farseer", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFarseer::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.99f, 1.5f).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).m_20719_().setTrackingRange(8), "farseer"));
    public static final RegistryObject<EntityType<EntitySkreecher>> SKREECHER = DEF_REG.register("skreecher", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySkreecher::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.99f, 0.95f).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(8), "skreecher"));
    public static final RegistryObject<EntityType<EntityUnderminer>> UNDERMINER = DEF_REG.register("underminer", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityUnderminer::new, (MobCategory)MobCategory.AMBIENT).m_20699_(0.8f, 1.8f).setTrackingRange(8), "underminer"));
    public static final RegistryObject<EntityType<EntityMurmur>> MURMUR = DEF_REG.register("murmur", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMurmur::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.7f, 1.45f).setTrackingRange(8), "murmur"));
    public static final RegistryObject<EntityType<EntityMurmurHead>> MURMUR_HEAD = DEF_REG.register("murmur_head", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityMurmurHead::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.55f, 0.55f).setTrackingRange(8), "murmur_head"));
    public static final RegistryObject<EntityType<EntityTendonSegment>> TENDON_SEGMENT = DEF_REG.register("tendon_segment", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTendonSegment::new, (MobCategory)MobCategory.MISC).m_20699_(0.1f, 0.1f).setCustomClientFactory(EntityTendonSegment::new).m_20719_(), "tendon_segment"));
    public static final RegistryObject<EntityType<EntitySkunk>> SKUNK = DEF_REG.register("skunk", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntitySkunk::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.85f, 0.65f).setTrackingRange(10), "skunk"));
    public static final RegistryObject<EntityType<EntityFart>> FART = DEF_REG.register("fart", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityFart::new, (MobCategory)MobCategory.MISC).m_20699_(0.7f, 0.3f).setCustomClientFactory(EntityFart::new).m_20719_(), "fart"));
    public static final RegistryObject<EntityType<EntityBananaSlug>> BANANA_SLUG = DEF_REG.register("banana_slug", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBananaSlug::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.8f, 0.4f).setTrackingRange(10), "banana_slug"));
    public static final RegistryObject<EntityType<EntityBlueJay>> BLUE_JAY = DEF_REG.register("blue_jay", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityBlueJay::new, (MobCategory)MobCategory.CREATURE).m_20699_(0.5f, 0.6f).setTrackingRange(10), "blue_jay"));
    public static final RegistryObject<EntityType<EntityCaiman>> CAIMAN = DEF_REG.register("caiman", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityCaiman::new, (MobCategory)MobCategory.CREATURE).m_20699_(1.3f, 0.6f).setTrackingRange(10), "caiman"));
    public static final RegistryObject<EntityType<EntityTriops>> TRIOPS = DEF_REG.register("triops", () -> AMEntityRegistry.registerEntity(EntityType.Builder.m_20704_(EntityTriops::new, (MobCategory)MobCategory.WATER_AMBIENT).m_20699_(0.7f, 0.25f).setTrackingRange(5), "triops"));

    private static EntityType registerEntity(EntityType.Builder builder, String entityName) {
        return builder.m_20712_(entityName);
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        SpawnPlacements.Type spawnsOnLeaves = SpawnPlacements.Type.create((String)"am_leaves", AMEntityRegistry::createLeavesSpawnPlacement);
        SpawnPlacements.m_21754_((EntityType)((EntityType)GRIZZLY_BEAR.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ROADRUNNER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRoadrunner::canRoadrunnerSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BONE_SERPENT.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_LAVA, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBoneSerpent::canBoneSerpentSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)GAZELLE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CROCODILE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCrocodile::canCrocodileSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)FLY.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFly::canFlySpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)HUMMINGBIRD.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityHummingbird::canHummingbirdSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ORCA.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityOrca::canOrcaSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SUNBIRD.get()), (SpawnPlacements.Type)SpawnPlacements.Type.NO_RESTRICTIONS, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySunbird::canSunbirdSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)GORILLA.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityGorilla::canGorillaSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CRIMSON_MOSQUITO.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCrimsonMosquito::canMosquitoSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)RATTLESNAKE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRattlesnake::canRattlesnakeSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ENDERGRADE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.NO_RESTRICTIONS, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityEndergrade::canEndergradeSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)HAMMERHEAD_SHARK.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityHammerheadShark::canHammerheadSharkSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)LOBSTER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityLobster::canLobsterSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)KOMODO_DRAGON.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityKomodoDragon::canKomodoDragonSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CAPUCHIN_MONKEY.get()), (SpawnPlacements.Type)spawnsOnLeaves, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityCapuchinMonkey::canCapuchinSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CENTIPEDE_HEAD.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCentipedeHead::canCentipedeSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)WARPED_TOAD.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityWarpedToad::canWarpedToadSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MOOSE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMoose::canMooseSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MIMICUBE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::m_217057_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)RACCOON.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BLOBFISH.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBlobfish::canBlobfishSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SEAL.get()), (SpawnPlacements.Type)SpawnPlacements.Type.NO_RESTRICTIONS, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySeal::canSealSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)COCKROACH.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCockroach::canCockroachSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SHOEBILL.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ELEPHANT.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SOUL_VULTURE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySoulVulture::canVultureSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SNOW_LEOPARD.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySnowLeopard::canSnowLeopardSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CROW.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityCrow::canCrowSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ALLIGATOR_SNAPPING_TURTLE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAlligatorSnappingTurtle::canTurtleSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MUNGUS.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMungus::canMungusSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MANTIS_SHRIMP.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMantisShrimp::canMantisShrimpSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)GUSTER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityGuster::canGusterSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)WARPED_MOSCO.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219019_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)STRADDLER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityStraddler::canStraddlerSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)STRADPOLE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_LAVA, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityStradpole::canStradpoleSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)EMU.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityEmu::canEmuSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)PLATYPUS.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityPlatypus::canPlatypusSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)DROPBEAR.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219019_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TASMANIAN_DEVIL.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)KANGAROO.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityKangaroo::canKangarooSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CACHALOT_WHALE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCachalotWhale::canCachalotWhaleSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)LEAFCUTTER_ANT.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ENDERIOPHAGE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.NO_RESTRICTIONS, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityEnderiophage::canEnderiophageSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BALD_EAGLE.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityBaldEagle::canEagleSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TIGER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTiger::canTigerSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TARANTULA_HAWK.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTarantulaHawk::canTarantulaHawkSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)VOID_WORM.get()), (SpawnPlacements.Type)SpawnPlacements.Type.NO_RESTRICTIONS, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityVoidWorm::canVoidWormSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)FRILLED_SHARK.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFrilledShark::canFrilledSharkSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MIMIC_OCTOPUS.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMimicOctopus::canMimicOctopusSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SEAGULL.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySeagull::canSeagullSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)FROSTSTALKER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFroststalker::canFroststalkerSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TUSKLIN.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTusklin::canTusklinSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)LAVIATHAN.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_LAVA, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityLaviathan::canLaviathanSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)COSMAW.get()), (SpawnPlacements.Type)SpawnPlacements.Type.NO_RESTRICTIONS, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCosmaw::canCosmawSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TOUCAN.get()), (SpawnPlacements.Type)spawnsOnLeaves, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityToucan::canToucanSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MANED_WOLF.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ANACONDA.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAnaconda::canAnacondaSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ANTEATER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAnteater::canAnteaterSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)ROCKY_ROLLER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRockyRoller::checkRockyRollerSpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)FLUTTER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFlutter::canFlutterSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)GELADA_MONKEY.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)JERBOA.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityJerboa::canJerboaSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TERRAPIN.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityTerrapin::canTerrapinSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)COMB_JELLY.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCombJelly::canCombJellySpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BUNFUNGUS.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBunfungus::canBunfungusSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BISON.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)GIANT_SQUID.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityGiantSquid::canGiantSquidSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)DEVILS_HOLE_PUPFISH.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityDevilsHolePupfish::canPupfishSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CATFISH.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCatfish::canCatfishSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)FLYING_FISH.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::m_218282_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SKELEWAG.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySkelewag::canSkelewagSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)RAIN_FROG.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityRainFrog::canRainFrogSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)POTOO.get()), (SpawnPlacements.Type)spawnsOnLeaves, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityPotoo::canPotooSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MUDSKIPPER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMudskipper::canMudskipperSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)RHINOCEROS.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SUGAR_GLIDER.get()), (SpawnPlacements.Type)spawnsOnLeaves, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntitySugarGlider::canSugarGliderSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)FARSEER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityFarseer::checkFarseerSpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SKREECHER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntitySkreecher::checkSkreecherSpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)UNDERMINER.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityUnderminer::checkUnderminerSpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)MURMUR.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMurmur::checkMurmurSpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)SKUNK.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::m_218104_);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BANANA_SLUG.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityBananaSlug::checkBananaSlugSpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)BLUE_JAY.get()), (SpawnPlacements.Type)spawnsOnLeaves, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING, EntityBlueJay::checkBlueJaySpawnRules);
        SpawnPlacements.m_21754_((EntityType)((EntityType)CAIMAN.get()), (SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityCaiman::canCaimanSpawn);
        SpawnPlacements.m_21754_((EntityType)((EntityType)TRIOPS.get()), (SpawnPlacements.Type)SpawnPlacements.Type.IN_WATER, (Heightmap.Types)Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::m_218282_);
        event.put((EntityType)GRIZZLY_BEAR.get(), EntityGrizzlyBear.bakeAttributes().m_22265_());
        event.put((EntityType)ROADRUNNER.get(), EntityRoadrunner.bakeAttributes().m_22265_());
        event.put((EntityType)BONE_SERPENT.get(), EntityBoneSerpent.bakeAttributes().m_22265_());
        event.put((EntityType)BONE_SERPENT_PART.get(), EntityBoneSerpentPart.bakeAttributes().m_22265_());
        event.put((EntityType)GAZELLE.get(), EntityGazelle.bakeAttributes().m_22265_());
        event.put((EntityType)CROCODILE.get(), EntityCrocodile.bakeAttributes().m_22265_());
        event.put((EntityType)FLY.get(), EntityFly.bakeAttributes().m_22265_());
        event.put((EntityType)HUMMINGBIRD.get(), EntityHummingbird.bakeAttributes().m_22265_());
        event.put((EntityType)ORCA.get(), EntityOrca.bakeAttributes().m_22265_());
        event.put((EntityType)SUNBIRD.get(), EntitySunbird.bakeAttributes().m_22265_());
        event.put((EntityType)GORILLA.get(), EntityGorilla.bakeAttributes().m_22265_());
        event.put((EntityType)CRIMSON_MOSQUITO.get(), EntityCrimsonMosquito.bakeAttributes().m_22265_());
        event.put((EntityType)RATTLESNAKE.get(), EntityRattlesnake.bakeAttributes().m_22265_());
        event.put((EntityType)ENDERGRADE.get(), EntityEndergrade.bakeAttributes().m_22265_());
        event.put((EntityType)HAMMERHEAD_SHARK.get(), EntityHammerheadShark.bakeAttributes().m_22265_());
        event.put((EntityType)LOBSTER.get(), EntityLobster.bakeAttributes().m_22265_());
        event.put((EntityType)KOMODO_DRAGON.get(), EntityKomodoDragon.bakeAttributes().m_22265_());
        event.put((EntityType)CAPUCHIN_MONKEY.get(), EntityCapuchinMonkey.bakeAttributes().m_22265_());
        event.put((EntityType)CENTIPEDE_HEAD.get(), EntityCentipedeHead.bakeAttributes().m_22265_());
        event.put((EntityType)CENTIPEDE_BODY.get(), EntityCentipedeBody.bakeAttributes().m_22265_());
        event.put((EntityType)CENTIPEDE_TAIL.get(), EntityCentipedeTail.bakeAttributes().m_22265_());
        event.put((EntityType)WARPED_TOAD.get(), EntityWarpedToad.bakeAttributes().m_22265_());
        event.put((EntityType)MOOSE.get(), EntityMoose.bakeAttributes().m_22265_());
        event.put((EntityType)MIMICUBE.get(), EntityMimicube.bakeAttributes().m_22265_());
        event.put((EntityType)RACCOON.get(), EntityRaccoon.bakeAttributes().m_22265_());
        event.put((EntityType)BLOBFISH.get(), EntityBlobfish.bakeAttributes().m_22265_());
        event.put((EntityType)SEAL.get(), EntitySeal.bakeAttributes().m_22265_());
        event.put((EntityType)COCKROACH.get(), EntityCockroach.bakeAttributes().m_22265_());
        event.put((EntityType)SHOEBILL.get(), EntityShoebill.bakeAttributes().m_22265_());
        event.put((EntityType)ELEPHANT.get(), EntityElephant.bakeAttributes().m_22265_());
        event.put((EntityType)SOUL_VULTURE.get(), EntitySoulVulture.bakeAttributes().m_22265_());
        event.put((EntityType)SNOW_LEOPARD.get(), EntitySnowLeopard.bakeAttributes().m_22265_());
        event.put((EntityType)SPECTRE.get(), EntitySpectre.bakeAttributes().m_22265_());
        event.put((EntityType)CROW.get(), EntityCrow.bakeAttributes().m_22265_());
        event.put((EntityType)ALLIGATOR_SNAPPING_TURTLE.get(), EntityAlligatorSnappingTurtle.bakeAttributes().m_22265_());
        event.put((EntityType)MUNGUS.get(), EntityMungus.bakeAttributes().m_22265_());
        event.put((EntityType)MANTIS_SHRIMP.get(), EntityMantisShrimp.bakeAttributes().m_22265_());
        event.put((EntityType)GUSTER.get(), EntityGuster.bakeAttributes().m_22265_());
        event.put((EntityType)WARPED_MOSCO.get(), EntityWarpedMosco.bakeAttributes().m_22265_());
        event.put((EntityType)STRADDLER.get(), EntityStraddler.bakeAttributes().m_22265_());
        event.put((EntityType)STRADPOLE.get(), EntityStradpole.bakeAttributes().m_22265_());
        event.put((EntityType)EMU.get(), EntityEmu.bakeAttributes().m_22265_());
        event.put((EntityType)PLATYPUS.get(), EntityPlatypus.bakeAttributes().m_22265_());
        event.put((EntityType)DROPBEAR.get(), EntityDropBear.bakeAttributes().m_22265_());
        event.put((EntityType)TASMANIAN_DEVIL.get(), EntityTasmanianDevil.bakeAttributes().m_22265_());
        event.put((EntityType)KANGAROO.get(), EntityKangaroo.bakeAttributes().m_22265_());
        event.put((EntityType)CACHALOT_WHALE.get(), EntityCachalotWhale.bakeAttributes().m_22265_());
        event.put((EntityType)LEAFCUTTER_ANT.get(), EntityLeafcutterAnt.bakeAttributes().m_22265_());
        event.put((EntityType)ENDERIOPHAGE.get(), EntityEnderiophage.bakeAttributes().m_22265_());
        event.put((EntityType)BALD_EAGLE.get(), EntityBaldEagle.bakeAttributes().m_22265_());
        event.put((EntityType)TIGER.get(), EntityTiger.bakeAttributes().m_22265_());
        event.put((EntityType)TARANTULA_HAWK.get(), EntityTarantulaHawk.bakeAttributes().m_22265_());
        event.put((EntityType)VOID_WORM.get(), EntityVoidWorm.bakeAttributes().m_22265_());
        event.put((EntityType)VOID_WORM_PART.get(), EntityVoidWormPart.bakeAttributes().m_22265_());
        event.put((EntityType)FRILLED_SHARK.get(), EntityFrilledShark.bakeAttributes().m_22265_());
        event.put((EntityType)MIMIC_OCTOPUS.get(), EntityMimicOctopus.bakeAttributes().m_22265_());
        event.put((EntityType)SEAGULL.get(), EntitySeagull.bakeAttributes().m_22265_());
        event.put((EntityType)FROSTSTALKER.get(), EntityFroststalker.bakeAttributes().m_22265_());
        event.put((EntityType)TUSKLIN.get(), EntityTusklin.bakeAttributes().m_22265_());
        event.put((EntityType)LAVIATHAN.get(), EntityLaviathan.bakeAttributes().m_22265_());
        event.put((EntityType)COSMAW.get(), EntityCosmaw.bakeAttributes().m_22265_());
        event.put((EntityType)TOUCAN.get(), EntityToucan.bakeAttributes().m_22265_());
        event.put((EntityType)MANED_WOLF.get(), EntityManedWolf.bakeAttributes().m_22265_());
        event.put((EntityType)ANACONDA.get(), EntityAnaconda.bakeAttributes().m_22265_());
        event.put((EntityType)ANACONDA_PART.get(), EntityAnacondaPart.bakeAttributes().m_22265_());
        event.put((EntityType)ANTEATER.get(), EntityAnteater.bakeAttributes().m_22265_());
        event.put((EntityType)ROCKY_ROLLER.get(), EntityRockyRoller.bakeAttributes().m_22265_());
        event.put((EntityType)FLUTTER.get(), EntityFlutter.bakeAttributes().m_22265_());
        event.put((EntityType)GELADA_MONKEY.get(), EntityGeladaMonkey.bakeAttributes().m_22265_());
        event.put((EntityType)JERBOA.get(), EntityJerboa.bakeAttributes().m_22265_());
        event.put((EntityType)TERRAPIN.get(), EntityTerrapin.bakeAttributes().m_22265_());
        event.put((EntityType)COMB_JELLY.get(), EntityCombJelly.bakeAttributes().m_22265_());
        event.put((EntityType)COSMIC_COD.get(), EntityCosmicCod.bakeAttributes().m_22265_());
        event.put((EntityType)BUNFUNGUS.get(), EntityBunfungus.bakeAttributes().m_22265_());
        event.put((EntityType)BISON.get(), EntityBison.bakeAttributes().m_22265_());
        event.put((EntityType)GIANT_SQUID.get(), EntityGiantSquid.bakeAttributes().m_22265_());
        event.put((EntityType)SEA_BEAR.get(), EntitySeaBear.bakeAttributes().m_22265_());
        event.put((EntityType)DEVILS_HOLE_PUPFISH.get(), EntityDevilsHolePupfish.bakeAttributes().m_22265_());
        event.put((EntityType)CATFISH.get(), EntityCatfish.bakeAttributes().m_22265_());
        event.put((EntityType)FLYING_FISH.get(), EntityFlyingFish.bakeAttributes().m_22265_());
        event.put((EntityType)SKELEWAG.get(), EntitySkelewag.bakeAttributes().m_22265_());
        event.put((EntityType)RAIN_FROG.get(), EntityRainFrog.bakeAttributes().m_22265_());
        event.put((EntityType)POTOO.get(), EntityPotoo.bakeAttributes().m_22265_());
        event.put((EntityType)MUDSKIPPER.get(), EntityMudskipper.bakeAttributes().m_22265_());
        event.put((EntityType)RHINOCEROS.get(), EntityRhinoceros.bakeAttributes().m_22265_());
        event.put((EntityType)SUGAR_GLIDER.get(), EntitySugarGlider.bakeAttributes().m_22265_());
        event.put((EntityType)FARSEER.get(), EntityFarseer.bakeAttributes().m_22265_());
        event.put((EntityType)SKREECHER.get(), EntitySkreecher.bakeAttributes().m_22265_());
        event.put((EntityType)UNDERMINER.get(), EntityUnderminer.bakeAttributes().m_22265_());
        event.put((EntityType)MURMUR.get(), EntityMurmur.bakeAttributes().m_22265_());
        event.put((EntityType)MURMUR_HEAD.get(), EntityMurmurHead.bakeAttributes().m_22265_());
        event.put((EntityType)SKUNK.get(), EntitySkunk.bakeAttributes().m_22265_());
        event.put((EntityType)BANANA_SLUG.get(), EntityBananaSlug.bakeAttributes().m_22265_());
        event.put((EntityType)BLUE_JAY.get(), EntityBlueJay.bakeAttributes().m_22265_());
        event.put((EntityType)CAIMAN.get(), EntityCaiman.bakeAttributes().m_22265_());
        event.put((EntityType)TRIOPS.get(), EntityTriops.bakeAttributes().m_22265_());
    }

    public static Predicate<LivingEntity> buildPredicateFromTag(TagKey<EntityType<?>> entityTag) {
        if (entityTag == null) {
            return Predicates.alwaysFalse();
        }
        return e -> e.m_6084_() && e.m_6095_().m_204039_(entityTag);
    }

    public static Predicate<LivingEntity> buildPredicateFromTagTameable(TagKey<EntityType<?>> entityTag, LivingEntity owner) {
        if (entityTag == null) {
            return Predicates.alwaysFalse();
        }
        return e -> e.m_6084_() && e.m_6095_().m_204039_(entityTag) && !owner.m_7307_((Entity)e);
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason) {
        if (reason == MobSpawnType.SPAWNER) {
            return true;
        }
        return rolls <= 0 || random.m_188503_(rolls) == 0;
    }

    public static boolean createLeavesSpawnPlacement(LevelReader level, BlockPos pos, EntityType<?> type) {
        BlockPos blockpos = pos.m_7494_();
        BlockPos blockpos1 = pos.m_7495_();
        FluidState fluidstate = level.m_6425_(pos);
        BlockState blockstate = level.m_8055_(pos);
        BlockState blockstate1 = level.m_8055_(blockpos1);
        if (!blockstate1.isValidSpawn(level, blockpos1, SpawnPlacements.Type.ON_GROUND, type) && !blockstate1.m_204336_(BlockTags.f_13035_)) {
            return false;
        }
        return NaturalSpawner.m_47056_((BlockGetter)level, (BlockPos)pos, (BlockState)blockstate, (FluidState)fluidstate, type) && NaturalSpawner.m_47056_((BlockGetter)level, (BlockPos)blockpos, (BlockState)level.m_8055_(blockpos), (FluidState)level.m_6425_(blockpos), type);
    }
}

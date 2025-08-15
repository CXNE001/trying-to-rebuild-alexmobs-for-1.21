/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetExperiencePacket
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.server.packs.resources.PreparableReloadListener
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal
 *  net.minecraft.world.entity.animal.Cat
 *  net.minecraft.world.entity.animal.Dolphin
 *  net.minecraft.world.entity.animal.Fox
 *  net.minecraft.world.entity.animal.Ocelot
 *  net.minecraft.world.entity.animal.PolarBear
 *  net.minecraft.world.entity.animal.Rabbit
 *  net.minecraft.world.entity.animal.Wolf
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Spider
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.entity.npc.WanderingTrader
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.ComputeFovModifierEvent
 *  net.minecraftforge.common.ForgeMod
 *  net.minecraftforge.event.AddReloadListenerEvent
 *  net.minecraftforge.event.TickEvent$LevelTickEvent
 *  net.minecraftforge.event.entity.EntityEvent$Size
 *  net.minecraftforge.event.entity.EntityStruckByLightningEvent
 *  net.minecraftforge.event.entity.ProjectileImpactEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Finish
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingTickEvent
 *  net.minecraftforge.event.entity.living.LootingLevelEvent
 *  net.minecraftforge.event.entity.living.MobSpawnEvent$AllowDespawn
 *  net.minecraftforge.event.entity.living.MobSpawnEvent$FinalizeSpawn
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$HarvestCheck
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickEmpty
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickEmpty
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.event.village.VillagerTradesEvent
 *  net.minecraftforge.event.village.WandererTradesEvent
 *  net.minecraftforge.eventbus.api.Event$Result
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.items.ItemHandlerHelper
 *  org.antlr.v4.runtime.misc.Triple
 */
package com.github.alexthe666.alexsmobs.event;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.effect.EffectClinging;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.github.alexthe666.alexsmobs.entity.EntityEmu;
import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import com.github.alexthe666.alexsmobs.entity.EntityFly;
import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import com.github.alexthe666.alexsmobs.entity.EntityJerboa;
import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.EntityMoose;
import com.github.alexthe666.alexsmobs.entity.EntitySeaBear;
import com.github.alexthe666.alexsmobs.entity.EntitySeal;
import com.github.alexthe666.alexsmobs.entity.EntitySnowLeopard;
import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.entity.util.FlyingFishBootsUtil;
import com.github.alexthe666.alexsmobs.entity.util.RainbowUtil;
import com.github.alexthe666.alexsmobs.entity.util.RockyChestplateUtil;
import com.github.alexthe666.alexsmobs.entity.util.VineLassoUtil;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.ILeftClick;
import com.github.alexthe666.alexsmobs.item.ItemGhostlyPickaxe;
import com.github.alexthe666.alexsmobs.message.MessageSwingArm;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.github.alexthe666.alexsmobs.misc.EmeraldsForItemsTrade;
import com.github.alexthe666.alexsmobs.misc.ItemsForEmeraldsTrade;
import com.github.alexthe666.alexsmobs.world.AMWorldData;
import com.github.alexthe666.alexsmobs.world.BeachedCachalotWhaleSpawner;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import org.antlr.v4.runtime.misc.Triple;

@Mod.EventBusSubscriber(modid="alexsmobs", bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    public static final UUID ALEX_UUID = UUID.fromString("71363abe-fd03-49c9-940d-aae8b8209b7c");
    public static final UUID CARRO_UUID = UUID.fromString("98905d4a-1cbc-41a4-9ded-2300404e2290");
    private static final UUID SAND_SPEED_MODIFIER = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF28E");
    private static final UUID SNEAK_SPEED_MODIFIER = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF28F");
    private static final AttributeModifier SAND_SPEED_BONUS = new AttributeModifier(SAND_SPEED_MODIFIER, "roadrunner speed bonus", (double)0.1f, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier SNEAK_SPEED_BONUS = new AttributeModifier(SNEAK_SPEED_MODIFIER, "frontier cap speed bonus", (double)0.1f, AttributeModifier.Operation.ADDITION);
    private static final Map<ServerLevel, BeachedCachalotWhaleSpawner> BEACHED_CACHALOT_WHALE_SPAWNER_MAP = new HashMap<ServerLevel, BeachedCachalotWhaleSpawner>();
    public static final ObjectList<Triple<ServerPlayer, ServerLevel, BlockPos>> teleportPlayers = new ObjectArrayList();
    private static final Random RAND = new Random();

    @SubscribeEvent
    public static void onServerTick(TickEvent.LevelTickEvent tick) {
        AMWorldData data;
        Level level;
        if (!tick.level.f_46443_ && (level = tick.level) instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel)level;
            BEACHED_CACHALOT_WHALE_SPAWNER_MAP.computeIfAbsent(serverWorld, k -> new BeachedCachalotWhaleSpawner(serverWorld));
            BeachedCachalotWhaleSpawner spawner = BEACHED_CACHALOT_WHALE_SPAWNER_MAP.get(serverWorld);
            spawner.tick();
            if (!teleportPlayers.isEmpty()) {
                for (Triple triple : teleportPlayers) {
                    ServerPlayer player = (ServerPlayer)triple.a;
                    ServerLevel endpointWorld = (ServerLevel)triple.b;
                    BlockPos endpoint = (BlockPos)triple.c;
                    int heightFromMap = endpointWorld.m_6924_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, endpoint.m_123341_(), endpoint.m_123343_());
                    endpoint = new BlockPos(endpoint.m_123341_(), Math.max(heightFromMap, endpoint.m_123342_()), endpoint.m_123343_());
                    player.m_8999_(endpointWorld, (double)endpoint.m_123341_() + 0.5, (double)endpoint.m_123342_() + 0.5, (double)endpoint.m_123343_() + 0.5, player.m_146908_(), player.m_146909_());
                    ChunkPos chunkpos = new ChunkPos(endpoint);
                    endpointWorld.m_7726_().m_8387_(TicketType.f_9448_, chunkpos, 1, (Object)player.m_19879_());
                    player.f_8906_.m_9829_((Packet)new ClientboundSetExperiencePacket(player.f_36080_, player.f_36079_, player.f_36078_));
                }
                teleportPlayers.clear();
            }
        }
        if ((data = AMWorldData.get(tick.level)) != null) {
            data.tickPupfish();
        }
    }

    protected static BlockHitResult rayTrace(Level worldIn, Player player, ClipContext.Fluid fluidMode) {
        float x = player.m_146909_();
        float y = player.m_146908_();
        Vec3 vector3d = player.m_20299_(1.0f);
        float f0 = -y * ((float)Math.PI / 180) - (float)Math.PI;
        float f1 = -x * ((float)Math.PI / 180);
        float f2 = Mth.m_14089_((float)f0);
        float f3 = Mth.m_14031_((float)f0);
        float f4 = -Mth.m_14089_((float)f1);
        float f5 = Mth.m_14031_((float)f1);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_();
        Vec3 vector3d1 = vector3d.m_82520_((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return worldIn.m_45547_(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, (Entity)player));
    }

    @SubscribeEvent
    public static void onItemUseLast(LivingEntityUseItemEvent.Finish event) {
        if (event.getItem().m_41720_() == Items.f_42730_ && RAND.nextInt(3) == 0 && event.getEntity().m_21023_((MobEffect)AMEffectRegistry.ENDER_FLU.get())) {
            event.getEntity().m_21195_((MobEffect)AMEffectRegistry.ENDER_FLU.get());
        }
    }

    @SubscribeEvent
    public static void onEntityResize(EntityEvent.Size event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player entity2 = (Player)entity;
            Map potions = entity2.m_21221_();
            if (event.getEntity().m_9236_() != null && potions != null && !potions.isEmpty() && potions.containsKey(AMEffectRegistry.CLINGING) && EffectClinging.isUpsideDown((LivingEntity)entity2)) {
                float minus = event.getOldSize().f_20378_ - event.getOldEyeHeight();
                event.setNewEyeHeight(minus);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        CompoundTag playerData;
        CompoundTag data;
        if (AMConfig.giveBookOnStartup && (data = (playerData = event.getEntity().getPersistentData()).m_128469_("PlayerPersisted")) != null && !data.m_128471_("alexsmobs_has_book")) {
            ItemHandlerHelper.giveItemToPlayer((Player)event.getEntity(), (ItemStack)new ItemStack((ItemLike)AMItemRegistry.ANIMAL_DICTIONARY.get()));
            boolean isAlex = Objects.equals(event.getEntity().m_20148_(), ALEX_UUID);
            if (isAlex || Objects.equals(event.getEntity().m_20148_(), CARRO_UUID)) {
                ItemHandlerHelper.giveItemToPlayer((Player)event.getEntity(), (ItemStack)new ItemStack((ItemLike)AMItemRegistry.BEAR_DUST.get()));
            }
            if (isAlex) {
                ItemHandlerHelper.giveItemToPlayer((Player)event.getEntity(), (ItemStack)new ItemStack((ItemLike)AMItemRegistry.NOVELTY_HAT.get()));
            }
            data.m_128379_("alexsmobs_has_book", true);
            playerData.m_128365_("PlayerPersisted", (Tag)data);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        ILeftClick iLeftClick;
        boolean flag = false;
        ItemStack leftItem = event.getEntity().m_21206_();
        ItemStack rightItem = event.getEntity().m_21205_();
        Item item = leftItem.m_41720_();
        if (item instanceof ILeftClick) {
            iLeftClick = (ILeftClick)item;
            iLeftClick.onLeftClick(leftItem, (LivingEntity)event.getEntity());
            flag = true;
        }
        if ((item = rightItem.m_41720_()) instanceof ILeftClick) {
            iLeftClick = (ILeftClick)item;
            iLeftClick.onLeftClick(rightItem, (LivingEntity)event.getEntity());
            flag = true;
        }
        if (flag && event.getLevel().f_46443_) {
            AlexsMobs.sendMSGToServer(MessageSwingArm.INSTANCE);
        }
    }

    @SubscribeEvent
    public static void onStruckByLightning(EntityStruckByLightningEvent event) {
        if (event.getEntity().m_6095_() == EntityType.f_20480_ && !event.getEntity().m_9236_().f_46443_) {
            ServerLevel level = (ServerLevel)event.getEntity().m_9236_();
            event.setCanceled(true);
            EntityGiantSquid squid = (EntityGiantSquid)((EntityType)AMEntityRegistry.GIANT_SQUID.get()).m_20615_((Level)level);
            squid.m_7678_(event.getEntity().m_20185_(), event.getEntity().m_20186_(), event.getEntity().m_20189_(), event.getEntity().m_146908_(), event.getEntity().m_146909_());
            squid.m_6518_((ServerLevelAccessor)level, level.m_6436_(squid.m_20183_()), MobSpawnType.CONVERSION, null, null);
            if (event.getEntity().m_8077_()) {
                squid.m_6593_(event.getEntity().m_7770_());
                squid.m_20340_(event.getEntity().m_20151_());
            }
            squid.setBlue(true);
            squid.m_21530_();
            level.m_47205_((Entity)squid);
            event.getEntity().m_146870_();
        }
    }

    @SubscribeEvent
    public void onProjectileHit(ProjectileImpactEvent event) {
        EntityHitResult hitResult;
        HitResult hitResult2 = event.getRayTraceResult();
        if (hitResult2 instanceof EntityHitResult && (hitResult2 = (hitResult = (EntityHitResult)hitResult2).m_82443_()) instanceof EntityEmu) {
            EntityEmu emu = (EntityEmu)hitResult2;
            if (!event.getEntity().m_9236_().f_46443_) {
                Entity entity = event.getEntity();
                if (entity instanceof AbstractArrow) {
                    AbstractArrow arrow = (AbstractArrow)entity;
                    arrow.m_36767_((byte)0);
                }
                if ((emu.getAnimation() == EntityEmu.ANIMATION_DODGE_RIGHT || emu.getAnimation() == EntityEmu.ANIMATION_DODGE_LEFT) && emu.getAnimationTick() < 7) {
                    event.setCanceled(true);
                }
                if (emu.getAnimation() != EntityEmu.ANIMATION_DODGE_RIGHT && emu.getAnimation() != EntityEmu.ANIMATION_DODGE_LEFT) {
                    Projectile projectile;
                    Entity entity2;
                    Entity entity3;
                    boolean left = true;
                    Vec3 arrowPos = event.getEntity().m_20182_();
                    Vec3 rightVector = emu.m_20154_().m_82524_(1.5707964f).m_82549_(emu.m_20182_());
                    Vec3 leftVector = emu.m_20154_().m_82524_(-1.5707964f).m_82549_(emu.m_20182_());
                    left = arrowPos.m_82554_(rightVector) < arrowPos.m_82554_(leftVector) ? false : (arrowPos.m_82554_(rightVector) > arrowPos.m_82554_(leftVector) ? true : emu.m_217043_().m_188499_());
                    Vec3 vector3d2 = event.getEntity().m_20184_().m_82524_((float)((double)(left ? -0.5f : 0.5f) * Math.PI)).m_82541_();
                    emu.setAnimation(left ? EntityEmu.ANIMATION_DODGE_LEFT : EntityEmu.ANIMATION_DODGE_RIGHT);
                    emu.f_19812_ = true;
                    if (!emu.f_19862_) {
                        emu.m_6478_(MoverType.SELF, new Vec3(vector3d2.m_7096_() * 0.25, (double)0.1f, vector3d2.m_7094_() * 0.25));
                    }
                    if (!event.getEntity().m_9236_().f_46443_ && (entity3 = event.getEntity()) instanceof Projectile && (entity2 = (projectile = (Projectile)entity3).m_19749_()) instanceof ServerPlayer) {
                        ServerPlayer serverPlayer = (ServerPlayer)entity2;
                        AMAdvancementTriggerRegistry.EMU_DODGE.trigger(serverPlayer);
                    }
                    emu.m_20256_(emu.m_20184_().m_82520_(vector3d2.m_7096_() * 0.5, (double)0.32f, vector3d2.m_7094_() * 0.5));
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityDespawnAttempt(MobSpawnEvent.AllowDespawn event) {
        if (event.getEntity().m_21023_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()) && event.getEntity().m_21124_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()) != null && event.getEntity().m_21124_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()).m_19564_() > 0) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onTradeSetup(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.f_35591_) {
            EmeraldsForItemsTrade ambergrisTrade = new EmeraldsForItemsTrade((ItemLike)AMItemRegistry.AMBERGRIS.get(), 20, 3, 4);
            List list = (List)event.getTrades().get(2);
            list.add(ambergrisTrade);
            event.getTrades().put(2, (Object)list);
        }
    }

    @SubscribeEvent
    public void onWanderingTradeSetup(WandererTradesEvent event) {
        if (AMConfig.wanderingTraderOffers) {
            List genericTrades = event.getGenericTrades();
            List rareTrades = event.getRareTrades();
            genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.ANIMAL_DICTIONARY.get(), 4, 1, 2, 1));
            genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.ACACIA_BLOSSOM.get(), 3, 2, 2, 1));
            if (AMConfig.cockroachSpawnWeight > 0) {
                genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.COCKROACH_OOTHECA.get(), 2, 1, 2, 1));
            }
            if (AMConfig.blobfishSpawnWeight > 0) {
                genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.BLOBFISH_BUCKET.get(), 4, 1, 3, 1));
            }
            if (AMConfig.crocodileSpawnWeight > 0) {
                genericTrades.add(new ItemsForEmeraldsTrade(((Block)AMBlockRegistry.CROCODILE_EGG.get()).m_5456_(), 6, 1, 2, 1));
            }
            genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.BEAR_FUR.get(), 1, 1, 2, 1));
            genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.CROCODILE_SCUTE.get(), 5, 1, 2, 1));
            genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.ROADRUNNER_FEATHER.get(), 1, 2, 2, 2));
            genericTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.MOSQUITO_LARVA.get(), 1, 3, 5, 1));
            rareTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.SOMBRERO.get(), 20, 1, 1, 1));
            rareTrades.add(new ItemsForEmeraldsTrade((Block)AMBlockRegistry.BANANA_PEEL.get(), 1, 2, 1, 1));
            rareTrades.add(new ItemsForEmeraldsTrade((Item)AMItemRegistry.BLOOD_SAC.get(), 5, 2, 3, 1));
        }
    }

    @SubscribeEvent
    public void onLootLevelEvent(LootingLevelEvent event) {
        DamageSource src = event.getDamageSource();
        if (src != null && src.m_7639_() instanceof EntitySnowLeopard) {
            event.setLootingLevel(event.getLootingLevel() + 2);
        }
    }

    @SubscribeEvent
    public void onUseItem(PlayerInteractEvent.RightClickItem event) {
        BlockHitResult raytraceresult;
        EntityElephant elephant;
        Entity entity;
        Player player = event.getEntity();
        if (event.getItemStack().m_41720_() == Items.f_42405_ && (entity = player.m_20202_()) instanceof EntityElephant && (elephant = (EntityElephant)entity).triggerCharge(event.getItemStack())) {
            player.m_6674_(event.getHand());
            if (!player.m_7500_()) {
                event.getItemStack().m_41774_(1);
            }
        }
        if (event.getItemStack().m_41720_() == Items.f_42590_ && AMConfig.lavaBottleEnabled && (raytraceresult = ServerEvents.rayTrace(event.getLevel(), player, ClipContext.Fluid.SOURCE_ONLY)).m_6662_() == HitResult.Type.BLOCK) {
            BlockPos blockpos = raytraceresult.m_82425_();
            if (event.getLevel().m_7966_(player, blockpos) && event.getLevel().m_6425_(blockpos).m_205070_(FluidTags.f_13132_)) {
                player.m_146850_(GameEvent.f_223698_);
                event.getLevel().m_6263_(player, player.m_20185_(), player.m_20186_(), player.m_20189_(), SoundEvents.f_11770_, SoundSource.NEUTRAL, 1.0f, 1.0f);
                player.m_36246_(Stats.f_12982_.m_12902_((Object)Items.f_42590_));
                player.m_20254_(6);
                if (!player.m_36356_(new ItemStack((ItemLike)AMItemRegistry.LAVA_BOTTLE.get()))) {
                    player.m_19983_(new ItemStack((ItemLike)AMItemRegistry.LAVA_BOTTLE.get()));
                }
                player.m_6674_(event.getHand());
                if (!player.m_7500_()) {
                    event.getItemStack().m_41774_(1);
                }
            }
        }
    }

    @SubscribeEvent
    public void onInteractWithEntity(PlayerInteractEvent.EntityInteract event) {
        Entity entity = event.getTarget();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            if (!event.getEntity().m_6144_() && VineLassoUtil.hasLassoData(living)) {
                if (!event.getEntity().m_9236_().f_46443_) {
                    event.getTarget().m_19983_(new ItemStack((ItemLike)AMItemRegistry.VINE_LASSO.get()));
                }
                VineLassoUtil.lassoTo(null, living);
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
            if (!(event.getTarget() instanceof Player) && !(event.getTarget() instanceof EntityEndergrade) && living.m_21023_((MobEffect)AMEffectRegistry.ENDER_FLU.get()) && event.getItemStack().m_41720_() == Items.f_42730_) {
                if (!event.getEntity().m_7500_()) {
                    event.getItemStack().m_41774_(1);
                }
                event.getTarget().m_146850_(GameEvent.f_157806_);
                event.getTarget().m_5496_(SoundEvents.f_11912_, 1.0f, 0.5f + event.getEntity().m_217043_().m_188501_());
                if (event.getEntity().m_217043_().m_188501_() < 0.4f) {
                    living.m_21195_((MobEffect)AMEffectRegistry.ENDER_FLU.get());
                    Items.f_42730_.m_5922_(event.getItemStack().m_41777_(), event.getLevel(), (LivingEntity)event.getTarget());
                }
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
            if (RainbowUtil.getRainbowType(living) > 0 && event.getItemStack().m_41720_() == Items.f_41902_) {
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
                RainbowUtil.setRainbowType(living, 0);
                if (!event.getEntity().m_7500_()) {
                    event.getItemStack().m_41774_(1);
                }
                ItemStack wetSponge = new ItemStack((ItemLike)Items.f_41903_);
                if (!event.getEntity().m_36356_(wetSponge)) {
                    event.getEntity().m_36176_(wetSponge, true);
                }
            }
            if (living instanceof Rabbit) {
                Rabbit rabbit = (Rabbit)living;
                if (event.getItemStack().m_41720_() == AMItemRegistry.MUNGAL_SPORES.get() && AMConfig.bunfungusTransformation) {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    if (!event.getEntity().m_9236_().f_46443_ && random.nextFloat() < 0.15f) {
                        EntityBunfungus bunfungus = (EntityBunfungus)rabbit.m_21406_((EntityType)AMEntityRegistry.BUNFUNGUS.get(), true);
                        if (bunfungus != null) {
                            event.getEntity().m_9236_().m_7967_((Entity)bunfungus);
                            bunfungus.setTransformsIn(50);
                        }
                    } else {
                        for (int i = 0; i < 2 + random.nextInt(2); ++i) {
                            double d0 = random.nextGaussian() * 0.02;
                            double d1 = (double)0.05f + random.nextGaussian() * 0.02;
                            double d2 = random.nextGaussian() * 0.02;
                            event.getTarget().m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.BUNFUNGUS_TRANSFORMATION.get(), event.getTarget().m_20208_((double)0.7f), event.getTarget().m_20227_((double)0.6f), event.getTarget().m_20262_((double)0.7f), d0, d1, d2);
                        }
                    }
                    if (!event.getEntity().m_7500_()) {
                        event.getItemStack().m_41774_(1);
                    }
                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }
        }
    }

    @SubscribeEvent
    public void onUseItemAir(PlayerInteractEvent.RightClickEmpty event) {
        ItemStack stack = event.getEntity().m_21120_(event.getHand());
        if (stack.m_41619_()) {
            stack = event.getEntity().m_6844_(EquipmentSlot.MAINHAND);
        }
        if (RainbowUtil.getRainbowType((LivingEntity)event.getEntity()) > 0 && stack.m_150930_(Items.f_41902_)) {
            event.getEntity().m_6674_(InteractionHand.MAIN_HAND);
            RainbowUtil.setRainbowType((LivingEntity)event.getEntity(), 0);
            if (!event.getEntity().m_7500_()) {
                stack.m_41774_(1);
            }
            ItemStack wetSponge = new ItemStack((ItemLike)Items.f_41903_);
            if (!event.getEntity().m_36356_(wetSponge)) {
                event.getEntity().m_36176_(wetSponge, true);
            }
        }
    }

    @SubscribeEvent
    public void onUseItemOnBlock(PlayerInteractEvent.RightClickBlock event) {
        if (AlexsMobs.isAprilFools() && event.getItemStack().m_150930_(Items.f_42398_) && !event.getEntity().m_36335_().m_41519_(Items.f_42398_)) {
            BlockState state = event.getEntity().m_9236_().m_8055_(event.getPos());
            boolean flag = false;
            if (state.m_60713_(Blocks.f_49992_)) {
                flag = true;
                event.getEntity().m_9236_().m_46597_(event.getPos(), ((Block)AMBlockRegistry.SAND_CIRCLE.get()).m_49966_());
            } else if (state.m_60713_(Blocks.f_49993_)) {
                flag = true;
                event.getEntity().m_9236_().m_46597_(event.getPos(), ((Block)AMBlockRegistry.RED_SAND_CIRCLE.get()).m_49966_());
            }
            if (flag) {
                event.setCanceled(true);
                event.getEntity().m_146850_(GameEvent.f_157797_);
                event.getEntity().m_5496_(SoundEvents.f_12331_, 1.0f, 1.0f);
                event.getEntity().m_36335_().m_41524_(Items.f_42398_, 30);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }

    @SubscribeEvent
    public void onEntityDrops(LivingDropsEvent event) {
        if (VineLassoUtil.hasLassoData(event.getEntity())) {
            VineLassoUtil.lassoTo(null, event.getEntity());
            event.getDrops().add(new ItemEntity(event.getEntity().m_9236_(), event.getEntity().m_20185_(), event.getEntity().m_20186_(), event.getEntity().m_20189_(), new ItemStack((ItemLike)AMItemRegistry.VINE_LASSO.get())));
        }
    }

    @SubscribeEvent
    public void onEntityFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        Mob entity = event.getEntity();
        if (entity instanceof WanderingTrader) {
            WanderingTrader trader = (WanderingTrader)entity;
            if (AMConfig.elephantTraderSpawnChance > 0.0) {
                Biome biome = (Biome)event.getLevel().m_204166_(entity.m_20183_()).m_203334_();
                if ((double)RAND.nextFloat() <= AMConfig.elephantTraderSpawnChance && (!AMConfig.limitElephantTraderBiomes || biome.m_47554_() >= 1.0f)) {
                    ChunkPos chunkPos = new ChunkPos(trader.m_20183_());
                    if (event.getLevel().m_7726_().m_7131_(chunkPos.f_45578_, chunkPos.f_45579_) != null) {
                        EntityElephant elephant = (EntityElephant)((EntityType)AMEntityRegistry.ELEPHANT.get()).m_20615_(trader.m_9236_());
                        elephant.m_20359_((Entity)trader);
                        if (elephant.canSpawnWithTraderHere()) {
                            elephant.setTrader(true);
                            elephant.setChested(true);
                            if (!event.getLevel().m_5776_()) {
                                trader.m_9236_().m_7967_((Entity)elephant);
                                trader.m_7998_((Entity)elephant, true);
                            }
                            elephant.addElephantLoot(null, RAND.nextInt());
                        }
                    }
                }
            }
        }
        try {
            if (AMConfig.spidersAttackFlies && entity instanceof Spider) {
                Spider spider = (Spider)entity;
                spider.f_21346_.m_25352_(4, (Goal)new NearestAttackableTargetGoal((Mob)spider, EntityFly.class, 1, true, false, null));
            } else if (AMConfig.wolvesAttackMoose && entity instanceof Wolf) {
                Wolf wolf = (Wolf)entity;
                wolf.f_21346_.m_25352_(6, (Goal)new NonTameRandomTargetGoal((TamableAnimal)wolf, EntityMoose.class, false, null));
            } else if (AMConfig.polarBearsAttackSeals && entity instanceof PolarBear) {
                PolarBear bear = (PolarBear)entity;
                bear.f_21346_.m_25352_(6, (Goal)new NearestAttackableTargetGoal((Mob)bear, EntitySeal.class, 15, true, true, null));
            } else if (entity instanceof Creeper) {
                Creeper creeper = (Creeper)entity;
                creeper.f_21346_.m_25352_(3, (Goal)new AvoidEntityGoal((PathfinderMob)creeper, EntitySnowLeopard.class, 6.0f, 1.0, 1.2));
                creeper.f_21346_.m_25352_(3, (Goal)new AvoidEntityGoal((PathfinderMob)creeper, EntityTiger.class, 6.0f, 1.0, 1.2));
            } else if (AMConfig.catsAndFoxesAttackJerboas && (entity instanceof Fox || entity instanceof Cat || entity instanceof Ocelot)) {
                Mob mb = entity;
                mb.f_21346_.m_25352_(6, (Goal)new NearestAttackableTargetGoal(mb, EntityJerboa.class, 45, true, true, null));
            } else if (AMConfig.bunfungusTransformation && entity instanceof Rabbit) {
                Rabbit rabbit = (Rabbit)entity;
                rabbit.f_21345_.m_25352_(3, (Goal)new TemptGoal((PathfinderMob)rabbit, 1.0, Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)AMItemRegistry.MUNGAL_SPORES.get()}), false));
            } else if (AMConfig.dolphinsAttackFlyingFish && entity instanceof Dolphin) {
                Dolphin dolphin = (Dolphin)entity;
                dolphin.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)dolphin, EntityFlyingFish.class, 70, true, true, null));
            }
        }
        catch (Exception e) {
            AlexsMobs.LOGGER.warn("Tried to add unique behaviors to vanilla mobs and encountered an error");
        }
    }

    @SubscribeEvent
    public void onPlayerAttackEntityEvent(AttackEntityEvent event) {
        Entity entity = event.getTarget();
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            if (event.getEntity().m_6844_(EquipmentSlot.HEAD).m_41720_() == AMItemRegistry.MOOSE_HEADGEAR.get()) {
                living.m_147240_(1.0, (double)Mth.m_14031_((float)(event.getEntity().m_146908_() * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(event.getEntity().m_146908_() * ((float)Math.PI / 180)))));
            }
            if (event.getEntity().m_21023_((MobEffect)AMEffectRegistry.TIGERS_BLESSING.get()) && !event.getTarget().m_7307_((Entity)event.getEntity()) && !(event.getTarget() instanceof EntityTiger)) {
                AABB bb = new AABB(event.getEntity().m_20185_() - 32.0, event.getEntity().m_20186_() - 32.0, event.getEntity().m_20189_() - 32.0, event.getEntity().m_20189_() + 32.0, event.getEntity().m_20186_() + 32.0, event.getEntity().m_20189_() + 32.0);
                List tigers = event.getEntity().m_9236_().m_6443_(EntityTiger.class, bb, EntitySelector.f_20402_);
                for (EntityTiger tiger : tigers) {
                    if (tiger.m_6162_()) continue;
                    tiger.m_6710_(living);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event) {
        Entity entity = event.getSource().m_7639_();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity;
            LivingEntity attacker = (LivingEntity)entity;
            if (event.getAmount() > 0.0f && attacker.m_21023_((MobEffect)AMEffectRegistry.SOULSTEAL.get()) && attacker.m_21124_((MobEffect)AMEffectRegistry.SOULSTEAL.get()) != null) {
                int level = attacker.m_21124_((MobEffect)AMEffectRegistry.SOULSTEAL.get()).m_19564_() + 1;
                if (attacker.m_21223_() < attacker.m_21233_() && ThreadLocalRandom.current().nextFloat() < 0.25f + (float)level * 0.25f) {
                    attacker.m_5634_(Math.min(event.getAmount() / 2.0f * (float)level, (float)(2 + 2 * level)));
                }
            }
            if ((livingEntity = event.getEntity()) instanceof Player) {
                EntityMimicOctopus octupus;
                Player player = (Player)livingEntity;
                if (attacker instanceof EntityMimicOctopus && (octupus = (EntityMimicOctopus)attacker).m_21830_((LivingEntity)player)) {
                    event.setCanceled(true);
                    return;
                }
                if (player.m_6844_(EquipmentSlot.HEAD).m_41720_() == AMItemRegistry.SPIKED_TURTLE_SHELL.get() && attacker.m_20270_((Entity)player) < attacker.m_20205_() + player.m_20205_() + 0.5f) {
                    attacker.m_6469_(attacker.m_269291_().m_269374_((Entity)player), 1.0f);
                    attacker.m_147240_(0.5, (double)Mth.m_14031_((float)((attacker.m_146908_() + 180.0f) * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)((attacker.m_146908_() + 180.0f) * ((float)Math.PI / 180)))));
                }
            }
        }
        if (!event.getEntity().m_6844_(EquipmentSlot.LEGS).m_41619_() && event.getEntity().m_6844_(EquipmentSlot.LEGS).m_41720_() == AMItemRegistry.EMU_LEGGINGS.get() && event.getSource().m_269533_(DamageTypeTags.f_268524_) && (double)event.getEntity().m_217043_().m_188501_() < AMConfig.emuPantsDodgeChance) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onLivingSetTargetEvent(LivingChangeTargetEvent event) {
        LivingEntity livingEntity;
        if (event.getNewTarget() != null && (livingEntity = event.getEntity()) instanceof Mob) {
            Mob mob = (Mob)livingEntity;
            if (mob.m_6336_() == MobType.f_21642_ && event.getNewTarget().m_21023_((MobEffect)AMEffectRegistry.BUG_PHEROMONES.get()) && event.getEntity().m_21188_() != event.getNewTarget()) {
                event.setCanceled(true);
                return;
            }
            if (mob.m_6336_() == MobType.f_21641_ && !mob.m_6095_().m_204039_(AMTagRegistry.IGNORES_KIMONO) && event.getNewTarget().m_6844_(EquipmentSlot.CHEST).m_150930_((Item)AMItemRegistry.UNSETTLING_KIMONO.get()) && event.getEntity().m_21188_() != event.getNewTarget()) {
                event.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingTickEvent event) {
        RandomSource random;
        ItemStack boots;
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if ((double)player.m_20192_() < (double)player.m_20206_() * 0.5) {
                player.m_6210_();
            }
            if (entity.m_21204_().m_22171_(Attributes.f_22279_)) {
                AttributeInstance attributes = entity.m_21051_(Attributes.f_22279_);
                if (player.m_6844_(EquipmentSlot.FEET).m_41720_() == AMItemRegistry.ROADDRUNNER_BOOTS.get() || attributes.m_22109_(SAND_SPEED_BONUS)) {
                    boolean sand = player.m_9236_().m_8055_(this.getDownPos(player.m_20183_(), (LevelAccessor)player.m_9236_())).m_204336_(BlockTags.f_13029_);
                    if (sand && !attributes.m_22109_(SAND_SPEED_BONUS)) {
                        attributes.m_22125_(SAND_SPEED_BONUS);
                    }
                    if (player.f_19797_ % 25 == 0 && (player.m_6844_(EquipmentSlot.FEET).m_41720_() != AMItemRegistry.ROADDRUNNER_BOOTS.get() || !sand) && attributes.m_22109_(SAND_SPEED_BONUS)) {
                        attributes.m_22130_(SAND_SPEED_BONUS);
                    }
                }
                if (player.m_6844_(EquipmentSlot.HEAD).m_41720_() == AMItemRegistry.FRONTIER_CAP.get() || attributes.m_22109_(SNEAK_SPEED_BONUS)) {
                    boolean shift = player.m_6144_();
                    if (shift && !attributes.m_22109_(SNEAK_SPEED_BONUS)) {
                        attributes.m_22125_(SNEAK_SPEED_BONUS);
                    }
                    if ((!shift || player.m_6844_(EquipmentSlot.HEAD).m_41720_() != AMItemRegistry.FRONTIER_CAP.get()) && attributes.m_22109_(SNEAK_SPEED_BONUS)) {
                        attributes.m_22130_(SNEAK_SPEED_BONUS);
                    }
                }
            }
            if (player.m_6844_(EquipmentSlot.HEAD).m_41720_() == AMItemRegistry.SPIKED_TURTLE_SHELL.get() && !player.m_204029_(FluidTags.f_13131_)) {
                player.m_7292_(new MobEffectInstance(MobEffects.f_19608_, 310, 0, false, false, true));
            }
        }
        if (!(boots = entity.m_6844_(EquipmentSlot.FEET)).m_41619_() && boots.m_41782_() && boots.m_41784_().m_128441_("BisonFur") && boots.m_41784_().m_128471_("BisonFur")) {
            BlockPos posBelow = new BlockPos((int)event.getEntity().m_20185_(), (int)(entity.m_20191_().f_82289_ - (double)0.1f), (int)entity.m_20189_());
            if (entity.m_9236_().m_8055_(posBelow).m_60713_(Blocks.f_152499_)) {
                entity.m_6853_(true);
                entity.m_146917_(0);
                entity.m_6034_(entity.m_20185_(), Math.max(entity.m_20186_(), (double)((float)posBelow.m_123342_() + 1.0f)), entity.m_20189_());
            }
            if (entity.f_146808_) {
                entity.m_6853_(true);
                entity.m_20256_(entity.m_20184_().m_82520_(0.0, (double)0.1f, 0.0));
            }
        }
        if (entity.m_6844_(EquipmentSlot.LEGS).m_41720_() == AMItemRegistry.CENTIPEDE_LEGGINGS.get() && entity.f_19862_ && !entity.m_20069_()) {
            entity.f_19789_ = 0.0f;
            Vec3 motion = entity.m_20184_();
            double d2 = 0.1;
            if (entity.m_6144_() || !entity.m_146900_().isScaffolding(entity) && entity.m_5791_()) {
                d2 = 0.0;
            }
            motion = new Vec3(Mth.m_14008_((double)motion.f_82479_, (double)-0.15f, (double)0.15f), d2, Mth.m_14008_((double)motion.f_82481_, (double)-0.15f, (double)0.15f));
            entity.m_20256_(motion);
        }
        if (entity.m_6844_(EquipmentSlot.HEAD).m_41720_() == AMItemRegistry.SOMBRERO.get() && !entity.m_9236_().f_46443_ && AlexsMobs.isAprilFools() && entity.m_20072_() && (random = entity.m_217043_()).m_188503_(245) == 0 && !EntitySeaBear.isMobSafe((Entity)entity)) {
            int dist = 32;
            List nearbySeabears = entity.m_9236_().m_45976_(EntitySeaBear.class, entity.m_20191_().m_82377_(32.0, 32.0, 32.0));
            if (nearbySeabears.isEmpty()) {
                EntitySeaBear bear = (EntitySeaBear)((EntityType)AMEntityRegistry.SEA_BEAR.get()).m_20615_(entity.m_9236_());
                BlockPos at = entity.m_20183_();
                BlockPos farOff = null;
                for (int i = 0; i < 15; ++i) {
                    int f1 = (int)Math.signum((float)random.m_188502_() - 0.5f);
                    int f2 = (int)Math.signum((float)random.m_188502_() - 0.5f);
                    BlockPos pos1 = at.m_7918_(f1 * (10 + random.m_188503_(22)), random.m_188503_(1), f2 * (10 + random.m_188503_(22)));
                    if (!entity.m_9236_().m_46801_(pos1)) continue;
                    farOff = pos1;
                }
                if (farOff != null) {
                    bear.m_6034_((float)farOff.m_123341_() + 0.5f, (float)farOff.m_123342_() + 0.5f, (float)farOff.m_123343_() + 0.5f);
                    bear.m_146922_(random.m_188501_() * 360.0f);
                    bear.m_6710_(entity);
                    entity.m_9236_().m_7967_((Entity)bear);
                }
            } else {
                for (EntitySeaBear bear : nearbySeabears) {
                    bear.m_6710_(entity);
                }
            }
        }
        if (VineLassoUtil.hasLassoData(entity)) {
            VineLassoUtil.tickLasso(entity);
        }
        if (RockyChestplateUtil.isWearing(entity)) {
            RockyChestplateUtil.tickRockyRolling(entity);
        }
        if (FlyingFishBootsUtil.isWearing(entity)) {
            FlyingFishBootsUtil.tickFlyingFishBoots(entity);
        }
    }

    private BlockPos getDownPos(BlockPos entered, LevelAccessor world) {
        for (int i = 0; world.m_46859_(entered) && i < 3; ++i) {
            entered = entered.m_7495_();
        }
        return entered;
    }

    @SubscribeEvent
    public void onFOVUpdate(ComputeFovModifierEvent event) {
        if (event.getPlayer().m_21023_((MobEffect)AMEffectRegistry.FEAR.get()) || event.getPlayer().m_21023_((MobEffect)AMEffectRegistry.POWER_DOWN.get())) {
            event.setNewFovModifier(1.0f);
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        Entity entity;
        if (!event.getEntity().m_21211_().m_41619_() && event.getSource() != null && event.getSource().m_7639_() != null && event.getEntity().m_21211_().m_41720_() == AMItemRegistry.SHIELD_OF_THE_DEEP.get() && (entity = event.getSource().m_7639_()) instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            boolean flag = false;
            if (living.m_20270_((Entity)event.getEntity()) <= 4.0f && !living.m_21023_((MobEffect)AMEffectRegistry.EXSANGUINATION.get())) {
                living.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.EXSANGUINATION.get(), 60, 2));
                flag = true;
            }
            if (event.getEntity().m_20072_()) {
                event.getEntity().m_20301_(Math.min(event.getEntity().m_6062_(), event.getEntity().m_20146_() + 150));
                flag = true;
            }
            if (flag) {
                event.getEntity().m_21211_().m_41622_(1, event.getEntity(), player -> player.m_21190_(event.getEntity().m_7655_()));
            }
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        CompoundTag tag = event.getItemStack().m_41783_();
        if (tag != null && tag.m_128441_("BisonFur") && tag.m_128471_("BisonFur")) {
            event.getToolTip().add(Component.m_237115_((String)"item.alexsmobs.insulated_with_fur").m_130940_(ChatFormatting.AQUA));
        }
    }

    @SubscribeEvent
    public void onAddReloadListener(AddReloadListenerEvent event) {
        AlexsMobs.LOGGER.info("Adding datapack listener capsid_recipes");
        event.addListener((PreparableReloadListener)AlexsMobs.PROXY.getCapsidRecipeManager());
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (event.getEntity() != null && event.getEntity().m_21055_((Item)AMItemRegistry.GHOSTLY_PICKAXE.get()) && ItemGhostlyPickaxe.shouldStoreInGhost((LivingEntity)event.getEntity(), event.getEntity().m_21205_())) {
            event.setCanHarvest(false);
        }
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.HolderGetter
 *  net.minecraft.core.QuartPos
 *  net.minecraft.core.Registry
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Shearable
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.FollowParentGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.Biomes
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.BonemealableBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.chunk.PalettedContainer
 *  net.minecraft.world.level.chunk.PalettedContainerRO
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.IForgeShearable
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import com.github.alexthe666.alexsmobs.entity.ai.MungusAIAlertBunfungus;
import com.github.alexthe666.alexsmobs.entity.ai.MungusAITemptMushroom;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.message.MessageMungusBiomeChange;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityMungus
extends Animal
implements ITargetsDroppedItems,
Shearable,
IForgeShearable {
    protected static final EntityDataAccessor<Optional<BlockPos>> TARGETED_BLOCK_POS = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    private static final EntityDataAccessor<Boolean> ALT_ORDER_MUSHROOMS = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> REVERTING = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Integer> MUSHROOM_COUNT = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> SACK_SWELL = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> EXPLOSION_DISABLED = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Optional<BlockState>> MUSHROOM_STATE = SynchedEntityData.m_135353_(EntityMungus.class, (EntityDataSerializer)EntityDataSerializers.f_268618_);
    private static final int WIDTH_BITS = Mth.m_14163_((int)16) - 2;
    public static final int MAX_SIZE = 1 << WIDTH_BITS + WIDTH_BITS + DimensionType.f_156649_ - 2;
    private static final int HORIZONTAL_MASK = (1 << WIDTH_BITS) - 1;
    private static final HashMap<String, String> MUSHROOM_TO_BIOME = new HashMap();
    private static final HashMap<String, String> MUSHROOM_TO_BLOCK = new HashMap();
    private static boolean initBiomeData = false;
    public float prevSwellProgress = 0.0f;
    public float swellProgress = 0.0f;
    private int beamCounter = 0;
    private int mosquitoAttackCooldown = 0;
    private boolean hasExploded;
    public int timeUntilNextEgg = this.f_19796_.m_188503_(24000) + 24000;

    protected EntityMungus(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        EntityMungus.initBiomeData();
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 15.0).m_22268_(Attributes.f_22279_, 0.25);
    }

    public static boolean canMungusSpawn(EntityType type, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        return worldIn.m_8055_(pos.m_7495_()).m_60815_();
    }

    public static BlockState getMushroomBlockstate(Item item) {
        ResourceLocation name;
        if (item instanceof BlockItem && (name = ForgeRegistries.ITEMS.getKey((Object)item)) != null && MUSHROOM_TO_BIOME.containsKey(name.toString())) {
            return ((BlockItem)item).m_40614_().m_49966_();
        }
        return null;
    }

    private static void initBiomeData() {
        if (!initBiomeData || MUSHROOM_TO_BIOME.isEmpty()) {
            initBiomeData = true;
            for (String string : AMConfig.mungusBiomeMatches) {
                String[] split = string.split("\\|");
                if (split.length < 2) continue;
                MUSHROOM_TO_BIOME.put(split[0], split[1]);
                MUSHROOM_TO_BLOCK.put(split[0], split[2]);
            }
        }
    }

    protected SoundEvent m_7515_() {
        return (SoundEvent)AMSoundRegistry.MUNGUS_IDLE.get();
    }

    protected SoundEvent m_7975_(DamageSource damageSourceIn) {
        return (SoundEvent)AMSoundRegistry.MUNGUS_HURT.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)AMSoundRegistry.MUNGUS_HURT.get();
    }

    public boolean m_5545_(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return AMEntityRegistry.rollSpawn(AMConfig.mungusSpawnRolls, this.m_217043_(), spawnReasonIn);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new BreedGoal((Animal)this, 1.0));
        this.f_21345_.m_25352_(1, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(2, (Goal)new PanicGoal((PathfinderMob)this, 1.25));
        this.f_21345_.m_25352_(3, (Goal)new MungusAITemptMushroom(this, 1.0));
        this.f_21345_.m_25352_(5, (Goal)new AITargetMushrooms());
        this.f_21345_.m_25352_(6, (Goal)new FollowParentGoal((Animal)this, 1.1));
        this.f_21345_.m_25352_(7, (Goal)new AnimalAIWanderRanged((PathfinderMob)this, 60, 1.0, 14, 7));
        this.f_21345_.m_25352_(8, (Goal)new LookAtPlayerGoal((Mob)this, LivingEntity.class, 15.0f));
        this.f_21345_.m_25352_(8, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, new CreatureAITargetItems((PathfinderMob)this, false, 10));
        this.f_21346_.m_25352_(2, (Goal)new MungusAIAlertBunfungus((PathfinderMob)this, EntityBunfungus.class));
    }

    public void m_8119_() {
        super.m_8119_();
        if (!this.m_9236_().f_46443_ && this.m_6084_() && !this.m_6162_() && --this.timeUntilNextEgg <= 0) {
            ItemEntity dropped = this.m_19998_((ItemLike)AMItemRegistry.MUNGAL_SPORES.get());
            dropped.m_32060_();
            this.timeUntilNextEgg = this.f_19796_.m_188503_(24000) + 24000;
        }
    }

    public void m_6075_() {
        super.m_6075_();
        this.prevSwellProgress = this.swellProgress;
        if (this.isReverting() && AMConfig.mungusBiomeTransformationType == 2) {
            this.swellProgress += 0.5f;
            if (this.swellProgress >= 10.0f) {
                try {
                    this.explode();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                this.swellProgress = 0.0f;
                this.f_19804_.m_135381_(REVERTING, (Object)false);
            }
        } else if (this.m_6084_() && this.swellProgress > 0.0f) {
            this.swellProgress -= 1.0f;
        }
        if (((Boolean)this.f_19804_.m_135370_(EXPLOSION_DISABLED)).booleanValue()) {
            if (this.mosquitoAttackCooldown < 0) {
                ++this.mosquitoAttackCooldown;
            }
            if (this.mosquitoAttackCooldown > 200) {
                this.mosquitoAttackCooldown = 0;
                this.f_19804_.m_135381_(EXPLOSION_DISABLED, (Object)false);
            }
        }
    }

    protected void m_6153_() {
        super.m_6153_();
        if (this.getMushroomCount() >= 5 && AMConfig.mungusBiomeTransformationType > 0 && !this.m_6162_() && !((Boolean)this.f_19804_.m_135370_(EXPLOSION_DISABLED)).booleanValue()) {
            this.swellProgress += 1.0f;
            if (this.f_20919_ == 19 && !this.hasExploded) {
                this.hasExploded = true;
                try {
                    this.explode();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void explode() {
        for (int i = 0; i < 5; ++i) {
            float r1 = 6.0f * (this.f_19796_.m_188501_() - 0.5f);
            float r2 = 2.0f * (this.f_19796_.m_188501_() - 0.5f);
            float r3 = 6.0f * (this.f_19796_.m_188501_() - 0.5f);
            this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123813_, this.m_20185_() + (double)r1, this.m_20186_() + 0.5 + (double)r2, this.m_20189_() + (double)r3, (double)(r1 * 4.0f), (double)(r2 * 4.0f), (double)(r3 * 4.0f));
        }
        if (!this.m_9236_().f_46443_) {
            ServerLevel serverLevel = (ServerLevel)this.m_9236_();
            int radius = 3;
            int j = 3 + this.m_9236_().f_46441_.m_188503_(1);
            int k = 3 + this.m_9236_().f_46441_.m_188503_(1);
            int l = 3 + this.m_9236_().f_46441_.m_188503_(1);
            float f = (float)(j + k + l) * 0.333f + 0.5f;
            float ff = f * f;
            double ffDouble = ff;
            BlockPos center = this.m_20183_();
            BlockState transformState = Blocks.f_50195_.m_49966_();
            Registry registry = serverLevel.m_7654_().m_206579_().m_175515_(Registries.f_256952_);
            Holder<Biome> biome = (Holder<Biome>)registry.m_203636_(Biomes.f_48215_).get();
            TagKey<Block> transformMatches = AMTagRegistry.MUNGUS_REPLACE_MUSHROOM;
            if (this.getMushroomState() != null) {
                Holder<Biome> gottenFrom;
                Block block;
                String mushroomKey = ForgeRegistries.BLOCKS.getKey((Object)this.getMushroomState().m_60734_()).toString();
                if (MUSHROOM_TO_BLOCK.containsKey(mushroomKey) && (block = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation(MUSHROOM_TO_BLOCK.get(mushroomKey)))) != null) {
                    transformState = block.m_49966_();
                    if (block == Blocks.f_50690_) {
                        transformMatches = AMTagRegistry.MUNGUS_REPLACE_NETHER;
                    }
                    if (block == Blocks.f_50699_) {
                        transformMatches = AMTagRegistry.MUNGUS_REPLACE_NETHER;
                    }
                }
                if ((gottenFrom = this.getBiomeKeyFromShroom()) != null) {
                    biome = gottenFrom;
                }
            }
            BlockState finalTransformState = transformState;
            TagKey<Block> finalTransformReplace = transformMatches;
            if (AMConfig.mungusBiomeTransformationType == 2 && !this.m_9236_().f_46443_) {
                this.transformBiome(center, biome);
            }
            this.m_146850_(GameEvent.f_157812_);
            this.m_5496_(SoundEvents.f_11913_, this.m_6121_(), this.m_6100_());
            if (!this.isReverting()) {
                BlockPos.m_121990_((BlockPos)center.m_7918_(-j, -k, -l), (BlockPos)center.m_7918_(j, k, l)).forEach(blockpos -> {
                    if (blockpos.m_123331_((Vec3i)center) <= ffDouble && this.m_9236_().f_46441_.m_188501_() > (float)blockpos.m_123331_((Vec3i)center) / ff) {
                        if (this.m_9236_().m_8055_(blockpos).m_204336_(finalTransformReplace) && !this.m_9236_().m_8055_(blockpos.m_7494_()).m_60815_()) {
                            this.m_9236_().m_46597_(blockpos, finalTransformState);
                        }
                        if (this.m_9236_().f_46441_.m_188503_(4) == 0 && this.m_9236_().m_8055_(blockpos).m_280296_() && this.m_9236_().m_6425_(blockpos.m_7494_()).m_76178_() && !this.m_9236_().m_8055_(blockpos.m_7494_()).m_60815_()) {
                            this.m_9236_().m_46597_(blockpos.m_7494_(), this.getMushroomState());
                        }
                    }
                });
            }
        }
    }

    public void disableExplosion() {
        this.f_19804_.m_135381_(EXPLOSION_DISABLED, (Object)true);
    }

    private Holder<Biome> getBiomeKeyFromShroom() {
        Registry registry = this.m_9236_().m_9598_().m_175515_(Registries.f_256952_);
        BlockState state = this.getMushroomState();
        if (state == null) {
            return null;
        }
        ResourceLocation blockRegName = ForgeRegistries.BLOCKS.getKey((Object)state.m_60734_());
        if (blockRegName != null && MUSHROOM_TO_BIOME.containsKey(blockRegName.toString())) {
            String str = MUSHROOM_TO_BIOME.get(blockRegName.toString());
            Biome biome = registry.m_6612_(new ResourceLocation(str)).orElse(null);
            ResourceKey resourceKey = registry.m_7854_((Object)biome).orElse(null);
            return registry.m_203636_(resourceKey).orElse(null);
        }
        return null;
    }

    private PalettedContainerRO<Holder<Biome>> getChunkBiomes(LevelChunk chunk) {
        int i = QuartPos.m_175400_((int)chunk.m_141937_());
        int k = i + QuartPos.m_175400_((int)chunk.m_141928_()) - 1;
        int l = Mth.m_14045_((int)QuartPos.m_175400_((int)((int)this.m_20186_())), (int)i, (int)k);
        int j = chunk.m_151564_(QuartPos.m_175402_((int)l));
        LevelChunkSection section = chunk.m_183278_(j);
        return section == null ? null : section.m_187996_();
    }

    private void setChunkBiomes(LevelChunk chunk, PalettedContainer<Holder<Biome>> container) {
        int i = QuartPos.m_175400_((int)chunk.m_141937_());
        int k = i + QuartPos.m_175400_((int)chunk.m_141928_()) - 1;
        int l = Mth.m_14045_((int)QuartPos.m_175400_((int)((int)this.m_20186_())), (int)i, (int)k);
        int j = chunk.m_151564_(QuartPos.m_175402_((int)l));
        LevelChunkSection section = chunk.m_183278_(j);
        if (section != null) {
            section.f_187995_ = container;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void transformBiome(BlockPos pos, Holder<Biome> biome) {
        LevelChunk chunk = this.m_9236_().m_46745_(pos);
        PalettedContainer container = this.getChunkBiomes(chunk).m_238334_();
        if (((Boolean)this.f_19804_.m_135370_(REVERTING)).booleanValue()) {
            int lvt_4_1_ = chunk.m_7697_().m_45604_() >> 2;
            int yChunk = (int)this.m_20186_() >> 2;
            int lvt_5_1_ = chunk.m_7697_().m_45605_() >> 2;
            ChunkGenerator chunkgenerator = ((ServerLevel)this.m_9236_()).m_7726_().m_8481_();
            for (int k = 0; k < 4; ++k) {
                for (int l = 0; l < 4; ++l) {
                    for (int i1 = 0; i1 < 4; ++i1) {
                        container.m_63127_(k, l, i1, (Object)((ServerLevel)this.m_9236_()).m_203675_(lvt_4_1_ + k, yChunk + l, lvt_5_1_ + i1));
                    }
                }
            }
            this.setChunkBiomes(chunk, (PalettedContainer<Holder<Biome>>)container);
            if (this.m_9236_().f_46443_) return;
        }
        if (biome == null) {
            return;
        }
        if (container == null || this.m_9236_().f_46443_) return;
        for (int biomeX = 0; biomeX < 4; ++biomeX) {
            for (int biomeY = 0; biomeY < 4; ++biomeY) {
                for (int biomeZ = 0; biomeZ < 4; ++biomeZ) {
                    container.m_63127_(biomeX, biomeY, biomeZ, biome);
                }
            }
        }
        this.setChunkBiomes(chunk, (PalettedContainer<Holder<Biome>>)container);
        ResourceLocation biomeKey = ForgeRegistries.BIOMES.getKey((Object)((Biome)biome.m_203334_()));
        if (biomeKey == null) return;
        AlexsMobs.sendMSGToAll(new MessageMungusBiomeChange(this.m_19879_(), pos.m_123341_(), pos.m_123343_(), biomeKey.toString()));
    }

    public boolean shouldFollowMushroom(ItemStack stack) {
        BlockState state = EntityMungus.getMushroomBlockstate(stack.m_41720_());
        if (state != null && !state.m_60795_()) {
            if (this.getMushroomCount() == 0) {
                return true;
            }
            return this.getMushroomState().m_60734_() == state.m_60734_();
        }
        return false;
    }

    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        InteractionResult type = super.m_6071_(player, hand);
        if (itemstack.m_41720_() == Items.f_42675_ && !this.m_6162_()) {
            this.f_19804_.m_135381_(REVERTING, (Object)true);
            this.m_142075_(player, hand, itemstack);
            return InteractionResult.SUCCESS;
        }
        if (this.shouldFollowMushroom(itemstack) && this.getMushroomCount() < 5) {
            this.f_19804_.m_135381_(REVERTING, (Object)false);
            BlockState state = EntityMungus.getMushroomBlockstate(itemstack.m_41720_());
            this.m_146850_(GameEvent.f_157797_);
            this.m_5496_(SoundEvents.f_12132_, this.m_6121_(), this.m_6100_());
            if (this.getMushroomState() != null && state != null && state.m_60734_() != this.getMushroomState().m_60734_()) {
                this.setMushroomCount(0);
            }
            this.setMushroomState(state);
            this.m_142075_(player, hand, itemstack);
            this.setMushroomCount(this.getMushroomCount() + 1);
            return InteractionResult.SUCCESS;
        }
        return type;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        boolean prev = super.m_6469_(source, amount);
        if (prev) {
            this.setBeamTarget(null);
            this.beamCounter = Math.min(this.beamCounter, -1200);
        }
        return prev;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(MUSHROOM_STATE, Optional.empty());
        this.m_20088_().m_135372_(TARGETED_BLOCK_POS, Optional.empty());
        this.f_19804_.m_135372_(ALT_ORDER_MUSHROOMS, (Object)false);
        this.f_19804_.m_135372_(REVERTING, (Object)false);
        this.f_19804_.m_135372_(EXPLOSION_DISABLED, (Object)false);
        this.f_19804_.m_135372_(MUSHROOM_COUNT, (Object)0);
        this.f_19804_.m_135372_(SACK_SWELL, (Object)0);
    }

    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        BlockState blockstate = this.getMushroomState();
        if (blockstate != null) {
            compound.m_128365_("MushroomState", (Tag)NbtUtils.m_129202_((BlockState)blockstate));
        }
        compound.m_128405_("MushroomCount", this.getMushroomCount());
        compound.m_128405_("Sack", this.getSackSwell());
        compound.m_128405_("BeamCounter", this.beamCounter);
        compound.m_128379_("AltMush", ((Boolean)this.f_19804_.m_135370_(ALT_ORDER_MUSHROOMS)).booleanValue());
        if (this.getBeamTarget() != null) {
            compound.m_128365_("BeamTarget", (Tag)NbtUtils.m_129224_((BlockPos)this.getBeamTarget()));
        }
        compound.m_128405_("EggTime", this.timeUntilNextEgg);
    }

    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        BlockState blockstate = null;
        if (compound.m_128425_("MushroomState", 10) && (blockstate = NbtUtils.m_247651_((HolderGetter)this.m_9236_().m_246945_(Registries.f_256747_), (CompoundTag)compound.m_128469_("MushroomState"))).m_60795_()) {
            blockstate = null;
        }
        if (compound.m_128425_("BeamTarget", 10)) {
            this.setBeamTarget(NbtUtils.m_129239_((CompoundTag)compound.m_128469_("BeamTarget")));
        }
        this.setMushroomState(blockstate);
        this.setMushroomCount(compound.m_128451_("MushroomCount"));
        this.setSackSwell(compound.m_128451_("Sack"));
        this.beamCounter = compound.m_128451_("BeamCounter");
        this.f_19804_.m_135381_(ALT_ORDER_MUSHROOMS, (Object)compound.m_128471_("AltMush"));
        if (compound.m_128441_("EggTime")) {
            this.timeUntilNextEgg = compound.m_128451_("EggTime");
        }
    }

    public void m_8107_() {
        super.m_8107_();
        if (this.getBeamTarget() != null) {
            BlockPos t = this.getBeamTarget();
            if (this.isMushroomTarget(t) && this.hasLineOfSightMushroom(t)) {
                BlockState state;
                this.m_21563_().m_24950_((double)((float)t.m_123341_() + 0.5f), (double)((float)t.m_123342_() + 0.15f), (double)((float)t.m_123343_() + 0.5f), 90.0f, 90.0f);
                this.m_21563_().m_8128_();
                double d5 = 1.0;
                double eyeHeight = this.m_20186_() + 1.0;
                if (this.beamCounter % 20 == 0) {
                    this.m_5496_((SoundEvent)AMSoundRegistry.MUNGUS_LASER_LOOP.get(), this.m_6100_(), this.m_6121_());
                }
                ++this.beamCounter;
                double d0 = (double)((float)t.m_123341_() + 0.5f) - this.m_20185_();
                double d1 = (double)((float)t.m_123342_() + 0.5f) - eyeHeight;
                double d2 = (double)((float)t.m_123343_() + 0.5f) - this.m_20189_();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d0 /= d3;
                d1 /= d3;
                d2 /= d3;
                for (double d4 = this.f_19796_.m_188500_(); d4 < d3 - 0.5; d4 += 1.0 - d5 + this.f_19796_.m_188500_()) {
                    if (!(this.f_19796_.m_188501_() < 0.1f)) continue;
                    float r1 = 0.3f * (this.f_19796_.m_188501_() - 0.5f);
                    float r2 = 0.3f * (this.f_19796_.m_188501_() - 0.5f);
                    float r3 = 0.3f * (this.f_19796_.m_188501_() - 0.5f);
                    this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123757_, this.m_20185_() + d0 * d4 + (double)r1, eyeHeight + d1 * d4 + (double)r2, this.m_20189_() + d2 * d4 + (double)r3, (double)(r1 * 4.0f), (double)(r2 * 4.0f), (double)(r3 * 4.0f));
                }
                if (this.beamCounter > 200 && (state = this.m_9236_().m_8055_(t)).m_60734_() instanceof BonemealableBlock) {
                    BonemealableBlock igrowable = (BonemealableBlock)state.m_60734_();
                    boolean flag = false;
                    if (igrowable.m_7370_((LevelReader)this.m_9236_(), t, state, this.m_9236_().f_46443_)) {
                        for (int i = 0; i < 5; ++i) {
                            float r1 = 3.0f * (this.f_19796_.m_188501_() - 0.5f);
                            float r2 = 2.0f * (this.f_19796_.m_188501_() - 0.5f);
                            float r3 = 3.0f * (this.f_19796_.m_188501_() - 0.5f);
                            this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123813_, (double)((float)t.m_123341_() + 0.5f + r1), (double)((float)t.m_123342_() + 0.5f + r2), (double)((float)t.m_123343_() + 0.5f + r3), (double)(r1 * 4.0f), (double)(r2 * 4.0f), (double)(r3 * 4.0f));
                        }
                        if (!this.m_9236_().f_46443_) {
                            this.m_9236_().m_46796_(2005, t, 0);
                            igrowable.m_214148_((ServerLevel)this.m_9236_(), this.m_9236_().f_46441_, t, state);
                            boolean bl = flag = this.m_9236_().m_8055_(t).m_60734_() != state.m_60734_();
                        }
                    }
                    if (!flag) {
                        int grown = 0;
                        int maxGrow = 2 + this.f_19796_.m_188503_(3);
                        for (int i = 0; i < 15; ++i) {
                            BlockPos pos = t.m_7918_(this.f_19796_.m_188503_(10) - 5, this.f_19796_.m_188503_(4) - 2, this.f_19796_.m_188503_(10) - 5);
                            if (grown >= maxGrow || !this.m_9236_().m_8055_(pos).m_60795_() || !this.m_9236_().m_8055_(pos.m_7495_()).m_60815_()) continue;
                            this.m_9236_().m_46597_(pos, state);
                            ++grown;
                        }
                    }
                    this.m_5496_((SoundEvent)AMSoundRegistry.MUNGUS_LASER_END.get(), this.m_6100_(), this.m_6121_());
                    if (flag) {
                        this.m_5496_((SoundEvent)AMSoundRegistry.MUNGUS_LASER_GROW.get(), this.m_6100_(), this.m_6121_());
                    }
                    this.setBeamTarget(null);
                    this.beamCounter = -1200;
                    if (this.getMushroomCount() > 0) {
                        this.setMushroomCount(this.getMushroomCount() - 1);
                    }
                }
            } else {
                this.setBeamTarget(null);
            }
        }
        if (this.beamCounter < 0) {
            ++this.beamCounter;
        }
    }

    public boolean m_6898_(ItemStack stack) {
        return stack.m_204117_(AMTagRegistry.MUNGUS_BREEDABLES);
    }

    @Nullable
    public SpawnGroupData m_6518_(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.f_19804_.m_135381_(ALT_ORDER_MUSHROOMS, (Object)this.f_19796_.m_188499_());
        this.setMushroomCount(this.f_19796_.m_188503_(2));
        this.setMushroomState(this.f_19796_.m_188499_() ? Blocks.f_50072_.m_49966_() : Blocks.f_50073_.m_49966_());
        return super.m_6518_(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public int getMushroomCount() {
        return (Integer)this.f_19804_.m_135370_(MUSHROOM_COUNT);
    }

    public void setMushroomCount(int command) {
        this.f_19804_.m_135381_(MUSHROOM_COUNT, (Object)command);
    }

    public int getSackSwell() {
        return (Integer)this.f_19804_.m_135370_(SACK_SWELL);
    }

    public void setSackSwell(int command) {
        this.f_19804_.m_135381_(SACK_SWELL, (Object)command);
    }

    @Nullable
    public BlockPos getBeamTarget() {
        return ((Optional)this.m_20088_().m_135370_(TARGETED_BLOCK_POS)).orElse(null);
    }

    public void setBeamTarget(@Nullable BlockPos beamTarget) {
        this.m_20088_().m_135381_(TARGETED_BLOCK_POS, Optional.ofNullable(beamTarget));
    }

    public boolean isAltOrderMushroom() {
        return (Boolean)this.f_19804_.m_135370_(ALT_ORDER_MUSHROOMS);
    }

    @Nullable
    public BlockState getMushroomState() {
        return ((Optional)this.f_19804_.m_135370_(MUSHROOM_STATE)).orElse(null);
    }

    public void setMushroomState(@Nullable BlockState state) {
        this.f_19804_.m_135381_(MUSHROOM_STATE, Optional.ofNullable(state));
    }

    @Nullable
    public AgeableMob m_142606_(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return (AgeableMob)((EntityType)AMEntityRegistry.MUNGUS.get()).m_20615_((Level)p_241840_1_);
    }

    public boolean isMushroomTarget(BlockPos pos) {
        if (this.getMushroomState() != null) {
            return this.m_9236_().m_8055_(pos).m_60734_() == this.getMushroomState().m_60734_();
        }
        return false;
    }

    @Override
    public boolean canTargetItem(ItemStack stack) {
        return this.shouldFollowMushroom(stack) && this.getMushroomCount() < 5;
    }

    @Override
    public void onGetItem(ItemEntity e) {
        if (this.shouldFollowMushroom(e.m_32055_())) {
            BlockState state = EntityMungus.getMushroomBlockstate(e.m_32055_().m_41720_());
            if (this.getMushroomState() != null && state != null && state.m_60734_() != this.getMushroomState().m_60734_()) {
                this.setMushroomCount(0);
            }
            this.m_146850_(GameEvent.f_157797_);
            this.m_5496_(SoundEvents.f_12132_, this.m_6121_(), this.m_6100_());
            this.setMushroomState(state);
            this.setMushroomCount(this.getMushroomCount() + 1);
        }
    }

    private boolean hasLineOfSightMushroom(BlockPos destinationBlock) {
        Vec3 Vector3d = new Vec3(this.m_20185_(), this.m_20188_(), this.m_20189_());
        Vec3 blockVec = Vec3.m_82512_((Vec3i)destinationBlock);
        BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(Vector3d, blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this));
        return result.m_82425_().equals((Object)destinationBlock);
    }

    public boolean m_6220_() {
        return this.m_6084_() && this.getMushroomState() != null && this.getMushroomCount() > 0;
    }

    public boolean isShearable(@Nonnull ItemStack item, Level world, BlockPos pos) {
        return this.m_6220_();
    }

    public void m_5851_(SoundSource category) {
        this.m_146850_(GameEvent.f_223708_);
        this.m_9236_().m_6269_(null, (Entity)this, SoundEvents.f_12344_, category, 1.0f, 1.0f);
        if (!this.m_9236_().m_5776_() && this.getMushroomState() != null && this.getMushroomCount() > 0) {
            this.setMushroomCount(this.getMushroomCount() - 1);
            if (this.getMushroomCount() <= 0) {
                this.setMushroomState(null);
                this.setBeamTarget(null);
                this.beamCounter = Math.min(-1200, this.beamCounter);
            }
        }
    }

    @Nonnull
    public List<ItemStack> onSheared(@Nullable Player player, @Nonnull ItemStack item, Level world, BlockPos pos, int fortune) {
        world.m_6269_(null, (Entity)this, SoundEvents.f_12344_, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0f, 1.0f);
        if (!world.m_5776_() && this.getMushroomState() != null && this.getMushroomCount() > 0) {
            this.setMushroomCount(this.getMushroomCount() - 1);
            if (this.getMushroomCount() <= 0) {
                this.setMushroomState(null);
                this.setBeamTarget(null);
                this.beamCounter = Math.min(-1200, this.beamCounter);
            }
        }
        return Collections.emptyList();
    }

    public boolean isReverting() {
        return (Boolean)this.f_19804_.m_135370_(REVERTING);
    }

    public boolean isWarpedMoscoReady() {
        return this.getMushroomState() == Blocks.f_50691_.m_49966_() && this.getMushroomCount() >= 5;
    }

    class AITargetMushrooms
    extends Goal {
        private final int searchLength;
        protected BlockPos destinationBlock;
        protected int runDelay = 70;

        private AITargetMushrooms() {
            this.searchLength = 20;
        }

        public boolean m_8045_() {
            return this.destinationBlock != null && EntityMungus.this.isMushroomTarget((BlockPos)this.destinationBlock.m_122032_()) && this.isCloseToShroom(32.0);
        }

        public boolean isCloseToShroom(double dist) {
            return this.destinationBlock == null || EntityMungus.this.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
        }

        public boolean m_8036_() {
            if (EntityMungus.this.getBeamTarget() != null || EntityMungus.this.beamCounter < 0 || EntityMungus.this.getMushroomCount() <= 0) {
                return false;
            }
            if (this.runDelay > 0) {
                --this.runDelay;
                return false;
            }
            this.runDelay = 70 + EntityMungus.this.f_19796_.m_188503_(150);
            return this.searchForDestination();
        }

        public void m_8056_() {
        }

        public void m_8037_() {
            if (this.destinationBlock == null || !EntityMungus.this.isMushroomTarget(this.destinationBlock) || EntityMungus.this.beamCounter < 0) {
                this.m_8041_();
            } else if (!EntityMungus.this.hasLineOfSightMushroom(this.destinationBlock)) {
                EntityMungus.this.m_21573_().m_26519_((double)this.destinationBlock.m_123341_(), (double)this.destinationBlock.m_123342_(), (double)this.destinationBlock.m_123343_(), 1.0);
            } else {
                EntityMungus.this.setBeamTarget(this.destinationBlock);
                if (!EntityMungus.this.m_27593_()) {
                    EntityMungus.this.m_21573_().m_26573_();
                }
            }
        }

        public void m_8041_() {
            EntityMungus.this.setBeamTarget(null);
        }

        protected boolean searchForDestination() {
            int lvt_1_1_ = this.searchLength;
            BlockPos lvt_3_1_ = EntityMungus.this.m_20183_();
            BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
            for (int lvt_5_1_ = -5; lvt_5_1_ <= 5; ++lvt_5_1_) {
                for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                    int lvt_7_1_ = 0;
                    while (lvt_7_1_ <= lvt_6_1_) {
                        int lvt_8_1_;
                        int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                        while (lvt_8_1_ <= lvt_6_1_) {
                            lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                            if (this.isMushroom(EntityMungus.this.m_9236_(), lvt_4_1_)) {
                                this.destinationBlock = lvt_4_1_;
                                return true;
                            }
                            lvt_8_1_ = lvt_8_1_ > 0 ? -lvt_8_1_ : 1 - lvt_8_1_;
                        }
                        lvt_7_1_ = lvt_7_1_ > 0 ? -lvt_7_1_ : 1 - lvt_7_1_;
                    }
                }
            }
            return false;
        }

        private boolean isMushroom(Level world, BlockPos.MutableBlockPos lvt_4_1_) {
            return EntityMungus.this.isMushroomTarget((BlockPos)lvt_4_1_);
        }
    }
}

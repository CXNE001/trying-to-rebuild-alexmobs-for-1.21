/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityVoidPortal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ItemDimensionalCarver
extends Item {
    public static final int MAX_TIME = 200;

    public ItemDimensionalCarver(Item.Properties props) {
        super(props);
    }

    protected static BlockHitResult rayTracePortal(Level worldIn, Player player, ClipContext.Fluid fluidMode) {
        float f = player.m_146909_();
        float f1 = player.m_146908_();
        Vec3 vector3d = player.m_20299_(1.0f);
        float f11 = -f1 * ((float)Math.PI / 180) - (float)Math.PI;
        float f12 = -f * ((float)Math.PI / 180);
        float f2 = Mth.m_14089_((float)f11);
        float f3 = Mth.m_14031_((float)f11);
        float f4 = -Mth.m_14089_((float)f12);
        float f5 = Mth.m_14031_((float)f12);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 1.5;
        Vec3 vector3d1 = vector3d.m_82520_((double)f6 * 1.5, (double)f5 * 1.5, (double)f7 * 1.5);
        return worldIn.m_45547_(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, (Entity)player));
    }

    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        if (itemstack.m_41773_() >= itemstack.m_41776_()) {
            return InteractionResultHolder.m_19100_((Object)itemstack);
        }
        playerIn.m_6672_(handIn);
        BlockHitResult raytraceresult = ItemDimensionalCarver.rayTracePortal(worldIn, playerIn, ClipContext.Fluid.ANY);
        Direction dir = Direction.m_122382_((Entity)playerIn)[0];
        double x = raytraceresult.m_82450_().f_82479_ - (double)((float)dir.m_122436_().m_123341_() * 0.1f);
        double y = raytraceresult.m_82450_().f_82480_ - (double)((float)dir.m_122436_().m_123342_() * 0.1f);
        double z = raytraceresult.m_82450_().f_82481_ - (double)((float)dir.m_122436_().m_123343_() * 0.1f);
        if (itemstack.m_41784_().m_128471_("HASBLOCK")) {
            x = itemstack.m_41784_().m_128459_("BLOCKX");
            y = itemstack.m_41784_().m_128459_("BLOCKY");
            z = itemstack.m_41784_().m_128459_("BLOCKZ");
        } else {
            itemstack.m_41784_().m_128379_("HASBLOCK", true);
            itemstack.m_41784_().m_128347_("BLOCKX", x);
            itemstack.m_41784_().m_128347_("BLOCKY", y);
            itemstack.m_41784_().m_128347_("BLOCKZ", z);
            itemstack.m_41751_(itemstack.m_41784_());
        }
        worldIn.m_7106_((ParticleOptions)AMParticleRegistry.INVERT_DIG.get(), x, y, z, (double)playerIn.m_19879_(), 0.0, 0.0);
        return InteractionResultHolder.m_19096_((Object)itemstack);
    }

    public int m_8105_(ItemStack stack) {
        return 200;
    }

    public float getXpRepairRatio(ItemStack stack) {
        return 100.0f;
    }

    public void m_5929_(Level level, LivingEntity player, ItemStack itemstack, int count) {
        player.m_6674_(player.m_7655_());
        RandomSource random = player.m_217043_();
        if (count % 5 == 0) {
            player.m_146850_(GameEvent.f_223698_);
            player.m_5496_(SoundEvents.f_12201_, 1.0f, 0.5f + random.m_188501_());
        }
        boolean flag = false;
        if (itemstack.m_41784_().m_128471_("HASBLOCK")) {
            double x = itemstack.m_41784_().m_128459_("BLOCKX");
            double y = itemstack.m_41784_().m_128459_("BLOCKY");
            double z = itemstack.m_41784_().m_128459_("BLOCKZ");
            if ((double)random.m_188501_() < 0.2) {
                player.m_9236_().m_7106_((ParticleOptions)AMParticleRegistry.WORM_PORTAL.get(), x + random.m_188583_() * (double)0.1f, y + random.m_188583_() * (double)0.1f, z + random.m_188583_() * (double)0.1f, random.m_188583_() * (double)0.1f, (double)-0.1f, random.m_188583_() * (double)0.1f);
            }
            if (player.m_20275_(x, y, z) > 9.0) {
                flag = true;
                if (player instanceof Player) {
                    ((Player)player).m_36335_().m_41524_((Item)this, 40);
                }
            }
            if (count == 1 && !player.m_9236_().f_46443_) {
                player.m_146850_(GameEvent.f_223698_);
                player.m_5496_(SoundEvents.f_11983_, 1.0f, 0.5f);
                EntityVoidPortal portal = new EntityVoidPortal(player.m_9236_(), this);
                portal.m_6034_(x, y, z);
                Direction dir = Direction.m_122382_((Entity)player)[0].m_122424_();
                if (dir == Direction.UP) {
                    dir = Direction.DOWN;
                }
                portal.setAttachmentFacing(dir);
                player.m_9236_().m_7967_((Entity)portal);
                this.onPortalOpen(player.m_9236_(), player, portal, dir);
                itemstack.m_41622_(1, player, playerIn -> player.m_21190_(playerIn.m_7655_()));
                flag = true;
                if (player instanceof Player) {
                    ((Player)player).m_36335_().m_41524_((Item)this, 200);
                }
            }
        }
        if (flag) {
            player.m_5810_();
            itemstack.m_41784_().m_128379_("HASBLOCK", false);
            itemstack.m_41784_().m_128347_("BLOCKX", 0.0);
            itemstack.m_41784_().m_128347_("BLOCKY", 0.0);
            itemstack.m_41784_().m_128347_("BLOCKZ", 0.0);
            itemstack.m_41751_(itemstack.m_41784_());
        }
    }

    public void m_5551_(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        stack.m_41784_().m_128379_("HASBLOCK", false);
        stack.m_41784_().m_128347_("BLOCKX", 0.0);
        stack.m_41784_().m_128347_("BLOCKY", 0.0);
        stack.m_41784_().m_128347_("BLOCKZ", 0.0);
        stack.m_41751_(stack.m_41784_());
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.m_41656_((ItemStack)oldStack, (ItemStack)newStack);
    }

    public void onPortalOpen(Level worldIn, LivingEntity player, EntityVoidPortal portal, Direction dir) {
        BlockPos respawnPosition;
        portal.setLifespan(1200);
        ResourceKey respawnDimension = Level.f_46428_;
        BlockPos blockPos = respawnPosition = player.m_21257_().isPresent() ? (BlockPos)player.m_21257_().get() : player.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING, BlockPos.f_121853_);
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer)player;
            respawnDimension = serverPlayer.m_8963_();
            if (serverPlayer.m_8961_() != null) {
                respawnPosition = serverPlayer.m_8961_();
            }
        }
        portal.exitDimension = respawnDimension;
        portal.setDestination(respawnPosition.m_6630_(2));
    }
}

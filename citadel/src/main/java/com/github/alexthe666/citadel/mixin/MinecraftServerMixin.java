/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.Services
 *  net.minecraft.server.WorldStem
 *  net.minecraft.server.level.progress.ChunkProgressListenerFactory
 *  net.minecraft.server.packs.repository.PackRepository
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.ServerProxy;
import com.github.alexthe666.citadel.server.world.ModifiableTickRateServer;
import com.mojang.datafixers.DataFixer;
import java.net.Proxy;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MinecraftServer.class})
public abstract class MinecraftServerMixin
implements ModifiableTickRateServer {
    private long modifiedMsPerTick = -1L;
    private long masterMs;

    @Inject(method={"Lnet/minecraft/server/MinecraftServer;<init>(Ljava/lang/Thread;Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lnet/minecraft/server/packs/repository/PackRepository;Lnet/minecraft/server/WorldStem;Ljava/net/Proxy;Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/server/Services;Lnet/minecraft/server/level/progress/ChunkProgressListenerFactory;)V"}, at={@At(value="TAIL")})
    private void citadel_init(Thread thread, LevelStorageSource.LevelStorageAccess levelStorageAccess, PackRepository packRepository, WorldStem worldStem, Proxy proxy, DataFixer dataFixer, Services services, ChunkProgressListenerFactory chunkProgressListenerFactory, CallbackInfo ci) {
        ServerProxy.setMinecraftServer((MinecraftServer)this);
    }

    @Inject(method={"Lnet/minecraft/server/MinecraftServer;runServer()V"}, remap=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;startMetricsRecordingTick()V", shift=At.Shift.BEFORE)})
    protected void citadel_beforeServerTick(CallbackInfo ci) {
        this.masterTick();
    }

    private void masterTick() {
        this.masterMs += 50L;
    }

    @ModifyConstant(method={"Lnet/minecraft/server/MinecraftServer;runServer()V"}, remap=true, constant={@Constant(longValue=50L)}, expect=4)
    private long citadel_serverMsPerTick(long value) {
        return this.modifiedMsPerTick == -1L ? value : this.modifiedMsPerTick;
    }

    @Override
    public void setGlobalTickLengthMs(long msPerTick) {
        this.modifiedMsPerTick = msPerTick;
    }

    @Override
    public long getMasterMs() {
        return this.masterMs;
    }
}

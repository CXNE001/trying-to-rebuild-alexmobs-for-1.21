/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.server.tick.ServerTickRateTracker;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value={ServerLevel.class})
public class ServerLevelMixin {
    @Shadow
    @Final
    private MinecraftServer f_8548_;

    @ModifyConstant(method={"Lnet/minecraft/server/level/ServerLevel;tickTime()V"}, remap=true, constant={@Constant(longValue=1L)}, expect=2)
    private long citadel_clientSetDayTime(long timeIn) {
        return ServerTickRateTracker.getForServer(this.f_8548_).getDayTimeIncrement(timeIn);
    }
}

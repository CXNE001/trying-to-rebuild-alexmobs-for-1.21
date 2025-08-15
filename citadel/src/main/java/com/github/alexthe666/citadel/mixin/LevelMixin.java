/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.Citadel;
import java.util.function.Consumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Level.class})
public class LevelMixin {
    @Shadow
    @Final
    public boolean f_46443_;

    @Inject(at={@At(value="HEAD")}, remap=true, cancellable=true, method={"Lnet/minecraft/world/level/Level;guardEntityTick(Ljava/util/function/Consumer;Lnet/minecraft/world/entity/Entity;)V"})
    private void citadel_guardEntityTick(Consumer<Entity> ticker, Entity entity, CallbackInfo ci) {
        if (!this.f_46443_) {
            if (!Citadel.PROXY.canEntityTickServer((Level)this, entity)) {
                ci.cancel();
            }
        } else if (!Citadel.PROXY.canEntityTickClient((Level)this, entity)) {
            ci.cancel();
        }
    }
}

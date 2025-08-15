/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.ShaderInstance
 */
package com.github.alexthe666.citadel.client.shader;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.ShaderInstance;

public class CitadelInternalShaders {
    private static ShaderInstance renderTypeRainbowAura;

    @Nullable
    public static ShaderInstance getRenderTypeRainbowAura() {
        return renderTypeRainbowAura;
    }

    public static void setRenderTypeRainbowAura(ShaderInstance instance) {
        renderTypeRainbowAura = instance;
    }
}

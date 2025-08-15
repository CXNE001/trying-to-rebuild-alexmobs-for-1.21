/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 */
package com.github.alexthe666.citadel.client;

import com.github.alexthe666.citadel.client.CitadelItemstackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CitadelItemRenderProperties
implements IClientItemExtensions {
    private final BlockEntityWithoutLevelRenderer renderer = new CitadelItemstackRenderer();

    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return this.renderer;
    }
}

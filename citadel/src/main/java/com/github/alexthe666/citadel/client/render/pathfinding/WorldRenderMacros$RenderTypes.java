/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.RenderType$CompositeState
 */
package com.github.alexthe666.citadel.client.render.pathfinding;

import com.github.alexthe666.citadel.client.render.pathfinding.WorldRenderMacros;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;

private static final class WorldRenderMacros.RenderTypes
extends RenderType {
    private static final RenderType GLINT_LINES = WorldRenderMacros.RenderTypes.m_173215_((String)"structurize_glint_lines", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.DEBUG_LINES, (int)4096, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110137_).m_110663_(f_110111_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110115_).m_110691_(false));
    private static final RenderType GLINT_LINES_WITH_WIDTH = WorldRenderMacros.RenderTypes.m_173215_((String)"structurize_glint_lines_with_width", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)8192, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110137_).m_110663_(WorldRenderMacros.AlwaysDepthTestStateShard.ALWAYS_DEPTH_TEST).m_110661_(f_110158_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110114_).m_110691_(false));
    private static final RenderType LINES = WorldRenderMacros.RenderTypes.m_173215_((String)"structurize_lines", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.DEBUG_LINES, (int)16384, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110113_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110115_).m_110691_(false));
    private static final RenderType LINES_WITH_WIDTH = WorldRenderMacros.RenderTypes.m_173215_((String)"structurize_lines_with_width", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)8192, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110113_).m_110661_(f_110158_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110114_).m_110691_(false));
    private static final RenderType COLORED_TRIANGLES = WorldRenderMacros.RenderTypes.m_173215_((String)"structurize_colored_triangles", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)8192, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110113_).m_110661_(f_110158_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110114_).m_110691_(false));
    private static final RenderType COLORED_TRIANGLES_NC_ND = WorldRenderMacros.RenderTypes.m_173215_((String)"structurize_colored_triangles_nc_nd", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)4096, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110111_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110115_).m_110691_(false));

    private WorldRenderMacros.RenderTypes(String nameIn, VertexFormat formatIn, VertexFormat.Mode drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
        throw new IllegalStateException();
    }
}

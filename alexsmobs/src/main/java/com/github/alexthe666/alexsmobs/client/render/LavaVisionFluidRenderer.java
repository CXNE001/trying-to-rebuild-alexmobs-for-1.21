/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.LevelRenderer
 *  net.minecraft.client.renderer.block.LiquidBlockRenderer
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Direction$Plane
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.BlockAndTintGetter
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;

public class LavaVisionFluidRenderer
extends LiquidBlockRenderer {
    private static boolean isFaceOccludedByNeighbor(BlockGetter p_239283_0_, BlockPos p_239283_1_, Direction p_239283_2_, float p_239283_3_) {
        BlockPos blockpos = p_239283_1_.m_121945_(p_239283_2_);
        BlockState blockstate = p_239283_0_.m_8055_(blockpos);
        return LavaVisionFluidRenderer.m_110978_(p_239283_0_, p_239283_2_, p_239283_3_, blockpos, blockstate);
    }

    private static boolean m_110978_(BlockGetter p_239284_0_, Direction p_239284_1_, float p_239284_2_, BlockPos p_239284_3_, BlockState p_239284_4_) {
        if (p_239284_4_.m_60815_()) {
            VoxelShape voxelshape = Shapes.m_83048_((double)0.0, (double)0.0, (double)0.0, (double)1.0, (double)p_239284_2_, (double)1.0);
            VoxelShape voxelshape1 = p_239284_4_.m_60768_(p_239284_0_, p_239284_3_);
            return Shapes.m_83117_((VoxelShape)voxelshape, (VoxelShape)voxelshape1, (Direction)p_239284_1_);
        }
        return false;
    }

    private static boolean isAdjacentFluidSameAs(BlockGetter worldIn, BlockPos pos, Direction side, FluidState state) {
        BlockPos blockpos = pos.m_121945_(side);
        FluidState fluidstate = worldIn.m_6425_(blockpos);
        return fluidstate.m_76152_().m_6212_(state.m_76152_());
    }

    public void m_234369_(BlockAndTintGetter lightReaderIn, BlockPos posIn, VertexConsumer vertexBuilderIn, BlockState blockstateIn, FluidState fluidStateIn) {
        try {
            if (fluidStateIn.m_205070_(FluidTags.f_13132_)) {
                float f17;
                float f10;
                float f9;
                float f8;
                float f7;
                TextureAtlasSprite[] atextureatlassprite = ForgeHooksClient.getFluidSprites((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn, (FluidState)fluidStateIn);
                int i = IClientFluidTypeExtensions.of((FluidState)fluidStateIn).getTintColor(fluidStateIn, lightReaderIn, posIn);
                float alpha = (float)AMConfig.lavaOpacity;
                float f = (float)(i >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                BlockState blockstate = lightReaderIn.m_8055_(posIn.m_121945_(Direction.DOWN));
                FluidState fluidstate = blockstate.m_60819_();
                BlockState blockstate1 = lightReaderIn.m_8055_(posIn.m_121945_(Direction.UP));
                FluidState fluidstate1 = blockstate1.m_60819_();
                BlockState blockstate2 = lightReaderIn.m_8055_(posIn.m_121945_(Direction.NORTH));
                FluidState fluidstate2 = blockstate2.m_60819_();
                BlockState blockstate3 = lightReaderIn.m_8055_(posIn.m_121945_(Direction.SOUTH));
                FluidState fluidstate3 = blockstate3.m_60819_();
                BlockState blockstate4 = lightReaderIn.m_8055_(posIn.m_121945_(Direction.WEST));
                FluidState fluidstate4 = blockstate4.m_60819_();
                BlockState blockstate5 = lightReaderIn.m_8055_(posIn.m_121945_(Direction.EAST));
                FluidState fluidstate5 = blockstate5.m_60819_();
                boolean flag1 = !LavaVisionFluidRenderer.isNeighborSameFluidVanilla(fluidStateIn, fluidstate1);
                boolean flag2 = LavaVisionFluidRenderer.m_203166_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn, (FluidState)fluidStateIn, (BlockState)blockstateIn, (Direction)Direction.DOWN, (FluidState)fluidstate) && !LavaVisionFluidRenderer.isFaceOccludedByNeighborVanilla((BlockGetter)lightReaderIn, posIn, Direction.DOWN, 0.8888889f, blockstate);
                boolean flag3 = LavaVisionFluidRenderer.m_203166_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn, (FluidState)fluidStateIn, (BlockState)blockstateIn, (Direction)Direction.NORTH, (FluidState)fluidstate2);
                boolean flag4 = LavaVisionFluidRenderer.m_203166_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn, (FluidState)fluidStateIn, (BlockState)blockstateIn, (Direction)Direction.SOUTH, (FluidState)fluidstate3);
                boolean flag5 = LavaVisionFluidRenderer.m_203166_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn, (FluidState)fluidStateIn, (BlockState)blockstateIn, (Direction)Direction.WEST, (FluidState)fluidstate4);
                boolean flag6 = LavaVisionFluidRenderer.m_203166_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn, (FluidState)fluidStateIn, (BlockState)blockstateIn, (Direction)Direction.EAST, (FluidState)fluidstate5);
                if (!(flag1 || flag2 || flag6 || flag5 || flag3 || flag4)) {
                    return;
                }
                float f3 = lightReaderIn.m_7717_(Direction.DOWN, true);
                float f4 = lightReaderIn.m_7717_(Direction.UP, true);
                float f5 = lightReaderIn.m_7717_(Direction.NORTH, true);
                float f6 = lightReaderIn.m_7717_(Direction.WEST, true);
                Fluid fluid = fluidStateIn.m_76152_();
                float f11 = this.getFluidHeight((BlockGetter)lightReaderIn, posIn, fluid);
                if (f11 >= 1.0f) {
                    f7 = 1.0f;
                    f8 = 1.0f;
                    f9 = 1.0f;
                    f10 = 1.0f;
                } else {
                    float f12 = this.m_203160_(lightReaderIn, fluid, posIn.m_122012_(), blockstate2, fluidstate2);
                    float f13 = this.m_203160_(lightReaderIn, fluid, posIn.m_122019_(), blockstate3, fluidstate3);
                    float f14 = this.m_203160_(lightReaderIn, fluid, posIn.m_122029_(), blockstate5, fluidstate5);
                    float f15 = this.m_203160_(lightReaderIn, fluid, posIn.m_122024_(), blockstate4, fluidstate4);
                    f7 = this.m_203149_(lightReaderIn, fluid, f11, f12, f14, posIn.m_121945_(Direction.NORTH).m_121945_(Direction.EAST));
                    f8 = this.m_203149_(lightReaderIn, fluid, f11, f12, f15, posIn.m_121945_(Direction.NORTH).m_121945_(Direction.WEST));
                    f9 = this.m_203149_(lightReaderIn, fluid, f11, f13, f14, posIn.m_121945_(Direction.SOUTH).m_121945_(Direction.EAST));
                    f10 = this.m_203149_(lightReaderIn, fluid, f11, f13, f15, posIn.m_121945_(Direction.SOUTH).m_121945_(Direction.WEST));
                }
                double d1 = posIn.m_123341_() & 0xF;
                double d2 = posIn.m_123342_() & 0xF;
                double d0 = posIn.m_123343_() & 0xF;
                float f12 = f17 = flag2 ? 0.001f : 0.0f;
                if (flag1 && !LavaVisionFluidRenderer.isFaceOccludedByNeighborVanilla((BlockGetter)lightReaderIn, posIn, Direction.UP, Math.min(Math.min(f8, f10), Math.min(f9, f7)), blockstate1)) {
                    float f25;
                    float f21;
                    float f24;
                    float f20;
                    float f23;
                    float f19;
                    float f22;
                    float f18;
                    f8 -= 0.001f;
                    f10 -= 0.001f;
                    f9 -= 0.001f;
                    f7 -= 0.001f;
                    Vec3 vec3 = fluidStateIn.m_76179_((BlockGetter)lightReaderIn, posIn);
                    if (vec3.f_82479_ == 0.0 && vec3.f_82481_ == 0.0) {
                        TextureAtlasSprite textureatlassprite1 = atextureatlassprite[0];
                        f18 = textureatlassprite1.m_118367_(0.0);
                        f22 = textureatlassprite1.m_118393_(0.0);
                        f19 = f18;
                        f23 = textureatlassprite1.m_118393_(16.0);
                        f20 = textureatlassprite1.m_118367_(16.0);
                        f24 = f23;
                        f21 = f20;
                        f25 = f22;
                    } else {
                        TextureAtlasSprite textureatlassprite = atextureatlassprite[1];
                        float f26 = (float)Mth.m_14136_((double)vec3.f_82481_, (double)vec3.f_82479_) - 1.5707964f;
                        float f27 = Mth.m_14031_((float)f26) * 0.25f;
                        float f28 = Mth.m_14089_((float)f26) * 0.25f;
                        f18 = textureatlassprite.m_118367_((double)(8.0f + (-f28 - f27) * 16.0f));
                        f22 = textureatlassprite.m_118393_((double)(8.0f + (-f28 + f27) * 16.0f));
                        f19 = textureatlassprite.m_118367_((double)(8.0f + (-f28 + f27) * 16.0f));
                        f23 = textureatlassprite.m_118393_((double)(8.0f + (f28 + f27) * 16.0f));
                        f20 = textureatlassprite.m_118367_((double)(8.0f + (f28 + f27) * 16.0f));
                        f24 = textureatlassprite.m_118393_((double)(8.0f + (f28 - f27) * 16.0f));
                        f21 = textureatlassprite.m_118367_((double)(8.0f + (f28 - f27) * 16.0f));
                        f25 = textureatlassprite.m_118393_((double)(8.0f + (-f28 - f27) * 16.0f));
                    }
                    float f49 = (f18 + f19 + f20 + f21) / 4.0f;
                    float f50 = (f22 + f23 + f24 + f25) / 4.0f;
                    float f51 = (float)atextureatlassprite[0].m_245424_().m_246492_() / (atextureatlassprite[0].m_118410_() - atextureatlassprite[0].m_118409_());
                    float f52 = (float)atextureatlassprite[0].m_245424_().m_245330_() / (atextureatlassprite[0].m_118412_() - atextureatlassprite[0].m_118411_());
                    float f53 = 4.0f / Math.max(f52, f51);
                    f18 = Mth.m_14179_((float)f53, (float)f18, (float)f49);
                    f19 = Mth.m_14179_((float)f53, (float)f19, (float)f49);
                    f20 = Mth.m_14179_((float)f53, (float)f20, (float)f49);
                    f21 = Mth.m_14179_((float)f53, (float)f21, (float)f49);
                    f22 = Mth.m_14179_((float)f53, (float)f22, (float)f50);
                    f23 = Mth.m_14179_((float)f53, (float)f23, (float)f50);
                    f24 = Mth.m_14179_((float)f53, (float)f24, (float)f50);
                    f25 = Mth.m_14179_((float)f53, (float)f25, (float)f50);
                    int j = this.getCombinedAverageLight(lightReaderIn, posIn);
                    float f30 = f4 * f;
                    float f31 = f4 * f1;
                    float f32 = f4 * f2;
                    this.vertexVanilla(vertexBuilderIn, d1 + 0.0, d2 + (double)f8, d0 + 0.0, f30, f31, f32, alpha, f18, f22, j);
                    this.vertexVanilla(vertexBuilderIn, d1 + 0.0, d2 + (double)f10, d0 + 1.0, f30, f31, f32, alpha, f19, f23, j);
                    this.vertexVanilla(vertexBuilderIn, d1 + 1.0, d2 + (double)f9, d0 + 1.0, f30, f31, f32, alpha, f20, f24, j);
                    this.vertexVanilla(vertexBuilderIn, d1 + 1.0, d2 + (double)f7, d0 + 0.0, f30, f31, f32, alpha, f21, f25, j);
                    if (fluidStateIn.m_76171_((BlockGetter)lightReaderIn, posIn.m_7494_())) {
                        this.vertexVanilla(vertexBuilderIn, d1 + 0.0, d2 + (double)f8, d0 + 0.0, f30, f31, f32, alpha, f18, f22, j);
                        this.vertexVanilla(vertexBuilderIn, d1 + 1.0, d2 + (double)f7, d0 + 0.0, f30, f31, f32, alpha, f21, f25, j);
                        this.vertexVanilla(vertexBuilderIn, d1 + 1.0, d2 + (double)f9, d0 + 1.0, f30, f31, f32, alpha, f20, f24, j);
                        this.vertexVanilla(vertexBuilderIn, d1 + 0.0, d2 + (double)f10, d0 + 1.0, f30, f31, f32, alpha, f19, f23, j);
                    }
                }
                if (flag2) {
                    float f40 = atextureatlassprite[0].m_118409_();
                    float f41 = atextureatlassprite[0].m_118410_();
                    float f42 = atextureatlassprite[0].m_118411_();
                    float f43 = atextureatlassprite[0].m_118412_();
                    int l = this.getCombinedAverageLight(lightReaderIn, posIn.m_7495_());
                    float f46 = f3 * f;
                    float f47 = f3 * f1;
                    float f48 = f3 * f2;
                    this.vertexVanilla(vertexBuilderIn, d1, d2 + (double)f17, d0 + 1.0, f46, f47, f48, alpha, f40, f43, l);
                    this.vertexVanilla(vertexBuilderIn, d1, d2 + (double)f17, d0, f46, f47, f48, alpha, f40, f42, l);
                    this.vertexVanilla(vertexBuilderIn, d1 + 1.0, d2 + (double)f17, d0, f46, f47, f48, alpha, f41, f42, l);
                    this.vertexVanilla(vertexBuilderIn, d1 + 1.0, d2 + (double)f17, d0 + 1.0, f46, f47, f48, alpha, f41, f43, l);
                }
                int k = this.getCombinedAverageLight(lightReaderIn, posIn);
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    double d6;
                    double d4;
                    double d5;
                    double d3;
                    float f45;
                    float f44;
                    if (!(switch (direction) {
                        case Direction.NORTH -> {
                            f44 = f8;
                            f45 = f7;
                            d3 = d1;
                            d5 = d1 + 1.0;
                            d4 = d0 + (double)0.001f;
                            d6 = d0 + (double)0.001f;
                            yield flag3;
                        }
                        case Direction.SOUTH -> {
                            f44 = f9;
                            f45 = f10;
                            d3 = d1 + 1.0;
                            d5 = d1;
                            d4 = d0 + 1.0 - (double)0.001f;
                            d6 = d0 + 1.0 - (double)0.001f;
                            yield flag4;
                        }
                        case Direction.WEST -> {
                            f44 = f10;
                            f45 = f8;
                            d3 = d1 + (double)0.001f;
                            d5 = d1 + (double)0.001f;
                            d4 = d0 + 1.0;
                            d6 = d0;
                            yield flag5;
                        }
                        default -> {
                            f44 = f7;
                            f45 = f9;
                            d3 = d1 + 1.0 - (double)0.001f;
                            d5 = d1 + 1.0 - (double)0.001f;
                            d4 = d0;
                            d6 = d0 + 1.0;
                            yield flag6;
                        }
                    }) || LavaVisionFluidRenderer.isFaceOccludedByNeighborVanilla((BlockGetter)lightReaderIn, posIn, direction, Math.max(f44, f45), lightReaderIn.m_8055_(posIn.m_121945_(direction)))) continue;
                    BlockPos blockpos = posIn.m_121945_(direction);
                    TextureAtlasSprite textureatlassprite2 = atextureatlassprite[1];
                    if (atextureatlassprite[2] != null && lightReaderIn.m_8055_(blockpos).shouldDisplayFluidOverlay(lightReaderIn, blockpos, fluidStateIn)) {
                        textureatlassprite2 = atextureatlassprite[2];
                    }
                    float f54 = textureatlassprite2.m_118367_(0.0);
                    float f55 = textureatlassprite2.m_118367_(8.0);
                    float f33 = textureatlassprite2.m_118393_((double)((1.0f - f44) * 16.0f * 0.5f));
                    float f34 = textureatlassprite2.m_118393_((double)((1.0f - f45) * 16.0f * 0.5f));
                    float f35 = textureatlassprite2.m_118393_(8.0);
                    float f36 = direction.m_122434_() == Direction.Axis.Z ? f5 : f6;
                    float f37 = f4 * f36 * f;
                    float f38 = f4 * f36 * f1;
                    float f39 = f4 * f36 * f2;
                    this.vertexVanilla(vertexBuilderIn, d3, d2 + (double)f44, d4, f37, f38, f39, alpha, f54, f33, k);
                    this.vertexVanilla(vertexBuilderIn, d5, d2 + (double)f45, d6, f37, f38, f39, alpha, f55, f34, k);
                    this.vertexVanilla(vertexBuilderIn, d5, d2 + (double)f17, d6, f37, f38, f39, alpha, f55, f35, k);
                    this.vertexVanilla(vertexBuilderIn, d3, d2 + (double)f17, d4, f37, f38, f39, alpha, f54, f35, k);
                }
                return;
            }
            super.m_234369_(lightReaderIn, posIn, vertexBuilderIn, blockstateIn, fluidStateIn);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void vertexVanilla(VertexConsumer vertexBuilderIn, double x, double y, double z, float red, float green, float blue, float alpha, float u, float v, int packedLight) {
        vertexBuilderIn.m_5483_(x, y, z).m_85950_(red, green, blue, alpha).m_7421_(u, v).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
    }

    private int getCombinedAverageLight(BlockAndTintGetter lightReaderIn, BlockPos posIn) {
        int i = LevelRenderer.m_109541_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn);
        int j = LevelRenderer.m_109541_((BlockAndTintGetter)lightReaderIn, (BlockPos)posIn.m_7494_());
        int k = i & 0xFF;
        int l = j & 0xFF;
        int i1 = i >> 16 & 0xFF;
        int j1 = j >> 16 & 0xFF;
        return Math.max(k, l) | Math.max(i1, j1) << 16;
    }

    private float getFluidHeight(BlockGetter reader, BlockPos pos, Fluid fluidIn) {
        int i = 0;
        float f = 0.0f;
        for (int j = 0; j < 4; ++j) {
            BlockPos blockpos = pos.m_7918_(-(j & 1), 0, -(j >> 1 & 1));
            if (reader.m_6425_(blockpos.m_7494_()).m_76152_().m_6212_(fluidIn)) {
                return 1.0f;
            }
            FluidState fluidstate = reader.m_6425_(blockpos);
            if (fluidstate.m_76152_().m_6212_(fluidIn)) {
                float f1 = fluidstate.m_76155_(reader, blockpos);
                if (f1 >= 0.8f) {
                    f += f1 * 10.0f;
                    i += 10;
                    continue;
                }
                f += f1;
                ++i;
                continue;
            }
            if (reader.m_8055_(blockpos).m_280296_()) continue;
            ++i;
        }
        return f / (float)i;
    }

    private static boolean isNeighborSameFluidVanilla(FluidState p_203186_, FluidState p_203187_) {
        return p_203187_.m_76152_().m_6212_(p_203186_.m_76152_());
    }

    private static boolean isFaceOccludedByStateVanilla(BlockGetter p_110979_, Direction p_110980_, float p_110981_, BlockPos p_110982_, BlockState p_110983_) {
        if (p_110983_.m_60815_()) {
            VoxelShape voxelshape = Shapes.m_83048_((double)0.0, (double)0.0, (double)0.0, (double)1.0, (double)p_110981_, (double)1.0);
            VoxelShape voxelshape1 = p_110983_.m_60768_(p_110979_, p_110982_);
            return Shapes.m_83117_((VoxelShape)voxelshape, (VoxelShape)voxelshape1, (Direction)p_110980_);
        }
        return false;
    }

    private static boolean isFaceOccludedByNeighborVanilla(BlockGetter p_203180_, BlockPos p_203181_, Direction p_203182_, float p_203183_, BlockState p_203184_) {
        return LavaVisionFluidRenderer.isFaceOccludedByStateVanilla(p_203180_, p_203182_, p_203183_, p_203181_.m_121945_(p_203182_), p_203184_);
    }

    private static boolean isFaceOccludedBySelfVanilla(BlockGetter p_110960_, BlockPos p_110961_, BlockState p_110962_, Direction p_110963_) {
        return LavaVisionFluidRenderer.isFaceOccludedByStateVanilla(p_110960_, p_110963_.m_122424_(), 1.0f, p_110961_, p_110962_);
    }

    private float m_203149_(BlockAndTintGetter p_203150_, Fluid p_203151_, float p_203152_, float p_203153_, float p_203154_, BlockPos p_203155_) {
        if (!(p_203154_ >= 1.0f) && !(p_203153_ >= 1.0f)) {
            float[] afloat = new float[2];
            if (p_203154_ > 0.0f || p_203153_ > 0.0f) {
                float f = this.m_203156_(p_203150_, p_203151_, p_203155_);
                if (f >= 1.0f) {
                    return 1.0f;
                }
                this.m_203188_(afloat, f);
            }
            this.m_203188_(afloat, p_203152_);
            this.m_203188_(afloat, p_203154_);
            this.m_203188_(afloat, p_203153_);
            return afloat[0] / afloat[1];
        }
        return 1.0f;
    }

    private void m_203188_(float[] p_203189_, float p_203190_) {
        if (p_203190_ >= 0.8f) {
            p_203189_[0] = p_203189_[0] + p_203190_ * 10.0f;
            p_203189_[1] = p_203189_[1] + 10.0f;
        } else if (p_203190_ >= 0.0f) {
            p_203189_[0] = p_203189_[0] + p_203190_;
            p_203189_[1] = p_203189_[1] + 1.0f;
        }
    }

    private float m_203156_(BlockAndTintGetter p_203157_, Fluid p_203158_, BlockPos p_203159_) {
        BlockState blockstate = p_203157_.m_8055_(p_203159_);
        return this.m_203160_(p_203157_, p_203158_, p_203159_, blockstate, blockstate.m_60819_());
    }

    private float m_203160_(BlockAndTintGetter p_203161_, Fluid p_203162_, BlockPos p_203163_, BlockState p_203164_, FluidState p_203165_) {
        if (p_203162_.m_6212_(p_203165_.m_76152_())) {
            BlockState blockstate = p_203161_.m_8055_(p_203163_.m_7494_());
            return p_203162_.m_6212_(blockstate.m_60819_().m_76152_()) ? 1.0f : p_203165_.m_76182_();
        }
        return !p_203164_.m_280296_() ? 0.0f : -1.0f;
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.InputConstants
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.screens.TitleScreen
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.TextureAtlas
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.client.resources.sounds.SimpleSoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.data.ModelData
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.citadel.client.game;

import com.github.alexthe666.citadel.client.game.TetrominoShape;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;

public class Tetris {
    protected final RandomSource random = RandomSource.m_216327_();
    private boolean started = false;
    private int score;
    private int renderTime = 0;
    private int keyCooldown;
    private static final int HEIGHT = 20;
    private TetrominoShape fallingShape;
    private BlockState fallingBlock;
    private float fallingX;
    private float prevFallingY;
    private float fallingY;
    private Rotation fallingRotation;
    private final BlockState[][] settledBlocks = new BlockState[10][20];
    private boolean gameOver = false;
    private TetrominoShape nextShape;
    private BlockState nextBlock;
    private final boolean[] flashingLayer = new boolean[20];
    private int flashFor = 0;
    private final Block[] allRegisteredBlocks = (Block[])ForgeRegistries.BLOCKS.getValues().stream().toArray(Block[]::new);

    public Tetris() {
        this.reset();
    }

    public void tick() {
        ++this.renderTime;
        this.prevFallingY = this.fallingY;
        if (this.keyCooldown > 0) {
            --this.keyCooldown;
        }
        if (this.started && !this.gameOver) {
            if (this.fallingShape == null) {
                this.generateTetromino();
                this.generateNextTetromino();
            } else if (this.groundedTetromino()) {
                this.groundTetromino();
                this.fallingShape = null;
            } else {
                float f = 0.15f;
                if (InputConstants.m_84830_((long)Minecraft.m_91087_().m_91268_().m_85439_(), (int)264)) {
                    f = 1.0f;
                }
                this.fallingY += f;
                if (this.keyPressed(263) && !this.isBlocksInOffset(-1, 0)) {
                    this.fallingX = this.restrictTetrominoX((int)(Math.floor(this.fallingX) - 1.0));
                }
                if (this.keyPressed(262) && !this.isBlocksInOffset(1, 0)) {
                    this.fallingX = this.restrictTetrominoX((int)(Math.ceil(this.fallingX) + 1.0));
                }
                if (this.keyPressed(265) && this.fallingRotation != null && this.fallingShape != TetrominoShape.SQUARE) {
                    this.fallingRotation = this.fallingRotation.m_55952_(Rotation.CLOCKWISE_90);
                    this.fallingX = this.restrictTetrominoX((int)Math.floor(this.fallingX));
                }
            }
        }
        if (this.flashFor > 0) {
            --this.flashFor;
            if (this.flashFor == 0) {
                for (int j = 0; j < 20; ++j) {
                    if (!this.flashingLayer[j]) continue;
                    for (int k = j; k < 20; ++k) {
                        for (int i = 0; i < 10; ++i) {
                            this.settledBlocks[i][k] = k < 19 ? this.settledBlocks[i][k + 1] : null;
                        }
                    }
                }
                int cleared = 0;
                Minecraft.m_91087_().m_91106_().m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)SoundEvents.f_12275_, (float)1.0f));
                for (int i = 0; i < this.flashingLayer.length; ++i) {
                    if (this.flashingLayer[i]) {
                        ++cleared;
                    }
                    this.flashingLayer[i] = false;
                }
                if (cleared == 1) {
                    this.score += 40;
                } else if (cleared == 2) {
                    this.score += 100;
                } else if (cleared == 3) {
                    this.score += 300;
                } else if (cleared >= 4) {
                    this.score += 1200 * (cleared - 3);
                }
            }
        }
        if (this.keyPressed(84)) {
            this.started = true;
            this.reset();
        }
    }

    private boolean groundedTetromino() {
        for (Vec3i vec : this.fallingShape.getRelativePositions()) {
            Vec3i vec2 = Tetris.transform(vec, this.fallingRotation, Vec3i.f_123288_);
            int x = Math.round(this.fallingX) + vec2.m_123341_();
            int y = 20 - (int)Math.ceil(this.fallingY) - vec2.m_123342_();
            if (y < 0) {
                return true;
            }
            if (x < 0 || x >= 10 || y < 0 || y >= 20 || y > 0 && this.settledBlocks[x][y - 1] == null) continue;
            return true;
        }
        return false;
    }

    private void groundTetromino() {
        for (Vec3i vec : this.fallingShape.getRelativePositions()) {
            Vec3i vec2 = Tetris.transform(vec, this.fallingRotation, Vec3i.f_123288_);
            int x = Math.round(this.fallingX) + vec2.m_123341_();
            int y = 20 - (int)Math.ceil(this.fallingY) - vec2.m_123342_();
            if (x < 0 || x >= 10 || y < 0 || y >= 20) continue;
            if (y >= 19) {
                this.gameOver = true;
                Minecraft.m_91087_().m_91106_().m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)SoundEvents.f_12322_, (float)1.0f));
            }
            if (this.settledBlocks[x][y] != null) continue;
            this.settledBlocks[x][y] = this.fallingBlock;
        }
        boolean flag = false;
        block1: for (int j = 0; j < 20; ++j) {
            for (int i = 0; i < 10 && this.settledBlocks[i][j] != null; ++i) {
                if (i != 9) continue;
                this.flashingLayer[j] = true;
                flag = true;
                continue block1;
            }
        }
        if (flag) {
            Minecraft.m_91087_().m_91106_().m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)SoundEvents.f_11871_, (float)1.0f));
            this.flashFor = 20;
        }
        Minecraft.m_91087_().m_91106_().m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)this.fallingBlock.m_60827_().m_56777_(), (float)1.0f));
    }

    private boolean isBlocksInOffset(int xOffset, int yOffset) {
        for (Vec3i vec : this.fallingShape.getRelativePositions()) {
            Vec3i vec2 = Tetris.transform(vec, this.fallingRotation, Vec3i.f_123288_);
            int x = Math.round(this.fallingX) + vec2.m_123341_() + xOffset;
            int y = 20 - (int)Math.ceil(this.fallingY) - vec2.m_123342_() + yOffset;
            if (x < 0 || x >= 10 || y < 0 || y >= 20 || y > 0 && this.settledBlocks[x][y] == null) continue;
            return true;
        }
        return false;
    }

    public boolean isStarted() {
        return this.started;
    }

    private boolean keyPressed(int keyId) {
        if (this.keyCooldown == 0 && InputConstants.m_84830_((long)Minecraft.m_91087_().m_91268_().m_85439_(), (int)keyId)) {
            this.keyCooldown = 4;
            return true;
        }
        return false;
    }

    private void generateNextTetromino() {
        BlockState randomState = Blocks.f_50493_.m_49966_();
        for (int tries = 0; tries < 5; ++tries) {
            if (this.allRegisteredBlocks.length <= 1) continue;
            BlockState block = this.allRegisteredBlocks[this.random.m_188503_(this.allRegisteredBlocks.length - 1)].m_49966_();
            try {
                BakedModel blockModel = Minecraft.m_91087_().m_91289_().m_110910_(block);
                if (blockModel == null || block.m_60713_(Blocks.f_50141_) || blockModel.m_7521_() || !blockModel.getRenderTypes(block, this.random, ModelData.EMPTY).contains(RenderType.m_110451_())) continue;
                randomState = block;
                break;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.nextShape = TetrominoShape.getRandom(this.random);
        this.nextBlock = randomState;
    }

    private void generateTetromino() {
        this.fallingShape = this.nextShape;
        this.fallingBlock = this.nextBlock;
        this.fallingRotation = Rotation.m_221990_((RandomSource)this.random);
        this.fallingX = this.restrictTetrominoX(this.random.m_188503_(10));
        this.prevFallingY = 0.0f;
        this.fallingY = -2.0f;
    }

    private int restrictTetrominoX(int xIn) {
        int minShapeX = 0;
        int maxShapeX = 0;
        for (Vec3i vec : this.fallingShape.getRelativePositions()) {
            Vec3i vec2 = Tetris.transform(vec, this.fallingRotation, Vec3i.f_123288_);
            if (vec2.m_123341_() < minShapeX) {
                minShapeX = vec2.m_123341_();
            }
            if (vec2.m_123341_() <= maxShapeX) continue;
            maxShapeX = vec2.m_123341_();
        }
        if (xIn + minShapeX < 0) {
            xIn = Math.max(xIn - minShapeX, minShapeX);
        }
        if (xIn + maxShapeX > 9) {
            xIn = Math.min(xIn - maxShapeX, 9 - maxShapeX);
        }
        return xIn;
    }

    private void renderTetromino(TetrominoShape shape, BlockState blockState, Rotation fallingRotation, float x, float y, float scale, float offsetX, float offsetY) {
        for (Vec3i vec : shape.getRelativePositions()) {
            Vec3i vec2 = Tetris.transform(vec, fallingRotation, Vec3i.f_123288_);
            this.renderBlockState(blockState, offsetX + (x + (float)vec2.m_123341_()) * scale, offsetY + (y + (float)vec2.m_123342_()) * scale, scale);
        }
    }

    private void renderBlockState(BlockState state, float offsetX, float offsetY, float size) {
        TextureAtlasSprite sprite = Minecraft.m_91087_().m_91289_().m_110910_(state).getParticleIcon(ModelData.EMPTY);
        Tesselator tesselator = Tesselator.m_85913_();
        BufferBuilder bufferbuilder = tesselator.m_85915_();
        bufferbuilder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85817_);
        float f = size * 0.5f;
        bufferbuilder.m_5483_((double)(-f + offsetX), (double)(f + offsetY), 80.0).m_7421_(sprite.m_118409_(), sprite.m_118412_()).m_5752_();
        bufferbuilder.m_5483_((double)(f + offsetX), (double)(f + offsetY), 80.0).m_7421_(sprite.m_118410_(), sprite.m_118412_()).m_5752_();
        bufferbuilder.m_5483_((double)(f + offsetX), (double)(-f + offsetY), 80.0).m_7421_(sprite.m_118410_(), sprite.m_118411_()).m_5752_();
        bufferbuilder.m_5483_((double)(-f + offsetX), (double)(-f + offsetY), 80.0).m_7421_(sprite.m_118409_(), sprite.m_118411_()).m_5752_();
        tesselator.m_85914_();
    }

    public void render(TitleScreen screen, GuiGraphics guiGraphics, float partialTick) {
        float scale = Math.min((float)screen.f_96543_ / 15.0f, (float)screen.f_96544_ / 20.0f);
        float offsetX = (float)screen.f_96543_ / 2.0f - scale * 5.0f;
        float offsetY = scale * 0.5f;
        if (this.started) {
            guiGraphics.m_285944_(RenderType.m_286086_(), (int)((float)screen.f_96543_ * 0.05f), (int)((float)screen.f_96544_ * 0.3f), (int)((float)screen.f_96543_ * 0.05f) + 70, (int)((float)screen.f_96544_ * 0.5f), -1873784752);
            guiGraphics.m_285944_(RenderType.m_286086_(), (int)((float)screen.f_96543_ * 0.7f), (int)((float)screen.f_96544_ * 0.3f), (int)((float)screen.f_96543_ * 0.7f) + 130, (int)((float)screen.f_96544_ * 0.84f), -1873784752);
            RenderSystem.setShader(GameRenderer::m_172817_);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.setShaderTexture((int)0, (ResourceLocation)TextureAtlas.f_118259_);
            for (int i = 0; i < this.settledBlocks.length; ++i) {
                int max = this.settledBlocks[i].length;
                for (int j = 0; j < max; ++j) {
                    BlockState state = this.settledBlocks[i][j];
                    if (this.flashingLayer[j] && this.renderTime % 4 < 2) {
                        state = Blocks.f_50141_.m_49966_();
                    }
                    if (state == null) continue;
                    this.renderBlockState(state, offsetX + (float)i * scale, offsetY + (float)(max - j - 1) * scale, scale);
                }
            }
            if (this.fallingShape != null) {
                float lerpedFallingY = this.prevFallingY + (this.fallingY - this.prevFallingY) * partialTick;
                this.renderTetromino(this.fallingShape, this.fallingBlock, this.fallingRotation, this.fallingX, lerpedFallingY, scale, offsetX, offsetY);
            }
            if (this.nextShape != null) {
                this.renderTetromino(this.nextShape, this.nextBlock, Rotation.NONE, 0.0f, 0.0f, scale, (float)screen.f_96543_ * 0.85f, (float)screen.f_96544_ * 0.4f);
            }
            float hue = (float)(System.currentTimeMillis() % 6000L) / 6000.0f;
            int rainbow = Color.HSBtoRGB(hue, 0.6f, 1.0f);
            guiGraphics.m_280168_().m_85836_();
            guiGraphics.m_280168_().m_85841_(2.0f, 2.0f, 2.0f);
            guiGraphics.m_280137_(Minecraft.m_91087_().f_91062_, "SCORE", (int)((float)screen.f_96543_ * 0.065f), (int)((float)screen.f_96544_ * 0.175f), rainbow);
            guiGraphics.m_280137_(Minecraft.m_91087_().f_91062_, "" + this.score, (int)((float)screen.f_96543_ * 0.065f), (int)((float)screen.f_96544_ * 0.175f) + 10, rainbow);
            guiGraphics.m_280168_().m_85849_();
            guiGraphics.m_280488_(Minecraft.m_91087_().f_91062_, "[LEFT ARROW] move left", (int)((float)screen.f_96543_ * 0.71f), (int)((float)screen.f_96544_ * 0.55f), rainbow);
            guiGraphics.m_280488_(Minecraft.m_91087_().f_91062_, "[RIGHT ARROW] move right", (int)((float)screen.f_96543_ * 0.71f), (int)((float)screen.f_96544_ * 0.55f) + 10, rainbow);
            guiGraphics.m_280488_(Minecraft.m_91087_().f_91062_, "[UP ARROW] rotate", (int)((float)screen.f_96543_ * 0.71f), (int)((float)screen.f_96544_ * 0.55f) + 20, rainbow);
            guiGraphics.m_280488_(Minecraft.m_91087_().f_91062_, "[DOWN ARROW] quick drop", (int)((float)screen.f_96543_ * 0.71f), (int)((float)screen.f_96544_ * 0.55f) + 30, rainbow);
            guiGraphics.m_280488_(Minecraft.m_91087_().f_91062_, "[T] start over", (int)((float)screen.f_96543_ * 0.71f), (int)((float)screen.f_96544_ * 0.55f) + 50, rainbow);
            guiGraphics.m_280488_(Minecraft.m_91087_().f_91062_, "Happy april fools from Citadel", 5, 5, rainbow);
            if (this.gameOver) {
                guiGraphics.m_280168_().m_85836_();
                guiGraphics.m_280168_().m_252880_((float)((int)((float)screen.f_96543_ * 0.5f)), (float)((int)((float)screen.f_96544_ * 0.5f)), 150.0f);
                guiGraphics.m_280168_().m_85841_(3.0f + (float)Math.sin((double)hue * Math.PI) * 0.4f, 3.0f + (float)Math.sin((double)hue * Math.PI) * 0.4f, 3.0f + (float)Math.sin((double)hue * Math.PI) * 0.4f);
                guiGraphics.m_280168_().m_252781_(Axis.f_252403_.m_252977_((float)Math.sin((double)hue * Math.PI) * 10.0f));
                guiGraphics.m_280137_(Minecraft.m_91087_().f_91062_, "GAME OVER", 0, 0, rainbow);
                guiGraphics.m_280168_().m_85849_();
            }
        }
    }

    public void reset() {
        int i;
        this.score = 0;
        for (i = 0; i < this.settledBlocks.length; ++i) {
            for (int j = 0; j < this.settledBlocks[i].length; ++j) {
                this.settledBlocks[i][j] = null;
            }
        }
        this.gameOver = false;
        for (i = 0; i < this.flashingLayer.length; ++i) {
            this.flashingLayer[i] = false;
        }
        this.generateNextTetromino();
        this.generateTetromino();
        this.generateNextTetromino();
    }

    private static Vec3i transform(Vec3i vec3i, Rotation rotation, Vec3i relativeTo) {
        int i = vec3i.m_123341_();
        int k = vec3i.m_123342_();
        int j = vec3i.m_123343_();
        boolean flag = true;
        int l = relativeTo.m_123341_();
        int i1 = relativeTo.m_123342_();
        switch (rotation) {
            case COUNTERCLOCKWISE_90: {
                return new Vec3i(l - i1 + k, l + i1 - i, j);
            }
            case CLOCKWISE_90: {
                return new Vec3i(l + i1 - k, i1 - l + i, j);
            }
            case CLOCKWISE_180: {
                return new Vec3i(l + l - i, i1 + i1 - k, j);
            }
        }
        return flag ? new Vec3i(i, k, j) : vec3i;
    }
}

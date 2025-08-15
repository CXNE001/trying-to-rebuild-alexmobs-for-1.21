/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.BookModel
 *  net.minecraft.client.model.geom.ModelLayers
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.citadel.client.render;

import com.github.alexthe666.citadel.server.block.CitadelLecternBlockEntity;
import com.github.alexthe666.citadel.server.block.LecternBooks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

public class CitadelLecternRenderer
implements BlockEntityRenderer<CitadelLecternBlockEntity> {
    private final BookModel bookModel;
    public static final ResourceLocation BOOK_PAGE_TEXTURE = new ResourceLocation("citadel:textures/entity/lectern_book_pages.png");
    public static final ResourceLocation BOOK_BINDING_TEXTURE = new ResourceLocation("citadel:textures/entity/lectern_book_binding.png");
    private static final LecternBooks.BookData EMPTY_BOOK_DATA = new LecternBooks.BookData(12944441, 16050623);

    public CitadelLecternRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.m_173582_(ModelLayers.f_171271_));
    }

    public void render(CitadelLecternBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int i, int j) {
        BlockState blockstate = blockEntity.m_58900_();
        if (((Boolean)blockstate.m_61143_((Property)LecternBlock.f_54467_)).booleanValue()) {
            LecternBooks.BookData bookData = LecternBooks.BOOKS.getOrDefault(ForgeRegistries.ITEMS.getKey((Object)blockEntity.getBook().m_41720_()), EMPTY_BOOK_DATA);
            poseStack.m_85836_();
            poseStack.m_85837_(0.5, 1.0625, 0.5);
            float f = ((Direction)blockstate.m_61143_((Property)LecternBlock.f_54465_)).m_122427_().m_122435_();
            poseStack.m_252781_(Axis.f_252436_.m_252977_(-f));
            poseStack.m_252781_(Axis.f_252403_.m_252977_(67.5f));
            poseStack.m_85837_(0.0, -0.125, 0.0);
            this.bookModel.m_102292_(0.0f, 0.1f, 0.9f, 1.2f);
            int bindingR = (bookData.getBindingColor() & 0xFF0000) >> 16;
            int bindingG = (bookData.getBindingColor() & 0xFF00) >> 8;
            int bindingB = bookData.getBindingColor() & 0xFF;
            int pageR = (bookData.getPageColor() & 0xFF0000) >> 16;
            int pageG = (bookData.getPageColor() & 0xFF00) >> 8;
            int pageB = bookData.getPageColor() & 0xFF;
            VertexConsumer pages = bufferSource.m_6299_(RenderType.m_110458_((ResourceLocation)BOOK_PAGE_TEXTURE));
            this.bookModel.m_102316_(poseStack, pages, i, j, (float)pageR / 255.0f, (float)pageG / 255.0f, (float)pageB / 255.0f, 1.0f);
            VertexConsumer binding = bufferSource.m_6299_(RenderType.m_110458_((ResourceLocation)BOOK_BINDING_TEXTURE));
            this.bookModel.m_102316_(poseStack, binding, i, j, (float)bindingR / 255.0f, (float)bindingG / 255.0f, (float)bindingB / 255.0f, 1.0f);
            poseStack.m_85849_();
        }
    }
}

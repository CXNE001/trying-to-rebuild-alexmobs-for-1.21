/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.CrashReport
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.ReportedException
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.ClientProxy;
import com.github.alexthe666.alexsmobs.client.model.ModelKangaroo;
import com.github.alexthe666.alexsmobs.client.render.RenderKangaroo;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;

public class LayerKangarooBaby
extends RenderLayer<EntityKangaroo, ModelKangaroo> {
    public LayerKangarooBaby(RenderKangaroo render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityKangaroo roo, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (roo.m_20160_() && !roo.m_6162_()) {
            for (Entity passenger : roo.m_20197_()) {
                float riderRot = passenger.f_19859_ + (passenger.m_146908_() - passenger.f_19859_) * partialTicks;
                EntityRenderer render = Minecraft.m_91087_().m_91290_().m_114382_(passenger);
                EntityModel modelBase = null;
                if (render instanceof LivingEntityRenderer) {
                    modelBase = ((LivingEntityRenderer)render).m_7200_();
                }
                if (modelBase == null) continue;
                ClientProxy.currentUnrenderedEntities.remove(passenger.m_20148_());
                matrixStackIn.m_85836_();
                this.translateToPouch(matrixStackIn);
                matrixStackIn.m_252880_(0.0f, 1.12f, -0.3f);
                ModelKangaroo.renderOnlyHead = true;
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(180.0f));
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(riderRot + 180.0f));
                this.renderEntity(passenger, 0.0, 0.0, 0.0, 0.0f, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                ModelKangaroo.renderOnlyHead = false;
                matrixStackIn.m_85849_();
                ClientProxy.currentUnrenderedEntities.add(passenger.m_20148_());
            }
        }
    }

    public <E extends Entity> void renderEntity(E entityIn, double x, double y, double z, float yaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLight) {
        block4: {
            EntityRenderer render = null;
            EntityRenderDispatcher manager = Minecraft.m_91087_().m_91290_();
            try {
                render = manager.m_114382_(entityIn);
                if (render == null) break block4;
                try {
                    render.m_7392_(entityIn, yaw, partialTicks, matrixStack, bufferIn, packedLight);
                }
                catch (Throwable throwable1) {
                    throw new ReportedException(CrashReport.m_127521_((Throwable)throwable1, (String)"Rendering entity in world"));
                }
            }
            catch (Throwable throwable3) {
                CrashReport crashreport = CrashReport.m_127521_((Throwable)throwable3, (String)"Rendering entity in world");
                CrashReportCategory crashreportcategory = crashreport.m_127514_("Entity being rendered");
                entityIn.m_7976_(crashreportcategory);
                CrashReportCategory crashreportcategory1 = crashreport.m_127514_("Renderer details");
                crashreportcategory1.m_128159_("Assigned renderer", (Object)render);
                crashreportcategory1.m_128159_("Rotation", (Object)Float.valueOf(yaw));
                crashreportcategory1.m_128159_("Delta", (Object)Float.valueOf(partialTicks));
                throw new ReportedException(crashreport);
            }
        }
    }

    protected void translateToPouch(PoseStack matrixStack) {
        ((ModelKangaroo)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelKangaroo)this.m_117386_()).body.translateAndRotate(matrixStack);
    }
}

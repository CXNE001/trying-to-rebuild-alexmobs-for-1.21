/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.BackupConfirmScreen
 *  net.minecraft.client.gui.screens.ConfirmScreen
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.gui.screens.SkinCustomizationScreen
 *  net.minecraft.client.gui.screens.TitleScreen
 *  net.minecraft.client.renderer.ShaderInstance
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderers
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.player.PlayerModelPart
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.RegisterShadersEvent
 *  net.minecraftforge.client.event.RenderLevelStageEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderTooltipEvent$Color
 *  net.minecraftforge.client.event.ScreenEvent$Init
 *  net.minecraftforge.client.event.ScreenEvent$KeyPressed
 *  net.minecraftforge.client.event.ScreenEvent$Opening
 *  net.minecraftforge.client.event.ScreenEvent$Render
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$Type
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.citadel;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.CitadelConstants;
import com.github.alexthe666.citadel.ServerProxy;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.CitadelItemRenderProperties;
import com.github.alexthe666.citadel.client.event.EventRenderSplashText;
import com.github.alexthe666.citadel.client.game.Tetris;
import com.github.alexthe666.citadel.client.gui.GuiCitadelBook;
import com.github.alexthe666.citadel.client.gui.GuiCitadelCapesConfig;
import com.github.alexthe666.citadel.client.gui.GuiCitadelPatreonConfig;
import com.github.alexthe666.citadel.client.model.TabulaModel;
import com.github.alexthe666.citadel.client.model.TabulaModelHandler;
import com.github.alexthe666.citadel.client.render.CitadelLecternRenderer;
import com.github.alexthe666.citadel.client.render.pathfinding.WorldEventContext;
import com.github.alexthe666.citadel.client.rewards.CitadelCapes;
import com.github.alexthe666.citadel.client.rewards.CitadelPatreonRenderer;
import com.github.alexthe666.citadel.client.rewards.SpaceStationPatreonRenderer;
import com.github.alexthe666.citadel.client.shader.CitadelInternalShaders;
import com.github.alexthe666.citadel.client.shader.PostEffectRegistry;
import com.github.alexthe666.citadel.client.tick.ClientTickRateTracker;
import com.github.alexthe666.citadel.config.ServerConfig;
import com.github.alexthe666.citadel.item.ItemWithHoverAnimation;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.Pathfinding;
import com.github.alexthe666.citadel.server.event.EventChangeEntityTickRate;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.BackupConfirmScreen;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.SkinCustomizationScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class ClientProxy
extends ServerProxy {
    public static TabulaModel CITADEL_MODEL;
    public static boolean hideFollower;
    private final Map<ItemStack, Float> prevMouseOverProgresses = new HashMap<ItemStack, Float>();
    private final Map<ItemStack, Float> mouseOverProgresses = new HashMap<ItemStack, Float>();
    private ItemStack lastHoveredItem = null;
    private Tetris aprilFoolsTetrisGame = null;
    public static final ResourceLocation RAINBOW_AURA_POST_SHADER;

    @Override
    public void onClientInit() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        try {
            CITADEL_MODEL = new TabulaModel(TabulaModelHandler.INSTANCE.loadTabulaModel("/assets/citadel/models/citadel_model"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        bus.addListener(this::registerShaders);
        BlockEntityRenderers.m_173590_((BlockEntityType)((BlockEntityType)Citadel.LECTERN_BE.get()), CitadelLecternRenderer::new);
        CitadelPatreonRenderer.register("citadel", new SpaceStationPatreonRenderer(new ResourceLocation("citadel:patreon_space_station"), new int[0]));
        CitadelPatreonRenderer.register("citadel_red", new SpaceStationPatreonRenderer(new ResourceLocation("citadel:patreon_space_station_red"), new int[]{11685960, 10306880, 8009265, 7417898}));
        CitadelPatreonRenderer.register("citadel_gray", new SpaceStationPatreonRenderer(new ResourceLocation("citadel:patreon_space_station_gray"), new int[]{0xA0A0A0, 0x888888, 0x646464, 0x575757}));
        if (CitadelConstants.debugShaders()) {
            PostEffectRegistry.registerEffect(RAINBOW_AURA_POST_SHADER);
        }
    }

    @SubscribeEvent
    public void screenOpen(ScreenEvent.Init event) {
        if (event.getScreen() instanceof SkinCustomizationScreen && Minecraft.m_91087_().f_91074_ != null) {
            try {
                String username = Minecraft.m_91087_().f_91074_.m_7755_().getString();
                int height = -20;
                if (Citadel.PATREONS.contains(username)) {
                    Button button1 = Button.m_253074_((Component)Component.m_237115_((String)"citadel.gui.patreon_rewards_option").m_130940_(ChatFormatting.GREEN), p_213080_2_ -> Minecraft.m_91087_().m_91152_((Screen)new GuiCitadelPatreonConfig(event.getScreen(), Minecraft.m_91087_().f_91066_))).m_253046_(200, 20).m_252794_(event.getScreen().f_96543_ / 2 - 100, event.getScreen().f_96544_ / 6 + 150 + height).m_253136_();
                    event.addListener((GuiEventListener)button1);
                    height += 25;
                }
                if (!CitadelCapes.getCapesFor(Minecraft.m_91087_().f_91074_.m_20148_()).isEmpty()) {
                    Button button2 = Button.m_253074_((Component)Component.m_237115_((String)"citadel.gui.capes_option").m_130940_(ChatFormatting.GREEN), p_213080_2_ -> Minecraft.m_91087_().m_91152_((Screen)new GuiCitadelCapesConfig(event.getScreen(), Minecraft.m_91087_().f_91066_))).m_253046_(200, 20).m_252794_(event.getScreen().f_96543_ / 2 - 100, event.getScreen().f_96544_ / 6 + 150 + height).m_253136_();
                    event.addListener((GuiEventListener)button2);
                    height += 25;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void screenRender(ScreenEvent.Render event) {
        if (event.getScreen() instanceof TitleScreen && CitadelConstants.isAprilFools()) {
            if (this.aprilFoolsTetrisGame == null) {
                this.aprilFoolsTetrisGame = new Tetris();
            } else {
                this.aprilFoolsTetrisGame.render((TitleScreen)event.getScreen(), event.getGuiGraphics(), event.getPartialTick());
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void playerRender(RenderPlayerEvent.Post event) {
        PoseStack matrixStackIn = event.getPoseStack();
        String username = event.getEntity().m_7755_().getString();
        if (!event.getEntity().m_36170_(PlayerModelPart.CAPE) || event.isCanceled() || event.getEntity().m_5833_()) {
            return;
        }
        if (Citadel.PATREONS.contains(username)) {
            CitadelPatreonRenderer renderer;
            String rendererName;
            CompoundTag tag = CitadelEntityData.getOrCreateCitadelTag((LivingEntity)event.getEntity());
            String string = rendererName = tag.m_128441_("CitadelFollowerType") ? tag.m_128461_("CitadelFollowerType") : "citadel";
            if (!rendererName.equals("none") && !hideFollower && (renderer = CitadelPatreonRenderer.get(rendererName)) != null) {
                float distance = tag.m_128441_("CitadelRotateDistance") ? tag.m_128457_("CitadelRotateDistance") : 2.0f;
                float speed = tag.m_128441_("CitadelRotateSpeed") ? tag.m_128457_("CitadelRotateSpeed") : 1.0f;
                float height = tag.m_128441_("CitadelRotateHeight") ? tag.m_128457_("CitadelRotateHeight") : 1.0f;
                renderer.render(matrixStackIn, event.getMultiBufferSource(), event.getPackedLight(), event.getPartialTick(), (LivingEntity)event.getEntity(), distance, speed, height);
            }
        }
    }

    @SubscribeEvent
    public void renderWorldLastEvent(RenderLevelStageEvent event) {
        if (Pathfinding.isDebug()) {
            WorldEventContext.INSTANCE.renderWorldLastEvent(event);
        }
    }

    private void registerShaders(RegisterShadersEvent e) {
        try {
            e.registerShader(new ShaderInstance(e.getResourceProvider(), new ResourceLocation("citadel:rendertype_rainbow_aura"), DefaultVertexFormat.f_85818_), CitadelInternalShaders::setRenderTypeRainbowAura);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onOpenGui(ScreenEvent.Opening event) {
        if (ServerConfig.skipWarnings) {
            try {
                Object name;
                Screen screen = event.getScreen();
                if (screen instanceof BackupConfirmScreen) {
                    BackupConfirmScreen confirmBackupScreen = (BackupConfirmScreen)screen;
                    name = "";
                    MutableComponent title = Component.m_237115_((String)"selectWorld.backupQuestion.experimental");
                    if (confirmBackupScreen.m_96636_().equals(title)) {
                        confirmBackupScreen.f_95536_.m_95565_(false, true);
                    }
                }
                if ((name = event.getScreen()) instanceof ConfirmScreen) {
                    ConfirmScreen confirmScreen = (ConfirmScreen)name;
                    MutableComponent title = Component.m_237115_((String)"selectWorld.backupQuestion.experimental");
                    String name2 = "";
                    if (confirmScreen.m_96636_().equals(title)) {
                        confirmScreen.f_95649_.accept(true);
                    }
                }
            }
            catch (Exception e) {
                Citadel.LOGGER.warn("Citadel couldn't skip world loadings");
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void renderSplashTextBefore(EventRenderSplashText.Pre event) {
        if (CitadelConstants.isAprilFools() && this.aprilFoolsTetrisGame != null) {
            event.setResult(Event.Result.ALLOW);
            float hue = (float)(System.currentTimeMillis() % 6000L) / 6000.0f;
            event.getGuiGraphics().m_280168_().m_252781_(Axis.f_252403_.m_252977_((float)Math.sin((double)hue * Math.PI) * 360.0f));
            if (!this.aprilFoolsTetrisGame.isStarted()) {
                event.setSplashText("Psst... press 'T' ;)");
            } else {
                event.setSplashText("");
            }
            int rainbow = Color.HSBtoRGB(hue, 0.6f, 1.0f);
            event.setSplashTextColor(rainbow);
        }
    }

    @SubscribeEvent
    public void onKeyPressed(ScreenEvent.KeyPressed event) {
        if (Minecraft.m_91087_().f_91080_ instanceof TitleScreen && this.aprilFoolsTetrisGame != null && this.aprilFoolsTetrisGame.isStarted() && (event.getKeyCode() == 263 || event.getKeyCode() == 262 || event.getKeyCode() == 264 || event.getKeyCode() == 265)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START && !this.isGamePaused() && Minecraft.m_91087_().m_91396_() && Minecraft.m_91087_().f_91073_ != null && Minecraft.m_91087_().f_91074_ != null) {
            ClientTickRateTracker.getForClient(Minecraft.m_91087_()).masterTick();
            this.tickMouseOverAnimations();
        }
        if (event.type == TickEvent.Type.CLIENT && event.phase == TickEvent.Phase.START && !this.isGamePaused() && CitadelConstants.isAprilFools() && this.aprilFoolsTetrisGame != null) {
            if (Minecraft.m_91087_().f_91080_ instanceof TitleScreen) {
                this.aprilFoolsTetrisGame.tick();
            } else {
                this.aprilFoolsTetrisGame.reset();
            }
        }
    }

    private void tickMouseOverAnimations() {
        this.prevMouseOverProgresses.putAll(this.mouseOverProgresses);
        if (this.lastHoveredItem != null) {
            float prev = this.mouseOverProgresses.getOrDefault(this.lastHoveredItem, Float.valueOf(0.0f)).floatValue();
            float maxTime = 5.0f;
            Item item = this.lastHoveredItem.m_41720_();
            if (item instanceof ItemWithHoverAnimation) {
                ItemWithHoverAnimation hoverOver = (ItemWithHoverAnimation)item;
                maxTime = hoverOver.getMaxHoverOverTime(this.lastHoveredItem);
            }
            if (prev < maxTime) {
                this.mouseOverProgresses.put(this.lastHoveredItem, Float.valueOf(prev + 1.0f));
            }
        }
        if (!this.mouseOverProgresses.isEmpty()) {
            Iterator<Map.Entry<ItemStack, Float>> it = this.mouseOverProgresses.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ItemStack, Float> next = it.next();
                float progress = next.getValue().floatValue();
                if (this.lastHoveredItem != null && next.getKey() == this.lastHoveredItem) continue;
                if (progress == 0.0f) {
                    it.remove();
                    continue;
                }
                next.setValue(Float.valueOf(progress - 1.0f));
            }
        }
        this.lastHoveredItem = null;
    }

    @SubscribeEvent
    public void renderTooltipColor(RenderTooltipEvent.Color event) {
        ItemWithHoverAnimation hoverOver;
        Item item = event.getItemStack().m_41720_();
        this.lastHoveredItem = item instanceof ItemWithHoverAnimation && (hoverOver = (ItemWithHoverAnimation)item).canHoverOver(event.getItemStack()) ? event.getItemStack() : null;
    }

    @Override
    public float getMouseOverProgress(ItemStack itemStack) {
        float prev = this.prevMouseOverProgresses.getOrDefault(itemStack, Float.valueOf(0.0f)).floatValue();
        float current = this.mouseOverProgresses.getOrDefault(itemStack, Float.valueOf(0.0f)).floatValue();
        float lerped = prev + (current - prev) * Minecraft.m_91087_().m_91296_();
        float maxTime = 5.0f;
        Item item = itemStack.m_41720_();
        if (item instanceof ItemWithHoverAnimation) {
            ItemWithHoverAnimation hoverOver = (ItemWithHoverAnimation)item;
            maxTime = hoverOver.getMaxHoverOverTime(itemStack);
        }
        return lerped / maxTime;
    }

    @Override
    public void handleAnimationPacket(int entityId, int index) {
        IAnimatedEntity entity;
        if (Minecraft.m_91087_().f_91073_ != null && (entity = (IAnimatedEntity)Minecraft.m_91087_().f_91073_.m_6815_(entityId)) != null) {
            if (index == -1) {
                entity.setAnimation(IAnimatedEntity.NO_ANIMATION);
            } else {
                entity.setAnimation(entity.getAnimations()[index]);
            }
            entity.setAnimationTick(0);
        }
    }

    @Override
    public void handlePropertiesPacket(String propertyID, CompoundTag compound, int entityID) {
        if (compound == null || Minecraft.m_91087_().f_91073_ == null) {
            return;
        }
        Entity entity = Minecraft.m_91087_().f_91073_.m_6815_(entityID);
        if ((propertyID.equals("CitadelPatreonConfig") || propertyID.equals("CitadelTagUpdate")) && entity instanceof LivingEntity) {
            CitadelEntityData.setCitadelTag((LivingEntity)entity, compound);
        }
    }

    @Override
    public void handleClientTickRatePacket(CompoundTag compound) {
        ClientTickRateTracker.getForClient(Minecraft.m_91087_()).syncFromServer(compound);
    }

    @Override
    public Object getISTERProperties() {
        return new CitadelItemRenderProperties();
    }

    @Override
    public void openBookGUI(ItemStack book) {
        Minecraft.m_91087_().m_91152_((Screen)new GuiCitadelBook(book));
    }

    @Override
    public boolean isGamePaused() {
        return Minecraft.m_91087_().m_91104_();
    }

    @Override
    public Player getClientSidePlayer() {
        return Minecraft.m_91087_().f_91074_;
    }

    @Override
    public boolean canEntityTickClient(Level level, Entity entity) {
        ClientTickRateTracker tracker = ClientTickRateTracker.getForClient(Minecraft.m_91087_());
        if (tracker.isTickingHandled(entity)) {
            return false;
        }
        if (!tracker.hasNormalTickRate(entity)) {
            EventChangeEntityTickRate event = new EventChangeEntityTickRate(entity, tracker.getEntityTickLengthModifier(entity));
            MinecraftForge.EVENT_BUS.post((Event)event);
            if (event.isCanceled()) {
                return true;
            }
            tracker.addTickBlockedEntity(entity);
            return false;
        }
        return true;
    }

    @SubscribeEvent
    public void postRenderStage(RenderLevelStageEvent event) {
    }

    @Override
    @Nullable
    public MinecraftServer getMinecraftServer() {
        return null;
    }

    static {
        hideFollower = false;
        RAINBOW_AURA_POST_SHADER = new ResourceLocation("citadel:shaders/post/rainbow_aura.json");
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.entity.BlockEntityType$Builder
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.tileentity;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityCapsid;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchor;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchorWinch;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateDoor;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateFlag;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateShipWheel;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityLeafcutterAnthill;
import com.github.alexthe666.alexsmobs.tileentity.TileEntitySculkBoomer;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityTerrapinEgg;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityTransmutationTable;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityVoidWormBeak;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid="alexsmobs", bus=Mod.EventBusSubscriber.Bus.MOD)
public class AMTileEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BLOCK_ENTITY_TYPES, (String)"alexsmobs");
    public static final RegistryObject<BlockEntityType<TileEntityLeafcutterAnthill>> LEAFCUTTER_ANTHILL = DEF_REG.register("leafcutter_anthill_te", () -> BlockEntityType.Builder.m_155273_(TileEntityLeafcutterAnthill::new, (Block[])new Block[]{(Block)AMBlockRegistry.LEAFCUTTER_ANTHILL.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<TileEntityCapsid>> CAPSID = DEF_REG.register("capsid_te", () -> BlockEntityType.Builder.m_155273_(TileEntityCapsid::new, (Block[])new Block[]{(Block)AMBlockRegistry.CAPSID.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<TileEntityVoidWormBeak>> VOID_WORM_BEAK = DEF_REG.register("void_worm_beak_te", () -> BlockEntityType.Builder.m_155273_(TileEntityVoidWormBeak::new, (Block[])new Block[]{(Block)AMBlockRegistry.VOID_WORM_BEAK.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<TileEntityTerrapinEgg>> TERRAPIN_EGG = DEF_REG.register("terrapin_egg_te", () -> BlockEntityType.Builder.m_155273_(TileEntityTerrapinEgg::new, (Block[])new Block[]{(Block)AMBlockRegistry.TERRAPIN_EGG.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<TileEntityTransmutationTable>> TRANSMUTATION_TABLE = DEF_REG.register("transmutation_table", () -> BlockEntityType.Builder.m_155273_(TileEntityTransmutationTable::new, (Block[])new Block[]{(Block)AMBlockRegistry.TRANSMUTATION_TABLE.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<TileEntitySculkBoomer>> SCULK_BOOMER = DEF_REG.register("sculk_boomer", () -> BlockEntityType.Builder.m_155273_(TileEntitySculkBoomer::new, (Block[])new Block[]{(Block)AMBlockRegistry.SCULK_BOOMER.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<TileEntityEndPirateDoor>> END_PIRATE_DOOR = null;
    public static final RegistryObject<BlockEntityType<TileEntityEndPirateAnchor>> END_PIRATE_ANCHOR = null;
    public static final RegistryObject<BlockEntityType<TileEntityEndPirateAnchorWinch>> END_PIRATE_ANCHOR_WINCH = null;
    public static final RegistryObject<BlockEntityType<TileEntityEndPirateShipWheel>> END_PIRATE_SHIP_WHEEL = null;
    public static final RegistryObject<BlockEntityType<TileEntityEndPirateFlag>> END_PIRATE_FLAG = null;
}

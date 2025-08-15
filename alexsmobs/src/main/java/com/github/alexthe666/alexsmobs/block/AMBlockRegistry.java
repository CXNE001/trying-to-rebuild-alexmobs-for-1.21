/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.SandBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.block.BlockBananaPeel;
import com.github.alexthe666.alexsmobs.block.BlockBananaSlugSlime;
import com.github.alexthe666.alexsmobs.block.BlockBisonCarpet;
import com.github.alexthe666.alexsmobs.block.BlockCapsid;
import com.github.alexthe666.alexsmobs.block.BlockCrystalizedMucus;
import com.github.alexthe666.alexsmobs.block.BlockEnderResidue;
import com.github.alexthe666.alexsmobs.block.BlockGustmaker;
import com.github.alexthe666.alexsmobs.block.BlockHummingbirdFeeder;
import com.github.alexthe666.alexsmobs.block.BlockLeafcutterAntChamber;
import com.github.alexthe666.alexsmobs.block.BlockLeafcutterAnthill;
import com.github.alexthe666.alexsmobs.block.BlockRainbowGlass;
import com.github.alexthe666.alexsmobs.block.BlockReptileEgg;
import com.github.alexthe666.alexsmobs.block.BlockSculkBoomer;
import com.github.alexthe666.alexsmobs.block.BlockSkunkSpray;
import com.github.alexthe666.alexsmobs.block.BlockTerrapinEgg;
import com.github.alexthe666.alexsmobs.block.BlockTransmutationTable;
import com.github.alexthe666.alexsmobs.block.BlockTriopsEggs;
import com.github.alexthe666.alexsmobs.block.BlockVoidWormBeak;
import com.github.alexthe666.alexsmobs.block.BlockVoidWormEffigy;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.item.AMBlockItem;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.BlockItemAMRender;
import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AMBlockRegistry {
    public static final BlockBehaviour.Properties PURPUR_PLANKS_PROPERTIES = BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283765_).m_60913_(0.5f, 1.0f).m_60918_(SoundType.f_56736_);
    public static final DeferredRegister<Block> DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BLOCKS, (String)"alexsmobs");
    public static final RegistryObject<Block> BANANA_PEEL = AMBlockRegistry.registerBlockAndItem("banana_peel", () -> new BlockBananaPeel());
    public static final RegistryObject<Block> HUMMINGBIRD_FEEDER = AMBlockRegistry.registerBlockAndItem("hummingbird_feeder", () -> new BlockHummingbirdFeeder());
    public static final RegistryObject<Block> CROCODILE_EGG = AMBlockRegistry.registerBlockAndItem("crocodile_egg", () -> new BlockReptileEgg(AMEntityRegistry.CROCODILE));
    public static final RegistryObject<Block> GUSTMAKER = AMBlockRegistry.registerBlockAndItem("gustmaker", () -> new BlockGustmaker());
    public static final RegistryObject<Block> STRADDLITE_BLOCK = AMBlockRegistry.registerBlockAndItem("straddlite_block", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283906_).m_60999_().m_60913_(1.0f, 1200.0f).m_60918_(SoundType.f_56726_)), new Item.Properties().m_41486_(), false);
    public static final RegistryObject<Block> PLATYPUS_EGG = AMBlockRegistry.registerBlockAndItem("platypus_egg", () -> new BlockReptileEgg(AMEntityRegistry.PLATYPUS));
    public static final RegistryObject<Block> LEAFCUTTER_ANTHILL = AMBlockRegistry.registerBlockAndItem("leafcutter_anthill", () -> new BlockLeafcutterAnthill());
    public static final RegistryObject<Block> LEAFCUTTER_ANT_CHAMBER = AMBlockRegistry.registerBlockAndItem("leafcutter_ant_chamber", () -> new BlockLeafcutterAntChamber());
    public static final RegistryObject<Block> CAPSID = AMBlockRegistry.registerBlockAndItem("capsid", () -> new BlockCapsid());
    public static final RegistryObject<Block> VOID_WORM_BEAK = AMBlockRegistry.registerBlockAndItem("void_worm_beak", () -> new BlockVoidWormBeak());
    public static final RegistryObject<Block> VOID_WORM_EFFIGY = AMBlockRegistry.registerBlockAndItem("void_worm_effigy", () -> new BlockVoidWormEffigy());
    public static final RegistryObject<Block> TERRAPIN_EGG = AMBlockRegistry.registerBlockAndItem("terrapin_egg", () -> new BlockTerrapinEgg());
    public static final RegistryObject<Block> RAINBOW_GLASS = AMBlockRegistry.registerBlockAndItem("rainbow_glass", () -> new BlockRainbowGlass());
    public static final RegistryObject<Block> BISON_FUR_BLOCK = AMBlockRegistry.registerBlockAndItem("bison_fur_block", () -> new Block(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283748_).m_60913_(0.6f, 1.0f).m_60918_(SoundType.f_56745_)));
    public static final RegistryObject<Block> BISON_CARPET = AMBlockRegistry.registerBlockAndItem("bison_carpet", () -> new BlockBisonCarpet());
    public static final RegistryObject<Block> SAND_CIRCLE = AMBlockRegistry.registerBlockAndItem("sand_circle", () -> new SandBlock(14406560, BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_49992_)), new Item.Properties(), false);
    public static final RegistryObject<Block> RED_SAND_CIRCLE = AMBlockRegistry.registerBlockAndItem("red_sand_circle", () -> new SandBlock(11098145, BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_49993_)), new Item.Properties(), false);
    public static final RegistryObject<Block> ENDER_RESIDUE = AMBlockRegistry.registerBlockAndItem("ender_residue", () -> new BlockEnderResidue());
    public static final RegistryObject<Block> TRANSMUTATION_TABLE = AMBlockRegistry.registerBlockAndItem("transmutation_table", () -> new BlockTransmutationTable(), new Item.Properties().m_41497_(Rarity.EPIC).m_41486_(), true);
    public static final RegistryObject<Block> SCULK_BOOMER = AMBlockRegistry.registerBlockAndItem("sculk_boomer", () -> new BlockSculkBoomer());
    public static final RegistryObject<Block> SKUNK_SPRAY = DEF_REG.register("skunk_spray", () -> new BlockSkunkSpray());
    public static final RegistryObject<Block> BANANA_SLUG_SLIME_BLOCK = AMBlockRegistry.registerBlockAndItem("banana_slug_slime_block", () -> new BlockBananaSlugSlime());
    public static final RegistryObject<Block> CRYSTALIZED_BANANA_SLUG_MUCUS = AMBlockRegistry.registerBlockAndItem("crystalized_banana_slug_mucus", () -> new BlockCrystalizedMucus());
    public static final RegistryObject<Block> CAIMAN_EGG = AMBlockRegistry.registerBlockAndItem("caiman_egg", () -> new BlockReptileEgg(AMEntityRegistry.CAIMAN));
    public static final RegistryObject<Block> TRIOPS_EGGS = AMBlockRegistry.registerBlockAndItem("triops_eggs", () -> new BlockTriopsEggs());

    public static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block) {
        return AMBlockRegistry.registerBlockAndItem(name, block, new Item.Properties(), false);
    }

    public static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block, Item.Properties blockItemProps, boolean specialRender) {
        RegistryObject blockObj = DEF_REG.register(name, block);
        AMItemRegistry.DEF_REG.register(name, () -> specialRender ? new BlockItemAMRender((RegistryObject<Block>)blockObj, blockItemProps) : new AMBlockItem((RegistryObject<Block>)blockObj, blockItemProps));
        return blockObj;
    }
}

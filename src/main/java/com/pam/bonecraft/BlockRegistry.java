package com.pam.bonecraft;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;

public final class BlockRegistry {

	public static Block pamFossil;
	public static ItemBlock pamfossilItemBlock;

    private static boolean initialized = false;

    public static void initBlockRegistry() {

        registerFossil();
        initialized = true;
    }

   

    private static void registerFossil() {
    	pamFossil = new BlockPamFossil().setHardness(3.0F).setResistance(5.0F);
        pamfossilItemBlock = new ItemBlock(pamFossil);
        ItemRegistry.items.put(BlockPamFossil.registryName, pamfossilItemBlock);
        registerBlock(BlockPamFossil.registryName, pamfossilItemBlock, pamFossil);
    }

   
    public static void registerBlock(String registerName, ItemBlock itemBlock, Block block) {
        block.setRegistryName(registerName);
        block.setUnlocalizedName(registerName);
        block.setCreativeTab(bonecraft.tabBonecraft);
        GameRegistry.register(block);

        itemBlock.setRegistryName(registerName);
        itemBlock.setUnlocalizedName(registerName);
        GameRegistry.register(itemBlock);
    }

    public static void registerBlock(String registerName, Block block) {
        final ItemBlock itemBlock = new ItemBlock(block);
        registerBlock(registerName, itemBlock, block);
    }
}

package com.pam.desertcraft;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        BlockRegistry.initBlockRegistry();
        ItemRegistry.registerItems();
        RecipeRegistry.registerRecipes();
        onBlocksAndItemsLoaded();
    }

    public void init(FMLInitializationEvent e) {
    	GameRegistry.registerWorldGenerator(new WorldGenDesertPlant(), 0);
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public void onBlocksAndItemsLoaded() {

        PacketHandler.init();

    }
}

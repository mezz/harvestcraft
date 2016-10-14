package com.pam.desertcraft;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;

import com.pam.harvestcraft.blocks.blocks.BlockBaseGarden;

public final class BlockRegistry {

	public static Block pamGlasssteel;
	public static ItemBlock pamglasssteelItemBlock;
	public static Block pamdesertplant;
	public static ItemBlock pamdesertplantItemBlock;
	
	// Desert plant blocks
    public static final HashMap<String, BlockPamDesertPlant> gardens = new HashMap<>();
    public static final String desertplant1 = "desertplant1";
    public static final String desertplant2 = "desertplant2";
    public static final String desertplant3 = "desertplant3";
    public static final String desertplant4 = "desertplant4";
    public static final String desertplant5 = "desertplant5";
    public static final String desertplant6 = "desertplant6";
    public static final String desertplant7 = "desertplant7";
    public static final String desertplant8 = "desertplant8";
    public static final String desertplant9 = "desertplant9";
    public static final String desertplant10 = "desertplant10";
    public static final String desertplant11 = "desertplant11";
    public static final String desertplant12 = "desertplant12";
    public static final String desertplant13 = "desertplant13";
    public static final String desertplant14 = "desertplant14";
    public static final String desertplant15 = "desertplant15";
    public static final String desertplant16 = "desertplant16";

    private static boolean initialized = false;

    public static void initBlockRegistry() {

    	registerGlasssteel();
    	registerDesertplant();
        initialized = true;
    }

    public static BlockPamDesertPlant getGarden(String gardenName) {
        if (!initialized) {
            FMLLog.bigWarning("BlockRegistry has not been initialized yet.");
            return null;
        }

        if (!gardens.containsKey(gardenName)) {
            FMLLog.bigWarning("Garden %s is not registered.", gardenName);
            return null;
        }

        return gardens.get(gardenName);
    }

    private static void registerGlasssteel() {
    	pamGlasssteel = new BlockPamGlassSteel(Material.GLASS, false).setHardness(50.0F).setResistance(2000.0F);
    	pamglasssteelItemBlock = new ItemBlock(pamGlasssteel);
        ItemRegistry.items.put(BlockPamGlassSteel.registryName, pamglasssteelItemBlock);
        registerBlock(BlockPamGlassSteel.registryName, pamglasssteelItemBlock, pamGlasssteel);
    }
    
    private static void registerDesertplant() {
    	addGarden(desertplant1, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant2, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant3, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant4, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant5, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant6, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant7, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant8, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant9, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant10, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant11, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant12, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant13, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant14, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant15, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertplant16, BlockPamDesertPlant.Region.DESERT);
    }

    private static void addGarden(String gardenName, BlockPamDesertPlant.Region region) {
        final BlockPamDesertPlant garden = new BlockPamDesertPlant(gardenName, region);

        gardens.put(gardenName, garden);
    }
    public static void registerBlock(String registerName, ItemBlock itemBlock, Block block) {
        block.setRegistryName(registerName);
        block.setUnlocalizedName(registerName);
        block.setCreativeTab(desertcraft.tabDesertcraft);
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

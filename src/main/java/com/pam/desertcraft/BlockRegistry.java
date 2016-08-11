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
	public static Block pamdesertPlant;
	public static ItemBlock pamdesertplantItemBlock;
	
	// Desert Plant blocks
    public static final HashMap<String, BlockPamDesertPlant> gardens = new HashMap<>();
    public static final String desertPlant1 = "desertPlant1";
    public static final String desertPlant2 = "desertPlant2";
    public static final String desertPlant3 = "desertPlant3";
    public static final String desertPlant4 = "desertPlant4";
    public static final String desertPlant5 = "desertPlant5";
    public static final String desertPlant6 = "desertPlant6";
    public static final String desertPlant7 = "desertPlant7";
    public static final String desertPlant8 = "desertPlant8";
    public static final String desertPlant9 = "desertPlant9";
    public static final String desertPlant10 = "desertPlant10";
    public static final String desertPlant11 = "desertPlant11";
    public static final String desertPlant12 = "desertPlant12";
    public static final String desertPlant13 = "desertPlant13";
    public static final String desertPlant14 = "desertPlant14";
    public static final String desertPlant15 = "desertPlant15";
    public static final String desertPlant16 = "desertPlant16";

    private static boolean initialized = false;

    public static void initBlockRegistry() {

    	registerGlasssteel();
    	registerDesertPlant();
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
    
    private static void registerDesertPlant() {
    	addGarden(desertPlant1, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant2, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant3, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant4, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant5, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant6, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant7, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant8, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant9, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant10, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant11, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant12, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant13, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant14, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant15, BlockPamDesertPlant.Region.DESERT);
    	addGarden(desertPlant16, BlockPamDesertPlant.Region.DESERT);
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

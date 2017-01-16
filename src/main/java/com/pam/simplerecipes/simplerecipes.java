package com.pam.simplerecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@SuppressWarnings("unused")
@Mod(modid = simplerecipes.modid, name = "Pam's Simple Recipes", version = simplerecipes.version)
public class simplerecipes
{
  public static final String modid = "simplerecipes";
  public static final String version = "1.11a";
  
  public static int saplingstickamount;
  public static int flintamount;
  public static int rottenfleshamount;
  public static int glassamount;
  public static int pumpkinorangedyeamount;
  public static int flowerorangedyeamount;
  public static int bonestickamount;
  public static int metadataofcactustoplank;
  public static int wheatseedsamount;
  
  public static boolean easysaddlerecipe;
  
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) 
  {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();
			saplingstickamount = cfg.get(Configuration.CATEGORY_GENERAL,"saplingstickamount", 1).getInt();
			flintamount = cfg.get(Configuration.CATEGORY_GENERAL,"flintamount", 1).getInt();
			rottenfleshamount = cfg.get(Configuration.CATEGORY_GENERAL,"rottenfleshamount", 1).getInt();
			glassamount = cfg.get(Configuration.CATEGORY_GENERAL,"glassamount", 4).getInt();
			pumpkinorangedyeamount = cfg.get(Configuration.CATEGORY_GENERAL,"pumpkinorangedyeamount", 8).getInt();
			flowerorangedyeamount = cfg.get(Configuration.CATEGORY_GENERAL,"flowerorangedyeamount", 4).getInt();
			bonestickamount = cfg.get(Configuration.CATEGORY_GENERAL,"bonestickamount", 4).getInt();
			metadataofcactustoplank = cfg.get(Configuration.CATEGORY_GENERAL,"metadataofcactustoplank", 0).getInt();
			wheatseedsamount = cfg.get(Configuration.CATEGORY_GENERAL,"wheatseedsamount", 2).getInt();
			easysaddlerecipe = cfg.get(Configuration.CATEGORY_GENERAL, "easysaddlerecipe", false).getBoolean(false);
		} catch (Exception e) {
			FMLLog.severe(modid, "Pam's Simple Recipes has a problem loading it's configuration");
		} finally {
			cfg.save();
		}
	  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) 
  {
	 
	//Saplings to Stick
    GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, saplingstickamount, 0), new Object[] { new ItemStack(Blocks.SAPLING, 1, OreDictionary.WILDCARD_VALUE) });
    //Gravel to Flint
    GameRegistry.addRecipe(new ItemStack(Items.FLINT, flintamount), new Object[] { "XX ", "XX ", Character.valueOf('X'), Blocks.GRAVEL });
    //Smelt Rotten Flesh for Leather
    GameRegistry.addSmelting(Items.ROTTEN_FLESH, new ItemStack(Items.LEATHER, rottenfleshamount), 0.1F);
    //Smelt Sandstone for Glass
    GameRegistry.addSmelting(Blocks.SANDSTONE, new ItemStack(Blocks.GLASS, glassamount), 0.1F);
    //Pumpkin or Flowers to Orange Dye
    GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, pumpkinorangedyeamount, 14), new Object[] { new ItemStack(Blocks.PUMPKIN, 1, 0), new ItemStack(Blocks.PUMPKIN, 1, 0) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, flowerorangedyeamount, 14), new Object[] { new ItemStack(Blocks.RED_FLOWER, 1, 0), new ItemStack(Blocks.YELLOW_FLOWER, 1, 0) });
    //Bones to Sticks
    GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, bonestickamount, 0), new Object[] { new ItemStack(Items.BONE, 1, 0), new ItemStack(Items.BONE, 1, 0) });
    //Sandstone to Cobblestone
    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new Object[] { new ItemStack(Blocks.SANDSTONE, 1, -1) });
    //Cactus to Planks
    GameRegistry.addShapelessRecipe(new ItemStack(Blocks.PLANKS, 1, metadataofcactustoplank), new Object[] { new ItemStack(Blocks.CACTUS, 1, 0) });
    //Wheat to Seeds
    GameRegistry.addShapelessRecipe(new ItemStack(Items.WHEAT_SEEDS, wheatseedsamount, 0), new Object[] { new ItemStack(Items.WHEAT, 1, 0), new ItemStack(Items.WHEAT, 1, 0) });
    //Name Tag
    GameRegistry.addShapelessRecipe(new ItemStack(Items.NAME_TAG, 1, 0), new Object[] { new ItemStack(Items.FEATHER, 1, 0), new ItemStack(Items.PAPER, 1, 0), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.STRING, 1, 0) });
    //Saddle
    if (easysaddlerecipe)
    {
        GameRegistry.addRecipe(new ItemStack(Items.SADDLE, 1), new Object[] { "XXX", "XXX", "Z Z", Character.valueOf('X'), Items.LEATHER, Character.valueOf('Z'), Items.STRING });
    }
    GameRegistry.addRecipe(new ItemStack(Items.SADDLE, 1), new Object[] { "XXX", "XOX", "Z Z", Character.valueOf('X'), Items.LEATHER, Character.valueOf('O'), Items.DIAMOND, Character.valueOf('Z'), Items.GOLD_INGOT });
    //Horse Armor
    GameRegistry.addShapelessRecipe(new ItemStack(Items.IRON_HORSE_ARMOR, 1, 0), new Object[] { new ItemStack(Blocks.IRON_BLOCK, 1, 0), new ItemStack(Items.LEATHER, 1, 0), new ItemStack(Items.LEATHER, 1, 0), new ItemStack(Items.LEATHER, 1, 0) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1, 0), new Object[] { new ItemStack(Blocks.GOLD_BLOCK, 1, 0), new ItemStack(Items.LEATHER, 1, 0), new ItemStack(Items.LEATHER, 1, 0), new ItemStack(Items.LEATHER, 1, 0) });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1, 0), new Object[] { new ItemStack(Blocks.DIAMOND_BLOCK, 1, 0), new ItemStack(Items.LEATHER, 1, 0), new ItemStack(Items.LEATHER, 1, 0), new ItemStack(Items.LEATHER, 1, 0) });
  }
}


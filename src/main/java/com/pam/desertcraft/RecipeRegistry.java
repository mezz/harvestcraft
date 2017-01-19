package com.pam.desertcraft;


import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class RecipeRegistry {
    public static final HashMap<String, Item> items = new HashMap<String, Item>();

    public static boolean initialized = false;

    public static void registerRecipes() {
    	
    	if(desertcraft.enablecactustools)
    		{
    		registerCactusRecipes();
    		}
    	if(desertcraft.enablesandstonetools)
		{
    	registerSandstoneRecipes();
		}
    	if(desertcraft.enableglasssteeltoolsandblock)
		{
    	registerGlasssteelRecipes();
		}
        initialized = true;
    }

    private static void registerCactusRecipes() {
    	GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusaxeitem, 1), new Object[]
                {
                    "OO ", "OX ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactushoeitem, 1), new Object[]
                {
                    "OO ", " X ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactuspickaxeitem, 1), new Object[]
                {
                    "OOO", " X ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusshovelitem, 1), new Object[]
                {
                    " O ", " X ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactussworditem, 1), new Object[]
                {
                    " O ", " O ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactushelmitem, 1), new Object[]
                {
                    "OOO", "O O", 'O', Blocks.CACTUS
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactuslegsitem, 1), new Object[]
                {
                    "OOO", "O O", "O O", 'O', Blocks.CACTUS
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactuschestitem, 1), new Object[]
                {
                    "O O", "OOO", "OOO", 'O', Blocks.CACTUS
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusbootsitem, 1), new Object[]
                {
                    "O O", "O O", 'O', Blocks.CACTUS
                });
		
		if(desertcraft.enablecactustoplankrecipe)
		{
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.PLANKS, 1, 0), new Object[]
	                {
	                    new ItemStack(Blocks.CACTUS, 1, 0)
	                });
		}
    }
    
    private static void registerSandstoneRecipes() {
    	GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstoneaxeitem, 1), new Object[]
                {
                    "OO ", "OX ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstonehoeitem, 1), new Object[]
                {
                    "OO ", " X ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstonepickaxeitem, 1), new Object[]
                {
                    "OOO", " X ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstoneshovelitem, 1), new Object[]
                {
                    " O ", " X ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstonesworditem, 1), new Object[]
                {
                    " O ", " O ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
    }
    
    private static void registerGlasssteelRecipes() {
    	GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelaxeitem, 1), new Object[]
                {
                    "OO ", "OX ", " X ", 'O', ItemRegistry.glasssteelingotitem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelhoeitem, 1), new Object[]
                {
                    "OO ", " X ", " X ", 'O', ItemRegistry.glasssteelingotitem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelpickaxeitem, 1), new Object[]
                {
                    "OOO", " X ", " X ", 'O', ItemRegistry.glasssteelingotitem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelshovelitem, 1), new Object[]
                {
                    " O ", " X ", " X ", 'O', ItemRegistry.glasssteelingotitem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelsworditem, 1), new Object[]
                {
                    " O ", " O ", " X ", 'O', ItemRegistry.glasssteelingotitem, 'X', Items.STICK
                });
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelingotitem, 4), new Object[]
	                {
					"OOO", "OXO", "OOO", 'O', Blocks.GLASS, 'X', Items.IRON_INGOT
	                });
		GameRegistry.addRecipe(new ItemStack(BlockRegistry.pamGlasssteel, 8), new Object[]
	                {
					"OOO", "O O", "OOO", 'O', ItemRegistry.glasssteelingotitem
	                });
    }

}

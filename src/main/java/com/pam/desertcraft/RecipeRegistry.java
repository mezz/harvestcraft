package com.pam.desertcraft;


import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class RecipeRegistry {
    public static final HashMap<String, Item> items = new HashMap<>();

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
    	GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusaxeItem, 1), new Object[]
                {
                    "OO ", "OX ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactushoeItem, 1), new Object[]
                {
                    "OO ", " X ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactuspickaxeItem, 1), new Object[]
                {
                    "OOO", " X ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusshovelItem, 1), new Object[]
                {
                    " O ", " X ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusswordItem, 1), new Object[]
                {
                    " O ", " O ", " X ", 'O', Blocks.CACTUS, 'X', Items.STICK
                });
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactushelmItem, 1), new Object[]
                {
                    "OOO", "O O", 'O', Blocks.CACTUS
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactuslegsItem, 1), new Object[]
                {
                    "OOO", "O O", "O O", 'O', Blocks.CACTUS
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactuschestItem, 1), new Object[]
                {
                    "O O", "OOO", "OOO", 'O', Blocks.CACTUS
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.cactusbootsItem, 1), new Object[]
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
    	GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstoneaxeItem, 1), new Object[]
                {
                    "OO ", "OX ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstonehoeItem, 1), new Object[]
                {
                    "OO ", " X ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstonepickaxeItem, 1), new Object[]
                {
                    "OOO", " X ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstoneshovelItem, 1), new Object[]
                {
                    " O ", " X ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.sandstoneswordItem, 1), new Object[]
                {
                    " O ", " O ", " X ", 'O', Blocks.SANDSTONE, 'X', Items.STICK
                });
    }
    
    private static void registerGlasssteelRecipes() {
    	GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelaxeItem, 1), new Object[]
                {
                    "OO ", "OX ", " X ", 'O', ItemRegistry.glasssteelingotItem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelhoeItem, 1), new Object[]
                {
                    "OO ", " X ", " X ", 'O', ItemRegistry.glasssteelingotItem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelpickaxeItem, 1), new Object[]
                {
                    "OOO", " X ", " X ", 'O', ItemRegistry.glasssteelingotItem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelshovelItem, 1), new Object[]
                {
                    " O ", " X ", " X ", 'O', ItemRegistry.glasssteelingotItem, 'X', Items.STICK
                });
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelswordItem, 1), new Object[]
                {
                    " O ", " O ", " X ", 'O', ItemRegistry.glasssteelingotItem, 'X', Items.STICK
                });
		
		GameRegistry.addRecipe(new ItemStack(ItemRegistry.glasssteelingotItem, 4), new Object[]
	                {
					"OOO", "OXO", "OOO", 'O', Blocks.GLASS, 'X', Items.IRON_INGOT
	                });
		GameRegistry.addRecipe(new ItemStack(BlockRegistry.pamGlasssteel, 8), new Object[]
	                {
					"OOO", "O O", "OOO", 'O', ItemRegistry.glasssteelingotItem
	                });
    }

}

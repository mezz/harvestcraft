package com.pam.bonecraft;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = "Pam's BoneCraft", version = Reference.VERSION)

public class bonecraft
{
	@Instance(Reference.MODID)
    public static bonecraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	
	
	public static int fossilrarity;

    public static ItemArmor.ArmorMaterial armorBone = EnumHelper.addArmorMaterial("BONE", "bonecraft:bone", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
	
    private static ItemArmor.ArmorMaterial addArmorMaterial(String enumName, String textureName, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness) {
		return EnumHelper.addEnum(ItemArmor.ArmorMaterial.class, enumName, new Class<?>[]{String.class, int.class, int[].class, int.class, SoundEvent.class, float.class}, textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
	}
    
    public static CreativeTabs tabBonecraft = new CreativeTabs("tabBonecraft") 
	{

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.BONE, 1, 0);
		}
	};
    
	  @EventHandler
	  public void preInit(FMLPreInitializationEvent event) 
	  {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();
			fossilrarity = cfg.get(Configuration.CATEGORY_GENERAL,"fossilrarity", 5).getInt();
		} catch (Exception e) {
			FMLLog.severe(Reference.MODID, "Bonecraft has a problem loading it's configuration");
		} finally {
			cfg.save();
		}
		
		proxy.preInit(event);

			
			
			
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.boneaxeitem, 1), new Object[]
	                {
	                    "OO ", "OX ", " X ", 'O', Items.BONE, 'X', Items.STICK
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonehoeitem, 1), new Object[]
	                {
	                    "OO ", " X ", " X ", 'O', Items.BONE, 'X', Items.STICK
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonepickaxeitem, 1), new Object[]
	                {
	                    "OOO", " X ", " X ", 'O', Items.BONE, 'X', Items.STICK
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.boneshovelitem, 1), new Object[]
	                {
	                    " O ", " X ", " X ", 'O', Items.BONE, 'X', Items.STICK
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonesworditem, 1), new Object[]
	                {
	                    " O ", " O ", " X ", 'O', Items.BONE, 'X', Items.STICK
	                });
			
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonehelmitem, 1), new Object[]
	                {
	                    "OOO", "O O", 'O', Items.BONE
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonechestitem, 1), new Object[]
	                {
	                    "O O", "OOO", "OOO", 'O', Items.BONE
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonelegsitem, 1), new Object[]
	                {
	                    "OOO", "O O", "O O", 'O', Items.BONE
	                });
			GameRegistry.addRecipe(new ItemStack(ItemRegistry.bonebootsitem, 1), new Object[]
	                {
	                    "O O", "O O", 'O', Items.BONE
	                });
			
			GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[]
  	                {
  	                    new ItemStack(Items.BONE, 1, 0), new ItemStack(Items.BONE, 1, 0)
  	                });
			
			
			
	  }
	  
	  
	  
	 
	  @EventHandler
	  public void init(FMLInitializationEvent event) 
	  {
		  proxy.init(event);
		  
        
	}
	  
	  @EventHandler
	    public void postInit(FMLPostInitializationEvent event) {
	        proxy.postInit(event);
	    }
}
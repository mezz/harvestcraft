package com.pam.desertcraft;

import java.util.HashMap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = "Pam's DesertCraft", version = Reference.VERSION)

public class desertcraft
{
	@Instance(Reference.MODID)
    public static desertcraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    
    public static int desertplantrarity;
	public static boolean enablecactustools;
	public static boolean enablesandstonetools;
	public static boolean enableglasssteeltoolsandblock;
	public static boolean enablecactustoplankrecipe;
    
    public static CreativeTabs tabDesertcraft = new CreativeTabs("tabDesertcraft") 
	{

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegistry.sandstonepickaxeitem);
		}
	};
    
	  @EventHandler
	  public void preInit(FMLPreInitializationEvent event) 
	  {
		  Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
			try {
				cfg.load();
				desertplantrarity = cfg.get(Configuration.CATEGORY_GENERAL,"desertplantrarity", 2).getInt();
				enablecactustools = cfg.get(Configuration.CATEGORY_GENERAL, "enablecactustools", true).getBoolean(true);
				enablesandstonetools = cfg.get(Configuration.CATEGORY_GENERAL, "enablesandstonetools", true).getBoolean(true);
				enableglasssteeltoolsandblock = cfg.get(Configuration.CATEGORY_GENERAL, "enableglasssteeltoolsandblock", true).getBoolean(true);
				enablecactustoplankrecipe = cfg.get(Configuration.CATEGORY_GENERAL, "enablecactustoplankrecipe", true).getBoolean(true);
			} catch (Exception e) {
				FMLLog.severe(Reference.MODID, "DesertCraft has a problem loading it's configuration");
			} finally {
				cfg.save();
			}
		
		proxy.preInit(event);	
			
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
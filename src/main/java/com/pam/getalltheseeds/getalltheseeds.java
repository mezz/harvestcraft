package com.pam.getalltheseeds;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
@Mod(modid=getalltheseeds.modid, name="Pam's Get all the Seeds!", version=getalltheseeds.version)
public class getalltheseeds
{
  public static final String modid = "getalltheseeds";
  public static final String version = "1.11a";
  static int pumpkinseedRarity;
  static int melonseedRarity;
  static int carrotRarity;
  static int potatoRarity;
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) 
  {
    Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
    try
    {
      cfg.load();
      pumpkinseedRarity = cfg.get("general", "pumpkinseedRarity", 1).getInt();
      melonseedRarity = cfg.get("general", "melonseedRarity", 1).getInt();
      carrotRarity = cfg.get("general", "carrotRarity", 1).getInt();
      potatoRarity = cfg.get("general", "potatoRarity", 1).getInt();
    }
    catch (Exception e)
    {
      FMLLog.severe("getalltheseeds", new Object[] { "Pam's GetAllTheSeeds has a problem loading it's configuration" });
    }
    finally
    {
      cfg.save();
    }
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) 
  {
    MinecraftForge.addGrassSeed(new ItemStack(Items.PUMPKIN_SEEDS, 1, 0), pumpkinseedRarity);
    MinecraftForge.addGrassSeed(new ItemStack(Items.MELON_SEEDS, 1, 0), melonseedRarity);
    MinecraftForge.addGrassSeed(new ItemStack(Items.CARROT, 1, 0), carrotRarity);
    MinecraftForge.addGrassSeed(new ItemStack(Items.POTATO, 1, 0), potatoRarity);
  }
}

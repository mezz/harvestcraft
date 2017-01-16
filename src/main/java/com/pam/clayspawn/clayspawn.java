package com.pam.clayspawn;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("unused")
@Mod(modid = clayspawn.modid, name = "Pam's Clay Spawn", version = clayspawn.version)
public class clayspawn
{
  public static boolean enableClaySpawn;
  public static boolean enableHardenedClaySpawn;
  public static int clayRarity;
  public static int hardenedClayRarity;
  public static final String modid = "clayspawn";
  public static final String version = "1.11a";
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) 
  {
    Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
    try
    {
      cfg.load();
      enableClaySpawn = cfg.get("general", "enableClaySpawn", true).getBoolean(true);
      enableHardenedClaySpawn = cfg.get("general", "enableHardenedClaySpawn", true).getBoolean(true);
      clayRarity = cfg.get("general", "clayRarity", 20).getInt();
      hardenedClayRarity = cfg.get("general", "clayRarity", 20).getInt();
    }
    catch (Exception e)
    {
      FMLLog.severe("clayspawn", new Object[] { "Pam's Clay Spawn has a problem loading it's configuration" });
    }
    finally
    {
      cfg.save();
    }
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) 
  {
	  GameRegistry.registerWorldGenerator(new WorldGenPamClay(), 1);
  }
}


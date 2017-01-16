package com.pam.needmoarsticks;

import net.minecraft.init.Blocks;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@SuppressWarnings("unused")
@Mod(modid=needmoarsticks.modid, name="Pam's Need Moar Sticks!", version=needmoarsticks.version)
public class needmoarsticks
{
  public static final String modid = "needmoarsticks";
  public static final String version = "1.11a";

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) 
  {
    Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
    try
    {
      cfg.load();

    }
    catch (Exception e)
    {
      FMLLog.severe("needmoarsticks", new Object[] { "Pam's Need Moar Sticks has a problem loading it's configuration" });
    }
    finally
    {
      cfg.save();
    }
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) 
  {
	  //Reeds
	  GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[]
                {
                    new ItemStack(Items.REEDS, 1, 0), new ItemStack(Items.REEDS, 1, 0)
                });
	  //Bone
	  GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[]
              {
                  new ItemStack(Items.BONE, 1, 0), new ItemStack(Items.BONE, 1, 0)
              });
	  //Sapling
	GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
			{ 
				new ItemStack(Blocks.SAPLING, 1, OreDictionary.WILDCARD_VALUE) 
			});
	//Fishing Pole
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
				{ 
					new ItemStack(Items.FISHING_ROD, 1, -1) 
				});
		//Carrot on a Stick
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Items.CARROT_ON_A_STICK, 1, -1) 
						});
		//Bow
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Items.BOW, 1, -1) 
						});
				//Arrow
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Items.ARROW, 1, 0) 
						});
				//Torch
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Blocks.TORCH, 1, 0) 
						});
				//Redstone Torch
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Blocks.REDSTONE_TORCH, 1, 0) 
						});
				//Lever
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Blocks.LEVER, 1, 0) 
						});
				//Ladder
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Blocks.LADDER, 1, 0) 
						});
				//Fence
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Blocks.OAK_FENCE, 1, -1) 
						});
				//Fence Gate
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] 
						{ 
							new ItemStack(Blocks.OAK_FENCE_GATE, 1, -1) 
						});
				//Fence
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Blocks.ACACIA_FENCE, 1, -1) 
						});
				//Fence Gate
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] 
						{ 
							new ItemStack(Blocks.ACACIA_FENCE_GATE, 1, -1) 
						});
				//Fence
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Blocks.BIRCH_FENCE, 1, -1) 
						});
				//Fence Gate
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] 
						{ 
							new ItemStack(Blocks.BIRCH_FENCE_GATE, 1, -1) 
						});
				//Fence
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Blocks.SPRUCE_FENCE, 1, -1) 
						});
				//Fence Gate
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] 
						{ 
							new ItemStack(Blocks.SPRUCE_FENCE_GATE, 1, -1) 
						});
				//Fence
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Blocks.JUNGLE_FENCE, 1, -1) 
						});
				//Fence Gate
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] 
						{ 
							new ItemStack(Blocks.JUNGLE_FENCE_GATE, 1, -1) 
						});
				//Fence
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 3, 0), new Object[] 
						{ 
							new ItemStack(Blocks.DARK_OAK_FENCE, 1, -1) 
						});
				//Fence Gate
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 4, 0), new Object[] 
						{ 
							new ItemStack(Blocks.DARK_OAK_FENCE_GATE, 1, -1) 
						});
				//Sign
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Items.SIGN, 1, 0) 
						});
				//Painting
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 8, 0), new Object[] 
						{ 
							new ItemStack(Items.PAINTING, 1, 0) 
						});
				//Item Frame
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 8, 0), new Object[] 
						{ 
							new ItemStack(Items.ITEM_FRAME, 1, 0) 
						});
	//Wooden Tools
	GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
			{ 
				new ItemStack(Items.WOODEN_AXE, 1, -1)
			});
	GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
			{ 
				new ItemStack(Items.WOODEN_HOE, 1, -1)
			});
	GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
			{ 
				new ItemStack(Items.WOODEN_PICKAXE, 1, -1)
			});    
	GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
			{ 
				new ItemStack(Items.WOODEN_SHOVEL, 1, -1)
			}); 
	GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
			{ 
				new ItemStack(Items.WOODEN_SWORD, 1, -1)
			}); 
	
	//Stone Tools
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
				{ 
					new ItemStack(Items.STONE_AXE, 1, -1)
				});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
				{ 
					new ItemStack(Items.STONE_HOE, 1, -1)
				});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
				{ 
					new ItemStack(Items.STONE_PICKAXE, 1, -1)
				});    
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
				{ 
					new ItemStack(Items.STONE_SHOVEL, 1, -1)
				}); 
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
				{ 
					new ItemStack(Items.STONE_SWORD, 1, -1)
				}); 
		//Iron Tools
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.IRON_AXE, 1, -1)
						});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.IRON_HOE, 1, -1)
						});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.IRON_PICKAXE, 1, -1)
						});    
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.IRON_SHOVEL, 1, -1)
						}); 
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Items.IRON_SWORD, 1, -1)
						}); 
				//Gold Tools
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.GOLDEN_AXE, 1, -1)
						});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.GOLDEN_HOE, 1, -1)
						});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.GOLDEN_PICKAXE, 1, -1)
						});    
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.GOLDEN_SHOVEL, 1, -1)
						}); 
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Items.GOLDEN_SWORD, 1, -1)
						}); 
				//Diamond Tools
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.DIAMOND_AXE, 1, -1)
						});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.DIAMOND_HOE, 1, -1)
						});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.DIAMOND_PICKAXE, 1, -1)
						});    
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 2, 0), new Object[] 
						{ 
							new ItemStack(Items.DIAMOND_SHOVEL, 1, -1)
						}); 
				GameRegistry.addShapelessRecipe(new ItemStack(Items.STICK, 1, 0), new Object[] 
						{ 
							new ItemStack(Items.DIAMOND_SWORD, 1, -1)
						}); 
  }
}

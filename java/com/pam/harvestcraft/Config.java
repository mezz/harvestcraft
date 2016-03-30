package com.pam.harvestcraft;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.pam.harvestcraft.blocks.AridGardenBlock;
import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.blocks.FrostGardenBlock;
import com.pam.harvestcraft.blocks.ShadedGardenBlock;
import com.pam.harvestcraft.blocks.SoggyGardenBlock;
import com.pam.harvestcraft.blocks.TropicalGardenBlock;
import com.pam.harvestcraft.blocks.WindyGardenBlock;
import com.pam.harvestcraft.item.ItemRegistry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Config 
{
	public static final Config instance = new Config();
	
	public static final String CATEGORY_CROPS = "crops";
	public static final String CATEGORY_GARDENS = "gardens";
	public static final String CATEGORY_FRUIT_TREES = "fruit trees";
	public static final String CATEGORY_SALT = "salt";
	public static final String CATEGORY_BEE = "beekeeping";
	public static final String CATEGORY_MARKET_SALES = "market sales";
	public static final String CATEGORY_MARKET_PRICES = "market prices";
	public static final String CATEGORY_MARKET_CURRENCY = "market currency";
	public static final String CATEGORY_CANDLES = "candles";
	public static final String CATEGORY_DIMENSIONS = "dimensions";
	public static final String CATEGORY_SEEDS = "seeds";
	public static final String CATEGORY_MISC_RECIPES = "miscellaneous recipes";
	
	public void load(FMLPreInitializationEvent event)
	{
	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	config.load();
	BlockRegistry.initBlocks(event, config);
	ItemRegistry.initItems(event, config);

		final Pattern ITEM_STACK_PATTERN = Pattern.compile("(?:([0-9]+)x)?([\\w:]+)(?:[@:]([0-9]+))?");
		final Matcher ITEM_STACK_MATCHER = ITEM_STACK_PATTERN.matcher("");
		 
		String[] aridGardenDropEntries = config.getStringList("aridGarden", "drops", 
				new String[] { "harvestcraft:cactusfruitItem"}, 
							"comment");
		ItemStack[] aridGardenDrops = new ItemStack[aridGardenDropEntries.length];
		for(int i = 0; i < aridGardenDrops.length; i++) 
		{
		    ITEM_STACK_MATCHER.reset(aridGardenDropEntries[i]);
		    if(ITEM_STACK_MATCHER.find()) {
		        String itemName = ITEM_STACK_MATCHER.group(2);
		        int metadata = 0;
		        if(ITEM_STACK_MATCHER.group(3) != null) {
		            metadata = Integer.parseInt(ITEM_STACK_MATCHER.group(3));
		        }
		        int stackSize = 1;
		        if(ITEM_STACK_MATCHER.group(1) != null) {
		            stackSize = Integer.parseInt(ITEM_STACK_MATCHER.group(1));
		        }
		        aridGardenDrops[i] = GameRegistry.makeItemStack(itemName, metadata, stackSize, null);

		    }
		   
		}
		 AridGardenBlock.drops = Lists.newArrayList(aridGardenDrops);
		 
		 String[] frostGardenDropEntries = config.getStringList("frostGarden", "drops", 
					new String[] { "harvestcraft:raspberryItem","harvestcraft:oatsItem","harvestcraft:ryeItem",
							"harvestcraft:celeryItem","harvestcraft:peasItem","harvestcraft:beetItem",
							"harvestcraft:rutabagaItem","harvestcraft:broccoliItem","harvestcraft:cauliflowerItem",
							"harvestcraft:cabbageItem","harvestcraft:spinachItem"}, 
								"comment");
			ItemStack[] frostGardenDrops = new ItemStack[frostGardenDropEntries.length];
			for(int i = 0; i < frostGardenDrops.length; i++) 
			{
			    ITEM_STACK_MATCHER.reset(frostGardenDropEntries[i]);
			    if(ITEM_STACK_MATCHER.find()) {
			        String itemName = ITEM_STACK_MATCHER.group(2);
			        int metadata = 0;
			        if(ITEM_STACK_MATCHER.group(3) != null) {
			            metadata = Integer.parseInt(ITEM_STACK_MATCHER.group(3));
			        }
			        int stackSize = 1;
			        if(ITEM_STACK_MATCHER.group(1) != null) {
			            stackSize = Integer.parseInt(ITEM_STACK_MATCHER.group(1));
			        }
			        frostGardenDrops[i] = GameRegistry.makeItemStack(itemName, metadata, stackSize, null);

			    }
			   
			}
			 FrostGardenBlock.drops = Lists.newArrayList(frostGardenDrops);
			 
			 String[] shadedGardenDropEntries = config.getStringList("shadedGarden", "drops", 
						new String[] { "harvestcraft:whitemushroomItem","harvestcraft:blackberryItem","harvestcraft:zucchiniItem",
								"harvestcraft:radishItem","harvestcraft:rhubarbItem","harvestcraft:tealeafItem",
								"harvestcraft:garlicItem","harvestcraft:sweetpotatoItem","harvestcraft:turnipItem",
								"harvestcraft:spiceleafItem","harvestcraft:beanItem","harvestcraft:leekItem",
								"harvestcraft:scallionItem","harvestcraft:tomatoItem"}, 
									"comment");
				ItemStack[] shadedGardenDrops = new ItemStack[shadedGardenDropEntries.length];
				for(int i = 0; i < shadedGardenDrops.length; i++) 
				{
				    ITEM_STACK_MATCHER.reset(shadedGardenDropEntries[i]);
				    if(ITEM_STACK_MATCHER.find()) {
				        String itemName = ITEM_STACK_MATCHER.group(2);
				        int metadata = 0;
				        if(ITEM_STACK_MATCHER.group(3) != null) {
				            metadata = Integer.parseInt(ITEM_STACK_MATCHER.group(3));
				        }
				        int stackSize = 1;
				        if(ITEM_STACK_MATCHER.group(1) != null) {
				            stackSize = Integer.parseInt(ITEM_STACK_MATCHER.group(1));
				        }
				        shadedGardenDrops[i] = GameRegistry.makeItemStack(itemName, metadata, stackSize, null);

				    }
				   
				}
				 ShadedGardenBlock.drops = Lists.newArrayList(shadedGardenDrops);
				 
				 String[] soggyGardenDropEntries = config.getStringList("soggyGarden", "drops", 
							new String[] { "harvestcraft:brusselsproutItem","harvestcraft:spiceleafItem","harvestcraft:blueberryItem",
									"harvestcraft:asparagusItem","harvestcraft:cranberryItem","harvestcraft:riceItem",
									"harvestcraft:seaweedItem","harvestcraft:waterchestnutItem","harvestcraft:okraItem"}, 
										"comment");
					ItemStack[] soggyGardenDrops = new ItemStack[soggyGardenDropEntries.length];
					for(int i = 0; i < soggyGardenDrops.length; i++) 
					{
					    ITEM_STACK_MATCHER.reset(soggyGardenDropEntries[i]);
					    if(ITEM_STACK_MATCHER.find()) {
					        String itemName = ITEM_STACK_MATCHER.group(2);
					        int metadata = 0;
					        if(ITEM_STACK_MATCHER.group(3) != null) {
					            metadata = Integer.parseInt(ITEM_STACK_MATCHER.group(3));
					        }
					        int stackSize = 1;
					        if(ITEM_STACK_MATCHER.group(1) != null) {
					            stackSize = Integer.parseInt(ITEM_STACK_MATCHER.group(1));
					        }
					        soggyGardenDrops[i] = GameRegistry.makeItemStack(itemName, metadata, stackSize, null);

					    }
					   
					}
					 SoggyGardenBlock.drops = Lists.newArrayList(soggyGardenDrops);
					 
					 String[] tropicalGardenDropEntries = config.getStringList("tropicalGarden", "drops", 
								new String[] { "harvestcraft:grapeItem","harvestcraft:pineappleItem","harvestcraft:kiwiItem",
										"harvestcraft:sesameseedsItem","harvestcraft:curryleafItem","harvestcraft:bambooshootItem",
										"harvestcraft:cantaloupeItem","harvestcraft:gingerItem","harvestcraft:coffeebeanItem",
										"harvestcraft:soybeanItem","harvestcraft:eggplantItem"}, 
											"comment");
						ItemStack[] tropicalGardenDrops = new ItemStack[tropicalGardenDropEntries.length];
						for(int i = 0; i < tropicalGardenDrops.length; i++) 
						{
						    ITEM_STACK_MATCHER.reset(tropicalGardenDropEntries[i]);
						    if(ITEM_STACK_MATCHER.find()) {
						        String itemName = ITEM_STACK_MATCHER.group(2);
						        int metadata = 0;
						        if(ITEM_STACK_MATCHER.group(3) != null) {
						            metadata = Integer.parseInt(ITEM_STACK_MATCHER.group(3));
						        }
						        int stackSize = 1;
						        if(ITEM_STACK_MATCHER.group(1) != null) {
						            stackSize = Integer.parseInt(ITEM_STACK_MATCHER.group(1));
						        }
						        tropicalGardenDrops[i] = GameRegistry.makeItemStack(itemName, metadata, stackSize, null);

						    }
						   
						}
						 TropicalGardenBlock.drops = Lists.newArrayList(tropicalGardenDrops);
						 
						 String[] windyGardenDropEntries = config.getStringList("windyGarden", "drops", 
									new String[] { "harvestcraft:strawberryItem","harvestcraft:barleyItem","harvestcraft:cornItem",
											"harvestcraft:cucumberItem","harvestcraft:wintersquashItem","harvestcraft:mustardseedsItem",
											"harvestcraft:onionItem","harvestcraft:parsnipItem","harvestcraft:peanutItem",
											"minecraft:potato","minecraft:carrot","harvestcraft:lettuceItem",
											"harvestcraft:artichokeItem","harvestcraft:bellpepperItem","harvestcraft:chilipepperItem",
											"minecraft:wheat",}, 
												"comment");
							ItemStack[] windyGardenDrops = new ItemStack[windyGardenDropEntries.length];
							for(int i = 0; i < windyGardenDrops.length; i++) 
							{
							    ITEM_STACK_MATCHER.reset(windyGardenDropEntries[i]);
							    if(ITEM_STACK_MATCHER.find()) {
							        String itemName = ITEM_STACK_MATCHER.group(2);
							        int metadata = 0;
							        if(ITEM_STACK_MATCHER.group(3) != null) {
							            metadata = Integer.parseInt(ITEM_STACK_MATCHER.group(3));
							        }
							        int stackSize = 1;
							        if(ITEM_STACK_MATCHER.group(1) != null) {
							            stackSize = Integer.parseInt(ITEM_STACK_MATCHER.group(1));
							        }
							        windyGardenDrops[i] = GameRegistry.makeItemStack(itemName, metadata, stackSize, null);

							    }
							   
							}
							 WindyGardenBlock.drops = Lists.newArrayList(windyGardenDrops);
		
		if (config.hasChanged())
		{
			config.save();
		}
	}
}

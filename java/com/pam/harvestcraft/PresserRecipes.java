package com.pam.harvestcraft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class PresserRecipes
{
  private static final PresserRecipes pressingBase = new PresserRecipes();
  


  private Map<ItemStack, ItemStack[]> pressingList = new HashMap();
  

  private static final String __OBFID = "CL_00000085";
  

  public static PresserRecipes pressing()
  {
    return pressingBase;
  }
  
  private PresserRecipes()
  {
    registerItemRecipe(ItemRegistry.waxcombItem, new ItemStack(ItemRegistry.beeswaxItem), null);
    registerItemRecipe(ItemRegistry.honeycombItem, new ItemStack(ItemRegistry.honeyItem), new ItemStack(ItemRegistry.beeswaxItem));
    
    registerItemRecipe(Items.apple, new ItemStack(ItemRegistry.applejuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.blackberryItem, new ItemStack(ItemRegistry.blackberryjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.blueberryItem, new ItemStack(ItemRegistry.blueberryjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.cactusfruitItem, new ItemStack(ItemRegistry.cactusfruitjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.cherryItem, new ItemStack(ItemRegistry.cherryjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.cranberryItem, new ItemStack(ItemRegistry.cranberryjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.grapeItem, new ItemStack(ItemRegistry.grapejuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.kiwiItem, new ItemStack(ItemRegistry.kiwijuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.limeItem, new ItemStack(ItemRegistry.limejuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.mangoItem, new ItemStack(ItemRegistry.mangojuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.oliveItem, new ItemStack(ItemRegistry.oliveoilItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.sunflowerseedsItem, new ItemStack(ItemRegistry.oliveoilItem), new ItemStack(ItemRegistry.grainbaitItem));
    registerItemRecipe(ItemRegistry.walnutItem, new ItemStack(ItemRegistry.oliveoilItem), new ItemStack(ItemRegistry.grainbaitItem));
    registerItemRecipe(ItemRegistry.orangeItem, new ItemStack(ItemRegistry.orangejuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.papayaItem, new ItemStack(ItemRegistry.papayajuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.peachItem, new ItemStack(ItemRegistry.peachjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.pomegranateItem, new ItemStack(ItemRegistry.pomegranatejuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.starfruitItem, new ItemStack(ItemRegistry.starfruitjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.strawberryItem, new ItemStack(ItemRegistry.strawberryjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(Items.reeds, new ItemStack(Items.sugar), null);
    registerItemRecipe(ItemRegistry.beetItem, new ItemStack(Items.sugar), new ItemStack(ItemRegistry.veggiebaitItem));
    registerBlockRecipe(Blocks.log, new ItemStack(Items.paper), null);
    registerItemRecipe(ItemRegistry.freshwaterItem, new ItemStack(ItemRegistry.bubblywaterItem), null);
    registerItemRecipe(ItemRegistry.soybeanItem, new ItemStack(ItemRegistry.silkentofuItem), null);
    registerItemRecipe(ItemRegistry.silkentofuItem, new ItemStack(ItemRegistry.firmtofuItem), new ItemStack(ItemRegistry.soymilkItem));
    registerItemRecipe(ItemRegistry.plumItem, new ItemStack(ItemRegistry.plumjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.pearItem, new ItemStack(ItemRegistry.pearjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    
    registerItemRecipe(ItemRegistry.apricotItem, new ItemStack(ItemRegistry.apricotjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.figItem, new ItemStack(ItemRegistry.figjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.grapefruitItem, new ItemStack(ItemRegistry.grapefruitjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    registerItemRecipe(ItemRegistry.persimmonItem, new ItemStack(ItemRegistry.persimmonjuiceItem), new ItemStack(ItemRegistry.fruitbaitItem));
    
    registerItemRecipe(ItemRegistry.almondItem, new ItemStack(ItemRegistry.freshmilkItem), new ItemStack(ItemRegistry.grainbaitItem));
    
    registerItemRecipe(ItemRegistry.sesameseedsItem, new ItemStack(ItemRegistry.oliveoilItem), new ItemStack(ItemRegistry.grainbaitItem));
  }
  

  public void registerBlockRecipe(Block par1, ItemStack par2, ItemStack par3)
  {
    registerItemRecipe(Item.getItemFromBlock(par1), par2, par3);
  }
  
  public void registerItemRecipe(Item par1, ItemStack par2, ItemStack par3)
  {
    makeItemStackRecipe(new ItemStack(par1, 1, 32767), par2, par3);
  }
  
  public void makeItemStackRecipe(ItemStack par1, ItemStack par2, ItemStack par3)
  {
    ItemStack[] ista = {par2, par3};
    this.pressingList.put(par1, ista);
  }
  



  public ItemStack[] getPressingResult(ItemStack pIst)
  {
    Iterator iterator = this.pressingList.entrySet().iterator();
    
    Map.Entry entry;
    do
    {
      if (!iterator.hasNext())
      {
        return null;
      }
      
      entry = (Map.Entry)iterator.next();
    }
    while (!func_151397_a(pIst, (ItemStack)entry.getKey()));
    
    return (ItemStack[])entry.getValue();
  }
  
  private boolean func_151397_a(ItemStack par1, ItemStack par2)
  {
    return (par2.getItem() == par1.getItem()) && ((par2.getItemDamage() == 32767) || (par2.getItemDamage() == par1.getItemDamage()));
  }
  
  public Map getPressingList()
  {
    return this.pressingList;
  }
  
}
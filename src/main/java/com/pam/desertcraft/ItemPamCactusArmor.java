package com.pam.desertcraft;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPamCactusArmor extends ItemArmor
{
  public ItemPamCactusArmor(EntityEquipmentSlot armorType) {
    super(ItemRegistry.armorCactus, 0, armorType);
    setCreativeTab(desertcraft.tabDesertcraft);
  }
  
  public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
  par1ItemStack.addEnchantment(Enchantment.getEnchantmentByID(7), 1);
  }
  
}

package com.pam.bonecraft;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemPamBoneArmor extends ItemArmor
{
  public ItemPamBoneArmor(EntityEquipmentSlot armorType) {
    super(bonecraft.armorBone, 0, armorType);
    setCreativeTab(bonecraft.tabBonecraft);
  }
  
  @Override
  public boolean getIsRepairable(ItemStack armor, ItemStack stack) {
   return stack.getItem() == Items.BONE; 
  }
}

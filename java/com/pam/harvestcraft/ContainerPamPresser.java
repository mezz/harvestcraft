package com.pam.harvestcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPamPresser extends Container
{
  private TileEntityPamPresser combPresser;
  private int lastCookTime = 0;
  
  public ContainerPamPresser(InventoryPlayer par1InventoryPlayer, TileEntityPamPresser par2TileEntityCombPresser) {
    this.combPresser = par2TileEntityCombPresser;
    this.addSlotToContainer(new SlotPamPresser(par1InventoryPlayer.player, par2TileEntityCombPresser, 0, 80, 23));
    this.addSlotToContainer(new SlotPamResult(par1InventoryPlayer.player, par2TileEntityCombPresser, 1, 62, 54));
    this.addSlotToContainer(new SlotPamResult(par1InventoryPlayer.player, par2TileEntityCombPresser, 2, 98, 54));

    int var3;
    for (var3 = 0; var3 < 3; ++var3) {
        for (int var4 = 0; var4 < 9; ++var4) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
        }
    }

    for (var3 = 0; var3 < 9; ++var3) {
        this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
    }

  }
  public void detectAndSendChanges()
  {
    super.detectAndSendChanges();
    
    for (int var1 = 0; var1 < this.crafters.size(); var1++) {
      ICrafting var2 = (ICrafting)this.crafters.get(var1);
      if (this.lastCookTime != this.combPresser.combPresserCookTime) {
        var2.sendProgressBarUpdate(this, 0, this.combPresser.combPresserCookTime);
      }
    }
    
    this.lastCookTime = this.combPresser.combPresserCookTime;
  }
  
  @SideOnly(Side.CLIENT)
  public void updateProgressBar(int par1, int par2) {
    if (par1 == 0) {
      this.combPresser.combPresserCookTime = par2;
    }
  }
  
  public boolean canInteractWith(EntityPlayer par1EntityPlayer)
  {
    return this.combPresser.isUseableByPlayer(par1EntityPlayer);
  }
  
  public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
  {
    ItemStack itemStack = null;
    Slot slot = (Slot)this.inventorySlots.get(slotIndex);
    if ((slot != null) && (slot.getHasStack())) {
      ItemStack slotStack = slot.getStack();
      itemStack = slotStack.copy();
      
      if (slotIndex >= 3) {
        if ((slotStack.getItem() == ItemRegistry.soybeanItem) || (slotStack.getItem() == ItemRegistry.silkentofuItem) || (slotStack.getItem() == ItemRegistry.soymilkItem)) {
          if (!mergeItemStack(slotStack, 0, 1, false))
          {
            return null;
          }
        } else if ((slotIndex >= 3) && (slotIndex < 30))
        {
          if (!mergeItemStack(slotStack, 30, 39, false)) {
            return null;
          }
        } else if ((slotIndex >= 30) && (slotIndex < 39) && (!mergeItemStack(slotStack, 3, 30, false)))
        {
          return null;
        }
      } else if (!mergeItemStack(slotStack, 3, 39, false))
      {
        return null;
      }
      
      if (slotStack.stackSize == 0) {
        slot.putStack(null);
      } else {
        slot.onSlotChanged();
      }
      
      if (slotStack.stackSize == itemStack.stackSize) {
        return null;
      }
      
      slot.onPickupFromSlot(entityPlayer, slotStack);
    }
    
    return itemStack;
  }
}
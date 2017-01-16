package com.pam.harvestcraft.gui;

import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPamGroundTrap extends Slot {

    public SlotPamGroundTrap(IInventory inventory, int index, int xPos, int yPos) {
        super(inventory, index, xPos, yPos);
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
    	return (par1ItemStack.getItem() == ItemRegistry.grainbaitItem) || (par1ItemStack.getItem() == ItemRegistry.veggiebaitItem) || (par1ItemStack.getItem() == ItemRegistry.fruitbaitItem);
    	}
}

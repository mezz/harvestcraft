package com.pam.harvestcraft.gui;

import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPamWaterTrap extends Slot {

    public SlotPamWaterTrap(IInventory inventory, int index, int xPos, int yPos) {
        super(inventory, index, xPos, yPos);
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == ItemRegistry.fishtrapbaitItem;
    }
}

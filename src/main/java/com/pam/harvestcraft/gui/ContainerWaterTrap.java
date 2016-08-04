package com.pam.harvestcraft.gui;

import com.pam.harvestcraft.item.ItemRegistry;
import com.pam.harvestcraft.tileentities.TileEntityWaterTrap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWaterTrap extends Container {

    private final TileEntityWaterTrap watertrap;
    private int lastProduceTime = 0;
    private int lastRunTime = 0;
    private int lastBeeRunTime = 0;

    public ContainerWaterTrap(InventoryPlayer inv, TileEntityWaterTrap TileEntityWaterTrap) {
        this.watertrap = TileEntityWaterTrap;

        for (int x = 0; x < 6; ++x) {
            for (int y = 0; y < 3; ++y) {
                addSlotToContainer(new SlotPamResult(TileEntityWaterTrap, y + x * 3, 62 + 18 * x, 17 + 18 * y));
            }
        }

        addSlotToContainer(new SlotPamWaterTrap(TileEntityWaterTrap, 18, 26, 35));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : listeners) {
            if (lastProduceTime != watertrap.produceTime) {
                listener.sendProgressBarUpdate(this, 1, watertrap.produceTime);
            }

            if (lastRunTime != watertrap.runTime) {
                listener.sendProgressBarUpdate(this, 2, watertrap.runTime);
            }

            if (lastBeeRunTime != watertrap.currentBeeRunTime) {
                listener.sendProgressBarUpdate(this, 3, watertrap.currentBeeRunTime);
            }
        }

        lastProduceTime = watertrap.produceTime;
        lastRunTime = watertrap.runTime;
        lastBeeRunTime = watertrap.currentBeeRunTime;
    }

    @Override
    public void updateProgressBar(int id, int data) {
        switch (id) {
            case 1:
                watertrap.produceTime = data;
                break;
            case 2:
                watertrap.runTime = data;
                break;
            case 3:
                watertrap.currentBeeRunTime = data;
                break;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return watertrap.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemStack = null;
        final Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getStack() != null) {
            final ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (index >= 19 ? (slotStack.getItem() == ItemRegistry.fishtrapbaitItem ? !mergeItemStack(slotStack, 18, 19, false) : (index >= 19 && index < 46 ? !mergeItemStack(slotStack, 46, 55, false) : index >= 46
                    && index < 55 && !mergeItemStack(slotStack, 19, 46, false))) : !mergeItemStack(slotStack, 19, 55, false)) {
                return null;
            }

            if (slotStack.stackSize == 0) {
                slot.putStack(null);
                return null;
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == itemStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(playerIn, slotStack);
        }

        return itemStack;
    }
}

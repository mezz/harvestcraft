package com.pam.harvestcraft.tileentities;

import java.util.Random;

import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.blocks.blocks.BlockBaseGarden;
import com.pam.harvestcraft.item.ItemRegistry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class TileEntityWaterTrap extends TileEntity implements IInventory, ITickable {

    private ItemStack[] inventory = new ItemStack[19];
    public int runTime = 0;
    public int currentBeeRunTime = 0;
    public int produceTime = 0;

    @Override
    public String getName() {
        return "WaterTrap";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        final NBTTagList tagList = compound.getTagList("Items", 10);
        inventory = new ItemStack[getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i) {
            final NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
            final byte slot = stackTag.getByte("Slot");

            if (slot < 0 || slot >= inventory.length) continue;
            inventory[slot] = ItemStack.loadItemStackFromNBT(stackTag);
        }

        runTime = compound.getShort("RunTime");
        produceTime = compound.getShort("ProduceTime");
        currentBeeRunTime = getRunTime(this.inventory[1]);
    }

    @Override
    @MethodsReturnNonnullByDefault
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setShort("RunTime", (short) runTime);
        compound.setShort("ProduceTime", (short) produceTime);

        final NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < inventory.length; ++i) {
            if (inventory[i] == null) continue;

            final NBTTagCompound stackTag = new NBTTagCompound();
            stackTag.setByte("Slot", (byte) i);
            inventory[i].writeToNBT(stackTag);
            tagList.appendTag(stackTag);
        }
        compound.setTag("Items", tagList);

        return compound;
    }
    
    public int countFlowers() {
        byte radius = 2;
        int count = 0;
        final World world = worldObj;
        final int varX = pos.getX();
        final int varY = pos.getY();
        final int varZ = pos.getZ();

        for (int offsetX = -radius; offsetX <= radius; ++offsetX) {
                for (int offsetZ = -radius; offsetZ <= radius; ++offsetZ) {
                        if (offsetX * offsetX + offsetZ * offsetZ <= radius * radius
                                        && (offsetX != -(radius - 1) || offsetZ != -(radius - 1))
                                        && (offsetX != radius - 1 || offsetZ != radius - 1)
                                        && (offsetX != radius - 1 || offsetZ != -(radius - 1))
                                        && (offsetX != -(radius - 1) || offsetZ != radius - 1)) {
                        	final Block blockAtCoords = world.getBlockState(new BlockPos(varX + offsetX, varY, varZ + offsetZ)).getBlock();
                                if (worldObj.getBlockState(new BlockPos(varX + offsetX, varY, varZ + offsetZ)).getBlock() == Blocks.WATER) {
                                        count++;
                                }
                        }
                }
        }

        return count;
}

    @Override
    public void update() {
        boolean isRunning = runTime > 0;
        boolean needsUpdate = false;

        if (isRunning) {
            --runTime;
        }

        ItemStack queenBee = inventory[18];
        if (!worldObj.isRemote) {
            if (runTime == 0 && canRun()) {
                currentBeeRunTime = runTime = getRunTime(queenBee);

                if (runTime > 0) {
                    needsUpdate = true;
                    if (queenBee != null) {
                        if (queenBee.getItem().getContainerItem() != null) {
                            queenBee = new ItemStack(queenBee.getItem().setFull3D());
                        } else {
                            if (queenBee.stackSize > 0) queenBee.stackSize -= 0;
                        }

                        if (queenBee.stackSize == 0) {
                            queenBee = null;
                        }
                    }
                }
            }

            if (canRun()) {
                ++produceTime;

                if (produceTime >= Math.floor(getRunTime())) {
                    produceTime = 0;
                    run();
                    needsUpdate = true;
                }
            } else {
                produceTime = 0;
            }

            if (isRunning != runTime > 0) {
                needsUpdate = true;
            }
        }

        if (needsUpdate) {
            markDirty();
            worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
        }
    }

    private boolean canRun() {
        if (this.inventory[18] != null) {
                if (this.inventory[18].getItem() == ItemRegistry.fishtrapbaitItem) {
                        
                                if (countFlowers() >= 5) {
                                        return true;
                                }
                }
        } else
                return false;
        return false;
}

    public void run() {
        
        ItemStack itemProduced = this.getComb();
        for (int i = 0; i < 18; ++i) {
                if (this.inventory[i] == null) {
                		this.decrStackSize(18, 1);
                        this.inventory[i] = itemProduced.copy();
                        break;
                }
        }
}

    public ItemStack getComb() {
        Random rnd = new Random();
        
        if (this.inventory[18] != null) 
        {
        		int rndnum = rnd.nextInt(32);
                switch (rndnum)
                {
                case 0: return new ItemStack(Items.FISH, 1, 0);
                case 1: return new ItemStack(Items.FISH, 1, 1);
                case 2: return new ItemStack(Items.FISH, 1, 2);
                case 3: return new ItemStack(Items.FISH, 1, 3);
                case 4: return new ItemStack(ItemRegistry.anchovyrawItem, 1, 0);
                case 5: return new ItemStack(ItemRegistry.bassrawItem, 1, 0);
                case 6: return new ItemStack(ItemRegistry.carprawItem, 1, 0);
                case 7: return new ItemStack(ItemRegistry.catfishrawItem, 1, 0);
                case 8: return new ItemStack(ItemRegistry.charrrawItem, 1, 0);
                case 9: return new ItemStack(ItemRegistry.clamrawItem, 1, 0);
                case 10: return new ItemStack(ItemRegistry.crabrawItem, 1, 0);
                case 11: return new ItemStack(ItemRegistry.crayfishrawItem, 1, 0);
                case 12: return new ItemStack(ItemRegistry.eelrawItem, 1, 0);
                case 13: return new ItemStack(ItemRegistry.frograwItem, 1, 0);
                case 14: return new ItemStack(ItemRegistry.grouperrawItem, 1, 0);
                case 15: return new ItemStack(ItemRegistry.herringrawItem, 1, 0);
                case 16: return new ItemStack(ItemRegistry.jellyfishrawItem, 1, 0);
                case 17: return new ItemStack(ItemRegistry.mudfishrawItem, 1, 0);
                case 18: return new ItemStack(ItemRegistry.octopusrawItem, 1, 0);
                case 19: return new ItemStack(ItemRegistry.perchrawItem, 1, 0);
                case 20: return new ItemStack(ItemRegistry.scalloprawItem, 1, 0);
                case 21: return new ItemStack(ItemRegistry.shrimprawItem, 1, 0);
                case 22: return new ItemStack(ItemRegistry.snailrawItem, 1, 0);
                case 23: return new ItemStack(ItemRegistry.snapperrawItem, 1, 0);
                case 24: return new ItemStack(ItemRegistry.tilapiarawItem, 1, 0);
                case 25: return new ItemStack(ItemRegistry.troutrawItem, 1, 0);
                case 26: return new ItemStack(ItemRegistry.tunarawItem, 1, 0);
                case 27: return new ItemStack(ItemRegistry.turtlerawItem, 1, 0);
                case 28: return new ItemStack(ItemRegistry.walleyerawItem, 1, 0);
                case 29: return new ItemStack(Items.FISH, 1, 0);
                case 30: return new ItemStack(Items.FISH, 1, 0);
                case 31: return new ItemStack(ItemRegistry.greenheartfishItem, 1, 0);
                }

        }
        return null;

}

    private int getRunTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        if (stack.getItem() == ItemRegistry.fishtrapbaitItem) {
            return 3200;
        }
        return 0;
    }

    private int getRunTime() {
        final int radius = 2;
        final World world = worldObj;
        final int varX = pos.getX();
        final int varY = pos.getY();
        final int varZ = pos.getZ();

        int speed = 3500;

        for (int offsetX = -radius; offsetX <= radius; ++offsetX) {
            for (int offsetZ = -radius; offsetZ <= radius; ++offsetZ) {
                if (offsetX * offsetX + offsetZ * offsetZ > radius * radius ||
                        offsetX == - radius - 1 && offsetZ == - radius - 1 ||
                        offsetX == radius - 1 && offsetZ == radius - 1 ||
                        offsetX == radius - 1 && offsetZ == - radius - 1 ||
                        offsetX == - radius - 1 && offsetZ == radius - 1) continue;
                final Block blockAtCoords = world.getBlockState(new BlockPos(varX + offsetX, varY, varZ + offsetZ)).getBlock();
                if (blockAtCoords instanceof BlockLiquid) {
                    speed = (int)(speed * 0.95);
                }
                if (blockAtCoords != BlockRegistry.watertrap) continue;
                speed = (int)(speed / 0.85);
            }
        }
        return speed;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (inventory[index] == null) {
            setInventorySlotContents(index, null);
            return null;
        }

        if (inventory[index].stackSize <= count) {
            final ItemStack stack = inventory[index];
            setInventorySlotContents(index, null);
            return stack;
        }

        final ItemStack stack = inventory[index].splitStack(count);
        if (inventory[index].stackSize <= 0) {
            setInventorySlotContents(index, null);
        }

        markDirty();

        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (worldObj.getTileEntity(getPos()) != this) return false;

        return player.getDistanceSq(getPos()) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        final NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);

        return new SPacketUpdateTileEntity(getPos(), 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {}
}

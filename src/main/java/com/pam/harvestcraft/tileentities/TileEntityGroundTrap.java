package com.pam.harvestcraft.tileentities;

import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.item.ItemRegistry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import java.util.Random;

public class TileEntityGroundTrap extends TileEntity implements IInventory, ITickable {

    private ItemStack[] inventory = new ItemStack[19];
    public int runTime = 0;
    public int currentBeeRunTime = 0;
    public int produceTime = 0;

    @Override
    public String getName() {
        return "GroundTrap";
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
            inventory[slot] = new ItemStack(stackTag);
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
        //final World world = world;
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
                                if (blockAtCoords instanceof BlockDirt || blockAtCoords instanceof BlockGrass) {
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
        if (!world.isRemote) {
            if (runTime == 0 && canRun()) {
                currentBeeRunTime = runTime = getRunTime(queenBee);

                if (runTime > 0) {
                    needsUpdate = true;
                    if (queenBee != null) {
                        if (queenBee.getItem().getContainerItem() != null) {
                            queenBee = new ItemStack(queenBee.getItem().setFull3D());
                        } else {
                            if (queenBee.getCount() > 0){
                                queenBee.setCount(queenBee.getCount() - 0);
                            }
                        }

                        if (queenBee.getCount() == 0) {
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
            world.notifyBlockUpdate(pos, world.getBlockState(getPos()), world.getBlockState(getPos()), 3);
        }
    }

    private boolean canRun() {
        if (this.inventory[18] != null) {
                if (this.inventory[18].getItem() == ItemRegistry.grainbaitItem
                		|| this.inventory[18].getItem() == ItemRegistry.fruitbaitItem
                		|| this.inventory[18].getItem() == ItemRegistry.veggiebaitItem) {
                        
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
           if (this.inventory[18].getItem() == ItemRegistry.grainbaitItem) 
           {
        	   int rndnum = rnd.nextInt(11);
               switch (rndnum)
               {
               case 0: return new ItemStack(ItemRegistry.turkeyrawItem, 1, 0);
               case 1: return new ItemStack(Items.BEEF, 1, 0);
               case 2: return new ItemStack(Items.CHICKEN, 1, 0);
               case 3: return new ItemStack(Items.LEATHER, 1, 0);
               case 4: return new ItemStack(Items.FEATHER, 1, 0);
               case 5: return new ItemStack(Items.EGG, 1, 0);
               case 6: return new ItemStack(Items.BONE, 1, 0);
               case 7: return new ItemStack(Items.CHICKEN, 1, 0);
               case 8: return new ItemStack(Items.CHICKEN, 1, 0);
               case 9: return new ItemStack(ItemRegistry.turkeyrawItem, 1, 0);
               case 10: return new ItemStack(Items.FEATHER, 1, 0);
               
               }
           }
           if (this.inventory[18].getItem() == ItemRegistry.fruitbaitItem) 
           {
        	   int rndnum = rnd.nextInt(11);
               switch (rndnum)
               {
               case 0: return new ItemStack(Items.RABBIT, 1, 0);
               case 1: return new ItemStack(Items.LEATHER, 1, 0);
               case 2: return new ItemStack(Items.FEATHER, 1, 0);
               case 3: return new ItemStack(Items.EGG, 1, 0);
               case 4: return new ItemStack(Items.BONE, 1, 0);
               case 5: return new ItemStack(Items.RABBIT_FOOT, 1, 0);
               case 6: return new ItemStack(Items.RABBIT_HIDE, 1, 0);
               case 7: return new ItemStack(Items.CHICKEN, 1, 0);
               case 8: return new ItemStack(Items.CHICKEN, 1, 0);
               case 9: return new ItemStack(Items.CHICKEN, 1, 0);
               case 10: return new ItemStack(Items.FEATHER, 1, 0);
               }
           }
           if (this.inventory[18].getItem() == ItemRegistry.veggiebaitItem) 
           {
        	   int rndnum = rnd.nextInt(11);
               switch (rndnum)
               {
               case 0: return new ItemStack(ItemRegistry.venisonrawItem, 1, 0);
               case 1: return new ItemStack(Items.MUTTON, 1, 0);
               case 2: return new ItemStack(Items.PORKCHOP, 1, 0);
               case 3: return new ItemStack(Items.LEATHER, 1, 0);
               case 4: return new ItemStack(Items.FEATHER, 1, 0);
               case 5: return new ItemStack(Items.EGG, 1, 0);
               case 6: return new ItemStack(Items.BONE, 1, 0);
               case 7: return new ItemStack(Items.CHICKEN, 1, 0);
               case 8: return new ItemStack(Items.CHICKEN, 1, 0);
               case 9: return new ItemStack(Items.CHICKEN, 1, 0);
               case 10: return new ItemStack(Items.FEATHER, 1, 0);
               }
           }
        }
        return null;

}

    int getRunTime(ItemStack stack) {
        if (stack == null) {
                return 0;
        }

        if (stack.getItem() == ItemRegistry.grainbaitItem 
        		|| stack.getItem() == ItemRegistry.fruitbaitItem
        		|| stack.getItem() == ItemRegistry.veggiebaitItem) {
                return 3200;
        }

        return 0;
}

    private int getRunTime() {
        final int radius = 2;
        //final World world = world;
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
                if (blockAtCoords instanceof BlockDirt || blockAtCoords instanceof BlockGrass) {
                    speed = (int)(speed * 0.95);
                }
                if (blockAtCoords != BlockRegistry.groundtrap) continue;
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

        if (inventory[index].getCount() <= count) {
            final ItemStack stack = inventory[index];
            setInventorySlotContents(index, null);
            return stack;
        }

        final ItemStack stack = inventory[index].splitStack(count);
        if (inventory[index].getCount() <= 0) {
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

        if (stack != null && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
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

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
	
	public String getGuiID()
    {
        return "harvestcraft:groundtrap";
    }
}

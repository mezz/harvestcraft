package com.pam.harvestcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPamPresser extends TileEntity implements ISidedInventory{

       private ItemStack[] combPresserItemStacks = new ItemStack[3];
       public int combPresserCookTime;
       public int meta = 0;
       
       private static final int[] slots_top = new int[] {0};
       private static final int[] slots_bottom = new int[] {2, 1};


       public int getSizeInventory() {
          return this.combPresserItemStacks.length;
       }

       public ItemStack getStackInSlot(int par1) {
          return this.combPresserItemStacks[par1];
       }

       public ItemStack decrStackSize(int par1, int par2) {
          if(this.combPresserItemStacks[par1] != null) {
             ItemStack var3;
             if(this.combPresserItemStacks[par1].stackSize <= par2) {
                var3 = this.combPresserItemStacks[par1];
                this.combPresserItemStacks[par1] = null;
                return var3;
             } else {
                var3 = this.combPresserItemStacks[par1].splitStack(par2);
                if(this.combPresserItemStacks[par1].stackSize == 0) {
                   this.combPresserItemStacks[par1] = null;
                }

                return var3;
             }
          } else {
             return null;
          }
       }

       public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
          this.combPresserItemStacks[par1] = par2ItemStack;
          if(par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
             par2ItemStack.stackSize = this.getInventoryStackLimit();
          }

       }

       public String getInvName() {
          return "Presser";
       }

       public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
          super.readFromNBT(par1NBTTagCompound);
          NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10);
          this.combPresserItemStacks = new ItemStack[this.getSizeInventory()];

          for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
             NBTTagCompound var4 = (NBTTagCompound)var2.getCompoundTagAt(var3);
             byte var5 = var4.getByte("Slot");
             if(var5 >= 0 && var5 < this.combPresserItemStacks.length) {
                this.combPresserItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
             }
          }

          this.combPresserCookTime = par1NBTTagCompound.getShort("CookTime");
       }

       public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
          super.writeToNBT(par1NBTTagCompound);
          par1NBTTagCompound.setShort("CookTime", (short)this.combPresserCookTime);
          NBTTagList var2 = new NBTTagList();

          for(int var3 = 0; var3 < this.combPresserItemStacks.length; ++var3) {
             if(this.combPresserItemStacks[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.combPresserItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
             }
          }

          par1NBTTagCompound.setTag("Items", var2);
       }

       public int getInventoryStackLimit() {
          return 64;
       }

       @SideOnly(Side.CLIENT)
       public int getCookProgressScaled(int par1) {
          return this.combPresserCookTime * par1 / 125;
       }

       public boolean isRunning() {
          return this.combPresserCookTime > 0;
       }

       public void updateEntity() 
       {
              boolean needsUpdate = false;

              if(!super.worldObj.isRemote) {

                 if(this.canRun()) 
                 {
                    ++this.combPresserCookTime;
                    if(this.combPresserCookTime >= 125) 
                    {
                       this.combPresserCookTime = 0;
                       this.pressComb();
                       needsUpdate = true;
                    }
                 } else {
                    this.combPresserCookTime = 0;
                 }

                 if(needsUpdate != this.combPresserCookTime > 0) 
                 {
                    needsUpdate = true;
                 }
              }

              if(needsUpdate) {
                 this.markDirty();
                 worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
              }

           }

       public int getDamage(ItemStack par1ItemStack) {
          return par1ItemStack.getItemDamage();
       }

       private boolean canRun()
        {
            if (this.combPresserItemStacks[0] == null)
            {
                return false;
            }
            else
            {
                ItemStack[] results = PresserRecipes.pressing().getPressingResult(this.combPresserItemStacks[0]);
                if (results == null) return false;
                if (this.combPresserItemStacks[1] != null) {
                    if (!this.combPresserItemStacks[1].isItemEqual(results[0])) return false;
                    if (combPresserItemStacks[1].stackSize + results[0].stackSize >= this.combPresserItemStacks[1].getMaxStackSize()) return false; 
                }
                if (results[1] != null) {
                    if (this.combPresserItemStacks[2] != null) {
                        if (!this.combPresserItemStacks[2].isItemEqual(results[1])) return false;
                        if (combPresserItemStacks[2].stackSize + results[1].stackSize >= this.combPresserItemStacks[2].getMaxStackSize()) return false; 
                    }
                }
                return true;
            }
        }

        public void pressComb()
        {
            if (this.canRun())
            {
                ItemStack[] results = PresserRecipes.pressing().getPressingResult(this.combPresserItemStacks[0]);

                if (this.combPresserItemStacks[1] == null)
                {
                    this.combPresserItemStacks[1] = results[0].copy();
                }
                else if (this.combPresserItemStacks[1].getItem() == results[0].getItem())
                {
                    this.combPresserItemStacks[1].stackSize += results[0].stackSize; // Forge BugFix: Results may have multiple items
                }

                if (results[1] != null) {
                    if (this.combPresserItemStacks[2] == null)
                    {
                        this.combPresserItemStacks[2] = results[1].copy();
                    }
                    else if (this.combPresserItemStacks[2].isItemEqual(results[1]))
                    {
                        this.combPresserItemStacks[2].stackSize += results[1].stackSize;
                    }
                }
                
                --this.combPresserItemStacks[0].stackSize;

                if (this.combPresserItemStacks[0].stackSize <= 0)
                {
                    this.combPresserItemStacks[0] = null;
                }
            }
        }

       public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
          return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:par1EntityPlayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
       }

       public void openChest() {}

       public void closeChest() {}

       public ItemStack getStackInSlotOnClosing(int var1) {
          return null;
       }

       public boolean isInvNameLocalized() {
          return false;
       }

       public boolean isStackValidForSlot(int i, ItemStack itemstack) {
          return true;
       }
    
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_top);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return true;
    }

    @Override
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void openInventory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Packet getDescriptionPacket() {
    NBTTagCompound tag = new NBTTagCompound();
    this.writeToNBT(tag);
    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
        
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    readFromNBT(packet.func_148857_g());
    }
}
package com.pam.harvestcraft.tileentities;

import java.util.Random;

import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.item.ItemRegistry;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityWaterTrap extends TileEntity implements ITickable {

	private ItemStackHandler itemstackhandler = new ItemStackHandler(19);
	public int runTime = 0;
	public int currentBeeRunTime = 0;
	public int produceTime = 0;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemstackhandler);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		itemstackhandler.deserializeNBT((NBTTagCompound) compound.getTag("Items"));
		runTime = compound.getShort("RunTime");
		produceTime = compound.getShort("ProduceTime");
		currentBeeRunTime = getRunTime(itemstackhandler.getStackInSlot(1));
	}

	@Override
	@MethodsReturnNonnullByDefault
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setShort("RunTime", (short) runTime);
		compound.setShort("ProduceTime", (short) produceTime);
		compound.setTag("Items", itemstackhandler.serializeNBT());

		return super.writeToNBT(compound);
	}

	public int countFlowers() {
		byte radius = 2;
		int count = 0;
		// final World world = world;
		final int varX = pos.getX();
		final int varY = pos.getY();
		final int varZ = pos.getZ();

		for(int offsetX = -radius; offsetX <= radius; ++offsetX) {
			for(int offsetZ = -radius; offsetZ <= radius; ++offsetZ) {
				if(offsetX * offsetX + offsetZ * offsetZ <= radius * radius
						&& (offsetX != -(radius - 1) || offsetZ != -(radius - 1))
						&& (offsetX != radius - 1 || offsetZ != radius - 1)
						&& (offsetX != radius - 1 || offsetZ != -(radius - 1))
						&& (offsetX != -(radius - 1) || offsetZ != radius - 1)) {
					final BlockPos pos = new BlockPos(varX + offsetX, varY, varZ + offsetZ);
					if (world.isBlockLoaded(pos) && world.getBlockState(pos).getBlock() == Blocks.WATER) {
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

		if(isRunning) {
			--runTime;
		}

		ItemStack queenBee = itemstackhandler.getStackInSlot(18);
		if(!world.isRemote) {
			if(runTime == 0 && canRun()) {
				currentBeeRunTime = runTime = getRunTime(queenBee);

				if(runTime > 0) {
					needsUpdate = true;
					if(!queenBee.isEmpty()) {
						queenBee.shrink(1);
					}
				}
			}

			if(canRun()) {
				++produceTime;

				if(produceTime >= Math.floor(getRunTime())) {
					produceTime = 0;
					run();
					needsUpdate = true;
				}
			}
			else {
				produceTime = 0;
			}

			if(isRunning != runTime > 0) {
				needsUpdate = true;
			}
		}

		if(needsUpdate) {
			markDirty();
			world.notifyBlockUpdate(pos, world.getBlockState(getPos()), world.getBlockState(getPos()), 3);
		}
	}

	private boolean canRun() {
		if(!itemstackhandler.getStackInSlot(18).isEmpty()) {
			if(itemstackhandler.getStackInSlot(18).getItem() == ItemRegistry.fishtrapbaitItem) {

				if(countFlowers() >= 5) {
					return true;
				}
			}
		}
		else
			return false;
		return false;
	}

	public void run() {

		ItemStack itemProduced = this.getComb();
		for(int i = 0; i < 18; ++i) {
			if(!itemstackhandler.getStackInSlot(i).isEmpty())
				continue;
			itemstackhandler.setStackInSlot(i, itemProduced.copy());
			break;
		}
	}

	public ItemStack getComb() {
		Random rnd = new Random();

		if(!itemstackhandler.getStackInSlot(18).isEmpty()) {
			int rndnum = rnd.nextInt(32);
			switch(rndnum) {
				case 0:
					return new ItemStack(Items.FISH, 1, 0);
				case 1:
					return new ItemStack(Items.FISH, 1, 1);
				case 2:
					return new ItemStack(Items.FISH, 1, 2);
				case 3:
					return new ItemStack(Items.FISH, 1, 3);
				case 4:
					return new ItemStack(ItemRegistry.anchovyrawItem, 1, 0);
				case 5:
					return new ItemStack(ItemRegistry.bassrawItem, 1, 0);
				case 6:
					return new ItemStack(ItemRegistry.carprawItem, 1, 0);
				case 7:
					return new ItemStack(ItemRegistry.catfishrawItem, 1, 0);
				case 8:
					return new ItemStack(ItemRegistry.charrrawItem, 1, 0);
				case 9:
					return new ItemStack(ItemRegistry.clamrawItem, 1, 0);
				case 10:
					return new ItemStack(ItemRegistry.crabrawItem, 1, 0);
				case 11:
					return new ItemStack(ItemRegistry.crayfishrawItem, 1, 0);
				case 12:
					return new ItemStack(ItemRegistry.eelrawItem, 1, 0);
				case 13:
					return new ItemStack(ItemRegistry.frograwItem, 1, 0);
				case 14:
					return new ItemStack(ItemRegistry.grouperrawItem, 1, 0);
				case 15:
					return new ItemStack(ItemRegistry.herringrawItem, 1, 0);
				case 16:
					return new ItemStack(ItemRegistry.jellyfishrawItem, 1, 0);
				case 17:
					return new ItemStack(ItemRegistry.mudfishrawItem, 1, 0);
				case 18:
					return new ItemStack(ItemRegistry.octopusrawItem, 1, 0);
				case 19:
					return new ItemStack(ItemRegistry.perchrawItem, 1, 0);
				case 20:
					return new ItemStack(ItemRegistry.scalloprawItem, 1, 0);
				case 21:
					return new ItemStack(ItemRegistry.shrimprawItem, 1, 0);
				case 22:
					return new ItemStack(ItemRegistry.snailrawItem, 1, 0);
				case 23:
					return new ItemStack(ItemRegistry.snapperrawItem, 1, 0);
				case 24:
					return new ItemStack(ItemRegistry.tilapiarawItem, 1, 0);
				case 25:
					return new ItemStack(ItemRegistry.troutrawItem, 1, 0);
				case 26:
					return new ItemStack(ItemRegistry.tunarawItem, 1, 0);
				case 27:
					return new ItemStack(ItemRegistry.turtlerawItem, 1, 0);
				case 28:
					return new ItemStack(ItemRegistry.walleyerawItem, 1, 0);
				case 29:
					return new ItemStack(Items.FISH, 1, 0);
				case 30:
					return new ItemStack(Items.FISH, 1, 0);
				case 31:
					return new ItemStack(ItemRegistry.greenheartfishItem, 1, 0);
			}

		}
		return null;

	}

	private int getRunTime(ItemStack stack) {
		if(!stack.isEmpty() && stack.getItem() == ItemRegistry.fishtrapbaitItem) {
			return 3200;
		}
		return 0;
	}

	private int getRunTime() {
		final int radius = 2;
		// final World world = world;
		final int varX = pos.getX();
		final int varY = pos.getY();
		final int varZ = pos.getZ();

		int speed = 3500;

		for(int offsetX = -radius; offsetX <= radius; ++offsetX) {
			for(int offsetZ = -radius; offsetZ <= radius; ++offsetZ) {
				if(offsetX * offsetX + offsetZ * offsetZ > radius * radius
						|| offsetX == -radius - 1 && offsetZ == -radius - 1
						|| offsetX == radius - 1 && offsetZ == radius - 1
						|| offsetX == radius - 1 && offsetZ == -radius - 1
						|| offsetX == -radius - 1 && offsetZ == radius - 1)
					continue;
				final BlockPos pos = new BlockPos(varX + offsetX, varY, varZ + offsetZ);
				if (!world.isBlockLoaded(pos)) continue;
				final Block blockAtCoords = world.getBlockState(pos).getBlock();
				if(blockAtCoords instanceof BlockLiquid) {
					speed = (int) (speed * 0.95);
				}
				if(blockAtCoords != BlockRegistry.watertrap)
					continue;
				speed = (int) (speed / 0.85);
			}
		}
		return speed;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

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

	public boolean canInteractWith(EntityPlayer playerIn) {
		// If we are too far away from this tile entity you cannot use it
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	public String getGuiID() {
		return "harvestcraft:watertrap";
	}
}

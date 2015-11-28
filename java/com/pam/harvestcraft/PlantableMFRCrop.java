package com.pam.harvestcraft;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;

public class PlantableMFRCrop implements IFactoryPlantable
	{
	    protected Item _sourceId;
	    protected Block _plantedBlockId;

	    public PlantableMFRCrop(Item sourceId, Block plantedBlockId)
	    {
	        this._sourceId = sourceId;
	        this._plantedBlockId = plantedBlockId;
	    }
	    public boolean canBePlanted(ItemStack stack, boolean forFermenting) {
	        return true;//TODO figure out what on earth this is supposed to be
	    }
	    @Override
	    public boolean canBePlantedHere (World world, int x, int y, int z, ItemStack stack)
	    {
			if(!world.isAirBlock(x, y, z))
				return false;
			
			Block ground = world.getBlock(x, y - 1, z);
			return ((ground.equals(Blocks.farmland)) ||
					(ground.equals(Blocks.grass)) ||
					(ground.equals(Blocks.dirt)) ||
					(_plantedBlockId.canPlaceBlockAt(world, x, y, z) && _plantedBlockId.canReplace(world, x, y, z, 0, stack)) ||
					(_plantedBlockId instanceof IPlantable && ground != null && ground.canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable)_plantedBlockId))
					);
	    }

	    @Override
	    public void prePlant (World world, int x, int y, int z, ItemStack stack)
	    {
			Block ground = world.getBlock(x, y - 1, z);
			if (ground.equals(Blocks.grass) || ground.equals(Blocks.dirt))
			{
				world.setBlock(x, y - 1, z, Blocks.farmland);
			}
	        return;
	    }

	    @Override
	    public void postPlant (World world, int x, int y, int z, ItemStack stack)
	    {
	        return;
	    }
	    @Override
	    public ReplacementBlock getPlantedBlock (World world, int x, int y, int z, ItemStack stack)
	    {
	        if (stack.getItem() != _sourceId)
	        {
	            return new ReplacementBlock(Blocks.air);
	        }
	        return new ReplacementBlock(_plantedBlockId).setMeta(getMeta(stack));

	    }

	    @Override
	    public Item getSeed ()
	    {
	        return _sourceId;
	    }
	    public int getMeta (ItemStack i) {
	        return i.getItemDamage();
	    }

}
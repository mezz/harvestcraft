package com.pam.harvestcraft.blocks;
import java.io.PrintStream;

import com.pam.harvestcraft.harvestcraft;
import com.pam.harvestcraft.tileentity.TileEntityMarket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPamMarket extends BlockContainer
{

  
  public BlockPamMarket(Material par2Material)
  {
	super(par2Material);
	setStepSound(Block.soundTypeWood);
	this.setCreativeTab(harvestcraft.modTab);
  }
  
  public TileEntity createNewTileEntity(World world)
  {
	return new TileEntityMarket();
  }
  


  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xCoord, float yCoord, float zCoord)
  {
	  TileEntity tile = world.getTileEntity(x, y, z);
	  if ((tile == null) || (player.isSneaking()))
    {
		  return false;
    }
	  System.out.println("Trying to open");
	  player.openGui(harvestcraft.instance, 0, world, x, y, z);
	  return true;
  }

  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
  {
	return new TileEntityMarket();
  }
}

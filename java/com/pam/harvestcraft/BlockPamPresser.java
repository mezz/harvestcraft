package com.pam.harvestcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPamPresser extends BlockContainer
{
  private IIcon top;
  private IIcon front;
  private IIcon middle;
  private IIcon bottom;
  
  public BlockPamPresser()
  {
    super(Material.wood);
    setStepSound(Block.soundTypeWood);
    this.setCreativeTab(harvestcraft.tabHarvestCraft);
  }
  
  public TileEntity createNewTileEntity(World world) {
    return new TileEntityPamPresser();
  }
  
  public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return Item.getItemFromBlock(BlockRegistry.pamPresser);
  }
  
  public void onBlockPlacedBy(World par1World, int xcoord, int ycoord, int zcoord, EntityLivingBase par5Entity, ItemStack par6Ist)
  {
    int l = MathHelper.floor_double(par5Entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
    
    if (l == 0)
    {
      par1World.setBlockMetadataWithNotify(xcoord, ycoord, zcoord, 2, 2);
    }
    
    if (l == 1)
    {
      par1World.setBlockMetadataWithNotify(xcoord, ycoord, zcoord, 5, 2);
    }
    
    if (l == 2)
    {
      par1World.setBlockMetadataWithNotify(xcoord, ycoord, zcoord, 3, 2);
    }
    
    if (l == 3)
    {
      par1World.setBlockMetadataWithNotify(xcoord, ycoord, zcoord, 4, 2);
    }
    
    if (par6Ist.hasDisplayName())
    {
      ((TileEntityOven)par1World.getTileEntity(xcoord, ycoord, zcoord)).func_145951_a(par6Ist.getDisplayName());
    }
  }
  
  public void breakBlock(World par1World, int xcoord, int ycoord, int zcoord, Block par5Block, int par6Int) {
      TileEntityPamPresser blockTileEntity = (TileEntityPamPresser)par1World.getTileEntity(xcoord, ycoord, zcoord);
      if (blockTileEntity != null) {
          for (int i=0; i<blockTileEntity.getSizeInventory(); i++) {
              ItemStack ist = blockTileEntity.getStackInSlot(i);
              if (ist != null) {
                  EntityItem enit = new EntityItem(par1World, xcoord+0.5, ycoord+0.5, zcoord+0.5, new ItemStack(ist.getItem(), ist.stackSize, ist.getItemDamage()));
                  if (ist.hasTagCompound()) enit.getEntityItem().setTagCompound((NBTTagCompound)ist.getTagCompound().copy());
                  par1World.spawnEntityInWorld(enit);
              }
          }
          par1World.func_147453_f(xcoord, ycoord, zcoord, par5Block);
      }
      super.breakBlock(par1World, xcoord, ycoord, zcoord, par5Block, par6Int);
  }
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xCoord, float yCoord, float zCoord)
  {
    TileEntity tile = world.getTileEntity(x, y, z);
    
    if ((tile == null) || (player.isSneaking()))
    {
      return false;
    }
    
    player.openGui(harvestcraft.instance, 1, world, x, y, z);
    
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister par1IconRegister)
  {
    this.middle = par1IconRegister.registerIcon("harvestcraft:presserside");
    this.front = par1IconRegister.registerIcon("harvestcraft:presserfront");
    this.top = par1IconRegister.registerIcon("harvestcraft:pressertop");
    this.bottom = par1IconRegister.registerIcon("harvestcraft:sinkbottom_0");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2)
  {
    if(par1 == 0) return this.bottom;
    if(par1 == 1) return this.top;
    if(par1 == par2) return this.front;
    return this.middle;
  }
  
  public TileEntity createNewTileEntity(World var1, int var2)
  {
    return new TileEntityPamPresser();
  }
}
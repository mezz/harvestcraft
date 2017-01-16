package com.pam.harvestcraft.blocks.blocks;

import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.gui.GuiHandler;
import com.pam.harvestcraft.tileentities.TileEntityGroundTrap;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GroundTrapBlock extends BlockContainerRotating {

    public static final String registryName = "groundtrap";
    private static boolean keepInventory;

    public GroundTrapBlock() {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        setCreativeTab(HarvestCraft.modTab);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGroundTrap();
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	keepInventory = true;
        if (!keepInventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityGroundTrap)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityGroundTrap)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        final TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (tileEntity == null) {
            return false;
        }

        playerIn.openGui(HarvestCraft.instance, GuiHandler.GUIID_GROUND_TRAP, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}

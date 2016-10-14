package com.pam.harvestcraft.gui;

import com.pam.harvestcraft.tileentities.TileEntityApiary;
import com.pam.harvestcraft.tileentities.TileEntityGroundTrap;
import com.pam.harvestcraft.tileentities.TileEntityMarket;
import com.pam.harvestcraft.tileentities.TileEntityPresser;
import com.pam.harvestcraft.tileentities.TileEntityShippingBin;
import com.pam.harvestcraft.tileentities.TileEntityWaterTrap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity instanceof TileEntityMarket) {
            return new ContainerMarket(player.inventory, (TileEntityMarket) tileEntity);
        }

        if (tileEntity instanceof TileEntityShippingBin) {
            return new ContainerShippingBin(player.inventory, (TileEntityShippingBin) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityApiary) {
            return new ContainerApiary(player.inventory, (TileEntityApiary) tileEntity);
        }

        if (tileEntity instanceof TileEntityPresser) {
            return new ContainerPresser(player.inventory, (TileEntityPresser) tileEntity);
        }

        if (tileEntity instanceof TileEntityGroundTrap) {
            return new ContainerGroundTrap(player.inventory, (TileEntityGroundTrap) tileEntity);
        }
        if (tileEntity instanceof TileEntityWaterTrap) {
            return new ContainerWaterTrap(player.inventory, (TileEntityWaterTrap) tileEntity);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity instanceof TileEntityMarket) {
            return new GuiMarket(player.inventory, (TileEntityMarket) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityShippingBin) {
            return new GuiShippingBin(player.inventory, (TileEntityShippingBin) tileEntity);
        }

        if (tileEntity instanceof TileEntityApiary) {
            return new GuiApiary(player.inventory, (TileEntityApiary) tileEntity);
        }

        if (tileEntity instanceof TileEntityPresser) {
            return new GuiPresser(player.inventory, (TileEntityPresser) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityGroundTrap) {
            return new GuiGroundTrap(player.inventory, (TileEntityGroundTrap) tileEntity);
        }
        
        if (tileEntity instanceof TileEntityWaterTrap) {
            return new GuiWaterTrap(player.inventory, (TileEntityWaterTrap) tileEntity);
        }

        return null;
    }
}

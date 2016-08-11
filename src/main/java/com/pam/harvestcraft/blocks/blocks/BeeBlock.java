package com.pam.harvestcraft.blocks.blocks;

import com.pam.harvestcraft.HarvestCraft;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BeeBlock extends Block{



    public BeeBlock() {
        super(Material.GROUND);
        setSoundType(SoundType.WOOD);
        setCreativeTab(HarvestCraft.modTab);
    }
}
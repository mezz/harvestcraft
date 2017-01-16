package com.pam.bonecraft;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPamFossil extends BlockOre
{
	
	 public static final String registryName = "pamfossil";
	 public BlockPamFossil() {
	        super();
	        setSoundType(SoundType.STONE);
	        setCreativeTab(bonecraft.tabBonecraft);
	    }
	@Override
	public int quantityDropped(Random p_149745_1_)
    {
        return this == BlockRegistry.pamFossil ? 1 + p_149745_1_.nextInt(3) : 1;
    }
	@Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.BONE;
    }
	
	@Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == BlockRegistry.pamFossil)
            {
                i = MathHelper.getInt(rand, 0, 2);
            }
           

            return i;
        }
        return 0;
    }

}

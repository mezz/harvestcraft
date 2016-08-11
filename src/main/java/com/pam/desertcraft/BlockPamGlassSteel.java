package com.pam.desertcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPamGlassSteel extends BlockGlass {

	public static final String registryName = "pamGlasssteel";
	
	public BlockPamGlassSteel(Material materialIn, boolean ignoreSimilarity)
    {
        super(materialIn, ignoreSimilarity);
		setCreativeTab(desertcraft.tabDesertcraft);
	}
	
	@SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
   
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int damageDropped(int i)
    {
        return i;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

}

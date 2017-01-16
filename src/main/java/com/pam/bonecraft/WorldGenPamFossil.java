package com.pam.bonecraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenPamFossil implements IWorldGenerator 
{
		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
	        switch (world.provider.getDimension()) {
	            case 0: GenerateOverworld(random, chunkX * 16, chunkZ * 16, world); break;
	        }
	    }
		
		private void GenerateOverworld(Random random, int x, int z, World world) 
		{
				this.addOreSpawn(BlockRegistry.pamFossil, world, random, x, z, 2, 4, bonecraft.fossilrarity, 0, 128);
	    }
		
		 public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY )
		    {
		        WorldGenMinable minable = new WorldGenMinable(block.getDefaultState(), 6);
		        for(int i = 0; i < chancesToSpawn; i++)
		        {
		            int posX = blockXPos + random.nextInt(16);
		            int posY = minY + random.nextInt(maxY - minY);
		            int posZ = blockZPos + random.nextInt(16);
		            BlockPos quisquePos = new BlockPos(posX, posY, posZ);
		            minable.generate(world, random, quisquePos);
		        }
		    }



}
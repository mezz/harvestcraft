package com.pam.desertcraft;

import java.util.Random;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenDesertPlant implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        final int xChunk = chunkX * 16 + 8, zChunk = chunkZ * 16 + 8;
        int xCh = chunkX * 16 + random.nextInt(16);
        int yCh = random.nextInt(128);
        int zCh = chunkZ * 16 + random.nextInt(16);

        final Biome biome = world.getBiomeForCoordsBody(new BlockPos(xChunk + 16, 0, zChunk + 16));
        final BlockPos blockPos = new BlockPos(xCh, yCh + 64, zCh);
        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD)) {
            return;
        }


        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MESA))) {
        	
        	int rand = ThreadLocalRandom.current().nextInt(0,15);
        	switch(rand) {
        	  case 0:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant1), world, blockPos); break;
        	  case 1:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant2), world, blockPos); break;
        	  case 2:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant3), world, blockPos); break;
        	  case 3:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant4), world, blockPos); break;
        	  case 4:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant5), world, blockPos); break;
        	  case 5:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant6), world, blockPos); break;
        	  case 6:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant7), world, blockPos); break;
        	  case 7:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant8), world, blockPos); break;
        	  case 8:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant9), world, blockPos); break;
        	  case 9:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant10), world, blockPos); break;
        	  case 10:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant11), world, blockPos); break;
        	  case 11:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant12), world, blockPos); break;
        	  case 12:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant13), world, blockPos); break;
        	  case 13:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant14), world, blockPos); break;
        	  case 14:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant15), world, blockPos); break;
        	  case 15:generateGarden(BlockRegistry.getGarden(BlockRegistry.desertplant16), world, blockPos); break;
        	}
        	}
}


    private void generateGarden(BlockPamDesertPlant gardenBlock, World world, BlockPos pos) {

        final int tries = 8 * desertcraft.desertplantrarity;

        for (int tryNum = 0; tryNum < tries; tryNum++) {
            int posX = (pos.getX() + world.rand.nextInt(8)) - world.rand.nextInt(8);
            int posY = (pos.getY() + world.rand.nextInt(4)) - world.rand.nextInt(4);
            int posZ = (pos.getZ() + world.rand.nextInt(8)) - world.rand.nextInt(8);

            final BlockPos newPos = new BlockPos(posX, posY, posZ);

            if (gardenBlock.canPlaceBlockAt(world, newPos)) {
                world.setBlockState(newPos, gardenBlock.getDefaultState(), 2);
            }
        }
    }

}

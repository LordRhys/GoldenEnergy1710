package com.lordrhys.mod.lib;

import java.util.Random;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.worldgen.GoldenWorldGenLakes;
import com.lordrhys.mod.worldgen.GoldenWorldGenTrees;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class EventManager implements IWorldGenerator 
{
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		case 2:
			generateOtherDimension(world, random, chunkX * 16, chunkZ * 16);
		}
		
	}

	private void generateEnd(World world, Random random, int x, int z)
	{
		this.addOreSpawn(LordRhysModMain.lightDirt, world, random, x, z, 16, 16, 6 + random.nextInt(3), 5, 15, 60);
		
	}

	private void generateSurface(World world, Random random, int x, int z)
	{
		this.addOreSpawn(LordRhysModMain.lightDirt, world, random, x, z, 16, 16, 6 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreCopper, world, random, x, z, 16, 16, 8 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreNickel, world, random, x, z, 16, 16, 7 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreTin, world, random, x, z, 16, 16, 7 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreVoid, world, random, x, z, 16, 16, 8 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreRandomite, world, random, x, z, 16, 16, 8 + random.nextInt(4), 8, 15, 90);
		
		for (int t = 0; t < 4; t++)
		{
			int chunkX = x + random.nextInt(16) + 8;
			int chunkY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
			int chunkZ = z + random.nextInt(16) + 8;
			
			if (random.nextInt(4) == 0)
			{
				(new GoldenWorldGenLakes(LordRhysModMain.acidLiquid)).generate(world,random,chunkX, chunkY, chunkZ);
			}
			else
			{
				(new GoldenWorldGenLakes(LordRhysModMain.lightLiquid)).generate(world,random,chunkX, chunkY, chunkZ);
			}			
		}
		
		for (int t = 0; t < 16; t++)
		{
			int chunkX = x + random.nextInt(16);
			int chunkY = random.nextInt(90);
			int chunkZ = z + random.nextInt(16);
			
			(new GoldenWorldGenTrees(false, 8, 0, 0, false)).generate(world,random,chunkX, chunkY, chunkZ);
		}		
	}
	
	private void generateOtherDimension(World world, Random random, int x, int z)
	{
		this.addOreSpawn(LordRhysModMain.lightDirt, world, random, x, z, 16, 16, 8 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreCopper, world, random, x, z, 16, 16, 8 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreNickel, world, random, x, z, 16, 16, 7 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreTin, world, random, x, z, 16, 16, 7 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreVoid, world, random, x, z, 16, 16, 5 + random.nextInt(3), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreRandomite, world, random, x, z, 16, 16, 8 + random.nextInt(4), 8, 15, 90);
		this.addOreSpawn(LordRhysModMain.oreRhodium, world, random, x, z, 16, 16, 6 + random.nextInt(4), 8, 15, 90);
		
		/*for (int t = 0; t < 4; t++)
		{
			int chunkX = x + random.nextInt(16) + 8;
			int chunkY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
			int chunkZ = z + random.nextInt(16) + 8;
			
			if (random.nextInt(4) == 0)
			{
				(new GoldenWorldGenLakes(LordRhysModMain.acidLiquid.blockID)).generate(world,random,chunkX, chunkY, chunkZ);
			}
			else
			{
				(new GoldenWorldGenLakes(LordRhysModMain.lightLiquid.blockID)).generate(world,random,chunkX, chunkY, chunkZ);
			}			
		}*/
		
		for (int t = 0; t < 16; t++)
		{
			int chunkX = x + random.nextInt(16);
			int chunkY = random.nextInt(90);
			int chunkZ = z + random.nextInt(16);
			
			(new GoldenWorldGenTrees(false, 8, 0, 0, false)).generate(world,random,chunkX, chunkY, chunkZ);
		}		
	}

	private void generateNether(World world, Random random, int x, int z)
	{
		this.addOreSpawn(LordRhysModMain.lightDirt, world, random, x, z, 16, 16, 4 + random.nextInt(3), 5, 15, 50);
		
	}
	
	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX,
			int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
		}
	}

}

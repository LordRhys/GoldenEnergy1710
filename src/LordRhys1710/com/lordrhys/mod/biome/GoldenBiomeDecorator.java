package com.lordrhys.mod.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.worldgen.GoldenWorldGenLakes;
import com.lordrhys.mod.worldgen.GoldenWorldGenTrees;

public class GoldenBiomeDecorator extends BiomeDecorator
{
	protected boolean generateLakes;
	protected int bigMushroomsPerChunk;
	protected int cactiPerChunk;
	protected int treesPerChunk;
	protected int flowersPerChunk;
	
	public GoldenBiomeDecorator(BiomeGenBase biomeBase) 
	{
		super();	
	}
	
	public void decorate(World world, Random rand, int chunk_X, int chunk_Z)
    {
		int k, l, i1;
        
		//super.decorateChunk(world, rand, chunk_X, chunk_Z);
        WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(LordRhysModMain.blueFlower);
        WorldGenFlowers purpleFlowerGenerator = new WorldGenFlowers(LordRhysModMain.purpleFlower);
        WorldGenerator goldenTreeGenerator = new GoldenWorldGenTrees(false, 8, 0, 0, false);
        WorldGenerator giantMushroomGen = new WorldGenBigMushroom();
        WorldGenerator genLakesOfLight = new GoldenWorldGenLakes(LordRhysModMain.lightLiquid);
        
        boolean doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, FLOWERS);
        for (int j = 0; doGen && j < flowersPerChunk; ++j)
        {
            k = chunk_X + rand.nextInt(16) + 8;
            l = rand.nextInt(128);
            i1 = chunk_Z + rand.nextInt(16) + 8;
            purpleFlowerGenerator.generate(world, rand, k, l, i1);
            
            if (rand.nextInt(4) == 0)
            {
                k = chunk_X + rand.nextInt(16) + 8;
                l = rand.nextInt(128);
                i1 = chunk_Z + rand.nextInt(16) + 8;
                blueFlowerGenerator.generate(world, rand, k, l, i1);
            }
        }
                
        doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, TREE);
        for (int j = 0; doGen && j < treesPerChunk; ++j)
        {
            k = chunk_X + rand.nextInt(16) + 8;
            l = chunk_Z + rand.nextInt(16) + 8;
            //goldenTreeGenerator = getRandomWorldGenForTrees(rand);
            //goldenTreeGenerator.setScale(1.0D, 1.0D, 1.0D);
            goldenTreeGenerator.generate(world, rand, k, world.getHeightValue(k, l), l);
        }
        
        doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, BIG_SHROOM);
        for (int j = 0; doGen && j < bigMushroomsPerChunk; ++j)
        {
            k = chunk_X + rand.nextInt(16) + 8;
            l = chunk_Z + rand.nextInt(16) + 8;
            giantMushroomGen.generate(world, rand, k, world.getHeightValue(k, l), l);
        }
        
        doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, LAKE);                            
        if (doGen && this.generateLakes)
        {
			for (int j = 0; j < 40; ++j)
		    {
		        k = chunk_X + rand.nextInt(16) + 8;
		        l = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
		        i1 = chunk_Z + rand.nextInt(16) + 8;
		        genLakesOfLight.generate(world, rand, k, l, i1);
		    }        	 
        }
    }

}

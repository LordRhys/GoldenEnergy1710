package com.lordrhys.mod.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.worldgen.GoldenWorldGenLakes;
import com.lordrhys.mod.worldgen.GoldenWorldGenTrees;

public class BiomeGoldenBiome extends BiomeGenBase
{
	//private GoldenBiomeDecorator myBiomeDecorator;
	
	public BiomeGoldenBiome(int id, boolean register)
	{
		super(id,register);
	}
	public BiomeGoldenBiome(int id)
	{
		super(id);
		this.topBlock = LordRhysModMain.goldenGrass;
		this.fillerBlock = LordRhysModMain.goldenDirt;
		this.setTemperatureRainfall(0.9F, 1.0F);
		this.setHeight(height_LowIslands);
		//myBiomeDecorator = new GoldenBiomeDecorator(this);
		this.theBiomeDecorator.generateLakes = true;
		this.theBiomeDecorator.bigMushroomsPerChunk = 5;
		this.theBiomeDecorator.cactiPerChunk = 3;
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 6;
		
		this.theBiomeDecorator.goldGen = new WorldGenMinable(Blocks.gold_ore, 28);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class,20,1,10));
		//this.spawnableMonsterList.add(new SpawnListEntry(EntityReddyFreddy.class,20,1,5));		
	}
	
	public void decorate(World world, Random rand, int chunk_X, int chunk_Z)
    {
		int k, l, i1;
        
		super.decorate(world, rand, chunk_X, chunk_Z);
        WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(LordRhysModMain.blueFlower);
        WorldGenFlowers purpleFlowerGenerator = new WorldGenFlowers(LordRhysModMain.purpleFlower);
        WorldGenerator goldenTreeGenerator = new GoldenWorldGenTrees(false, 6, 0, 0, false);
        WorldGenerator giantMushroomGen = new WorldGenBigMushroom();
        WorldGenerator genLakesOfLight = new GoldenWorldGenLakes(LordRhysModMain.lightLiquid);
        
        boolean doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, FLOWERS);
        for (int j = 0; doGen && j < 6; ++j)
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
        for (int j = 0; doGen && j < 4; ++j)
        {
            k = chunk_X + rand.nextInt(16) + 8;
            l = chunk_Z + rand.nextInt(16) + 8;
            //goldenTreeGenerator = getRandomWorldGenForTrees(rand);
            goldenTreeGenerator.setScale(1.0D, 1.0D, 1.0D);
            goldenTreeGenerator.generate(world, rand, k, world.getHeightValue(k, l), l);
        }
        
        doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, BIG_SHROOM);
        for (int j = 0; doGen && j < 5; ++j)
        {
            k = chunk_X + rand.nextInt(16) + 8;
            l = chunk_Z + rand.nextInt(16) + 8;
            giantMushroomGen.generate(world, rand, k, world.getHeightValue(k, l), l);
        }
        
        doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, LAKE);                            
        //if (doGen && this.theBiomeDecorator.generateLakes)
        //{
    	for (int j = 0; j < 30; ++j)
        {
            k = chunk_X + rand.nextInt(16) + 8;
            l = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
            i1 = chunk_Z + rand.nextInt(16) + 8;
            genLakesOfLight.generate(world, rand, k, l, i1);
        }        	 
        // }
    }

}

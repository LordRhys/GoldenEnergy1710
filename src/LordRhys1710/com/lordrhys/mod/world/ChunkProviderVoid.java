package com.lordrhys.mod.world;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class ChunkProviderVoid implements IChunkProvider 
{
	private Random rand;
	
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	
	
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCaves();
	private MapGenStronghold strongholdGenerator = new MapGenStronghold();
	private MapGenVillage villageGenerator = new MapGenVillage();
	private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
	private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
	private MapGenBase ravineGenerator = new MapGenRavine();
	
	private BiomeGenBase[] biomesforGeneration;
	
	public double[] noise1;
	public double[] noise2;
	public double[] noise3;
	public double[] noise5;
	public double[] noise6;
	
	public float[] parabolicField;
	public int[][] field = new int[32][32]; 
	
	{
		caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
		strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGenerator, STRONGHOLD);
		villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(villageGenerator,VILLAGE);
		mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGenerator, MINESHAFT);
		scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator,SCATTERED_FEATURE);
		ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator,RAVINE);
	}
	
	public ChunkProviderVoid(World worldObj, long seed, boolean features) 
	{
		this.worldObj = worldObj;
		this.mapFeaturesEnabled = features;
		this.rand = new Random(seed);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand,16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand,16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand,8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand,4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand,10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand,16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand,8);
		
		NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6, mobSpawnerNoise};
		noiseGens = TerrainGen.getModdedNoiseGenerators(worldObj, rand, noiseGens);
		
		this.noiseGen1 = (NoiseGeneratorOctaves) noiseGens[0];
		this.noiseGen2 = (NoiseGeneratorOctaves) noiseGens[1];
		this.noiseGen3 = (NoiseGeneratorOctaves) noiseGens[2];
		this.noiseGen4 = (NoiseGeneratorOctaves) noiseGens[3];
		this.noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
		this.noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
		this.mobSpawnerNoise = (NoiseGeneratorOctaves) noiseGens[6];
	}
	
	public Chunk provideChunk(int i, int j) 
	{
		this.rand.setSeed((long)i * 341873128712L + (long)j * 132897987541L);
		
		Block[] ablock = new Block[32768];
		this.generateTerrain(i,j,ablock);
		this.biomesforGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesforGeneration, i * 16, j * 16, 16, 16);
		this.replaceBlocksForBiome(i,j, ablock, this.biomesforGeneration);
		this.caveGenerator.func_151539_a(this, this.worldObj, i, j, ablock);
		this.ravineGenerator.func_151539_a(this, this.worldObj, i, j, ablock);
		
		if(mapFeaturesEnabled)
		{
			this.mineshaftGenerator.func_151539_a(this, this.worldObj, i, j, ablock);
			this.villageGenerator.func_151539_a(this, this.worldObj, i, j, ablock);
			this.strongholdGenerator.func_151539_a(this, this.worldObj, i, j, ablock);
			this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, i, j, ablock);
		}
		
		Chunk chunk = new Chunk(this.worldObj,ablock,i,j);
		byte[] byteArray2 = chunk.getBiomeArray();
		
		for (int k = 0; k < byteArray2.length; k++)
		{
			byteArray2[k] = (byte) this.biomesforGeneration[k].biomeID;
		}
		
		chunk.generateSkylightMap();
		return chunk;
	}

	public void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] ablock, BiomeGenBase[] biomesforGeneration2) 
	{
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, ablock, biomesforGeneration2);
		MinecraftForge.EVENT_BUS.post(event);
		
		if(event.getResult() == Result.DENY) return;
		
		byte b = 63;
		double d = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(stoneNoise, chunkX*16, chunkZ*16, 0, 16, 16, 1, d*2D, d*2D, d*2D);
		
		for(int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				BiomeGenBase biome = biomesforGeneration2[z + x*16];
				float temperature = biome.getFloatTemperature(0,0,0);
				int i = (int) (this.stoneNoise[z + x*16] / 3D + 3D + this.rand.nextDouble() * 0.25D);
				int j = -1;
				Block b1 = biome.topBlock;
				Block b2 = biome.fillerBlock;
				
				for(int k = 127; k >= 0; k--)
				{
					int l = (z*16 + x) * 128 + k;
					if(k <= this.rand.nextInt(5))
					{
						ablock[l] = Blocks.bedrock;
					}
					else
					{
						Block b3 = ablock[l];
						if(b3 == Blocks.air)
						{
							j = -1;
						}
						else if(b3 == Blocks.stone)
						{
							if(j == -1)
							{
								if(i <= 0)
								{
									b1 = Blocks.air;
									b2 = Blocks.stone;
								}
								else if(k >= b - 4 && k  <= b + 1) //Generating Land
								{
									b1 = biome.topBlock;
									b2 = biome.fillerBlock;
								}
								//Generating Water
								if(k < b && b1 == Blocks.air)
								{
									if(temperature < 0.15F)
									{
										b1 = Blocks.ice;
									}
									else
									{
										b1 = Blocks.water;
									}
								}
								j = i;
								
								if(k >= b-1)
								{
									ablock[l] = b1;
								}
								else
								{
									ablock[l] = b2;
								}
							}
							else if (j > 0)
							{
								j--;
								ablock[l] = b2;
								if(j ==0 && b2 == Blocks.sand)
								{
									j = this.rand.nextInt(4);
									b2 = Blocks.sandstone;
								}
							}
						}
					}
				}
			}
		}		
	}

	public void generateTerrain(int i, int j, Block[] ablock) 
	{
		byte b0 = 4;
		byte b1 = 16;
		byte b2 = 63;
		byte b3 = 17;
		
		int k = b0 + 1;
		int l = b0 + 1;
		
		this.biomesforGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(biomesforGeneration, i * 4 - 2, j * 4 - 2, k + 5, l + 5);
		this.noiseArray = this.initializeNoiseField(this.noiseArray, i*b0,0,j*b0,k,b3,l);
		
		for(int i1 = 0; i1 < b0; i1++)
		{
			for(int j1 = 0; j1 < b0; j1++)
			{
				for(int k1 = 0; k1 < b1; k1++)
				{
					double d0 = 0.125D;
					double d1 = this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 0];
					double d2 = this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 0];
					double d3 = this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 0];
					double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 0];
					double d5 = (this.noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1 + 1] - d1) * d0;
					double d6 = (this.noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1 + 1] - d2) * d0;
					double d7 = (this.noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1 + 1] - d3) * d0;
					double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1 + 1] - d4) * d0;
					
					for(int i2 = 0; i2 < b0; i2++)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						
						for(int j2 = 0; j2 < b0; j2++)
						{
							int j3 = j2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + i2;
							short short1 = 128;
							j2 -= short1;
							double d14 = 0.25D;
							double d15 = (d11 - d10) * d14;
							double d16 = d10 - d15;
							
							for(int k2 = 0; k2 < b1; k2++)
							{
								if((d16 += d15) > 0.0D)
								{
									ablock[j3 += short1] = Blocks.stone;
								}
								else if(k1 * 8 + i2 < b2)
								{
									ablock[j3 += short1] = Blocks.water;
								}
								else
								{
									ablock[j3 += short1] = Blocks.air;
								}
							}
							
							d10 += d12;
							d11 += d13;
						}
						
						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}		
	}

	private double[] initializeNoiseField(double[] noisefield, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ) 
	{
		ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, noisefield, posX, posY, posZ, sizeX, sizeY, sizeZ);
		MinecraftForge.EVENT_BUS.post(event);
		
		if(event.getResult() == Result.DENY) return event.noisefield;
		
		if(noisefield == null) noisefield = new double[sizeX * sizeY * sizeZ];
		
		if(this.parabolicField == null)
		{
			this.parabolicField = new float[25];
			
			for(int k1 = -2; k1 <= 2; k1++)
			{
				for(int l1 = -2; l1 <= 2; l1++)
				{
					float f = 10F / MathHelper.sqrt_float((float)(k1 * k1 + l1*l1) + 0.2F);
					this.parabolicField[k1 + 2 + (l1 + 2) * 5] = f;
				}
			}
		}
		
		double d0 = 684.412D;
		double d1 = 684.412D;
		
		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, posX, posZ, sizeX, sizeZ, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, posX, posZ, sizeX, sizeZ, 200D, 200D, 0.5D);
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, posX, posZ, sizeX, sizeZ, sizeY, sizeZ, d0 / 80D, d1 / 160D, d0 / 80D);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, posX, posZ, sizeX, sizeZ, sizeY, sizeZ, d0, d1, d0);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, posX, posZ, sizeX, sizeZ, sizeY, sizeZ, d0, d1, d0);
		
		boolean flag = false;
		boolean flag1 = false;
		int i2 = 0;
		int j2 = 0;
		
		for(int k2 = 0; k2 < sizeX; k2++)
		{
			for(int l2 = 0; l2 < sizeZ; l2++)
			{
				float f1 = 0.0F;
				float f2 = 0.0F;
				float f3 = 0.0F;
				byte b0 = 2;
				BiomeGenBase biome = this.biomesforGeneration[k2 + 2 + (l2 + 2) * (sizeX + 5)];
				
				for(int i3 = -b0; i3 <= b0; i3++)
				{
					for(int j3 = -b0; j3 <= b0; j3++)
					{
						BiomeGenBase biome1 = this.biomesforGeneration[k2 + i3 + 2 + (l2 + j3 + 2) * (sizeX + 5)];
						float f4 = this.parabolicField[i3 + 2 + (j3 + 2) * 5] / (biome1.rootHeight + 2F);
						
						if(biome1.rootHeight > biome.rootHeight)
						{
							f4 /= 2F;
						}
						
						f1 += biome1.rootHeight * f4;
						f2 += biome1.heightVariation * f4;
						f3 += f4;
					}
				}
				
				f1 /= f3;
				f2 /= f3;
				f1 = f1 * 0.9F + 0.1F;
				f2 = (f2 * 4.0F - 0.1F) / 8F;
				double d2 = this.noise6[j2] / 8000D;
				
				if(d2 < 0D)
				{
					d2 = -d2 * 0.3D;
				}
				
				d2 = d2*3D - 2D;
				
				if(d2 < 0D)
				{
					d2 /= 2D;
					
					if(d2 < -1D)
					{
						d2 = 1D;
					}
					
					d2 /= 1.4D;
					d2 /= 2D;
				}
				else
				{
					if(d2 > 1D)
					{
						d2 = 1D;
					}
					
					d2 /= 8D;
				}
				
				j2++;
				
				for(int k3 = 0; k3 < sizeY; k3++)
				{
					double d3 = (double)f2;
					double d4 = (double)f1;
					d3 += d2*0.2D;
					double d5 = (double)sizeY/2D + d3*4D;
					double d6 = 0D;
					double d7 = ((double)k3 - 5) * 12D * 128D / 128D / d4; //TODO Pointless
					
					if(d7 < 0D)
					{
						d7 *= 4D;
					}
					
					double d8 = this.noise1[i2] / 512D;
					double d9 = this.noise2[i2] / 512D;
					double d10 = (this.noise3[i2] / 10D + 1D) / 2D;
					
					if(d10 < 0D)
					{
						d6 = d8;
					}
					else if(d10 > 1D)
					{
						d6 = d9;
					}
					else
					{
						d6 = d8 + (d9-d8)*d10;
					}
					
					d6 -= d7;
					
					if(k3 > sizeY-4)
					{
						double d11 = (double)((float)(k3 - (sizeY - 4)) / 3F); //TODO Pointless
						d6 = d6 * (1D - d11) + -10D*d11;
					}
					
					noisefield[i2] = d6;
					i2++;
				}
			}
		}
		
		return noisefield;
	}

	public Chunk loadChunk(int i, int j) 
	{
		return this.provideChunk(i, j);
	}
	
	public boolean chunkExists(int i, int j) 
	{
		return true;
	}

	public void populate(IChunkProvider ichunkprovider, int i, int j) 
	{
		BlockSand.fallInstantly = true;
		int k = i * 16;
		int l = j * 16;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());
		boolean flag = false;
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, flag));
		
		if (mapFeaturesEnabled)
		{
			this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, rand, i, j);
			flag = this.villageGenerator.generateStructuresInChunk(this.worldObj, rand, i, j);
			this.strongholdGenerator.generateStructuresInChunk(this.worldObj, rand, i, j);
			this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, rand, i, j);			
		}
		
		int k1;
		int l1;
		int i2;
		
		if (biome != BiomeGenBase.desert && biome != BiomeGenBase.desertHills && !flag && 
				this.rand.nextInt(4) == 0 && 
				TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, LAKE))
		{
			k1 = k + this.rand.nextInt(16) + 8;
			l1 = this.rand.nextInt(128);
			i2 = l + this.rand.nextInt(16) + 8;
			(new WorldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, k1, l1, i2);			
		}
		
		if (TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, LAVA) && !flag && this.rand.nextInt(8) == 0)
		{
			k1 = k + this.rand.nextInt(16) + 8;
			l1 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			i2 = l + this.rand.nextInt(16) + 8;
			if (l1 < 63 || this.rand.nextInt(10) == 0) 
			{
				(new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, k1, l1, i2);
			}
		}
		
		boolean doGen = TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, DUNGEON);
		for (k1 = 0; doGen && k1 < 8; k1++) //x position in chunk, chunks are 16 wide, dungeons are 8 wide, fits in one chunk
		{
			l1 = k + this.rand.nextInt(16) + 8;
			i2 = this.rand.nextInt(128); //y pos
			int j2 = l + this.rand.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(this.worldObj, this.rand, l1, i2, j2);
		}
		
		biome.decorate(worldObj, rand, k, l);
		SpawnerAnimals.performWorldGenSpawning(worldObj, biome, k + 8, l + 8, 16, 16, rand);
		
		k += 8;
		l += 8;
		
		doGen = TerrainGen.populate(ichunkprovider, worldObj, rand, i, j, flag, ICE);
		for (k1 = 0; doGen && k1 < 16; k1++) 
		{
			for (l1 = 0; l1 < 16; l1++) 
			{
				i2 = this.worldObj.getPrecipitationHeight(k + k1, l + l1);
				if(this.worldObj.isBlockFreezable(k + k1, i2 - 1, l + l1))
				{
					this.worldObj.setBlock(k + k1, i2 - 1, l1 + 1, Blocks.ice, 0, 2);
				}
				if(this.worldObj.canSnowAtBody(k + k1, i2, l + l1,true))
				{
					this.worldObj.setBlock(k + k1, i2, l1 + 1, Blocks.snow_layer, 0, 2);
				}				
			}
		}
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));
		BlockSand.fallInstantly = false;
	}

	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) 
	{
		return true;
	}

	public boolean unloadQueuedChunks() 
	{
		return false;
	}

	public boolean canSave() 
	{
		return true;
	}

	public String makeString() 
	{
		return "RandomLevelSource";
	}

	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) 
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
		return biome == null ? null : (biome == BiomeGenBase.swampland && enumcreaturetype == EnumCreatureType.monster &&
				this.scatteredFeatureGenerator.hasStructureAt(i, j, k) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() :
					biome.getSpawnableList(enumcreaturetype));
	}

	public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k) 
	{
		return "Stronghold".equals(s) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(world, i, j, k) : null;
	}

	public int getLoadedChunkCount() 
	{
		return 0;
	}

	public void recreateStructures(int i, int j) 
	{
		if(mapFeaturesEnabled)
		{
			this.mineshaftGenerator.func_151539_a(this, this.worldObj, i, j, (Block[])null);
			this.villageGenerator.func_151539_a(this, this.worldObj, i, j, (Block[])null);
			this.strongholdGenerator.func_151539_a(this, this.worldObj, i, j, (Block[])null);
			this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, i, j, (Block[])null);
		}
	}

	public void saveExtraData() 
	{
		
	}

	@Override
	public ChunkPosition func_147416_a(World var1, String var2, int var3,
			int var4, int var5) {
		// TODO Auto-generated method stub
		return null;
	}
}

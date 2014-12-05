package com.lordrhys.mod.world;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.world.*;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVoid extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(LordRhysModMain.goldenBiome, 0.2F);
		this.dimensionId = LordRhysModMain.dimensionIdVoid;
	}
	
	public IChunkProvider createChunkProvider()
	{
		return new ChunkProviderVoid(this.worldObj, this.worldObj.getSeed(), true);
	}
	
	public String getDimensionName() 
	{
		return "Void Dimension";
	}

}

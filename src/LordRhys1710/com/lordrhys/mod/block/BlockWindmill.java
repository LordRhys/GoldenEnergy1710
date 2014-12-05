package com.lordrhys.mod.block;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityWindmill;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWindmill extends BlockContainer
{

	public BlockWindmill(Material material) 
	{
		super(material);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) 
	{
		float pixel = 1F/16F;
		
		if(world.getBlockMetadata(x, y, z) < 7) 
		{
			this.setBlockBounds(pixel*4, 0, pixel*4, 1-pixel*4, 1, 1-pixel*4);
		}
		else
		{
			//this.setBlockBounds(0, 0, 0, 1, 1, 1);
			this.setBlockBounds(pixel*2, 0, pixel*2, 1-pixel*2, 1, 1-pixel*2);
		}
	}
	
	public int getRenderType()
    {
        return -1;
    }
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			FMLNetworkHandler.openGui(player, LordRhysModMain.instance, LordRhysModMain.guiIdWindmillGenerator, world, x, y, z);
			return true;
		}
		
		return true;
	}

	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new TileEntityWindmill(); 
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		if(world.getBlock(x, y+1, z).equals(LordRhysModMain.blockWindmill))	world.setBlockToAir(x, y+1, z);
		if(world.getBlock(x, y-1, z).equals(LordRhysModMain.blockWindmill))	world.setBlockToAir(x, y-1, z);
	}
	
	

}

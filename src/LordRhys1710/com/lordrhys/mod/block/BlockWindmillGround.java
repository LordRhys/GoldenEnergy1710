package com.lordrhys.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityWindmillFloor;

public class BlockWindmillGround extends BlockContainer
{

	public BlockWindmillGround(Material material) 
	{
		super(material);
		this.setBlockBounds(0, 0, 0, 1, (1F/16F)*16, 1);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) 
	{
		float pixel = 1F/16F;
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch(metadata ) 
		{
		case 0:
			this.setBlockBounds(0, 0, 0, 1, pixel*16, 1);
		case 1:
			this.setBlockBounds(0, 0, 0, 0.5F, pixel*16, 0.5F);
			break;
		case 2:
			this.setBlockBounds(0, 0, 0, 0.5F, pixel*16, 1);
			break;
		case 3:
			this.setBlockBounds(0, 0, 0.5F, 0.5F, pixel*16, 1);
			break;
		case 4:
			this.setBlockBounds(0, 0, 0, 1, pixel*16, 0.5F);
			break;
		case 5:
			this.setBlockBounds(0, 0, 0, 1, pixel*16, 1);
			break;
		case 6:
			this.setBlockBounds(0, 0, 0.5F, 1, pixel*16, 1);
			break;
		case 7:
			this.setBlockBounds(0.5F, 0, 0, 1, pixel*16, 0.5F);
			break;
		case 8:
			this.setBlockBounds(0.5F, 0, 0, 1, pixel*16, 1);
			break;
		case 9:
			this.setBlockBounds(0.5F, 0, 0.5F, 1, pixel*16, 1);
			break;			
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
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		updateMultiBlockStructure(world, x, y, z);
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
	{
		updateMultiBlockStructure(world, x, y, z);
	}
	
	public void updateMultiBlockStructure(World world, int x, int y, int z)
	{
		isMultiBlockStructure(world, x, y, z);
	}
	
	public boolean isMultiBlockStructure(World world, int x1, int y1, int z1)
	{
		boolean mStructure = false;
		boolean currentCheckStructure = true;
		
		for (int x2 = 0; x2 < 3; x2++) 
		{
			for (int z2 = 0; z2 < 3; z2++) 
			{
				if (!mStructure) 
				{
					currentCheckStructure = true;
					
					for (int x3 = 0; x3 < 3; x3++) 
					{
						for (int z3 = 0; z3 < 3; z3++) 
						{
							if (currentCheckStructure && !world.getBlock(x1+x2-x3, y1, z1+z2-z3)
									.equals(LordRhysModMain.blockWindmillGround)) 
							{
								currentCheckStructure = false;
							}
						}
					}
					if(currentCheckStructure)
					{
						for (int x3 = 0; x3 < 3; x3++) 
						{
							for (int z3 = 0; z3 < 3; z3++) 
							{
								world.setBlockMetadataWithNotify(x1+x2-x3, y1, z1+z2-z3, x3*3+z3+1, 2);
							}
						}
					}
				}
				mStructure = currentCheckStructure;
			}
		}
		if(mStructure) return true;
		if (world.getBlockMetadata(x1, y1, z1) > 0) 
		{
			world.setBlockMetadataWithNotify(x1, y1, z1, 0, 3);
		}
		return false;
	}
	
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new TileEntityWindmillFloor(); 
	}

}

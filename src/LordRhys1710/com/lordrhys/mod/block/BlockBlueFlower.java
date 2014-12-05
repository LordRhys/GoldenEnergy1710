package com.lordrhys.mod.block;

import java.util.List;
import java.util.Random;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBlueFlower extends BlockFlower
{

	public BlockBlueFlower(int type) 
	{
		super(type);
		this.setTickRandomly(true);
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
		this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	public Block getPlant(IBlockAccess world, int x, int y, int z) 
	{
		return this;
	}

	public int getPlantMetadata(World world, int x, int y, int z) 
	{
		return 0;
	}
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
    }

	
	public boolean isOpaqueCube()
    {
        return false;
    }

	
	public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 1;
    }
    
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	super.checkAndDropBlock(world, x, y, z);
    }

	public void onNeighborBlockChange(World world, int x, int y, int z, Block par5)
    {
        super.onNeighborBlockChange(world, x, y, z, par5);
        super.checkAndDropBlock(world, x, y, z);
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	public Item getItemDropped(int i, Random random, int par3)
	{
    	return Item.getItemFromBlock(this);
	}
	
	public boolean canPlaceBlockOn(Block par1)
    {
        return par1 == Blocks.grass|| par1 == Blocks.dirt || par1 == Blocks.farmland || 
        		par1 == LordRhysModMain.goldenGrass || par1 == LordRhysModMain.goldenDirt 
        		|| par1 == LordRhysModMain.tilledGoldenField;
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));       
    }
	
	public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));        
    }
}

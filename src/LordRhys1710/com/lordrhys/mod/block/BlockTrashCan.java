package com.lordrhys.mod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityTrashcan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTrashCan extends BlockContainer
{

	public BlockTrashCan() 
	{
		super(Material.rock);
		this.setBlockBounds(.1875F, 0F, .1875F, 0.8125F, 0.8125F, 0.8125F);
		this.setCreativeTab(LordRhysModMain.energyTab);
	}

	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityTrashcan();
	}
	
	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaque()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));        
    }
	
}

package com.lordrhys.mod.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTomato extends BlockCrops
{
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;
	
	public BlockTomato() 
	{
		this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata < 7)
		{
			if(metadata == 6)
			{
				metadata = 5;
			}
			return iconArray[metadata >> 1];
		}
		return iconArray[3];
	}
	
	public Item getSeedItem()
	{
		return LordRhysModMain.tomato;
	}
	
	public boolean canPlaceBlockOn(Block par1)
    {
        return par1 == Blocks.farmland || par1 == LordRhysModMain.tilledGoldenField;
    }
	
	public Item getCropItem()
	{
		return LordRhysModMain.tomato;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconArray = new IIcon[4];
        
        for (int i = 0; i < iconArray.length; i++) 
        {
        	this.iconArray[i] = iconRegister.registerIcon(LordRhysModMain.modid + ":" + "tomato_stage_" + i);
		}        
    }

}

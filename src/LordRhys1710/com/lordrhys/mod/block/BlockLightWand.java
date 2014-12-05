package com.lordrhys.mod.block;

import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightWand extends BlockTorch
{	
	public BlockLightWand()
	{
		this.setCreativeTab(LordRhysModMain.energyTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}

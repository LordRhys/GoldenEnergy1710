package com.lordrhys.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StairBlocks extends BlockStairs
{
	private Block extender;
	
	public StairBlocks(Block blockExtender) {
		super(blockExtender, 1);
		this.extender = blockExtender;
		this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(LordRhysModMain.modid + ":" + this.extender.getUnlocalizedName().substring(5));        
    }

}

package com.lordrhys.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenDirt extends Block
{

	public BlockGoldenDirt() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeGravel);
		this.setCreativeTab(LordRhysModMain.energyTab);
		this.setHardness(0.6F);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(LordRhysModMain.modid + ":goldenDirt");        
    }
}

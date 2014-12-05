package com.lordrhys.mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSlabLightStone extends BlockSlab
{
	private Block extendedBlock;
	
	public BlockSlabLightStone(boolean fullBlock, Block block, Material material) 
	{
		super(fullBlock, material);
		this.setHardness(3.5F);
		this.setLightLevel(0.975F);
		this.extendedBlock = block;
		if(!fullBlock)
		{
			this.setCreativeTab(LordRhysModMain.energyTab);
			//this.useNeighborBrightness[id] = true;
		}		
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(LordRhysModMain.modid + ":" + (this.extendedBlock.getUnlocalizedName().substring(5)));         
    }

	public String getFullSlabName(int i) 
	{
		return super.getUnlocalizedName();
	}
	
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(LordRhysModMain.lightStoneHalfSlab);
	}

	public String func_150002_b(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

}

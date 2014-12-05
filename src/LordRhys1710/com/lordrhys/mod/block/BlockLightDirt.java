package com.lordrhys.mod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightDirt extends Block
{
	public BlockLightDirt(Material material)
	{
		super(material);
		slipperiness = 0.8F;
		this.setCreativeTab(LordRhysModMain.energyTab);
	}	
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));        
    }
    
    public Item getItemDropped(int i, Random random, int par3)
	{
    	return Item.getItemFromBlock(this);
	}

    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
//    @Override
//    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
//    {
//    	EntityPlayer player = (EntityPlayer)world.playerEntities.get(0);
//    	player.addStat(LordRhysModMain.breakAchievement, 1);
//      super.breakBlock(world, x, y, x, par5, par6);
//    }
}

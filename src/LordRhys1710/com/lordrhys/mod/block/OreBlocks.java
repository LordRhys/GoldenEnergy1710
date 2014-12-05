package com.lordrhys.mod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OreBlocks extends Block
{
	public OreBlocks(Material material)
	{
		super(material);
		this.setCreativeTab(LordRhysModMain.energyTab);
	}	
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));        
    }
    
    public Item getItemDropped(int i, Random random, int par3)
	{
    	return Item.getItemFromBlock(this);
	}
    
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
        if (this.getItemDropped(par5, world.rand, par7) != Item.getItemFromBlock(this))
        {
            int j1 = 0;

            if (Item.getItemFromBlock(this) == Item.getItemFromBlock(LordRhysModMain.oreCopper))
            {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
            }
            else if (Item.getItemFromBlock(this) == Item.getItemFromBlock(LordRhysModMain.oreNickel))
            {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
            }
            else if (Item.getItemFromBlock(this) == Item.getItemFromBlock(LordRhysModMain.oreTin))
            {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
            }
            else if (Item.getItemFromBlock(this) == Item.getItemFromBlock(LordRhysModMain.oreVoid))
            {
                j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
            }
                        
            this.dropXpOnBlockBreak(world, x, y, z, j1);
        }    
    }
    
}


package com.lordrhys.mod.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenWoodPlank extends Block
{
	/** The type of tree this block came from. */
    public static final String[] woodType = new String[] {"golden"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    public BlockGoldenWoodPlank()
    {
        super(Material.wood);
        this.setStepSound(soundTypeWood);
        this.setCreativeTab(LordRhysModMain.energyTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length)
        {
            par2 = 0;
        }

        return this.iconArray[par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    /*@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs creativeTabs, List list)
    {
        list.add(new ItemStack(par1, 1, 0));        
    }*/

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[woodType.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(LordRhysModMain.modid + ":planks_" + woodType[i]);
        }
    }
}

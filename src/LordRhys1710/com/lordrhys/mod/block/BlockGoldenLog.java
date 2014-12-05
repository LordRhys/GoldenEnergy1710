package com.lordrhys.mod.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenLog extends BlockRotatedPillar
{
    /** The type of tree this log came from. */
    public static final String[] woodType = new String[] {"golden"};
    @SideOnly(Side.CLIENT)
    private IIcon treeSide;
    @SideOnly(Side.CLIENT)
    private IIcon tree_top;

    public BlockGoldenLog()
    {
        super(Material.wood);
        this.setCreativeTab(LordRhysModMain.energyTab);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(this);
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (world.checkChunksExist(x - j1, y - j1, z - j1, x + j1, y + j1, z + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        Block j2 = world.getBlock(x + k1, y + l1, z + i2);

                        if (j2.isLeaves(world, x + k1, y + l1, z + i2))
                        {
                            j2.beginLeavesDecay(world, x + k1, y + l1, z + i2);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * The icon for the side of the block.
     */
    public IIcon getSideIcon(int par1)
    {
        return this.treeSide; //[par1 % this.treeSide.length];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getTopIcon(int par1)
    {
        return this.tree_top;  //[par1 % this.tree_top.length];
    }

    /**
     * returns a number between 0 and 3
     *//*
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }*/

    /*@SideOnly(Side.CLIENT)
    public void getSubBlocks(Block par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        
    }*/

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        //this.treeSide = new IIcon[woodType.length];
        //this.tree_top = new IIcon[woodType.length];

        /*for (int i = 0; i < this.treeSide.length; ++i)
        {
            this.treeSide[i] = par1IconRegister.registerIcon(LordRhysModMain.modid + ":log_" + woodType[i]);
            this.tree_top[i] = par1IconRegister.registerIcon(LordRhysModMain.modid + ":log_" + woodType[i] + "_top");
        }*/
        this.treeSide = par1IconRegister.registerIcon(LordRhysModMain.modid + ":log_golden");
        this.tree_top = par1IconRegister.registerIcon(LordRhysModMain.modid + ":log_golden_top");
    }
    
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}

package com.lordrhys.mod.block;

import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.worldgen.GoldenWorldGenTrees;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenSapling  extends BlockBush implements IGrowable
{
    public static final String[] WOOD_TYPES = new String[] {"golden"};
    @SideOnly(Side.CLIENT)
    private IIcon[] saplingIcon;

    public BlockGoldenSapling()
    {
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(LordRhysModMain.energyTab);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                this.markOrGrowMarked(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        par2 &= 3;
        return this.saplingIcon[par2];
    }

    public void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);

        if ((l & 8) == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l | 8, 4);
        }
        else
        {
            this.growTree(par1World, par2, par3, par4, par5Random);
        }
    }

    public boolean canThisPlantGrowOnThisBlockID(Block par1)
    {
        return par1 == Blocks.grass || par1 == Blocks.dirt || par1 == Blocks.farmland 
        		|| par1 == LordRhysModMain.goldenGrass || par1 == LordRhysModMain.goldenDirt 
        		|| par1 == LordRhysModMain.tilledGoldenField;
    }
    
    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World world, int x, int y, int z, Random rand)
    {
        if (!TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;
        
        int l = world.getBlockMetadata(x, y, z) & 7;
        Object object = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new GoldenWorldGenTrees(true);
        
        //int l = world.getBlockMetadata(x, y, z) & 3;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        
        
        /*switch (l)
        {
            case 0:
            default:
                break;
            case 1:
                label78:

                for (i1 = 0; i1 >= -1; --i1)
                {
                    for (j1 = 0; j1 >= -1; --j1)
                    {
                        if (this.func_149880_a(world, x + i1, y, z + j1, 1) && 
                        this.func_149880_a(world, x + i1 + 1, y, z + j1, 1) && 
                        this.func_149880_a(world, x + i1, y, z + j1 + 1, 1) && 
                        this.func_149880_a(world, x + i1 + 1, y, z + j1 + 1, 1))
                        {
                            object = new WorldGenMegaPineTree(false, p_149878_5_.nextBoolean());
                            flag = true;
                            break label78;
                        }
                    }
                }

                if (!flag)
                {
                    j1 = 0;
                    i1 = 0;
                    object = new WorldGenTaiga2(true);
                }

                break;
            case 2:
                object = new WorldGenForest(true, false);
                break;
            case 3:
                label93:

                for (i1 = 0; i1 >= -1; --i1)
                {
                    for (j1 = 0; j1 >= -1; --j1)
                    {
                        if (this.func_149880_a(world, x + i1, y, z + j1, 3) && 
                        this.func_149880_a(world, x + i1 + 1, y, z + j1, 3) && 
                        this.func_149880_a(world, x + i1, y, z + j1 + 1, 3) && 
                        this.func_149880_a(world, x + i1 + 1, y, z + j1 + 1, 3))
                        {
                            object = new WorldGenMegaJungle(true, 10, 20, 3, 3);
                            flag = true;
                            break label93;
                        }
                    }
                }

                if (!flag)
                {
                    j1 = 0;
                    i1 = 0;
                    object = new WorldGenTrees(true, 4 + p_149878_5_.nextInt(7), 3, 3, false);
                }

                break;
            case 4:
                object = new WorldGenSavannaTree(true);
                break;
            case 5:
                label108:

                for (i1 = 0; i1 >= -1; --i1)
                {
                    for (j1 = 0; j1 >= -1; --j1)
                    {
                        if (this.func_149880_a(world, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1, 5) && this.func_149880_a(world, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1, 5) && 
                        this.func_149880_a(world, p_149878_2_ + i1, p_149878_3_, p_149878_4_ + j1 + 1, 5) && this.func_149880_a(world, p_149878_2_ + i1 + 1, p_149878_3_, p_149878_4_ + j1 + 1, 5))
                        {
                            object = new WorldGenCanopyTree(true);
                            flag = true;
                            break label108;
                        }
                    }
                }

                if (!flag)
                {
                    return;
                }
        }*/
            
        Block block = Blocks.air;
            
        if (flag)
        {
            world.setBlock(x + i1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        }
        else
        {
            world.setBlock(x, y, z, block, 0, 4);
        }

        if (!((WorldGenerator)object).generate(world, rand, x + i1, y, z + j1))
        {
        	
        	if (flag)
            {
                world.setBlock(x + i1, y, z + j1, this, l, 4);
                world.setBlock(x + i1 + 1, y, z + j1, this, l, 4);
                world.setBlock(x + i1, y, z + j1 + 1, this, l, 4);
                world.setBlock(x + i1 + 1, y, z + j1 + 1, this, l, 4);
            }
            else
            {
                world.setBlock(x, y, z, this, l, 4);
            }
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlock(par2, par3, par4) == this && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Block par1, CreativeTabs par2CreativeTabs, List list)
    {
        list.add(new ItemStack(par1, 1, 0));        
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.saplingIcon = new IIcon[WOOD_TYPES.length];

        for (int i = 0; i < this.saplingIcon.length; ++i)
        {
            this.saplingIcon[i] = iconRegister.registerIcon(LordRhysModMain.modid + ":sapling_" + WOOD_TYPES[i]);
        }
    }

	public boolean func_149851_a(World world, int x, int y, int z, boolean var5) 
	{
		return true;
	}

	public boolean func_149852_a(World world, Random rand, int x, int y, int z) 
	{
		return (double)world.rand.nextFloat() < 0.45D;
	}

	public void func_149853_b(World world, Random rand, int x, int y, int z) 
	{
		this.markOrGrowMarked(world, x, y, z, rand);
		
	}
}

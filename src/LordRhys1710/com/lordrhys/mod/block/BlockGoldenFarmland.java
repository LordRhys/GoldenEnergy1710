package com.lordrhys.mod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenFarmland extends BlockFarmland
{
	@SideOnly(Side.CLIENT)
    public IIcon iconFarmlandTop; //Top of our block
	@SideOnly(Side.CLIENT)
    public IIcon iconFarmlandBottom; //Bottom of our block
	
	public BlockGoldenFarmland() 
	{
		this.setStepSound(soundTypeGravel);
		//this.setTextureName("goldenFarmland");
	}
	
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float par6)
    {
        if (!world.isRemote && world.rand.nextFloat() < par6 - 0.5F)
        {
            if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            world.setBlock(x, y, z, LordRhysModMain.goldenDirt);
        }
    }
	
	public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!this.isWaterNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
        {
            int l = world.getBlockMetadata(x, y, z);

            if (l > 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, l - 1, 2);
            }
            else if (!this.isCropsNearby(world, x, y, z))
            {
                world.setBlock(x, y, z, LordRhysModMain.goldenDirt);
            }
        }
        else
        {
            world.setBlockMetadataWithNotify(x, y, z, 7, 2);
        }
    }
	
	private boolean isCropsNearby(World world, int x, int y, int z)
    {
        byte b0 = 0;

        for (int l = x - b0; l <= x + b0; ++l)
        {
            for (int i1 = z - b0; i1 <= z + b0; ++i1)
            {
                Block j1 = world.getBlock(l, y + 1, i1);

                //Block plant = blocksList[j1];
                if (j1 instanceof IPlantable && canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable)j1))
                {
                    return true;
                }
            }
        }

        return false;
    }
	
	private boolean isWaterNearby(World world, int x, int y, int z)
    {
        for (int l = x - 4; l <= x + 4; ++l)
        {
            for (int i1 = y; i1 <= y + 1; ++i1)
            {
                for (int j1 = z - 4; j1 <= z + 4; ++j1)
                {
                    if (world.getBlock(l, i1, j1).getMaterial() == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
	
	public Item GetItemDropped(int par1, Random par2Random, int par3)
    {
        return LordRhysModMain.goldenDirt.getItemDropped(0, par2Random, par3);
    }
	
	public void onNeighborBlockChange(World world, int par2, int par3, int par4, Block par5)
    {
        super.onNeighborBlockChange(world, par2, par3, par4, par5);
        Material material = world.getBlock(par2, par3 + 1, par4).getMaterial();

        if (material.isSolid())
        {
            world.setBlock(par2, par3, par4, LordRhysModMain.goldenDirt);
        }
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(LordRhysModMain.goldenDirt);
    }
    
    public boolean isFertile(World world, int x, int y, int z)
    {
        if (this == LordRhysModMain.tilledGoldenField || this == Blocks.farmland)
        {
            return world.getBlockMetadata(x, y, z) > 0;
        }

        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? (par2 > 0 ? this.iconFarmlandTop : this.iconFarmlandBottom) : LordRhysModMain.goldenDirt.getBlockTextureFromSide(par1);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconFarmlandTop = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenFarmland_wet");
        this.iconFarmlandBottom = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenFarmland_dry");
    }

}

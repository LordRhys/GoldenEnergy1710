package com.lordrhys.mod.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenGrass extends Block
{

	public BlockGoldenGrass(Material material) {
		super(material);
		this.setStepSound(Block.soundTypeGrass);
		this.setCreativeTab(LordRhysModMain.energyTab);
		this.setHardness(0.6F);
	}
	
	public IIcon iconGrassTop; //Top of our block
	public IIcon iconGrassBottom; //Bottom of our block
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(LordRhysModMain.modid + ":goldenGrass_Side");
        iconGrassTop = register.registerIcon(LordRhysModMain.modid + ":goldenGrass_Top");
        iconGrassBottom = register.registerIcon(LordRhysModMain.modid + ":goldenGrass_Bottom");
    }
	
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 0 ? this.iconGrassBottom : (par1 == 1 ? this.iconGrassTop: this.blockIcon);
	}
	
	public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return LordRhysModMain.goldenDirt.getItemDropped(0, par2Random, par3);
    }

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        Block plantID = plant.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

        if (plantID == Blocks.cactus && this == Blocks.cactus)
        {
            return true;
        }

        if (plantID == Blocks.reeds && this == Blocks.reeds)
        {
            return true;
        }

        if (plant instanceof BlockBush && canPlaceBlockOn(this))
        {
            return true;
        }

        switch (plantType)
        {
            case Desert: return this == (Block)Blocks.sand;
            case Nether: return this == Blocks.soul_sand;
            case Crop:   return this == Blocks.farmland;
            case Cave:   return isSideSolid(world, x, y, z, UP);
            case Plains: return this == Blocks.dirt || this == LordRhysModMain.goldenGrass || this == LordRhysModMain.goldenDirt;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
            case Beach:
                boolean isBeach = (this == LordRhysModMain.goldenGrass || this == LordRhysModMain.goldenDirt || this == (Block)Blocks.sand);
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                                    world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return isBeach && hasWater;
        }

        return false;
    }
	
	public boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.grass || p_149854_1_ == Blocks.dirt || p_149854_1_ == Blocks.farmland || 
        		p_149854_1_ == LordRhysModMain.goldenGrass || p_149854_1_ == LordRhysModMain.goldenDirt || 
        		p_149854_1_ == LordRhysModMain.tilledGoldenField;
    }
	
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        if (this == LordRhysModMain.goldenGrass)
        {
            world.setBlock(x, y, z, LordRhysModMain.goldenDirt, 0, 2);
        }
    }

}

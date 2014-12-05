package com.lordrhys.mod.block;

import javax.swing.Icon;

import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockTeleporterFire extends BlockFire
{
	@SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
	
	public BlockTeleporterFire() 
	{
		
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y - 1, z) != LordRhysModMain.oreRandomite || 
        		!((BlockTeleporter)LordRhysModMain.blockTeleporter).tryToCreatePortal(world, x, y, z)) //world.provider.dimensionId >= 0 || 
        {
            if (!world.doesBlockHaveSolidTopSurface(world, x, y-1, z) && !this.canNeighborBurn(world, x, y, z))
            {
                world.setBlockToAir(x, y, z);
            }
            else
            {
                world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
            }
        }
    }
	
	public boolean canNeighborBurn(World world, int x, int y, int z)
    {
        return canCatchFire(world, x + 1, y, z, WEST ) ||
        		canCatchFire(world, x - 1, y, z, EAST ) ||
        		canCatchFire(world, x, y - 1, z, UP   ) ||
        		canCatchFire(world, x, y + 1, z, DOWN ) ||
        		canCatchFire(world, x, y, z - 1, SOUTH) ||
        		canCatchFire(world, x, y, z + 1, NORTH);
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.iconArray = new IIcon[] {iconRegister.registerIcon(LordRhysModMain.modid + ":blueFire_layer_0"), 
        		iconRegister.registerIcon(LordRhysModMain.modid + ":blueFire_layer_1")};
    }

    @SideOnly(Side.CLIENT)
    public IIcon getFireIcon(int par1)
    {
        return this.iconArray[par1];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return this.iconArray[0];
    }

}

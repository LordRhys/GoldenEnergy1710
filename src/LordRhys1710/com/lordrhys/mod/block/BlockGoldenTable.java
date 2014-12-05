package com.lordrhys.mod.block;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityGoldenCrafter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenTable extends BlockWorkbench
{
	@SideOnly(Side.CLIENT)
    private IIcon goldenWorkbenchTop;
    @SideOnly(Side.CLIENT)
    private IIcon goldenWorkbenchFront;
    
	public BlockGoldenTable() 
	{
		this.setHardness(3.5F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityGoldenCrafter();
	}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.goldenWorkbenchTop : (par1 == 0 ? LordRhysModMain.goldenWoodPlanks.getBlockTextureFromSide(par1) : (par1 != 2 && par1 != 4 ? this.blockIcon : this.goldenWorkbenchFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenCraftTable_side"); 
        this.goldenWorkbenchTop = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenCraftTable_top");
        this.goldenWorkbenchFront = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenCraftTable_front");
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!player.isSneaking())
        {
            player.openGui(LordRhysModMain.instance, 3, world, x, y, z);
        	return true;
        }
        else
        {
        	/*IInventory iinventory = this.getInventory(world, x, y, z);

            if (iinventory != null)
            {
                player.displayGUIChest(iinventory);
            }*/

            return false;
        }
    }

}

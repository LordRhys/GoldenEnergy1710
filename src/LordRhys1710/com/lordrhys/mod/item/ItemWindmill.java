package com.lordrhys.mod.item;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWindmill extends Item
{
	public ItemWindmill()
	{
		this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        return itemStack;
    }
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, 
			int z, int side, float hitX, float hitY, float hitZ)
    {
		if(!world.isRemote)
        {        	
        	if(side == 1 && world.getBlock(x, y, z).equals(LordRhysModMain.blockWindmillGround) && 
        			world.getBlockMetadata(x, y, z) == 5) 
        	{
        		boolean notEnoughSpace = false;
        		
        		for(int x1 = -1; x1 < 1; x1++)
        		{
        			for(int z1 = -1; z1 < 1; z1++)
        			{
        				for(int y1 = 0; y1 < 7; y1++)
        				{
        					if(!world.isAirBlock(x+x1, y+y1+1, z+z1)) notEnoughSpace = true;
        					
        				}
        			}
        		}
        		
        		if (!notEnoughSpace) 
        		{
					int direction = Math.abs((int) ((player.rotationYaw+45)/90));
					if(direction == 0) direction = 4;
        			for (int y1 = 0; y1 < 7; y1++) 
					{
						world.setBlock(x, y + y1 + 1, z, LordRhysModMain.blockWindmill, (y1+1)==7?(y1+1+direction):(y1+1), 2);
					}
				}
        	}
        	
        	return true;
        }
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
}

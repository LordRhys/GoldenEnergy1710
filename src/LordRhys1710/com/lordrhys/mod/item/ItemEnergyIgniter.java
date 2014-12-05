package com.lordrhys.mod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnergyIgniter extends Item
{

	public ItemEnergyIgniter() 
	{
		this.setMaxDamage(26);
        this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, 
			int side, float par8, float par9, float par10)
	{
		if(side == 0)
		{
			y--;			
		}
		
		if(side == 1)
		{
			y++;			
		}
		
		if(side == 2)
		{
			z--;			
		}
		
		if(side == 3)
		{
			z++;			
		}
		
		if(side == 4)
		{
			x--;			
		}
		
		if(side == 5)
		{
			x++;			
		}
		
		if(!player.canPlayerEdit(x, y, z, side, itemstack))
		{
			return false;
		}
		else
		{
			if(world.isAirBlock(x, y, z))
			{
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 
						1F, itemRand.nextFloat()*0.4F + 0.8F);
				world.setBlock(x, y, z, LordRhysModMain.teleporterFire);
			}
			
			itemstack.damageItem(1, player);
			return true;
		}		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
}

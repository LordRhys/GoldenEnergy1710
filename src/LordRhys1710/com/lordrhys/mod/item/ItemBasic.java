package com.lordrhys.mod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBasic extends Item
{

	public ItemBasic()
	{
		setHasSubtypes(false);
		setMaxDamage(0);
		this.setCreativeTab(LordRhysModMain.energyMaterialsTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}

package com.lordrhys.mod.food;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodBaconAndEggsItem extends ItemFood
{

	public FoodBaconAndEggsItem(int heal, float saturation, boolean wolfFood) {
		super(heal, saturation, wolfFood);
		this.setCreativeTab(LordRhysModMain.energyFoodsTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}

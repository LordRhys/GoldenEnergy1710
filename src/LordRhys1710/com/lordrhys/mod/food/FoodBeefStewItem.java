package com.lordrhys.mod.food;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodBeefStewItem extends ItemFood
{

	public FoodBeefStewItem(int heal, float saturation, boolean wolfFood) {
		super(heal, saturation, wolfFood);
		this.setCreativeTab(LordRhysModMain.energyFoodsTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		this.itemIcon = register.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}

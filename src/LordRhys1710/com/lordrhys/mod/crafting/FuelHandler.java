package com.lordrhys.mod.crafting;


import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == Item.getItemFromBlock(LordRhysModMain.oreVoid)) return 200;
		if(fuel.getItem() == LordRhysModMain.cobblestoneNugget) return 9999;
		if(fuel.getItem() == Item.getItemFromBlock(Blocks.diamond_block)) return 5;
		if(fuel.getItem() == Items.slime_ball) return 1;
		
		if (fuel.getItem() == Item.getItemFromBlock(Blocks.dirt)) return fuel.stackSize;
		
		return 0;
	}	
}

package com.lordrhys.mod.crafting;

import com.lordrhys.mod.LordRhysModMain;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class OreRecipes 
{
	public static final FurnaceRecipes originalRecipes = FurnaceRecipes.smelting();
	
	/*static{
		originalRecipes.addSmelting(LordRhysModMain.blueFlower, new ItemStack(LordRhysModMain.voidPowder), 1F);
	}*/
	
	public static FurnaceRecipes getRecipes()
	{
		return originalRecipes;
	}	
	
	/*public static void overrideRecipe(int itemID, ItemStack itemStack, float xp)
	{
		originalRecipes.addSmelting(itemID, itemStack, xp);
	}*/
}

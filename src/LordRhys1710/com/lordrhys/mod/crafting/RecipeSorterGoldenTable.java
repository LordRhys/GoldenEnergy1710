package com.lordrhys.mod.crafting;

import java.util.Comparator;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

class RecipeSorterGoldenTable implements Comparator
{
    final CraftingManagerGoldenTable craftingManagerGoldenTable;

    RecipeSorterGoldenTable(CraftingManagerGoldenTable craftManager)
    {
        this.craftingManagerGoldenTable = craftManager;
    }

    public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe)
    {
        return par1IRecipe instanceof GoldenTableShapelessRecipes && par2IRecipe instanceof GoldenTableShapedRecipes ? 1 : 
        	(par2IRecipe instanceof GoldenTableShapelessRecipes && par1IRecipe instanceof GoldenTableShapedRecipes ? -1 : 
        		(par2IRecipe.getRecipeSize() < par1IRecipe.getRecipeSize() ? -1 : 
        			(par2IRecipe.getRecipeSize() > par1IRecipe.getRecipeSize() ? 1 : 0)));
    }

    public int compare(Object par1Obj, Object par2Obj)
    {
        return this.compareRecipes((IRecipe)par1Obj, (IRecipe)par2Obj);
    }

}

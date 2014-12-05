package com.lordrhys.mod.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;



public class CraftingManagerGoldenTable
{
	/** The static instance of this class */
    private static final CraftingManagerGoldenTable instance = new CraftingManagerGoldenTable();

    /** A list of all the recipes added */
    private List recipes = new ArrayList();

    /**
     * Returns the static instance of this class
     */
    public static final CraftingManagerGoldenTable getInstance()
    {
        return instance;
    }

    private static String[][] armorPatterns = new String[][] {{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	private static Object[] armorItems = new Object[] {LordRhysModMain.lightHelmet, LordRhysModMain.lightChestplate, LordRhysModMain.lightLeggings, LordRhysModMain.lightBoots};
	
    private CraftingManagerGoldenTable()
    {
        
      this.addRecipe(new ItemStack(LordRhysModMain.lightDirt), new Object[]{"XXX", "XDX","XXX",
      'D', Blocks.dirt, 'X', LordRhysModMain.lightStoneDust});
  
      this.addRecipe(new ItemStack(LordRhysModMain.purpleLightsaber), new Object[]{"  B", " D ", "P  ",
      'B', Items.blaze_rod, 'D', Items.diamond, 'P', Items.ender_pearl});
  
      this.addRecipe(new ItemStack(LordRhysModMain.goldenBow), new Object[]{" GS", "L S", " GS",
      'L', LordRhysModMain.lightStone, 'G', Items.gold_ingot, 'S', Items.string});
      
      // this.addRecipe(new ItemStack(LordRhysModMain.energyBow), new Object[]{" GE", "L S", "E S", "L S",
      // " GE", 'L', LordRhysModMain.lightStone, 'G', Item.ingotGold, 'E', LordRhysModMain.bucketOfLight,
      // 'S', Item.silk});
  
      this.addGoldenTableShapelessRecipe(new ItemStack(LordRhysModMain.lightStoneDust, 4),new Object[]{
       new ItemStack(LordRhysModMain.lightStone)});
      
      this.addGoldenTableShapelessRecipe(new ItemStack(LordRhysModMain.cheese, 4),new Object[]{
     new ItemStack(Items.milk_bucket),new ItemStack(Items.slime_ball),new ItemStack(Items.egg)});
  
      this.addGoldenTableShapelessRecipe(new ItemStack(LordRhysModMain.cheeseBurger),new Object[]{
      new ItemStack(LordRhysModMain.cheese),new ItemStack(Items.bread),new ItemStack(Items.beef)});
  
      this.addGoldenTableShapelessRecipe(new ItemStack(LordRhysModMain.hamburger),new Object[]{
      new ItemStack(Items.bread),new ItemStack(Items.beef)});
  
      this.addGoldenTableShapelessRecipe(new ItemStack(LordRhysModMain.sloppyJoe),new Object[]{
      new ItemStack(Items.bread),new ItemStack(Items.rotten_flesh)});
  
      this.addGoldenTableShapelessRecipe(new ItemStack(LordRhysModMain.baconAndEggs, 2),new Object[]{
      new ItemStack(Items.porkchop),new ItemStack(Items.egg),new ItemStack(Items.egg)});
  
      this.addRecipe(new ItemStack(LordRhysModMain.beefStew), new Object[]{"PCP","XXX","MBM",
      'P', Items.potato, 'C', Items.carrot,'X', Items.beef,
      'M', Blocks.brown_mushroom, 'B', Items.bowl});
  
      this.addRecipe(new ItemStack(LordRhysModMain.blockGoldenFurnaceIdle), new Object[] {
      "#G#", "L L", "#G#", '#', Blocks.cobblestone,'G', Items.gold_nugget,'L', LordRhysModMain.lightStone});
  
      this.addRecipe(new ItemStack(LordRhysModMain.blockFurnaceOfLightIdle), new Object[] {
      "#L#", "LGL", "#L#", '#', Blocks.cobblestone,'L', LordRhysModMain.lightStone,
      'G', LordRhysModMain.blockGoldenFurnaceIdle});
  
      this.addRecipe(new ItemStack(LordRhysModMain.goldenWoodPlanks, 4), new Object[] {"#",
      '#', new ItemStack(LordRhysModMain.goldenLog, 1)});
  
      this.addRecipe(new ItemStack(LordRhysModMain.goldenChest), new Object[] {
      "###","# #", "###", '#', LordRhysModMain.goldenWoodPlanks});
  
      this.addRecipe(new ItemStack(LordRhysModMain.goldenChestTrapped), new Object[] {
      "#-", '#', LordRhysModMain.goldenChest, '-', Blocks.tripwire});
      
      this.addRecipe(new ItemStack(LordRhysModMain.lightHelmet), new Object[] {
      "EEE","XXX", "X X", 'E', LordRhysModMain.liquidLightBucket, 'X', LordRhysModMain.lightStone });
      
      this.addRecipe(new ItemStack(LordRhysModMain.lightChestplate), new Object[] {
      "EEE","X X", "XXX", "XXX", 'E', LordRhysModMain.liquidLightBucket, 'X', LordRhysModMain.lightStone });
      
      this.addRecipe(new ItemStack(LordRhysModMain.lightLeggings), new Object[] {
      "EEE","XXX", "X X", "X X", 'E', LordRhysModMain.liquidLightBucket, 'X', LordRhysModMain.lightStone });
      
      this.addRecipe(new ItemStack(LordRhysModMain.lightBoots), new Object[] {
      "E E","X X", "X X", 'E', LordRhysModMain.liquidLightBucket, 'X', LordRhysModMain.lightStone });
      
      this.addRecipe(new ItemStack(Blocks.cobblestone), new Object[]{
			"XXX","XEX","XXX",'X', LordRhysModMain.cobblestoneNugget, 
			'E', LordRhysModMain.liquidLightBucket});
      
        Collections.sort(this.recipes, new RecipeSorterGoldenTable(this));
    }

    public GoldenTableShapedRecipes addRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (par2ArrayOfObj[i] instanceof String[])
        {
            String[] astring = (String[])((String[])par2ArrayOfObj[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (par2ArrayOfObj[i] instanceof String)
            {
                String s2 = (String)par2ArrayOfObj[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < par2ArrayOfObj.length; i += 2)
        {
            Character character = (Character)par2ArrayOfObj[i];
            ItemStack itemstack1 = null;

            if (par2ArrayOfObj[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)par2ArrayOfObj[i + 1]);
            }
            else if (par2ArrayOfObj[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)par2ArrayOfObj[i + 1], 1, 32767);
            }
            else if (par2ArrayOfObj[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)par2ArrayOfObj[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        GoldenTableShapedRecipes shapedrecipes = new GoldenTableShapedRecipes(j, k, aitemstack, par1ItemStack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    public void addGoldenTableShapelessRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        ArrayList arraylist = new ArrayList();
        Object[] aobject = par2ArrayOfObj;
        int i = par2ArrayOfObj.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.recipes.add(new GoldenTableShapelessRecipes(par1ItemStack, arraylist));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting par1InventoryCrafting, World world)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < par1InventoryCrafting.getSizeInventory(); ++j)
        {
            ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(j);

            if (itemstack2 != null)
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && 
        		itemstack1.stackSize == 1 && itemstack.getItem().isRepairable())
        {
            Item item = itemstack.getItem();
            int k = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
            int l = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
            int i1 = k + l + item.getMaxDamage() * 5 / 100;
            int j1 = item.getMaxDamage() - i1;

            if (j1 < 0)
            {
                j1 = 0;
            }

            return new ItemStack(itemstack.getItem(), 1, j1);
        }
        else
        {
            for (j = 0; j < this.recipes.size(); ++j)
            {
                IRecipe irecipe = (IRecipe)this.recipes.get(j);

                if (irecipe.matches(par1InventoryCrafting, world))
                {
                    return irecipe.getCraftingResult(par1InventoryCrafting);
                }
            }

            return null;
        }
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}

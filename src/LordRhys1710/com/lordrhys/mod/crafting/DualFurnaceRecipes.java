package com.lordrhys.mod.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lordrhys.mod.LordRhysModMain;

public class DualFurnaceRecipes
{
	private static final DualFurnaceRecipes dualSmelting = new DualFurnaceRecipes(); 
	   
	private HashMap<List<Integer>, ItemStack> dualMetaSmeltingList = new HashMap<List<Integer>, ItemStack>(); 
	private HashMap<List<Integer>, Float> dualMetaExperience = new HashMap<List<Integer>, Float>(); 
	private Map experienceList = new HashMap(); 
	   
  public static final DualFurnaceRecipes recipes() 
  { 
    return dualSmelting; 
  }
	
	private DualFurnaceRecipes() 
  {
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.lightDirt)),
    		Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.goldenLog))), new ItemStack(LordRhysModMain.lightWand), 1.0F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.goldenLog)),
    		Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.lightDirt))), new ItemStack(LordRhysModMain.lightWand), 1.0F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Item.getIdFromItem(LordRhysModMain.lightStone)),
    		Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.goldenDirt))), new ItemStack(LordRhysModMain.lightDirt), 1.0F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.goldenDirt)),
    		Integer.valueOf(Item.getIdFromItem(LordRhysModMain.lightStone))), new ItemStack(LordRhysModMain.lightDirt), 1.0F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Item.getIdFromItem(Items.iron_ingot)),
    		Integer.valueOf(Item.getIdFromItem(LordRhysModMain.ingotNickel))), new ItemStack(LordRhysModMain.ingotSteel), 1.2F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Item.getIdFromItem(LordRhysModMain.ingotCopper)),
    		Integer.valueOf(Item.getIdFromItem(LordRhysModMain.ingotTin))), new ItemStack(LordRhysModMain.ingotBronze), 1.2F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Item.getIdFromItem(LordRhysModMain.ingotTin)),
    		Integer.valueOf(Item.getIdFromItem(LordRhysModMain.ingotCopper))), new ItemStack(LordRhysModMain.ingotBronze), 1.2F); 
    this.addMultiSmelting(Arrays.asList(Integer.valueOf(Item.getIdFromItem(Items.gold_ingot)),
    		Integer.valueOf(Block.getIdFromBlock(LordRhysModMain.lightDirt))),new ItemStack(Items.blaze_rod), 0.3F);
  } 
	
	public ItemStack getSmeltingResult(ItemStack[] ores)
  {
    Integer[] idIndex = new Integer[ores.length]; 
    for (int i = 0; i < ores.length; i++)
    { 
      idIndex[i] =  Item.getIdFromItem(ores[i].getItem()); 
    } 
      
    return (ItemStack)dualMetaSmeltingList.get(Arrays.asList(idIndex));  
  }
     
  public void addMultiSmelting(List<Integer> ores, ItemStack stack, float experience) 
  { 
    // Check if recipe already exists and print conflict information:
    if (dualMetaSmeltingList.containsKey(ores))
    {
      System.out.println("[WARNING] Conflicting recipe: " + ores.toString() + " for " + dualMetaSmeltingList.get(ores).toString());
    }
    else
    {
      dualMetaSmeltingList.put(ores, stack); 
      dualMetaExperience.put(Arrays.asList(Item.getIdFromItem(stack.getItem()), stack.getItemDamage()), experience); 
    }
  } 
  
  public float getExperience(ItemStack item)
  {
    if (item == null || item.getItem() == null)
    {
      return 0;
    }
    float ret = -1; // value returned by "item.getItem().getSmeltingExperience(item);" when item doesn't specify experience to give
    if (ret < 0 && dualMetaExperience.containsKey(Arrays.asList(item, item.getItemDamage())))
    {
      ret = dualMetaExperience.get(Arrays.asList(item, item.getItemDamage()));
    }

    return (ret < 0 ? 0 : ret);
  }

  public Map<List<Integer>, ItemStack> getMetaSmeltingList()
  {
    return dualMetaSmeltingList;
  }
    
}

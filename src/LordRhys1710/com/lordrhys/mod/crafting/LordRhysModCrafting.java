package com.lordrhys.mod.crafting;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.common.registry.GameRegistry;

public class LordRhysModCrafting 
{

	public static void loadRecipes() 
	{
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.goldenCraftTable), new Object[]{
			"XXX",
			"GFG",
			"WCW",
			'F', LordRhysModMain.blockGoldenFurnaceIdle, 'X', LordRhysModMain.lightStoneDust,
			'C', Blocks.crafting_table,'G', Items.gold_ingot, 'W', Blocks.planks
		});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.lightDirt), new Object[]{
			"XXX",
			"XDX",
			"XXX",
			'D', Blocks.dirt, 'X', LordRhysModMain.lightStoneDust
		});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.purpleLightsaber), new Object[]{
			"  B",
			" D ",
			"P  ",
			'B', Items.blaze_rod, 'D', Items.diamond, 'P', Items.ender_pearl
		});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.goldenBow), new Object[]{
			" GS",
			"L S",
			" GS",
			'L', LordRhysModMain.lightStone, 'G', Items.gold_ingot, 'S', Items.string
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(LordRhysModMain.lightStoneDust, 4),new Object[]{
				 new ItemStack(LordRhysModMain.lightStone)}); 
		
		ItemStack enchanted = new ItemStack(Items.stone_pickaxe);
		enchanted.addEnchantment(Enchantment.sharpness, 2);
		
		GameRegistry.addShapelessRecipe(enchanted, new Object[]{
				new ItemStack(Items.stone_pickaxe), new ItemStack(Items.flint), new ItemStack(Blocks.stone)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(LordRhysModMain.cheese, 4),new Object[]{
			 new ItemStack(Items.milk_bucket),new ItemStack(Items.slime_ball),new ItemStack(Items.egg)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(LordRhysModMain.cheeseBurger),new Object[]{
			new ItemStack(LordRhysModMain.cheese),new ItemStack(Items.bread),new ItemStack(Items.beef)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(LordRhysModMain.hamburger),new Object[]{
			new ItemStack(Items.bread),new ItemStack(Items.beef)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(LordRhysModMain.sloppyJoe),new Object[]{
			new ItemStack(Items.bread),new ItemStack(Items.rotten_flesh)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(LordRhysModMain.baconAndEggs, 2),new Object[]{
			new ItemStack(Items.porkchop),new ItemStack(Items.egg),new ItemStack(Items.egg)});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.beefStew), new Object[]{
			"PCP",
			"XXX",
			"MBM",
			'P', Items.potato, 'C', Items.carrot,'X', Items.beef,
			'M', Blocks.brown_mushroom, 'B', Items.bowl
		});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.blockGoldenFurnaceIdle), new Object[] {
			"#G#", "L L", "#G#",
			'#', Blocks.cobblestone,
			'G', Items.gold_nugget,
			'L', LordRhysModMain.lightStone});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.blockFurnaceOfLightIdle), new Object[] {
			"#L#",
			"LGL",
			"#L#",
			'#', Blocks.cobblestone,
			'L', LordRhysModMain.lightStone,
			'G', LordRhysModMain.blockGoldenFurnaceIdle});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.goldenWoodPlanks, 4), new Object[] {
			"#",
			'#', new ItemStack(LordRhysModMain.goldenLog, 1)});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.goldenChest), new Object[] {
			"###",
			"# #",
			"###",
			'#', LordRhysModMain.goldenWoodPlanks});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.goldenChestTrapped), new Object[] {
			"#-",
			'#', LordRhysModMain.goldenChest,
			'-', Blocks.tripwire});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.cobblestone), new Object[]{
			"XXX","XEX","XXX",'X', LordRhysModMain.cobblestoneNugget, 
			'E', LordRhysModMain.liquidLightBucket});
		
		GameRegistry.addRecipe(new ItemStack(LordRhysModMain.energyCell,1,LordRhysModMain.energyCell.getMaxDamage()), new Object[]{
			" X "," E "," X ",'X', LordRhysModMain.ingotSteel,'E', LordRhysModMain.liquidLightBucket});
		
	}
	
	private static String[][] armorPatterns = new String[][] {{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	private static Object[] armorItems = new Object[] {LordRhysModMain.lightHelmet, LordRhysModMain.lightChestplate, LordRhysModMain.lightLeggings, LordRhysModMain.lightBoots};
	
	public static void loadArmorRecipes()
	{
		for (int j = 0; j < armorItems.length; j++)
        {
            Item item = (Item)armorItems[j];
            GameRegistry.addRecipe(new ItemStack(item), new Object[] {armorPatterns[j], 'X', LordRhysModMain.lightStone});
        }
        
	}
    
	public static void loadSmeltingRecipes()
	{
		GameRegistry.addSmelting(LordRhysModMain.oreCopper, new ItemStack(LordRhysModMain.ingotCopper), 1.0F);
		GameRegistry.addSmelting(LordRhysModMain.oreNickel, new ItemStack(LordRhysModMain.ingotNickel), 1.0F);
		GameRegistry.addSmelting(LordRhysModMain.oreTin, new ItemStack(LordRhysModMain.ingotTin), 1.0F);
		GameRegistry.addSmelting(LordRhysModMain.oreVoid, new ItemStack(LordRhysModMain.ingotVoid,4), 1.0F);
		GameRegistry.addSmelting(LordRhysModMain.lightDirt, new ItemStack(LordRhysModMain.lightStone,4), 0.8F);
		GameRegistry.addSmelting(Blocks.cobblestone, new ItemStack(LordRhysModMain.cobblestoneNugget), 0.2F);
	}	

}

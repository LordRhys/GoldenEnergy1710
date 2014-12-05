package com.lordrhys.mod.lib;

import com.lordrhys.mod.LordRhysModMain;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreRegistry 
{
	
	public static void RegisterOres()
	{
		OreDictionary.registerOre("oreVoid", LordRhysModMain.oreVoid);
		OreDictionary.registerOre("oreCopper", LordRhysModMain.oreCopper);
		OreDictionary.registerOre("oreNickel", LordRhysModMain.oreNickel);
		OreDictionary.registerOre("oreTin", LordRhysModMain.oreTin);
		OreDictionary.registerOre("oreEnergy", LordRhysModMain.lightStone);
		OreDictionary.registerOre("oreLight", LordRhysModMain.lightDirt);
		OreDictionary.registerOre("ingotBronze", LordRhysModMain.ingotBronze);
		OreDictionary.registerOre("ingotCopper", LordRhysModMain.ingotCopper);
		OreDictionary.registerOre("ingotNickel", LordRhysModMain.ingotNickel);
		OreDictionary.registerOre("ingotSteel", LordRhysModMain.ingotSteel);
		OreDictionary.registerOre("ingotTin", LordRhysModMain.ingotTin);
		OreDictionary.registerOre("ingotVoid", LordRhysModMain.ingotVoid);
		OreDictionary.registerOre("ingotStone", LordRhysModMain.cobblestoneNugget);
		
	}
}

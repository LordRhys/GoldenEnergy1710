package com.lordrhys.mod.tabs;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEnergyFoods extends CreativeTabs
{
	public CreativeTabEnergyFoods(String tabName)
	{
		super(tabName);
	}
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return LordRhysModMain.cheese;
    }

}

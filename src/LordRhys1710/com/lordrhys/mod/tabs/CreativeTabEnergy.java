package com.lordrhys.mod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEnergy extends CreativeTabs {

	public CreativeTabEnergy(String tabName)
	{
		super(tabName);
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
    {
        return LordRhysModMain.randomite;
    }	
}

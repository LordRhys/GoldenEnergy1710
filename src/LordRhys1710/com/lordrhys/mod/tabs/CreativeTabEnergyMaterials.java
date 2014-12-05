package com.lordrhys.mod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEnergyMaterials extends CreativeTabs {

	public CreativeTabEnergyMaterials(String tabName)
	{
		super(tabName);
	}
	
	@SideOnly(Side.CLIENT)

    public Item getTabIconItem()
    {
        return LordRhysModMain.lightStone;
    }
}

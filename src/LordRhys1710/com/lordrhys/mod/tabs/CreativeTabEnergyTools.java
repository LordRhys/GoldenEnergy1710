package com.lordrhys.mod.tabs;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEnergyTools extends CreativeTabs
{
	public CreativeTabEnergyTools(String par2Str)
	{
		super(par2Str);
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
    {
        return LordRhysModMain.lightStoneDust;
    }

}

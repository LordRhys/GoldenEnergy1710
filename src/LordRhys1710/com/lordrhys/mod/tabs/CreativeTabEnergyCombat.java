package com.lordrhys.mod.tabs;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEnergyCombat extends CreativeTabs {

	public CreativeTabEnergyCombat(String par2Str)
	{
		super(par2Str);
	}
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return LordRhysModMain.purpleLightsaber;
    }

}

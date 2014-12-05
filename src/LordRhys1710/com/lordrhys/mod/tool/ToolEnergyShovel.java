package com.lordrhys.mod.tool;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSpade;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToolEnergyShovel extends ItemSpade
{

	public ToolEnergyShovel(ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
		this.setCreativeTab(LordRhysModMain.energyToolsTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));        
    }

}

package com.lordrhys.mod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBattery extends Item
{
	@SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
	
	public boolean rechargeable;
	
	public ItemBattery(int maxPower, boolean chargeable) 
	{
		this.setMaxDamage(maxPower);
		this.setCreativeTab(LordRhysModMain.energyTab);
		this.rechargeable = chargeable;
	}
	
	public boolean isRepairable()
    {
        return rechargeable && isDamageable();
    }
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
    {
		float batteryLevel = (float)par1 / (float)this.getMaxDamage();
		if (batteryLevel == 0.0f)
		{
			return this.iconArray[4];
		}
		else if (batteryLevel <= 0.25f)
		{
			return this.iconArray[3];
		}
		else if (batteryLevel <= 0.50f)
		{
			return this.iconArray[2];
		}
		else if (batteryLevel <= 0.75f)
		{
			return this.iconArray[1];
		}
		else 
		{
			return this.iconArray[0];
		}		
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.iconArray = new IIcon[5];
		for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = iconRegister.registerIcon(LordRhysModMain.modid + ":" + "energycell_" + i);
        }
	}

}

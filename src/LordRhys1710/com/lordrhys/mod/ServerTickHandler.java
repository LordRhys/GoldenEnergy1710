package com.lordrhys.mod;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;


public class ServerTickHandler //implements ITickHandler 
{
	private void onPlayerTick(EntityPlayer player)
	{
		if (player.getCurrentArmor(4) != null)
		{
			ItemStack helmet = player.getCurrentArmor(4);
			
//			if (helmet.getItem() == LordRhysModMain.redstoneHelmet)
//			{
//				player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(),60,0));
//				player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(),40,0));
//			}
		}
		
		if (player.getCurrentArmor(3) != null)
		{
			ItemStack chestPlate = player.getCurrentArmor(3);
			
//			if (chestPlate.getItem() == RedstoneMain.redstoneChestplate)
//			{
//				player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(),40,0));
//			}
		}
		
		if (player.getCurrentArmor(2) != null)
		{
			ItemStack leggings = player.getCurrentArmor(2);
			
//			if (leggings.getItem() == RedstoneMain.redstoneLeggings)
//			{
//				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(),40,2));
//			}
		}
		
		if (player.getCurrentArmor(1) != null)
		{
			ItemStack boots = player.getCurrentArmor(1);
			
//			if (boots.getItem() == RedstoneMain.redstoneBoots)
//			{
//				player.addPotionEffect(new PotionEffect(Potion.jump.getId(),40,4));
//			}
		}
	}	

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.lordrhys.mod.crafting;

import net.minecraft.block.Block;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.lib.LordRhysAchievementPage;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class AchievementHandler 
{

	@SubscribeEvent
	public void onCraftingThings(PlayerEvent.ItemCraftedEvent e)
	{
		if (e.crafting.getItem().equals(LordRhysModMain.ingotBronze))
		{
			e.player.addStat(LordRhysAchievementPage.bronzeAchievement, 1);
		}
		
		if (Block.getBlockFromItem(e.crafting.getItem()).equals(LordRhysModMain.blockFurnaceOfLightIdle))
		{
			e.player.addStat(LordRhysAchievementPage.dualFurnaces, 1);
		}
		if (Block.getBlockFromItem(e.crafting.getItem()).equals(LordRhysModMain.blockGoldenFurnaceIdle))
		{
			e.player.addStat(LordRhysAchievementPage.goldenFurnaces, 1);
		}		
		
	}

	@SubscribeEvent
	public void onSmeltingThings(PlayerEvent.ItemSmeltedEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@SubscribeEvent
	public void onPlayerPickup(PlayerEvent.ItemPickupEvent e)
	{
		if(e.pickedUp.equals(LordRhysModMain.oreCopper))
		{
			e.player.addStat(LordRhysAchievementPage.miningAchievement,1);
		}
	}

	

}

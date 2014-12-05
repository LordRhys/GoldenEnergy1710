package com.lordrhys.mod.lib;

import net.minecraftforge.event.entity.player.BonemealEvent;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.block.BlockGoldenSapling;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Golden_Bonemeal_Event 
{
	@SubscribeEvent
	public void usedBonemeal(BonemealEvent event)
	{
		if (event.block == LordRhysModMain.goldenSapling)
		{
			//event.entityPlayer.addChatMessage("[EVENT] Event " + event.ID + " has occurred!");
			if (!event.world.isRemote) 
			{
				//event.entityPlayer.addChatMessage("[EVENT] Event growTree should occur!");
				((BlockGoldenSapling)LordRhysModMain.goldenSapling).growTree(event.world, event.x, event.y, event.z, event.world.rand);
			}
			event.setResult(Result.ALLOW);
		}
	}
}
  
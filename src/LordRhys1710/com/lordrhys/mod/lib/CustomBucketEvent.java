package com.lordrhys.mod.lib;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CustomBucketEvent 
{
	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		ItemStack result = fillBucket(event.world, event.target);
		if (result != null)
		{
			event.result = result;
			event.setResult(Result.ALLOW);
		}
	}

	private ItemStack fillBucket(World world, MovingObjectPosition target) {
		Block id = world.getBlock( target.blockX, target.blockY, target.blockZ );

		if ( id == LordRhysModMain.lightLiquid && world.getBlockMetadata(target.blockX, target.blockY, target.blockZ  ) == 0)
		{			
				world.setBlock( target.blockX, target.blockY, target.blockZ ,id , 0, 2); // Remove the fluid block		
				return new ItemStack( LordRhysModMain.liquidLightBucket ); // Return the filled bucket item here.			
		}
    else if ( id == LordRhysModMain.acidLiquid && world.getBlockMetadata(target.blockX, target.blockY, target.blockZ  ) == 0)
		{			
				world.setBlock( target.blockX, target.blockY, target.blockZ ,id , 0, 2); // Remove the fluid block		
				return new ItemStack( LordRhysModMain.liquidAcidBucket ); // Return the filled bucket item here.			
		}

		return null;
	}
}

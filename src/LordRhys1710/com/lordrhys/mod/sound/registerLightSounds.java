package com.lordrhys.mod.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class registerLightSounds
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onSound(SoundLoadEvent event)
	{
		/*event.manager.addSound("lordrhys_mod:weapons/lightsaber1.ogg");
		event.manager.addSound("lordrhys_mod:weapons/lightsaber2.ogg");
		event.manager.addSound("lordrhys_mod:weapons/lightsaber3.ogg");
		event.manager.addSound("lordrhys_mod:weapons/lightsaber4.ogg");*/
		
	}
}

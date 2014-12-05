package com.lordrhys.mod;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.lordrhys.mod.entity.EntityReddyFreddy;
import com.lordrhys.mod.model.ReddyFreddy;
import com.lordrhys.mod.renderer.ItemGoldenChestRenderer;
import com.lordrhys.mod.renderer.ItemRendererTrashcan;
import com.lordrhys.mod.renderer.ItemRendererWindmillBase;
import com.lordrhys.mod.renderer.RenderReddyFreddy;
import com.lordrhys.mod.renderer.RenderTrashcan;
import com.lordrhys.mod.renderer.RenderWindmill;
import com.lordrhys.mod.renderer.RenderWindmillFloor;
import com.lordrhys.mod.renderer.RendererGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityTrashcan;
import com.lordrhys.mod.tileentity.TileEntityWindmill;
import com.lordrhys.mod.tileentity.TileEntityWindmillFloor;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy
{
	public void registerRenderThings()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldenChest.class, new RendererGoldenChest());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrashcan.class, new RenderTrashcan());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new RenderWindmill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillFloor.class, new RenderWindmillFloor());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LordRhysModMain.goldenChest), new ItemGoldenChestRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LordRhysModMain.energyTrashcan), new ItemRendererTrashcan());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LordRhysModMain.blockWindmillGround), new ItemRendererWindmillBase());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityReddyFreddy.class, new RenderReddyFreddy(new ReddyFreddy(),0.3F));		
	}
	
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
  
  // KeyInputEvent is in the FML package, meaning it's posted to the FML event bus
  // rather than the regular Forge event bus:
  FMLCommonHandler.instance().bus().register(new KeyHandler());
}

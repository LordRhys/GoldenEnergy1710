package com.lordrhys.mod.renderer;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;

public class ItemGoldenChestRenderer implements IItemRenderer {

	private ModelChest modelGoldenChest;
	private final TileEntityGoldenChest goldChest = new TileEntityGoldenChest();
	
	public ItemGoldenChestRenderer()
	{
		modelGoldenChest = new ModelChest();
	}
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		TileEntityRendererDispatcher.instance.renderTileEntityAt(goldChest, 0.0D, 0.0D, 0.0D, 0.0F);
		
	}

}

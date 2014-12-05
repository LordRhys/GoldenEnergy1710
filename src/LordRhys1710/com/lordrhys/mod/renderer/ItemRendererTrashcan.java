package com.lordrhys.mod.renderer;

import com.lordrhys.mod.model.ModelTrashCan;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityTrashcan;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
//import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererTrashcan implements IItemRenderer
{
	private ModelTrashCan modelTrashcan;
	private final TileEntityTrashcan trashcan = new TileEntityTrashcan();

	public ItemRendererTrashcan()
	{
		modelTrashcan = new ModelTrashCan();
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
		TileEntityRendererDispatcher.instance.renderTileEntityAt(trashcan, 0.0D, 0.0D, 0.0D, 0.0F);
	}

}

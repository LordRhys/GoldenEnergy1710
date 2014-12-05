package com.lordrhys.mod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.container.ContainerWindmill;
import com.lordrhys.mod.tileentity.TileEntityWindmill;

public class GuiWindmill extends GuiContainer
{

	public static final ResourceLocation texture = new ResourceLocation(LordRhysModMain.modid,"textures/gui/windmillGenerator.png");
	
	private TileEntityWindmill windmill;
	
	public GuiWindmill(InventoryPlayer inventoryPlayer, TileEntityWindmill entity)
	{
		super(new ContainerWindmill(inventoryPlayer, entity));
		this.windmill = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2j)
	{
		String name = this.windmill.hasCustomInventoryName() ? this.windmill.getInventoryName() : I18n.format(this.windmill.getInventoryName(), new Object[]{});
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2,6,4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 4, 4210752);
		
	}
	
	public void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		//if (this.windmill.hasPower())
		//{
			int k = (int) this.windmill.getPowerScaled(45);
			drawTexturedModalRect(guiLeft + 80, guiTop + 65 - k, 176, 45 - k, 16, k);
		//}
		
		//int k = this.windmill.getGeneratorProgressScaled(22);
		//drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 0 , k + 1, 16);
		
	}

}

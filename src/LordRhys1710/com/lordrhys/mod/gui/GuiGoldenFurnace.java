package com.lordrhys.mod.gui;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.container.ContainerGoldenFurnace;
import com.lordrhys.mod.tileentity.TileEntityGoldenFurnace;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGoldenFurnace extends GuiContainer
{
	public static final ResourceLocation texture = new ResourceLocation(LordRhysModMain.modid,"textures/gui/goldenFurnace.png");
	
	public TileEntityGoldenFurnace goldenFurnace;
	
	public GuiGoldenFurnace(InventoryPlayer inventoryPlayer, TileEntityGoldenFurnace entity)
	{
		super(new ContainerGoldenFurnace(inventoryPlayer, entity));
		this.goldenFurnace = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2j)
	{
		String name = this.goldenFurnace.hasCustomInventoryName() ? this.goldenFurnace.getInventoryName() : 
			I18n.format(this.goldenFurnace.getInventoryName());
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2,6,4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
	}
	
	public void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (this.goldenFurnace.isBurning())
		{
			int k = this.goldenFurnace.getBurnTimeRemainingScaled(13);
			drawTexturedModalRect(guiLeft + 57, guiTop + 37 + 14 - k, 176, 14 - k, 14, k + 1);
		}
		
		int k = this.goldenFurnace.getCookProgressScaled(22);
		drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 14 , k + 1, 16);
		
	}

}

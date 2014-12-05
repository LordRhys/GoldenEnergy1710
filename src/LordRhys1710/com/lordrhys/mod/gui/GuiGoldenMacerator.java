package com.lordrhys.mod.gui;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.container.ContainerGoldenMacerator;
import com.lordrhys.mod.tileentity.TileEntityGoldenMacerator;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiGoldenMacerator extends GuiContainer
{
	public static final ResourceLocation texture = new ResourceLocation(LordRhysModMain.modid,"textures/gui/goldenMacerator.png");
	
	public TileEntityGoldenMacerator goldenMacerator;
	
	public GuiGoldenMacerator(InventoryPlayer inventoryPlayer, TileEntityGoldenMacerator entity)
	{
		super(new ContainerGoldenMacerator(inventoryPlayer, entity));
		this.goldenMacerator = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2j)
	{
		String name = this.goldenMacerator.hasCustomInventoryName() ? this.goldenMacerator.getInventoryName() : I18n.format(this.goldenMacerator.getInventoryName());
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2,6,4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 4, 4210752);
		
	}
	
	public void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (this.goldenMacerator.hasPower())
		{
			int k = this.goldenMacerator.getPowerRemainingScaled(45);
			drawTexturedModalRect(guiLeft + 8, guiTop + 53 - k, 176, 62 - k, 16, k);
		}
		
		int k = this.goldenMacerator.getMacerationProgressScaled(22);
		drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 0 , k + 1, 16);
		
	}

}

package com.lordrhys.mod.gui;

import com.lordrhys.mod.container.ContainerGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiGoldenChest extends GuiContainer
{
	public static final ResourceLocation img = new ResourceLocation("lordrhys_mod:/textures/gui/goldenChestGui.png");	
	
	public GuiGoldenChest(InventoryPlayer inventory, TileEntityGoldenChest te) 
	{
		super(new ContainerGoldenChest(inventory, te));
		xSize = 176;
		ySize = 222;
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(img);
		int k = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, y, 0, 0, this.xSize, this.ySize);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Golden Chest", 8, 6, 2237106);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

}

package com.lordrhys.mod.gui;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.container.ContainerGoldenCrafter;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGoldenCrafter extends GuiContainer
{
	private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation(LordRhysModMain.modid,"textures/gui/goldenCraftTableGUI.png");

    public GuiGoldenCrafter(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
    {
        super(new ContainerGoldenCrafter(inventoryPlayer, world, x, y, z));
        xSize = 176;
		ySize = 222;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(I18n.format("Golden Craft Table"), 28, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 112 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}

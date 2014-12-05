package com.lordrhys.mod.renderer;

import org.lwjgl.opengl.GL11;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.model.ModelTrashCan;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTrashcan extends TileEntitySpecialRenderer
{
	private static final ResourceLocation ResourceLocation = new ResourceLocation(LordRhysModMain.modid,"textures/entity/trashcan/trashCan.png");
	private ModelTrashCan model;
	
	public RenderTrashcan()
	{
		this.model = new ModelTrashCan();
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) 
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
		GL11.glRotatef(180, 0F, 0F, 1F);
		
		this.bindTexture(ResourceLocation);
		
		GL11.glPushMatrix();
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();		
	}

}

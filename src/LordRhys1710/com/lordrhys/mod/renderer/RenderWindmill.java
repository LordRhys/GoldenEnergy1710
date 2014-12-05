package com.lordrhys.mod.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityWindmill;

public class RenderWindmill extends TileEntitySpecialRenderer {

	private final ResourceLocation textureWindmill = new ResourceLocation(LordRhysModMain.modid, "textures/models/windmill.png");
	
	private float pixel = 1F/16F;
	private int textureWidth = 32;
	private int textureHeight = 32;
	
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) 
	{
		int x1 = tileEntity.xCoord;
		int z1 = tileEntity.zCoord;
		int y1 = tileEntity.yCoord;
		
		while(tileEntity.getWorldObj().getBlockMetadata(x1, y1, z1) < 7 && 
				tileEntity.getWorldObj().getBlock(x1, y1, z1).equals(LordRhysModMain.blockWindmill))
		{
			y1++;
		}
		
		int direction = tileEntity.getWorldObj().getBlockMetadata(x1, y1, z1) - 8;
		int metadata = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
		
		GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glTranslatef((float)x, (float)y, (float)z);
			
			GL11.glTranslatef(0.5F, 0, 0.5F);
			GL11.glRotatef(direction*90, 0, 1, 0);
			GL11.glTranslatef(-0.5F, 0, -0.5F);
			Tessellator tessellator = Tessellator.instance;	
			this.bindTexture(textureWindmill);
			tessellator.startDrawingQuads();	//Start of Tessellator
			{
				if (metadata > 0 && metadata < 7) 
				{
					//side 1
					tessellator.addVertexWithUV((4*pixel), 0, 1-(4*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 1, 1-(4*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 1, (4*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 0, (4*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//side 2
					tessellator.addVertexWithUV(1-(4*pixel), 0, 1-(4*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 1, 1-(4*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 1, 1-(4*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 0, 1-(4*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//side 3
					tessellator.addVertexWithUV(1-(4*pixel), 0, (4*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 1, (4*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 1, 1-(4*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 0, 1-(4*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//side 4
					tessellator.addVertexWithUV((4*pixel), 0, (4*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 1, (4*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 1, (4*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 0, (4*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//top
					tessellator.addVertexWithUV((4*pixel), 1, 1-(4*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 1, 1-(4*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 1, (4*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 1, (4*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//bottom
					tessellator.addVertexWithUV(1-(4*pixel), 0, 1-(4*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 0, 1-(4*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((4*pixel), 0, (4*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(4*pixel), 0, (4*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
				}
				if (metadata > 7) 
				{
					//side 1
					tessellator.addVertexWithUV((2*pixel), 0, 1-(2*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 1, 1-(2*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 1, (2*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 0, (2*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//side 2
					tessellator.addVertexWithUV(1-(2*pixel), 0, 1-(2*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 1, 1-(2*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 1, 1-(2*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 0, 1-(2*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//side 3
					tessellator.addVertexWithUV(1-(2*pixel), 0, (2*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 1, (2*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 1, 1-(2*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 0, 1-(2*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//side 4
					tessellator.addVertexWithUV((2*pixel), 0, (2*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 1, (2*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 1, (2*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 0, (2*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//top
					tessellator.addVertexWithUV((2*pixel), 1, 1-(2*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 1, 1-(2*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 1, (2*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 1, (2*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
					//bottom
					tessellator.addVertexWithUV(1-(2*pixel), 0, 1-(2*pixel), 8*(1F/textureWidth), 1*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 0, 1-(2*pixel), 8*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV((2*pixel), 0, (2*pixel), 0*(1F/textureWidth), 0*(1F/textureHeight));
					tessellator.addVertexWithUV(1-(2*pixel), 0, (2*pixel), 0*(1F/textureWidth), 1*(1F/textureHeight));
				}
			}
			tessellator.draw();	//End of Tessellator
			if(metadata > 7) drawRotor(tileEntity);
			GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	private void drawRotor(TileEntity tileEntity)
	{
		TileEntityWindmill windmill = (TileEntityWindmill) tileEntity.getWorldObj().getTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
		
		GL11.glTranslatef(0, 0.5F, 0.5F);
		GL11.glRotatef(windmill.rotation, 1, 0, 0);
		GL11.glTranslatef(0, -0.5F, -0.5F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		Tessellator tessellator = Tessellator.instance;	
		this.bindTexture(textureWindmill);
		tessellator.startDrawingQuads();	//Start of Tessellator
		{
			//Windmill Blades
			//blade 1
			tessellator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 1*pixel+0.5F , 9*(1F/textureWidth), 1*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 2.5		  , 1*pixel+0.5F , 9*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 2.5		  , -1*pixel+0.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1*pixel+0.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));
			//blade 2
			tessellator.addVertexWithUV(-2*pixel, -1.5F		  , 1*pixel+0.5F , 9*(1F/textureWidth), 1*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 1*pixel+0.5F , 9*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1*pixel+0.5F, 8*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, -1.5		  , -1*pixel+0.5F, 8*(1F/textureWidth), 1*(1F/textureHeight));
			//blade 3 is bent
			tessellator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 2.5F		 , 9*(1F/textureWidth), 1*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 2.5F		 , 9*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F+1*pixel , 8*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F+1*pixel , 8*(1F/textureWidth), 1*(1F/textureHeight));
			//blade 4
			tessellator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F-1*pixel , 9*(1F/textureWidth), 1*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F-1*pixel , 9*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1.5F		 , 8*(1F/textureWidth), 0*(1F/textureHeight));
			tessellator.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1.5F		 , 8*(1F/textureWidth), 1*(1F/textureHeight));
		}
		tessellator.draw();	//End of Tessellator
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

}

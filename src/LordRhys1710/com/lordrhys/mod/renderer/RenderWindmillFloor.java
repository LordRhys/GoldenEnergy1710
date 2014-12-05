package com.lordrhys.mod.renderer;

import org.lwjgl.opengl.GL11;

import com.lordrhys.mod.LordRhysModMain;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderWindmillFloor extends TileEntitySpecialRenderer 
{
	private final ResourceLocation textureWindmillFloor = new ResourceLocation(LordRhysModMain.modid, "textures/models/windmillFloor.png");
	
	private int textureWidth = 32;
	private int textureHeight = 32;
	private float pixel = 1F/16F;
	
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) 
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float)x, (float)y, (float)z);
		
		Tessellator tessellator = Tessellator.instance;	
		this.bindTexture(textureWindmillFloor);
		tessellator.startDrawingQuads();	//Start of Tessellator
		{
			int metadata = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
			switch(metadata)
			{
			case 1:
				//side 2
				tessellator.addVertexWithUV(0.5, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(0, 0, 0.5, 0, 1);
				//side 3
				tessellator.addVertexWithUV(0.5, 0, 0, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 0.5, 0, 1);
				//top
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1F/textureWidth*(32), 1F/textureHeight*(32));
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 1F/textureWidth*(32), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(0, pixel*16, 0, 1F/textureWidth*(24), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 1F/textureWidth*(24), 1F/textureHeight*(32));
				break;
			case 2:
				//side 3
				tessellator.addVertexWithUV(0.5, 0, 0, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 1, 0, 1);
				
				//top
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 1F/textureWidth*(32), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 1F/textureWidth*(32), 1F/textureHeight*(8));
				tessellator.addVertexWithUV(0, pixel*16, 0, 1F/textureWidth*24, 1F/textureHeight*8);
				tessellator.addVertexWithUV(0, pixel*16, 1, 1F/textureWidth*24, 1F/textureHeight*(24));
				break;
			case 3:				
				//side 3
				tessellator.addVertexWithUV(0.5, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 1, 0, 1);
				
				//side 4
				tessellator.addVertexWithUV(0, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 0.5, 0, 1);
				//top
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 1F/textureWidth*(32), 1F/textureHeight*(8));
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1F/textureWidth*(32), 1F/textureHeight*(0));
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 1F/textureWidth*24, 1F/textureHeight*0);
				tessellator.addVertexWithUV(0, pixel*16, 1, 1F/textureWidth*24, 1F/textureHeight*(8));
				break;
			case 4:
				//side 2
				tessellator.addVertexWithUV(1, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(0, 0, 0.5, 0, 1);
				
				//top
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 1F/textureWidth*(24), 1F/textureHeight*(32));
				tessellator.addVertexWithUV(1, pixel*16, 0, 1F/textureWidth*(24), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(0, pixel*16, 0, 1F/textureWidth*8, 1F/textureHeight*24);
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 1F/textureWidth*8, 1F/textureHeight*(32));
				break;
			case 5:
				//top middle
				tessellator.addVertexWithUV(1, pixel*16, 1, 1F/textureWidth*(24), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(1, pixel*16, 0, 1F/textureWidth*(24), 1F/textureHeight*(8));
				tessellator.addVertexWithUV(0, pixel*16, 0, 1F/textureWidth*8, 1F/textureHeight*8);
				tessellator.addVertexWithUV(0, pixel*16, 1, 1F/textureWidth*8, 1F/textureHeight*(24));
				break;
			case 6:
				//side 4
				tessellator.addVertexWithUV(0, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(1, 0, 0.5, 0, 1);
				//top
				tessellator.addVertexWithUV(1, pixel*16, 1, 1F/textureWidth*(24), 1F/textureHeight*(8));
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 1F/textureWidth*(24), 1F/textureHeight*(0));
				tessellator.addVertexWithUV(0, pixel*16, 0.5, 1F/textureWidth*8, 1F/textureHeight*0);
				tessellator.addVertexWithUV(0, pixel*16, 1, 1F/textureWidth*8, 1F/textureHeight*(8));
				break;
			case 7:
				//side 1
				tessellator.addVertexWithUV(0.5, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 0, 0, 1);
				//side 2
				tessellator.addVertexWithUV(1, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 0.5, 0, 1);
				//top
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 1F/textureWidth*(8), 1F/textureHeight*(32));
				tessellator.addVertexWithUV(1, pixel*16, 0, 1F/textureWidth*(8), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 1F/textureWidth*0, 1F/textureHeight*24);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1F/textureWidth*0, 1F/textureHeight*(32));
				break;
			case 8:
				//side 1
				tessellator.addVertexWithUV(0.5, 0, 1, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 0, 0, 1);
				//top
				tessellator.addVertexWithUV(1, pixel*16, 1, 1F/textureWidth*(8), 1F/textureHeight*(24));
				tessellator.addVertexWithUV(1, pixel*16, 0, 1F/textureWidth*(8), 1F/textureHeight*(8));
				tessellator.addVertexWithUV(0.5, pixel*16, 0, 1F/textureWidth*0, 1F/textureHeight*8);
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 1F/textureWidth*0, 1F/textureHeight*(24));
				break;
			case 9:
				//side 1
				tessellator.addVertexWithUV(0.5, 0, 1, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 1, 0);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(0.5, 0, 0.5, 0, 1);
				//side 4
				tessellator.addVertexWithUV(0.5, 0, 0.5, 1, 1);
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1, 0);
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 0, 0);
				tessellator.addVertexWithUV(1, 0, 0.5, 0, 1);
				//top
				tessellator.addVertexWithUV(1, pixel*16, 1, 1F/textureWidth*(8), 1F/textureHeight*(8));
				tessellator.addVertexWithUV(1, pixel*16, 0.5, 1F/textureWidth*(8), 1F/textureHeight*(0));
				tessellator.addVertexWithUV(0.5, pixel*16, 0.5, 1F/textureWidth*0, 1F/textureHeight*0);
				tessellator.addVertexWithUV(0.5, pixel*16, 1, 1F/textureWidth*0, 1F/textureHeight*(8));
				break;
			default:
				tessellator.addVertexWithUV(1, pixel*16, 1, 1F/textureWidth*(32), 1F/textureHeight*(32));
				tessellator.addVertexWithUV(1, pixel*16, 0, 1F/textureWidth*(32), 1F/textureHeight*(0));
				tessellator.addVertexWithUV(0, pixel*16, 0, 1F/textureWidth*0, 1F/textureHeight*0);
				tessellator.addVertexWithUV(0, pixel*16, 1, 1F/textureWidth*0, 1F/textureHeight*(32));
			}
		}
		tessellator.draw();	//End of Tessellator
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

}

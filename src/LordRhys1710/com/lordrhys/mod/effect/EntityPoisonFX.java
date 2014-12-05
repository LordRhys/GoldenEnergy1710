package com.lordrhys.mod.effect;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

import com.lordrhys.mod.LordRhysModMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPoisonFX extends EntityFX
{
	private static final ResourceLocation texture = new ResourceLocation(LordRhysModMain.modid, "textures/particle/poison.png");
	
	public EntityPoisonFX(World world, double x, double y, double z) 
	{
		super(world, x, y, z);
		setMaxAge(100);
		setGravity(-1.5F);
		setScale(1.5F);
	}
	
	public void renderParticle(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glDepthMask(false);
		GL11.glEnable(GL_BLEND);
		GL11.glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL_GREATER, 0.003921569F);
		tess.startDrawingQuads();
		tess.setBrightness(getBrightnessForRender(partialTicks));
		float scale = 0.1F * particleScale;
		float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		tess.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
		tess.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
		tess.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, 1, 1);
		tess.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale + par7 * scale, 0, 1);
		tess.draw();
		GL11.glDisable(GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glAlphaFunc(GL_GREATER, 0.1F);
	}
	
	public int getFXLayer()
	{
		return 3;		
	}
	
	public EntityPoisonFX setMaxAge(int maxAge)
	{
		particleMaxAge = maxAge;
		return this;
	}
	
	public EntityPoisonFX setGravity(float gravity)
	{
		particleGravity = gravity;
		return this;
	}
	
	public EntityPoisonFX setScale(float scale)
	{
		particleScale = scale;
		return this;
	}

}

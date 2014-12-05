package com.lordrhys.mod.renderer;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.entity.EntityReddyFreddy;
import com.lordrhys.mod.model.ReddyFreddy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderReddyFreddy extends RenderLiving
{
	protected ReddyFreddy model;
	
	public RenderReddyFreddy(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
		model = ((ReddyFreddy)mainModel);
	}
	
	public void renderReddyFreddy(EntityReddyFreddy entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderReddyFreddy((EntityReddyFreddy)par1EntityLiving, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
	{
		renderReddyFreddy((EntityReddyFreddy)entity, d0, d1, d2, f, f1);

	}

	public ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation(LordRhysModMain.modid,"textures/mobs/ReddyFreddy.png");
	}

}

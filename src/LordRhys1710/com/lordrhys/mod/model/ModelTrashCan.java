package com.lordrhys.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrashCan extends ModelBase
{
  //fields
    ModelRenderer Bottom;
    ModelRenderer Side1;
    ModelRenderer Side2;
    ModelRenderer Side3;
    ModelRenderer Side4;
      
  public ModelTrashCan()
  {
    textureWidth = 64;
    textureHeight = 64;
    
	Bottom = new ModelRenderer(this, 0, 0);
	Bottom.addBox(0F, 0F, 0F, 12, 1, 12);
	Bottom.setRotationPoint(-6F, 23F, -6F);
	Bottom.setTextureSize(64, 32);
	Bottom.mirror = true;
	setRotation(Bottom, 0F, 0F, 0F);
	Side1 = new ModelRenderer(this, 0, 14);
	Side1.addBox(-1F, -12F, -6F, 1, 12, 12);
	Side1.setRotationPoint(-5F, 23F, 0F);
	Side1.setTextureSize(64, 32);
	Side1.mirror = true;
	setRotation(Side1, 0F, 0F, -0.0872665F);
	Side2 = new ModelRenderer(this, 27, 14);
	Side2.addBox(0F, -12F, -6F, 1, 12, 12);
	Side2.setRotationPoint(0F, 23F, 6F);
	Side2.setTextureSize(64, 32);
	Side2.mirror = true;
	setRotation(Side2, 0F, 0F, -0.0872665F);      
	Side3 = new ModelRenderer(this, 0, 39);
	Side3.addBox(0F, -12F, -7F, 1, 12, 12);
	Side3.setRotationPoint(5F, 23F, 1F);
	Side3.setTextureSize(64, 32);
	Side3.mirror = true;
	setRotation(Side3, 0F, 0F, 0.0872665F);
	Side4 = new ModelRenderer(this, 28, 39);
	Side4.addBox(0F, -12F, -6F, 1, 12, 12);
	Side4.setRotationPoint(0F, 23F, -5F);
	Side4.setTextureSize(64, 32);
	Side4.mirror = true;
	setRotation(Side4, 0F, 0F, 0.0872665F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bottom.render(f5);
    Side1.render(f5);
    Side2.render(f5);
    Side3.render(f5);
    Side4.render(f5);    
  }
  
  public void renderModel(float f) 
  {
	Bottom.render(f);
    Side1.render(f);
    Side2.render(f);
    Side3.render(f);
    Side4.render(f);    
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  } 

}

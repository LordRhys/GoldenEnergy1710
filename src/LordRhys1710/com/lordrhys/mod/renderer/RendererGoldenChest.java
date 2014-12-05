package com.lordrhys.mod.renderer;

import java.util.Calendar;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.block.BlockGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererGoldenChest extends TileEntitySpecialRenderer
{
    private static final ResourceLocation RES_GOLDEN_DOUBLE = new ResourceLocation(LordRhysModMain.modid + ":textures/entity/chest/goldenChest_double.png");
    private static final ResourceLocation RES_GOLDEN_SINGLE = new ResourceLocation(LordRhysModMain.modid + ":textures/entity/chest/goldenChest.png");
    private static final ResourceLocation RES_TRAPPED_DOUBLE = new ResourceLocation(LordRhysModMain.modid + ":textures/entity/chest/trapped_double.png");
    private static final ResourceLocation RES_CHRISTMAS_DOUBLE = new ResourceLocation(LordRhysModMain.modid + ":textures/entity/chest/christmas_double.png");
    private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation(LordRhysModMain.modid + ":textures/entity/chest/trapped.png");
    private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation(LordRhysModMain.modid + ":textures/entity/chest/christmas.png");    

    /** The normal small chest model. */
    private ModelChest goldenChestModel;

    /** The large double chest model. */
    private ModelChest largeGoldenChestModel;

    /** If true, chests will be rendered with the Christmas present textures. */
    private boolean isChristmas;

    public RendererGoldenChest()
    {
    	this.goldenChestModel = new ModelChest();
    	this.largeGoldenChestModel = new ModelLargeChest();
    	
    	Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
        {
            this.isChristmas = true;
        }
    }

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void renderTileEntityChestAt(TileEntityGoldenChest tileEntityGoldenChest, double x, double y, double z, float par8)
    {
        int i;

        if (!tileEntityGoldenChest.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            Block block = tileEntityGoldenChest.getBlockType();
            i = tileEntityGoldenChest.getBlockMetadata();

            if (block instanceof BlockGoldenChest && i == 0)
            {
                try
                {
                    ((BlockGoldenChest)block).unifyAdjacentChests(tileEntityGoldenChest.getWorldObj(), tileEntityGoldenChest.xCoord, tileEntityGoldenChest.yCoord, tileEntityGoldenChest.zCoord);
                }
                catch (ClassCastException e)
                {
                    FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest",
                            tileEntityGoldenChest.xCoord, tileEntityGoldenChest.yCoord, tileEntityGoldenChest.zCoord);
                }
                i = tileEntityGoldenChest.getBlockMetadata();
            }

            tileEntityGoldenChest.checkForAdjacentChests();
        }

        if (tileEntityGoldenChest.adjacentChestZNeg == null && tileEntityGoldenChest.adjacentChestXNeg == null)
        {
            ModelChest modelchest;
            
            if (tileEntityGoldenChest.adjacentChestXPos == null && tileEntityGoldenChest.adjacentChestZPosition == null)
            {
                modelchest = this.goldenChestModel;

                if (tileEntityGoldenChest.getChestType() == 1)
                {
                    this.bindTexture(RES_TRAPPED_SINGLE);
                }
                else if (this.isChristmas)
                {
                    this.bindTexture(RES_CHRISTMAS_SINGLE);
                }
                else
                {
                    this.bindTexture(RES_GOLDEN_SINGLE);
                }
            }
            else
            {
            	modelchest = this.largeGoldenChestModel;

                if (tileEntityGoldenChest.getChestType() == 1)
                {
                    this.bindTexture(RES_TRAPPED_DOUBLE);
                }
                else if (this.isChristmas)
                {
                    this.bindTexture(RES_CHRISTMAS_DOUBLE);
                }
                else
                {
                    this.bindTexture(RES_GOLDEN_DOUBLE);
                }
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (i == 2)
            {
                short1 = 180;
            }

            if (i == 3)
            {
                short1 = 0;
            }

            if (i == 4)
            {
                short1 = 90;
            }

            if (i == 5)
            {
                short1 = -90;
            }

            if (i == 2 && tileEntityGoldenChest.adjacentChestXPos != null)
            {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && tileEntityGoldenChest.adjacentChestZPosition != null)
            {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = tileEntityGoldenChest.prevLidAngle + (tileEntityGoldenChest.lidAngle - tileEntityGoldenChest.prevLidAngle) * par8;
            float f2;

            if (tileEntityGoldenChest.adjacentChestZNeg != null)
            {
                f2 = tileEntityGoldenChest.adjacentChestZNeg.prevLidAngle + (tileEntityGoldenChest.adjacentChestZNeg.lidAngle - tileEntityGoldenChest.adjacentChestZNeg.prevLidAngle) * par8;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (tileEntityGoldenChest.adjacentChestXNeg != null)
            {
                f2 = tileEntityGoldenChest.adjacentChestXNeg.prevLidAngle + (tileEntityGoldenChest.adjacentChestXNeg.lidAngle - tileEntityGoldenChest.adjacentChestXNeg.prevLidAngle) * par8;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityChestAt((TileEntityGoldenChest)par1TileEntity, par2, par4, par6, par8);
    }
}


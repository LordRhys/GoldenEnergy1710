package com.lordrhys.mod.tool;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToolEnergyHoe extends ItemHoe
{

	public ToolEnergyHoe(ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.setCreativeTab(LordRhysModMain.energyToolsTab);
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if (!entityPlayer.canPlayerEdit(x, y, z, par7, itemStack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(entityPlayer, itemStack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Result.ALLOW)
            {
                itemStack.damageItem(1, entityPlayer);
                return true;
            }

            Block i1 = world.getBlock(x, y, z);
            boolean air = world.isAirBlock(x, y + 1, z);

            if (par7 != 0 && air && (i1 == Blocks.grass || i1 == Blocks.dirt || i1 == LordRhysModMain.goldenDirt || 
            		i1 == LordRhysModMain.goldenGrass))
            {
                
            	Block block = Blocks.farmland;
            	if(i1 == LordRhysModMain.goldenDirt || i1 == LordRhysModMain.goldenGrass)
                {
                	block = LordRhysModMain.tilledGoldenField;
                }
                          	
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
                		block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, 
                		block.stepSound.getPitch() * 0.8F);

                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    world.setBlock(x, y, z, block);
                    itemStack.damageItem(1, entityPlayer);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));        
    }

}

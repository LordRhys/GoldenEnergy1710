package com.lordrhys.mod.gui;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.container.ContainerFurnaceOfLight;
import com.lordrhys.mod.container.ContainerGoldenChest;
import com.lordrhys.mod.container.ContainerGoldenCrafter;
import com.lordrhys.mod.container.ContainerGoldenFurnace;
import com.lordrhys.mod.container.ContainerGoldenMacerator;
import com.lordrhys.mod.container.ContainerWindmill;
import com.lordrhys.mod.tileentity.TileEntityFurnaceOfLight;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityGoldenCrafter;
import com.lordrhys.mod.tileentity.TileEntityGoldenFurnace;
import com.lordrhys.mod.tileentity.TileEntityGoldenMacerator;
import com.lordrhys.mod.tileentity.TileEntityWindmill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CustomGUIHandler implements IGuiHandler
{
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{				
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if (entity != null)
		{
			switch(id)
			{
				case LordRhysModMain.guiIdGoldenFurnace:
					if (entity instanceof TileEntityGoldenFurnace)
					{
						return new ContainerGoldenFurnace(player.inventory, (TileEntityGoldenFurnace) entity);
					}
				case LordRhysModMain.guiIdGoldenChest:
					if (entity instanceof TileEntityGoldenChest)
					{
						return new ContainerGoldenChest(player.inventory, (TileEntityGoldenChest)entity);
					}
				case LordRhysModMain.guiIdFurnaceOfLight:
					if (entity instanceof TileEntityFurnaceOfLight)
					{
						return new ContainerFurnaceOfLight(player.inventory, (TileEntityFurnaceOfLight) entity);
					}
				case LordRhysModMain.guiIdGoldenMacerator:
					if (entity instanceof TileEntityGoldenMacerator)
					{
						return new ContainerGoldenMacerator(player.inventory, (TileEntityGoldenMacerator) entity);
					}
				case LordRhysModMain.guiIdWindmillGenerator:
					while (entity instanceof TileEntityWindmill && world.getBlockMetadata(x, y, z) < 8)
					{
						y++;
					}
						return new ContainerWindmill(player.inventory, (TileEntityWindmill) world.getTileEntity(x, y, z));
					
			}
		}
		else
		{
			switch(id)
			{				
				case LordRhysModMain.guiIdGoldenCraftTable:
					return new ContainerGoldenCrafter(player.inventory, world, x, y, z);					
			}
		}
		
		return null;
	}
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{		
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if (entity != null)
		{
			switch(id)
			{
				case LordRhysModMain.guiIdGoldenFurnace:
					if (entity instanceof TileEntityGoldenFurnace)
					{
						return new GuiGoldenFurnace(player.inventory, (TileEntityGoldenFurnace) entity);
					}
				case LordRhysModMain.guiIdGoldenChest:
					if (entity instanceof TileEntityGoldenChest)
					{
						return new GuiGoldenChest(player.inventory, (TileEntityGoldenChest)entity);
					}
				case LordRhysModMain.guiIdFurnaceOfLight:
					if (entity instanceof TileEntityFurnaceOfLight)
					{
						return new GuiFurnaceOfLight(player.inventory, (TileEntityFurnaceOfLight) entity);
					}
				case LordRhysModMain.guiIdGoldenMacerator:
					if (entity instanceof TileEntityGoldenMacerator)
					{
						return new GuiGoldenMacerator(player.inventory, (TileEntityGoldenMacerator) entity);
					}
				case LordRhysModMain.guiIdWindmillGenerator:
					while (entity instanceof TileEntityWindmill && world.getBlockMetadata(x, y, z) < 8)
					{
						y++;
					}
					return new GuiWindmill(player.inventory, (TileEntityWindmill) world.getTileEntity(x, y, z));
			}
		}
		else
		{
			switch(id)
			{				
				case LordRhysModMain.guiIdGoldenCraftTable:
					return new GuiGoldenCrafter(player.inventory, world, x, y, z);					
			}
		}
		
		return null;
	} 
}

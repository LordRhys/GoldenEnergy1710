package com.lordrhys.mod.gui;

import com.lordrhys.mod.container.ContainerGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CustomChestHandler implements IGuiHandler
{
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityGoldenChest)
		{
			return new ContainerGoldenChest(player.inventory, (TileEntityGoldenChest)te);
		}
		return null;
	}
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityGoldenChest)
		{
			return new GuiGoldenChest(player.inventory, (TileEntityGoldenChest)te);
		}
		return null;
	} 
}

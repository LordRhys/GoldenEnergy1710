package com.lordrhys.mod.container;

import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGoldenChest extends Container {

	protected TileEntityGoldenChest te;
	private int slotCount = 0;
	
	public ContainerGoldenChest(InventoryPlayer inv, TileEntityGoldenChest TE)
	{
		this.te = TE;
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(te, slotCount++, 9 + j * 18, 19 + i * 18));				
			}
		}
		fillInv(inv);
	}	
	

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return te.isUseableByPlayer(player);
	}
	
	protected void fillInv(InventoryPlayer inv)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(inv, j + i * 9 + 9, 9 + j * 18, 141 + i * 18));
			}
		}
		for (int i = 0; i < 9; i++)
		{			
				addSlotToContainer(new Slot(inv, i, 9 + i * 18, 199));			
		}		
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		ItemStack stack = null;
		Slot slotObject = (Slot)inventorySlots.get(slot);
		
		if (slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			if (slotObject.inventory instanceof TileEntityGoldenChest)
			{
				if (!this.mergeItemStack(stackInSlot, 54, 90, true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(stackInSlot, 0, 53, false))
			{
				return null;
			}			
			
			
			if (stackInSlot.stackSize == 0)
			{
				slotObject.putStack(null);
			}
			else
			{
				slotObject.onSlotChanged();
			}
			
			if (stackInSlot.stackSize == stack.stackSize)
			{
				return null;
			}
			
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		
		return stack;
	}

}

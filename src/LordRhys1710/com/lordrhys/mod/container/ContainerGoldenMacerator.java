package com.lordrhys.mod.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.lordrhys.mod.tileentity.TileEntityGoldenMacerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerGoldenMacerator extends Container 
{
	private TileEntityGoldenMacerator goldenMacerator;
	public int lastBurnTime;		
	public int lastItemBurnTime;		
	public int lastCookTime;
	
	public ContainerGoldenMacerator(InventoryPlayer inventory, TileEntityGoldenMacerator tileEntity)
	{
		this.goldenMacerator = tileEntity;
		
		// this.addSlotToContainer(new Slot(tileEntity, 0, 36, 17 )); if using dual furnace
		this.addSlotToContainer(new Slot(tileEntity, 0, 56, 35 )); // slot 1 if dual furnace and so on
		this.addSlotToContainer(new Slot(tileEntity, 1, 8, 56 ));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 2, 116, 35 ));
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 ));
			}
		}
		
		for ( int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}
	
	public void addCraftingToCrafters(ICrafting iCrafting)
	{
		super.addCraftingToCrafters(iCrafting);
		iCrafting.sendProgressBarUpdate(this, 0, this.goldenMacerator.cookTime);
		iCrafting.sendProgressBarUpdate(this, 1, this.goldenMacerator.power);		
	}
	
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting iCrafting = (ICrafting)this.crafters.get(i);
			
			if (this.lastCookTime != this.goldenMacerator.cookTime)
			{
				iCrafting.sendProgressBarUpdate(this, 0, this.goldenMacerator.cookTime);
			}
			if (this.lastBurnTime != this.goldenMacerator.power)
			{
				iCrafting.sendProgressBarUpdate(this, 1, this.goldenMacerator.power);
			}			
		}
		
		this.lastCookTime = this.goldenMacerator.cookTime;
		this.lastBurnTime = this.goldenMacerator.power;		
	}
	
	
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotClicked)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotClicked);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if (slotClicked == 2)
			{
				if (!this.mergeItemStack(stack, 3, 39, true))
				{
					return null;
				}
				
				slot.onSlotChange(stack, itemstack);
			}
			else if (slotClicked != 1 && slotClicked != 0)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(stack) != null)
				{
					if (!this.mergeItemStack(stack, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntityGoldenMacerator.hasItemPower(stack))
				{
					if (!this.mergeItemStack(stack, 1, 2, false))
					{
						return null;
					}
				}
				else if (slotClicked >= 3 && slotClicked < 30)
				{
					if (!this.mergeItemStack(stack, 30, 39, false))
					{
						return null;
					}
				}
				else if (slotClicked >= 30 && slotClicked < 39)
				{
					if (!this.mergeItemStack(stack, 3, 30, false))
					{
						return null;
					}
				}
			}
			else if (!this.mergeItemStack(stack, 3, 39, false))
			{
				return null;
			}
			
			if (stack.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (stack.stackSize == itemstack.stackSize)
			{
				return null;
			}
			
			slot.onPickupFromSlot(player, stack);
		}		
		
		return itemstack;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue)
	{
		if (slot == 0) this.goldenMacerator.cookTime = newValue;
		if (slot == 1) this.goldenMacerator.power = newValue;				
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.goldenMacerator.isUseableByPlayer(entityplayer);
	}

}

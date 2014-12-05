package com.lordrhys.mod.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.lordrhys.mod.tileentity.TileEntityFurnaceOfLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerFurnaceOfLight extends Container 
{
	private TileEntityFurnaceOfLight FurnaceOfLight;
	public int lastBurnTime;		
	public int lastItemBurnTime;		
	public int lastCookTime;
	
	public ContainerFurnaceOfLight(InventoryPlayer inventory, TileEntityFurnaceOfLight tileEntity)
	{
		this.FurnaceOfLight = tileEntity;
		
		this.addSlotToContainer(new Slot(tileEntity, 0, 36, 17 )); //if using dual furnace
		this.addSlotToContainer(new Slot(tileEntity, 1, 56, 17 )); // slot 1 if dual furnace and so on
		this.addSlotToContainer(new Slot(tileEntity, 2, 56, 53 ));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 3, 116, 35 ));
		
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
		iCrafting.sendProgressBarUpdate(this, 0, this.FurnaceOfLight.dualCookTime);
		iCrafting.sendProgressBarUpdate(this, 1, this.FurnaceOfLight.dualBurnTime);
		iCrafting.sendProgressBarUpdate(this, 2, this.FurnaceOfLight.dualCurrentItemBurnTime);
	}
	
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting iCrafting = (ICrafting)this.crafters.get(i);
			
			if (this.lastCookTime != this.FurnaceOfLight.dualCookTime)
			{
				iCrafting.sendProgressBarUpdate(this, 0, this.FurnaceOfLight.dualCookTime);
			}
			if (this.lastBurnTime != this.FurnaceOfLight.dualBurnTime)
			{
				iCrafting.sendProgressBarUpdate(this, 1, this.FurnaceOfLight.dualBurnTime);
			}
			if (this.lastItemBurnTime != this.FurnaceOfLight.dualCurrentItemBurnTime)
			{
				iCrafting.sendProgressBarUpdate(this, 2, this.FurnaceOfLight.dualCurrentItemBurnTime);
			}
		}
		
		this.lastCookTime = this.FurnaceOfLight.dualCookTime;
		this.lastBurnTime = this.FurnaceOfLight.dualBurnTime;
		this.lastItemBurnTime = this.FurnaceOfLight.dualCurrentItemBurnTime;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotClicked)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotClicked);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if (slotClicked == 3)
			{
				if (!this.mergeItemStack(stack, 4, 40, true))
				{
					return null;
				}
				
				slot.onSlotChange(stack, itemstack);
			}
			else if (slotClicked != 1 && slotClicked != 0 && slotClicked != 2)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(stack) != null) //this needs to also include new recipes
				{
					if (!this.mergeItemStack(stack, 0, 2, false))
					{
						return null;
					}
				}
				else if (TileEntityFurnaceOfLight.isItemFuel(stack))
				{
					if (!this.mergeItemStack(stack, 2, 3, false))
					{
						return null;
					}
				}
				else if (slotClicked >= 4 && slotClicked < 31)
				{
					if (!this.mergeItemStack(stack, 31, 40, false))
					{
						return null;
					}
				}
				else if (slotClicked >= 31 && slotClicked < 40)
				{
					if (!this.mergeItemStack(stack, 4, 31, false))
					{
						return null;
					}
				}
			}
			else if (!this.mergeItemStack(stack, 4, 40, false))
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
		if (slot == 0) this.FurnaceOfLight.dualCookTime = newValue;
		if (slot == 1) this.FurnaceOfLight.dualBurnTime = newValue;
		if (slot == 2) this.FurnaceOfLight.dualCurrentItemBurnTime = newValue;		
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.FurnaceOfLight.isUseableByPlayer(entityplayer);
	}

}

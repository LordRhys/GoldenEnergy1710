package com.lordrhys.mod.tileentity;

import com.lordrhys.mod.item.ItemBattery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWindmill extends TileEntity implements ISidedInventory
{
	public float rotation = 0;
	public int maxPower = 10000;
	public float power = 0;
	public float powerPerRotation = 0.25F;
	private String localizedName;
	
	private static final int[] slots_input = new int[]{0};
	private static final int[] slots_output = new int[]{1};
	
	private ItemStack[] slots = new ItemStack[2];
	
	public void updateEntity()
	{
		if (this.getWorldObj().getBlockMetadata(xCoord, yCoord, zCoord) > 6) {
			rotation++;
			powerPerRotation = 0.25F;
			power += powerPerRotation;
			if (power > maxPower)
				power = maxPower;
		}
		
		boolean flag = this.power > 0;
		boolean flag1 = false;
		
		if (!this.worldObj.isRemote)
		{
			if (this.canHavePower(this.slots[0]) && this.power >= (this.slots[0].getMaxDamage() - this.slots[0].getItemDamage()))
			{
				if(this.slots[0].getItemDamage() < this.slots[0].getMaxDamage())
				{
					
					this.slots[0] = new ItemStack(this.slots[0].getItem(), this.slots[0].stackSize, this.slots[0].getItemDamage()-1);
					this.power -= 1;
				}
			}
		}		
	}
	
	private boolean canHavePower(ItemStack itemStack) 
	{
		if (itemStack != null) 
		{
			Item item = itemStack.getItem();
			if (item instanceof ItemBattery && itemStack.isItemDamaged()) {
				return true;
			}
		}
		
		return false;		
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("Slots", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound item = list.getCompoundTagAt(i);
			byte b = item.getByte("Item");
			
			if (b >= 0 && b < this.slots.length)
			{
				this.slots[b] = ItemStack.loadItemStackFromNBT(item);
			}
		}
		
		this.power = nbt.getFloat("Power");
		this.powerPerRotation = nbt.getFloat("RotationPower");
				
		if (nbt.hasKey("CustomName"))
		{
			this.localizedName = nbt.getString("CustomName");
		}	
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setFloat("Power", this.power);
		nbt.setFloat("RotationPower", this.powerPerRotation);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.slots.length; i++)
		{
			if(this.slots[i] != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Item", (byte)i);
				this.slots[i].writeToNBT(item);
				list.appendTag(item);
			}
		}
		
		nbt.setTag("Slots", list);
		
		if(this.hasCustomInventoryName())
		{
			nbt.setString("CustomName", this.localizedName);
		}
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) 
	{
		return true;
	}

	public boolean hasCustomInventoryName() 
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public String getInventoryName() 
	{
		return this.hasCustomInventoryName() ? this.localizedName : "Windmill Generator";
	}	

	public int getSizeInventory() 
	{
		return this.slots.length;
	}

	public ItemStack getStackInSlot(int i) 
	{
		return this.slots[i];
	}

	public ItemStack decrStackSize(int i, int j) 
	{
		if (this.slots[i] != null)
		{
			ItemStack itemstack;
			
			if (this.slots[i].stackSize <= j)
			{
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.slots[i].splitStack(j);
				
				if (this.slots[i].stackSize == 0)
				{
					this.slots[i] = null;
				}
				
				return itemstack;
			}
		}
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int i) 
	{
		if(this.slots[i] != null)
		{
			ItemStack itemStack = this.slots[i];
			this.slots[i] = null;
			return itemStack;
		}
		
		return null;
	}

	public void setInventorySlotContents(int i, ItemStack itemStack) 
	{
		this.slots[i] = itemStack;
		
		if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public int getInventoryStackLimit() 
	{
		return 64;
	}

	public void openInventory() 
	{
		
	}

	public void closeInventory() 
	{
		
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if(i == 0 )
		{
			if(this.canHavePower(this.slots[i]) && this.power >= (this.slots[i].getMaxDamage() - this.slots[i].getItemDamage()))
			{
				return true;
			}
		}
		return false;
	}

	public int[] getAccessibleSlotsFromSide(int var1) 
	{
		return var1 == 0 ? slots_input : (var1 == 1 ? slots_output : null);
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return this.isItemValidForSlot(i, itemstack);		
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j) 
	{
		return j != 0 || i != 1 || itemstack.getItem() instanceof ItemBattery;
	}
	
	public boolean hasPower() 
	{
		return (this.power > 0);
	}

	public float getPowerScaled(int scaled) 
	{
		return this.power * scaled / this.maxPower;
	}

	
}

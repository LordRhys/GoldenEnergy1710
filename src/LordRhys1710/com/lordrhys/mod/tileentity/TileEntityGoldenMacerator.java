package com.lordrhys.mod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.block.BlockGoldenMacerator;
import com.lordrhys.mod.crafting.OreRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGoldenMacerator extends TileEntity implements ISidedInventory
{
	private String localizedName;
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2,1}; // slots listed in priority
	private static final int[] slots_sides = new int[]{1};
	
	private ItemStack[] slots = new ItemStack[3];
	
	public int maceratingSpeed = 70; 
	public int power;
	public final int maxPower = 10000;
	public int cookTime;
	
	public int getSizeInventory()
	{
		return this.slots.length;
	}
	
	public void setGuiDisplayName(String displayName)
	{
		this.localizedName = displayName;		
	}

	public boolean hasCustomInventoryName()
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.localizedName : "Golden Macerator";
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
		if (this.slots[i] != null)
		{
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		
		return null;
	}

	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.slots[i] = itemstack;
		
		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			
			if (b >= 0 && b < this.slots.length)
			{
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		this.power = nbt.getShort("power");
		this.cookTime = nbt.getShort("CookTime");
				
		if (nbt.hasKey("CustomName"))
		{
			this.localizedName = nbt.getString("CustomName");
		}		
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setShort("power", (short)this.power);
		nbt.setShort("CookTime", (short)this.cookTime);
				
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < this.slots.length; i++)
		{
			if (this.slots[i] != null)
			{
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte)i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		
		nbt.setTag("Items", list);
		
		if(this.hasCustomInventoryName())
		{
			nbt.setString("CustomName", this.localizedName);
		}
	}
	
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, 
				(double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory()
	{
		
	}

	public void closeInventory()
	{
		
	}
	
	public void updateEntity()
	{
		boolean flag = this.power > 0;
		boolean flag1 = false;
		
		if (this.hasPower() && this.isMacerating())
		{
			this.power--;
		}
		
		if (!this.worldObj.isRemote)
		{
			if (this.hasItemPower(this.slots[1]) && this.power <= (this.maxPower - this.getItemPower(this.slots[1])))  
			{
				if (!this.slots[1].isItemStackDamageable()) 
				{
					this.power += getItemPower(this.slots[1]);
					if (this.slots[1] != null) {
						flag1 = true;
						this.slots[1].stackSize--;

						if (this.slots[1].stackSize == 0) 
						{
							this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);

						}
					}
				}
				else
				{
					if(this.slots[1].getItemDamage() < this.slots[1].getMaxDamage())
					{
						this.power += getItemPower(this.slots[1]);
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage()+1);
					}
					
					if(this.slots[1].getItemDamage() >= this.slots[1].getMaxDamage() && !this.slots[1].getItem().isRepairable())
					{
						this.slots[1].stackSize--;
					}					
					
					if (this.slots[1].stackSize == 0) 
					{
						this.slots[1] = null;
					}
				}
			}
			
			if (this.hasPower() && this.canMacerate())
			{
				this.cookTime++;
				if (this.cookTime == this.maceratingSpeed)
				{
					this.cookTime = 0;
					this.macerateItem();
					flag1 = true;
				}
			}
			else
			{
				this.cookTime = 0;
			}
		
			if (flag != this.power > 0)
			{
				flag1 = true;
				BlockGoldenMacerator.updateFurnaceBlockState(this.hasPower(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if (flag1)
		{
			this.markDirty();
		}
	}
	
	private boolean isMacerating() 
	{
		return this.cookTime > 0;
	}

	String[] ores = OreDictionary.getOreNames();
	
	private boolean isOre(ItemStack itemStack) 
	{
		for(int i = 0; i < ores.length; i++)
		{
			if(ores[i].contains("ore"))
			{
				if(OreDictionary.getOres(ores[i]) != null)
				{
					if(OreDictionary.getOres(ores[i]).get(0).getItem() == itemStack.getItem())
					{
						return true;
					}
				}
			}
		}
		return false;
		
		
	}
	
	public void macerateItem()
	{
		if (this.canMacerate())
		{
			ItemStack itemstack  = OreRecipes.getRecipes().getSmeltingResult(this.slots[0]);
			
			if (this.slots[2] == null)
			{
				this.slots[2] = itemstack.copy();
				this.slots[2].stackSize *= 2; // returns double amount
			}
			else if (this.slots[2].isItemEqual(itemstack))
			{
				this.slots[2].stackSize += itemstack.stackSize*2; // returns double amount
			}
			
			this.slots[0].stackSize--;
			
			if (this.slots[0].stackSize <= 0)
			{
				this.slots[0] = null;
			}
		}
		
	}

	private boolean canMacerate()
	{
		if(this.slots[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = OreRecipes.getRecipes().getSmeltingResult(this.slots[0]);
			
			if (itemstack == null) return false;
			if(!this.isOre(this.slots[0])) return false;
			if (this.slots[2] == null) return true;
			if (!this.slots[2].isItemEqual(itemstack)) return false;
			
			int result = this.slots[2].stackSize + itemstack.stackSize*2; //returns double the normal amount
			
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}				
	}	

	public boolean hasPower()
	{
		return (this.power > 0);
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return i == 2 ? false : (i == 1 ? hasItemPower(itemstack) : true);
	}
	
	public static boolean hasItemPower(ItemStack itemstack)
	{
		return getItemPower(itemstack) > 0;
	}

	public static int getItemPower(ItemStack itemstack)
	{
		if (itemstack == null)
		{
			return 0;
		}
		else
		{
			Item item = itemstack.getItem();
			
			
			if (item == Items.redstone) return 50;
			if (item == LordRhysModMain.lightStone) return 75;
			if (item == Item.getItemFromBlock(LordRhysModMain.lightWand)) return 90;
			if (item == LordRhysModMain.liquidLightBucket) return 200;
			if (item == LordRhysModMain.energyIgniter) return 5;
			if (item == LordRhysModMain.energyCell) return 1;
			
			return 0;
		}
		
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return this.isItemValidForSlot(i, itemstack);		
	}
	
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
		
	}

	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}
	
	@SideOnly(Side.CLIENT)
	public int getPowerRemainingScaled(int i)
	{
		return this.power * i / this.maxPower;
	}

	@SideOnly(Side.CLIENT)
	public int getMacerationProgressScaled(int i)
	{
		return this.cookTime * i / this.maceratingSpeed;
	}

}

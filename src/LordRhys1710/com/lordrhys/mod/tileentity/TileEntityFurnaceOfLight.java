package com.lordrhys.mod.tileentity;

import com.lordrhys.mod.block.BlockFurnaceOfLight;
import com.lordrhys.mod.block.BlockGoldenFurnace;
import com.lordrhys.mod.crafting.DualFurnaceRecipes;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFurnaceOfLight extends TileEntity implements ISidedInventory
{
	private String localizedName;
	private ItemStack[] slots = new ItemStack[4];
	private static final int[] slots_top = new int[]{0,1};
	private static final int[] slots_bottom = new int[]{3,2}; // slots listed in priority
	private static final int[] slots_sides = new int[]{2};
	
	public int dualFurnaceSpeed = 125; //Twice as fast as standard furnace(200)
	public int dualBurnTime;
	public int dualCurrentItemBurnTime;
	public int dualCookTime;
	
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
					this.slots[i] =null;
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
	
	public void setGuiDisplayName(String displayName)
	{
		this.localizedName = displayName;		
	}
	
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.localizedName : "Furnace Of Light";
	}
	
	public boolean hasCustomInventoryName()
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, 
				(double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public void openInventory()
	{	}
	
	public void closeInventory()
	{	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			
			if (b >= 0 && b < this.slots.length)
			{
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		this.dualBurnTime = (int)nbt.getShort("BurnTime");
		this.dualCookTime = (int)nbt.getShort("CookTime");
		this.dualCurrentItemBurnTime = (int)nbt.getShort("CurrentBurnTime");
		
		if (nbt.hasKey("CustomName"))
		{
			this.localizedName = nbt.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setShort("BurnTime", (short)this.dualBurnTime);
		nbt.setShort("CookTime", (short)this.dualCookTime);
		nbt.setShort("CurrentBurnTime", (short)this.dualCurrentItemBurnTime);
		
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
	
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return i == 3 ? false : (i == 2 ? isItemFuel(itemstack) : true);
	}
	
	public static boolean isItemFuel(ItemStack itemstack)
	{
		return getItemBurnTime(itemstack) > 0;
	}
	
	private static int getItemBurnTime(ItemStack itemstack)
	{
		if (itemstack == null)
		{
			return 0;
		}
		else
		{
Item item = itemstack.getItem();
			
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
			{
				Block block = Block.getBlockFromItem(item);
				
				if (block == Blocks.wooden_slab) return 150;
				if (block.getMaterial() == Material.wood) return 300;
				if (block == Blocks.coal_block) return 16000; 
			}
			
			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
			if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
			if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD")) return 200;
			
			if (item == Items.stick) return 100;
			if (item == Items.coal) return 1600;
			if (item == Items.lava_bucket) return 20000;
			if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
			if (item == Items.blaze_rod) return 2400;
			// Not a real recipe
			if (item == Items.quartz) return 250;			
			
			return GameRegistry.getFuelValue(itemstack);
		}
	}
	
	public void updateEntity()
	{
		boolean flag = this.dualBurnTime > 0;
		boolean flag1 = false;
		
		if (this.dualBurnTime > 0)
		{
			--this.dualBurnTime;
		}
		
		if (!this.worldObj.isRemote)
		{
			if (this.dualBurnTime == 0 && this.canSmelt())
			{
				this.dualCurrentItemBurnTime = this.dualBurnTime = getItemBurnTime(this.slots[2]);
				
				if (this.dualBurnTime > 0)
				{
					flag1 = true;
					if (this.slots[1] != null)
					{
						--this.slots[2].stackSize;
						if(this.slots[2].stackSize == 0)
						{
							this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
						}
					}
				}
			}
			if (this.isBurning() && this.canSmelt())
			{
				++this.dualCookTime;
				if (this.dualCookTime == this.dualFurnaceSpeed)
				{
					this.dualCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.dualCookTime = 0;
			}
		
			if (flag != this.dualBurnTime > 0)
			{
				flag1 = true;
				BlockFurnaceOfLight.updateFurnaceBlockState(this.dualBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if (flag1)
		{
			this.markDirty();
		}
	}
	
	public boolean isBurning()
	{
		return dualBurnTime > 0;
	}

	private void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack[] stack = {this.slots[0], this.slots[1]};
			
			ItemStack itemstack = DualFurnaceRecipes.recipes().getSmeltingResult(stack); 
			
			if (this.slots[3] == null)
			{
				this.slots[3] = itemstack.copy();
			}
			else if (this.slots[3].isItemEqual(itemstack))
			{
				this.slots[3].stackSize += itemstack.stackSize;
			}				
			
			for (int i = 0; i < 2; i++)
	        {	            
	            this.slots[i].stackSize--;
	            
	            if (this.slots[i].stackSize <= 0)
	            {
	            	this.slots[i] = null;
	            }
	        }
		}
		
	}

	private boolean canSmelt()
	{
		if(this.slots[0] == null || this.slots[1] == null)
		{
			return false;
		}
		else
		{
			ItemStack[] stack = {this.slots[0], this.slots[1]};	
			ItemStack itemstack = DualFurnaceRecipes.recipes().getSmeltingResult(stack);
			// if (itemstack == null)
			// {
				// stack[0] = this.slots[1];
				// stack[1] = this.slots[0];
				// itemstack = DualFurnaceRecipes.recipes().getSmeltingResult(stack);
			// }
			
			if (itemstack == null) return false;
			if (this.slots[3] == null) return true;
			if (!this.slots[3].isItemEqual(itemstack)) return false;
			
			int result = this.slots[3].stackSize + itemstack.stackSize;
			
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}	
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i)
	{
		if (this.dualCurrentItemBurnTime == 0)
		{
			this.dualCurrentItemBurnTime = this.dualFurnaceSpeed;
		}
		
		return this.dualBurnTime * i / this.dualCurrentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i)
	{
		return this.dualCookTime * i / this.dualFurnaceSpeed;
	}

	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return var1 < 2 ? slots_bottom : (var1 == 2 ? slots_top : slots_sides);
	}
	
	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return this.isItemValidForSlot(i, itemstack);
	}
	
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return j != 0 || j != 1 || i != 2 || itemstack.getItem() == Items.bucket;
	}

}

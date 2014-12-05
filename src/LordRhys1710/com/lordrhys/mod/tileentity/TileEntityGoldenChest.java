package com.lordrhys.mod.tileentity;

import java.util.Iterator;
import java.util.List;

import com.lordrhys.mod.block.BlockGoldenChest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGoldenChest extends TileEntity implements IInventory
{
	private ItemStack[] inv;
	private String localizedName;
	public boolean adjacentChestChecked;
	public TileEntityGoldenChest adjacentChestZNeg;
	public TileEntityGoldenChest adjacentChestXPos;
	public TileEntityGoldenChest adjacentChestXNeg;
	public TileEntityGoldenChest adjacentChestZPosition;
	public float lidAngle;
	public float prevLidAngle;
	public int numUsingPlayers;
	private int ticksSinceSync;
    private int cachedChestType;
    
	public TileEntityGoldenChest()
	{
		inv = new ItemStack[27];
		this.cachedChestType = -1;
	}
	
	@SideOnly(Side.CLIENT)
    public TileEntityGoldenChest(int par1)
    {
		inv = new ItemStack[54];
		this.cachedChestType = par1;
    }
	
	public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
        this.adjacentChestChecked = false;
    }
	
	public int getSizeInventory()
	{
		return inv.length;
	}

	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}
	
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		
		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}		
	}
	
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = getStackInSlot(slot);
		
		if (stack != null)
		{
			if (stack.stackSize <= amount)
			{
				setInventorySlotContents(slot, null);
			}
			else
			{
				stack = stack.splitStack(amount);
				
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}
	
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{			
			setInventorySlotContents(slot, null);			
		}
		
		return stack;
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	public String getInventoryName()
	{
		//return "Golden Chest";
		return this.hasCustomInventoryName() ? this.localizedName : "Golden Chest";
	}

	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	public void setGuiDisplayName(String displayName)
	{
		this.localizedName = displayName;		
	}
	
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
		
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound)tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			
			if (slot >= 0 && slot < inv.length)
			{
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
		
		for (int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		
		tagCompound.setTag("Inventory", itemList);
	}

	private void func_90009_a(TileEntityGoldenChest tileEntityGoldenChest, int par2)
    {
        if (tileEntityGoldenChest.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (par2)
            {
                case 0:
                    if (this.adjacentChestZPosition != tileEntityGoldenChest)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 1:
                    if (this.adjacentChestXNeg != tileEntityGoldenChest)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 2:
                    if (this.adjacentChestZNeg != tileEntityGoldenChest)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 3:
                    if (this.adjacentChestXPos != tileEntityGoldenChest)
                    {
                        this.adjacentChestChecked = false;
                    }
            }
        }
    }

    /**
     * Performs the check for adjacent chests to determine if this chest is double or not.
     */
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPosition = null;

            if (this.func_94044_a(this.xCoord - 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXNeg = (TileEntityGoldenChest)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.func_94044_a(this.xCoord + 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXPos = (TileEntityGoldenChest)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if (this.func_94044_a(this.xCoord, this.yCoord, this.zCoord - 1))
            {
                this.adjacentChestZNeg = (TileEntityGoldenChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.func_94044_a(this.xCoord, this.yCoord, this.zCoord + 1))
            {
                this.adjacentChestZPosition = (TileEntityGoldenChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if (this.adjacentChestZNeg != null)
            {
                this.adjacentChestZNeg.func_90009_a(this, 0);
            }

            if (this.adjacentChestZPosition != null)
            {
                this.adjacentChestZPosition.func_90009_a(this, 2);
            }

            if (this.adjacentChestXPos != null)
            {
                this.adjacentChestXPos.func_90009_a(this, 1);
            }

            if (this.adjacentChestXNeg != null)
            {
                this.adjacentChestXNeg.func_90009_a(this, 3);
            }
        }
    }

    private boolean func_94044_a(int par1, int par2, int par3)
    {
        Block block = this.worldObj.getBlock(par1, par2, par3);
        return block != null && block instanceof BlockGoldenChest ? ((BlockGoldenChest)block).chestType == this.getChestType() : false;
    }
	
    public void updateEntity()
    {
        super.updateEntity();
        this.checkForAdjacentChests();
        ++this.ticksSinceSync;
        float f;

        if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
        {
            this.numUsingPlayers = 0;
            f = 5.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - f), (double)((float)this.yCoord - f), (double)((float)this.zCoord - f), (double)((float)(this.xCoord + 1) + f), (double)((float)(this.yCoord + 1) + f), (double)((float)(this.zCoord + 1) + f)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

                if (entityplayer.openContainer instanceof ContainerChest)
                {
                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
                    {
                        ++this.numUsingPlayers;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        f = 0.1F;
        double d0;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            double d1 = (double)this.xCoord + 0.5D;
            d0 = (double)this.zCoord + 0.5D;

            if (this.adjacentChestZPosition != null)
            {
                d0 += 0.5D;
            }

            if (this.adjacentChestXPos != null)
            {
                d1 += 0.5D;
            }

            this.worldObj.playSoundEffect(d1, (double)this.yCoord + 0.5D, d0, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
        {
            float f1 = this.lidAngle;

            if (this.numUsingPlayers > 0)
            {
                this.lidAngle += f;
            }
            else
            {
                this.lidAngle -= f;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
            {
                d0 = (double)this.xCoord + 0.5D;
                double d2 = (double)this.zCoord + 0.5D;

                if (this.adjacentChestZPosition != null)
                {
                    d2 += 0.5D;
                }

                if (this.adjacentChestXPos != null)
                {
                    d0 += 0.5D;
                }

                this.worldObj.playSoundEffect(d0, (double)this.yCoord + 0.5D, d2, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }
    
    public int getChestType()
    {
        if (this.cachedChestType == -1)
        {
            if (this.worldObj == null || !(this.getBlockType() instanceof BlockGoldenChest))
            {
                return 0;
            }

            this.cachedChestType = ((BlockGoldenChest)this.getBlockType()).chestType;
        }

        return this.cachedChestType;
    }
    
    public boolean receiveClientEvent(int par1, int par2)
    {
        if (par1 == 1)
        {
            this.numUsingPlayers = par2;
            return true;
        }
        else
        {
            return super.receiveClientEvent(par1, par2);
        }
    }

    public void openInventory()
    {
        if (this.numUsingPlayers < 0)
        {
            this.numUsingPlayers = 0;
        }

        ++this.numUsingPlayers;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }

    public void closeInventory()
    {
        if (this.getBlockType() != null && this.getBlockType() instanceof BlockGoldenChest)
        {
            --this.numUsingPlayers;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numUsingPlayers);
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
        }
    }
	
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		//return false;
		return true;
	}

	public void invalidate()
    {
        super.invalidate();
        this.updateContainingBlockInfo();
        this.checkForAdjacentChests();
    }

}

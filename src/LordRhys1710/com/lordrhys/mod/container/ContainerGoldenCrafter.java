package com.lordrhys.mod.container;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.crafting.CraftingManagerGoldenTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class ContainerGoldenCrafter extends Container
{

	/** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 5);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;

    public ContainerGoldenCrafter(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
    {
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 124, 53));
        int l;
        int i1;

        for (l = 0; l < 5; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }

        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 120 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, l, 8 + l * 18, 178));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManagerGoldenTable.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer entityPlayer)
    {
        super.onContainerClosed(entityPlayer);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 15; ++i)
            {
                ItemStack itemstack = this.craftMatrix.getStackInSlot(i);    //getStackInSlotOnClosing(i)

                if (itemstack != null)
                {
                    entityPlayer.dropPlayerItemWithRandomChoice(itemstack,false);
                }
            }
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.worldObj.getBlock(this.posX, this.posY, this.posZ) != LordRhysModMain.goldenCraftTable ? false : 
        	entityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 16, 52, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 >= 16 && par2 < 43)
            {
                if (!this.mergeItemStack(itemstack1, 43, 52, false))
                {
                    return null;
                }
            }
            else if (par2 >= 43 && par2 < 52)
            {
                if (!this.mergeItemStack(itemstack1, 16, 43, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 16, 52, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }

    /*public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
    {
        return par2Slot.inventory != this.craftResult && super.func_94530_a(par1ItemStack, par2Slot);
    }*/
	
}

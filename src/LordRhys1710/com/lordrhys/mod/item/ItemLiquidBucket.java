package com.lordrhys.mod.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLiquidBucket extends ItemBucket
{
	/** field for checking if the bucket has been filled. */
    private int isFull;

	public ItemLiquidBucket(Block fluidID)
	{
		super(fluidID);
		this.maxStackSize = 5;
        this.isFull = 0;
        this.setContainerItem(Items.bucket);
        this.setCreativeTab(LordRhysModMain.energyTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}

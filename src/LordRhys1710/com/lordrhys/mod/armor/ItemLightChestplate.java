package com.lordrhys.mod.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLightChestplate extends ItemArmor
{

	public ItemLightChestplate(ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par2EnumArmorMaterial, par3, par4);
		this.setCreativeTab(LordRhysModMain.energyCombatTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(LordRhysModMain.modid + ":lightChestplate");        
    }
	
	public String getArmorTexture(ItemStack stack,Entity entity, int slot, int layer)
	{
		return LordRhysModMain.modid + ":textures/models/armor/energy_layer_1.png";
	}
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack)
  {
    if (itemstack.getItem() == LordRhysModMain.lightChestplate)
    {
      //player.addChatMessage("[ARMOR TICK] You're wearing Chestplate of Light Energy!!! Yippee!");
      player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(),40,0));
    }
  }

}

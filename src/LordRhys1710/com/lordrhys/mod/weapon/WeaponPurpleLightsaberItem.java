package com.lordrhys.mod.weapon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponPurpleLightsaberItem extends ItemSword
{	
	public WeaponPurpleLightsaberItem(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(LordRhysModMain.energyCombatTab);
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		if (stack.getItem() == LordRhysModMain.purpleLightsaber) 
	      {
			EntityLightningBolt bolt = new EntityLightningBolt(world, x + 10, y, z + 4);
			EntityLightningBolt bolt2 = new EntityLightningBolt(world, x + 13, y, z + 6);
			EntityLightningBolt bolt3 = new EntityLightningBolt(world, x + 16, y, z + 8);
			//EntityLightningBolt bolt4 = new EntityLightningBolt(world, x + 6, y, z);
			//EntityLightningBolt bolt5 = new EntityLightningBolt(world, x + 8, y, z);
			//EntityLightningBolt bolt6 = new EntityLightningBolt(world, x + 10, y, z);
			world.spawnEntityInWorld(bolt);
			world.spawnEntityInWorld(bolt2);
			world.spawnEntityInWorld(bolt3);
			//world.spawnEntityInWorld(bolt4);
			//world.spawnEntityInWorld(bolt5);
			//world.spawnEntityInWorld(bolt6);
			}
			return true; 
    }	
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		if (stack.getItem() == LordRhysModMain.purpleLightsaber)
		{
		   player.worldObj.playSoundAtEntity(player, "lordrhys_mod:weapons.lightsaber", 1.0F, 1.0F);
		   //player.addChatMessage("[ARMOR TICK] You're using a LightSaber!!! Yippee!");		     
		}
		return false;
    } 
	
}

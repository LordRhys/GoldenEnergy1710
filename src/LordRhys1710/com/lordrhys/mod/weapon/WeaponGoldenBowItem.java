package com.lordrhys.mod.weapon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponGoldenBowItem extends ItemBow
{
	public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
	
	public WeaponGoldenBowItem() {
		this.maxStackSize = 1;
        this.setMaxDamage(884);
		this.setCreativeTab(LordRhysModMain.energyCombatTab);
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
	
  /** ArrowNockEvent should be placed in 'onItemRightClick' */
  @Override
  public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
  {
    // Create the event and post it
    ArrowNockEvent event = new ArrowNockEvent(player, itemstack);
    MinecraftForge.EVENT_BUS.post(event);

    if (event.isCanceled())
    {
      // you could do other stuff here as well
      return event.result;
    }

    player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
    //}
      return itemstack;
  }

  /** ArrowLooseEvent should be placed in 'onPlayerStoppedUsing' */
  @Override
  public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int par4)
  {
    // Ticks in use is max duration minus par4, which is equal to max duration - 1 for every tick in use
    int ticksInUse = this.getMaxItemUseDuration(itemstack) - par4;

    ArrowLooseEvent event = new ArrowLooseEvent(player, itemstack, ticksInUse);
    MinecraftForge.EVENT_BUS.post(event);

    if (event.isCanceled()) { return; }

    // ticksInUse might be modified by the Event in your EventHandler, so reassign it here:
    ticksInUse = event.charge;

    // Do whatever else you want with the itemstack like fire an arrow or cast a spell    
  }
  
	public int getItemEnchantability()
    {
        return 10;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(LordRhysModMain.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_" + bowPullIconNameArray[i]);
        }
    }
	
  @SideOnly(Side.CLIENT) 
  public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) 
  { 
    if (usingItem == null) 
    { 
      return itemIcon;
    } 
    int ticksInUse = stack.getMaxItemUseDuration() - useRemaining; 
    if (ticksInUse > 17) 
    { 
      return iconArray[2]; 
    } 
    else if (ticksInUse > 13) 
    { 
      return iconArray[1]; 
    } 
    else if (ticksInUse > 0) 
    { 
      return iconArray[0]; 
    } 
    else 
    { 
      return itemIcon; 
    } 
  }


	/*@SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1)
    {
        return this.iconArray[par1];
    }*/

}

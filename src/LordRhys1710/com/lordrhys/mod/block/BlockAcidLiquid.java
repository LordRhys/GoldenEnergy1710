package com.lordrhys.mod.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

import com.lordrhys.mod.LordRhysModMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAcidLiquid extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	protected static IIcon[] theIcon;

	public BlockAcidLiquid()
	{
		super(LordRhysModMain.acidFluid, Material.water);
		this.setCreativeTab(LordRhysModMain.energyTab);		
	}
	
	@SideOnly(Side.CLIENT)
	   public IIcon getIcon(int side, int metadata)
	   {
		   return side != 0 && side != 1 ? this.theIcon[1] : this.theIcon[0];
	   }	
		
	   @SideOnly(Side.CLIENT)
	   public void registerBlockIcons(IIconRegister iconRegister)
	   {
	       this.theIcon = new IIcon[] {iconRegister.registerIcon(LordRhysModMain.modid + ":acid_still"), iconRegister.registerIcon(LordRhysModMain.modid + ":acid_flow")};
	       
	   }

	   @SideOnly(Side.CLIENT)
	   public static IIcon getFluidIcon(String par0Str)
	   {
	       return par0Str == "acid_still" ? theIcon[0] : (par0Str == "acid_flow" ? theIcon[1] :  null);
	   }

}

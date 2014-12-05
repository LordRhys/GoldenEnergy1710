package com.lordrhys.mod.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidAcidFluid extends Fluid
{

	public FluidAcidFluid(String fluidName) 
	{
		super(fluidName);
		this.setDensity(10);
		this.setViscosity(1000);
		FluidRegistry.registerFluid(this);
	}
	
}

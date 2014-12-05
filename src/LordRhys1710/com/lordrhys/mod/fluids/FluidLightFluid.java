package com.lordrhys.mod.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidLightFluid extends Fluid
{
	public FluidLightFluid(String fluidName)
	{
		super(fluidName);
		this.setDensity(10);
		this.setViscosity(1000);
		FluidRegistry.registerFluid(this);		
	}

}

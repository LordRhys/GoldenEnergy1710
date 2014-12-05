package com.lordrhys.mod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.effect.EntityPoisonFX;
import com.lordrhys.mod.tileentity.TileEntityGoldenFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGoldenFurnace extends BlockContainer
{

	private final Random furnaceRand = new Random();

    private final boolean isActive;

    private static boolean keepFurnaceInventory;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconFront;


	public BlockGoldenFurnace(boolean using)
	{
		super(Material.rock);
		this.isActive = using;
		this.setHardness(3.5F);
		if(!this.isActive)
		{
			this.setCreativeTab(LordRhysModMain.energyTab);
		}
	}

	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityGoldenFurnace();
	}
	
	public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(LordRhysModMain.blockGoldenFurnaceIdle);
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            Block l = par1World.getBlock(par2, par3, par4 - 1);
            Block i1 = par1World.getBlock(par2, par3, par4 + 1);
            Block j1 = par1World.getBlock(par2 - 1, par3, par4);
            Block k1 = par1World.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;

            if (l.func_149730_j() && !i1.func_149730_j()) //func_149730_j() --> opaqueCubeLookup()
            {
                b0 = 3;
            }

            if (i1.func_149730_j() && !l.func_149730_j())
            {
                b0 = 2;
            }

            if (j1.func_149730_j() && !k1.func_149730_j())
            {
                b0 = 5;
            }

            if (k1.func_149730_j() && !j1.func_149730_j())
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        //return side == 1 ? this.furnaceIconTop : (side == 0 ? this.furnaceIconTop : (side != metadata ? this.blockIcon : this.furnaceIconFront));
        return side == 3 && metadata == 0 ? this.furnaceIconFront : (side == metadata ? this.furnaceIconFront : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenFurnace_top");
        this.furnaceIconFront = iconRegister.registerIcon(this.isActive ? LordRhysModMain.modid + ":goldenFurnace_front_active" : LordRhysModMain.modid + ":goldenFurnace_front_idle");
        this.furnaceIconTop = iconRegister.registerIcon(LordRhysModMain.modid + ":goldenFurnace_top");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
        	TileEntityGoldenFurnace tileEntityGoldenFurnace = (TileEntityGoldenFurnace)world.getTileEntity(x, y, z);

            if (tileEntityGoldenFurnace != null)
            {
                player.openGui(LordRhysModMain.instance, LordRhysModMain.guiIdGoldenFurnace, world, x, y, z); 
            }

            return true;
        }
        else
        {
        	return false;
        }
    }

    public static void updateFurnaceBlockState(boolean active, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        keepFurnaceInventory = true;

        if (active)
        {
            world.setBlock(x, y, z, LordRhysModMain.blockGoldenFurnaceActive);
        }
        else
        {
            world.setBlock(x, y, z, LordRhysModMain.blockGoldenFurnaceIdle);
        }

        keepFurnaceInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (this.isActive)
        {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPoisonFX(world,(double)(f - f3), (double)f1, (double)(f2 + f4)));
            }
            else if (l == 5)
            {
                world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPoisonFX(world,(double)(f + f3), (double)f1, (double)(f2 + f4)));
            }
            else if (l == 2)
            {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPoisonFX(world,(double)(f + f4), (double)f1, (double)(f2 - f3)));
            }
            else if (l == 3)
            {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPoisonFX(world,(double)(f + f4), (double)f1, (double)(f2 + f3)));
            }
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase base, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(base.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (stack.hasDisplayName())
        {
            ((TileEntityGoldenFurnace)world.getTileEntity(x, y, z)).setGuiDisplayName(stack.getDisplayName());
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
        if (!keepFurnaceInventory)
        {
        	TileEntityGoldenFurnace tileEntityGoldenFurnace = (TileEntityGoldenFurnace)world.getTileEntity(x, y, z);

            if (tileEntityGoldenFurnace != null)
            {
                for (int j1 = 0; j1 < tileEntityGoldenFurnace.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileEntityGoldenFurnace.getStackInSlot(j1);

                    if (itemstack != null)
                    {
                        float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int k1 = this.furnaceRand.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize)
                            {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.func_147453_f(x, y, z, par5);
            }
        }

        super.breakBlock(world, x, y, z, par5, par6);
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return Item.getItemFromBlock(LordRhysModMain.blockGoldenFurnaceIdle);
    }
}

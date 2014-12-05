package com.lordrhys.mod.block;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.lordrhys.mod.LordRhysModMain;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockGoldenChest extends BlockContainer
{
	private final Random random = new Random();

	/** 1 for trapped chests, 0 for normal chests. */
    public final int chestType;	
	
	public BlockGoldenChest(int type) {
		super(Material.wood);
		this.chestType = type;
		this.setStepSound(soundTypeWood);
        this.setCreativeTab(LordRhysModMain.energyTab);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new TileEntityGoldenChest();
	}
	
	 public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public int getRenderType()
    {
        return 22;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(LordRhysModMain.modid + ":planks_golden");

    }
	
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		//this.setDefaultDirection(world, x, y, z);
		this.unifyAdjacentChests(world, x, y, z);
        Block l = world.getBlock(x, y, z - 1);
        Block i1 = world.getBlock(x, y, z + 1);
        Block j1 = world.getBlock(x - 1, y, z);
        Block k1 = world.getBlock(x + 1, y, z);

        if (l == this)
        {
            this.unifyAdjacentChests(world, x, y, z - 1);
        }

        if (i1 == this)
        {
            this.unifyAdjacentChests(world, x, y, z + 1);
        }

        if (j1 == this)
        {
            this.unifyAdjacentChests(world, x - 1, y, z);
        }

        if (k1 == this)
        {
            this.unifyAdjacentChests(world, x + 1, y, z);
        }
	}
	
	public void unifyAdjacentChests(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
        	Block l = world.getBlock(x, y, z - 1);
        	Block i1 = world.getBlock(x, y, z + 1);
        	Block j1 = world.getBlock(x - 1, y, z);
        	Block k1 = world.getBlock(x + 1, y, z);
            boolean flag = true;
            Block l1;
            Block i2;
            boolean flag1;
            byte b0;
            int j2;

            if (l != this && i1 != this)
            {
                if (j1 != this && k1 != this)
                {
                    b0 = 3;

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
                }
                else
                {
                    l1 = world.getBlock(j1 == this ? x - 1 : x + 1, y, z - 1);
                    i2 = world.getBlock(j1 == this ? x - 1 : x + 1, y, z + 1);
                    b0 = 3;
                    flag1 = true;

                    if (j1 == this)
                    {
                        j2 = world.getBlockMetadata(x - 1, y, z);
                    }
                    else
                    {
                        j2 = world.getBlockMetadata(x + 1, y, z);
                    }

                    if (j2 == 2)
                    {
                        b0 = 2;
                    }

                    if ((l.func_149730_j() || l1.func_149730_j()) && !i1.func_149730_j() && !i2.func_149730_j())
                    {
                        b0 = 3;
                    }

                    if ((i1.func_149730_j() || i2.func_149730_j()) && !l.func_149730_j() && !l1.func_149730_j())
                    {
                        b0 = 2;
                    }
                }
            }
            else
            {
                l1 = world.getBlock(x - 1, y, l == this ? z - 1 : z + 1);
                i2 = world.getBlock(x + 1, y, l == this ? z - 1 : z + 1);
                b0 = 5;
                flag1 = true;

                if (l == this)
                {
                    j2 = world.getBlockMetadata(x, y, z - 1);
                }
                else
                {
                    j2 = world.getBlockMetadata(x, y, z + 1);
                }

                if (j2 == 4)
                {
                    b0 = 4;
                }

                if ((j1.func_149730_j() || l1.func_149730_j()) && !k1.func_149730_j() && !i2.func_149730_j())
                {
                    b0 = 5;
                }

                if ((k1.func_149730_j() || i2.func_149730_j()) && !j1.func_149730_j() && !l1.func_149730_j())
                {
                    b0 = 4;
                }
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 3);
        }
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        if (iBlockAccess.getBlock(x, y, z - 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (iBlockAccess.getBlock(x, y, z + 1) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (iBlockAccess.getBlock(x - 1, y, z) == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (iBlockAccess.getBlock(x + 1, y, z) == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        int l = 0;

        if (world.getBlock(x - 1, y, z) == this)
        {
            ++l;
        }

        if (world.getBlock(x + 1, y, z) == this)
        {
            ++l;
        }

        if (world.getBlock(x, y, z - 1) == this)
        {
            ++l;
        }

        if (world.getBlock(x, y, z + 1) == this)
        {
            ++l;
        }

        return l > 1 ? false : (this.isThereANeighborChest(world, x - 1, y, z) ? false : (this.isThereANeighborChest(world, x + 1, y, z) ? false : 
        	(this.isThereANeighborChest(world, x, y, z - 1) ? false : !this.isThereANeighborChest(world, x, y, z + 1))));
    }
	
	private boolean isThereANeighborChest(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z) != this ? false : (world.getBlock(x - 1, y, z) == this ? true : (world.getBlock(x + 1, y, z) == this ? true : 
        	(world.getBlock(x, y, z - 1) == this ? true : world.getBlock(x, y, z + 1) == this)));
    }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block par5)
    {
        super.onNeighborBlockChange(world, x, y, z, par5);
        TileEntityGoldenChest tileentityGoldenchest = (TileEntityGoldenChest)world.getTileEntity(x, y, z);

        if (tileentityGoldenchest != null)
        {
        	tileentityGoldenchest.updateContainingBlockInfo();
        }
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase base, ItemStack stack)
	{
		Block l = world.getBlock(x, y, z - 1);
		Block i1 = world.getBlock(x, y, z + 1);
		Block j1 = world.getBlock(x - 1, y, z);
		Block k1 = world.getBlock(x + 1, y, z);
        byte b0 = 0;
		
        int l1 = MathHelper.floor_double((double)(base.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
        if (l1 == 0)
        {
            b0 = 2;
        }

        if (l1 == 1)
        {
            b0 = 5;
        }

        if (l1 == 2)
        {
            b0 = 3;
        }

        if (l1 == 3)
        {
            b0 = 4;
        }
        
        if (l != this && i1 != this && j1 != this && k1 != this)
        {
            world.setBlockMetadataWithNotify(x, y, z, b0, 3);
        }
        else
        {
            if ((l == this || i1 == this) && (b0 == 4 || b0 == 5))
            {
                if (l == this)
                {
                	world.setBlockMetadataWithNotify(x, y, z - 1, b0, 3);
                }
                else
                {
                	world.setBlockMetadataWithNotify(x, y, z + 1, b0, 3);
                }

                world.setBlockMetadataWithNotify(x, y, z, b0, 3);
            }

            if ((j1 == this || k1 == this) && (b0 == 2 || b0 == 3))
            {
                if (j1 == this)
                {
                	world.setBlockMetadataWithNotify(x - 1, y, z, b0, 3);
                }
                else
                {
                	world.setBlockMetadataWithNotify(x + 1, y, z, b0, 3);
                }

                world.setBlockMetadataWithNotify(x, y, z, b0, 3);
            }
        }
		
		if (stack.hasDisplayName())
		{
			((TileEntityGoldenChest)world.getTileEntity(x, y, z)).setGuiDisplayName(stack.getDisplayName());
		}
	}
	
	//Drop Items Contained in the Chest
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, x, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random();
		TileEntity te = world.getTileEntity(x, y, z);
		
		if (!(te instanceof IInventory))
		{
			return;
		}
		
		IInventory inventory = (IInventory)te;
		
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);
			
			if (item !=null && item.stackSize > 0)
			{
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;
				
				EntityItem entityItem = new EntityItem(world, x +rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, 
						item.getItemDamage()));
				
				if (item.hasTagCompound())
				{
					entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
				}
				
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;				
			}
		}		
	}
	
	//@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are)
	{		
		if (world.isRemote)
        {
            return true;
        }
        else
        {
            IInventory iinventory = this.getInventory(world, x, y, z);

            if (iinventory != null)
            {
                player.displayGUIChest(iinventory);
            }

            return true;
        }
	}
	
	public IInventory getInventory(World world, int x, int y, int z)
    {
        Object object = (TileEntityGoldenChest)world.getTileEntity(x, y, z);

        if (object == null)
        {
            return null;
        }
        else if (world.isSideSolid(x, y + 1, z, DOWN))
        {
            return null;
        }
        else if (isOcelotBlockingChest(world, x, y, z))
        {
            return null;
        }
        else if (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, DOWN) || isOcelotBlockingChest(world, x - 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, DOWN) || isOcelotBlockingChest(world, x + 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, DOWN) || isOcelotBlockingChest(world, x, y, z - 1)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, DOWN) || isOcelotBlockingChest(world, x, y, z + 1)))
        {
            return null;
        }
        else
        {
            if (world.getBlock(x - 1, y, z) == this)
            {
                object = new InventoryLargeChest("Golden Double Chest", (TileEntityGoldenChest)world.getTileEntity(x - 1, y, z), (IInventory)object);
            }

            if (world.getBlock(x + 1, y, z) == this)
            {
                object = new InventoryLargeChest("Golden Double Chest", (IInventory)object, (TileEntityGoldenChest)world.getTileEntity(x + 1, y, z));
            }

            if (world.getBlock(x, y, z - 1) == this)
            {
                object = new InventoryLargeChest("Golden Double Chest", (TileEntityGoldenChest)world.getTileEntity(x, y, z - 1), (IInventory)object);
            }

            if (world.getBlock(x, y, z + 1) == this)
            {
                object = new InventoryLargeChest("Golden Double Chest", (IInventory)object, (TileEntityGoldenChest)world.getTileEntity(x, y, z + 1));
            }

            return (IInventory)object;
        }
    }
	
	public static boolean isOcelotBlockingChest(World world, int x, int y, int z)
    {
        Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)x, (double)(y + 1),
        		(double)z, (double)(x + 1), (double)(y + 2), (double)(z + 1))).iterator();
        EntityOcelot entityocelot;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            EntityOcelot entityocelot1 = (EntityOcelot)iterator.next();
            entityocelot = (EntityOcelot)entityocelot1;
        }
        while (!entityocelot.isSitting());

        return true;
    }
	
	public boolean canProvidePower()
    {
        return this.chestType == 1;
    }
	
	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int par5)
    {
        if (!this.canProvidePower())
        {
            return 0;
        }
        else
        {
            int i1 = ((TileEntityGoldenChest)iBlockAccess.getTileEntity(x, y, z)).numUsingPlayers;
            return MathHelper.clamp_int(i1, 0, 15);
        }
    }
	
	public int isProvidingStrongPower(IBlockAccess iBlockAccess, int x, int y, int z, int metadata)
    {
        return metadata == 1 ? this.isProvidingWeakPower(iBlockAccess, x, y, z, metadata) : 0;
    }
	
	public boolean hasComparatorInputOverride()
    {
        return true;
    }

	public int getComparatorInputOverride(World world, int x, int y, int z, int par5)
    {
        return Container.calcRedstoneFromInventory(this.getInventory(world, x, y, z));
    }
}

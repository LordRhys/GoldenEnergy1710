package com.lordrhys.mod.teleporters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import com.lordrhys.mod.LordRhysModMain;

public class TeleporterVoid extends Teleporter 
{
	private final WorldServer worldServerInstance;
	private final Random random;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();
	
	public TeleporterVoid(WorldServer worldServer) 
	{
		super(worldServer);
		this.worldServerInstance = worldServer;
		this.random = new Random(worldServer.getSeed());
	}
	
	public void placeInPortal(Entity entity, double x, double y, double z, float f)
    {
        if (this.worldServerInstance.provider.dimensionId != 1)
        {
            if (!this.placeInExistingPortal(entity, x, y, z, f))
            {
                this.makePortal(entity);
                this.placeInExistingPortal(entity, x, y, z, f);
            }
        }
        else
        {
            int entity_x = MathHelper.floor_double(entity.posX);
            int entity_y = MathHelper.floor_double(entity.posY) - 1;
            int entity_z = MathHelper.floor_double(entity.posZ);
            byte b0 = 1;
            byte b1 = 0;

            for (int i = -2; i <= 2; i++)
            {
                for (int j = -2; j <= 2; j++)
                {
                    for (int k = -1; k < 3; k++)
                    {
                        int x2 = entity_x + j * b0 + i * b1;
                        int y2 = entity_y + k;
                        int z2 = entity_z + j * b1 - i * b0;
                        boolean flag = k < 0;
                        this.worldServerInstance.setBlock(x2, y2, z2, flag ? LordRhysModMain.oreRandomite : Blocks.air);
                    }
                }
            }

            entity.setLocationAndAngles((double)entity_x, (double)entity_y, (double)entity_z, entity.rotationYaw, 0.0F);
            entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        }
    }
	
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float f)
	{
		short short1 = 128;
        double d3 = -1.0D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.floor_double(entity.posX);
        int i1 = MathHelper.floor_double(entity.posZ);
        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
        boolean flag = true;
        double d4;
        int k1;

        if (this.destinationCoordinateCache.containsItem(j1))
        {
            PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
            d3 = 0.0D;
            i = portalposition.posX;
            j = portalposition.posY;
            k = portalposition.posZ;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            flag = false;
        }
        else
        {
            for (k1 = l - short1; k1 <= l + short1; k1++)
            {
                double d5 = (double)k1 + 0.5D - entity.posX;

                for (int l1 = i1 - short1; l1 <= i1 + short1; l1++)
                {
                    double d6 = (double)l1 + 0.5D - entity.posZ;

                    for (int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; i2--)
                    {
                        if (this.worldServerInstance.getBlock(k1, i2, l1) == LordRhysModMain.blockTeleporter)
                        {
                            while (this.worldServerInstance.getBlock(k1, i2 - 1, l1) == LordRhysModMain.blockTeleporter)
                            {
                                i2--;
                            }

                            d4 = (double)i2 + 0.5D - entity.posY;
                            double d7 = d5 * d5 + d4 * d4 + d6 * d6;

                            if (d3 < 0.0D || d7 < d3)
                            {
                                d3 = d7;
                                i = k1;
                                j = i2;
                                k = l1;
                            }
                        }
                    }
                }
            }
        }

        if (d3 >= 0.0D)
        {
            if (flag)
            {
                this.destinationCoordinateCache.add(j1, new PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(j1));
            }

            double d8 = (double)i + 0.5D;
            double d9 = (double)j + 0.5D;
            d4 = (double)k + 0.5D;
            int j2 = -1;	//Direction

            if (this.worldServerInstance.getBlock(i - 1, j, k) == LordRhysModMain.blockTeleporter)
            {
                j2 = 2;
            }

            if (this.worldServerInstance.getBlock(i + 1, j, k) == LordRhysModMain.blockTeleporter)
            {
                j2 = 0;
            }

            if (this.worldServerInstance.getBlock(i, j, k - 1) == LordRhysModMain.blockTeleporter)
            {
                j2 = 3;
            }

            if (this.worldServerInstance.getBlock(i, j, k + 1) == LordRhysModMain.blockTeleporter)
            {
                j2 = 1;
            }

            int k2 = entity.getTeleportDirection();

            if (j2 > -1)
            {
                int l2 = Direction.rotateLeft[j2];
                int i3 = Direction.offsetX[j2];
                int j3 = Direction.offsetZ[j2];
                int k3 = Direction.offsetX[l2];
                int l3 = Direction.offsetZ[l2];
                boolean flag1 = !this.worldServerInstance.isAirBlock(i + i3 + k3, j, k + j3 + l3) || !this.worldServerInstance.isAirBlock(i + i3 + k3, j + 1, k + j3 + l3);
                boolean flag2 = !this.worldServerInstance.isAirBlock(i + i3, j, k + j3) || !this.worldServerInstance.isAirBlock(i + i3, j + 1, k + j3);

                if (flag1 && flag2)
                {
                    j2 = Direction.rotateOpposite[j2];
                    l2 = Direction.rotateOpposite[l2];
                    i3 = Direction.offsetX[j2];
                    j3 = Direction.offsetZ[j2];
                    k3 = Direction.offsetX[l2];
                    l3 = Direction.offsetZ[l2];
                    k1 = i - k3;
                    d8 -= (double)k3;
                    int i4 = k - l3;
                    d4 -= (double)l3;
                    flag1 = !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j, i4 + j3 + l3) || !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j + 1, i4 + j3 + l3);
                    flag2 = !this.worldServerInstance.isAirBlock(k1 + i3, j, i4 + j3) || !this.worldServerInstance.isAirBlock(k1 + i3, j + 1, i4 + j3);
                }

                float f1 = 0.5F;
                float f2 = 0.5F;

                if (!flag1 && flag2)
                {
                    f1 = 1.0F;
                }
                else if (flag1 && !flag2)
                {
                    f1 = 0.0F;
                }
                else if (flag1 && flag2)
                {
                    f2 = 0.0F;
                }

                d8 += (double)((float)k3 * f1 + f2 * (float)i3);
                d4 += (double)((float)l3 * f1 + f2 * (float)j3);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;

                if (j2 == k2)
                {
                    f3 = 1.0F;
                    f4 = 1.0F;
                }
                else if (j2 == Direction.rotateOpposite[k2])
                {
                    f3 = -1.0F;
                    f4 = -1.0F;
                }
                else if (j2 == Direction.rotateRight[k2])
                {
                    f5 = 1.0F;
                    f6 = -1.0F;
                }
                else
                {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                double d10 = entity.motionX;
                double d11 = entity.motionZ;
                entity.motionX = d10 * (double)f3 + d11 * (double)f6;
                entity.motionZ = d10 * (double)f5 + d11 * (double)f4;
                entity.rotationYaw = f - (float)(k2 * 90) + (float)(j2 * 90);
            }
            else
            {
                entity.motionX = entity.motionY = entity.motionZ = 0.0D;
            }

            entity.setLocationAndAngles(d8, d9, d4, entity.rotationYaw, entity.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
	}
		
	public boolean makePortal(Entity entity)
    {
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);
        int l = i;
        int m = j;
        int n = k;
        int o = 0;
        int p = this.random.nextInt(4);
        int q; //X
        double d1; //X
        double d2; //Z
        int i2; //Z
        int j2; //Y
        int k2;
        int l2;
        int m2;
        int n2;
        int o2;
        int p2;
        int q2; //Y
        int r2; //X
        double d3;
        double d4; //Y

        for (q = i - b0; q <= i + b0; ++q)
        {
            d1 = (double)q + 0.5D - entity.posX;

            for (i2 = k - b0; i2 <= k + b0; ++i2)
            {
                d2 = (double)i2 + 0.5D - entity.posZ;
                label274:

                for (j2 = this.worldServerInstance.getActualHeight() - 1; j2 >= 0; j2--)
                {
                    if (this.worldServerInstance.isAirBlock(q, j2, i2))
                    {
                        while (j2 > 0 && this.worldServerInstance.isAirBlock(q, j2 - 1, i2))
                        {
                            --j2;
                        }

                        for (l2 = p; l2 < p + 4; ++l2)
                        {
                            k2 = l2 % 2;
                            n2 = 1 - k2;

                            if (l2 % 4 >= 2)
                            {
                                k2 = -k2;
                                n2 = -n2;
                            }

                            for (m2 = 0; m2 < 3; ++m2)
                            {
                                for (p2 = 0; p2 < 4; ++p2)
                                {
                                    for (o2 = -1; o2 < 4; ++o2)
                                    {
                                        r2 = q + (p2 - 1) * k2 + m2 * n2;
                                        q2 = j2 + o2;
                                        int l4 = i2 + (p2 - 1) * n2 - m2 * k2;

                                        if (o2 < 0 && !this.worldServerInstance.getBlock(r2, q2, l4).getMaterial().isSolid() || 
                                        		o2 >= 0 && !this.worldServerInstance.isAirBlock(r2, q2, l4))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d4 = (double)j2 + 0.5D - entity.posY;
                            d3 = d1 * d1 + d4 * d4 + d2 * d2;

                            if (d0 < 0.0D || d3 < d0)
                            {
                                d0 = d3;
                                l = q;
                                m = j2;
                                n = i2;
                                o = l2 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (q = i - b0; q <= i + b0; q++)
            {
                d1 = (double)q + 0.5D - entity.posX;

                for (i2 = k - b0; i2 <= k + b0; i2++)
                {
                    d2 = (double)i2 + 0.5D - entity.posZ;
                    label222:

                    for (j2 = this.worldServerInstance.getActualHeight() - 1; j2 >= 0; j2--)
                    {
                        if (this.worldServerInstance.isAirBlock(q, j2, i2)) //q, j2, i2 from base code
                        {
                            while (j2 > 0 && this.worldServerInstance.isAirBlock(q, j2 - 1, i2))
                            {
                                j2--;
                            }

                            for (l2 = p; l2 < p + 2; l2++) 
                            {
                                k2 = l2 % 2;
                                n2 = 1 - k2;

                                for (m2 = 0; m2 < 4; m2++)
                                {
                                    for (p2 = -1; p2 < 4; p2++)
                                    {
                                        o2 = q + (m2 - 1) * k2;
                                        r2 = j2 + p2;
                                        q2 = i2 + (m2 - 1) * n2;

                                        if (p2 < 0 && !this.worldServerInstance.getBlock(o2, r2, q2).getMaterial().isSolid() || 
                                        		p2 >= 0 && !this.worldServerInstance.isAirBlock(o2, r2, q2))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                d4 = (double)j2 + 0.5D - entity.posY;
                                d3 = d1 * d1 + d4 * d4 + d2 * d2;

                                if (d0 < 0.0D || d3 < d0)
                                {
                                    d0 = d3;
                                    l = q;
                                    m = j2;
                                    n = i2;
                                    o = l2 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i5 = l;
        int j5 = m;
        i2 = n;
        int k5 = o % 2;
        int l5 = 1 - k5;

        if (o % 4 >= 2)
        {
            k5 = -k5;
            l5 = -l5;
        }

        boolean flag;

        if (d0 < 0.0D)
        {
            if (m < 70)
            {
                m = 70;
            }

            if (m > this.worldServerInstance.getActualHeight() - 10)
            {
                m = this.worldServerInstance.getActualHeight() - 10;
            }

            j5 = m;

            for (j2 = -1; j2 <= 1; j2++)
            {
                for (l2 = 1; l2 < 3; l2++) // should l2 be o?
                {
                    for (k2 = -1; k2 < 3; k2++)
                    {
                        n2 = i5 + (l2 - 1) * k5 + j2 * l5; // he used j5 to start
                        m2 = j5 + k2;
                        p2 = i2 + (l2 - 1) * l5 - j2 * k5;
                        flag = k2 < 0;
                        this.worldServerInstance.setBlock(n2, m2, p2, flag ? LordRhysModMain.oreRandomite : Blocks.air);
                    }
                }
            }
        }

        for (j2 = 0; j2 < 4; j2++)
        {
            for (l2 = 0; l2 < 4; l2++)
            {
                for (k2 = -1; k2 < 4; k2++)
                {
                    n2 = i5 + (l2 - 1) * k5; // he used j5 to start
                    m2 = j5 + k2;
                    p2 = i2 + (l2 - 1) * l5;
                    flag = l2 == 0 || l2 == 3 || k2 == -1 || k2 == 3;
                    this.worldServerInstance.setBlock(n2, m2, p2, flag ? LordRhysModMain.oreRandomite : LordRhysModMain.blockTeleporter, 0, 2);
                }
            }

            for (l2 = 0; l2 < 4; l2++)
            {
                for (k2 = -1; k2 < 4; k2++)
                {
                    n2 = i5 + (l2 - 1) * k5;
                    m2 = j5 + k2;
                    p2 = i2 + (l2 - 1) * l5;
                    this.worldServerInstance.notifyBlocksOfNeighborChange(n2, m2, p2, this.worldServerInstance.getBlock(n2, m2, p2));
                }
            }
        }

        return true;
    }

	
	public void removeStalePortalLocations(long par1)
	{
		
	}

}

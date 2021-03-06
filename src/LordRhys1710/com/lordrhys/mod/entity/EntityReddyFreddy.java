package com.lordrhys.mod.entity;

import com.lordrhys.mod.LordRhysModMain;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityReddyFreddy extends EntityMob
{

	public EntityReddyFreddy(World par1World) {
		super(par1World);
		this.experienceValue = 10;
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.5D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        
	}
	
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
	}
	
	protected String getHurtSound()
	{
		//return "mob.zombie.hurt";
		return "lordrhys:reddyfreddy.evil_laugh_male";
	}
	
	protected String getDeathSound()
	{
		return "mob.zombie.death";
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.zombie.step", 0.15F, 1.0F);
	}
	
	protected Item getDropItem()
	{
		return Items.redstone;
	}
	
	protected void dropRareDrop(int par1)
	{
		switch (this.rand.nextInt(3))
		{
			case 0:
				this.dropItem(LordRhysModMain.lightStone, 1);
				break;
			case 1:
				this.dropItem(LordRhysModMain.energyCell, 1);
				break;
			case 2:
				this.dropItem(LordRhysModMain.randomite, 1);
				break;
		}
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

}

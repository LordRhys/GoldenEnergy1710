package com.lordrhys.mod.lib;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import com.lordrhys.mod.LordRhysModMain;

public class LordRhysAchievementPage 
{
	public static AchievementPage lordRhysAchievementsPage;
	public static Achievement bronzeAchievement;
	public static Achievement miningAchievement;
	public static Achievement goldenFurnaces;
	public static Achievement dualFurnaces;
	
	public static void loadAchievements()
	{
		goldenFurnaces = new Achievement("achievement.goldenFurnaces", "goldenFurnaces", 0, 0, LordRhysModMain.blockGoldenFurnaceIdle, (Achievement)null).initIndependentStat().registerStat();
		dualFurnaces = new Achievement("achievement.dualFurnaces", "dualFurnaces", 2,2, LordRhysModMain.blockFurnaceOfLightIdle, goldenFurnaces).setSpecial().registerStat();
		bronzeAchievement = new Achievement("achievement.bronzeAchievement", "bronzeAchievement", 0, 2, LordRhysModMain.ingotBronze, goldenFurnaces).registerStat();
		miningAchievement = new Achievement("achievement.miningAchievement", "miningAchievement", -2, -2, LordRhysModMain.oreCopper, (Achievement)null).initIndependentStat().registerStat();
		//achDesc();
		lordRhysAchievementsPage = new AchievementPage("Golden Energy Achievements", new Achievement[]{goldenFurnaces, dualFurnaces,bronzeAchievement, miningAchievement});
		AchievementPage.registerAchievementPage(lordRhysAchievementsPage);
	}	
}

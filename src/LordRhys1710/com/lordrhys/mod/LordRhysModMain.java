package com.lordrhys.mod;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.lordrhys.mod.armor.ItemLightBoots;
import com.lordrhys.mod.armor.ItemLightChestplate;
import com.lordrhys.mod.armor.ItemLightHelmet;
import com.lordrhys.mod.armor.ItemLightLeggings;
import com.lordrhys.mod.biome.BiomeGoldenBiome;
import com.lordrhys.mod.block.BlockAcidLiquid;
import com.lordrhys.mod.block.BlockBlueFlower;
import com.lordrhys.mod.block.BlockFurnaceOfLight;
import com.lordrhys.mod.block.BlockGoldenChest;
import com.lordrhys.mod.block.BlockGoldenDirt;
import com.lordrhys.mod.block.BlockGoldenFarmland;
import com.lordrhys.mod.block.BlockGoldenFurnace;
import com.lordrhys.mod.block.BlockGoldenGrass;
import com.lordrhys.mod.block.BlockGoldenLeaf;
import com.lordrhys.mod.block.BlockGoldenLog;
import com.lordrhys.mod.block.BlockGoldenMacerator;
import com.lordrhys.mod.block.BlockGoldenSapling;
import com.lordrhys.mod.block.BlockGoldenTable;
import com.lordrhys.mod.block.BlockGoldenWoodPlank;
import com.lordrhys.mod.block.BlockLightDirt;
import com.lordrhys.mod.block.BlockLightLiquid;
import com.lordrhys.mod.block.BlockLightWand;
import com.lordrhys.mod.block.BlockSlabLightStone;
import com.lordrhys.mod.block.BlockSlabVoidPowder;
import com.lordrhys.mod.block.BlockTeleporter;
import com.lordrhys.mod.block.BlockTeleporterFire;
import com.lordrhys.mod.block.BlockTomato;
import com.lordrhys.mod.block.BlockTrashCan;
import com.lordrhys.mod.block.BlockWindmill;
import com.lordrhys.mod.block.BlockWindmillGround;
import com.lordrhys.mod.block.OreBlocks;
import com.lordrhys.mod.block.PurpleFlowerBlock;
import com.lordrhys.mod.block.StairBlocks;
import com.lordrhys.mod.crafting.AchievementHandler;
import com.lordrhys.mod.crafting.LordRhysModCrafting;
import com.lordrhys.mod.crafting.OreRecipes;
import com.lordrhys.mod.entity.EntityReddyFreddy;
import com.lordrhys.mod.food.FoodBaconAndEggsItem;
import com.lordrhys.mod.food.FoodBeefStewItem;
import com.lordrhys.mod.food.FoodCheeseBurgerItem;
import com.lordrhys.mod.food.FoodCheeseItem;
import com.lordrhys.mod.food.FoodHamburgerItem;
import com.lordrhys.mod.food.FoodSloppyJoeItem;
import com.lordrhys.mod.gui.CustomGUIHandler;
import com.lordrhys.mod.item.IngotItem;
import com.lordrhys.mod.item.ItemBasic;
import com.lordrhys.mod.item.ItemBattery;
import com.lordrhys.mod.item.ItemEnergyIgniter;
import com.lordrhys.mod.item.ItemLiquidBucket;
import com.lordrhys.mod.item.ItemWindmill;
import com.lordrhys.mod.lib.CustomBucketEvent;
import com.lordrhys.mod.lib.EventManager;
import com.lordrhys.mod.lib.Golden_Bonemeal_Event;
import com.lordrhys.mod.lib.LordRhysAchievementPage;
import com.lordrhys.mod.lib.OreRegistry;
import com.lordrhys.mod.lib.WindmillHighlightEvent;
import com.lordrhys.mod.sound.registerLightSounds;
import com.lordrhys.mod.spawn.customSpawner;
import com.lordrhys.mod.tabs.CreativeTabEnergy;
import com.lordrhys.mod.tabs.CreativeTabEnergyCombat;
import com.lordrhys.mod.tabs.CreativeTabEnergyFoods;
import com.lordrhys.mod.tabs.CreativeTabEnergyMaterials;
import com.lordrhys.mod.tabs.CreativeTabEnergyTools;
import com.lordrhys.mod.tileentity.TileEntityFurnaceOfLight;
import com.lordrhys.mod.tileentity.TileEntityGoldenChest;
import com.lordrhys.mod.tileentity.TileEntityGoldenFurnace;
import com.lordrhys.mod.tileentity.TileEntityGoldenMacerator;
import com.lordrhys.mod.tileentity.TileEntityTrashcan;
import com.lordrhys.mod.tileentity.TileEntityWindmill;
import com.lordrhys.mod.tileentity.TileEntityWindmillFloor;
import com.lordrhys.mod.tool.ToolEnergyAxe;
import com.lordrhys.mod.tool.ToolEnergyHoe;
import com.lordrhys.mod.tool.ToolEnergyPickaxe;
import com.lordrhys.mod.tool.ToolEnergyShovel;
import com.lordrhys.mod.weapon.WeaponGoldenBowItem;
import com.lordrhys.mod.weapon.WeaponPurpleLightsaberItem;
import com.lordrhys.mod.world.WorldProviderVoid;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid = LordRhysModMain.modid, name = "LordRhys Mod", version = "1.0.1")
public class LordRhysModMain
{
	public static final String modid = "lordrhys";	
	
	@Instance("lordrhys")
	public static LordRhysModMain instance;
	
	@SidedProxy(clientSide = "com.lordrhys.mod.ClientProxy", serverSide = "com.lordrhys.mod.CommonProxy")
	public static CommonProxy proxy;
	
	//Define Fluids
	public static Fluid lightFluid;
	public static Fluid acidFluid;
	public static Material materialLight;
	
	//Define Blocks
	public static Block lightDirt;
	public static Block lightStoneHalfSlab;
	public static Block lightStoneDoubleSlab;
	public static Block voidPowderHalfSlab;
	public static Block voidPowderDoubleSlab;
	public static Block lightStoneStair;
	public static Block lightWand;
	public static Block lightLiquid;
	public static Block acidLiquid;
	public static Block goldenDirt;
	public static Block tilledGoldenField;
	public static Block goldenGrass;
	public static Block energyTrashcan;
	public static Block blockTeleporter;
	public static Block teleporterFire;
	public static Block blockWindmill;
	public static Block blockWindmillGround;
	
	public static Block blueFlower;
	public static Block purpleFlower;
	
	
	//Define Ores
	public static Block oreTin;
	public static Block oreNickel;
	public static Block oreVoid;
	public static Block oreCopper;
	public static Block oreRandomite;
	public static Block oreSulphur;
	public static Block oreRhodium;
	
	//Define Items
	public static Item lightStoneDust;
	public static Item lightStone;
	public static Item liquidLightBucket;
	public static Item liquidAcidBucket;
	public static Item ingotSteel;
	public static Item ingotTin;
	public static Item ingotNickel;
	public static Item ingotBronze;
	public static Item ingotCopper;
	public static Item ingotVoid;
	public static Item voidPowder;
	public static Item randomitePowder;
	public static Item cobblestoneNugget;
	public static Item energyIgniter;
	public static Item crushedIron;
	public static Item energyCell;
	public static Item randomite;
	public static Item sulphurNugget;
	public static Item rhodium;
	public static Item itemWindmill;	
	
	//Define Tools
	public static Item energyAxe;
	public static Item energyHoe;
	public static Item energyShovel;
	public static Item energyPickaxe;
	
	//Define Food Items
	public static Item cheese;
	public static Item cheeseBurger;
	public static Item hamburger;
	public static Item baconAndEggs;
	public static Item sloppyJoe;
	public static Item beefStew;
	public static Item tomato;
	
	//Define Plants, Trees, and Crops
	public static Block goldenLog;
	public static Block goldenLeaf;
	public static Block goldenSapling;
	public static Block goldenWoodPlanks;
	public static Block blockTomato;
	
	//Define Weapons
	public static Item purpleLightsaber;
	public static Item goldenBow;
  
  //Define Armor
	public static Item lightLeggings;
	public static Item lightHelmet;
	public static Item lightBoots;
	public static Item lightChestplate;
	
	// Chests, Furnaces, and Crafting Tables
	public static Block goldenChest;
	public static Block goldenChestTrapped;
	public static Block blockGoldenFurnaceIdle;
	public static Block blockGoldenFurnaceActive;
	public static Block blockFurnaceOfLightIdle;
	public static Block blockFurnaceOfLightActive;
	public static Block goldenCraftTable;
	public static Block blockGoldenMaceratorIdle;
	public static Block blockGoldenMaceratorActive;
	
	//GUI ID's
	public static final int guiIdGoldenFurnace = 0;
	public static final int guiIdGoldenChest = 1;
	public static final int guiIdFurnaceOfLight = 2;
	public static final int guiIdGoldenCraftTable = 3;
	public static final int guiIdGoldenMacerator = 4;
	public static final int guiIdWindmillGenerator = 5;
	
		
	//Dimension ID's
	public static final int dimensionIdVoid = 2;
	
	//Define Custom Mob Spawner
	public static Item customSpawner;
	
	//Define Mobs ID's
	private static int startEntityId = 300;
		
	//Mob Generation	
	public static int getUniqueEntityId()
	{
		do
		{
			startEntityId++;
		}
		while (EntityList.getStringFromID(startEntityId) != null);
		return startEntityId++;
	}
	
	//Init Spawn Eggs
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor)
	{
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}	
		
	//Define Biomes
	public static BiomeGenBase goldenBiome;
	
	public static CreativeTabs energyTab;
	public static CreativeTabs energyCombatTab;
	public static CreativeTabs energyToolsTab;
	public static CreativeTabs energyFoodsTab;
	public static CreativeTabs energyMaterialsTab;
	
	EventManager eventmanager = new EventManager();
	AchievementHandler craftingHandler = new AchievementHandler();
	
	public static ToolMaterial LightEnergy = EnumHelper.addToolMaterial("LightEnergy", 5, 4096, 16.0F, 8.0F, 30);
	public static ArmorMaterial EnergyArmor = EnumHelper.addArmorMaterial("LightEnergy", 136, new int[]{5,9,8,5},30);
	
	@EventHandler 
	public void PreInit(FMLPreInitializationEvent event) 
	{	 
		
	  //Settings for Custom Tabs
	  		energyTab = new CreativeTabEnergy("energyTab");
	  		energyCombatTab = new CreativeTabEnergyCombat("energyCombatTab");
	  		energyToolsTab = new CreativeTabEnergyTools("energyToolsTab");
	  		energyFoodsTab = new CreativeTabEnergyFoods("energyFoodsTab");
	  		energyMaterialsTab = new CreativeTabEnergyMaterials("energyMaterialsTab");
	  		
	  		//Settings for Fluids
	  		lightFluid = new Fluid("lightFluid").setBlock(lightLiquid).setDensity(7).setViscosity(750);
	  		acidFluid = new Fluid("acidFluid").setBlock(acidLiquid).setDensity(9).setViscosity(900);
	  		//materialLight = new MaterialLiquid(MapColor.sandColor);
	  		FluidRegistry.registerFluid(lightFluid);
	  		FluidRegistry.registerFluid(acidFluid);
	  		
	  		//Settings for Blocks
	  		lightDirt = new BlockLightDirt(Material.ground).setHardness(0.5F).setStepSound(Block.soundTypeSand).setLightLevel(0.9375F).setBlockName("lightDirt");
	  		lightWand = new BlockLightWand().setHardness(0.0F).setLightLevel(1.00F).setStepSound(Block.soundTypeWood).setBlockName("lightWand").setBlockTextureName("lightwand"); //502
	  		lightLiquid = new BlockLightLiquid().setBlockName("fluidLight");
	  		acidLiquid = new BlockAcidLiquid().setBlockName("fluidAcid");
	  		goldenDirt = new BlockGoldenDirt().setBlockName("goldenDirt");
	  		tilledGoldenField = new BlockGoldenFarmland().setHardness(0.6F).setBlockName("goldenFarmland");
	  	    goldenGrass = new BlockGoldenGrass(Material.grass).setBlockName("goldenGrass");
	  		goldenWoodPlanks = new BlockGoldenWoodPlank().setHardness(1.5F).setBlockName("goldenWoodPlank");
	  		energyTrashcan = new BlockTrashCan().setBlockName("energizedTrashcan");
	  		blockTeleporter = new BlockTeleporter().setBlockName("energyPortal").setBlockTextureName(modid + ":energyPortal");
	  		teleporterFire = new BlockTeleporterFire().setBlockName("blueFire");
	  		//blueFlower = new BlockBlueFlower(2).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("blueFlower");
	  		//purpleFlower = new PurpleFlowerBlock(2).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("purpleFlower");
	  		blockWindmill = new BlockWindmill(Material.rock).setBlockName("blockWindmill");
	  		blockWindmillGround = new BlockWindmillGround(Material.ground).setBlockName("blockWindmillGround").setBlockTextureName(modid + ":blockWindmillGround").setCreativeTab(energyTab);
	  				
	  		registerBlock(lightDirt, "lightDirt");
	  		registerBlock(lightWand, "lightWand");
	  		registerBlock(lightLiquid, "fluidLight");
	  		registerBlock(acidLiquid, "fluidAcid");
	  		registerBlock(goldenDirt, "goldenDirt");
	  		registerBlock(tilledGoldenField, "goldenFarmland");
	  		registerBlock(goldenGrass, "goldenGrass");
	  		registerBlock(goldenWoodPlanks,  "goldenWoodPlank");
	  		registerBlock(energyTrashcan, "energizedTrashcan");
	  		//registerBlock(blueFlower, "blueFlower");
	  		//registerBlock(purpleFlower, "purpleFlower");
	  		registerBlock(blockTeleporter, "energyPortal");
	  		registerBlock(teleporterFire, "blueFire");
	  		registerBlock(blockWindmill, "blockWindmill");
	  		registerBlock(blockWindmillGround, "blockWindmillGround");
	  		
	          
	  		//Settings for Ores
	  		oreTin = new OreBlocks(Material.iron).setBlockName("tinOre");
	  		oreNickel = new OreBlocks(Material.iron).setBlockName("nickelOre");
	  		oreVoid = new OreBlocks(Material.ground).setBlockName("voidOre");
	  		oreCopper = new OreBlocks(Material.ground).setBlockName("copperOre");
	  		oreRandomite = new OreBlocks(Material.rock).setBlockName("randomiteOre");
	  		oreSulphur = new OreBlocks(Material.ground).setBlockName("sulphurOre");
	  		oreRhodium = new OreBlocks(Material.iron).setBlockName("rhodiumOre");
	  		
	  		registerBlock(oreTin,  "tinOre");
	  		registerBlock(oreNickel,  "nickelOre");
	  		registerBlock(oreVoid,  "voidOre");
	  		registerBlock(oreCopper,  "copperOre");
	  		registerBlock(oreRandomite,  "randomiteOre");
	  		registerBlock(oreSulphur,  "sulphuOre");
	  		registerBlock(oreRhodium,  "rhodiumOre");
	  		
	  		
	  		
	  		//Settings for  Items
	  	    lightStoneDust = new ItemBasic().setUnlocalizedName("lightStoneDust");
	  	    cobblestoneNugget = new ItemBasic().setUnlocalizedName("cobblestoneNugget");
	  	    lightStone = new ItemBasic().setUnlocalizedName("lightStone");
	  	    liquidLightBucket = new ItemLiquidBucket(lightLiquid).setUnlocalizedName("bucketOfLight");
	  	    liquidAcidBucket = new ItemLiquidBucket(acidLiquid).setUnlocalizedName("bucketOfAcid");
	  	    ingotCopper = new IngotItem().setUnlocalizedName("copperIngot");
	  	    ingotTin = new IngotItem().setUnlocalizedName("tinIngot");
	  	    ingotNickel = new IngotItem().setUnlocalizedName("nickelIngot");
	  	    ingotVoid = new IngotItem().setUnlocalizedName("voidIngot");
	  	    ingotSteel = new IngotItem().setUnlocalizedName("steelIngot");
	  	    ingotBronze = new IngotItem().setUnlocalizedName("bronzeIngot");
	  	    voidPowder = new ItemBasic().setUnlocalizedName("voidPowder");
	  	    randomitePowder = new ItemBasic().setUnlocalizedName("randomitePowder");
	  	    randomite = new ItemBasic().setUnlocalizedName("randomite");
	  	    energyIgniter = new ItemEnergyIgniter().setUnlocalizedName("energyIgniter");
	  	    crushedIron = new ItemBasic().setUnlocalizedName("crushedIron");
	  	    energyCell = new ItemBattery(1500, true).setUnlocalizedName("energyCell");
	  	    sulphurNugget = new ItemBasic().setUnlocalizedName("sulphurNugget");
	  		rhodium = new ItemBasic().setUnlocalizedName("rhodium");
	  		itemWindmill = new ItemWindmill().setUnlocalizedName("windmill");
	        
	  	    
	  	    registerItem(lightStoneDust, "lightStoneDust");
	  	    registerItem(lightStone, "lightStone");
	  	    registerItem(liquidLightBucket, "bucketOfLight");
	  	    registerItem(liquidAcidBucket, "bucketOfAcid");
	  	    registerItem(ingotCopper, "copperIngot");
	  	    registerItem(ingotTin, "tinIngot");
	  	    registerItem(ingotNickel, "nickelIngot");
	  	    registerItem(ingotVoid, "voidIngot");
	  	    registerItem(ingotSteel, "steelIngot");
	  	    registerItem(ingotBronze, "bronzeIngot");
	  	    registerItem(voidPowder, "voidPowder");
	  	    registerItem(randomitePowder, "randomitePowder");
	  	    registerItem(randomite, "randomite");
	  	    registerItem(cobblestoneNugget, "cobblestoneNugget");
	  	    registerItem(energyIgniter, "energyIgniter");
	  	    registerItem(crushedIron, "crushedIron");
	  	    registerItem(energyCell, "energyCell");
	  	    registerItem(sulphurNugget, "sulphurNugget");
	  	    registerItem(rhodium, "rhodium");
	  	    registerItem(itemWindmill, "windMill");
	  	    
	  	    
	  	    //Settings for Tools
	  	    energyAxe = new ToolEnergyAxe(LightEnergy).setUnlocalizedName("energyAxe");
	  		energyHoe = new ToolEnergyHoe(LightEnergy).setUnlocalizedName("energyHoe");
	  		energyShovel = new ToolEnergyShovel(LightEnergy).setUnlocalizedName("energyShovel");
	  		energyPickaxe = new ToolEnergyPickaxe(LightEnergy).setUnlocalizedName("energyPickaxe");
	  		
	  		registerItem(energyAxe, "energyAxe");
	  	    registerItem(energyHoe, "energyHoe");
	  	    registerItem(energyShovel, "energyShovel");
	  	    registerItem(energyPickaxe, "energyPickaxe");
	  	    
	  	    //Settings for Weapons
	  	    purpleLightsaber = new WeaponPurpleLightsaberItem(LightEnergy).setUnlocalizedName("purple_lightsaber");
	  	    goldenBow = new WeaponGoldenBowItem().setUnlocalizedName("goldenBow").setTextureName("goldenBow");
	  	    
	  	    registerItem(purpleLightsaber, "purple_lightsaber");
	  	    registerItem(goldenBow, "goldenBow");
	  	    
	  	    int renderLightEnergyArmor = proxy.addArmor("energy");
	  	    				
	  	    //Settings for Armor
	  	    lightHelmet = new ItemLightHelmet(EnergyArmor,renderLightEnergyArmor,0).setUnlocalizedName("lightHelmet");
	  	    lightChestplate = new ItemLightChestplate(EnergyArmor,renderLightEnergyArmor,1).setUnlocalizedName("lightChestplate");
	  	    lightLeggings = new ItemLightLeggings(EnergyArmor,renderLightEnergyArmor,2).setUnlocalizedName("lightLeggings");
	  	    lightBoots = new ItemLightBoots(EnergyArmor,renderLightEnergyArmor,3).setUnlocalizedName("lightBoots");	    
	  	  
	  	    registerItem(lightHelmet,  "lightHelmet");
	  	    registerItem(lightChestplate,  "lightChestplate");
	  	  	registerItem(lightLeggings,  "lightLeggings");
	  	 	registerItem(lightBoots,  "lightBoots");	  	
	        
	  	    //Settings for Plants, Trees, and Crops
	  	    goldenLog = new BlockGoldenLog().setBlockName("goldenLog").setHardness(1.5F);
	  		goldenLeaf = new BlockGoldenLeaf().setHardness(0.1F).setBlockName("goldenLeaf");
	  		goldenSapling = new BlockGoldenSapling().setHardness(0.0F).setBlockName("goldenSapling");
	  		blockTomato = new BlockTomato().setHardness(0.0F).setBlockName("tomatoPlant");
	  		
	  		registerBlock(goldenLog, "goldenLog");
	  		registerBlock(goldenLeaf, "goldenLeaf");
	  		registerBlock(goldenSapling,  "goldenSapling");	  		
	  		registerBlock(blockTomato,  "tomatoPlant");
	  		
	  	    //Settings for Food Items
	  	    cheese = new FoodCheeseItem(2,0.3F,false).setUnlocalizedName("cheese");
	  	    sloppyJoe = new FoodSloppyJoeItem(5,0.4F,true).setUnlocalizedName("sloppyJoe");
	  	    cheeseBurger = new FoodCheeseBurgerItem(10,1.2F,false).setUnlocalizedName("cheeseburger");
	  	    hamburger = new FoodHamburgerItem(9,1.1F,true).setUnlocalizedName("hamburger");
	  	    baconAndEggs = new FoodBaconAndEggsItem(10,1.2F,false).setUnlocalizedName("baconAndEggs");
	  	    beefStew = new FoodBeefStewItem(9,1.1F,false).setUnlocalizedName("beefStew");
	  	    tomato = new ItemSeedFood(3,0.5F,blockTomato,Blocks.farmland).setUnlocalizedName("tomato").setTextureName(modid + ":tomato").setCreativeTab(energyFoodsTab);
	  	
	  	    registerItem(cheese,  "cheese");
	  	    registerItem(sloppyJoe,  "sloppyJoe");
	  	    registerItem(cheeseBurger,  "cheeseburger");
	  	    registerItem(hamburger,  "hamburger");
	  	    registerItem(baconAndEggs,  "baconAndEggs");
	  	    registerItem(beefStew,  "beefStew");
	  	    registerItem(tomato,  "tomato");
	  	    
	  	    //setting for Mob
	  		int mobID = getUniqueEntityId();
	  		EntityRegistry.findGlobalUniqueEntityId();
	  		EntityRegistry.registerGlobalEntityID(EntityReddyFreddy.class, "Reddy Freddy", EntityRegistry.findGlobalUniqueEntityId());
	  		EntityRegistry.addSpawn(EntityReddyFreddy.class, 10, 2, 4, EnumCreatureType.creature, BiomeGenBase.forest);
	  		registerEntityEgg(EntityReddyFreddy.class,0x3c578b,0xFFD338);
	  		
	  			  		
		    //Settings for Chests, Furnaces, and Crafting Tables
	  		goldenChest = new BlockGoldenChest(0).setBlockName("goldenChest").setHardness(2.5F);
	  		goldenChestTrapped = new BlockGoldenChest(1).setBlockName("goldenChestTrapped").setHardness(2.5F);
	  		blockGoldenFurnaceIdle = new BlockGoldenFurnace(false).setBlockName("goldenFurnaceIdle").setLightLevel(0.75F);
	  		blockGoldenFurnaceActive = new BlockGoldenFurnace(true).setBlockName("goldenFurnaceActive").setLightLevel(1.0F);
	  		blockFurnaceOfLightIdle = new BlockFurnaceOfLight(false).setBlockName("furnaceOfLightIdle").setLightLevel(0.75F);
	  		blockFurnaceOfLightActive = new BlockFurnaceOfLight(true).setBlockName("furnaceOfLightActive").setLightLevel(1.0F);
	  		goldenCraftTable = new BlockGoldenTable().setLightLevel(0.88F).setBlockName("goldenCraftTable").setLightLevel(0.75F);
	  		blockGoldenMaceratorIdle = new BlockGoldenMacerator(false).setBlockName("goldenMaceratorIdle").setLightLevel(0.75F);
	  		blockGoldenMaceratorActive = new BlockGoldenMacerator(true).setBlockName("goldenMaceratorActive").setLightLevel(1.0F);
	  		
	  		registerBlock(goldenChest, "goldenChest");
	  		registerBlock(goldenChestTrapped, "goldenChestTrapped");
	  		registerBlock(blockGoldenFurnaceIdle, "goldenFurnaceIdle");
	  		registerBlock(blockGoldenFurnaceActive, "goldenFurnaceActive");
	  		registerBlock(blockFurnaceOfLightIdle, "furnaceOfLightActive");
	  		registerBlock(blockFurnaceOfLightActive, "ActiveFurnacofLight");
	  		registerBlock(goldenCraftTable, "goldenCraftTable");
	  		registerBlock(blockGoldenMaceratorIdle,"goldenMaceratorIdle");
	  		registerBlock(blockGoldenMaceratorActive, "goldenMaceratorActive");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{		
		//Events
	    MinecraftForge.EVENT_BUS.register(new Golden_Bonemeal_Event());
	    MinecraftForge.EVENT_BUS.register(new CustomBucketEvent());	  	
	  	MinecraftForge.EVENT_BUS.register(new registerLightSounds());
	  	MinecraftForge.EVENT_BUS.register(new WindmillHighlightEvent());
	  	
	  	FMLCommonHandler.instance().bus().register(new AchievementHandler());
		
		/*lightStoneHalfSlab = new BlockSlabLightStone(false,lightDirt,Material.ground).setBlockName("slabLightStone");
		lightStoneDoubleSlab = new BlockSlabLightStone(true,lightDirt,Material.ground).setBlockName("doubleSlabLightStone");
		voidPowderHalfSlab = new BlockSlabVoidPowder(false,oreVoid,Material.ground).setBlockName("slabVoidPowder");
		voidPowderDoubleSlab = new BlockSlabVoidPowder(true,oreVoid,Material.ground).setBlockName("doubleSlabVoidPowder");
		lightStoneStair = new StairBlocks(lightDirt).setBlockName("lightstoneStair");
		
		GameRegistry.registerBlock(lightStoneHalfSlab,  BlockSlabLightStone.class, "slabLightStone");
		GameRegistry.registerBlock(lightStoneDoubleSlab,  BlockSlabLightStone.class,"doubleSlabLightStone");
		GameRegistry.registerBlock(voidPowderHalfSlab,  BlockSlabVoidPowder.class,"slabVoidPowder");
		GameRegistry.registerBlock(voidPowderDoubleSlab,  BlockSlabVoidPowder.class,"doubleSlabVoidPowder");
		registerBlock(lightStoneStair,  "lightstoneStair");*/
	    
	    //settings for Custom Spawner
  		//customSpawner = new customSpawner().setUnlocalizedName("customSpawner"); 
  		  		
  	    //Settings for Biomes
		//goldenBiome = new BiomeGoldenBiome(150).setBiomeName("Golden Land");		
		//GameRegistry.addBiome(goldenBiome);  		
  		
  		GameRegistry.registerTileEntity(TileEntityGoldenChest.class, "tileEntityGoldenChest");
  		GameRegistry.registerTileEntity(TileEntityGoldenFurnace.class, "tileEntityGoldenFurnace");
  		GameRegistry.registerTileEntity(TileEntityFurnaceOfLight.class, "tileEntityFurnaceOfLight");
  		GameRegistry.registerTileEntity(TileEntityTrashcan.class, "tileEntityTrashcan");
  		GameRegistry.registerTileEntity(TileEntityGoldenMacerator.class, "tileEntityGoldenMacerator");
  		GameRegistry.registerTileEntity(TileEntityWindmill.class, "tileEntityWindmill");
  		GameRegistry.registerTileEntity(TileEntityWindmillFloor.class, "tileEntityWindmillFloor");
  		
  		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CustomGUIHandler());
  		//NetworkRegistry.INSTANCE.registerConnectionHandler(new ConnectionHandler());  		
		
		//Register Handlers		
		GameRegistry.registerWorldGenerator(eventmanager, 0);
		
		//Dimensions
		//DimensionManager.registerProviderType(dimensionIdVoid, WorldProviderVoid.class, false);
		//DimensionManager.registerDimension(dimensionIdVoid, dimensionIdVoid);
		
		OreRegistry.RegisterOres();
		
		LordRhysModCrafting.loadRecipes();
		LordRhysModCrafting.loadSmeltingRecipes();
		LordRhysModCrafting.loadArmorRecipes();
		
		LordRhysAchievementPage.loadAchievements();
		
		proxy.registerRenderThings();
		//proxy.registerServerTickHandler();
	}
	
	@EventHandler 
	public void postInit(FMLPostInitializationEvent e)
	{
		//OreRecipes.overrideRecipe(oreIron, new ItemStack(crushedIron), 1F);
	}
	
	public void registerBlock(Block block, String string)
	{
		GameRegistry.registerBlock(block, string);
	}
	
	public void registerItem(Item item, String string)
	{
		GameRegistry.registerItem(item, string);
	}

}

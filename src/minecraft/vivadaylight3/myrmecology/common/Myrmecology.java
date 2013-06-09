package vivadaylight3.myrmecology.common;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

import vivadaylight3.myrmecology.common.block.BlockAntFarm;
import vivadaylight3.myrmecology.common.block.BlockAntHill;
import vivadaylight3.myrmecology.common.item.ItemAnt;
import vivadaylight3.myrmecology.common.item.ItemExtractor;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Myrmecology main class
 * @author VivaDaylight3
 */

@Mod(modid=Myrmecology.MOD_ID, name=Myrmecology.MOD_NAME, version=Myrmecology.MOD_VERSION, dependencies = Myrmecology.MOD_DEPENDENCIES)
@NetworkMod(channels = {Myrmecology.MOD_CHANNEL}, clientSideRequired=true, serverSideRequired=false, packetHandler = MyrmecologyPacketHandler.class)
public class Myrmecology
{
	
	public static final String MOD_ID = "Myrmecology";
	public static final String MOD_ID_LOWER = "myrmecology";
	public static final String MOD_NAME = "Myrmecology";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_DEPENDENCIES = "";
	
	public static Block blockAntFarm;
	public static int blockAntFarmID;
	public static final String BLOCK_ANTFARM_NAME = "antFarm";
	public static final String BLOCK_ANTFARM_NAME_HUMAN = "Ant Farm";
	
	public static Block blockAntHill;
	public static int blockAntHillID;
	public static final String BLOCK_ANTHILL_NAME = "antHill";
	public static final String BLOCK_ANTHILL_NAME_HUMAN = "Ant Hill";
	
	public static Item itemExtractor;
	public static int itemExtractorID;
	public static final String ITEM_EXTRACTOR_NAME = "extractor";
	public static final String ITEM_EXTRACTOR_NAME_HUMAN = "Ant Extractor";
	
	public static Item itemAnt;
	public static int itemAntID;
	public static final String ITEM_ANT_NAME = "ant";
	
	public static Item antForestQueen;
	public static Item antForestDrone;
	public static Item antForestWorker;
	public static Item antForestSoldier;
	public static Item antForestLarva;
	
	public static Item antHillsQueen;
	public static Item antHillsDrone;
	public static Item antHillsWorker;
	public static Item antHillsSoldier;
	public static Item antHillsLarva;
	
	public static Item antDesertQueen;
	public static Item antDesertDrone;
	public static Item antDesertWorker;
	public static Item antDesertSoldier;
	public static Item antDesertLarva;
	
	public static Item antSwampQueen;
	public static Item antSwampDrone;
	public static Item antSwampWorker;
	public static Item antSwampSoldier;
	public static Item antSwampLarva;
	
	public static Item antPlainsQueen;
	public static Item antPlainsDrone;
	public static Item antPlainsWorker;
	public static Item antPlainsSoldier;
	public static Item antPlainsLarva;
	
	public static Item antJungleQueen;
	public static Item antJungleDrone;
	public static Item antJungleWorker;
	public static Item antJungleSoldier;
	public static Item antJungleLarva;
	
	@SidedProxy(clientSide = "vivadaylight3." + Myrmecology.MOD_ID_LOWER + ".client.ClientProxy", serverSide = "vivadaylight3." + Myrmecology.MOD_ID_LOWER + ".common.CommonProxy")
	public static final String MOD_CHANNEL = Myrmecology.MOD_ID;
		
	public static final int ID_BLOCK = 1551;
	public static final int ID_ITEM = 3853;
	
	@Metadata(Myrmecology.MOD_ID)
	public static ModMetadata meta;
	
	public static final String RESOURCE_PATH = "/mods/" + Myrmecology.MOD_ID_LOWER + "/";
	public static final String TEXTURE_PATH = RESOURCE_PATH + "textures/";
	public static final String BLOCK_PATH = TEXTURE_PATH + "blocks/";
	public static final String ITEM_PATH = TEXTURE_PATH + "items/";
	public static final String GUI_PATH = TEXTURE_PATH + "gui/";
	public static final String LANG_PATH = RESOURCE_PATH + "langauges/";
	public static final String TEXTURE_PREFIX = Myrmecology.MOD_ID_LOWER + ":";
	
	private static Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), MOD_ID + ".cfg"));
	
	@Instance(MOD_NAME)
	public static Myrmecology instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
	
		// Config
		config.load();
		
		blockAntFarmID = config.get(config.CATEGORY_BLOCK, BLOCK_ANTFARM_NAME, ID_BLOCK).getInt();
		blockAntHillID = config.get(config.CATEGORY_BLOCK, BLOCK_ANTFARM_NAME, ID_BLOCK + 1).getInt();
		
		itemExtractorID = config.get(config.CATEGORY_ITEM, ITEM_EXTRACTOR_NAME, ID_ITEM).getInt();
		itemAntID = config.get(config.CATEGORY_ITEM, ITEM_ANT_NAME, ID_ITEM+2).getInt();
		
		config.save();
		
		blockAntFarm = new BlockAntFarm(blockAntFarmID, BLOCK_ANTFARM_NAME);
		blockAntHill = new BlockAntHill(blockAntHillID, BLOCK_ANTHILL_NAME);
		
		itemExtractor = new ItemExtractor(itemExtractorID, ITEM_EXTRACTOR_NAME);
		
		antForestQueen = new ItemAnt(itemAntID, ItemAnt.FOREST_ANT+" "+ItemAnt.QUEEN_ANT, antForestQueen);
		antForestDrone = new ItemAnt(itemAntID, ItemAnt.FOREST_ANT+" "+ItemAnt.DRONE_ANT, antForestDrone);
		antForestWorker = new ItemAnt(itemAntID, ItemAnt.FOREST_ANT+" "+ItemAnt.WORKER_ANT, antForestWorker);
		antForestSoldier = new ItemAnt(itemAntID, ItemAnt.FOREST_ANT+" "+ItemAnt.SOLDIER_ANT, antForestSoldier);
		antForestLarva = new ItemAnt(itemAntID, ItemAnt.FOREST_ANT+" "+ItemAnt.LARVA_ANT, antForestLarva);
		
		antHillsQueen = new ItemAnt(itemAntID, ItemAnt.HILLS_ANT+" "+ItemAnt.QUEEN_ANT, antHillsQueen);
		antHillsDrone = new ItemAnt(itemAntID, ItemAnt.HILLS_ANT+" "+ItemAnt.DRONE_ANT, antHillsDrone);
		antHillsWorker = new ItemAnt(itemAntID, ItemAnt.HILLS_ANT+" "+ItemAnt.WORKER_ANT, antHillsWorker);
		antHillsSoldier = new ItemAnt(itemAntID, ItemAnt.HILLS_ANT+" "+ItemAnt.SOLDIER_ANT, antHillsSoldier);
		antHillsLarva = new ItemAnt(itemAntID, ItemAnt.HILLS_ANT+" "+ItemAnt.LARVA_ANT, antHillsLarva);
		
		antDesertQueen = new ItemAnt(itemAntID, ItemAnt.DESERT_ANT+" "+ItemAnt.QUEEN_ANT, antDesertQueen);
		antDesertDrone = new ItemAnt(itemAntID, ItemAnt.DESERT_ANT+" "+ItemAnt.DRONE_ANT, antDesertDrone);
		antDesertWorker = new ItemAnt(itemAntID, ItemAnt.DESERT_ANT+" "+ItemAnt.WORKER_ANT, antDesertWorker);
		antDesertSoldier = new ItemAnt(itemAntID, ItemAnt.DESERT_ANT+" "+ItemAnt.SOLDIER_ANT, antDesertSoldier);
		antDesertLarva = new ItemAnt(itemAntID, ItemAnt.DESERT_ANT+" "+ItemAnt.LARVA_ANT, antDesertLarva);
		
		antSwampQueen = new ItemAnt(itemAntID, ItemAnt.SWAMP_ANT+" "+ItemAnt.QUEEN_ANT, antSwampQueen);
		antSwampDrone = new ItemAnt(itemAntID, ItemAnt.SWAMP_ANT+" "+ItemAnt.DRONE_ANT, antSwampDrone);
		antSwampWorker = new ItemAnt(itemAntID, ItemAnt.SWAMP_ANT+" "+ItemAnt.WORKER_ANT, antSwampWorker);
		antSwampSoldier = new ItemAnt(itemAntID, ItemAnt.SWAMP_ANT+" "+ItemAnt.SOLDIER_ANT, antSwampWorker);
		antSwampLarva = new ItemAnt(itemAntID, ItemAnt.SWAMP_ANT+" "+ItemAnt.LARVA_ANT, antSwampLarva);
		
		antPlainsQueen = new ItemAnt(itemAntID, ItemAnt.PLAINS_ANT+" "+ItemAnt.QUEEN_ANT, antPlainsQueen);
		antPlainsDrone = new ItemAnt(itemAntID, ItemAnt.PLAINS_ANT+" "+ItemAnt.DRONE_ANT, antPlainsDrone);
		antPlainsWorker = new ItemAnt(itemAntID, ItemAnt.PLAINS_ANT+" "+ItemAnt.WORKER_ANT, antPlainsWorker);
		antPlainsSoldier = new ItemAnt(itemAntID, ItemAnt.PLAINS_ANT+" "+ItemAnt.SOLDIER_ANT, antPlainsSoldier);
		antPlainsLarva = new ItemAnt(itemAntID, ItemAnt.PLAINS_ANT+" "+ItemAnt.LARVA_ANT, antPlainsLarva);
		
		antJungleQueen = new ItemAnt(itemAntID, ItemAnt.JUNGLE_ANT+" "+ItemAnt.QUEEN_ANT, antJungleQueen);
		antJungleDrone = new ItemAnt(itemAntID, ItemAnt.JUNGLE_ANT+" "+ItemAnt.DRONE_ANT, antJungleDrone);
		antJungleWorker = new ItemAnt(itemAntID, ItemAnt.JUNGLE_ANT+" "+ItemAnt.WORKER_ANT, antJungleWorker);
		antJungleSoldier = new ItemAnt(itemAntID, ItemAnt.JUNGLE_ANT+" "+ItemAnt.SOLDIER_ANT, antJungleSoldier);
		antJungleLarva = new ItemAnt(itemAntID, ItemAnt.JUNGLE_ANT+" "+ItemAnt.LARVA_ANT, antJungleLarva);
				
	}
	
	public static CreativeTabs tabMyrmecology = new CreativeTabs("tab" + MOD_ID){
        
        public ItemStack getIconItemStack() {
        	
                return new ItemStack(Item.paper, 1, 0);
                
        }
	};
	
	@Init
	public void mainInit(FMLInitializationEvent event){
				
		meta.modId = Myrmecology.MOD_ID;
		meta.name = Myrmecology.MOD_NAME;
		meta.description = "Allows you to breed and cultivate ants.";
		meta.url = "https://github.com/VivaDaylight3/myrmecology";

		meta.logoFile = Myrmecology.TEXTURE_PATH + "Myrmecology_Banner.png";
		meta.version = Myrmecology.MOD_VERSION;
		meta.authorList = Arrays.asList(new String[] {"VivaDaylight3"});
		meta.credits = "The Minecraft Forge team and community for their fantastic work.";
		meta.autogenerated = false;
				
		LanguageRegistry.instance().addStringLocalization("itemGroup.tab" + MOD_ID, "en_GB", MOD_NAME);
		
		LanguageRegistry.addName(blockAntFarm, BLOCK_ANTFARM_NAME_HUMAN);
		LanguageRegistry.addName(blockAntHill, BLOCK_ANTHILL_NAME_HUMAN);
		LanguageRegistry.addName(itemExtractor, ITEM_EXTRACTOR_NAME_HUMAN);
		
		GameRegistry.registerBlock(blockAntFarm, BLOCK_ANTFARM_NAME_HUMAN);
		GameRegistry.registerBlock(blockAntHill, BLOCK_ANTHILL_NAME_HUMAN);
		GameRegistry.registerItem(itemExtractor, ITEM_EXTRACTOR_NAME_HUMAN);
				
	}
	
	/**
	 * Returns the appropriate metadata change and block orientation depending on entity facing
	 * @param x
	 * @param y
	 * @param z
	 * @param entity
	 * @return
	 */
	
	public static int getBlockOrientation(int x, int y, int z, EntityLiving entity){
		
		int angle = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int change = 0;
		
		switch(angle){
			
			case 0:
				change = 0;
				break;

			case 1:
				change = 1;
				break;

			case 2:
				change = 2;
				break;

			case 3:
				change = 3;
				break;
			
		}
		
		return change;
		
	}
	
	/**
	 * Returns the side name of the block depending on its metadata and side
	 * @author VivaDaylight3
	 * @param side
	 * @param metadata
	 * @param basemeta
	 * @return String 'top', 'bottom', 'front', 'input', 'output' or 'back'
	 */
		
	public static String getBlockSide(int side, int metadata, int basemeta){
		
		int meta1 = basemeta;
		int meta2 = basemeta + 1;
		int meta3 = basemeta + 2;
		int meta4 = basemeta + 3;
		
		if (side == 1){
			
			return "top";
			
		}if (side == 0){
			
			return "bottom";
		
		}else if((metadata == meta1 && side == 2) || (metadata == meta2 && side == 5) || (metadata == meta3 && side == 3) || (metadata == meta4 && side == 4)){
			
			return "front";
			
		}else if((metadata == meta1 && side == 4) || (metadata == meta2 && side == 2) || (metadata == meta3 && side == 5) || (metadata == meta4 && side == 3)){
			
			return "input";
			
		}else if((metadata == meta1 && side == 5) || (metadata == meta2 && side == 3) || (metadata == meta3 && side == 4) || (metadata == meta4 && side == 2)){
			
			return "output";
			
		}else{
		
			return "back";
		
		}
		
	}
	
	/**
	 * Gets the input side depending on metadata
	 * @param int metadata
	 * @param int block's lowest metadata
	 * @return int input side number
	 */
	public static int getBlockInput(int metadata, int basemetadata){
		
		if(metadata == basemetadata){
			
			return 4;
			
		}else if(metadata == basemetadata + 1){
			
			return 2;
			
		}else if(metadata == basemetadata + 2){
			
			return 5;
			
		}else{
			
			return 3;
			
		}
		
	}
	
	/**
	 * Gets the output side depending on metadata
	 * @param int metadata
	 * @param int block's lowest metadata
	 * @return int output side number
	 */
	public static int getBlockOutput(int metadata, int basemetadata){
		
		if(metadata == basemetadata){
			
			return 5;
			
		}else if(metadata == basemetadata + 1){
			
			return 3;
			
		}else if(metadata == basemetadata + 2){
			
			return 4;
			
		}else{
			
			return 2;
			
		}
		
	}
	
	public static int getChance(int max){
		
		Random num = new Random();
		
		int i = num.nextInt(max+1);
		
		return i;
		
	}
	
	public static BiomeGenBase getBiome(World world, int x, int z){
		
		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);
		
		return biome;
		
	}
	
	public static Icon[] iconsToArray(IconRegister iconRegister, String ... icons){
		
		Icon[] iconArray = null;
		
		for(int k = 0; k < icons.length + 1; k++){
			
			iconArray[k] = iconRegister.registerIcon(icons[k]);
			
		}
		
		return iconArray;
		
	}
	
}

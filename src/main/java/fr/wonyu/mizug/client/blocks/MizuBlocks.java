package fr.wonyu.mizug.client.blocks;

import java.util.ArrayList;
import java.util.List;

import fr.wonyu.mizug.client.blocks.bar.MizuBeerPlate;
import fr.wonyu.mizug.client.blocks.bar.MizuBoutChoppXXL;
import fr.wonyu.mizug.client.blocks.bar.MizuBoutXL;
import fr.wonyu.mizug.client.blocks.bar.MizuChaiseBar;
import fr.wonyu.mizug.client.blocks.bar.MizuTableBar;
import fr.wonyu.mizug.client.blocks.bar.MizuTonneau;
import fr.wonyu.mizug.client.blocks.container.MizuBigCratesBlock;
import fr.wonyu.mizug.client.blocks.container.MizuCratesBlock;
import fr.wonyu.mizug.client.blocks.container.MizuRackArmes;
import fr.wonyu.mizug.client.blocks.crafting.MizuCompactor;
import fr.wonyu.mizug.client.blocks.crafting.MizuForge;
import fr.wonyu.mizug.client.blocks.escalier.MizuIceStairs;
import fr.wonyu.mizug.client.blocks.escalier.MizuStoneSlabStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuBlackClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuBlueClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuBrownClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuCyanClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuGrayClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuGreenClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuLimeClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuMagentaClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuOrangeClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuPinkClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuPurpleClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuRedClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuSilverClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuWhiteClayStairs;
import fr.wonyu.mizug.client.blocks.escalier.clay.MizuYellowClayStairs;
import fr.wonyu.mizug.client.blocks.fun.MizuCaseVide;
import fr.wonyu.mizug.client.blocks.fun.MizuFishCase;
import fr.wonyu.mizug.client.blocks.fun.MizuPiedestalOak;
import fr.wonyu.mizug.client.blocks.fun.MizuSceau;
import fr.wonyu.mizug.client.blocks.mobiler.MizuBucheChaiseOak;
import fr.wonyu.mizug.client.blocks.mobiler.MizuChaise;
import fr.wonyu.mizug.client.blocks.mobiler.MizuGrandeTable;
import fr.wonyu.mizug.client.blocks.mobiler.MizuMeubleTiroirs;
import fr.wonyu.mizug.client.blocks.mobiler.MizuPorte;
import fr.wonyu.mizug.client.blocks.mobiler.MizuPorteVerre;
import fr.wonyu.mizug.client.blocks.mobiler.MizuTable;
import fr.wonyu.mizug.client.blocks.ores.MizuAluminiumOre;
import fr.wonyu.mizug.client.blocks.ores.MizuAmetysteOre;
import fr.wonyu.mizug.client.blocks.ores.MizuArgentOre;
import fr.wonyu.mizug.client.blocks.ores.MizuBerylliumOre;
import fr.wonyu.mizug.client.blocks.ores.MizuCobaltOre;
import fr.wonyu.mizug.client.blocks.ores.MizuCuivreOre;
import fr.wonyu.mizug.client.blocks.ores.MizuEtainOre;
import fr.wonyu.mizug.client.blocks.ores.MizuFairiteOre;
import fr.wonyu.mizug.client.blocks.ores.MizuMiziumOre;
import fr.wonyu.mizug.client.blocks.ores.MizuOsmiumOre;
import fr.wonyu.mizug.client.blocks.ores.MizuPlatineOre;
import fr.wonyu.mizug.client.blocks.ores.MizuQuartzOre;
import fr.wonyu.mizug.client.blocks.ores.MizuTitaneOre;
import fr.wonyu.mizug.client.blocks.ores.MizuTritiumOre;
import fr.wonyu.mizug.client.blocks.ores.MizuUraniumOre;
import fr.wonyu.mizug.client.blocks.ores.blocks.MizuBeryllium;
import fr.wonyu.mizug.client.blocks.ores.blocks.MizuCuivre;
import fr.wonyu.mizug.client.blocks.ores.blocks.MizuEtain;
import fr.wonyu.mizug.client.blocks.ores.blocks.MizuTitane;
import fr.wonyu.mizug.client.blocks.ores.blocks.MizuUranium;
import fr.wonyu.mizug.client.blocks.plants.MizuMaisCrop;
import fr.wonyu.mizug.client.blocks.plants.fruit.MizuApple;
import fr.wonyu.mizug.client.blocks.plants.tree.leaf.MizuAppleLeaf;
import fr.wonyu.mizug.client.blocks.plants.tree.log.MizuAppleLog;
import fr.wonyu.mizug.client.blocks.sapling.MizuAppleSapling;
import fr.wonyu.mizug.client.blocks.transport.MizuPontD;
import fr.wonyu.mizug.client.blocks.transport.MizuPontM;
import fr.wonyu.mizug.client.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class MizuBlocks {
	
	public static Material mat;
	
	public static final List<Block> blocks = new ArrayList<Block>();
	
		// NORMAUX
		public static Block CHAISE = new MizuChaise(mat.WOOD);
		public static Block TABLE = new MizuTable(mat.WOOD);;
		public static Block GRANDE_TABLE = new MizuGrandeTable(mat.WOOD);
		public static Block ARMOIRE_PORTE = new MizuPorte(mat.WOOD);
		public static Block ARMOIRE_VERRE_P = new MizuPorteVerre(mat.GLASS);
		public static Block MEUBLET = new MizuMeubleTiroirs(mat.WOOD);
		public static Block PONT_D = new MizuPontD(mat.WOOD);
		public static Block PONT_M = new MizuPontM(mat.WOOD);
		public static Block SCEAU = new MizuSceau(mat.WOOD);
		public static Block BUCHE_CHAISE = new MizuBucheChaiseOak(mat.WOOD);
		public static Block PIEDESTAL_OAK = new MizuPiedestalOak(mat.WOOD);
		public static Block BEER_PLATE = new MizuBeerPlate(mat.WOOD);
		public static Block BOUT_CHOPP_XXL = new MizuBoutChoppXXL(mat.GLASS);
		public static Block BOUT_XL = new MizuBoutXL(mat.GLASS);
		public static Block CHAISE_BAR = new MizuChaiseBar(mat.WOOD);
		public static Block TABLE_BAR = new MizuTableBar(mat.WOOD);
		public static Block TONNEAU = new MizuTonneau(mat.WOOD);
		public static Block CASE = new MizuCaseVide(mat.WOOD);
		
		
		// CRAFT
		public static Block FISH_CASE = new MizuFishCase(mat.WOOD);
		
		public static Block TEST = new Test(mat.WOOD);
				
		// CONTAINERS
		public static Block CRATES = new MizuCratesBlock(mat.WOOD);
		public static Block BIG_CRATES = new MizuBigCratesBlock(mat.WOOD);
		public static Block RACK = new MizuRackArmes(mat.WOOD);
		
		// MACHINES
		public static Block COMPACTOR = new MizuCompactor(mat.WOOD);
		public static Block FORGE = new MizuForge(mat.ROCK, false);

		// BLOCKS DE MINERAIS
		public static Block BERYLLIUM = new MizuBeryllium(mat.ROCK);
		public static Block CUIVRE = new MizuCuivre(mat.ROCK);
		public static Block ETAIN = new MizuEtain(mat.ROCK);
		public static Block TITANE = new MizuTitane(mat.ROCK);
		public static Block URANIUM = new MizuUranium(mat.ROCK);
		
		// MINERAIS OVERWORLD
		// MINERAIS NETHER
		// MINERAIS END
		public static Block BERYLLIUM_ORE = new MizuBerylliumOre(mat.ROCK);
		public static Block CUIVRE_ORE = new MizuCuivreOre(mat.ROCK);
		public static Block MIZIUM_ORE = new MizuMiziumOre(mat.ROCK);
		public static Block TRITIUM_ORE = new MizuTritiumOre(mat.ROCK);
		public static Block ARGENT_ORE = new MizuArgentOre(mat.ROCK);
		public static Block ETAIN_ORE = new MizuEtainOre(mat.ROCK);
		public static Block TITANE_ORE = new MizuTitaneOre(mat.ROCK);
		public static Block QUARTZ_ORE = new MizuQuartzOre(mat.ROCK);
		public static Block FAIRITE_ORE = new MizuFairiteOre(mat.ROCK);
		public static Block URANIUM_ORE = new MizuUraniumOre(mat.ROCK);
		public static Block COBALT_ORE = new MizuCobaltOre(mat.ROCK);
		public static Block AMETYSTE_ORE = new MizuAmetysteOre(mat.ROCK);
		public static Block OSMIUM_ORE = new MizuOsmiumOre(mat.ROCK);
		public static Block ALUMINIUM_ORE = new MizuAluminiumOre(mat.ROCK);
		public static Block PLATINE_ORE = new MizuPlatineOre(mat.ROCK);
		
		// FRUITS
		public static Block APPLE_BLOCK = new MizuApple(mat.PLANTS);
		
		// PLANTES
		public static Block MAIS_PLANT = new MizuMaisCrop(mat.PLANTS);
		
		// LOGS
		public static Block APPLE_LOG = new MizuAppleLog(mat.WOOD);
		
		// LEAVES
		public static Block APPLE_LEAF = new MizuAppleLeaf(mat.PLANTS);
		
		// SAPLING
		public static Block APPLE_SAPLING = new MizuAppleSapling(mat.PLANTS);
		
		// STAIRS
		public static Block STONE_SLAB_STAIR = new MizuStoneSlabStairs(mat.ROCK, Blocks.STONE_SLAB.getDefaultState());
		public static Block ICE_STAIR = new MizuIceStairs(mat.ICE, Blocks.ICE.getDefaultState());
		
		// CLAY STAIRS
        public static Block WHITE_CLAY_STAIRS = new MizuWhiteClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block BLACK_CLAY_STAIRS = new MizuBlackClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block BLUE_CLAY_STAIRS = new MizuBlueClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block BROWN_CLAY_STAIRS = new MizuBrownClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block CYAN_CLAY_STAIRS = new MizuCyanClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block GRAY_CLAY_STAIRS = new MizuGrayClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block GREEN_CLAY_STAIRS = new MizuGreenClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block LIME_CLAY_STAIRS = new MizuLimeClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block MAGENTA_CLAY_STAIRS = new MizuMagentaClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block ORANGE_CLAY_STAIRS = new MizuOrangeClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block PINK_CLAY_STAIRS = new MizuPinkClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block PURPLE_CLAY_STAIRS = new MizuPurpleClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block RED_CLAY_STAIRS = new MizuRedClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block SILVER_CLAY_STAIRS = new MizuSilverClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
        public static Block YELLOW_CLAY_STAIRS = new MizuYellowClayStairs(mat.ROCK, Blocks.HARDENED_CLAY.getDefaultState());
		
	    public static void setBlockName(Block block, String name) {
		block.setRegistryName(References.MODID, name).setUnlocalizedName(References.MODID + "." + name);
	}

}

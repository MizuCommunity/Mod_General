package fr.wonyu.mizug.client.utils.ores.generation;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import fr.wonyu.mizug.client.blocks.MizuBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MizuOresGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {

		case -1:

			break;

		case 0:
			Biome biome = world.getBiomeForCoordsBody(new BlockPos(chunkX * 16, 0, chunkZ * 16));
			generateOverworld(random, chunkX * 16, chunkZ * 16, world, biome);

			break;

		case 1:

			break;

		}

	}

	private void generateOverworld(Random rand, int worldX, int worldZ, World world, Biome biome) {
		if (IsBiome(biome, 0, 10, 11, 7)) {
			addMizuOre(MizuBlocks.MIZIUM_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 15, 70, 1,
					10, 60);
		}

		if (IsBiome(biome, 15, 16, 17)) {
			addMizuOre(MizuBlocks.BERYLLIUM_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 5, 10,
					1, 4, 30);
		}

		if (IsBiome(biome, 5, 13, 29, 30, 31, 32, 33, 85, 157, 158, 160, 161)) {
			addMizuOre(MizuBlocks.TRITIUM_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 5, 54,
					1, 4, 40);

		}

		addMizuOre(MizuBlocks.CUIVRE_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 1, 50, 3, 8,
				20);
		addMizuOre(MizuBlocks.ETAIN_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 2, 40, 2, 5,
				25);
		addMizuOre(MizuBlocks.ARGENT_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 2, 25, 2, 8,
				10);
		addMizuOre(MizuBlocks.TITANE_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 2, 20, 1, 4,
				15);
		addMizuOre(MizuBlocks.QUARTZ_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 5, 50, 1, 5,
				20);
		addMizuOre(MizuBlocks.FAIRITE_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 3, 20, 1, 6,
				20);
		addMizuOre(MizuBlocks.URANIUM_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 5, 10, 2, 5,
				20);
		addMizuOre(MizuBlocks.COBALT_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 2, 20, 2, 3,
				20);
		addMizuOre(MizuBlocks.AMETYSTE_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 2, 35, 2, 5,
				15);
		addMizuOre(MizuBlocks.OSMIUM_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 3, 28, 1, 4,
				15);
		addMizuOre(MizuBlocks.ALUMINIUM_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 5, 30, 2, 6,
				30);
		addMizuOre(MizuBlocks.PLATINE_ORE.getDefaultState(), Blocks.STONE, rand, world, worldX, worldZ, 5, 20, 2, 6,
				25);

	}

	private void addMizuOre(IBlockState block, final Block blockspawn, Random random, World world, int posX, int posZ,
			int minY, int maxY, int minVein, int maxVein, int spawnChance) {
		for (int i = 0; i < spawnChance; i++) {
			int defaultChunkSize = 16;
			int xPos = posX + random.nextInt(defaultChunkSize);
			int zPos = posZ + random.nextInt(defaultChunkSize);
			int yPos = minY + random.nextInt(maxY - minY);

			new WorldGenMinable(block, (minVein + random.nextInt(maxVein - minVein)), new Predicate<IBlockState>() {
				@Override
				public boolean apply(@Nullable IBlockState input) {
					return input.getBlock() == blockspawn;
				}
			}).generate(world, random, new BlockPos(xPos, yPos, zPos));
		}
	}

	private boolean IsBiome(Biome input, int... allowedBiomeIds) {
		ArrayList<Biome> allowedBiomes = new ArrayList();
		for (int biomeId : allowedBiomeIds)
			allowedBiomes.add(Biome.getBiome(biomeId));

		return allowedBiomes.contains(input);
	}

}

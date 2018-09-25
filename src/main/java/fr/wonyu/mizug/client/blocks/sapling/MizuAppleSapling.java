package fr.wonyu.mizug.client.blocks.sapling;

import java.util.Random;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import fr.wonyu.mizug.client.utils.tree.generation.MizuAppleTreeGen;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MizuAppleSapling extends BlockBush implements IHasModel, IGrowable {

	public static final String APPLE_SAPLING = "apple_sapling";

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	protected static AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D,
			0.8999999761481421D, 0.800000011920929D, 0.8999999761481421D);

	public MizuAppleSapling(Material mat) {
		super();
		MizuBlocks.setBlockName(this, APPLE_SAPLING);
		setCreativeTab(MizuTabs.mizuitems);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        if ( worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(worldIn, pos, state, rand);
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        super.updateTick(worldIn, pos, state, rand);
        if ( worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(worldIn, pos, state, rand);
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (((Integer)state.getValue(STAGE)).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
            this.markBlockUpdate(worldIn, pos);
        } else {
            this.generateTree(worldIn, pos, state, rand);
        }
    }

    private void markBlockUpdate(World worldIn, BlockPos pos ) {
        worldIn.markBlockRangeForRenderUpdate(pos, pos);
        worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
        worldIn.scheduleBlockUpdate(pos, this, 0,0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 7) >> 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((Integer)state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{STAGE});
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        WorldGenerator worldGenerator = new MizuAppleTreeGen(true);
        worldGenerator.generate(worldIn, rand, pos);
    }


}

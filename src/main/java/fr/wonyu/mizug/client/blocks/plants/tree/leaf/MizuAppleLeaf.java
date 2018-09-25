package fr.wonyu.mizug.client.blocks.plants.tree.leaf;

import java.util.Random;

import javax.annotation.Nullable;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.blocks.plants.fruit.MizuApple;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MizuAppleLeaf extends BlockLeaves implements IHasModel, IGrowable {

	public static final String APPLE_LEAF = "apple_leaf";
	
	private static final AxisAlignedBB BOUNDING_BOX =
            new AxisAlignedBB(0.0625 * 0, 0.0625 * 0, 0.0625 * 0, 0.0625 * 16, 0.0625 * 16, 0.0625 * 16);


	public MizuAppleLeaf(Material mat) {
		MizuBlocks.blocks.add(this);
		MizuBlocks.setBlockName(this, APPLE_LEAF);
		setCreativeTab(MizuTabs.mizublock);
		setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true))
				.withProperty(DECAYABLE, Boolean.valueOf(true)));
		MizuG.proxy.setAppleGraphicsLevel(this, true);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);

	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY,
				Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (!state.getValue(DECAYABLE).booleanValue()) {
			i |= 4;
		}

		if (state.getValue(CHECK_DECAY).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { CHECK_DECAY, DECAYABLE });
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state,
			@Nullable TileEntity te, ItemStack stack) {
		if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
			player.addStat(StatList.getBlockStats(this));
		} else {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}

	@Override
	public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos,
			int fortune) {
		return NonNullList.withSize(1, new ItemStack(this));
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
	
	
    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, EnumDyeColor color) {
        return super.recolorBlock(world, pos, side, EnumDyeColor.GREEN);
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }
/**
    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        return true;
    }
*/
    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return block instanceof BlockAir;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if ( worldIn.getBlockState(pos.down()).getBlock() instanceof BlockAir ){
            worldIn.setBlockState(pos.down(), MizuBlocks.APPLE_BLOCK.getDefaultState(), 2);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if ( !worldIn.isRemote ) {
            super.updateTick(worldIn, pos, state, rand);
            // check the light level and pick a randomness for growth.
            if ( worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0 ) {
                this.grow( worldIn, rand, pos, state );
            }
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
        Block blockDown = worldIn.getBlockState(pos.down()).getBlock();
        if ( blockDown instanceof MizuApple) {
            worldIn.destroyBlock(pos.down(), false);
        }
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 0, 2);
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(5) == 0 ? 1 : 0;
    }

}

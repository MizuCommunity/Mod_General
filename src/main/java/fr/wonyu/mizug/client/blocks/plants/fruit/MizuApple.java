package fr.wonyu.mizug.client.blocks.plants.fruit;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.blocks.plants.tree.leaf.MizuAppleLeaf;
import fr.wonyu.mizug.client.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuApple extends BlockBush implements IHasModel, IGrowable {

	public static final String APPLE_BLOCK = "apple_block";

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);

	private static final AxisAlignedBB[] BOUNDING_BOXES = { new AxisAlignedBB(0.25D, 0.9D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.8D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.7D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.4D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.3D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.2D, 0.25D, 0.75D, 1.0D, 0.75D),
			new AxisAlignedBB(0.25D, 0.2D, 0.25D, 0.75D, 1.0D, 0.75D) };

	public MizuApple(Material mat) {

		MizuBlocks.setBlockName(this, APPLE_BLOCK);
		MizuBlocks.blocks.add(this);
		setTickRandomly(true);
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AGE);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(TextFormatting.BLUE + I18n.format(this.getUnlocalizedName() + ".tooltip"));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int iAge = this.getAge(state);

		return BOUNDING_BOXES[((Integer)state.getValue(AGE)).intValue()];
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
			this.grow(worldIn, rand, pos, state);
		}
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return this.getAge(state) != 7;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return this.getAge(state) < 7;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		super.updateTick(worldIn, pos, state, rand);
		// If we have enough light there is a 25% chance of growth to the next stage
		if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(1) == 0) {
			// If the apple isn't full grown
			if (this.getAge(state) != 7) {
				// Then increment the age.
				worldIn.setBlockState(pos, state.cycleProperty(AGE), 4);
				this.markBlockUpdate(worldIn, pos);
			}
		}
	}

	private void markBlockUpdate(World worldIn, BlockPos pos) {
		worldIn.markBlockRangeForRenderUpdate(pos, pos);
		worldIn.notifyBlockUpdate(pos, worldIn.getBlockState(pos), worldIn.getBlockState(pos), 3);
		worldIn.scheduleBlockUpdate(pos, this, 0, 0);
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		Block block = worldIn.getBlockState(pos.up()).getBlock();
		return block instanceof MizuAppleLeaf;
	}

	protected int getAge(IBlockState state) {
		return state.getValue(AGE).intValue();
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (this.getAge(state) == 7) {
			int qty = new Random().nextInt(1) + 1;
			if (!worldIn.isRemote) {
				ItemStack appleStack = new ItemStack(Items.APPLE, qty);
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), appleStack);
				worldIn.setBlockToAir(pos);
			}
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

}
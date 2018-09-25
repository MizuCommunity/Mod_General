package fr.wonyu.mizug.client.blocks.container;

import java.util.List;

import com.google.common.collect.Lists;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.blocks.extend.MizuBlockFacing;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MizuRackArmes extends MizuBlockFacing implements IHasModel {

	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0 * 0.0725, 0.0, 4 * 0.0625, 12 * 0.0625, 2.0,
			12 * 0.0625);

	protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {
			new AxisAlignedBB(-13 * 0.0725, 0.0, 0 * 0.0625, 31 * 0.0625, 2.0, 16 * 0.0625),
			new AxisAlignedBB(0 * 0.0725, 0.0, -15 * 0.0625, 16 * 0.0625, 2.0, 31 * 0.0625),
			new AxisAlignedBB(-13 * 0.0725, 0.0, 0 * 0.0625, 31 * 0.0625, 2.0, 16 * 0.0625),
			new AxisAlignedBB(0 * 0.0725, 0.0, -15 * 0.0625, 16 * 0.0625, 2.0, 31 * 0.0625) };

	public static final String RACK = "rack_armes";

	public MizuRackArmes(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, RACK);
		setCreativeTab(MizuTabs.mizu3d);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		List<AxisAlignedBB> list = Lists.newArrayList();
		EnumFacing facing = state.getValue(FACING);
		list.add(BOUNDING_BOXES[facing.getHorizontalIndex()]);
		return BOUNDING_BOXES[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		List<AxisAlignedBB> list = getCollisionBoxList(this.getActualState(state, worldIn, pos));
		for (AxisAlignedBB box : list) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, box);
		}
	}

	private List<AxisAlignedBB> getCollisionBoxList(IBlockState state) {
		List<AxisAlignedBB> list = Lists.newArrayList();
		EnumFacing facing = state.getValue(FACING);
		list.add(BOUNDING_BOXES[facing.getHorizontalIndex()]);
		return list;
	}

	@Override
	public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start,
			Vec3d end) {
		List<RayTraceResult> list = Lists.newArrayList();

		for (AxisAlignedBB axisalignedbb : getCollisionBoxList(this.getActualState(blockState, worldIn, pos))) {
			list.add(this.rayTrace(pos, start, end, axisalignedbb));
		}

		RayTraceResult raytraceresult1 = null;
		double d1 = 0.0D;

		for (RayTraceResult raytraceresult : list) {
			if (raytraceresult != null) {
				double d0 = raytraceresult.hitVec.squareDistanceTo(end);

				if (d0 > d1) {
					raytraceresult1 = raytraceresult;
					d1 = d0;
				}
			}
		}

		return raytraceresult1;
	}

}

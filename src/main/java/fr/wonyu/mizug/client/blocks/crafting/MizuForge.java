package fr.wonyu.mizug.client.blocks.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.entity.MizuSitEntity;
import fr.wonyu.mizug.client.entity.tiles.MizuForgeTile;
import fr.wonyu.mizug.client.gui.MizuGuiHandler;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.CollisionHelper;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuSit;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuForge extends BlockFurnace implements IHasModel{

	Item toDrop;
	int minDropAmount = 1;
	int maxDropAmount = 0;

	private boolean isBurning;

	public static final AxisAlignedBB FORGE_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, -1.0,
			1.0, 1.8, 2.0);
	public static final AxisAlignedBB FORGE_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, -1.0, 1.0,
			1.8, 2.0);
	public static final AxisAlignedBB FORGE_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, -1.0,
			1.0, 1.8, 2.0);
	public static final AxisAlignedBB FORGE_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, -1.0, 1.0,
			1.8, 2.0);
	public static final String FORGE = "forge";

	public MizuForge(Material mat, boolean isBurning) {
		super(isBurning);
		MizuBlocks.setBlockName(this, FORGE);
		setResistance(5.0F);
		setHardness(3.0F);
		setCreativeTab(MizuTabs.mizu3d);
		this.isBurning = isBurning;
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new MizuForgeTile();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.setDefaultFacing(worldIn, pos, state);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
		EntityPlayer entity = Minecraft.getMinecraft().player;
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		World par1World = world;
		int par2 = i;
		int par3 = j;
		int par4 = k;
		Random par5Random = random;
		if (true)
			for (int l = 0; l < 4; ++l) {
				double d0 = (double) ((float) par2 + par5Random.nextFloat());
				double d1 = (double) ((float) par3 + par5Random.nextFloat());
				double d2 = (double) ((float) par4 + par5Random.nextFloat());
				double d3 = 0.0D;
				double d4 = 0.0D;
				double d5 = 0.0D;
				int i1 = par5Random.nextInt(2) * 2 - 1;
				d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
				d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
				d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
				TileEntity tileEntity = par1World.getTileEntity(pos);
				if (tileEntity instanceof MizuForgeTile) {
					MizuForgeTile tileInventoryFurnace = (MizuForgeTile) tileEntity;
					int burningSlots = tileInventoryFurnace.numberOfBurningFuelSlots();
					burningSlots = MathHelper.clamp(burningSlots, 0, 4);
					par1World.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.0D, d1 + 0.7D, d2 + d4, 0.0D, 0.0D,
							0.0D);
					par1World.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.0D, d1 + 0.7D, d2 + d4, 0.0D, 0.0D, 0.0D);
				}
			}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof MizuForgeTile) {
			MizuForgeTile tileInventoryFurnace = (MizuForgeTile) tileEntity;
			int burningSlots = tileInventoryFurnace.numberOfBurningFuelSlots();
			burningSlots = MathHelper.clamp(burningSlots, 0, 4);
		}
		return state;
	}

	@Override
	public int quantityDropped(Random random) {
		if (this.minDropAmount > this.maxDropAmount) {
			int i = this.minDropAmount;
			this.minDropAmount = this.maxDropAmount;
			this.maxDropAmount = i;
		}
		return this.minDropAmount + random.nextInt(this.maxDropAmount - this.minDropAmount + 1);
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		drops.add(new ItemStack(MizuBlocks.FORGE));

		return drops;
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		playerIn.openGui(MizuG.instance, MizuGuiHandler.forgeGuiID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		if (!(entityIn instanceof MizuSitEntity)) {
			List<AxisAlignedBB> list = getCollisionBoxList(this.getActualState(state, worldIn, pos));
			for (AxisAlignedBB box : list) {
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, box);
			}
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(FACING, placer.getHorizontalFacing());
		return state;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return MizuSit.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
	}

	private List<AxisAlignedBB> getCollisionBoxList(IBlockState state) {
		List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		switch (facing) {
		case NORTH:
			list.add(FORGE_NORTH);
			break;
		case SOUTH:
			list.add(FORGE_SOUTH);
			break;
		case WEST:
			list.add(FORGE_WEST);
			break;
		default:
			list.add(FORGE_EAST);
			break;
		}
		return list;
	}

	@Override
	public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start,
			Vec3d end) {
		List<RayTraceResult> list = Lists.<RayTraceResult>newArrayList();

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

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}



}

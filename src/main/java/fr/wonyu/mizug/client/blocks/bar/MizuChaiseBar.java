package fr.wonyu.mizug.client.blocks.bar;

import java.util.List;

import com.google.common.collect.Lists;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.entity.MizuSitEntity;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuSit;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MizuChaiseBar extends BlockHorizontal implements IHasModel{

	public static final String CHAISE_BAR = "chaise_bar";
	
    public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.2, 1.0);

    public static final AxisAlignedBB CHAIR_SEAT = new AxisAlignedBB(1.6 * 0.0625, 12 * 0.0625, 1.6 * 0.0625, 14.4 * 0.0625, 18.6 * 0.0625, 14.4 * 0.0625);
    public static final AxisAlignedBB[] LEGS = {new AxisAlignedBB(1.6 * 0.0625, 0, 12.8 * 0.0625, 3.2 * 0.0625, 8 * 0.0625, 14.4 * 0.0625), new AxisAlignedBB(12.8 * 0.0625, 0, 12.8 * 0.0625, 14.4 * 0.0625, 8 * 0.0625, 14.4 * 0.0625), new AxisAlignedBB(12.8 * 0.0625, 0, 1.6 * 0.0625, 14.4 * 0.0625, 8 * 0.0625, 3.2 * 0.0625), new AxisAlignedBB(1.6 * 0.0625, 0, 1.6 * 0.0625, 3.2 * 0.0625, 8 * 0.0625, 3.2 * 0.0625)};;
	public MizuChaiseBar(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, CHAISE_BAR);
		setResistance(5.0F);
		setHardness(3.0F);
		setCreativeTab(MizuTabs.mizu3d);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!playerIn.isSneaking())
        {
            if(MizuSit.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 12 * 0.0825))
            {
                worldIn.updateComparatorOutputLevel(pos, this);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_)
    {
        if(!(entityIn instanceof MizuSitEntity))
        {
            List<AxisAlignedBB> list = getCollisionBoxList(this.getActualState(state, worldIn, pos));
            for(AxisAlignedBB box : list)
            {
                super.addCollisionBoxToList(pos, entityBox, collidingBoxes, box);
            }
        }
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
        state = state.withProperty(FACING, placer.getHorizontalFacing());
        return state;
    }
    
    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return MizuSit.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
    }
    
    private List<AxisAlignedBB> getCollisionBoxList(IBlockState state)
    {
        List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
        EnumFacing facing = state.getValue(FACING);

        list.add(CHAIR_SEAT);
        for(int i = 0; i < LEGS.length; i++)
        {
            list.add(LEGS[i]);
        }
        return list;
    }

    @Override
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end)
    {
        List<RayTraceResult> list = Lists.<RayTraceResult>newArrayList();

        for(AxisAlignedBB axisalignedbb : getCollisionBoxList(this.getActualState(blockState, worldIn, pos)))
        {
            list.add(this.rayTrace(pos, start, end, axisalignedbb));
        }

        RayTraceResult raytraceresult1 = null;
        double d1 = 0.0D;

        for(RayTraceResult raytraceresult : list)
        {
            if(raytraceresult != null)
            {
                double d0 = raytraceresult.hitVec.squareDistanceTo(end);

                if(d0 > d1)
                {
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
	
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }
    
}

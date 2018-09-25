package fr.wonyu.mizug.client.blocks.container;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.entity.tiles.MizuTileBigCratesBlock;
import fr.wonyu.mizug.client.gui.MizuGuiHandler;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuBigCratesBlock extends BlockContainer implements IHasModel {

	public static final String BIG_CRATES = "big_crates_block";

	public MizuBigCratesBlock(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, BIG_CRATES);
		setCreativeTab(MizuTabs.mizucontainer);
		setHardness(2.5F);
		setSoundType(SoundType.WOOD);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new MizuTileBigCratesBlock();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		playerIn.openGui(MizuG.instance, MizuGuiHandler.bigCratesGuiID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		IInventory inventory = worldIn.getTileEntity(pos) instanceof IInventory
				? (IInventory) worldIn.getTileEntity(pos)
				: null;

		if (inventory != null) {
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				if (!inventory.getStackInSlot(i).isEmpty()) {
					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							inventory.getStackInSlot(i));

					float multiplier = 0.1f;
					float motionX = worldIn.rand.nextFloat() - 0.5f;
					float motionY = worldIn.rand.nextFloat() - 0.5f;
					float motionZ = worldIn.rand.nextFloat() - 0.5f;

					item.motionX = motionX * multiplier;
					item.motionY = motionY * multiplier;
					item.motionZ = motionZ * multiplier;

					worldIn.spawnEntity(item);
				}
			}

			inventory.clear();
		}

		super.breakBlock(worldIn, pos, state);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
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
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

}

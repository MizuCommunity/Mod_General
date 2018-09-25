package fr.wonyu.mizug.client.blocks.escalier;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuIceStairs extends BlockStairs implements IHasModel {

	public static final String ICE_STAIR = "ice_stair";

	public MizuIceStairs(Material mat, IBlockState state) {
		super(state);
		MizuBlocks.setBlockName(this, ICE_STAIR);
		setCreativeTab(MizuTabs.mizustairs);
		useNeighborBrightness = true;
        setLightOpacity(3);
        setDefaultSlipperiness(0.98F);
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
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return MapColor.ICE;
    }
    
    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}

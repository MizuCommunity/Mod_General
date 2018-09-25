package fr.wonyu.mizug.client.blocks;

import java.util.Random;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.entity.tiles.Testtile;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Test extends BlockContainer implements IHasModel {

	public static final String TEST = "test";
	Random rand;

	public Test(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, TEST);
		setResistance(5.0F);
		setHardness(2.0F);
		setCreativeTab(MizuTabs.mizu3d);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return false;
    }
	
    public boolean renderAsNormalBlock()
    {
        return false;
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new Testtile();
	}

	
}

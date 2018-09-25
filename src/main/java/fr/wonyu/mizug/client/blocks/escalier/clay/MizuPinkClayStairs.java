package fr.wonyu.mizug.client.blocks.escalier.clay;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MizuPinkClayStairs extends BlockStairs implements IHasModel {
	
	public static final String CLAY_PINK = "stair_clay_pink";

	public MizuPinkClayStairs(Material mat, IBlockState state) {
		super(state);
		MizuBlocks.setBlockName(this, CLAY_PINK);
		setCreativeTab(MizuTabs.mizustairs);
		useNeighborBrightness = true;
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}

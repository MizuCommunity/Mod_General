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

public class MizuPurpleClayStairs extends BlockStairs implements IHasModel {
	
	public static final String CLAY_PURPLE = "stair_clay_purple";

	public MizuPurpleClayStairs(Material mat, IBlockState state) {
		super(state);
		MizuBlocks.setBlockName(this, CLAY_PURPLE);
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

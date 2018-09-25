package fr.wonyu.mizug.client.blocks.ores;

import java.util.Random;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MizuCobaltOre extends Block implements IHasModel {

	public static final String COBALT_ORE = "cobalt_ore";
	Random rand;

	public MizuCobaltOre(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, COBALT_ORE);
		setResistance(5.0F);
		setHardness(5.5F);
		setCreativeTab(MizuTabs.mizuores);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}

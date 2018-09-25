package fr.wonyu.mizug.client.blocks.ores;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MizuTritiumOre extends Block implements IHasModel{

	public static final String TRITIUM_ORE = "tritium_ore";

	public MizuTritiumOre(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, TRITIUM_ORE);
		setResistance(5.0F);
		setHardness(5.0F);
		setCreativeTab(MizuTabs.mizuores);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}

package fr.wonyu.mizug.client.blocks.ores;

import java.util.Random;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MizuQuartzOre extends Block implements IHasModel {

	public static final String QUARTZ_ORE = "quartz_ore";

	public MizuQuartzOre(Material mat) {
		super(mat);
		MizuBlocks.setBlockName(this, QUARTZ_ORE);
		setResistance(5.0F);
		setHardness(3.0F);
		setCreativeTab(MizuTabs.mizuores);
		MizuBlocks.blocks.add(this);
		MizuItems.items.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(
				(IBlockState) this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
			int i = random.nextInt(fortune + 2) - 1;

			if (i < 0) {
				i = 0;
			}

			return this.quantityDropped(random) * (i + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this == MizuBlocks.QUARTZ_ORE ? Items.QUARTZ : Item.getItemFromBlock(this);
	}
	
	@Override
    public int quantityDropped(Random random)
    {
        return this == MizuBlocks.QUARTZ_ORE ? 4 + random.nextInt(5) : 1;
    }
}

package fr.wonyu.mizug.client.blocks.plants;

import java.util.Random;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MizuMaisCrop extends BlockCrops implements IHasModel {

	@Override
	protected Item getSeed()
	{
		return MizuItems.MAIS;
	}

	@Override
	protected Item getCrop()
	{
		return MizuItems.MAIS;
	}
	
	public static final String MAIS_PLANT = "mais_plant";

	public MizuMaisCrop(Material mat) {
		super();
		MizuBlocks.setBlockName(this, MAIS_PLANT);
		MizuBlocks.blocks.add(this);
	}
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
    }


	
}

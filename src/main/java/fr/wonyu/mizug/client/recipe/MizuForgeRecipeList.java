package fr.wonyu.mizug.client.recipe;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MizuForgeRecipeList
{
    private static final MizuForgeRecipeList SMELTING_BASE = new MizuForgeRecipeList();
    private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static MizuForgeRecipeList instance()
    {
        return SMELTING_BASE;
    }

    private MizuForgeRecipeList()
    {
    	
    	// MIZU
    	this.addSmeltingRecipeForBlock(MizuBlocks.BERYLLIUM_ORE, new ItemStack(MizuItems.FUSION_INGOT_BERYLLIUM), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.CUIVRE_ORE, new ItemStack(MizuItems.FUSION_INGOT_CUIVRE), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.ETAIN_ORE, new ItemStack(MizuItems.ETAIN_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.TITANE_ORE, new ItemStack(MizuItems.TITANE_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.ARGENT_ORE, new ItemStack(MizuItems.ARGENT_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.AMETYSTE_ORE, new ItemStack(MizuItems.AMETYSTE_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.MIZIUM_ORE, new ItemStack(MizuItems.MIZIUM_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.ALUMINIUM_ORE, new ItemStack(MizuItems.ALUMINIUM_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.COBALT_ORE, new ItemStack(MizuItems.COBALT_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.CUIVRE_ORE, new ItemStack(MizuItems.CUIVRE_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.FAIRITE_ORE, new ItemStack(MizuItems.FAIRITE_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.OSMIUM_ORE, new ItemStack(MizuItems.OSMIUM_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.PLATINE_ORE, new ItemStack(MizuItems.PLATINE_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.TRITIUM_ORE, new ItemStack(MizuItems.TRITIUM_INGOT), 1.0F);
    	this.addSmeltingRecipeForBlock(MizuBlocks.URANIUM_ORE, new ItemStack(MizuItems.URANIUM_INGOT), 1.0F);
    	
    	
    	// Minecraft
        this.addSmeltingRecipeForBlock(Blocks.IRON_ORE, new ItemStack(Items.IRON_INGOT), 0.7F);
        this.addSmeltingRecipeForBlock(Blocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1.0F);
        this.addSmeltingRecipeForBlock(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 1.0F);
        this.addSmeltingRecipeForBlock(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD), 1.0F);
    }

    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    public void addSmelting(Item input, ItemStack stack, float experience)
    {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getSmeltingResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }

    public ItemStack getSmeltingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.smeltingList;
    }

    public float getSmeltingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
}
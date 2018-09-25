package fr.wonyu.mizug.client.items.eating;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MizuMais extends ItemSeedFood implements IHasModel{

	public static final String MAIS = "mais_drop";
    private final int healAmount;
    private final float saturationModifier;
    private boolean alwaysEdible;
    private Potion potionId;
    private float potionEffectProbability;

    private int effectDuration, effectAmplifier;

	public MizuMais(int amount, float saturation, int duration, int amplifier, Block crops, Block soilId) {
		super(amount, saturation, crops, soilId);
		MizuItems.setItemName(this, MAIS);
		setMaxStackSize(1);
		setCreativeTab(MizuTabs.mizufoods);
        this.healAmount = amount;
        this.saturationModifier = saturation;
        this.setAlwaysEdible();
        this.alwaysEdible = true;
        this.effectAmplifier = amplifier;
        this.effectDuration = duration;
        this.setMaxStackSize(64);
        MizuItems.items.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }
	
    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return MizuBlocks.MAIS_PLANT.getDefaultState();
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), MizuBlocks.MAIS_PLANT.getDefaultState());

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
	
    public ItemStack onItemUseEnd(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {

        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(healAmount, saturationModifier);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        stack.shrink(1);
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.EAT;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
    {
        ItemStack itemstack = worldIn.getHeldItem(playerIn);

        if (worldIn.canEat(true))
        {
            worldIn.setActiveHand(playerIn);
            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult(EnumActionResult.FAIL, itemstack);
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        this.onItemUseEnd(stack, worldIn, entityLiving);
        return stack;
    }
}
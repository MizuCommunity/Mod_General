package fr.wonyu.mizug.client.items.bar;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class MizuChoppePleine extends ItemFood implements IHasModel{

	public static final String CHOPPE_PLEINE = "choppe_pleine";
    private final int healAmount;
    private final float saturationModifier;
    private boolean alwaysEdible;
    private Potion potionId;
    private float potionEffectProbability;

    private int effectDuration, effectAmplifier;

	public MizuChoppePleine(int amount, float saturation, Potion potion, int duration, int amplifier) {
		super(amount, saturation, false);
		MizuItems.setItemName(this, CHOPPE_PLEINE);
		setMaxStackSize(1);
		setCreativeTab(MizuTabs.mizuitems);
        this.healAmount = amount;
        this.saturationModifier = saturation;
        this.setAlwaysEdible();
        this.alwaysEdible = true;
        this.potionId = potion;
        this.effectAmplifier = amplifier;
        this.effectDuration = duration;
        this.setMaxStackSize(1);
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
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            player.addPotionEffect(new PotionEffect(potionId, effectDuration, effectAmplifier, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, effectDuration, effectAmplifier, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 200, 0, false, false));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 1, false, false));
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
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
        return new ItemStack(MizuItems.CHOPPE_VIDE);
    }
}
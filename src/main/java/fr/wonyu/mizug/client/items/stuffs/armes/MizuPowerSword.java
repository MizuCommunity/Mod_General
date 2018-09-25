package fr.wonyu.mizug.client.items.stuffs.armes;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class MizuPowerSword extends ItemSword implements IHasModel{

	public static final String POWER_SWORD = "power_sword";

	public MizuPowerSword(ToolMaterial material) {
		super(material);
		MizuItems.setItemName(this, POWER_SWORD);
		setCreativeTab(MizuTabs.mizuswords);
		setMaxStackSize(1);
		MizuItems.items.add(this);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 10));
        return true;
    }
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}

}

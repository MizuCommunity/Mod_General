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

public class MizuWonyuSword extends ItemSword implements IHasModel{

	public static final String WONYU_SWORD = "wonyu_sword";

	public MizuWonyuSword(ToolMaterial material) {
		super(material);
		MizuItems.setItemName(this, WONYU_SWORD);
		setCreativeTab(MizuTabs.mizuswords);
		setMaxStackSize(1);
		MizuItems.items.add(this);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		target.addPotionEffect(new PotionEffect(MobEffects.POISON, 8000, 10));
        return true;
    }
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}

}

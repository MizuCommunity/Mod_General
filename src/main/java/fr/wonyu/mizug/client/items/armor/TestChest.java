package fr.wonyu.mizug.client.items.armor;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.models.armor.plastron.PlastronTest;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TestChest extends ItemArmor implements IHasModel {
	
	public static final String TestArmor = "test_p";

	public TestChest(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		MizuItems.setItemName(this, TestArmor);
		setCreativeTab(MizuTabs.mizu3d);
		setMaxStackSize(1);
		MizuItems.itemsArmor.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped bip) {
		
		if(!stack.isEmpty()) {
			if(stack.getItem() instanceof ItemArmor) {
				
				PlastronTest plast = new PlastronTest(1.0F);
				PlastronTest plastlegs = new PlastronTest(0.5F);
				
				plast.bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;
				plast.bipedHeadwear.showModel = slot == EntityEquipmentSlot.HEAD;
				plast.bipedBody.showModel = (slot == EntityEquipmentSlot.CHEST) || (slot == EntityEquipmentSlot.CHEST);
				plast.bipedLeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
				plast.bipedRightArm.showModel = slot == EntityEquipmentSlot.CHEST;
				plastlegs.bipedRightLeg.showModel = (slot == EntityEquipmentSlot.LEGS) || (slot == EntityEquipmentSlot.LEGS);
				plastlegs.bipedLeftLeg.showModel = (slot == EntityEquipmentSlot.LEGS) || (slot == EntityEquipmentSlot.LEGS);
				

				plast.isSneak = bip.isSneak;
				plast.isRiding = bip.isRiding;
				plast.isChild = bip.isChild;
				plast.leftArmPose = bip.leftArmPose;
				plast.rightArmPose = bip.rightArmPose;

				
				return plast;
			}
		}
		
		return null;
	}

}

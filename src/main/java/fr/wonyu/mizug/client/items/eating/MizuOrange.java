package fr.wonyu.mizug.client.items.eating;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.ItemFood;

public class MizuOrange extends ItemFood implements IHasModel {
	
	public static final String ORANGE = "orange";

	public MizuOrange(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setCreativeTab(MizuTabs.mizufoods);
		MizuItems.items.add(this);
		MizuItems.setItemName(this, ORANGE);
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);		
	}

}

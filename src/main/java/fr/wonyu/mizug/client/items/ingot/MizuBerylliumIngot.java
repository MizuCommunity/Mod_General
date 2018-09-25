package fr.wonyu.mizug.client.items.ingot;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class MizuBerylliumIngot extends Item implements IHasModel{
	
	public static final String BERYLLIM = "beryllium_ingot";
	
	public MizuBerylliumIngot() {
		super();
		MizuItems.setItemName(this, BERYLLIM);
		setCreativeTab(MizuTabs.mizuingots);
		MizuItems.items.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}
}

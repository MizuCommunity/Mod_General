package fr.wonyu.mizug.client.items.ingot;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class MizuAluminiumIngot extends Item implements IHasModel {
	
	public static String ALUMINIUM_INGOT = "aluminium_ingot";
	
	public MizuAluminiumIngot() {
		super();
		setCreativeTab(MizuTabs.mizuingots);
		MizuItems.setItemName(this, ALUMINIUM_INGOT);
		MizuItems.items.add(this);
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
		
	}

}

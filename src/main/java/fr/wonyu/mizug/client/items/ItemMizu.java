package fr.wonyu.mizug.client.items;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class ItemMizu extends Item implements IHasModel{
	
	public static final String MIZU = "item_mizu";
	
	public ItemMizu() {
		super();
		MizuItems.setItemName(this, MIZU);
		setCreativeTab(MizuTabs.mizuitems);
		MizuItems.items.add(this);
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}

}

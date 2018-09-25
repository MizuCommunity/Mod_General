package fr.wonyu.mizug.client.items.stuffs;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class MizuMizite extends Item implements IHasModel {
	
	public static final String MIZITE = "mizite";
	
	public MizuMizite() {
		super();
		MizuItems.setItemName(this, MIZITE);
		setCreativeTab(MizuTabs.mizuitems);
		MizuItems.items.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}

}

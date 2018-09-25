package fr.wonyu.mizug.client.items.bar;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class MizuChoppeVide extends Item implements IHasModel{
	
	
	public static final String CHOPPE_VIDE = "choppe_vide";
	
	public MizuChoppeVide() {
		super();
		MizuItems.setItemName(this, CHOPPE_VIDE);
		setCreativeTab(MizuTabs.mizuitems);
		MizuItems.items.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}
}

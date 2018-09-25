package fr.wonyu.mizug.client.items.ingot;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class MizuFusionIngotBeryllium extends Item implements IHasModel{
	
	public static final String FUSION_INGOT_BERYLLIUM = "lingot_en_fusion_beryllium";
	
	public MizuFusionIngotBeryllium() {
		super();
		MizuItems.setItemName(this, FUSION_INGOT_BERYLLIUM);
		setCreativeTab(MizuTabs.mizuingots);
		MizuItems.items.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}
}

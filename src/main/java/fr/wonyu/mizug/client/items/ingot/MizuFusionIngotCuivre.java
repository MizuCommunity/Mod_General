package fr.wonyu.mizug.client.items.ingot;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.Item;

public class MizuFusionIngotCuivre extends Item implements IHasModel{
	
	public static final String FUSION_INGOT_CUIVRE = "lingot_en_fusion_cuivre";
	
	public MizuFusionIngotCuivre() {
		super();
		MizuItems.setItemName(this, FUSION_INGOT_CUIVRE);
		setCreativeTab(MizuTabs.mizuingots);
		MizuItems.items.add(this);
	}
	
	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}
}

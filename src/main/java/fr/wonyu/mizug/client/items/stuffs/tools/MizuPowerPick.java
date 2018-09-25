package fr.wonyu.mizug.client.items.stuffs.tools;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.IHasModel;
import fr.wonyu.mizug.client.utils.MizuTabs;
import net.minecraft.item.ItemPickaxe;

public class MizuPowerPick extends ItemPickaxe implements IHasModel {
	
	public static final String POWER_PICK = "power_pick";

	public MizuPowerPick(ToolMaterial material) {
		super(material);
		MizuItems.setItemName(this, POWER_PICK);
		setCreativeTab(MizuTabs.mizupick);
		MizuItems.items.add(this);
	}

	@Override
	public void registerModels() {
		MizuG.proxy.registerModel(this, 0);
	}

}

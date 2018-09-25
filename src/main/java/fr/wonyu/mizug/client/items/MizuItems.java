package fr.wonyu.mizug.client.items;

import java.util.ArrayList;
import java.util.List;

import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.items.armor.TestChest;
import fr.wonyu.mizug.client.items.bar.MizuChoppePleine;
import fr.wonyu.mizug.client.items.bar.MizuChoppeVide;
import fr.wonyu.mizug.client.items.eating.MizuBeurre;
import fr.wonyu.mizug.client.items.eating.MizuMais;
import fr.wonyu.mizug.client.items.eating.MizuOrange;
import fr.wonyu.mizug.client.items.ingot.MizuAluminiumIngot;
import fr.wonyu.mizug.client.items.ingot.MizuAmetysteIngot;
import fr.wonyu.mizug.client.items.ingot.MizuArgentIngot;
import fr.wonyu.mizug.client.items.ingot.MizuBerylliumIngot;
import fr.wonyu.mizug.client.items.ingot.MizuCobaltIngot;
import fr.wonyu.mizug.client.items.ingot.MizuCuivreIngot;
import fr.wonyu.mizug.client.items.ingot.MizuEtainIngot;
import fr.wonyu.mizug.client.items.ingot.MizuFairiteIngot;
import fr.wonyu.mizug.client.items.ingot.MizuFusionIngotBeryllium;
import fr.wonyu.mizug.client.items.ingot.MizuFusionIngotCuivre;
import fr.wonyu.mizug.client.items.ingot.MizuMiziumIngot;
import fr.wonyu.mizug.client.items.ingot.MizuOsmiumIngot;
import fr.wonyu.mizug.client.items.ingot.MizuPlatineIngot;
import fr.wonyu.mizug.client.items.ingot.MizuTitaneIngot;
import fr.wonyu.mizug.client.items.ingot.MizuTritiumIngot;
import fr.wonyu.mizug.client.items.ingot.MizuUraniumIngot;
import fr.wonyu.mizug.client.items.stuffs.MizuMizite;
import fr.wonyu.mizug.client.items.stuffs.armes.MizuPowerSword;
import fr.wonyu.mizug.client.items.stuffs.armes.MizuWonyuSword;
import fr.wonyu.mizug.client.items.stuffs.tools.MizuPowerPick;
import fr.wonyu.mizug.client.utils.MizuMaterials;
import fr.wonyu.mizug.client.utils.References;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MizuItems {
	
	public static final ArmorMaterial FLYARMOR = EnumHelper.addArmorMaterial("FlyArmor", "mizug:armordd", 40, new int[]{1,5,3,1}, 20, SoundEvents.BLOCK_ANVIL_LAND, 0.0f);
	
	public static final List<Item> items = new ArrayList<Item>();
	public static final List<ItemArmor> itemsArmor = new ArrayList<ItemArmor>();
	
	    public static MizuMaterials tool;
		public static Item MIZU = new ItemMizu();
		public static Item MIZITE = new MizuMizite();
		public static Item CHOPPE_VIDE = new MizuChoppeVide();
		public static Item CHOPPE_PLEINE = new MizuChoppePleine(2, 1, MobEffects.NAUSEA, 200, 0);
		public static Item POWER_SWORD = new MizuPowerSword(tool.ADMIN);
		public static Item POWER_PICK = new MizuPowerPick(tool.ADMIN);
		public static Item WONYU_SWORD = new MizuWonyuSword(tool.ADMINPOISON);
				
		// FOOD SEEDS
		public static Item MAIS = new MizuMais(2, 2, 200, 0, MizuBlocks.MAIS_PLANT, Blocks.FARMLAND);
		
		// FOODS
		public static Item ORANGE = new MizuOrange(2, 0.3F, false);
		public static Item BEURRE = new MizuBeurre(2, 1.5F, false);
		
		// ARMOR
		public static Item Chest = new TestChest(FLYARMOR, 1, EntityEquipmentSlot.CHEST);
		
		
		// INGOTS
		public static Item BERYLLIUM_INGOT = new MizuBerylliumIngot();
		public static Item FUSION_INGOT_BERYLLIUM = new MizuFusionIngotBeryllium();
		public static Item FUSION_INGOT_CUIVRE = new MizuFusionIngotCuivre();
		public static Item TITANE_INGOT = new MizuTitaneIngot();
		public static Item MIZIUM_INGOT = new MizuMiziumIngot();
		public static Item ETAIN_INGOT = new MizuEtainIngot();
		public static Item ARGENT_INGOT = new MizuArgentIngot();
		public static Item ALUMINIUM_INGOT = new MizuAluminiumIngot();
		public static Item AMETYSTE_INGOT = new MizuAmetysteIngot();
		public static Item COBALT_INGOT = new MizuCobaltIngot();
		public static Item CUIVRE_INGOT = new MizuCuivreIngot();
		public static Item FAIRITE_INGOT = new MizuFairiteIngot();
		public static Item OSMIUM_INGOT = new MizuOsmiumIngot();
		public static Item PLATINE_INGOT = new MizuPlatineIngot();
		public static Item TRITIUM_INGOT = new MizuTritiumIngot();
		public static Item URANIUM_INGOT = new MizuUraniumIngot();

		    
    public static void setItemName(Item item, String name)
    {
        item.setRegistryName(References.MODID, name).setUnlocalizedName(References.MODID + "." + name);
    }
    


}

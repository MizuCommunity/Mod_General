package fr.wonyu.mizug.client.utils;

import fr.wonyu.mizug.client.blocks.MizuBlocks;
import fr.wonyu.mizug.client.entity.tiles.MizuForgeTile;
import fr.wonyu.mizug.client.entity.tiles.MizuTileBigCratesBlock;
import fr.wonyu.mizug.client.entity.tiles.MizuTileCratesBlock;
import fr.wonyu.mizug.client.entity.tiles.MizuTileTirroirs;
import fr.wonyu.mizug.client.items.MizuItems;
import fr.wonyu.mizug.client.utils.ores.generation.MizuOresGen;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class MizuRegisters {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(MizuBlocks.blocks.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(MizuItems.items.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void registerItemArmor(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(MizuItems.itemsArmor.toArray(new ItemArmor[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent e) {
		for (Block block : MizuBlocks.blocks) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
		for (Item items : MizuItems.items) {
			if (items instanceof IHasModel) {
				((IHasModel) items).registerModels();
			}
		}
		
		for (Item items : MizuItems.itemsArmor) {
			if (items instanceof IHasModel) {
				((IHasModel) items).registerModels();
			}
		}

	}

	public static void registryGen() {
		GameRegistry.registerWorldGenerator(new MizuOresGen(), 0);
	}

	public static void registryTile() {
		GameRegistry.registerTileEntity(MizuForgeTile.class, "container.mizu_furnace.name");
		GameRegistry.registerTileEntity(MizuTileCratesBlock.class, "container.mizu_crates.name");
		GameRegistry.registerTileEntity(MizuTileBigCratesBlock.class, "container.mizu_big_crates.name");
		GameRegistry.registerTileEntity(MizuTileTirroirs.class, "container.mizu_tirroirs.name");
	}

	@SubscribeEvent
	public static void onPlayerTick(LivingUpdateEvent e) {

		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			InventoryPlayer inv = player.inventory;

			if (inv.hasItemStack(new ItemStack(MizuBlocks.BERYLLIUM_ORE))) {
				player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 500, 0));
			}

		}

	}


}

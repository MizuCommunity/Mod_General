package fr.wonyu.mizug.proxy;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;

import fr.wonyu.mizug.MizuG;
import fr.wonyu.mizug.client.blocks.plants.tree.leaf.MizuAppleLeaf;
import fr.wonyu.mizug.client.event.MizuOverlayEvents;
import fr.wonyu.mizug.client.utils.References;
import fr.wonyu.mizug.client.utils.inventory.MizuInventoryCosArmor;
import fr.wonyu.mizug.client.utils.inventory.PlayerRenderHandler;
import fr.wonyu.mizug.client.utils.network.packet.PacketOpenCosArmorInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class MizuClient extends MizuCommon {

	public static final Map<Item, ModelBiped> MIZU_ARMOR_MODELS = new HashMap<Item, ModelBiped>();

	@Override
	public void preInit(File configFile) {
		super.preInit(configFile);
		MinecraftForge.EVENT_BUS.register(new MizuOverlayEvents());
		OBJLoader.INSTANCE.addDomain(References.MODID);
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void registerModel(Item item, int metadata) {
		if (metadata < 0)
			metadata = 0;
		String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
		if (metadata > 0)
			resourceName += "_m" + String.valueOf(metadata);

		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(resourceName, "inventory"));
	}

	@Override
	public void registerModelObj(Item item, int metadata) {
		if (metadata < 0)
			metadata = 0;
		String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
		if (metadata > 0)
			resourceName += "_m" + String.valueOf(metadata);

		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceName, "normal"));
	}

	@Override
	public void setAppleGraphicsLevel(MizuAppleLeaf parBlock, boolean parFancyEnabled) {
		super.setAppleGraphicsLevel(parBlock, parFancyEnabled);
		parBlock.setGraphicsLevel(parFancyEnabled);
	}

	LoadingCache<UUID, MizuInventoryCosArmor> cacheClient = CacheBuilder.newBuilder()
			.build(new CacheLoader<UUID, MizuInventoryCosArmor>() {

				@Override
				public MizuInventoryCosArmor load(UUID owner) throws Exception {
					return new MizuInventoryCosArmor();
				}

			});

	Map<UUID, UUID> map = Maps.newHashMap();

	@Override
	public MizuInventoryCosArmor getCosArmorInventoryClient(UUID uuid) {
		if (map.isEmpty()) {
			Minecraft mc = FMLClientHandler.instance().getClient();
			if (mc.player != null)
				map.put(UUID.nameUUIDFromBytes(
						("OfflinePlayer:" + mc.player.getGameProfile().getName()).getBytes(StandardCharsets.UTF_8)),
						mc.player.getUniqueID());
		}
		if (map.containsKey(uuid))
			uuid = map.get(uuid);
		return cacheClient.getUnchecked(uuid);
	}

	@SubscribeEvent
	public void handleEvent(ClientDisconnectionFromServerEvent event) {
		PlayerRenderHandler.HideCosArmor = false;
		cacheClient.invalidateAll();
		map.clear();
	}

	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if (event.getGui() instanceof GuiInventory) {
			if (!player.isCreative()) {
				event.setCanceled(true);

				MizuG.network.sendToServer(new PacketOpenCosArmorInventory());
			}
		}
	}

}

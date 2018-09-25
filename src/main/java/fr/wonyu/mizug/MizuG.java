package fr.wonyu.mizug;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import baubles.client.gui.GuiEvents;
import de.matthiasmann.twl.utils.PNGDecoder;
import fr.wonyu.mizug.client.entity.MizuListEntity;
import fr.wonyu.mizug.client.event.KeyHandler;
import fr.wonyu.mizug.client.gui.MizuGuiHandler;
import fr.wonyu.mizug.client.gui.inventory.MizuInvGuiHandler;
import fr.wonyu.mizug.client.utils.MizuRegisters;
import fr.wonyu.mizug.client.utils.ModConfigs;
import fr.wonyu.mizug.client.utils.References;
import fr.wonyu.mizug.client.utils.inventory.PlayerRenderHandler;
import fr.wonyu.mizug.client.utils.network.NetworkManager;
import fr.wonyu.mizug.client.utils.network.packet.PacketOpenCosArmorInventory;
import fr.wonyu.mizug.client.utils.network.packet.PacketOpenNormalInventory;
import fr.wonyu.mizug.client.utils.network.packet.PacketSetSkinArmor;
import fr.wonyu.mizug.client.utils.network.packet.PacketSyncCosArmor;
import fr.wonyu.mizug.commands.CommandClearCosArmor;
import fr.wonyu.mizug.proxy.MizuCommon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class MizuG {

	@Instance(References.MODID)
	public static MizuG instance;
	public static Logger logger;

	@SideOnly(Side.CLIENT)
	public static KeyHandler keyHandler;

	@SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY)
	public static MizuCommon proxy;

	public String[] ICON_PATHS = { "/assets/mizug/textures/gui/icon_16x16.png",
			"/assets/mizug/textures/gui/icon_32x32.png" };

	public static final NetworkManager network = new NetworkManager("wonyu|nm|cos");

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
		proxy.preInit(e.getSuggestedConfigurationFile());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new MizuGuiHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new MizuInvGuiHandler());
		MizuRegisters.registryGen();
		MizuRegisters.registryTile();

		if (e.getSide() == Side.CLIENT) {
			Display.setTitle("Mizu || Community");
			ByteBuffer[] icon_array = new ByteBuffer[ICON_PATHS.length];
			try {
				for (int i = 0; i < ICON_PATHS.length; i++) {
					icon_array[i] = ByteBuffer.allocateDirect(1);
					String path = ICON_PATHS[i];
					icon_array[i] = loadIcon(path);
				}
			} catch (IOException event) {
				event.printStackTrace();
			}
			Display.setIcon(icon_array);
		}

		network.registerPacket(1, PacketSyncCosArmor.class);
		network.registerPacket(2, PacketSetSkinArmor.class);
		network.registerPacket(3, PacketOpenCosArmorInventory.class);
		network.registerPacket(4, PacketOpenNormalInventory.class);

		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		ModConfigs.loadConfigs(config);

		if (e.getSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(new PlayerRenderHandler());
			MinecraftForge.EVENT_BUS.register(keyHandler = new KeyHandler());
			MinecraftForge.EVENT_BUS.register(new GuiEvents());
		}

		MinecraftForge.EVENT_BUS.register(proxy);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init();
		MizuListEntity.load();
	}

	public static Logger logger() {
		return logger;
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandClearCosArmor());

		proxy.onServerStarting();
	}

	@EventHandler
	public void onServerStopping(FMLServerStoppingEvent event) {
		proxy.onServerStopping();
	}

	public ByteBuffer loadIcon(String path) throws IOException {
		InputStream inputStream = getClass().getResourceAsStream(path);
		try {
			PNGDecoder decoder = new PNGDecoder(inputStream);
			ByteBuffer buffer = ByteBuffer.allocate(decoder.getWidth() * decoder.getHeight() * 4);
			decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);

			buffer.flip();
			return buffer;
		} finally {
			inputStream.close();
		}
	}

}

package fr.wonyu.mizug.client.event;

import fr.wonyu.mizug.client.utils.Direction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MizuOverlayEvents extends Gui {

	public static final Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void mizuOverlayLoad(RenderGameOverlayEvent.Pre e) {
		//mizuOverlayType(e);
	}

	public void mizuOverlayType(RenderGameOverlayEvent e) {
		if (e.getType().equals(ElementType.DEBUG)) {
			e.setCanceled(true);
			mc.mcProfiler.startSection("fps");
			drawString(mc.getMinecraft().fontRenderer,
					 TextFormatting.BLACK + "[" + TextFormatting.RED + "FPS" + TextFormatting.BLACK + "] " + TextFormatting.RED + ": " + TextFormatting.RESET + TextFormatting.BOLD + mc.debug.split(",", 2)[0], 10,
					10, 16777215);
			int angle = MathHelper.floor((double) (mc.player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			String direction = Direction.directions[angle];
			String var5 = "     ";
			String var4 = TextFormatting.BLACK + "[" + TextFormatting.BLUE + "Mizu || Community" + TextFormatting.BLACK +"]";
			String var3 = TextFormatting.BLACK + "[" + TextFormatting.GOLD +"X" + TextFormatting.BLACK +"]" + TextFormatting.BLACK + ": " + TextFormatting.RED + mc.player.posX;
			String var2 = TextFormatting.BLACK + "[" + TextFormatting.GOLD +"Y" + TextFormatting.BLACK +"]" + TextFormatting.BLACK + ": " + TextFormatting.RED + mc.player.posY;
			String var1 = TextFormatting.BLACK + "[" + TextFormatting.GOLD +"Z" + TextFormatting.BLACK +"]" + TextFormatting.BLACK + ": " + TextFormatting.RED + mc.player.posZ;
			String var0 = TextFormatting.BLACK + "[" + TextFormatting.BLUE + "Bienvenue : " + TextFormatting.GOLD + mc.player.getName() + TextFormatting.BLACK + "]";
			drawString(mc.fontRenderer, var4, 10, 50, 16777215);
			drawString(mc.fontRenderer, var3, 10, 85, 16777215);
			drawString(mc.fontRenderer, var2, 10, 95, 16777215);
			drawString(mc.fontRenderer, var1, 10, 105, 16777215);
			drawString(mc.fontRenderer, var0, 10, 65, 16777215);
			mc.mcProfiler.endSection();
		}
	}

}

package fr.wonyu.mizug.client.gui;

import java.awt.Color;

import fr.wonyu.mizug.client.entity.tiles.MizuTileCratesBlock;
import fr.wonyu.mizug.client.entity.tiles.MizuTileTirroirs;
import fr.wonyu.mizug.client.gui.container.MizuContainerCratesBlock;
import fr.wonyu.mizug.client.gui.container.MizuContainerTirroirs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MizuGuiTirroirs extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation("mizug", "textures/gui/tirroirs_gui.png");
	private MizuTileTirroirs tileEntityInventoryBasic;

	public MizuGuiTirroirs(InventoryPlayer invPlayer, MizuTileTirroirs tile) {
		super(new MizuContainerTirroirs(invPlayer, tile));
		tileEntityInventoryBasic = tile;
		xSize = 176;
		ySize = 221;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRenderer.drawString(tileEntityInventoryBasic.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
	}
}
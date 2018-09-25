package fr.wonyu.mizug.client.gui;

import java.awt.Color;

import fr.wonyu.mizug.client.entity.tiles.MizuTileCratesBlock;
import fr.wonyu.mizug.client.gui.container.MizuContainerCratesBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MizuGuiCratesBlock extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation("mizug", "textures/gui/crates_block.png");
	private MizuTileCratesBlock tileEntityInventoryBasic;

	public MizuGuiCratesBlock(InventoryPlayer invPlayer, MizuTileCratesBlock tile) {
		super(new MizuContainerCratesBlock(invPlayer, tile));
		tileEntityInventoryBasic = tile;
		xSize = 176;
		ySize = 168;
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
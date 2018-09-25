package fr.wonyu.mizug.client.gui;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import fr.wonyu.mizug.client.gui.container.MizuContainerCompactor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MizuGuiCompactor extends GuiContainer {

    private static final ResourceLocation backgroundResourceLocation = new ResourceLocation("textures/gui/container/crafting_table.png");

    public MizuGuiCompactor(InventoryPlayer playerInventory, World world, BlockPos pos) {
        super(new MizuContainerCompactor(playerInventory, world, pos));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
     	final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRenderer.drawString("container.compactor", LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float arg0, int arg1, int arg2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(backgroundResourceLocation);

        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
    }

}

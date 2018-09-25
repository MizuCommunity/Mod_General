package fr.wonyu.mizug.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class MizuGuiCosArmorToggleButton extends GuiButton
{

    public int state = 0;

    public MizuGuiCosArmorToggleButton(int arg0, int arg1, int arg2, int arg3, int arg4, String arg5)
    {
        super(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y, float f)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(MizuGuiCosArmorInventory.texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            hovered = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            this.drawTexturedModalRect(this.x, this.y, 0 + 5 * state, 176, 5, 5);

            this.mouseDragged(mc, x, y);
        }
    }

}

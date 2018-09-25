package fr.wonyu.mizug.client.entity.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.wonyu.mizug.client.entity.tiles.Testtile;
import fr.wonyu.mizug.client.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;

public class TestTesr extends TileEntitySpecialRenderer<Testtile> {

	private IModel modelBall;
	private IBakedModel bakedModelBall;

	ResourceLocation texture = new ResourceLocation(References.MODID, "textures/blocks/snowball.png");

	private IBakedModel getBakedModelBall() {

		if (bakedModelBall == null) {
			try {
				modelBall = ModelLoaderRegistry
						.getModel(new ResourceLocation(References.MODID, "block/test"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			bakedModelBall = modelBall.bake(TRSRTransformation.identity(), DefaultVertexFormats.BLOCK,
					location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
		}
		return bakedModelBall;
	}

	@Override
	public void render(Testtile entity, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {

		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glScalef(0.09375F, 0.09375F, 0.09375F);
		GL11.glPopMatrix();
	}

}

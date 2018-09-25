package fr.wonyu.mizug.client.models.armor.plastron;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PlastronTest extends ModelBiped {

	public ModelRenderer e1_PoignetGauche;
	public ModelRenderer e2_PoignetDroit;
	public ModelRenderer e3_CoudeGauche;
	public ModelRenderer e4_CoudeDroit;
	public ModelRenderer e5_PauleDroite;
	public ModelRenderer e6_PauleGauche;
	public ModelRenderer e7_BusteHaut;
	public ModelRenderer e8_BusteBas;

	public PlastronTest(float scale) {
		super(scale, 0, 64, 64);
		textureWidth = 64;
		textureHeight = 64;

		e1_PoignetGauche = new ModelRenderer(this, 0, 31);
		e1_PoignetGauche.setRotationPoint(-9F, 37.02F, -2.45F);
		e1_PoignetGauche.addBox(0F, -2.009998F, -4.768372E-07F, 5, 3, 5);
		e1_PoignetGauche.setTextureSize(64, 64);
		e1_PoignetGauche.mirror = false;
		setRotation(e1_PoignetGauche, 0F, 0F, 0F);
		e2_PoignetDroit = new ModelRenderer(this, 0, 31);
		e2_PoignetDroit.setRotationPoint(3F, 37.02F, -2.45F);
		e2_PoignetDroit.addBox(0F, -2.009998F, -4.768372E-07F, 5, 3, 5);
		e2_PoignetDroit.setTextureSize(64, 64);
		e2_PoignetDroit.mirror = false;
		setRotation(e2_PoignetDroit, 0F, 0F, 0F);
		e3_CoudeGauche = new ModelRenderer(this, 0, 31);
		e3_CoudeGauche.setRotationPoint(-9F, 42.02F, -2.45F);
		e3_CoudeGauche.addBox(0F, -2.01F, -4.768372E-07F, 5, 3, 5);
		e3_CoudeGauche.setTextureSize(64, 64);
		e3_CoudeGauche.mirror = false;
		setRotation(e3_CoudeGauche, 0F, 0F, 0F);
		e4_CoudeDroit = new ModelRenderer(this, 0, 31);
		e4_CoudeDroit.setRotationPoint(3F, 42.02F, -2.45F);
		e4_CoudeDroit.addBox(0F, -2.01F, -4.768372E-07F, 5, 3, 5);
		e4_CoudeDroit.setTextureSize(64, 64);
		e4_CoudeDroit.mirror = false;
		setRotation(e4_CoudeDroit, 0F, 0F, 0F);
		e5_PauleDroite = new ModelRenderer(this, 0, 31);
		e5_PauleDroite.setRotationPoint(3F, 47.02F, -2.45F);
		e5_PauleDroite.addBox(0F, -2.009998F, -4.768372E-07F, 5, 3, 5);
		e5_PauleDroite.setTextureSize(64, 64);
		e5_PauleDroite.mirror = false;
		setRotation(e5_PauleDroite, 0F, 0F, 0F);
		e6_PauleGauche = new ModelRenderer(this, 0, 31);
		e6_PauleGauche.setRotationPoint(-9F, 47.02F, -2.45F);
		e6_PauleGauche.addBox(0F, -2.009998F, -4.768372E-07F, 5, 3, 5);
		e6_PauleGauche.setTextureSize(64, 64);
		e6_PauleGauche.mirror = false;
		setRotation(e6_PauleGauche, 0F, 0F, 0F);
		e7_BusteHaut = new ModelRenderer(this, 0, 51);
		e7_BusteHaut.setRotationPoint(-4F, 7F, -2.5F);
		e7_BusteHaut.addBox(0F, -7.01F, -4.768372E-07F, 9, 8, 5);
		e7_BusteHaut.setTextureSize(64, 64);
		e7_BusteHaut.mirror = false;
		setRotation(e7_BusteHaut, 0F, 0F, 0F);
		e8_BusteBas = new ModelRenderer(this, 0, 39);
		e8_BusteBas.setRotationPoint(-5F, 35.02F, -2.49F);
		e8_BusteBas.addBox(0F, -6.009999F, -4.768372E-07F, 9, 7, 5);
		e8_BusteBas.setTextureSize(64, 64);
		e8_BusteBas.mirror = false;
		setRotation(e8_BusteBas, 0F, 0F, 0F);
		
		
		
		bipedBody.addChild(e7_BusteHaut);
		bipedBody.addChild(e8_BusteBas);
		
		bipedLeftArm.addChild(e5_PauleDroite);
		bipedLeftArm.addChild(e4_CoudeDroit);
		bipedLeftArm.addChild(e2_PoignetDroit);
		bipedRightArm.addChild(e6_PauleGauche);
		bipedRightArm.addChild(e3_CoudeGauche);
		bipedRightArm.addChild(e1_PoignetGauche);		
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;

	}
}

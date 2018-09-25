package fr.wonyu.mizug.client.entity;

import fr.wonyu.mizug.MizuG;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class MizuListEntity {

	public static void load() {
		EntityRegistry.registerModEntity(new ResourceLocation("mizug:mountable_block"), MizuSitEntity.class, "entity_sit", 0, MizuG.instance, 256, 20, false);
	}
	
}

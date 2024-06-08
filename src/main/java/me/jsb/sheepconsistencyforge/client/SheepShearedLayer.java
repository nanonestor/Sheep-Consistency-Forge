package me.jsb.sheepconsistencyforge.client;

import com.mojang.blaze3d.vertex.PoseStack;

import me.jsb.sheepconsistencyforge.SheepConsistencyForge;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;

public class SheepShearedLayer extends RenderLayer<Sheep, SheepModel<Sheep>> {

	private final SheepModel<Sheep> model;
	private static final ResourceLocation SKIN = new ResourceLocation(SheepConsistencyForge.MODID,"textures/entity/sheep/sheep_sheared.png");

	public SheepShearedLayer(RenderLayerParent<Sheep, SheepModel<Sheep>> context, EntityModelSet modelSet) {
		super(context);
		this.model = new SheepModel<>(modelSet.bakeLayer(ModelLayers.SHEEP));
	}

	@Override
	public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, Sheep sheepEntity, float f, float g, float h, float j, float k, float l) {
		float v;
		float w;
		float x;
		if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getContents())) {
			int n = sheepEntity.tickCount / 25 + sheepEntity.getId();
			int o = DyeColor.values().length;
			int p = n % o;
			int q = (n + 1) % o;
			float f3 = ((float) (sheepEntity.tickCount % 25) + h) / 25.0F;
			float[] afloat1 = Sheep.getColorArray(DyeColor.byId(p));
			float[] afloat2 = Sheep.getColorArray(DyeColor.byId(q));
			v = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
			w = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
			x = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
		} else {
			float[] afloat = Sheep.getColorArray(sheepEntity.getColor());
			v = afloat[0];
			w = afloat[1];
			x = afloat[2];
		}
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrixStack, vertexConsumerProvider,
				i, sheepEntity, f, g, j, k, l, h, v, w, x);
	}
}

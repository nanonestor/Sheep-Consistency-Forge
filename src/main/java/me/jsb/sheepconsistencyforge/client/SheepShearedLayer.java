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

import org.jetbrains.annotations.NotNull;


public class SheepShearedLayer extends RenderLayer<Sheep, SheepModel<Sheep>> {

	private final SheepModel<Sheep> model;
	private static final ResourceLocation SKIN = ResourceLocation.fromNamespaceAndPath(SheepConsistencyForge.MODID,"textures/entity/sheep/sheep_sheared.png");

	public SheepShearedLayer(RenderLayerParent<Sheep, SheepModel<Sheep>> context, EntityModelSet modelSet) {
		super(context);
		this.model = new SheepModel<>(modelSet.bakeLayer(ModelLayers.SHEEP));
	}

	@Override
	public void render(@NotNull PoseStack matrixStack, @NotNull MultiBufferSource vertexConsumerProvider, int i, Sheep sheepEntity, float f, float g, float h, float j, float k, float l) {
		int v;
		//int w;
		//int x;
		if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getContents())) {
			int n = sheepEntity.tickCount / 25 + sheepEntity.getId();
			int o = DyeColor.values().length;
			int p = n % o;
			int q = (n + 1) % o;
			int f3 = (int) (((float) (sheepEntity.tickCount % 25) + h) / 25.0F);

			int color1int = Sheep.getColor(DyeColor.byId(p));
			int color2int = Sheep.getColor(DyeColor.byId(q));

			v = color1int * (1 - f3) + color2int * f3;
		//	w = float1int * (1 - f3) + float2int * f3;
		//	x = float1int * (1 - f3) + float2int * f3;

		} else {
			int color_int = Sheep.getColor(sheepEntity.getColor());

			v = color_int;
		//	w = afloat_int;
		//	x = afloat_int;
		}
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrixStack, vertexConsumerProvider,
				i, sheepEntity, f, g, j, k, l, h, v);
	}


}

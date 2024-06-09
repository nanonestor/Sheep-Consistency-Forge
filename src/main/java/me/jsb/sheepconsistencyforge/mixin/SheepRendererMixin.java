package me.jsb.sheepconsistencyforge.mixin;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.jsb.sheepconsistencyforge.client.SheepShearedLayer;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.world.entity.animal.Sheep;


@OnlyIn(Dist.CLIENT)
@Mixin(SheepRenderer.class)
public abstract class SheepRendererMixin extends MobRenderer<Sheep, SheepModel<Sheep>> {

	public SheepRendererMixin(EntityRendererProvider.Context context, SheepModel<Sheep> model, float f) {
		super(context, model, f);
	}

	@Inject(at = @At("TAIL"), method = "<init>")
	private void sheep_consistency_forge_init(EntityRendererProvider.Context context, CallbackInfo info) {
		this.addLayer(new SheepShearedLayer(this, context.getModelSet()));
	}
}

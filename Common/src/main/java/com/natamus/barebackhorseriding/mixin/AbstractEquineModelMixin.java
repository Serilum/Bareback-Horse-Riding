package com.natamus.barebackhorseriding.mixin;

import com.natamus.barebackhorseriding.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AbstractEquineModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AbstractEquineModel.class, priority = 1001)
public class AbstractEquineModelMixin {
	@Shadow private @Final ModelPart[] saddleParts;

	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/EquineRenderState;)V", at = @At(value = "TAIL"))
	public void setupAnim(EquineRenderState equineRenderState, CallbackInfo ci) {
		LocalPlayer localPlayer = Minecraft.getInstance().player;
		if (localPlayer == null) {
			return;
		}

		if (localPlayer.getVehicle() instanceof AbstractHorse abstractHorse) {
			if (Util.hideSaddleModel(abstractHorse)) {
				for (ModelPart saddlePart : this.saddleParts) {
					saddlePart.visible = false;
				}
			}
		}
	}
}
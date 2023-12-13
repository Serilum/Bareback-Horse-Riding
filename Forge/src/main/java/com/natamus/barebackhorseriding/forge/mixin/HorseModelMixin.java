package com.natamus.barebackhorseriding.forge.mixin;

import com.natamus.barebackhorseriding.util.Util;
import net.minecraft.client.model.HorseModel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = HorseModel.class, priority = 1001)
public class HorseModelMixin {
	@ModifyVariable(method = "setupAnim(Lnet/minecraft/world/entity/animal/horse/AbstractHorse;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;isVehicle()Z"))
	private boolean bl_setupAnim(boolean bl, AbstractHorse abstractHorse, float f, float g, float h, float i, float j) {
		if (Util.hideSaddleModel(abstractHorse)) {
			return false;
		}
		return bl;
	}
}
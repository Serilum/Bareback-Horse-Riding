package com.natamus.barebackhorseriding.forge.mixin;

import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractHorse.class, priority = 1001)
public class AbstractHorseMixin {
	@Inject(method = "isSaddled()Z", at = @At(value = "HEAD"), cancellable = true)
	public void isSaddled(CallbackInfoReturnable<Boolean> cir) {
		AbstractHorse abstractHorse = (AbstractHorse)(Object)this;
		if (abstractHorse.isTamed() && abstractHorse.isVehicle() && abstractHorse.getPassengers().size() > 0) {
			cir.setReturnValue(true);
		}
	}
}
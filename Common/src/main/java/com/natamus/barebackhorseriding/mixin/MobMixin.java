package com.natamus.barebackhorseriding.mixin;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Mob.class, priority = 1001)
public class MobMixin {
	@Inject(method = "isSaddled", at = @At(value = "HEAD"), cancellable = true)
	public void isSaddled(CallbackInfoReturnable<Boolean> cir) {
		if (((Mob)(Object)this) instanceof AbstractHorse abstractHorse) {
			if (abstractHorse.isTamed() && abstractHorse.isVehicle() && !abstractHorse.getPassengers().isEmpty()) {
				cir.setReturnValue(true);
			}
		}
	}
}
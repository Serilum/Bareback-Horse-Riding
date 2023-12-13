package com.natamus.barebackhorseriding.events;

import com.natamus.barebackhorseriding.config.ConfigHandler;
import com.natamus.barebackhorseriding.util.Util;
import com.natamus.collective.functions.ItemFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

public class RidingEvent {
	public static void onPlayerTick(ServerLevel level, ServerPlayer player) {
		if (!ConfigHandler.shouldDamageDuringRidingWithoutSaddle) {
			return;
		}

		if (!player.isPassenger()) {
			return;
		}

		if (player.isCreative()) {
			return;
		}

		Entity vehicleEntity = player.getVehicle();
		if (!(vehicleEntity instanceof AbstractHorse)) {
			return;
		}

		AbstractHorse abstractHorse = (AbstractHorse)vehicleEntity;
		if (!abstractHorse.isTamed()) {
			return;
		}

		if (Util.isActuallyWearingASaddle(abstractHorse)) {
			return;
		}

		if (player.tickCount % ConfigHandler.ticksBetweenDamage != 0) {
			return;
		}

		if (ConfigHandler.leatherPantsNegateDamage) {
			ItemStack leggingsStack = player.getItemBySlot(EquipmentSlot.LEGS);
			if (leggingsStack.getItem() instanceof DyeableArmorItem) {
				if (ConfigHandler.leatherPantsLoseDurabilityOnNegation) {
					ItemFunctions.itemHurtBreakAndEvent(leggingsStack, player, null, 1);
				}
				return;
			}
		}

		Util.damagePlayer(player, ConfigHandler.halfHeartDamageAmount);
	}
}

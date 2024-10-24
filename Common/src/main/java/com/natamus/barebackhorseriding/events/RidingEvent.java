package com.natamus.barebackhorseriding.events;

import com.natamus.barebackhorseriding.config.ConfigHandler;
import com.natamus.barebackhorseriding.util.Util;
import com.natamus.collective.functions.ItemFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RidingEvent {
	public static void onPlayerTick(ServerLevel level, ServerPlayer player) {
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

		ItemStack legsStack = player.getItemBySlot(EquipmentSlot.LEGS);
		Item legsItem = legsStack.getItem();
		boolean wearingLeatherPants = legsItem instanceof ArmorItem && legsStack.isValidRepairItem(Items.LEATHER.getDefaultInstance());

		if (ConfigHandler.shouldReceiveSlownessDuringRidingWithoutSaddle) {
			if (player.tickCount % 20 == 0) {
				if (ConfigHandler.leatherPantsNegateEffect && wearingLeatherPants) {
					if (ConfigHandler.leatherPantsLoseDurabilityOnNegation) {
						ItemFunctions.itemHurtBreakAndEvent(player.getItemBySlot(EquipmentSlot.LEGS), player, null, 1);
					}
					return;
				}

				Util.giveSlowness(player);
			}
		}
		if (ConfigHandler.shouldDamageDuringRidingWithoutSaddle) {
			if (player.tickCount % ConfigHandler.ticksBetweenDamage == 0) {
				if (ConfigHandler.leatherPantsNegateEffect && wearingLeatherPants) {
					if (ConfigHandler.leatherPantsLoseDurabilityOnNegation) {
						ItemFunctions.itemHurtBreakAndEvent(player.getItemBySlot(EquipmentSlot.LEGS), player, null, 1);
					}
					return;
				}

				Util.damagePlayer(player, ConfigHandler.halfHeartDamageAmount);
			}
		}
	}
}

package com.natamus.barebackhorseriding.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Util {
	public static boolean isActuallyWearingASaddle(AbstractHorse abstractHorse) {
		return abstractHorse.hasItemInSlot(EquipmentSlot.SADDLE);
	}

	public static void giveSlowness(Player player) {
		player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 40, 0)));

		Entity vehicle = player.getVehicle();
		if (vehicle instanceof Horse horse) {
            horse.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 0));
		}
	}

	public static void damagePlayer(Player player, int halfheartdamage) {
		Level level = player.level();
		if (level.isClientSide()) {
			return;
		}

		ServerLevel serverLevel = (ServerLevel)level;

		float newhealth = player.getHealth() - (float)halfheartdamage;
		if (newhealth > 0f) {
			player.hurtServer(serverLevel, level.damageSources().generic(), 0.1F);
			player.setHealth(newhealth);
		}
		else {
			player.hurtServer(serverLevel, level.damageSources().generic(), Float.MAX_VALUE);
		}
	}
}

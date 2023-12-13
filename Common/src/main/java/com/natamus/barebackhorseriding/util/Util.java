package com.natamus.barebackhorseriding.util;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Util {
	public static boolean hideSaddleModel(AbstractHorse abstractHorse) {
		return !isActuallyWearingASaddle(abstractHorse);
	}

	public static boolean isActuallyWearingASaddle(AbstractHorse abstractHorse) {
		SynchedEntityData entityData = abstractHorse.getEntityData();
		for (SynchedEntityData.DataItem<?> entityDataValue : entityData.getAll()) {
			if (entityDataValue.getAccessor().getId() == 17) { // Saddle flag
				return Byte.toUnsignedInt((byte)entityDataValue.getValue()) == 6; // 6: Yes, 2: No
			}
		}
		return false;
	}

	public static void damagePlayer(Player player, int halfheartdamage) {
		Level level = player.getLevel();
		float newhealth = player.getHealth() - (float)halfheartdamage;
		if (newhealth > 0f) {
			player.hurt(DamageSource.GENERIC, 0.1F);
			player.setHealth(newhealth);
		}
		else {
			player.hurt(DamageSource.GENERIC, Float.MAX_VALUE);
		}
	}
}

package com.natamus.barebackhorseriding.util;

import com.natamus.collective.functions.EntityFunctions;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Util {
	public static boolean hideSaddleModel(AbstractHorse abstractHorse) {
		return !isActuallyWearingASaddle(abstractHorse);
	}

	public static boolean isActuallyWearingASaddle(AbstractHorse abstractHorse) {
		return EntityFunctions.getAbstractHorseEntityFlagResult(abstractHorse, 4);
	}

	public static void damagePlayer(Player player, int halfheartdamage) {
		Level level = player.level();
		float newhealth = player.getHealth() - (float)halfheartdamage;
		if (newhealth > 0f) {
			player.hurt(level.damageSources().generic(), 0.1F);
			player.setHealth(newhealth);
		}
		else {
			player.hurt(level.damageSources().generic(), Float.MAX_VALUE);
		}
	}
}

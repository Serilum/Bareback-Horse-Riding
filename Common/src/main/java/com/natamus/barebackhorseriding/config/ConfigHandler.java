package com.natamus.barebackhorseriding.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.barebackhorseriding.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean shouldReceiveSlownessDuringRidingWithoutSaddle = true;
	@Entry public static boolean shouldDamageDuringRidingWithoutSaddle = false;
	@Entry public static boolean leatherPantsNegateEffect = true;
	@Entry(min = 1, max = 72000) public static int ticksBetweenDamage = 300;
	@Entry(min = 1, max = 100) public static int halfHeartDamageAmount = 2;
	@Entry public static boolean leatherPantsLoseDurabilityOnNegation = true;

	public static void initConfig() {
		configMetaData.put("shouldReceiveSlownessDuringRidingWithoutSaddle", Arrays.asList(
			"If the player (and horse) should receive the slowness effect when riding a horse without a saddle."
		));
		configMetaData.put("shouldDamageDuringRidingWithoutSaddle", Arrays.asList(
			"If the player should be damaged when riding a horse without a saddle."
		));
		configMetaData.put("leatherPantsNegateEffect", Arrays.asList(
			"If wearing leather pants should prevent the player from being damaged or received slowness, if enabled, when riding without a saddle."
		));
		configMetaData.put("ticksBetweenDamage", Arrays.asList(
			"How often the player should be damaged, if enabled, during riding without a saddle in ticks. 1 second = 20 ticks"
		));
		configMetaData.put("halfHeartDamageAmount", Arrays.asList(
			"How much damage should be done to the player from riding without a saddle.."
		));
		configMetaData.put("leatherPantsLoseDurabilityOnNegation", Arrays.asList(
			"If leather pants should lose durability each time it prevents damage."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}
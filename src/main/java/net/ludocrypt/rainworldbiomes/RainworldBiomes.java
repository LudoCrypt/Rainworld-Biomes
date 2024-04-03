package net.ludocrypt.rainworldbiomes;

import com.mojang.serialization.Lifecycle;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryOps.RegistryInfoGetter;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

public class RainworldBiomes implements ModInitializer {

	public static final RegistryKey<Biome> SU_A15 = register("su_a15");

	@Override
	public void onInitialize() {
	}

	public static void createBiomes(RegistryInfoGetter infoGetter, MutableRegistry<Biome> registry) {
		RegistryEntryLookup<PlacedFeature> features = infoGetter
			.getRegistryInfo(RegistryKeys.PLACED_FEATURE)
			.get()
			.entryLookup();
		RegistryEntryLookup<ConfiguredCarver<?>> carvers = infoGetter
			.getRegistryInfo(RegistryKeys.CONFIGURED_CARVER)
			.get()
			.entryLookup();

		registry.add(SU_A15, createDefaulted(features, carvers), Lifecycle.stable());
	}

	public static Biome createDefaulted(RegistryEntryLookup<PlacedFeature> features,
			RegistryEntryLookup<ConfiguredCarver<?>> carvers) {
		Biome.Builder biome = new Biome.Builder();

		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

		BiomeEffects.Builder effecst = new BiomeEffects.Builder();
		effecst.waterColor(29308);
		effecst.waterFogColor(546137);
		effecst.fogColor(12578794);
		effecst.skyColor(12578794);
		effecst.grassColor(13818488);

		biome.spawnSettings(spawnSettings.build());
		biome.generationSettings(generationSettings.build());
		biome.effects(effecst.build());
		biome.precipitation(false);
		biome.temperature(0.8F);
		biome.downfall(0.0F);

		return biome.build();
	}

	private static RegistryKey<Biome> register(String name) {
		return RegistryKey.of(RegistryKeys.BIOME, id(name));
	}

	public static Identifier id(String name) {
		return new Identifier("rainworldbiomes", name);
	}

}

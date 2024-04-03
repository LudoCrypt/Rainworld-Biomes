package net.ludocrypt.rainworldbiomes.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.serialization.Decoder;

import net.ludocrypt.rainworldbiomes.RainworldBiomes;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryLoader;
import net.minecraft.registry.RegistryOps;
import net.minecraft.resource.ResourceManager;
import net.minecraft.world.biome.Biome;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {

	@SuppressWarnings("unchecked")
	@Inject(method = "Lnet/minecraft/registry/RegistryLoader;load(Lnet/minecraft/registry/RegistryOps$RegistryInfoGetter;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/MutableRegistry;Lcom/mojang/serialization/Decoder;Ljava/util/Map;)V", at = @At("TAIL"))
	private static <E> void loadRegistryContents(RegistryOps.RegistryInfoGetter registryInfoGetter,
			ResourceManager resourceManager, RegistryKey<? extends Registry<E>> registryRef, MutableRegistry<E> newRegistry,
			Decoder<E> decoder, Map<RegistryKey<?>, Exception> exceptions, CallbackInfo ci) {

		if (registryRef == RegistryKeys.BIOME) {
			RainworldBiomes.createBiomes(registryInfoGetter, (MutableRegistry<Biome>) (Object) newRegistry);
		}

	}

}

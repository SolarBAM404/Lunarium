package me.solar.lunarium;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public enum PlacedFeatures {

    STAR_METAL_ORE(RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("lunarium", "star_metal_ore")),
            BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES),
    ;

    private final RegistryKey<PlacedFeature> key;
    private final Predicate<BiomeSelectionContext> selector;

    private final GenerationStep.Feature feature;

    PlacedFeatures(RegistryKey<PlacedFeature> key, Predicate<BiomeSelectionContext> selector, GenerationStep.Feature feature) {
        this.key = key;
        this.selector = selector;
        this.feature = feature;
    }

    public void register() {
        BiomeModifications.addFeature(selector, feature, key);
    }
}

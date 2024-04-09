package me.solar.lunarium;

import me.solar.lunarium.items.StarMetal;
import me.solar.lunarium.utils.ReflectionExecutor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.ArrayList;
import java.util.List;

public class Lunarium implements ModInitializer {

    public final static Logger LOGGER = LogManager.getLogger();

    private static final List<Item> ITEMS = new ArrayList<>();

    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(StarMetal.getInstance()))
            .displayName(Text.translatable("Lunarium"))
            .entries((context, entries) -> {
                ITEMS.forEach(item -> entries.add(() -> new ItemStack(item).getItem()));
            })
            .build();

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    @Override
    public void onInitialize() {
        Configurator.setLevel(LOGGER.getName(), Level.DEBUG);
        LOGGER.info("Initializing Lunarium");

        // Tools must be registered before items
        registerEnrichableBlocks();
        ReflectionExecutor.registerAuto();
        registerPlacedFeatures();

        // Register Item Group
        Registry.register(Registries.ITEM_GROUP, new Identifier("lunarium", "item_group"), ITEM_GROUP);
    }

    private void registerEnrichableBlocks() {
        for (EnrichableBlocks enrichableBlock : EnrichableBlocks.values()) {
            enrichableBlock.register();
        }
    }

    private void registerPlacedFeatures() {
        for (PlacedFeatures placedFeature : PlacedFeatures.values()) {
            placedFeature.register();
        }
    }
}

package me.solar.lunarium;

import me.solar.lunarium.blockstates.EnrichableBlock;
import me.solar.lunarium.items.EnrichableBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public enum EnrichableBlocks {

    STAR_METAL_ORE("star_metal_ore", EnrichableBlock.ENRICHABLE_BLOCK),
    ;

    private final String id;
    private final EnrichableBlock block;

    EnrichableBlocks(String id, EnrichableBlock block) {
        this.id = id;
        this.block = block;
    }

    public void register() {
        Registry.register(Registries.BLOCK, new Identifier("lunarium", id), block);
        Item item = new EnrichableBlockItem(block, new FabricItemSettings(), false);
        block.setUnenrichedItem(item);
        block.setEnrichedItem(new EnrichableBlockItem(block, new FabricItemSettings(), true));
        Registry.register(Registries.ITEM, new Identifier("lunarium", getId()), block.getUnenrichedItem());
        Registry.register(Registries.ITEM, new Identifier("lunarium", getId() + "_enriched"), block.getEnrichedItem());

        Lunarium.registerItem(block.getUnenrichedItem());
        Lunarium.registerItem(block.getEnrichedItem());
    }

    public String getId() {
        return id;
    }
}

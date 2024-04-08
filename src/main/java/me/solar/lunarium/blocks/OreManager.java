package me.solar.lunarium.blocks;

import me.solar.lunarium.Lunarium;
import me.solar.lunarium.blockstates.EnrichableBlock;
import me.solar.lunarium.items.EnrichableBlockItem;
import me.solar.lunarium.utils.autos.IAutoRegister;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public enum OreManager implements IAutoRegister {

    STAR_METAL_ORE("star_metal_ore", 3.0f, 8, 0, 16, 4, true);

    private String id;
    private Block block;
    private int veinSize;
    private int minY;
    private int maxY;
    private int count;
    private float strength;
    private boolean isEnrichable;

    private OreManager(String id, float strength) {
        this.id = id;
        this.strength = strength;

        block = new Block(FabricBlockSettings.create().strength(strength));
    }

    OreManager(String id, float strength, int veinSize, int minY, int maxY, int count, boolean isEnrichable) {
        this.id = id;
        this.strength = strength;
        this.veinSize = veinSize;
        this.minY = minY;
        this.maxY = maxY;
        this.count = count;
        this.isEnrichable = isEnrichable;

        if (isEnrichable) {
            block = EnrichableBlock.ENRICHABLE_BLOCK;
            return;
        }

        block = new Block(FabricBlockSettings.create().strength(strength));
    }


    @Override
    public <T extends IAutoRegister> void register() {
        Registry.register(Registries.BLOCK, new Identifier("lunarium", getId()), block);
        BlockItem item;

        if (isEnrichable) {
            item = new EnrichableBlockItem(block, new FabricItemSettings(), false);
            ((EnrichableBlock) block).setUnenrichedItem(item);
            ((EnrichableBlock) block).setEnrichedItem(new EnrichableBlockItem(block, new FabricItemSettings(), true));
            Registry.register(Registries.ITEM, new Identifier("lunarium", getId() + "_enriched"), ((EnrichableBlock) block).getEnrichedItem());
        } else {
            item = new BlockItem(block, new FabricItemSettings());
        }
        Registry.register(Registries.ITEM, new Identifier("lunarium", getId()), item);
        Lunarium.registerItem(block.asItem());
    }

    @Override
    public String getId() {
        return id;
    }

    public Block getBlock() {
        return block;
    }
}

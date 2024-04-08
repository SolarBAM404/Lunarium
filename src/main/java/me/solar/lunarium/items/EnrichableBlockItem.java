package me.solar.lunarium.items;

import me.solar.lunarium.blockstates.EnrichableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;

public class EnrichableBlockItem extends BlockItem {
    private final boolean isEnriched;

    public EnrichableBlockItem(Block block, Settings settings, boolean isEnriched) {
        super(block, settings);
        this.isEnriched = isEnriched;
    }

    @Override
    protected boolean place(ItemPlacementContext context, BlockState state) {
        return super.place(context, state.with(EnrichableBlock.ENRICHED, isEnriched));
    }
}

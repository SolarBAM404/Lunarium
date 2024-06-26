package me.solar.lunarium.blockstates;

import me.solar.lunarium.blocks.OreManager;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

import java.util.Collections;
import java.util.List;

public class EnrichableBlock extends Block {
    public static final IntProperty STAR_POWER = IntProperty.of("star_power", 0, 6000);
    public static final BooleanProperty ENRICHED = BooleanProperty.of("enriched");

    public static final EnrichableBlock ENRICHABLE_BLOCK = new EnrichableBlock(FabricBlockSettings.copyOf(Blocks.STONE).requiresTool());

    private Item unenrichedItem;
    private Item enrichedItem;

    private EnrichableBlock(Settings settings) {
        super(settings.ticksRandomly());
        setDefaultState(getDefaultState().with(ENRICHED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ENRICHED);
        builder.add(STAR_POWER);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(state.get(ENRICHED) ? enrichedItem : unenrichedItem);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        EnrichableBlock block = (EnrichableBlock) OreManager.STAR_METAL_ORE.getBlock();
        return Collections.singletonList(new ItemStack(state.get(ENRICHED) ? block.enrichedItem : block.unenrichedItem));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isSkyVisible(pos.add(0, 1, 0))) {
            int starPower = state.get(STAR_POWER);
            if (state.get(ENRICHED)) {
                return;
            }
            if (starPower < 7) {
                world.setBlockState(pos, state.with(STAR_POWER, starPower + 1));
            } else {
                // Replace with your enriched block
                world.setBlockState(pos, state.with(ENRICHED, true));
            }
        }
    }

    public Item getEnrichedItem() {
        return enrichedItem;
    }

    public void setEnrichedItem(Item enrichedItem) {
        this.enrichedItem = enrichedItem;
    }

    public Item getUnenrichedItem() {
        return unenrichedItem;
    }

    public void setUnenrichedItem(Item unenrichedItem) {
        this.unenrichedItem = unenrichedItem;
    }
}

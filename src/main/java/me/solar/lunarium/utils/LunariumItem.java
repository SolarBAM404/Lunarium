package me.solar.lunarium.utils;

import me.solar.lunarium.Lunarium;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.registry.Registries.ITEM;

public abstract class LunariumItem extends Item {

    protected LunariumItem(Settings settings) {
        super(settings);
    }

    public <T extends LunariumItem> void register() {
        Lunarium.LOGGER.info("Registering item: " + getId());
        Registry.register(ITEM, new Identifier("lunarium", getId()), this);
    }

    protected abstract String getId();

    public abstract Text getName(ItemStack stack);

}

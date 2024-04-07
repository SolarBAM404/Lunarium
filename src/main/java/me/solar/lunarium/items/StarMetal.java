package me.solar.lunarium.items;

import me.solar.lunarium.utils.LunariumItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StarMetal extends LunariumItem {

    private static StarMetal INSTANCE = new StarMetal();
    private static Logger LOGGER = LogManager.getLogger();

    private static final String ID = "star_metal";
    private static final String NAME = "Star Metal";
    private static final String DESCRIPTION = "A rare metal that shines like the stars.";

    private StarMetal() {
        super(new Item.Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(NAME);
    }

    public static StarMetal getInstance() {
        return INSTANCE;
    }

    @Override
    protected String getId() {
        return "star_metal";
    }
}

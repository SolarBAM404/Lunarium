package me.solar.lunarium.items;

import me.solar.lunarium.utils.autos.IAutoRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.registry.Registries.ITEM;

public class StarMetal extends Item implements IAutoRegister {

    public static final StarMetal INSTANCE = new StarMetal();
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
    public <T extends IAutoRegister> void register() {
        Registry.register(ITEM, new Identifier("lunarium", getId()), this);
    }

    @Override
    public String getId() {
        return "star_metal";
    }
}

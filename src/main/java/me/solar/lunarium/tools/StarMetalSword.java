package me.solar.lunarium.tools;

import me.solar.lunarium.Lunarium;
import me.solar.lunarium.items.StarMetal;
import me.solar.lunarium.materials.StarMetalMaterial;
import me.solar.lunarium.utils.autos.IAutoRegister;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class StarMetalSword extends SwordItem implements IAutoRegister {

    public final static StarMetalSword INSTANCE = new StarMetalSword();

    private StarMetalSword() {
        super(StarMetalMaterial.INSTANCE, 8, 6, new Item.Settings());
    }

    @Override
    public <T extends IAutoRegister> void register() {
        Registry.register(Registries.ITEM, new Identifier("lunarium", getId()), this);
        Lunarium.registerItem(this);
    }

    public String getId() {
        return "star_metal_sword";
    }

    @Override
    public Text getName() {
        return Text.translatable("Star Metal Sword");
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        user.playSound(SoundEvents.ITEM_TRIDENT_HIT, 5.0F, 1.0F);
        return super.useOnEntity(stack, user, entity, hand);
    }
}

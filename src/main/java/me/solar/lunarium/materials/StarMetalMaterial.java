package me.solar.lunarium.materials;

import me.solar.lunarium.items.StarMetal;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class StarMetalMaterial implements ToolMaterial {

    public static final StarMetalMaterial INSTANCE = new StarMetalMaterial();

    @Override
    public int getDurability() {
        return 10000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 8;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(StarMetal.getInstance());
    }
}

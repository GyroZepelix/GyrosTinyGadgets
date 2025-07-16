package com.dgjalic.gyrostinygadgets.datagen;

import com.dgjalic.gyrostinygadgets.item.ItemRegistry;
import com.dgjalic.gyrostinygadgets.item.PitchforkItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        pitchforkItem(pWriter, ItemRegistry.IRON_PITCHFORK.get(), Items.IRON_INGOT, Items.IRON_NUGGET, Items.STICK);
        pitchforkItem(pWriter, ItemRegistry.WOODEN_PITCHFORK.get(), Items.STICK, Items.STICK, Items.STICK);
    }

    private void pitchforkItem(Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull PitchforkItem pitchforkItem, Item ingredientItem, Item secondaryIngredientItem, Item stickItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pitchforkItem)
                .pattern(" NX")
                .pattern(" SN")
                .pattern("S  ")
                .define('X', ingredientItem)
                .define('N', secondaryIngredientItem)
                .define('S', stickItem)
                .unlockedBy(getHasName(ingredientItem), has(ingredientItem))
                .save(pFinishedRecipeConsumer);
    }

}

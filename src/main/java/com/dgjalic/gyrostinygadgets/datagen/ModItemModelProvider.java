package com.dgjalic.gyrostinygadgets.datagen;

import com.dgjalic.gyrostinygadgets.GyrosTinyGadgets;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GyrosTinyGadgets.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }

    private <T extends Item> ItemModelBuilder simpleItem(RegistryObject<T> item) {
        return withExistingParent(
                    item.getId().getPath(),
                    ResourceLocation.parse("item/generated")
                ).texture(
                    "layer0",
                    ResourceLocation.fromNamespaceAndPath(GyrosTinyGadgets.MOD_ID, "item/" + item.getId().getPath())
                );
    }
}

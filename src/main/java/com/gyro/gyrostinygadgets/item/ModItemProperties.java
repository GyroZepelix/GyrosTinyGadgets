package com.gyro.gyrostinygadgets.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        addSmokeableItemProperties(ModItems.CIGAR.get());
        addSmokeableItemProperties(ModItems.SMOKE_PIPE.get());
    }

    private static void addSmokeableItemProperties(SmokeableItem item) {
        ItemProperties.register(item, new ResourceLocation("using_lit"), (itemStack, clientLevel, livingEntity, p_174603_) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
        ItemProperties.register(item, new ResourceLocation("lit"), (itemStack, clientLevel, livingEntity, p_174603_) -> {
            return SmokeableItem.isLit.apply(itemStack) ? 1.0F : 0.0F;
        });
    }
}

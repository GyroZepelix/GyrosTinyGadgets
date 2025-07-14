package com.dgjalic.gyrostinygadgets.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        addSmokeableItemProperties(ItemRegistry.CIGAR.get());
        addSmokeableItemProperties(ItemRegistry.SMOKE_PIPE.get());
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

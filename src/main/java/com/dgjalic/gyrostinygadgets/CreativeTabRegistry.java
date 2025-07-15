package com.dgjalic.gyrostinygadgets;

import com.dgjalic.gyrostinygadgets.item.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GyrosTinyGadgets.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ARMORY_TAB = REGISTRY.register("tiny_gadgets_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemRegistry.IRON_HAMMER.get()))
                    .title(Component.translatable("creativetab.tiny_gadgets_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        for (RegistryObject<Item> item: ItemRegistry.ITEMS.getEntries()) {
                            pOutput.accept(item.get());
                        }
                    })
                    .build()
    );
}
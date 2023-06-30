package com.gyro.gyrostinygadgets.item;


import com.gyro.gyrostinygadgets.GadgetsTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gyro.gyrostinygadgets.GyrosTinyGadgets.GADGETS_TAB;
import static com.gyro.gyrostinygadgets.GyrosTinyGadgets.MODID;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<PickaxeItem> SMALL_HAMMER = ITEMS.register("small_hammer", () -> new PickaxeItem(Tiers.IRON, 3, -2.4F, new Item.Properties().tab(GADGETS_TAB)));
    public static final RegistryObject<PickaxeItem> HAMMER = ITEMS.register("hammer", () -> new PickaxeItem(Tiers.IRON, 7, -3.4F, new Item.Properties().tab(GADGETS_TAB)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}

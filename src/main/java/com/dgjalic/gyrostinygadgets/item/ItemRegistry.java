package com.dgjalic.gyrostinygadgets.item;


import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

import static com.dgjalic.gyrostinygadgets.GyrosTinyGadgets.MOD_ID;
import static net.minecraft.world.item.Items.GREEN_DYE;


public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<PickaxeItem> SMALL_IRON_HAMMER = ITEMS.register("small_iron_hammer", () -> new PickaxeItem(Tiers.IRON, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new PickaxeItem(Tiers.IRON, 6, -3.1F, new Item.Properties()));
    public static final RegistryObject<PickaxeItem> WOODEN_HAMMER = ITEMS.register("wooden_hammer", () -> new PickaxeItem(Tiers.WOOD, 6, -3.2F, new Item.Properties()));

    public static final RegistryObject<SmokeableItem> CIGAR = ITEMS.register("cigar", () -> new SmokeableItem(new Item.Properties().defaultDurability(1200), Optional.empty()));

    public static final RegistryObject<SmokeableItem> SMOKE_PIPE = ITEMS.register("smoke_pipe", () -> new SmokeableItem(new Item.Properties().defaultDurability(1200), Optional.of(GREEN_DYE)));

    public static final RegistryObject<SwordItem> COPPER_WRENCH = ITEMS.register("copper_wrench", () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> DRIED_CACTUS = ITEMS.register("dried_cactus", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}

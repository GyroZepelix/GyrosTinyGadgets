package com.gyro.gyrostinygadgets.item;


import com.gyro.gyrostinygadgets.GadgetsTab;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

import static com.gyro.gyrostinygadgets.GyrosTinyGadgets.GADGETS_TAB;
import static com.gyro.gyrostinygadgets.GyrosTinyGadgets.MODID;
import static net.minecraft.world.item.Items.GREEN_DYE;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<PickaxeItem> SMALL_HAMMER = ITEMS.register("small_hammer", () -> new PickaxeItem(Tiers.IRON, 3, -2.4F, new Item.Properties().tab(GADGETS_TAB)));
    public static final RegistryObject<PickaxeItem> HAMMER = ITEMS.register("hammer", () -> new PickaxeItem(Tiers.IRON, 6, -3.1F, new Item.Properties().tab(GADGETS_TAB)));

    public static final RegistryObject<SmokeableItem> CIGAR = ITEMS.register("cigar", () -> new SmokeableItem(new Item.Properties().tab(GADGETS_TAB).defaultDurability(1200), Optional.empty()));

    public static final RegistryObject<SmokeableItem> SMOKE_PIPE = ITEMS.register("smoke_pipe", () -> new SmokeableItem(new Item.Properties().tab(GADGETS_TAB).defaultDurability(1200), Optional.of(GREEN_DYE)));

    public static final RegistryObject<SwordItem> COPPER_WRENCH = ITEMS.register("copper_wrench", () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties().tab(GADGETS_TAB)));

    public static final RegistryObject<Item> DRIED_CACTUS = ITEMS.register("dried_cactus", () -> new Item(new Item.Properties().tab(GADGETS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}

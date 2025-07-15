package com.dgjalic.gyrostinygadgets;

import com.dgjalic.gyrostinygadgets.item.ModItemProperties;
import com.dgjalic.gyrostinygadgets.item.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GyrosTinyGadgets.MOD_ID)
public class GyrosTinyGadgets
{
    public static final String MOD_ID = "gyrostinygadgets";

    public GyrosTinyGadgets(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ItemRegistry.register(modEventBus);
        CreativeTabRegistry.REGISTRY.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ModItemProperties.addCustomItemProperties();
    }

}

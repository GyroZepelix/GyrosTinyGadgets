package com.dgjalic.gyrostinygadgets;

import com.dgjalic.gyrostinygadgets.config.ModCommonConfigs;
import com.dgjalic.gyrostinygadgets.item.ModItemProperties;
import com.dgjalic.gyrostinygadgets.item.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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

        context.registerConfig(ModConfig.Type.CLIENT, ModCommonConfigs.SPEC, "gyrostinygadgets-client.toml");
        context.registerConfig(ModConfig.Type.COMMON, ModCommonConfigs.SPEC, "gyrostinygadgets-common.toml");
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ModItemProperties.addCustomItemProperties();
    }

}

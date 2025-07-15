package com.dgjalic.gyrostinygadgets.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfigs {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue FLINT_AND_IRON_LIGHT_CHANCE;
    public static final ForgeConfigSpec.DoubleValue FLINT_AND_IRON_BREAK_CHANCE;
    public static final ForgeConfigSpec.IntValue FLINT_AND_IRON_USE_COOLDOWN;

    static {
        BUILDER.push("flint_and_iron");

        FLINT_AND_IRON_LIGHT_CHANCE = BUILDER
                .comment("How often should the flint and iron light a block")
                .defineInRange("flintAndIronLightChance", 0.2, 0, 1);
        FLINT_AND_IRON_BREAK_CHANCE = BUILDER
                .comment("How often should the flint break")
                .defineInRange("flintAndIronBreakChance", 0.1, 0, 1);
        FLINT_AND_IRON_USE_COOLDOWN = BUILDER
                .comment("Use cooldown in ticks")
                .defineInRange("flintAndIronCooldown", 20, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

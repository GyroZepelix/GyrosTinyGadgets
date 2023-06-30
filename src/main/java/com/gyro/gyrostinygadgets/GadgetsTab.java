package com.gyro.gyrostinygadgets;

import com.gyro.gyrostinygadgets.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class GadgetsTab extends CreativeModeTab {
    public GadgetsTab() {
        super("gadgets_tab");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.HAMMER.get());
    }
}

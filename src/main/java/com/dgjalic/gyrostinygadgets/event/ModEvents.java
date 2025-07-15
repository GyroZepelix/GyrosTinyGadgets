package com.dgjalic.gyrostinygadgets.event;

import com.dgjalic.gyrostinygadgets.GyrosTinyGadgets;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = GyrosTinyGadgets.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
            if (event.getHand() != InteractionHand.MAIN_HAND) {
                return;
            }

            Player player = event.getEntity();
            boolean correctItemsToStrike = player.getItemInHand(InteractionHand.MAIN_HAND).is(Items.IRON_INGOT) && player.getItemInHand(InteractionHand.OFF_HAND).is(Items.FLINT);
            if (correctItemsToStrike) {
                FlintAndIronEventUtil.useOn(event);
            }
        }
    }
}

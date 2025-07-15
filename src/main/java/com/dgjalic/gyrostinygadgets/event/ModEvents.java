package com.dgjalic.gyrostinygadgets.event;

import com.dgjalic.gyrostinygadgets.GyrosTinyGadgets;
import com.mojang.blaze3d.vertex.DefaultedVertexConsumer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = GyrosTinyGadgets.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onItemUse(PlayerInteractEvent.RightClickBlock event) {
            if (event.getHand() != InteractionHand.MAIN_HAND) {
                return;
            }
            Set<Item> itemsToStrikeFire = Set.of(Items.IRON_INGOT, Items.FLINT);

            Player player = event.getEntity();
            Set<Item> itemsWielded = Set.of(
                    player.getItemInHand(InteractionHand.MAIN_HAND).getItem(),
                    player.getItemInHand(InteractionHand.OFF_HAND).getItem()
            );

            if (itemsToStrikeFire.equals(itemsWielded) && FlintAndIronEventUtil.useOn(event)) {
               event.getLevel().addParticle(ParticleTypes.FLAME, 1, 1, 1, 1, 1, 1);
            }
        }
    }
}

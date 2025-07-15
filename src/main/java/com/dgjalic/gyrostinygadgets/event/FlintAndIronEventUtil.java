package com.dgjalic.gyrostinygadgets.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class FlintAndIronEventUtil {
    public static boolean useOn(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos blockpos = event.getPos();
        BlockState blockstate = level.getBlockState(blockpos);

        Direction useFace = event.getFace();
        if (useFace == null) {
            return false;
        }

        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            BlockPos blockpos1 = blockpos.relative(event.getFace());
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, getHorizontalDirection(player))) {
                level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                swingBothHands(player);

                return true;
            } else {
                return false;
            }
        } else {
            level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
            swingBothHands(player);

            return true;
        }
    }

    private static Direction getHorizontalDirection(Player player) {
        return player == null ? Direction.NORTH : player.getDirection();
    }

    private static void swingBothHands(Player player) {
        player.swing(InteractionHand.OFF_HAND);
        player.swing(InteractionHand.MAIN_HAND );
    }
}

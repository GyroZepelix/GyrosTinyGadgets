package com.dgjalic.gyrostinygadgets.event;

import com.dgjalic.gyrostinygadgets.config.ModCommonConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class FlintAndIronEventUtil {
    public static void useOn(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos eventBlockpos = event.getPos();
        BlockState blockstate = level.getBlockState(eventBlockpos);
        double randomChance = Math.random();

        Direction useFace = event.getFace();
        boolean isMainOnCooldown = player.getCooldowns().isOnCooldown(player.getMainHandItem().getItem());
        if (useFace == null || isMainOnCooldown) {
            return;
        }

        playSparkAnimation(event, level, player, eventBlockpos);

        if (level.isClientSide()) {
            return;
        }
        if (randomChance < ModCommonConfigs.FLINT_AND_IRON_BREAK_CHANCE.get()) {
            destroyFlint(player, level, eventBlockpos);
        }
        if (randomChance < 1-ModCommonConfigs.FLINT_AND_IRON_LIGHT_CHANCE.get()) {
            return;
        }

        boolean isLightableBlock = CampfireBlock.canLight(blockstate) || CandleBlock.canLight(blockstate) || CandleCakeBlock.canLight(blockstate);
        if (isLightableBlock) {
            lightLightableBlock(level, eventBlockpos, blockstate, player);
            return;
        }

        BlockPos relativeBlockpos = eventBlockpos.relative(event.getFace());
        if (BaseFireBlock.canBePlacedAt(level, relativeBlockpos, getHorizontalDirection(player))) {
            lightBlock(level, player, relativeBlockpos, eventBlockpos);
        }
    }

    private static void playSparkAnimation(PlayerInteractEvent.RightClickBlock event, Level level, Player player, BlockPos eventBlockpos) {
        level.playSound(player, eventBlockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
        player.swing(InteractionHand.MAIN_HAND);
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            Vec3 facingVector = player.getViewVector(1);
            double x = player.getX() + facingVector.x * .8;
            double y = player.getY() + 1.1;
            double z = player.getZ() + facingVector.z * .8;
            serverLevel.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 1, 0.1, 0.1, 0.1, 0);
        }
        player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), ModCommonConfigs.FLINT_AND_IRON_USE_COOLDOWN.get());
        player.getCooldowns().addCooldown(player.getOffhandItem().getItem(), ModCommonConfigs.FLINT_AND_IRON_USE_COOLDOWN.get());
    }

    private static void lightLightableBlock(Level level, BlockPos eventBlockpos, BlockState blockstate, Player player) {
        level.setBlock(eventBlockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, eventBlockpos);
    }

    private static void lightBlock(Level level, Player player, BlockPos relativeBlockpos, BlockPos eventBlockpos) {
        BlockState blockstate1 = BaseFireBlock.getState(level, relativeBlockpos);
        level.setBlock(relativeBlockpos, blockstate1, 11);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, eventBlockpos);
    }
    
    private static void destroyFlint(Player player, Level level, BlockPos eventBlockpos) {
        player.getItemInHand(InteractionHand.OFF_HAND).shrink(1);
        level.playSound(null, eventBlockpos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
    }

    private static Direction getHorizontalDirection(Player player) {
        return player == null ? Direction.NORTH : player.getDirection();
    }
}

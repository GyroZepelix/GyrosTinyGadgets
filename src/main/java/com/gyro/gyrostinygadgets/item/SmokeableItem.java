package com.gyro.gyrostinygadgets.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static net.minecraft.world.item.Items.FLINT_AND_STEEL;

public class SmokeableItem extends Item {

    private final Optional<Item> refillItem;
    private static final Function<ItemStack, Boolean> isFull = (stack) -> stack.getDamageValue() == 0;
    private static final Function<ItemStack, Boolean> isEmpty = (stack) -> stack.getDamageValue() >= stack.getMaxDamage();

    public static final Function<ItemStack, Boolean> isLit = (stack) -> {

        if (stack.hasTag() && !isEmpty.apply(stack)) {
            CompoundTag tags = stack.getOrCreateTag();
            return tags.getBoolean("isLit");
        }
        return false;
    };
    public SmokeableItem(Properties pProperties, Optional<Item> refillItem ) {
        super(pProperties);
        this.refillItem = refillItem;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!(pEntity instanceof Player) || pLevel.isClientSide || isEmpty.apply(pStack) || !isLit.apply(pStack)) return;
        boolean burntUp = pStack.hurt(1, pLevel.random, null);
        if (!burntUp) return;
        if (refillItem.isPresent()) return;
        Player player = (Player) pEntity;
        player.getInventory().removeItem(pStack);

    }


    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        final InteractionHand otherHand = pUsedHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        final ItemStack lighterItemStack = pPlayer.getItemInHand(otherHand);
        final ItemStack smokeableItemStack = pPlayer.getItemInHand(pUsedHand);
        final CompoundTag tags = smokeableItemStack.getOrCreateTag();

        final boolean hasFlintAndSteel = lighterItemStack.getItem() == FLINT_AND_STEEL;



        if ( !isLit.apply(smokeableItemStack) && hasFlintAndSteel && !pLevel.isClientSide ) {
            tags.putBoolean("isLit", true);
            lighterItemStack.hurtAndBreak(1, pPlayer, (player -> player.broadcastBreakEvent(otherHand)));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        else if (!isLit.apply(smokeableItemStack)) {
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }
        pPlayer.startUsingItem(pUsedHand);

        return super.use(pLevel, pPlayer, pUsedHand);
    }


    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pLivingEntity.getX(), pLivingEntity.getY()+2, pLivingEntity.getZ(), 0.0D, 0.02D, 0.0D);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        boolean[] shouldRefill = new boolean[1];
        refillItem.ifPresentOrElse(item -> shouldRefill[0] = Ingredient.of(item).test(pRepairCandidate), () -> shouldRefill[0] = false);
        return shouldRefill[0];
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        if (isLit.apply(pStack)) {
            return 0xFFFF00;
        } else {
            return 0x00FF00;
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockState clickedBlock = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (clickedBlock.is(Blocks.CAMPFIRE) && !isLit.apply(pContext.getItemInHand())) {
            pContext.getItemInHand().getOrCreateTag().putBoolean("isLit", true);
            return InteractionResult.SUCCESS;
        } else if (isLit.apply(pContext.getItemInHand()) && Objects.requireNonNull(pContext.getPlayer()).isCrouching()) {
            pContext.getItemInHand().getOrCreateTag().putBoolean("isLit", false);
            return InteractionResult.SUCCESS;
        }

        return super.useOn(pContext);
    }

    @Override
    public boolean canBeDepleted() {
        return super.canBeDepleted();
    }
}

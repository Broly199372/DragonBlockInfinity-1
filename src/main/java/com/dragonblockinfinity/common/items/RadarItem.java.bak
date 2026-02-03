package com.dragonblockinfinity.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class RadarItem extends Item {

    public RadarItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(100));
            String nearest = list.stream()
                .filter(e -> e != player)
                .findFirst()
                .map(e -> e.getType().getRegistryName().toString())
                .orElse("nenhuma");
            Component msg = Component.literal("Radar: entidade mais próxima = " + nearest);
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(msg);
            } else {
                player.displayClientMessage(msg, false);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}

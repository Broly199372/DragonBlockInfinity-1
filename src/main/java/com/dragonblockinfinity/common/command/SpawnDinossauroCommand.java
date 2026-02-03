package com.dragonblockinfinity.common.command;

import com.dragonblockinfinity.common.entity.DinossauroEntity;
import com.dragonblockinfinity.registry.ModEntities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import com.mojang.brigadier.CommandDispatcher;

public class SpawnDinossauroCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spawn_dino")
            .executes(context -> {
                CommandSourceStack source = context.getSource();
                Level level = source.getLevel();
                
                if (!level.isClientSide) {
                    DinossauroEntity dino = new DinossauroEntity(ModEntities.DINOSSAURO.get(), level);
                    dino.setPos(source.getPosition().x, source.getPosition().y, source.getPosition().z);
                    level.addFreshEntity(dino);
                    source.sendSuccess(() -> Component.literal("§6Dinossauro spawned!"), true);
                }
                return 1;
            })
        );
    }
}

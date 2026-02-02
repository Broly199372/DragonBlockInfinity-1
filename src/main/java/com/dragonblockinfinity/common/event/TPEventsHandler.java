package com.dragonblockinfinity.common.event;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.Random;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID)
public class TPEventsHandler {
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onPlayerHit(LivingHurtEvent event) {
        // Verifica se quem atacou foi um Player
        if (event.getSource().getEntity() instanceof Player player && !player.level().isClientSide) {
            player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                
                // Incrementa contador de hits
                int currentHits = data.getHitCounter() + 1;
                
                if (currentHits >= 5) {
                    data.setHitCounter(0); // Reseta a cada 5 hits
                    
                    // 20% de chance (0.2)
                    if (RANDOM.nextFloat() <= 0.20f) {
                        int mnd = data.getStats().getMind();
                        double baseTP = 10.0; // Valor base que você pediu
                        
                        // Fórmula: tpGain = baseTP * (1.0 + MND * 0.02)
                        double finalTP = baseTP * (1.0 + (mnd * 0.02));
                        
                        data.addTP(finalTP);
                        
                        // Opcional: Enviar mensagem ou som de "Level Up" de treino
                    }
                } else {
                    data.setHitCounter(currentHits);
                }
            });
        }
    }
}

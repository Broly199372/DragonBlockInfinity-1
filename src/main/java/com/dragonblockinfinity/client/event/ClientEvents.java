package com.dragonblockinfinity.client.event;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.client.keybind.KeyBindings;
import com.dragonblockinfinity.client.renderer.layer.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.MENU_KEY);
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        addLayerToPlayer(event, "default");
        addLayerToPlayer(event, "slim");
    }

    private static void addLayerToPlayer(EntityRenderersEvent.AddLayers event, String skinType) {
        PlayerRenderer renderer = event.getSkin(skinType);
        if (renderer != null) {
            renderer.addLayer(new RaceLayer(renderer)); // Camada da Pele/Raça
            renderer.addLayer(new HairLayer(renderer));
            renderer.addLayer(new MuscleLayer(renderer));
        }
    }
}

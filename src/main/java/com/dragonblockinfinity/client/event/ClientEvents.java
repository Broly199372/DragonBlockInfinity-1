package com.dragonblockinfinity.client.event;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.client.renderer.entity.DinossauroRenderer;
import com.dragonblockinfinity.common.entity.DinossauroEntity;
import com.dragonblockinfinity.registry.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    
    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(ModEntities.DINOSSAURO.get(), DinossauroRenderer::new);
    }
}

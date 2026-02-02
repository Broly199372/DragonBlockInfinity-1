package com.dragonblockinfinity;

import com.dragonblockinfinity.common.ki.KiCapability;
import com.dragonblockinfinity.common.network.NetworkHandler;
import com.dragonblockinfinity.registry.ModRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DragonBlockInfinity.MOD_ID)
public class DragonBlockInfinity {

    public static final String MOD_ID = "dragonblockinfinity";

    public DragonBlockInfinity() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ModRegistry.register(modEventBus);

        // Eventos Forge (attach capability + tick regen + sync)
        MinecraftForge.EVENT_BUS.register(new KiCapability.Events());

        System.out.println("=== DRAGON BLOCK INFINITY CARREGADO ===");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NetworkHandler.register();
            System.out.println("[DBI] Network Handler registrado!");
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            System.out.println("[DBI] Client setup concluído!");
        });
    }
}

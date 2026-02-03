package com.dragonblockinfinity.registry;

import net.minecraftforge.eventbus.api.IEventBus;

/**
 * Classe central para registrar todos os componentes do mod
 */
public class ModRegistry {

    public static void register(IEventBus bus) {
        System.out.println("[DBI] Registrando componentes...");

        // Registrar abas criativas
        ModCreativeTabs.register(bus);

        // Registrar blocos
        ModBlocks.BLOCKS.register(bus);

        // Registrar itens
        ModItems.ITEMS.register(bus);

        // Registrar entidades
        ModEntities.ENTITIES.register(bus);

        System.out.println("[DBI] Componentes registrados com sucesso!");
    }
}

package com.dragonblockinfinity.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, "dragonblockinfinity");
    
    // BlockItems
    public static final RegistryObject<Item> DIRTY_STONE = ITEMS.register("dirty_stone",
        () -> new BlockItem(ModBlocks.DIRTY_STONE.get(), new Item.Properties()));
    
    // Comida
    public static final RegistryObject<Item> SENZU = ITEMS.register("senzu",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> DINO_MEAT = ITEMS.register("dino_meat",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> DINO_MEAT_COOKED = ITEMS.register("dino_meat_cooked",
        () -> new Item(new Item.Properties()));
    
    // Equipamentos
    public static final RegistryObject<Item> SCOUTER = ITEMS.register("scouter",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> RADAR = ITEMS.register("radar",
        () -> new Item(new Item.Properties()));
    
    // Dragon Balls
    public static final RegistryObject<Item> DRAGON_ESFER = ITEMS.register("dragon_esfer",
        () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> ESFERA_DRAGON_EARTH = ITEMS.register("esfera_dragon_earth",
        () -> new Item(new Item.Properties()));
}

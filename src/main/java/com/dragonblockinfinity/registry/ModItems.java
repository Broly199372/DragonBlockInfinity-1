package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.common.items.DinoMeatCookedItem;
import com.dragonblockinfinity.common.items.DinoMeatItem;
import com.dragonblockinfinity.common.items.EsferaDragonItem;
import com.dragonblockinfinity.common.items.RadarItem;
import com.dragonblockinfinity.common.items.SenzuItem;
import com.dragonblockinfinity.common.items.ScouterItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
        () -> new SenzuItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> DINO_MEAT = ITEMS.register("dino_meat",
        () -> new DinoMeatItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.3f).meat().build())));

    public static final RegistryObject<Item> DINO_MEAT_COOKED = ITEMS.register("dino_meat_cooked",
        () -> new DinoMeatCookedItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).meat().build())));

    // Equipamentos
    public static final RegistryObject<Item> SCOUTER = ITEMS.register("scouter",
        () -> new ScouterItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> RADAR = ITEMS.register("radar",
        () -> new RadarItem(new Item.Properties().stacksTo(1)));

    // Dragon Balls / itens especiais
    public static final RegistryObject<Item> DRAGON_ESFER = ITEMS.register("dragon_esfer",
        () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ESFERA_DRAGON_EARTH = ITEMS.register("esfera_dragon_earth",
        () -> new EsferaDragonItem(new Item.Properties().stacksTo(1)));
}

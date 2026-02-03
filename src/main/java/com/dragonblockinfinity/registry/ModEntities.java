package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.entity.DinossauroEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
        DeferredRegister.create(ForgeRegistries.ENTITIES, DragonBlockInfinity.MOD_ID);

    public static final RegistryObject<EntityType<DinossauroEntity>> DINOSSAURO =
        ENTITIES.register("dinossauro", () ->
            EntityType.Builder.of(DinossauroEntity::new, MobCategory.CREATURE)
                .sized(1.8f, 2.33f)
                .build(new ResourceLocation(DragonBlockInfinity.MOD_ID, "dinossauro").toString())
        );

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(DINOSSAURO.get(), DinossauroEntity.createAttributes().build());
    }
}

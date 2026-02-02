package com.dragonblockinfinity.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

/**
 * Entidade base do Dinossauro
 * 
 * TODO: Adicione aqui as funcionalidades de:
 * - Vida (health)
 * - Dano (attackDamage)
 * - Velocidade (movementSpeed)
 * 
 * @author DragonBlockInfinity
 */
public class DinossauroEntity extends Mob {
    
    // Adicione seus atributos aqui
    private int vida = 3000;
    int dano = 1525;
    float velocidade = 2.2F;
    
    public int getVida() {
        return vida;
    }
    
    public void setVida(int vida) {
        this.vida = vida;
    }
    
    public DinossauroEntity(EntityType<? extends DinossauroEntity> entityType, Level level) {
        super(entityType, level);
    }
    public static final RegistryObject<EntityType<DinossauroEntity>> DINOSSAURO = 
        ENTITIES.register("dinossauro", () -> EntityType.Builder
        .of(DinossauroEntity::new, MobCategory.CREATURE)
        .sized(1.8f, 2.33f)  // ← LARGURA: 1.8 blocos, ALTURA: 2.33 blocos
        .build("dinossauro"));
    // Adicione seus métodos aqui
}

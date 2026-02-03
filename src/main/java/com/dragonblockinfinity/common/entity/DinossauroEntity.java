package com.dragonblockinfinity.common.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;


public class DinossauroEntity extends Mob {

    // Campos locais (valores de configuração)
    private int vida = 3000;
    private int dano = 1525;
    private float velocidade = 2.2F;

    public DinossauroEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public static float getVelocidadeBase() {
        return 2.2F;
    }

    @Override
    protected void registerGoals() {
        // registra goals locais (usa o goal existente no pacote se houver)
        try {
            this.goalSelector.addGoal(0, new com.dragonblockinfinity.common.entity.goal.AtacarentidadevivaGoal(this, 1.0D, false));
        } catch (Throwable ignored) {}
    }

    /**
     * Cria o AttributeSupplier para registro (evita erro "has attributes").
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 3000.0D)
            .add(Attributes.ATTACK_DAMAGE, 1525.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }
}

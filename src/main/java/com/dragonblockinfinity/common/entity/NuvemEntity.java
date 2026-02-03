package com.dragonblockinfinity.common.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Entidade da Nuvem Voadora
 * 
 * Características:
 * - Velocidade: 1.5F (controla velocidade de movimento)
 * - Altura de flutuação: variável
 * - Comportamento: Flutua e se move pelo ar
 * 
 * @author DragonBlockInfinity
 */
public class NuvemEntity extends Mob implements GeoEntity {
    
    // Cache de animações do GeckoLib
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    
    // Sincronizador de dados para cliente/servidor
    private static final EntityDataAccessor<Float> VELOCIDADE = 
        EntityDataAccessor.defineId(NuvemEntity.class, EntityDataSerializers.FLOAT);
    
    // EntityType estático para registro
    public static final EntityType<NuvemEntity> NUVEM = EntityType.Builder.of(NuvemEntity::new, null)
        .sized(2.0F, 1.5F)
        .build("nuvem");
    
    public NuvemEntity(EntityType<? extends Mob> entityType) {
        super(entityType);
        this.setNoGravity(true); // Nuvem não cai
    }
    
    @Override
    protected void registerGoals() {
        // TODO: Adicionar goals de IA (voo, procurar jogador, etc.)
        super.registerGoals();
    }
    
    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VELOCIDADE, 1.5F);
    }
    
    @Override
    protected void createAttributes() {
        super.createAttributes();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.5D);
    }
    
    /**
     * Define a velocidade de movimento da nuvem
     */
    public void setVelocidadeMovimento(float velocidade) {
        this.entityData.set(VELOCIDADE, velocidade);
    }
    
    /**
     * Obtém a velocidade de movimento da nuvem
     */
    public float getVelocidadeMovimento() {
        return this.entityData.get(VELOCIDADE);
    }
    
    @Override
    public void tick() {
        super.tick();
        
        // Movimento suave flutuante
        if (!this.level().isClientSide) {
            float velocidade = this.getVelocidadeMovimento();
            
            // Aplica movimento baseado na rotação (direção)
            double dx = Math.cos(Math.toRadians(this.getYRot() - 90.0F)) * velocidade;
            double dz = Math.sin(Math.toRadians(this.getYRot() - 90.0F)) * velocidade;
            
            this.setDeltaMovement(dx, this.getDeltaMovement().y, dz);
        }
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Velocidade", this.getVelocidadeMovimento());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Velocidade")) {
            this.setVelocidadeMovimento(tag.getFloat("Velocidade"));
        }
    }
    
    // GeoEntity methods
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrationEvent event) {
        // Registrar controllers de animação aqui
        // Exemplo: event.add(new AnimationController<>(this, "controller", ...))
    }
}

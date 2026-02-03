package com.dragonblockinfinity.common.entity.animation;

import com.dragonblockinfinity.common.entity.NuvemEntity;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

/**
 * Animações da Nuvem para GeckoLib
 * 
 * Bones disponíveis do modelo Nuvem:
 * - root (raiz, controla movimento geral)
 * - nubecita1 até nubecita29 (29 partes individuais da nuvem)
 * 
 * As animações abaixo podem ser referenciadas pelo JSON de animação
 * 
 * @author DragonBlockInfinity
 */
public class NuvemAnimations {
    
    /**
     * Animação de flutuação (idle)
     * Movimento suave de flutuação no ar
     */
    public static final RawAnimation FLOAT = RawAnimation.begin()
        .thenLoop("animation.nuvem.float");
    
    /**
     * Animação de voo rápido
     * Movimento mais acelerado para quando está em movimento
     */
    public static final RawAnimation FLY = RawAnimation.begin()
        .thenLoop("animation.nuvem.fly");
    
    /**
     * Animação de ataque
     * Contração das partículas da nuvem para simular ataque
     */
    public static final RawAnimation ATTACK = RawAnimation.begin()
        .thenPlay("animation.nuvem.attack");
    
    /**
     * Animação de morte
     * Dispersão das partículas da nuvem
     */
    public static final RawAnimation DEATH = RawAnimation.begin()
        .thenPlay("animation.nuvem.death");
    
    /**
     * Animação de rotação
     * Rotação suave para virar a nuvem
     */
    public static final RawAnimation TURN = RawAnimation.begin()
        .thenLoop("animation.nuvem.turn");
    
    /**
     * Cria o controller de animação padrão
     * Pode ser usado no método registerControllers() da entidade
     */
    public static AnimationController<NuvemEntity> createAnimationController() {
        return new AnimationController<>(null, "controller", 0, event -> {
            // Lógica de seleção de animação
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                event.getController().setAnimation(FLOAT);
            }
            return true;
        });
    }
}

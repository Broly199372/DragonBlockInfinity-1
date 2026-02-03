package com.dragonblockinfinity.common.entity.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.phys.AABB;
import java.util.List;

/**
 * Goal para atacar qualquer entidade viva
 * 
 * Range de ataque:
 * - Largura: 5 blocos
 * - Altura: 4 blocos
 * - À frente do dinossauro
 */
public class AtacarentidadevivaGoal extends MeleeAttackGoal {
    
    // Dimensões da caixa de ataque
    private static final double LARGURA_ATAQUE = 5.0D;
    private static final double ALTURA_ATAQUE = 4.0D;
    private static final double PROFUNDIDADE_ATAQUE = 8.0D; // Distância à frente
    
    public AtacarentidadevivaGoal(PathfinderMob mob, double speed, boolean followingTargetEvenWithLowHealth) {
        super(mob, speed, followingTargetEvenWithLowHealth);
    }
    
    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive() && super.canUse();
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public void tick() {
        super.tick();
        
        // Procurar por qualquer entidade viva próxima se não tiver target
        if (this.mob.getTarget() == null) {
                List<LivingEntity> list = this.mob.level().getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(this.calcularDistancia()));
                LivingEntity proximaEntidade = null;
                for (LivingEntity e : list) {
                    if (!(e instanceof Mob)) {
                        proximaEntidade = e;
                        break;
                    }
                }
            
            if (proximaEntidade != null && !(proximaEntidade instanceof Mob)) {
                // Verifica se está dentro da caixa de ataque
                if (this.estaNoRangeDeAtaque(proximaEntidade)) {
                    this.mob.setTarget(proximaEntidade);
                }
            }
        }
    }
    
    /**
     * Calcula a distância de detecção baseado na altura do inimigo
     * - 24 blocos de distância horizontal padrão
     * - +16 blocos de altura se o inimigo estiver 5+ blocos mais alto
     */
    private double calcularDistancia() {
        LivingEntity target = this.mob.getTarget();
        
        if (target != null) {
            double difAltura = target.getY() - this.mob.getY();
            
            // Se está 5+ blocos mais alto, aumenta distância vertical
            if (difAltura >= 5.0D) {
                return 24.0D + 16.0D; // 40 blocos total
            }
        }
        
        return 24.0D; // 24 blocos padrão
    }
    
    /**
     * Verifica se o inimigo está dentro da caixa de ataque
     * Caixa: 5 blocos de largura x 4 blocos de altura x 8 blocos de profundidade (à frente)
     */
    private boolean estaNoRangeDeAtaque(LivingEntity entidade) {
        double difAltura = entidade.getY() - this.mob.getY();
        
        // Verifica altura: -0.5 até 4 blocos acima
        if (difAltura < -0.5D || difAltura > ALTURA_ATAQUE) {
            return false;
        }
        
        // Calcula a caixa de ataque à frente do dinossauro
        AABB caixaAtaque = this.gerarCaixaAtaque();
        
        // Verifica se o inimigo está dentro da caixa
        return caixaAtaque.contains(entidade.getX(), entidade.getY(), entidade.getZ());
    }
    
    /**
     * Gera a caixa de ataque à frente do dinossauro
     * 5 blocos de largura x 4 blocos de altura x 8 blocos de profundidade
     */
    private AABB gerarCaixaAtaque() {
        double x = this.mob.getX();
        double y = this.mob.getY();
        double z = this.mob.getZ();
        
        // Calcula direção do dinossauro baseado na rotação
        double yaw = Math.toRadians(this.mob.getYRot());
        
        // Ponto à frente baseado na rotação
        double profundidade = PROFUNDIDADE_ATAQUE; // Distância até o início da caixa
        double frontX = x + Math.cos(yaw) * profundidade;
        double frontZ = z + Math.sin(yaw) * profundidade;
        
        // Metade da largura
        double metadeLargura = LARGURA_ATAQUE / 2.0D;
        
        // Cria a caixa de ataque
        // Largura total: LARGURA_ATAQUE
        // Altura total: ALTURA_ATAQUE
        // Profundidade total: PROFUNDIDADE_ATAQUE
        return new AABB(
            frontX - metadeLargura,                    // minX
            y,                                          // minY
            frontZ - metadeLargura,                    // minZ
            frontX + PROFUNDIDADE_ATAQUE + metadeLargura, // maxX
            y + ALTURA_ATAQUE,                         // maxY
            frontZ + metadeLargura                     // maxZ
        );
    }
}

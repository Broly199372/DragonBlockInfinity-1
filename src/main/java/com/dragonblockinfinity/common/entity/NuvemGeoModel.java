package com.dragonblockinfinity.common.entity;

import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.VertexConsumer;

/**
 * Modelo GeoModel para a Nuvem usando GeckoLib
 * 
 * Bones/Partes disponíveis:
 * - root (raiz)
 * - nubecita1 até nubecita29 (29 partes individuais da nuvem)
 * 
 * Este modelo utiliza a estrutura do Blockbench e a converte para GeckoLib
 * 
 * @author DragonBlockInfinity
 */
public class NuvemGeoModel extends GeoModel<NuvemEntity> {
    
    // Caminho do modelo em formato GeckoLib JSON
    private static final ResourceLocation MODEL = new ResourceLocation("dragonblock", "geo/nuvem.geo.json");
    
    // Caminho da textura
    private static final ResourceLocation TEXTURE = new ResourceLocation("dragonblock", "textures/entity/nuvem.png");
    
    // Caminho das animações
    private static final ResourceLocation ANIMATION = new ResourceLocation("dragonblock", "animations/nuvem.animation.json");
    
    /**
     * Retorna o modelo em formato GeckoLib
     */
    @Override
    public ResourceLocation getModelResource(NuvemEntity nuvem) {
        return MODEL;
    }
    
    /**
     * Retorna a textura da Nuvem
     */
    @Override
    public ResourceLocation getTextureResource(NuvemEntity nuvem) {
        return TEXTURE;
    }
    
    /**
     * Retorna as animações disponíveis
     */
    @Override
    public ResourceLocation getAnimationResource(NuvemEntity nuvem) {
        return ANIMATION;
    }
    
    /**
     * Define o tipo de renderização (translúcido para nuvem)
     */
    @Override
    public RenderType getRenderType(NuvemEntity nuvem, float partialTick, PoseStack poseStack, 
                                    MultiBufferSource bufferSource, VertexConsumer buffer, 
                                    int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
    
    /**
     * Animações customizadas da entidade
     * Aqui você pode animar os bones individuais (nubecita1-29)
     */
    @Override
    public void setCustomAnimations(NuvemEntity nuvem, long instanceId, AnimationEvent<NuvemEntity> animationEvent) {
        // Pega o bone raiz
        CoreGeoBone root = this.getAnimationProcessor().getBone("root");
        
        if (root != null) {
            // Aplica rotação suave baseado na direção da Nuvem
            root.setRotY(Mth.lerp(animationEvent.getPartialTick(), 
                nuvem.yBodyRotO, nuvem.getYRot()) * Mth.DEG_TO_RAD);
        }
        
        // Exemplo: Você pode animar cada parte individualmente
        // Para isso, obtenha o bone específico:
        // CoreGeoBone nubecita1 = this.getAnimationProcessor().getBone("nubecita1");
        // if (nubecita1 != null) {
        //     nubecita1.setRotX(...);
        //     nubecita1.setRotY(...);
        //     nubecita1.setRotZ(...);
        // }
    }
}

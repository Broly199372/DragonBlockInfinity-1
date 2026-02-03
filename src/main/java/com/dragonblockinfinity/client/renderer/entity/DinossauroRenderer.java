package com.dragonblockinfinity.client.renderer.entity;

import com.dragonblockinfinity.common.entity.DinossauroEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import com.dragonblockinfinity.common.entity.DinossauroModel;

/**
 * Renderer para a entidade Dinossauro
 * 
 * Responsável por renderizar o Dinossauro com seu modelo
 * 
 * @author DragonBlockInfinity
 */
public class DinossauroRenderer extends MobRenderer<DinossauroEntity, DinossauroModel> {
    
    // Caminho da textura do dinossauro
    private static final ResourceLocation TEXTURE = 
        new ResourceLocation("dragonblock", "textures/entity/dinossauro.png");
    
    public DinossauroRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DinossauroModel(renderManager.bakeLayer(DinossauroModel.LAYER_LOCATION)), 1.5F);
        
        // Distância de renderização da sombra
        this.shadowRadius = 2.0F;
    }
    
    /**
     * Retorna a textura do dinossauro
     */
    @Override
    public ResourceLocation getTextureLocation(DinossauroEntity dinossauro) {
        return TEXTURE;
    }
    
    /**
     * Renderiza a entidade
     */
    @Override
    public void render(DinossauroEntity dinossauro, float entityYaw, float partialTick, 
                      PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(dinossauro, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

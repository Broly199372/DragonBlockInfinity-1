package com.dragonblockinfinity.client.renderer.entity;

import com.dragonblockinfinity.common.entity.NuvemEntity;
import com.dragonblockinfinity.common.entity.NuvemGeoModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Renderer para a entidade Nuvem usando GeckoLib
 * 
 * Responsável por renderizar a Nuvem com seu modelo GeoModel
 * 
 * @author DragonBlockInfinity
 */
public class NuvemRenderer extends GeoEntityRenderer<NuvemEntity> {
    
    public NuvemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NuvemGeoModel());
        
        // Distância de renderização
        this.shadowRadius = 1.0F;
    }
    
    /**
     * Define o tipo de renderização para translúcido (nuvem)
     */
    @Override
    public RenderType getRenderType(NuvemEntity nuvem, float partialTick, PoseStack poseStack,
                                     MultiBufferSource bufferSource, VertexConsumer buffer,
                                     int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
    
    /**
     * Renderiza o shadow (sombra) da entidade
     */
    @Override
    public void render(NuvemEntity nuvem, float entityYaw, float partialTick, PoseStack poseStack,
                      MultiBufferSource bufferSource, int packedLight) {
        super.render(nuvem, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

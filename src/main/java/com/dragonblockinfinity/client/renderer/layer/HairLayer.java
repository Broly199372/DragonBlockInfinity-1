package com.dragonblockinfinity.client.renderer.layer;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HairLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public HairLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
            int hairIndex = data.getHairBaseIndex();
            if (hairIndex <= 0) return;

            ResourceLocation texture = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/entity/customizacao/hair/hair_base" + hairIndex + ".png");
            
            int color = data.getHairColor();
            float r = (float)(color >> 16 & 255) / 255.0F;
            float g = (float)(color >> 8 & 255) / 255.0F;
            float b = (float)(color & 255) / 255.0F;

            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
            this.getParentModel().head.render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
        });
    }
}

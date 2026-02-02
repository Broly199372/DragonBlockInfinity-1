package com.dragonblockinfinity.client.renderer.layer;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import com.dragonblockinfinity.common.race.RaceEnum;
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

public class MuscleLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private static final ResourceLocation MUSCULOS = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/entity/musculosrace/musculos.png");
    private static final ResourceLocation MUSCULOS_ARC = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/entity/musculosrace/musculos_arconsian.png");

    public MuscleLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
            if (data.getRace() == RaceEnum.NAMEKIAN) return;

            ResourceLocation texture = (data.getRace() == RaceEnum.ARCONSIAN) ? MUSCULOS_ARC : MUSCULOS;
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        });
    }
}

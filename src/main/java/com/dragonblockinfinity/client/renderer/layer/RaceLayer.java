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

public class RaceLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public RaceLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> p) { super(p); }

    @Override
    public void render(PoseStack ps, MultiBufferSource b, int pl, AbstractClientPlayer p, float ls, float la, float pt, float at, float hy, float hp) {
        p.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(d -> {
            String path = switch(d.getRace()) {
                case SAIYAN -> "sayajin";
                case HALF -> "fsayajin";
                case ARCONSIAN -> "arconsian";
                case NAMEKIAN -> "namkian";
                default -> "humano";
            };
            ResourceLocation tex = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/entity/race/" + path + ".png");
            VertexConsumer vc = b.getBuffer(RenderType.entityCutoutNoCull(tex));
            this.getParentModel().renderToBuffer(ps, vc, pl, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        });
    }
}

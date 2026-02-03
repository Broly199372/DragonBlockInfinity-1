package com.dragonblockinfinity.client.renderer.entity;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.entity.DinossauroEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class DinossauroRenderer extends MobRenderer<DinossauroEntity, EntityModel<DinossauroEntity>> {

    public DinossauroRenderer(EntityRendererProvider.Context context) {
        super(context, new DinossauroModel(new ModelPart(com.google.common.collect.ImmutableList.of(), java.util.Map.of())), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(DinossauroEntity entity) {
        return new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/entity/mobs/dino/dinossauro.png");
    }
}

class DinossauroModel extends EntityModel<DinossauroEntity> {
    private final ModelPart root;
    
    public DinossauroModel(ModelPart modelPart) {
        this.root = modelPart;
    }

    @Override
    public void setupAnim(DinossauroEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

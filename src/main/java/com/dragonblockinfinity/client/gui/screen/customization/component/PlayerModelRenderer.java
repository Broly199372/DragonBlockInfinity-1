package com.dragonblockinfinity.client.gui.screen.customization.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;

public class PlayerModelRenderer {
    /**
     * Renderiza uma entidade na GUI usando o método oficial e estável da 1.20.1
     */
    public static void renderEntity(GuiGraphics graphics, int x, int y, int scale, float rotation, LivingEntity entity) {
        if (entity == null) return;

        // Ajusta a rotação para o padrão JOML (quaternion) exigido pela 1.20.1
        Quaternionf quaternionf = (new Quaternionf()).rotationZ((float)Math.PI);
        Quaternionf quaternionf1 = (new Quaternionf()).rotationY(rotation * ((float)Math.PI / 180F));
        quaternionf.mul(quaternionf1);

        // O método renderEntityInInventory é o mais seguro:
        // Ele já cuida do Lighting (iluminação), Z-Level e renderização das camadas de armadura/skin.
        InventoryScreen.renderEntityInInventory(
            graphics, 
            x, y,       // Posição na tela
            scale,      // Tamanho do boneco
            quaternionf, // Rotação (Pose)
            null,       // Rotação da cabeça (null segue o corpo)
            entity      // A entidade (o player)
        );
    }
}

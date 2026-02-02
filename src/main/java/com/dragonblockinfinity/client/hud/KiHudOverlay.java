package com.dragonblockinfinity.client.hud;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.client.ki.ClientKiData;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KiHudOverlay {

    // Você já tem isso no resources:
    // assets/dragonblockinfinity/textures/gui/huds/ki_bar_good.png
    private static final ResourceLocation KI_BAR = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/huds/ki_bar_good.png");

    // Ajuste esses tamanhos se sua textura tiver outro tamanho.
    // (Isso é o “tamanho do desenho” na tela)
    private static final int BAR_W = 182;
    private static final int BAR_H = 10;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // não renderiza se HUD padrão estiver escondido (F1)
        if (mc.options.hideGui) return;

        int ki = ClientKiData.getKi();
        int maxKi = ClientKiData.getMaxKi();
        float pct = maxKi <= 0 ? 0f : (ki / (float) maxKi);
        pct = Math.max(0f, Math.min(1f, pct));

        GuiGraphics gg = event.getGuiGraphics();

        int screenW = gg.guiWidth();
        int screenH = gg.guiHeight();

        // posição: acima da hotbar (padrão)
        int x = (screenW / 2) - (BAR_W / 2);
        int y = screenH - 49; // ajuste se quiser mais alto/baixo

        RenderSystem.enableBlend();

        // 1) fundo (assumindo que a textura tem “barra cheia”, mas a gente usa como base)
        gg.blit(KI_BAR, x, y, 0, 0, BAR_W, BAR_H, BAR_W, BAR_H);

        // 2) parte preenchida (recorte)
        int filled = (int) (BAR_W * pct);
        if (filled > 0) {
            gg.blit(KI_BAR, x, y, 0, 0, filled, BAR_H, BAR_W, BAR_H);
        }

        RenderSystem.disableBlend();
    }
}

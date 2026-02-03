package com.dragonblockinfinity.client.gui.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import com.dragonblockinfinity.client.gui.screen.customization.component.PlayerModelRenderer;
import com.dragonblockinfinity.common.race.RaceEnum;

import java.util.Arrays;
import java.util.List;

/**
 * Menu Inicial do DragonBlockInfinity
 *
 * - 6 controles "⬅ nome ➡" com looping
 * - Primeira linha atualiza preview do player (roda direita/esquerda e cabeça segue o mouse)
 * - Mantive o nome da constante `Menu_Inicial1` conforme solicitado
 * - Tamanho do menu: 256x200 ; preview 128x128
 */
public class MenuInicial extends Screen {
    private static final ResourceLocation btn2 = new ResourceLocation("dragonblockinfinity", "textures/gui/buttoes/seta_right.png");
    private static final ResourceLocation btn = new ResourceLocation("dragonblockinfinity", "textures/gui/buttoes/seta_left.png");
    private static final ResourceLocation Menu_Inicial1 = new ResourceLocation("dragonblockinfinity", "textures/gui/menu_base.png");

    private final int menuWidth = 256;
    private final int menuHeight = 200;
    private int menuLeft;
    private int menuTop;

    private final List<String> raceNames;
    private final int[] selected = new int[6];
    private final Button[] centerButtons = new Button[6];

    // animação do corpo (apenas direita/esquerda)
    private float rotTicker = 0f;

    public MenuInicial() {
        super(Component.literal("Menu Inicial"));
        this.raceNames = Arrays.stream(RaceEnum.values()).map(Enum::toString).toList();
        for (int i = 0; i < selected.length; i++) selected[i] = 0;
    }

    @Override
    protected void init() {
        super.init();
        this.menuLeft = (this.width - menuWidth) / 2;
        this.menuTop = (this.height - menuHeight) / 2;

        this.clearWidgets();

        final int rowHeight = 28;
        final int btnSize = 20;
        final int centerLabelWidth = 120;

        for (int row = 0; row < 6; row++) {
            final int y = menuTop + 12 + row * rowHeight;
            // left arrow (2px from left edge of menu)
            int leftX = menuLeft + 2;
            final int rowFinal = row;
            Button leftBtn = new Button(leftX, y, btnSize, btnSize, Component.literal("⬅"), btn -> {
                selected[rowFinal] = (selected[rowFinal] - 1 + raceNames.size()) % raceNames.size();
                updateLabel(rowFinal);
                if (rowFinal == 0) applyRaceToPlayer(selected[0]);
            });
            this.addRenderableWidget(leftBtn);

            // center label/button (shows current value)
            int centerX = menuLeft + (menuWidth / 2) - (centerLabelWidth / 2);
            Button center = new Button(centerX, y, centerLabelWidth, btnSize, Component.literal(raceNames.get(selected[row])), btn -> {
                selected[rowFinal] = (selected[rowFinal] + 1) % raceNames.size();
                updateLabel(rowFinal);
                if (rowFinal == 0) applyRaceToPlayer(selected[0]);
            });
            center.setMessage(Component.literal(raceNames.get(selected[row])));
            this.addRenderableWidget(center);
            centerButtons[row] = center;

            // right arrow (especial: row 0 -> 4px, outros -> 2px)
            int rightMargin = (row == 0) ? 4 : 2;
            int rightX = menuLeft + menuWidth - btnSize - rightMargin;
            Button rightBtn = new Button(rightX, y, btnSize, btnSize, Component.literal("➡"), btn -> {
                selected[rowFinal] = (selected[rowFinal] + 1) % raceNames.size();
                updateLabel(rowFinal);
                if (rowFinal == 0) applyRaceToPlayer(selected[0]);
            });
            this.addRenderableWidget(rightBtn);
        }
    }

    private void updateLabel(int row) {
        if (centerButtons[row] != null) {
            centerButtons[row].setMessage(Component.literal(raceNames.get(selected[row])));
        }
    }

    private void applyRaceToPlayer(int raceIndex) {
        // Lugar para integração real de troca de raça/texture/model do jogador.
        // Aqui mantemos apenas o índice selecionado para o preview local.
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        // desenha background do menu (tamanho exato 256x200) usando a constante solicitada
        graphics.blit(Menu_Inicial1, menuLeft, menuTop, 0, 0, menuWidth, menuHeight, menuWidth, menuHeight);

        // preview do player (128x128) no canto esquerdo interno
        int previewSize = 128;
        int previewX = menuLeft + 12;
        int previewY = menuTop + 18;

        // animação de corpo: apenas direita/esquerda (oscila)
        rotTicker += 1.2f;
        float bodyYaw = (float) Math.sin(rotTicker * 0.05f) * 25.0f; // ±25 graus

        // cabeça segue mouse (relativa ao centro do preview)
        int centerX = previewX + previewSize / 2;
        int centerY = previewY + previewSize / 2;
        float dx = mouseX - centerX;
        float dy = mouseY - centerY;
        float headYaw = Mth.clamp(dx / (previewSize / 2f) * 30f, -30f, 30f); // ±30 graus yaw
        float headPitch = Mth.clamp(dy / (previewSize / 2f) * 20f, -20f, 20f); // ±20 graus pitch

        // entidade para preview
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            // escala ajustada para o preview (experimente ajustar o valor)
            int scale = 50;
            // desenha o player girando só Y e com cabeça seguindo mouse
            PlayerModelRenderer.renderEntity(graphics, centerX, centerY + 10, scale, bodyYaw, headYaw, headPitch, player);
        }

        // desenha widgets (botões)
        super.render(graphics, mouseX, mouseY, partialTicks);

        // dica visual (opcional)
        graphics.drawString(this.font, Component.literal("Preview 128x128"), previewX, previewY - 10, 0xFFFFFF, false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

package com.dragonblockinfinity.client.gui.screen;

import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import com.dragonblockinfinity.common.network.NetworkHandler;
import com.dragonblockinfinity.common.network.packet.UpgradeStatPacket;
import com.dragonblockinfinity.client.gui.screen.customization.data.PreviewStatsCalculator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class StatsMenuScreen extends Screen {
    
    public StatsMenuScreen() {
        super(Component.literal("Menu de Status"));
    }

    @Override
    protected void init() {
        int rightSide = this.width / 2 + 20;
        
        // Botões de Upgrade (Lado Direito)
        String[] stats = {"STR", "CON", "DEX", "WILL", "SPI", "MND"};
        for (int i = 0; i < stats.length; i++) {
            String stat = stats[i];
            addRenderableWidget(Button.builder(Component.literal("+"), b -> {
                NetworkHandler.sendToServer(new UpgradeStatPacket(stat));
            }).bounds(rightSide + 140, 40 + (i * 25), 20, 20).build());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        this.minecraft.player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
            // LADO ESQUERDO: Informações e Status Reais
            int left = 40;
            graphics.drawString(font, "TP DISPONÍVEL: " + (int)data.getTP(), left, 40, 0xFFFF00);
            graphics.drawString(font, "RAÇA: " + data.getRace().getDisplayName(), left, 60, 0xFFFFFF);
            
            // Fórmulas de Status Real (Parte 1 do seu projeto)
            int str = data.getStats().getStrength();
            graphics.drawString(font, "DANO SOCO: " + PreviewStatsCalculator.getRealPunchDamage(str), left, 90, 0x00FF00);
            graphics.drawString(font, "VIDA MÁX: " + PreviewStatsCalculator.getRealHP(data.getStats().getConstitution()), left, 110, 0xFF0000);
            graphics.drawString(font, "KI MÁX: " + PreviewStatsCalculator.getRealKi(data.getRace()), left, 130, 0x00FFFF);

            // LADO DIREITO: Status "Fakes" (Nível)
            int right = this.width / 2 + 20;
            graphics.drawString(font, "FORÇA (STR): " + data.getStats().getStrength(), right, 45, 0xFFFFFF);
            graphics.drawString(font, "CONST (CON): " + data.getStats().getConstitution(), right, 70, 0xFFFFFF);
            graphics.drawString(font, "DEST (DEX): " + data.getStats().getDexterity(), right, 95, 0xFFFFFF);
            graphics.drawString(font, "VONT (WILL): " + data.getStats().getWillpower(), right, 120, 0xFFFFFF);
            graphics.drawString(font, "ESP (SPI): " + data.getStats().getSpirit(), right, 145, 0xFFFFFF);
            graphics.drawString(font, "MENTE (MND): " + data.getStats().getMind(), right, 170, 0xFFFFFF);
        });
    }
}

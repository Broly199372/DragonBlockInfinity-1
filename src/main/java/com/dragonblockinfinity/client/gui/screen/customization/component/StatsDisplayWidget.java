package com.dragonblockinfinity.client.gui.screen.customization.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class StatsDisplayWidget {
    private final String label;
    private int value;
    private final int x, y;
    private final Consumer<String> onUpgrade;

    public StatsDisplayWidget(int x, int y, String label, int initialValue, Consumer<String> onUpgrade) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.value = initialValue;
        this.onUpgrade = onUpgrade;
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Desenha o nome do Stat e o Valor (Ex: Força: 15)
        graphics.drawString(net.minecraft.client.Minecraft.getInstance().font, 
            label + ": " + value, x, y + 5, 0xFFFFFF);

        // O custo de TP (Exemplo simples: valor atual * 2)
        int cost = value * 2;
        graphics.drawString(net.minecraft.client.Minecraft.getInstance().font, 
            "Custo: " + cost + " TP", x + 80, y + 5, 0xAAAAAA);
    }
    
    public void updateValue(int newValue) {
        this.value = newValue;
    }

    // Método para ser chamado no init() da sua Screen para adicionar o botão de "+"
    public Button createUpgradeButton() {
        return Button.builder(Component.literal("+"), b -> {
            onUpgrade.accept(label);
        }).bounds(x + 150, y, 20, 20).build();
    }
}

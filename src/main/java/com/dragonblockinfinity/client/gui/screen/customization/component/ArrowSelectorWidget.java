package com.dragonblockinfinity.client.gui.screen.customization.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import com.dragonblockinfinity.DragonBlockInfinity;
import java.util.function.Consumer;

public class ArrowSelectorWidget extends AbstractWidget {
    private static final ResourceLocation L = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/buttoes/seta_left.png");
    private static final ResourceLocation R = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/buttoes/seta_right.png");
    private final String[] options;
    private int currentIndex;
    private final Consumer<Integer> onSwitch;

    public ArrowSelectorWidget(int x, int y, int width, int height, String[] options, int startIndex, Consumer<Integer> onSwitch) {
        super(x, y, width, height, Component.empty());
        this.options = options;
        this.currentIndex = startIndex;
        this.onSwitch = onSwitch;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Renderiza as setas nas extremidades do widget
        graphics.blit(L, getX(), getY(), 0, 0, 20, 20, 20, 20);
        
        // Texto no meio (Alinhado com a altura da seta)
        graphics.drawCenteredString(net.minecraft.client.Minecraft.getInstance().font, options[currentIndex], getX() + width / 2, getY() + 6, 0xFFFFFF);
        
        graphics.blit(R, getX() + width - 20, getY(), 0, 0, 20, 20, 20, 20);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible && this.clicked(mouseX, mouseY)) {
            if (mouseX < getX() + 35) { // Área da seta esquerda
                currentIndex = (currentIndex <= 0) ? options.length - 1 : currentIndex - 1;
                onSwitch.accept(currentIndex);
                return true;
            } else if (mouseX > getX() + width - 35) { // Área da seta direita
                currentIndex = (currentIndex >= options.length - 1) ? 0 : currentIndex + 1;
                onSwitch.accept(currentIndex);
                return true;
            }
        }
        return false;
    }

    @Override protected void updateWidgetNarration(NarrationElementOutput output) {}
}

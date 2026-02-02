package com.dragonblockinfinity.client.gui.screen.customization.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import java.util.function.Consumer;

public class ColorPickerWidget {
    private final Slider r, g, b;
    private final Consumer<Integer> onColorChange;

    public ColorPickerWidget(int x, int y, Consumer<Integer> onColorChange) {
        this.onColorChange = onColorChange;
        this.r = new Slider(x, y, "R");
        this.g = new Slider(x, y + 22, "G");
        this.b = new Slider(x, y + 44, "B");
    }

    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        r.render(graphics, mouseX, mouseY, partialTick);
        g.render(graphics, mouseX, mouseY, partialTick);
        b.render(graphics, mouseX, mouseY, partialTick);
        
        // Preview da Cor
        int color = getColor();
        graphics.fill(r.getX() + 110, r.getY(), r.getX() + 140, r.getY() + 60, 0xFF000000 | color);
    }

    public int getColor() {
        return (r.getValueInt() << 16) | (g.getValueInt() << 8) | b.getValueInt();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        r.mouseClicked(mouseX, mouseY, button);
        g.mouseClicked(mouseX, mouseY, button);
        b.mouseClicked(mouseX, mouseY, button);
        onColorChange.accept(getColor());
    }

    private class Slider extends AbstractSliderButton {
        private final String label;
        public Slider(int x, int y, String label) {
            super(x, y, 100, 20, Component.literal(label), 0.5D);
            this.label = label;
        }
        public int getValueInt() { return (int)(value * 255); }
        @Override protected void updateMessage() { setMessage(Component.literal(label + ": " + getValueInt())); }
        @Override protected void applyValue() {}
    }
}

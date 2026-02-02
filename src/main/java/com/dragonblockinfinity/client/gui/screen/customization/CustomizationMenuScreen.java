package com.dragonblockinfinity.client.gui.screen.customization;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.client.gui.screen.customization.component.ArrowSelectorWidget;
import com.dragonblockinfinity.client.gui.screen.customization.component.PlayerModelRenderer;
import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import com.dragonblockinfinity.common.race.RaceEnum;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CustomizationMenuScreen extends Screen {
    private static final ResourceLocation BG = new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/menus/menu_base.png");
    private float rot = 0;

    public CustomizationMenuScreen() { super(Component.literal("Raca")); }

    @Override
    protected void init() {
        int bgW = 250; int bgH = 140;
        int x = (this.width - bgW) / 2;
        int y = (this.height - bgH) / 2;

        // "Metade-S" para caber no espaço sem bugar
        String[] races = {"Humano", "Saiyajin", "Metade-S", "Arcosiano", "Namekiano"};
        
        // Widget com 115 de largura para caber o nome e as setas
        addRenderableWidget(new ArrowSelectorWidget(x + 115, y + 50, 115, 20, races, 0, i -> {
            this.minecraft.player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(cap -> cap.setRace(RaceEnum.values()[i]));
        }));

        addRenderableWidget(Button.builder(Component.literal("OK"), b -> this.minecraft.setScreen(new FightStyleMenuScreen()))
            .bounds(x + bgW - 35, y + bgH - 22, 30, 18).build());
    }

    @Override
    public boolean mouseDragged(double mx, double my, int b, double dx, double dy) {
        this.rot -= (float)dx * 2.5F;
        return super.mouseDragged(mx, my, b, dx, dy);
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        this.renderBackground(g);
        int bgW = 250; int bgH = 140;
        int x = (this.width - bgW) / 2;
        int y = (this.height - bgH) / 2;
        g.blit(BG, x, y, 0, 0, bgW, bgH, bgW, bgH);
        
        if (this.minecraft.player != null) {
            PlayerModelRenderer.renderEntity(g, x + 55, y + 110, 55, rot, this.minecraft.player);
        }
        super.render(g, mx, my, pt);
        g.drawString(font, "RAÇA", x + 115, y + 40, 0xFFCC00, false);
        g.drawString(font, "CRIAÇÃO", x + 12, y + 10, 0xFFFFFF, false);
    }
}

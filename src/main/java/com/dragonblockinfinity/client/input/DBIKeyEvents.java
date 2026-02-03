package com.dragonblockinfinity.client.input;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.network.NetworkHandler;
import com.dragonblockinfinity.common.network.packet.ConsumeKiPacket;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import com.dragonblockinfinity.client.gui.screen.MenuInicial;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DBIKeyEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (DBIKeybinds.CONSUME_KI != null && DBIKeybinds.CONSUME_KI.consumeClick()) {
            NetworkHandler.sendToServer(new ConsumeKiPacket(10));
        }

        if (DBIKeybinds.OPEN_MENU != null && DBIKeybinds.OPEN_MENU.consumeClick()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc != null) {
                mc.setScreen(new MenuInicial());
            }
        }
    }
}



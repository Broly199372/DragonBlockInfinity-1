package com.dragonblockinfinity.client.input;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.network.NetworkHandler;
import com.dragonblockinfinity.common.network.packet.ConsumeKiPacket;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DBIKeyEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (DBIKeybinds.CONSUME_KI != null && DBIKeybinds.CONSUME_KI.consumeClick()) {
            NetworkHandler.sendToServer(new ConsumeKiPacket(10));
        }
    }
}

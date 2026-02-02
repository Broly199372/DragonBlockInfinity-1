package com.dragonblockinfinity.client.input;

import com.dragonblockinfinity.DragonBlockInfinity;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = DragonBlockInfinity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DBIKeybinds {

    public static final String CATEGORY = "key.categories.dragonblockinfinity";
    public static KeyMapping CONSUME_KI;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        CONSUME_KI = new KeyMapping(
                "key.dragonblockinfinity.consume_ki",
                GLFW.GLFW_KEY_G,
                CATEGORY
        );

        event.register(CONSUME_KI);
    }
}

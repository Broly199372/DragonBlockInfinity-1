package com.dragonblockinfinity.client.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String KEY_CATEGORY_DBI = "key.category.dragonblockinfinity";
    public static final String KEY_OPEN_MENU = "key.dragonblockinfinity.open_menu";

    public static final KeyMapping MENU_KEY = new KeyMapping(
            KEY_OPEN_MENU,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            KEY_CATEGORY_DBI
    );
}

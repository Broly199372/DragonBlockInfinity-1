package com.dragonblockinfinity.common.network.packet;

import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import com.dragonblockinfinity.common.race.RaceEnum;
import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class CustomizationPacket {
    private final String race;
    private final String style;
    private final int skin;
    private final int hair;

    public CustomizationPacket(RaceEnum race, FightStyleEnum style, int skin, int hair) {
        this.race = race.name();
        this.style = style.name();
        this.skin = skin;
        this.hair = hair;
    }

    public static void encode(CustomizationPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.race);
        buf.writeUtf(msg.style);
        buf.writeInt(msg.skin);
        buf.writeInt(msg.hair);
    }

    public static CustomizationPacket decode(FriendlyByteBuf buf) {
        return new CustomizationPacket(
            RaceEnum.valueOf(buf.readUtf()),
            FightStyleEnum.valueOf(buf.readUtf()),
            buf.readInt(),
            buf.readInt()
        );
    }

    public static void handle(CustomizationPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                    data.setRace(RaceEnum.valueOf(msg.race));
                    data.setFightStyle(FightStyleEnum.valueOf(msg.style));
                    data.setSkinColor(msg.skin);
                    data.setHairColor(msg.hair);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

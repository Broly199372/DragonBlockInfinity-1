package com.dragonblockinfinity.common.network.packet;

import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import com.dragonblockinfinity.common.network.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class UpgradeStatPacket {
    private final String statName;

    public UpgradeStatPacket(String statName) {
        this.statName = statName;
    }

    public static void encode(UpgradeStatPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.statName);
    }

    public static UpgradeStatPacket decode(FriendlyByteBuf buf) {
        return new UpgradeStatPacket(buf.readUtf());
    }

    public static void handle(UpgradeStatPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                int currentVal = 0;
                switch(msg.statName) {
                    case "STR" -> currentVal = data.getStats().getStrength();
                    case "CON" -> currentVal = data.getStats().getConstitution();
                    case "DEX" -> currentVal = data.getStats().getDexterity();
                    case "WILL" -> currentVal = data.getStats().getWillpower();
                    case "SPI" -> currentVal = data.getStats().getSpirit();
                    case "MND" -> currentVal = data.getStats().getMind();
                }

                int cost = currentVal * 2;
                if (data.getTP() >= cost) {
                    data.setTP(data.getTP() - cost);
                    switch(msg.statName) {
                        case "STR" -> data.getStats().setStrength(currentVal + 1);
                        case "CON" -> data.getStats().setConstitution(currentVal + 1);
                        case "DEX" -> data.getStats().setDexterity(currentVal + 1);
                        case "WILL" -> data.getStats().setWillpower(currentVal + 1);
                        case "SPI" -> data.getStats().setSpirit(currentVal + 1);
                        case "MND" -> data.getStats().setMind(currentVal + 1);
                    }
                    // Sincroniza os novos dados com o player
                    NetworkHandler.sendToPlayer(new SyncPlayerDataPacket(data), player);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }
}

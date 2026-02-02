package com.dragonblockinfinity.common.network.packet;

import com.dragonblockinfinity.common.capability.IPlayerData;
import com.dragonblockinfinity.common.capability.provider.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class SyncPlayerDataPacket {
    private final CompoundTag data;

    public SyncPlayerDataPacket(IPlayerData capability) {
        this.data = new CompoundTag();
        capability.saveNBTData(this.data);
    }

    public SyncPlayerDataPacket(CompoundTag nbt) {
        this.data = nbt;
    }

    public static void encode(SyncPlayerDataPacket msg, FriendlyByteBuf buf) {
        buf.writeNbt(msg.data);
    }

    public static SyncPlayerDataPacket decode(FriendlyByteBuf buf) {
        return new SyncPlayerDataPacket(buf.readNbt());
    }

    public static void handle(SyncPlayerDataPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(cap -> {
                    cap.loadNBTData(msg.data);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

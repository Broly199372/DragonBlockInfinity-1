package com.dragonblockinfinity.common.network.packet;

import com.dragonblockinfinity.client.ki.ClientKiData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncKiPacket {

    private final int ki;
    private final int maxKi;

    public SyncKiPacket(int ki, int maxKi) {
        this.ki = ki;
        this.maxKi = maxKi;
    }

    public static void encode(SyncKiPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.ki);
        buf.writeInt(msg.maxKi);
    }

    public static SyncKiPacket decode(FriendlyByteBuf buf) {
        int ki = buf.readInt();
        int maxKi = buf.readInt();
        return new SyncKiPacket(ki, maxKi);
    }

    public static void handle(SyncKiPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientKiData.set(msg.ki, msg.maxKi);
        });
        ctx.get().setPacketHandled(true);
    }
}

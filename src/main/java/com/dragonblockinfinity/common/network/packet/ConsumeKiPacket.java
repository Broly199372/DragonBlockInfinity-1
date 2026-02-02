package com.dragonblockinfinity.common.network.packet;

import com.dragonblockinfinity.common.ki.KiCapability;
import com.dragonblockinfinity.common.network.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ConsumeKiPacket {

    private final int amount;

    public ConsumeKiPacket(int amount) {
        this.amount = amount;
    }

    public static void encode(ConsumeKiPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.amount);
    }

    public static ConsumeKiPacket decode(FriendlyByteBuf buf) {
        return new ConsumeKiPacket(buf.readInt());
    }

    public static void handle(ConsumeKiPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sp = ctx.get().getSender();
            if (sp == null) return;

            sp.getCapability(KiCapability.KI).ifPresent(ki -> {
                if (ki.consume(msg.amount)) {
                    NetworkHandler.sendToPlayer(sp, new SyncKiPacket(ki.getKi(), ki.getMaxKi()));
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }
}

package com.dragonblockinfinity.common.network;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.network.packet.ConsumeKiPacket;
import com.dragonblockinfinity.common.network.packet.SyncKiPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

    private static final String PROTOCOL = "1";
    private static int id = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(DragonBlockInfinity.MOD_ID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    public static void register() {
        CHANNEL.messageBuilder(SyncKiPacket.class, nextId())
                .encoder(SyncKiPacket::encode)
                .decoder(SyncKiPacket::decode)
                .consumerMainThread(SyncKiPacket::handle)
                .add();

        CHANNEL.messageBuilder(ConsumeKiPacket.class, nextId())
                .encoder(ConsumeKiPacket::encode)
                .decoder(ConsumeKiPacket::decode)
                .consumerMainThread(ConsumeKiPacket::handle)
                .add();

        System.out.println("[DBI] Packets registrados: SyncKiPacket, ConsumeKiPacket");
    }

    private static int nextId() {
        return id++;
    }

    public static void sendToPlayer(ServerPlayer player, Object msg) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void sendToServer(Object msg) {
        CHANNEL.sendToServer(msg);
    }
}

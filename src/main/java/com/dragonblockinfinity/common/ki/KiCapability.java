package com.dragonblockinfinity.common.ki;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.common.network.NetworkHandler;
import com.dragonblockinfinity.common.network.packet.SyncKiPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KiCapability {

    public static final Capability<IKi> KI = CapabilityManager.get(new CapabilityToken<>() {});
    public static final ResourceLocation KEY = new ResourceLocation(DragonBlockInfinity.MOD_ID, "ki");

    public static class Provider implements ICapabilityProvider {
        private final KiData backend = new KiData();
        private final LazyOptional<IKi> optional = LazyOptional.of(() -> backend);

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, net.minecraft.core.Direction side) {
            return cap == KI ? optional.cast() : LazyOptional.empty();
        }

        public void invalidate() {
            optional.invalidate();
        }
    }

    public static class Events {

        @SubscribeEvent
        public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                event.addCapability(KEY, new Provider());
            }
        }

        @SubscribeEvent
        public void onPlayerClone(PlayerEvent.Clone event) {
            // Protótipo: copia ao morrer (pra não sumir durante testes)
            event.getOriginal().getCapability(KI).ifPresent(oldData -> {
                event.getEntity().getCapability(KI).ifPresent(newData -> {
                    newData.setMaxKi(oldData.getMaxKi());
                    newData.setKi(oldData.getKi());
                });
            });
        }

        @SubscribeEvent
        public void playerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;

            Player player = event.player;
            if (player.level().isClientSide) return;

            player.getCapability(KI).ifPresent(ki -> {
                int before = ki.getKi();
                ki.tickRegen();

                // Sync leve: a cada 10 ticks ou se mudou
                if (player.tickCount % 10 == 0 || ki.getKi() != before) {
                    if (player instanceof ServerPlayer sp) {
                        NetworkHandler.sendToPlayer(sp, new SyncKiPacket(ki.getKi(), ki.getMaxKi()));
                    }
                }
            });
        }

        @SubscribeEvent
        public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            if (!(event.getEntity() instanceof ServerPlayer sp)) return;

            sp.getCapability(KI).ifPresent(ki -> {
                NetworkHandler.sendToPlayer(sp, new SyncKiPacket(ki.getKi(), ki.getMaxKi()));
            });
        }
    }
}

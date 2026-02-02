package com.dragonblockinfinity.common.capability.provider;

import com.dragonblockinfinity.common.capability.IPlayerData;
import com.dragonblockinfinity.common.capability.PlayerDataCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDataProvider implements ICapabilitySerializable<CompoundTag> {
    public static Capability<IPlayerData> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<>() {});
    private final PlayerDataCapability backend = new PlayerDataCapability();
    private final LazyOptional<IPlayerData> optional = LazyOptional.of(() -> backend);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return PLAYER_DATA.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        backend.saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.loadNBTData(nbt);
    }
}

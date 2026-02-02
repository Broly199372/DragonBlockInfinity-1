package com.dragonblockinfinity.common.capability;

import com.dragonblockinfinity.common.stats.PlayerStats;
import com.dragonblockinfinity.common.race.RaceEnum;
import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;
import net.minecraft.nbt.CompoundTag;

public interface IPlayerData {
    PlayerStats getStats();
    RaceEnum getRace();
    void setRace(RaceEnum race);
    FightStyleEnum getFightStyle();
    void setFightStyle(FightStyleEnum style);
    double getTP();
    void setTP(double tp);
    void addTP(double amount);
    int getHitCounter();
    void setHitCounter(int count);
    int getSkinColor();
    void setSkinColor(int color);
    int getHairColor();
    void setHairColor(int color);
    int getHairBaseIndex();
    void setHairBaseIndex(int index);
    void saveNBTData(CompoundTag compound);
    void loadNBTData(CompoundTag compound);
}

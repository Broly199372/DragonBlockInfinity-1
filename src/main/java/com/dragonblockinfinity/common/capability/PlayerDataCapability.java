package com.dragonblockinfinity.common.capability;

import com.dragonblockinfinity.common.stats.PlayerStats;
import com.dragonblockinfinity.common.race.RaceEnum;
import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;
import net.minecraft.nbt.CompoundTag;

public class PlayerDataCapability implements IPlayerData {
    private final PlayerStats stats = new PlayerStats();
    private RaceEnum race = RaceEnum.HUMAN;
    private FightStyleEnum style = FightStyleEnum.MARTIAL_ARTS;
    private double tp = 0;
    private int hits = 0;
    private int sCol = 0xFFFFFF;
    private int hCol = 0x000000;
    private int hIndex = 1;

    @Override public PlayerStats getStats() { return stats; }
    @Override public RaceEnum getRace() { return race; }
    @Override public void setRace(RaceEnum r) { this.race = r; }
    @Override public FightStyleEnum getFightStyle() { return style; }
    @Override public void setFightStyle(FightStyleEnum s) { this.style = s; }
    @Override public double getTP() { return tp; }
    @Override public void setTP(double t) { this.tp = t; }
    @Override public void addTP(double a) { this.tp += a; }
    @Override public int getHitCounter() { return hits; }
    @Override public void setHitCounter(int c) { this.hits = c; }
    @Override public int getSkinColor() { return sCol; }
    @Override public void setSkinColor(int c) { this.sCol = c; }
    @Override public int getHairColor() { return hCol; }
    @Override public void setHairColor(int c) { this.hCol = c; }
    @Override public int getHairBaseIndex() { return hIndex; }
    @Override public void setHairBaseIndex(int i) { this.hIndex = i; }

    @Override
    public void saveNBTData(CompoundTag n) {
        n.putString("Race", race.name());
        n.putString("Style", style.name());
        n.putDouble("TP", tp);
        n.putInt("SkinColor", sCol);
        n.putInt("HairColor", hCol);
        n.putInt("HairIndex", hIndex);
        CompoundTag s = new CompoundTag();
        stats.saveToNBT(s);
        n.put("Stats", s);
    }

    @Override
    public void loadNBTData(CompoundTag n) {
        if(n.contains("Race")) this.race = RaceEnum.valueOf(n.getString("Race"));
        if(n.contains("Style")) this.style = FightStyleEnum.valueOf(n.getString("Style"));
        this.tp = n.getDouble("TP");
        this.sCol = n.getInt("SkinColor");
        this.hCol = n.getInt("HairColor");
        this.hIndex = n.getInt("HairIndex");
        if(n.contains("Stats")) stats.loadFromNBT(n.getCompound("Stats"));
    }
}

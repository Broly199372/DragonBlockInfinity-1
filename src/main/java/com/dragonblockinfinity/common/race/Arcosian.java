package com.dragonblockinfinity.common.race;

import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;

public class Arcosian implements IRace {
    
    @Override public int getMaxKiBase() { return 6000; }
    @Override public double getKiMultiplier() { return 1.8; }
    @Override public FightStyleEnum getPreferredFightStyle() { return FightStyleEnum.WARRIOR; }
    
    @Override public int getBaseStrength() { return 14; }
    @Override public int getBaseConstitution() { return 13; }
    @Override public int getBaseDexterity() { return 9; }
    @Override public int getBaseWillpower() { return 9; }
    @Override public int getBaseSpirit() { return 9; }
    @Override public int getBaseMind() { return 10; }
    
    @Override public String getRaceName() { return "Arcosian"; }
    @Override public String getRaceDescription() { 
        return "Tirano intergaláctico com poder lendário"; 
    }
}

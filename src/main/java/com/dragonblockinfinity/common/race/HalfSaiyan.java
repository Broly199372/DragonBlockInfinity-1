package com.dragonblockinfinity.common.race;

import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;

public class HalfSaiyan implements IRace {
    
    @Override public int getMaxKiBase() { return 4000; }
    @Override public double getKiMultiplier() { return 1.4; }
    @Override public FightStyleEnum getPreferredFightStyle() { return FightStyleEnum.MARTIAL_ARTS; }
    
    @Override public int getBaseStrength() { return 12; }
    @Override public int getBaseConstitution() { return 11; }
    @Override public int getBaseDexterity() { return 11; }
    @Override public int getBaseWillpower() { return 10; }
    @Override public int getBaseSpirit() { return 11; }
    @Override public int getBaseMind() { return 10; }
    
    @Override public String getRaceName() { return "Half-Saiyajin"; }
    @Override public String getRaceDescription() { 
        return "Híbrido com potencial extraordinário"; 
    }
}

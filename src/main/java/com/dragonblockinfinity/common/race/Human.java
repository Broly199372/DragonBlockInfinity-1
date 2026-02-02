package com.dragonblockinfinity.common.race;

import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;

public class Human implements IRace {
    
    @Override public int getMaxKiBase() { return 3000; }
    @Override public double getKiMultiplier() { return 1.0; }
    @Override public FightStyleEnum getPreferredFightStyle() { return FightStyleEnum.MARTIAL_ARTS; }
    
    @Override public int getBaseStrength() { return 11; }
    @Override public int getBaseConstitution() { return 11; }
    @Override public int getBaseDexterity() { return 11; }
    @Override public int getBaseWillpower() { return 11; }
    @Override public int getBaseSpirit() { return 11; }
    @Override public int getBaseMind() { return 12; }
    
    @Override public String getRaceName() { return "Humano"; }
    @Override public String getRaceDescription() { 
        return "Adaptável e determinado com grande potencial de aprendizado"; 
    }
}

package com.dragonblockinfinity.common.race;

import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;

public class Saiyan implements IRace {
    
    @Override public int getMaxKiBase() { return 5000; }
    @Override public double getKiMultiplier() { return 1.5; }
    @Override public FightStyleEnum getPreferredFightStyle() { return FightStyleEnum.WARRIOR; }
    
    @Override public int getBaseStrength() { return 15; }
    @Override public int getBaseConstitution() { return 14; }
    @Override public int getBaseDexterity() { return 12; }
    @Override public int getBaseWillpower() { return 10; }
    @Override public int getBaseSpirit() { return 10; }
    @Override public int getBaseMind() { return 8; }
    
    @Override public String getRaceName() { return "Saiyajin"; }
    @Override public String getRaceDescription() { 
        return "Guerreiros natos com grande potencial de poder e transformações"; 
    }
}

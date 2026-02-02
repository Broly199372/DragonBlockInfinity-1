package com.dragonblockinfinity.common.race;

import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;

public interface IRace {
    
    int getMaxKiBase();
    double getKiMultiplier();
    FightStyleEnum getPreferredFightStyle();
    
    int getBaseStrength();
    int getBaseConstitution();
    int getBaseDexterity();
    int getBaseWillpower();
    int getBaseSpirit();
    int getBaseMind();
    
    String getRaceName();
    String getRaceDescription();
    
    default int calculateMaxKi() {
        return (int)(getMaxKiBase() * getKiMultiplier());
    }
}

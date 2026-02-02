package com.dragonblockinfinity.common.race;

import com.dragonblockinfinity.common.fightstyle.FightStyleEnum;

public class Namekian implements IRace {
    
    @Override public int getMaxKiBase() { return 4500; }
    @Override public double getKiMultiplier() { return 1.8; }
    @Override public FightStyleEnum getPreferredFightStyle() { return FightStyleEnum.SPIRITUALIST; }
    
    @Override public int getBaseStrength() { return 9; }
    @Override public int getBaseConstitution() { return 10; }
    @Override public int getBaseDexterity() { return 10; }
    @Override public int getBaseWillpower() { return 14; }
    @Override public int getBaseSpirit() { return 15; }
    @Override public int getBaseMind() { return 12; }
    
    @Override public String getRaceName() { return "Namekiano"; }
    @Override public String getRaceDescription() { 
        return "Místicos e regenerativos com grande afinidade com Ki"; 
    }
}

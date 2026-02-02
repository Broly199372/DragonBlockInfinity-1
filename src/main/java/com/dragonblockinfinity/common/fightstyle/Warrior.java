package com.dragonblockinfinity.common.fightstyle;

public class Warrior implements IFightStyle {
    
    @Override public double getStrengthMultiplier() { return 2.2; }
    @Override public double getConstitutionMultiplier() { return 2.0; }
    @Override public double getDexterityMultiplier() { return 1.2; }
    @Override public double getWillMultiplier() { return 0.8; }
    @Override public double getSpiritMultiplier() { return 0.5; }
    @Override public double getMindMultiplier() { return 0.8; }
    
    @Override public int getBaseStrength() { return 14; }
    @Override public int getBaseConstitution() { return 13; }
    @Override public int getBaseDexterity() { return 10; }
    @Override public int getBaseWillpower() { return 9; }
    @Override public int getBaseSpirit() { return 8; }
    @Override public int getBaseMind() { return 9; }
    
    @Override public String getStyleName() { return "Guerreiro"; }
    @Override public String getStyleDescription() { 
        return "Focado em força e resistência corporal. Excelente em combate corpo a corpo mas fraco em Ki."; 
    }
    
    @Override
    public int calculateBP(int str, int con, int dex, int will, int spi, int mnd) {
        return (int)(
            (str * getStrengthMultiplier()) +
            (con * getConstitutionMultiplier()) +
            (dex * getDexterityMultiplier()) +
            (will * getWillMultiplier()) +
            (spi * getSpiritMultiplier()) +
            (mnd * getMindMultiplier())
        );
    }
}

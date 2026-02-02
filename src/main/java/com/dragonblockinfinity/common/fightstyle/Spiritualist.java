package com.dragonblockinfinity.common.fightstyle;

public class Spiritualist implements IFightStyle {
    
    @Override public double getStrengthMultiplier() { return 0.5; }
    @Override public double getConstitutionMultiplier() { return 0.8; }
    @Override public double getDexterityMultiplier() { return 1.2; }
    @Override public double getWillMultiplier() { return 2.0; }
    @Override public double getSpiritMultiplier() { return 2.2; }
    @Override public double getMindMultiplier() { return 1.0; }
    
    @Override public int getBaseStrength() { return 8; }
    @Override public int getBaseConstitution() { return 9; }
    @Override public int getBaseDexterity() { return 10; }
    @Override public int getBaseWillpower() { return 13; }
    @Override public int getBaseSpirit() { return 14; }
    @Override public int getBaseMind() { return 11; }
    
    @Override public String getStyleName() { return "Espiritualista"; }
    @Override public String getStyleDescription() { 
        return "Focado em Ki e ataques mágicos. Excelente em ofensiva mágica mas fraco em combate corpo a corpo."; 
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

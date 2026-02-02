package com.dragonblockinfinity.common.fightstyle;

public class MartialArts implements IFightStyle {
    
    @Override public double getStrengthMultiplier() { return 1.8; }
    @Override public double getConstitutionMultiplier() { return 1.8; }
    @Override public double getDexterityMultiplier() { return 1.8; }
    @Override public double getWillMultiplier() { return 1.2; }
    @Override public double getSpiritMultiplier() { return 1.2; }
    @Override public double getMindMultiplier() { return 1.0; }
    
    @Override public int getBaseStrength() { return 11; }
    @Override public int getBaseConstitution() { return 11; }
    @Override public int getBaseDexterity() { return 12; }
    @Override public int getBaseWillpower() { return 11; }
    @Override public int getBaseSpirit() { return 11; }
    @Override public int getBaseMind() { return 11; }
    
    @Override public String getStyleName() { return "Artes Marciais"; }
    @Override public String getStyleDescription() { 
        return "Estilo equilibrado e versátil. Bom em vários aspectos mas não se destaca em nenhum."; 
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

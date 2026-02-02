package com.dragonblockinfinity.common.fightstyle;

public interface IFightStyle {
    
    double getStrengthMultiplier();
    double getConstitutionMultiplier();
    double getDexterityMultiplier();
    double getWillMultiplier();
    double getSpiritMultiplier();
    double getMindMultiplier();
    
    int getBaseStrength();
    int getBaseConstitution();
    int getBaseDexterity();
    int getBaseWillpower();
    int getBaseSpirit();
    int getBaseMind();
    
    String getStyleName();
    String getStyleDescription();
    
    int calculateBP(int str, int con, int dex, int will, int spi, int mnd);
}

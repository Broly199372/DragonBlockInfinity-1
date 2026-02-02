package com.dragonblockinfinity.common.race;

public enum RaceEnum {
    SAIYAN("Saiyajin", "Guerreiros natos com grande potencial"),
    HALF("Metade-Saiyajin", "Híbrido com potencial extraordinário"),
    HUMAN("Humano", "Adaptável e determinado"),
    ARCONSIAN("Arconsiano", "Guerreiro galáctico frio e poderoso"),
    NAMEKIAN("Namekiano", "Místicos e regenerativos");
    
    private final String displayName;
    private final String description;
    
    RaceEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}

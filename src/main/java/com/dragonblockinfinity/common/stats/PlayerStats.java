package com.dragonblockinfinity.common.stats;

import com.dragonblockinfinity.common.race.IRace;
import com.dragonblockinfinity.common.fightstyle.IFightStyle;
import net.minecraft.nbt.CompoundTag;

/**
 * Stats consolidados do jogador
 * Combina Race + FightStyle para calcular valores finais
 */
public class PlayerStats {
    
    // Stats base
    private int strength;
    private int constitution;
    private int dexterity;
    private int willpower;
    private int spirit;
    private int mind;
    
    // Ki e Vida
    private int currentKi;
    private int maxKi;
    private int currentHealth;
    private int maxHealth;
    private int currentStamina;
    private int maxStamina;
    
    // Battle Power
    private int battlePower;
    
    public PlayerStats() {
        this.strength = 10;
        this.constitution = 10;
        this.dexterity = 10;
        this.willpower = 10;
        this.spirit = 10;
        this.mind = 10;
        
        this.maxKi = 1000;
        this.currentKi = maxKi;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.maxStamina = 100;
        this.currentStamina = maxStamina;
        
        this.battlePower = 0;
    }
    
    /**
     * Inicializa stats baseado em Race + FightStyle
     */
    public void initializeFromRaceAndStyle(IRace race, IFightStyle style) {
        // Stats base = Race base + FightStyle base
        this.strength = race.getBaseStrength() + style.getBaseStrength();
        this.constitution = race.getBaseConstitution() + style.getBaseConstitution();
        this.dexterity = race.getBaseDexterity() + style.getBaseDexterity();
        this.willpower = race.getBaseWillpower() + style.getBaseWillpower();
        this.spirit = race.getBaseSpirit() + style.getBaseSpirit();
        this.mind = race.getBaseMind() + style.getBaseMind();
        
        // Ki máximo = Race Ki
        this.maxKi = race.calculateMaxKi();
        this.currentKi = maxKi;
        
        // Vida = Constitution * 5
        this.maxHealth = constitution * 5;
        this.currentHealth = maxHealth;
        
        // Stamina = Constitution * 5
        this.maxStamina = constitution * 5;
        this.currentStamina = maxStamina;
        
        // Calcula BP
        recalculateBP(style);
    }
    
    /**
     * Recalcula Battle Power baseado no FightStyle
     */
    public void recalculateBP(IFightStyle style) {
        this.battlePower = style.calculateBP(strength, constitution, dexterity, willpower, spirit, mind);
    }
    
    // ===== GETTERS =====
    public int getStrength() { return strength; }
    public int getConstitution() { return constitution; }
    public int getDexterity() { return dexterity; }
    public int getWillpower() { return willpower; }
    public int getSpirit() { return spirit; }
    public int getMind() { return mind; }
    
    public int getCurrentKi() { return currentKi; }
    public int getMaxKi() { return maxKi; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getCurrentStamina() { return currentStamina; }
    public int getMaxStamina() { return maxStamina; }
    
    public int getBattlePower() { return battlePower; }
    
    public double getKiPercentage() {
        return maxKi > 0 ? (double)currentKi / maxKi : 0.0;
    }
    
    public double getHealthPercentage() {
        return maxHealth > 0 ? (double)currentHealth / maxHealth : 0.0;
    }
    
    public double getStaminaPercentage() {
        return maxStamina > 0 ? (double)currentStamina / maxStamina : 0.0;
    }
    
    // ===== SETTERS =====
    public void setStrength(int value) { this.strength = value; }
    public void setConstitution(int value) { 
        this.constitution = value;
        this.maxHealth = value * 5;
        this.maxStamina = value * 5;
    }
    public void setDexterity(int value) { this.dexterity = value; }
    public void setWillpower(int value) { this.willpower = value; }
    public void setSpirit(int value) { this.spirit = value; }
    public void setMind(int value) { this.mind = value; }
    
    public void setCurrentKi(int value) { this.currentKi = Math.max(0, Math.min(value, maxKi)); }
    public void setMaxKi(int value) { this.maxKi = value; }
    public void setCurrentHealth(int value) { this.currentHealth = Math.max(0, Math.min(value, maxHealth)); }
    public void setCurrentStamina(int value) { this.currentStamina = Math.max(0, Math.min(value, maxStamina)); }
    
    // ===== KI OPERATIONS =====
    public void consumeKi(int amount) {
        currentKi = Math.max(0, currentKi - amount);
    }
    
    public void restoreKi(int amount) {
        currentKi = Math.min(currentKi + amount, maxKi);
    }
    
    public boolean hasKi(int required) {
        return currentKi >= required;
    }
    
    // ===== HEALTH OPERATIONS =====
    public void heal(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }
    
    public void takeDamage(int amount) {
        currentHealth = Math.max(0, currentHealth - amount);
    }
    
    // ===== STAMINA OPERATIONS =====
    public void consumeStamina(int amount) {
        currentStamina = Math.max(0, currentStamina - amount);
    }
    
    public void restoreStamina(int amount) {
        currentStamina = Math.min(currentStamina + amount, maxStamina);
    }
    
    public boolean hasStamina(int required) {
        return currentStamina >= required;
    }
    
    // ===== NBT SERIALIZATION =====
    public void saveToNBT(CompoundTag nbt) {
        nbt.putInt("Strength", strength);
        nbt.putInt("Constitution", constitution);
        nbt.putInt("Dexterity", dexterity);
        nbt.putInt("Willpower", willpower);
        nbt.putInt("Spirit", spirit);
        nbt.putInt("Mind", mind);
        
        nbt.putInt("CurrentKi", currentKi);
        nbt.putInt("MaxKi", maxKi);
        nbt.putInt("CurrentHealth", currentHealth);
        nbt.putInt("MaxHealth", maxHealth);
        nbt.putInt("CurrentStamina", currentStamina);
        nbt.putInt("MaxStamina", maxStamina);
        
        nbt.putInt("BattlePower", battlePower);
    }
    
    public void loadFromNBT(CompoundTag nbt) {
        this.strength = nbt.getInt("Strength");
        this.constitution = nbt.getInt("Constitution");
        this.dexterity = nbt.getInt("Dexterity");
        this.willpower = nbt.getInt("Willpower");
        this.spirit = nbt.getInt("Spirit");
        this.mind = nbt.getInt("Mind");
        
        this.currentKi = nbt.getInt("CurrentKi");
        this.maxKi = nbt.getInt("MaxKi");
        this.currentHealth = nbt.getInt("CurrentHealth");
        this.maxHealth = nbt.getInt("MaxHealth");
        this.currentStamina = nbt.getInt("CurrentStamina");
        this.maxStamina = nbt.getInt("MaxStamina");
        
        this.battlePower = nbt.getInt("BattlePower");
    }
    
    @Override
    public String toString() {
        return "PlayerStats{" +
                "BP=" + battlePower +
                ", Stats=[Str=" + strength +
                ", Con=" + constitution +
                ", Dex=" + dexterity +
                ", Will=" + willpower +
                ", Spi=" + spirit +
                ", Mnd=" + mind +
                "], Ki=" + currentKi + "/" + maxKi +
                ", HP=" + currentHealth + "/" + maxHealth +
                ", Stm=" + currentStamina + "/" + maxStamina +
                '}';
    }
}

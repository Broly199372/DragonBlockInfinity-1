package com.dragonblockinfinity.common.ki;

public class KiData implements IKi {

    private int ki = 100;
    private int maxKi = 100;

    // Protótipo: +1 Ki a cada 10 ticks (~0,5s)
    private int regenCooldown = 0;

    @Override
    public int getKi() {
        return ki;
    }

    @Override
    public int getMaxKi() {
        return maxKi;
    }

    @Override
    public void setKi(int value) {
        this.ki = Math.max(0, Math.min(value, maxKi));
    }

    @Override
    public void setMaxKi(int value) {
        this.maxKi = Math.max(1, value);
        if (ki > maxKi) ki = maxKi;
    }

    @Override
    public boolean consume(int amount) {
        if (amount <= 0) return true;
        if (ki >= amount) {
            ki -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void tickRegen() {
        regenCooldown++;
        if (regenCooldown >= 10) {
            regenCooldown = 0;
            if (ki < maxKi) ki++;
        }
    }
}

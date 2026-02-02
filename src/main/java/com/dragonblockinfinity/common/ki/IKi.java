package com.dragonblockinfinity.common.ki;

public interface IKi {
    int getKi();
    int getMaxKi();

    void setKi(int value);
    void setMaxKi(int value);

    boolean consume(int amount);

    void tickRegen();
}

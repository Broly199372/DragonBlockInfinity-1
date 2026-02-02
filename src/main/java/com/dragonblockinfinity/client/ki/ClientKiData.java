package com.dragonblockinfinity.client.ki;

public class ClientKiData {
    private static int ki = 0;
    private static int maxKi = 100;

    public static void set(int newKi, int newMax) {
        ki = Math.max(0, newKi);
        maxKi = Math.max(1, newMax);
    }

    public static int getKi() {
        return ki;
    }

    public static int getMaxKi() {
        return maxKi;
    }
}

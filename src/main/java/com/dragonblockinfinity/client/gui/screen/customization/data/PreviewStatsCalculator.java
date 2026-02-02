package com.dragonblockinfinity.client.gui.screen.customization.data;

import com.dragonblockinfinity.common.race.*;
import com.dragonblockinfinity.common.fightstyle.*;

public class PreviewStatsCalculator {
    
    public static int getFakeSTR(RaceEnum race, FightStyleEnum style) {
        return getRaceBase(race).getBaseStrength() + getStyleBase(style).getBaseStrength();
    }

    public static int getFakeCON(RaceEnum race, FightStyleEnum style) {
        return getRaceBase(race).getBaseConstitution() + getStyleBase(style).getBaseConstitution();
    }

    // Fórmulas Reais (Baseado no seu PROJETO_PARTE_1)
    public static double getRealPunchDamage(int str) {
        return (str * 1.5) + 5;
    }

    public static int getRealHP(int con) {
        return con * 5;
    }

    public static int getRealKi(RaceEnum race) {
        IRace r = getRaceBase(race);
        return (int)(r.getMaxKiBase() * r.getKiMultiplier());
    }

    // Helpers para pegar instâncias das classes que você já criou
    private static IRace getRaceBase(RaceEnum race) {
        return switch (race) {
            case SAIYAN -> new Saiyan();
            case HALF -> new HalfSaiyan();
            case ARCONSIAN -> new Arcosian();
            case NAMEKIAN -> new Namekian();
            default -> new Human();
        };
    }

    private static IFightStyle getStyleBase(FightStyleEnum style) {
        return switch (style) {
            case WARRIOR -> new Warrior();
            case SPIRITUALIST -> new Spiritualist();
            default -> new MartialArts();
        };
    }
}

package com.dragonblockinfinity.common.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class NuvemEntity extends Mob {
    
    public NuvemEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
    }
}

package com.dragonblockinfinity.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;

public class DirtyStoneBlock extends Block {

    public DirtyStoneBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE)
            .strength(1.5f, 6.0f)
            .sound(SoundType.STONE));
    }
}

package com.dragonblockinfinity.registry;

import net.minecraft.world.level.block.Block;
import com.dragonblockinfinity.common.blocks.DirtyStoneBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, "dragonblockinfinity");
    
    public static final RegistryObject<Block> DIRTY_STONE = BLOCKS.register("dirty_stone",
        DirtyStoneBlock::new);
}

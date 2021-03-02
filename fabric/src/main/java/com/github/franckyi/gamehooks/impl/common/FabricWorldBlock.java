package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.BlockPos;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.tag.FabricTagFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtHelper;

public class FabricWorldBlock implements WorldBlock {
    private final BlockPos blockPos;
    private final BlockState state;
    private final BlockEntity blockEntity;

    public FabricWorldBlock(BlockPos blockPos, BlockState state, BlockEntity blockEntity) {
        this.blockPos = blockPos;
        this.state = state;
        this.blockEntity = blockEntity;
    }

    @Override
    public BlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public CompoundTag getState() {
        return state == null ? null : FabricTagFactory.parseCompound(NbtHelper.fromBlockState(state));
    }

    @Override
    public CompoundTag getData() {
        return blockEntity == null ? null : FabricTagFactory.parseCompound(blockEntity.toTag(new net.minecraft.nbt.CompoundTag()));
    }
}

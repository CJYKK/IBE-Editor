package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;

public interface Item {
    CompoundTag getData();

    boolean isBlockItem();

    boolean isPotionItem();

    boolean isDyeableItem();

    <S> S get();
}

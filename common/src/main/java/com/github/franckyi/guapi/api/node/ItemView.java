package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import net.minecraft.world.item.ItemStack;

public interface ItemView extends Control {
    default ItemStack getItem() {
        return itemProperty().getValue();
    }

    ObjectProperty<ItemStack> itemProperty();

    default void setItem(ItemStack value) {
        itemProperty().setValue(value);
    }
}

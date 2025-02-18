package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.ibeeditor.base.client.mvc.model.category.EntityCategoryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class EntityEditorModel extends StandardEditorModel<CompoundTag, EntityCategoryModel> {
    public EntityEditorModel(CompoundTag entity, Consumer<CompoundTag> action, Component disabledTooltip) {
        super(entity, action, disabledTooltip, ModTexts.ENTITY);
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public CompoundTag applyChanges() {
        return getTarget(); // TODO
    }
}

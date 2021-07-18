package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.entry.item;

import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.minecraft.api.common.Enchantment;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class EnchantmentEditorEntryModel extends IntegerEditorEntryModel {
    public EnchantmentEditorEntryModel(EditorCategoryModel category, Enchantment enchantment, boolean canApply, int value, Consumer<Integer> action) {
        super(category, translated(enchantment.getName()), value, action);
        if (enchantment.isCurse()) {
            getLabel().setColor(Color.RED);
        } else if (canApply) {
            getLabel().setColor(Color.GREEN);
        }
    }

    @Override
    public Type getType() {
        return Type.ENCHANTMENT;
    }
}

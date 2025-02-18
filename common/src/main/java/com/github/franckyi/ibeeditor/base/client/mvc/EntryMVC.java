package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.*;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.*;

public final class EntryMVC implements MVC<EntryModel, EntryView, EntryController<EntryModel, EntryView>> {
    public static final EntryMVC INSTANCE = new EntryMVC();

    @Override
    public EntryView setup(EntryModel model) {
        return switch (model.getType()) {
            case STRING -> MVC.createViewAndBind((StringEntryModel) model, StringEntryView::new, StringEntryController::new);
            case INTEGER -> MVC.createViewAndBind((IntegerEntryModel) model, IntegerEntryView::new, IntegerEntryController::new);
            case TEXT -> MVC.createViewAndBind((TextEntryModel) model, TextEntryView::new, TextEntryController::new);
            case ENUM -> createEnumViewAndBind((EnumEntryModel<?>) model);
            case ACTION -> MVC.createViewAndBind((ActionEntryModel) model, ActionEntryView::new, ActionEntryController::new);
            case ADD_LIST_ENTRY -> MVC.createViewAndBind((AddListEntryEntryModel) model, AddListEntryEntryView::new, AddListEntryEntryController::new);
            case BOOLEAN -> MVC.createViewAndBind((BooleanEntryModel) model, BooleanEntryView::new, BooleanEntryController::new);
            case ENCHANTMENT -> MVC.createViewAndBind((EnchantmentEntryModel) model, EnchantmentEntryView::new, EnchantmentEntryController::new);
            case HIDE_FLAG -> MVC.createViewAndBind((HideFlagEntryModel) model, HideFlagEntryView::new, HideFlagEntryController::new);
            case ATTRIBUTE_MODIFIER -> MVC.createViewAndBind((AttributeModifierEntryModel) model, AttributeModifierEntryView::new, AttributeModifierEntryController::new);
            case SELECTION -> MVC.createViewAndBind((SelectionEntryModel) model, SelectionEntryView::new, SelectionEntryController::new);
            case SELECTION_POTION -> MVC.createViewAndBind(((PotionSelectionEntryModel) model), PotionSelectionEntryView::new, PotionSelectionEntryController::new);
            case POTION_EFFECT -> MVC.createViewAndBind(((PotionEffectEntryModel) model), PotionEffectEntryView::new, PotionEffectEntryController::new);
            case ARMOR_COLOR -> MVC.createViewAndBind((ArmorColorEntryModel) model, ArmorColorEntryView::new, ArmorColorEntryController::new);
        };
    }

    private <E extends Enum<E>> EntryView createEnumViewAndBind(EnumEntryModel<E> model) {
        return MVC.createViewAndBind(model, () -> new EnumEntryView<>(model.getValue().getDeclaringClass()), EnumEntryController::new);
    }
}

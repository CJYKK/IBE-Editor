package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.editor;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry.TextEntryController;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

public abstract class AbstractEditorModel<T, C extends CategoryModel> implements EditorModel {
    private final T target;
    private final Consumer<T> action;
    private final Text disabledTooltip;
    private final ObservableList<C> categories = DataBindings.getObservableListFactory().createObservableArrayList();
    private final ObjectProperty<CategoryModel> selectedCategory = DataBindings.getPropertyFactory().createObjectProperty();
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);
    private final ObjectProperty<TextEntryController> focusedTextEntryProperty = DataBindings.getPropertyFactory().createObjectProperty();

    protected AbstractEditorModel(T target, Consumer<T> action, Text disabledTooltip) {
        this.target = target;
        this.action = action;
        this.disabledTooltip = disabledTooltip;
        selectedCategoryProperty().addListener((oldVal, newVal) -> {
            if (oldVal != null) {
                oldVal.setSelected(false);
            }
            if (newVal != null) {
                newVal.setSelected(true);
            }
        });
        getCategories().addListener(() -> {
            if (!selectedCategoryProperty().hasValue() && getCategories().size() > 0) {
                setSelectedCategory(getCategories().get(0));
            }
            updateValidity();
        });
    }

    public T getTarget() {
        return target;
    }

    public Consumer<T> getAction() {
        return action;
    }

    @Override
    public ObservableList<C> getCategories() {
        return categories;
    }

    @Override
    public ObjectProperty<CategoryModel> selectedCategoryProperty() {
        return selectedCategory;
    }

    @Override
    public Text getDisabledTooltip() {
        return disabledTooltip;
    }

    @Override
    public BooleanProperty validProperty() {
        return validProperty;
    }

    @Override
    public ObjectProperty<TextEntryController> focusedTextEntryProperty() {
        return focusedTextEntryProperty;
    }

    @Override
    public void apply() {
        getAction().accept(applyChanges());
    }

    @Override
    public void updateValidity() {
        setValid(getCategories().stream().allMatch(CategoryModel::isValid));
    }

    protected abstract T applyChanges();
}

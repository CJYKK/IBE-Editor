package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.SelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.SelectionEntryView;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class SelectionEntryController<M extends SelectionEntryModel, V extends SelectionEntryView> extends StringEntryController<M, V> {
    public SelectionEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setPlaceholder(model.getSelectionScreenTitle());
        view.getTextField().getSuggestions().setAll(model.getSuggestions());
        view.getSelectionScreenButton().getTooltip().add(ModTexts.choose(model.getSelectionScreenTitle()));
        view.getSelectionScreenButton().onAction(this::openSelectionScreen);
        if (model.getLabel() == null) {
            view.getRoot().getChildren().remove(view.getLabel());
        }
    }

    private void openSelectionScreen() {
        ModScreenHandler.openListSelectionScreen(model.getSelectionScreenTitle(),
                model.getValue().contains(":") ? model.getValue() : "minecraft:" + model.getValue(),
                model.getSelectionItems(), model::setValue);
    }
}

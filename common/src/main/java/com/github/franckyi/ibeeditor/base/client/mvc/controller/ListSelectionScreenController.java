package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ListSelectionScreenView;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.stream.Collectors;

public class ListSelectionScreenController extends AbstractController<ListSelectionScreenModel, ListSelectionScreenView> {
    public ListSelectionScreenController(ListSelectionScreenModel model, ListSelectionScreenView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getHeaderLabel().setLabel(ModTexts.title(ModTexts.choose(model.getTitle())));
        view.getSearchField().textProperty().addListener(this::filter);
        model.getItems().forEach(item -> {
            if (item.getId().toString().equals(model.getInitialValue())) {
                view.getListView().setFocusedElement(item);
            }
        });
        view.getListView().focusedElementProperty().addListener(this::refreshButton);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
        view.getDoneButton().onAction(() -> {
            model.getAction().accept(view.getListView().getFocusedElement().getId().toString());
            Guapi.getScreenHandler().hideScene();
        });
        refreshButton();
        filter("");
    }

    private void filter(String filter) {
        view.getListView().getItems().setAll(model.getItems()
                .stream()
                .filter(item -> item.matches(filter))
                .limit(ClientConfiguration.INSTANCE.getSelectionScreenMaxItems())
                .toList());
    }

    private void refreshButton() {
        view.getDoneButton().setDisable(view.getListView().getFocusedElement() == null);
    }
}

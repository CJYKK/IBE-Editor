package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.util.Align;
import com.github.franckyi.ibeeditor.base.client.ModTextures;

import static com.github.franckyi.guapi.api.GuapiHelper.hBox;
import static com.github.franckyi.guapi.api.GuapiHelper.texturedButton;

public class AddListEntryEntryView extends EntryView {
    private TexturedButton button;

    @Override
    public void build() {
        super.build();
        getRoot().setAlignment(Align.TOP_CENTER);
    }

    @Override
    protected Node createContent() {
        return hBox(button = texturedButton(ModTextures.ADD, 16, 16, false)).align(Align.CENTER);
    }

    public TexturedButton getButton() {
        return button;
    }
}

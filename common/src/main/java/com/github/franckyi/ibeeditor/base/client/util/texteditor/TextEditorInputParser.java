package com.github.franckyi.ibeeditor.base.client.util.texteditor;

import net.minecraft.network.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TextEditorInputParser {
    private final List<Formatting> formattings = new ArrayList<>();
    private int index;

    public void parse(TextComponent text) {
        int length = text.getText().length();
        if (text.getStyle().isBold()) {
            addStyleFormatting(new StyleFormatting(index, index + length, StyleType.BOLD));
        }
        if (text.getStyle().isItalic()) {
            addStyleFormatting(new StyleFormatting(index, index + length, StyleType.ITALIC));
        }
        if (text.getStyle().isUnderlined()) {
            addStyleFormatting(new StyleFormatting(index, index + length, StyleType.UNDERLINED));
        }
        if (text.getStyle().isStrikethrough()) {
            addStyleFormatting(new StyleFormatting(index, index + length, StyleType.STRIKETHROUGH));
        }
        if (text.getStyle().isObfuscated()) {
            addStyleFormatting(new StyleFormatting(index, index + length, StyleType.OBFUSCATED));
        }
        if (text.getStyle().getColor() != null) {
            addColorFormatting(new ColorFormatting(index, index + length, text.getStyle().getColor().toString()));
        }
        if (text.getText() != null) {
            index += text.getText().length();
        }
        if (text.getSiblings() != null) {
            text.getSiblings().stream()
                    .filter(TextComponent.class::isInstance)
                    .map(TextComponent.class::cast)
                    .forEach(this::parse);
        }
    }

    private void addStyleFormatting(StyleFormatting formatting) {
        Optional<StyleFormatting> merge = formattings.stream()
                .filter(StyleFormatting.class::isInstance)
                .map(StyleFormatting.class::cast)
                .filter(other -> other.getType() == formatting.getType() && other.getEnd() == formatting.getStart())
                .findAny();
        if (merge.isPresent()) {
            merge.get().setEnd(formatting.getEnd());
        } else {
            formattings.add(formatting);
        }
    }

    private void addColorFormatting(ColorFormatting formatting) {
        Optional<ColorFormatting> merge = formattings.stream()
                .filter(ColorFormatting.class::isInstance)
                .map(ColorFormatting.class::cast)
                .filter(other -> Objects.equals(other.getColor(), formatting.getColor()) && other.getEnd() == formatting.getStart())
                .findAny();
        if (merge.isPresent()) {
            merge.get().setEnd(formatting.getEnd());
        } else {
            formattings.add(formatting);
        }
    }

    public List<Formatting> getFormattings() {
        return formattings;
    }
}

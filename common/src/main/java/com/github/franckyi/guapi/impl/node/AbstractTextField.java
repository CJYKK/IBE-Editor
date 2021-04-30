package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.*;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Predicate;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractTextField extends AbstractLabeled implements TextField {
    private final StringProperty textProperty = DataBindings.getPropertyFactory().createStringProperty("");
    private final IntegerProperty maxLengthProperty = DataBindings.getPropertyFactory().createIntegerProperty(Integer.MAX_VALUE);
    private final ObjectProperty<Predicate<String>> validatorProperty = DataBindings.getPropertyFactory().createObjectProperty(s -> true);
    private final BooleanProperty validationForcedProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    protected final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final ObservableBooleanValue validPropertyReadOnly = DataBindings.getPropertyFactory().createReadOnlyProperty(validProperty);
    private final ObjectProperty<TextRenderer> textRendererProperty = DataBindings.getPropertyFactory().createObjectProperty();

    protected AbstractTextField() {
        this("");
    }

    protected AbstractTextField(String value) {
        this(emptyText(), value);
    }

    protected AbstractTextField(String label, String value) {
        this(text(label), value);
    }

    protected AbstractTextField(Text label, String value) {
        super(label);
        setText(value);
        textProperty().addListener(this::updateValid);
        validatorProperty().addListener(this::updateValid);
    }

    @Override
    public StringProperty textProperty() {
        return textProperty;
    }

    @Override
    public IntegerProperty maxLengthProperty() {
        return maxLengthProperty;
    }

    @Override
    public ObjectProperty<Predicate<String>> validatorProperty() {
        return validatorProperty;
    }

    @Override
    public BooleanProperty validationForcedProperty() {
        return validationForcedProperty;
    }

    @Override
    public ObservableBooleanValue validProperty() {
        return validPropertyReadOnly;
    }

    @Override
    public ObjectProperty<TextRenderer> textRendererProperty() {
        return textRendererProperty;
    }

    private void updateValid() {
        validProperty.setValue(getValidator().test(getText()));
    }
}

package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.builder.ListViewBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

import java.util.Collection;

public class ListViewImpl<E> extends AbstractListView<E> implements ListViewBuilder<E> {
    public ListViewImpl() {
        super();
    }

    public ListViewImpl(int itemHeight) {
        super(itemHeight);
    }

    @SafeVarargs
    public ListViewImpl(int itemHeight, E... items) {
        super(itemHeight, items);
    }

    public ListViewImpl(int itemHeight, Collection<? extends E> items) {
        super(itemHeight, items);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.LIST_VIEW;
    }

    @Override
    public String toString() {
        return "ListView" + getItems();
    }
}

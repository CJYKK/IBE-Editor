package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.SpriteView;
import com.github.franckyi.guapi.api.node.builder.SpriteViewBuilder;
import com.github.franckyi.guapi.api.util.NodeType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.function.Supplier;

public class SpriteViewImpl extends AbstractSpriteView implements SpriteViewBuilder {
    public SpriteViewImpl() {
    }

    public SpriteViewImpl(Supplier<TextureAtlasSprite> spriteFactory) {
        super(spriteFactory);
    }

    public SpriteViewImpl(Supplier<TextureAtlasSprite> spriteFactory, int imageWidth, int imageHeight) {
        super(spriteFactory, imageWidth, imageHeight);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<SpriteView> getType() {
        return NodeType.SPRITE_VIEW;
    }
}

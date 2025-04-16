package com.mbrlabs.mundus.commons.terrain;

import com.badlogic.gdx.graphics.Texture;
import com.mbrlabs.mundus.commons.utils.TextureProvider;


public class HeightTexture implements TextureProvider {
    private float minHeight;
    private float maxHeight;
    private Texture texture;

    public HeightTexture(float minHeight, float maxHeight, Texture texture) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.texture = texture;
    }

    public float getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(float minHeight) {
        this.minHeight = minHeight;
    }

    public float getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(float maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}

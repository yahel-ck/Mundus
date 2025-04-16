package com.mbrlabs.mundus.commons.terrain;

import com.badlogic.gdx.graphics.Texture;
import com.mbrlabs.mundus.commons.utils.TextureProvider;

public class SlopeTexture implements TextureProvider {
    private float minSlope;
    private float maxSlope;
    private Texture texture;

    public SlopeTexture(float minSlope, float maxSlope, Texture texture) {
        this.minSlope = minSlope;
        this.maxSlope = maxSlope;
        this.texture = texture;
    }

    public float getMinSlope() {
        return minSlope;
    }

    public void setMinSlope(float minSlope) {
        this.minSlope = minSlope;
    }

    public float getMaxSlope() {
        return maxSlope;
    }

    public void setMaxSlope(float maxSlope) {
        this.maxSlope = maxSlope;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}

package com.mbrlabs.mundus.commons.utils;

import com.badlogic.gdx.graphics.Texture;

public class PBRTextureProvider implements TextureProvider {
    public enum TextureType {
        BASE_COLOR,
        NORMAL,
        METALLIC_ROUGHNESS
    }

    private Texture texture;
    private Texture normalTexture;
    private Texture metallicRoughnessTexture;

    public PBRTextureProvider(Texture texture, Texture normalTexture, Texture metallicRoughnessTexture) {
        this.texture = texture;
        this.normalTexture = normalTexture;
        this.metallicRoughnessTexture = metallicRoughnessTexture;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getNormalTexture() {
        return normalTexture;
    }

    public void setNormalTexture(Texture normalTexture) {
        this.normalTexture = normalTexture;
    }

    public Texture getMetallicRoughnessTexture() {
        return metallicRoughnessTexture;
    }

    public void setMetallicRoughnessTexture(Texture metallicRoughnessTexture) {
        this.metallicRoughnessTexture = metallicRoughnessTexture;
    }

    public Texture getTexture(TextureType type) {
        if (type == TextureType.BASE_COLOR) {
            return texture;
        } else if (type == TextureType.NORMAL) {
            return normalTexture;
        } else if (type == TextureType.METALLIC_ROUGHNESS) {
            return metallicRoughnessTexture;
        } else {
            throw new UnsupportedOperationException("No texture for type " + type);
        }
    }
}

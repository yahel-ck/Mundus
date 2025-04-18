package com.mbrlabs.mundus.commons.terrain;

import com.badlogic.gdx.graphics.Texture;
import com.mbrlabs.mundus.commons.utils.PBRTextureProvider;


/** Holds 3 textures (base color, normal, metallic roughness) and a float array to store parameters
 * for the shader to procedurally blend this texture. */
public class ProceduralBlendTexture extends PBRTextureProvider  {
    protected final float[] blendParams;

    public ProceduralBlendTexture(Texture texture, Texture normalTexture, Texture metallicRoughnessTexture,
            float[] blendParams) {
        super(texture, normalTexture, metallicRoughnessTexture);
        this.blendParams = blendParams;
    }

    public float[] getBlendParams() {
        return blendParams;
    }
}

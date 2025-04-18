package com.mbrlabs.mundus.commons.terrain;

import com.badlogic.gdx.graphics.Texture;
import com.mbrlabs.mundus.commons.utils.PBRTextureProvider;

/**
 * A PBRTextureProvider with a height and slope range. Used to render a PBR texture on the specified height and slope
 * range of a mesh. Holds 3 textures (base color, normal, metallic roughness) and a float array of length 4 to store the
 * ranges. The normal and metallic-roughness textures should be optional.
 */
public class HeightSlopeTexture extends ProceduralBlendTexture {
    protected static int MIN_HEIGHT_INDEX = 0;
    protected static int MAX_HEIGHT_INDEX = 1;
    protected static int MIN_SLOPE_INDEX = 2;
    protected static int MAX_SLOPE_INDEX = 3;

    public HeightSlopeTexture(float minHeight, float maxHeight, float minSlope, float maxSlope, Texture texture,
            Texture normalTexture, Texture metallicRoughnessTexture) {
        super(texture, normalTexture, metallicRoughnessTexture, new float[4]);
        setRangeValues(minHeight, maxHeight, minSlope, maxSlope);
    }

    public HeightSlopeTexture(float minHeight, float maxHeight, Texture texture) {
        this(minHeight, maxHeight, -1f, 1f, texture, null, null);
    }

    public float getMinHeight() {
        return blendParams[MIN_HEIGHT_INDEX];
    }

    public void setMinHeight(float minHeight) {
        blendParams[MIN_HEIGHT_INDEX] = minHeight;
    }

    public float getMaxHeight() {
        return blendParams[MAX_HEIGHT_INDEX];
    }

    public void setMaxHeight(float maxHeight) {
        blendParams[MAX_HEIGHT_INDEX] = maxHeight;
    }

    public float getMinSlope() {
        return blendParams[MIN_SLOPE_INDEX];
    }

    public void setMinSlope(float minSlope) {
        blendParams[MIN_SLOPE_INDEX] = minSlope;
    }

    public float getMaxSlope() {
        return blendParams[MAX_SLOPE_INDEX];
    }

    public void setMaxSlope(float maxSlope) {
        blendParams[MAX_SLOPE_INDEX] = maxSlope;
    }

    public void setRangeValues(float minHeight, float maxHeight, float minSlope, float maxSlope) {
        setMinHeight(minHeight);
        setMaxHeight(maxHeight);
        setMinSlope(minSlope);
        setMaxSlope(maxSlope);
    }

    public void setHeightRange(float minHeight, float maxHeight) {
        setMinHeight(minHeight);
        setMaxHeight(maxHeight);
    }

    public void setSlopeRange(float minSlope, float maxSlope) {
        setMinSlope(minSlope);
        setMaxSlope(maxSlope);
    }
}

package com.mbrlabs.mundus.commons.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.mbrlabs.mundus.commons.terrain.ProceduralBlendTexture;
import com.mbrlabs.mundus.commons.terrain.SplatTexture;
import com.mbrlabs.mundus.commons.terrain.TerrainMaterial;
import com.mbrlabs.mundus.commons.terrain.attributes.TerrainMaterialAttribute;
import com.mbrlabs.mundus.commons.water.attributes.WaterMaterialAttribute;
import net.mgsx.gltf.scene3d.shaders.PBRShader;
import net.mgsx.gltf.scene3d.shaders.PBRShaderConfig;
import net.mgsx.gltf.scene3d.shaders.PBRShaderProvider;

/**
 * @author JamesTKhan
 * @version July 01, 2022
 */
public class MundusPBRShaderProvider extends PBRShaderProvider {
    private final StringBuilder sb = new StringBuilder();

    public MundusPBRShaderProvider(PBRShaderConfig config) {
        super(config);
    }

    @Override
    protected Shader createShader(Renderable renderable) {
         if (renderable.material.has(WaterMaterialAttribute.WaterMaterial))
            return createWaterShader(renderable);

        return super.createShader(renderable);
    }

    @Override
    protected PBRShader createShader(Renderable renderable, PBRShaderConfig config, String prefix){
        if (renderable.material.has(TerrainMaterialAttribute.TerrainMaterial)) {
            return createPBRTerrainShader(renderable, config, prefix);
        }

        return new MundusPBRShader(renderable, config, prefix);
    }

    protected PBRShader createPBRTerrainShader(Renderable renderable, PBRShaderConfig config, String prefix) {
        TerrainMaterialAttribute terrainMaterialA = (TerrainMaterialAttribute) renderable.material.get(TerrainMaterialAttribute.TerrainMaterial);
        TerrainMaterial terrainMaterial = terrainMaterialA.terrainMaterial;

         prefix += getTerrainPrefix(terrainMaterial);

        return new PBRTerrainShader(renderable, config, prefix);
    }

    private Shader createWaterShader(Renderable renderable) {
        Shader shader = new WaterUberShader(renderable, config);
        shaders.add(shader);
        Gdx.app.log(MundusPBRShader.class.getSimpleName(), "Water Shader Compiled");
        return shader;
    }

    protected String getTerrainPrefix(TerrainMaterial terrainMaterial) {
        sb.setLength(0);

        if (terrainMaterial.isTriplanar()) {
            sb.append("#define triplanarFlag\n");
        }

        if (terrainMaterial.getSplatmap() != null && terrainMaterial.getSplatmap().getTexture() != null) {
            sb.append("#define splatFlag\n");
        }

        if (terrainMaterial.hasTextureChannel(SplatTexture.Channel.R)) {
            sb.append("#define splatRFlag\n");
        }

        if (terrainMaterial.hasTextureChannel(SplatTexture.Channel.G)) {
            sb.append("#define splatGFlag\n");
        }

        if (terrainMaterial.hasTextureChannel(SplatTexture.Channel.B)) {
            sb.append("#define splatBFlag\n");
        }

        if (terrainMaterial.hasTextureChannel(SplatTexture.Channel.A)) {
            sb.append("#define splatAFlag\n");
        }

        // Normals
        if (terrainMaterial.hasNormalTextures()) {
            sb.append("#define normalTextureFlag\n");

            if (terrainMaterial.hasNormalChannel(SplatTexture.Channel.BASE)) {
                sb.append("#define baseNormalFlag\n");
            }

            if (terrainMaterial.hasNormalChannel(SplatTexture.Channel.R)) {
                sb.append("#define splatRNormalFlag\n");
            }

            if (terrainMaterial.hasNormalChannel(SplatTexture.Channel.G)) {
                sb.append("#define splatGNormalFlag\n");
            }

            if (terrainMaterial.hasNormalChannel(SplatTexture.Channel.B)) {
                sb.append("#define splatBNormalFlag\n");
            }

            if (terrainMaterial.hasNormalChannel(SplatTexture.Channel.A)) {
                sb.append("#define splatANormalFlag\n");
            }
        }

        final int procBlendTextureCount = terrainMaterial.getProceduralBlendTextureCount();
        for (int i = 0; i < procBlendTextureCount; i++) {
            final ProceduralBlendTexture texture = terrainMaterial.getProceduralBlendTexture(i);
            sb.append("#define proceduralBlendTextureFlag").append(i).append("\n");
            if (texture.getNormalTexture() != null)
                sb.append("#define proceduralBlendNormalTextureFlag").append(i).append("\n");
            if (texture.getMetallicRoughnessTexture() != null)
                sb.append("#define proceduralBlendMetallicRoughnessTextureFlag").append(i).append("\n");
            if (texture.getMetallicRoughnessValues() != null)
                sb.append("#define proceduralBlendMetallicRoughnessValuesFlag").append(i).append("\n");
        }

        return sb.toString();
    }
}

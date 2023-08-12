package com.xamlo.engine.api.resources;

public interface ResourceLoader<I> {

    ITextureResource<I> loadTexture(I identifier);

    IShaderResource<I> loadShader(I identifier);
}

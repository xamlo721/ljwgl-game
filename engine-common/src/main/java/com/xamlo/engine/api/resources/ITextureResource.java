package com.xamlo.engine.api.resources;

public interface ITextureResource<I> extends IResource<I> {

    /**
     * Получить размер текстуры.
     * @return размер текстуры
     */
    int getTextureSize();

    int getWidth();

    int getHeight();

    void bind();

    void unbind();
}
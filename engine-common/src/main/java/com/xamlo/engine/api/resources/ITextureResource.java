package com.xamlo.engine.api.resources;

public interface ITextureResource extends IResource {

    /**
     * Получить размер текстуры.
     * @return размер текстуры
     */
    int getTextureSize();

    int getWidth();

    int getHeight();
}
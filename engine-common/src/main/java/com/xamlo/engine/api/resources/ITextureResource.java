package com.xamlo.engine.api.resources;

public interface ITextureResource extends IResource {

    /**
     * Получить размер текстуры.
     * @return размер текстуры
     */
    int getTextureSize();

    /**
     * Установить размер текстуры.
     * @param size размер текстуры
     */
    void setTextureSize(int size);
}
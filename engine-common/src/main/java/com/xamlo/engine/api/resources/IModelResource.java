package com.xamlo.engine.api.resources;

public interface IModelResource extends IResource {

    /**
     * Получить количество полигонов модели.
     * @return количество полигонов модели
     */
    int getPolygonCount();

    /**
     * Установить количество полигонов модели.
     * @param count количество полигонов модели
     */
    void setPolygonCount(int count);
}
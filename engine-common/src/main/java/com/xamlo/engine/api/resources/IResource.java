package com.xamlo.engine.api.resources;

import java.net.URI;

/**
 * Интерфейс для описания правил работы с ресурсами
 * Ресурсы могут быть разных типов, но все они должны
 * быть унаследованы от данного интерфейса
 * @author Satomi
 */
public interface IResource<I> {
    
    /**
     * Получить местоположение ресурса.
     * @return местоположение ресурса
     */
    I getIdentifier();
}
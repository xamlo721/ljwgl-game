package com.xamlo.engine.api.resources;

/**
 * Интерфейс для описания правил работы с ресурсами
 * Ресурсы могут быть разных типов, но все они должны
 * быть унаследованы от данного интерфейса
 * @author Satomi
 */
public interface IResource {
    
    /**
     * Получить имя ресурса.
     * @return имя ресурса
     */
    String getName();
    
    /**
     * Установить имя ресурса.
     * @param name имя ресурса
     */
    void setName(String name);
    
    /**
     * Получить тип ресурса.
     * @return тип ресурса
     */
    EnumResourceType getType();
    
    /**
     * Получить местоположение ресурса.
     * @return местоположение ресурса
     */
    String getLocation();
    
    /**
     * Установить местоположение ресурса.
     * @param location местоположение ресурса
     */
    void setLocation(String location);
    
    /**
     * Проверить доступность ресурса.
     * @return true, если ресурс доступен, иначе - false
     */
    boolean isAvailable();
    
    /**
     * Установить доступность ресурса.
     * @param available true, если ресурс доступен, иначе - false
     */
    void setAvailable(boolean available);
}
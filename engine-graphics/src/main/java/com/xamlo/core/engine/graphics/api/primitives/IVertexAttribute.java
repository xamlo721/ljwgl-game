package com.xamlo.core.engine.graphics.api.primitives;

import com.xamlo.core.engine.graphics.components.attribs.EnumAttributeType;

/** 
 * Определяет общие методы для атрибутов вершин.
 */ 
public interface IVertexAttribute {

	/**
	 * Получает размер атрибута в байтах.
	 * @return размер атрибута типа int 
	 */ 
	int getSize();
	
	/** 
	 * Получает тип атрибута. 
	 * @return тип атрибута типа EnumAttributeType 
	 */ 
	EnumAttributeType getType();
	
	/** 
	 * Определяет, является ли атрибут нормализованным. 
	 * Если атрибут нормализован, то его значения будут приведены к диапазону от -1 до 1 или от 0 до 1.
	 * @return флаг, указывающий на нормализацию типа boolean 
	*/ 
	boolean isNormalized();
	
	/** 
	 * Получает шаг между атрибутами вершины в байтах. 
	 * @return шаг между атрибутами вершины типа int 
	 */ 
	int getStride();
	
	/** 
	 * Получает смещение (offset) атрибута от начала массива вершинных данных в байтах. 
	 * @return смещение атрибута типа long 
	 */ 
	long getOffset();
	
	/** 
	 * Получает данные вершины в виде массива типа float. 
	 * @return массив данных вершины типа float[] 
	 */ 
	float[] getVertexData();
	
}

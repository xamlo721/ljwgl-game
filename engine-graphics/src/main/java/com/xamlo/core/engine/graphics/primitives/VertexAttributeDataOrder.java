package com.xamlo.core.engine.graphics.primitives;

import com.xamlo.core.engine.graphics.components.attribs.EnumAttributeType;

/**
 *  index: Указывает местоположение, в котором шейдер ожидает эти данные.
 * 
 *  size: Задает количество компонентов для каждого атрибута вершины (от 1 до 4). 
 *  В данном случае мы передаем 3D-координаты, поэтому их должно быть 3.
 *  
 *  type: Указывает тип каждого компонента в массиве, в данном случае float.
 *  
 *  normalized: Указывает, должны ли значения быть нормализованы или нет.
 *  
 *  stride: Задает смещение в байтах между последовательными общими атрибутами вершины. 
 */
public class VertexAttributeDataOrder {
	
	private int channel;
	
	private int dimensionSize;
	
	private EnumAttributeType type;
	
	private boolean normalised;
	
	private int stride;

	public VertexAttributeDataOrder(int channel, int dimensionSize, EnumAttributeType type, boolean normalised, int stride) {
		this.channel = channel;
		this.dimensionSize = dimensionSize;
		this.type = type;
		this.normalised = normalised;
		this.stride = stride;
	}
	
	public int getChannel() {
		return channel;
	}

	public int getDimensionSize() {
		return dimensionSize;
	}

	public EnumAttributeType getType() {
		return type;
	}

	public boolean isNormalised() {
		return normalised;
	}

	public int getStride() {
		return stride;
	}

	
}

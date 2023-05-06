package com.xamlo.core.engine.graphics.components.attribs;

import com.xamlo.core.engine.graphics.api.primitives.IVertexAttribute;

public abstract class AbstractAttribute implements IVertexAttribute  {

	protected final int attribSize;	
	protected final EnumAttributeType attribType;
	protected float[] attribBuffer;

	public AbstractAttribute(int attribSize, EnumAttributeType attribType) {
		this.attribSize = attribSize;
		this.attribType = attribType;
	}
	
	@Override
	public int getSize() {
		return attribSize;
	}

	@Override
	public EnumAttributeType getType() {
		return attribType;
	}
	
	@Override
	public long getOffset() {
		return attribSize * attribType.getTypeSize();
	}
	
	@Override
	public float[]  getVertexData() {
		return attribBuffer;
	}
	
	

}

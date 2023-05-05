package com.xamlo.core.engine.graphics.components.attribs;

import com.xamlo.core.engine.graphics.api.primitives.IVertexAttribute;

public abstract class AbstractAttribute implements IVertexAttribute  {

	protected final int attribSize;	
	protected final EnumAttributeType attribType;

	public AbstractAttribute(int attribSize, EnumAttributeType attribType) {
		this.attribSize = attribSize;
		this.attribType = attribType;
	}
	
	@Override
	public int getSize() {
		return attribSize;
	}

	@Override
	public int getType() {
		return attribType.getOpenGLValue();
	}
	
	@Override
	public long getOffset() {
		return attribSize * attribType.getTypeSize();
	}

}

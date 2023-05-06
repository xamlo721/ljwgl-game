package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector2f;

public class TexCoordAttribute extends AbstractAttribute {

	private final static int attribSize = 2;
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public TexCoordAttribute(float u, float v) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getSize()];
		attribBuffer[0] = u;
	    attribBuffer[1] = v;
	}

	public TexCoordAttribute(Vector2f uv) {
		this(uv.x, uv.y);
	}
	@Override
	public boolean isNormalized() {
		return false;
	}

	@Override
	public int getStride() {
		// TODO Auto-generated method stub
		return 0;
	}

}

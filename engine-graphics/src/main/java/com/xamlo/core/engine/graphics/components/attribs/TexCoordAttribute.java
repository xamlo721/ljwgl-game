package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector2f;

import com.xamlo.core.engine.graphics.primitives.EnumDimestionSize;

public class TexCoordAttribute extends AbstractAttribute {

	private final static EnumDimestionSize attribSize = EnumDimestionSize._2D;	
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public TexCoordAttribute(float u, float v) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getDimensionSize().value()];
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


}

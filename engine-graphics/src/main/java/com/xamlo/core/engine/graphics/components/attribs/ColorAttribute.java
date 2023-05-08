package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector3f;

public class ColorAttribute extends AbstractAttribute {
	
	private final static int attribSize = 3;	
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public ColorAttribute(float red, float green, float blue) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getDimensionSize()];
		attribBuffer[0] = red;
	    attribBuffer[1] = green;
	    attribBuffer[2] = blue;
	}
	
	public ColorAttribute(Vector3f rgb) {
		this(rgb.x,rgb.y, rgb.z);
	}

	@Override
	public boolean isNormalized() {
		return false;
	}
	
}

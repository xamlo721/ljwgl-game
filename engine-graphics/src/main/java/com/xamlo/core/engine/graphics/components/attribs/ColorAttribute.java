package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.primitives.EnumDimestionSize;

public class ColorAttribute extends AbstractAttribute {
	
	private final static EnumDimestionSize attribSize = EnumDimestionSize._3D;	
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public ColorAttribute(float red, float green, float blue) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getDimensionSize().value()];
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

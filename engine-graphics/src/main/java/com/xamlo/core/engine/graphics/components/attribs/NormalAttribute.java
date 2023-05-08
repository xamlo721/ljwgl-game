package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.primitives.EnumDimestionSize;

public class NormalAttribute extends AbstractAttribute {

	private final static EnumDimestionSize attribSize = EnumDimestionSize._3D;	
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public NormalAttribute(float xCoord, float yCoord, float zCoord) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getDimensionSize().value()];
		attribBuffer[0] = xCoord;
	    attribBuffer[1] = yCoord;
	    attribBuffer[2] = zCoord;
	}
	
	public NormalAttribute(Vector3f vec) {
		this(vec.x, vec.y, vec.z);
	}
	
	@Override
	public boolean isNormalized() {
		return false;
	}

}

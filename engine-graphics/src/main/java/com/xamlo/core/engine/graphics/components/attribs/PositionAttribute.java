package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.primitives.EnumDimestionSize;

public class PositionAttribute extends AbstractAttribute {

	private final static EnumDimestionSize attribSize = EnumDimestionSize._3D;	
	private final static EnumAttributeType attribType = EnumAttributeType.Float;
	

	public PositionAttribute(float xCoord, float yCoord, float zCoord) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getDimensionSize().value()];
		attribBuffer[0] = xCoord;
	    attribBuffer[1] = yCoord;
	    attribBuffer[2] = zCoord;
	}
	
	public PositionAttribute(Vector3f pos) {
		this(pos.x, pos.y, pos.z);
	}
	
	@Override
	public boolean isNormalized() {
		return false;
	}

}

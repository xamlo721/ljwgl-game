package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector3f;

public class PositionAttribute extends AbstractAttribute {

	private final static int attribSize = 3;
	private final static EnumAttributeType attribType = EnumAttributeType.Float;
	

	public PositionAttribute(float xCoord, float yCoord, float zCoord) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getDimensionSize()];
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

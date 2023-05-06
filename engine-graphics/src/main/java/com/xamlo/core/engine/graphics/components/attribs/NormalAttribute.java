package com.xamlo.core.engine.graphics.components.attribs;

import org.joml.Vector3f;

public class NormalAttribute extends AbstractAttribute{

	private final static int attribSize = 3;	
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public NormalAttribute(float xCoord, float yCoord, float zCoord) {
		super(attribSize, attribType);
		attribBuffer = new float[this.getSize()];
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

	@Override
	public int getStride() {
		// TODO Auto-generated method stub
		return 0;
	}

}

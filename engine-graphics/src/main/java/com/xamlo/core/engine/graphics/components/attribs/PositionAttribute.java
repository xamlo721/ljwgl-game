package com.xamlo.core.engine.graphics.components.attribs;

public class PositionAttribute extends AbstractAttribute {

	private final static int attribSize = 3;
	private final static EnumAttributeType attribType = EnumAttributeType.Float;

	public PositionAttribute() {
		super(attribSize, attribType);
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

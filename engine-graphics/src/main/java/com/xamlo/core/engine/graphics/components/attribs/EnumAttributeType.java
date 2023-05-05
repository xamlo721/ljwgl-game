package com.xamlo.core.engine.graphics.components.attribs;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;

public enum EnumAttributeType {

	Float (GL_FLOAT),
	
	Interger (GL_INT);
	
	private final int openGLValue;

	private EnumAttributeType(int levelCode) {
		this.openGLValue = levelCode;
	}
	
	int getOpenGLValue() {
		return this.openGLValue;
	}
	
	int getTypeSize() {
		
		int size = 0;
		
		switch (this) {
		
			case Float: {
				size =  4;
				break;
			}
			
			case Interger: {
				size =  4;
				break;
			}
			
		}
		return size;

	}
	
}

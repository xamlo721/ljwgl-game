package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.glBufferData;


import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;


// Объект вершинного буфера, который может хранить большое количество вершин в памяти GPU
public class VertexBufferObject {
	
	private boolean isRegistred;
	
	private int bufferID;
	private float[] bufferData;
	
	//GL_STATIC_DRAW: данные либо никогда не будут изменяться, либо будут изменяться очень редко;
	//GL_DYNAMIC_DRAW: данные будут меняться довольно часто;
	//GL_STREAM_DRAW: данные будут меняться при каждой отрисовке.
	private int bufferType = GL_STATIC_DRAW;
	
	
	public VertexBufferObject() {
		bufferID = glGenBuffers();
	}
	
	
	public boolean bindMemory() {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
		
		//Говорим, что сейчас будем работать с конкретно этим VBO
		glBindBuffer(GL_ARRAY_BUFFER, bufferID);
		//Пихаем в него массив
		glBufferData(GL_ARRAY_BUFFER, bufferData, bufferType);
		

		
		return true;
	}
	
	public boolean releaseMemory() {
		if (!isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = false;
		
		glDeleteBuffers(bufferID);

		
		return true;
	}
}

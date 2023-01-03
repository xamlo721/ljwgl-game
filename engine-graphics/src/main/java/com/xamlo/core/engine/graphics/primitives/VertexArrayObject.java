package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;

public class VertexArrayObject {
	
	private boolean isRegistred;
		
	protected int ibo;
	protected int vaoId;
	protected int size;
	
	private ArrayList<VertexBufferObject> vbos;
	
	public boolean bindMemory() {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
		
		
		
		glBindVertexArray(vaoId);
		for (VertexBufferObject vbo : vbos) {
			vbo.bindMemory();
		}
		glBindVertexArray(0);

		return true;
	}
	
	public boolean releaseMemory() {
		if (!isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = false;
		
		//TODO: Код регистрации в памяти видеокарты
		glBindVertexArray(vaoId);
		for (VertexBufferObject vbo : vbos) {
			vbo.bindMemory();
		}
		glDeleteVertexArrays(vaoId);

		return true;
	}

}

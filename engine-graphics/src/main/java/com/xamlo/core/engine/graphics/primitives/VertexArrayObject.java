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

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.system.MemoryStack;

/**
 * Согласно документации Opengl, под VAO понимают список VBO
 * Причём такой список, который относится к одному объекту
 * По сути, VBO в таком случае являются примитивными хранилищами атрибутов
 * таких как координаты, цвета, текстуры и т.д
 * А VAO интерпретирует VBO как буферы одного конкретного элемента
 * @author Satomi
 *
 */
public class VertexArrayObject {
	/**
	 * Флаг, сообщающий о том, что данный буфер уже был размещён в VRAM
	 * Для такого объекта можно вызвать release, но нельзя вызвать bind
	 */
	private boolean isRegistred;
		
	protected int ibo;
	
	/**
	 * Целочисленный ID области памяти (буфера) в VRAM видеокарты
	 * Устанавливается какое-то значение методом glBindVertexArray
	 */
	public int vaoId;
	/**
	 * Список VBO, сходящих в состав VAO
	 */
	private ArrayList<VertexBufferObject> vbos;
	
	
	public VertexBufferObject coordsVBuffer;
	public VertexBufferObject normalesVBuffer;
	public VertexBufferObject colorsVBuffer;
	public VertexBufferObject textureVBuffer;

	
	public VertexArrayObject() {
		vbos = new ArrayList<VertexBufferObject>();
		vaoId = glGenVertexArrays();
	}
	
	public VertexArrayObject(Vertex[] vertices) {
		vbos = new ArrayList<VertexBufferObject>();
		
		float[] coordsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] normalesData= new float[vertices.length * 3]; //FIXME: Magic number
		float[] colorsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] textureData = new float[vertices.length * 2]; //FIXME: Magic number
		int[] vertexIndices = new int[vertices.length];
		
		for (int i = 0; i < vertices.length; i++) {
			Vertex v  = vertices[i];
			coordsData[i*3 + 0] = v.getPos().Z; //FIXME: Magic number
			coordsData[i*3 + 1] = v.getPos().Z; //FIXME: Magic number
			coordsData[i*3 + 2] = v.getPos().Y; //FIXME: Magic number
			
			normalesData[i*3 + 0] = v.getPos().X; //FIXME: Magic number
			normalesData[i*3 + 1] = v.getPos().Y; //FIXME: Magic number
			normalesData[i*3 + 2] = v.getPos().Z; //FIXME: Magic number
			
			colorsData[i*3 + 0] = v.getPos().X; //FIXME: Magic number
			colorsData[i*3 + 1] = v.getPos().Y; //FIXME: Magic number
			colorsData[i*3 + 2] = v.getPos().Z; //FIXME: Magic number
			
			textureData[i*2 + 0] = v.getPos().X; //FIXME: Magic number
			textureData[i*2 + 1] = v.getPos().Y; //FIXME: Magic number
			
			vertexIndices[i] = i;
		}
        
		coordsVBuffer = new VertexBufferObject(coordsData);
		normalesVBuffer = new VertexBufferObject(normalesData);
		colorsVBuffer = new VertexBufferObject(colorsData);
		textureVBuffer = new VertexBufferObject(textureData);

		this.vbos.add(coordsVBuffer);
		this.vbos.add(normalesVBuffer);
		this.vbos.add(colorsVBuffer);
		this.vbos.add(textureVBuffer);
		
		vaoId = glGenVertexArrays();
	}
	
	public VertexArrayObject(Vertex[] vertices, int[] vertexOrder)  {
		
	}

	public boolean allocMemory() {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
		///
        glBindVertexArray(vaoId);
		for (VertexBufferObject vbo : vbos) {
			vbo.allocMemory();
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
			vbo.allocMemory();
		}
		glDeleteVertexArrays(vaoId);
		return true;
	}

}

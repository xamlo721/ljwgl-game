package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;

/**
 * Согласно документации Opengl, под VAO понимают список VBO
 * Причём такой список, который относится к одному объекту
 * По сути, VBO в таком случае являются примитивными хранилищами атрибутов
 * таких как координаты, цвета, текстуры и т.д
 * А VAO интерпретирует VBO как буферы одного конкретного элемента
 * @author Satomi
 *
 */
public class VertexArrayObject implements IBufferObject {
	/**
	 * Флаг, сообщающий о том, что данный буфер уже был размещён в VRAM
	 * Для такого объекта можно вызвать release, но нельзя вызвать bind
	 */
	private boolean isRegistred;
			
	/**
	 * Целочисленный ID области памяти (буфера) в VRAM видеокарты
	 * Устанавливается какое-то значение методом glBindVertexArray
	 */
	private int vaoId;
	/**
	 * Список VBO, сходящих в состав VAO
	 */
	private ArrayList<VertexBufferObject> vbos;
	
	private IndexBufferObject indexBufferObject;
	
	private VertexBufferObject coordsVBuffer;
	private VertexBufferObject normalesVBuffer;
	private VertexBufferObject colorsVBuffer;
	private VertexBufferObject textureVBuffer;

	public VertexArrayObject(Vertex[] vertices) {
		int[] vertexOrder = new int[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			vertexOrder[i] = i;
		}
		
		vbos = new ArrayList<VertexBufferObject>();
		
		float[] coordsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] normalesData= new float[vertices.length * 3]; //FIXME: Magic number
		float[] colorsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] textureData = new float[vertices.length * 2]; //FIXME: Magic number
		
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
		}
        
		coordsVBuffer = new VertexBufferObject(coordsData);
		normalesVBuffer = new VertexBufferObject(normalesData);
		colorsVBuffer = new VertexBufferObject(colorsData);
		textureVBuffer = new VertexBufferObject(textureData);
		indexBufferObject = new IndexBufferObject(vertexOrder);
		
		this.vbos.add(coordsVBuffer);
		this.vbos.add(normalesVBuffer);
		this.vbos.add(colorsVBuffer);
		this.vbos.add(textureVBuffer);
		
		vaoId = glGenVertexArrays();

	}
	
	public VertexArrayObject(Vertex[] vertices, int[] vertexOrder)  {
		vbos = new ArrayList<VertexBufferObject>();
		
		float[] coordsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] normalesData= new float[vertices.length * 3]; //FIXME: Magic number
		float[] colorsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] textureData = new float[vertices.length * 2]; //FIXME: Magic number
		
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
		}
        
		coordsVBuffer = new VertexBufferObject(coordsData);
		normalesVBuffer = new VertexBufferObject(normalesData);
		colorsVBuffer = new VertexBufferObject(colorsData);
		textureVBuffer = new VertexBufferObject(textureData);
		indexBufferObject = new IndexBufferObject(vertexOrder);
		
		this.vbos.add(coordsVBuffer);
		this.vbos.add(normalesVBuffer);
		this.vbos.add(colorsVBuffer);
		this.vbos.add(textureVBuffer);
		
		vaoId = glGenVertexArrays();
	}

	@Override
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
	
	@Override
	public void bind() {
	    glBindVertexArray(vaoId);
	    glEnableVertexAttribArray(0);

	}
	
	@Override
	public void unbind() {
	    glBindVertexArray(0);
	    glEnableVertexAttribArray(0);		
	}
	
	@Override
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
		indexBufferObject.releaseMemory();
		glDeleteVertexArrays(vaoId);
		return true;
	}



}

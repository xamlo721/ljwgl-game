package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;

import com.xamlo.core.engine.graphics.api.primitives.IBufferObject;
import com.xamlo.core.engine.graphics.components.Texture;
import com.xamlo.core.engine.graphics.primitives.VertexBufferObject.EnumMemoryType;

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
	
	
	private VertexBufferObject coordsVBuffer;
	private VertexBufferObject normalesVBuffer;
	private VertexBufferObject colorsVBuffer;
	private VertexBufferObject textureVBuffer;
	private IndexBufferObject indexBufferObject;

	public VertexArrayObject(Vertex[] vertices) {
		vaoId = glGenVertexArrays();

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
			coordsData[i*3 + 0] = v.getPos().x; //FIXME: Magic number
			coordsData[i*3 + 1] = v.getPos().y; //FIXME: Magic number
			coordsData[i*3 + 2] = v.getPos().z; //FIXME: Magic number
			
			normalesData[i*3 + 0] = v.getNormal().x; //FIXME: Magic number
			normalesData[i*3 + 1] = v.getNormal().y; //FIXME: Magic number
			normalesData[i*3 + 2] = v.getNormal().z; //FIXME: Magic number
			
			colorsData[i*3 + 0] = v.getColor().x; //FIXME: Magic number
			colorsData[i*3 + 1] = v.getColor().y; //FIXME: Magic number
			colorsData[i*3 + 2] = v.getColor().z; //FIXME: Magic number
			
			textureData[i*2 + 0] = v.getTextureCoord().x; //FIXME: Magic number
			textureData[i*2 + 1] = v.getTextureCoord().y; //FIXME: Magic number
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
		

	}
	
	public VertexArrayObject(Vertex[] vertices, int[] vertexOrder)  {
		vaoId = glGenVertexArrays();

		vbos = new ArrayList<VertexBufferObject>();
		
		float[] coordsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] normalesData= new float[vertices.length * 3]; //FIXME: Magic number
		float[] colorsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] textureData = new float[vertices.length * 2]; //FIXME: Magic number

		for (int i = 0; i < vertices.length; i++) {
			Vertex v  = vertices[i];
			coordsData[i*3 + 0] = v.getPos().x; //FIXME: Magic number
			coordsData[i*3 + 1] = v.getPos().y; //FIXME: Magic number
			coordsData[i*3 + 2] = v.getPos().z; //FIXME: Magic number
			
			normalesData[i*3 + 0] = v.getNormal().x; //FIXME: Magic number
			normalesData[i*3 + 1] = v.getNormal().y; //FIXME: Magic number
			normalesData[i*3 + 2] = v.getNormal().z; //FIXME: Magic number
			
			colorsData[i*3 + 0] = v.getColor().x; //FIXME: Magic number
			colorsData[i*3 + 1] = v.getColor().y; //FIXME: Magic number
			colorsData[i*3 + 2] = v.getColor().z; //FIXME: Magic number
			
			textureData[i*2 + 0] = v.getTextureCoord().x; //FIXME: Magic number
			textureData[i*2 + 1] = v.getTextureCoord().y; //FIXME: Magic number
		}
        
		coordsVBuffer = new VertexBufferObject(coordsData);
		normalesVBuffer = new VertexBufferObject(normalesData);
		colorsVBuffer = new VertexBufferObject(colorsData);
		textureVBuffer = new VertexBufferObject(textureData, EnumMemoryType.STATIC, 2);
		indexBufferObject = new IndexBufferObject(vertexOrder);
		
		this.vbos.add(coordsVBuffer);
		this.vbos.add(textureVBuffer);

		this.vbos.add(colorsVBuffer);
		this.vbos.add(normalesVBuffer);
		
	}

	public VertexArrayObject(Vertex[] vertices, int[] vertexOrder, Texture texture) {
		this(vertices, vertexOrder);
		//TODO: Что-то сделать с текстурой?
	}

	@Override
	public boolean allocMemory(int indexVBO) {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
		///
        glBindVertexArray(vaoId);
		indexBufferObject.allocMemory(0);
		int i = 0;
		for (VertexBufferObject vbo : vbos) {
			vbo.allocMemory(i++);
		}
		glBindVertexArray(0);

		return true;
	}
	
	@Override
	public void bind() {
	    glBindVertexArray(vaoId);
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);
	    glEnableVertexAttribArray(2);
	    glEnableVertexAttribArray(3);

	}
	
	@Override
	public void unbind() {
	    glBindVertexArray(0);
	    glDisableVertexAttribArray(0);	
	    glDisableVertexAttribArray(1);		
	    glDisableVertexAttribArray(2);		
	    glDisableVertexAttribArray(3);		

	}
	
	@Override
	public boolean releaseMemory() {
		if (!isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = false;
		
		glBindVertexArray(vaoId);
		indexBufferObject.releaseMemory();
		for (VertexBufferObject vbo : vbos) {
			vbo.releaseMemory();
		}
		glDeleteVertexArrays(vaoId);
		return true;
	}



}

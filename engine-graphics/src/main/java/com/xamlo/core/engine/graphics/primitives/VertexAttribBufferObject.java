package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

import com.xamlo.core.engine.graphics.api.primitives.IBufferObject;

import static org.lwjgl.opengl.GL15.glBufferData;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STREAM_DRAW;


/**
 * Объект вершинного буфера, который может хранить в VRAM
 * данные одного типа об одной или нескольки вершинах
 * Класс является отображением примитивного фрагмента сырой памяти VRAM/
 * хранит указатель и копию памяти VRAM
 * @author Satomi
 *
 */
public class VertexAttribBufferObject implements IBufferObject {
	
	/**
	 * Флаг, сообщающий о том, что данный буфер уже был размещён в VRAM
	 * Для такого объекта можно вызвать release, но нельзя вызвать alloc
	 */
	private boolean isRegistred;
	
	/**
	 * Целочисленный ID области памяти (буфера) в VRAM видеокарты
	 * Устанавливается какое-то значение методом glBindBuffer
	 */
	public int bufferID;
	/**
	 * Копия данных, которые должны быть положены в VRAM
	 * Возможно их стоило убрать из озу совсем, но ладно
	 */
	
	public float[] vertexData;
	
	private EnumMemoryType bufferType;

	
	/**
	 * Размерность пространства, координаты которого мы запоминаем в буфере.
	 * 		Для 2D - 2
	 * 		Для 3D - 3 
	 * и т.д
	 */
	private int dimensionSize;
	
	public VertexAttribBufferObject(float[] vertexData) {
		this(vertexData, EnumMemoryType.STATIC);
	}

	public VertexAttribBufferObject(float[] vertexData, EnumMemoryType memoryType) {
		this(vertexData, memoryType, 3);
	}
	
	public VertexAttribBufferObject(float[] vertexData, int dimensionSize) {
		this(vertexData, EnumMemoryType.STATIC, 3);
	}
	
	public VertexAttribBufferObject(float[] vertexData, EnumMemoryType memoryType, int dimensionSize) {
		this.vertexData = vertexData;
		this.bufferType = memoryType;
		this.dimensionSize = dimensionSize;
		bufferID = glGenBuffers();
	}

	@Override
	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, bufferID);
	}

	@Override
	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	@Override
	public boolean allocMemory(int indexVBO) {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
		
	    FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertexData.length);
	    vertexBuffer.put(vertexData);
	    vertexBuffer.flip();

		/**
		 * Указываем OpenGL, что нужно переключиться на область памяти с индексом bufferID
		 */
		glBindBuffer(GL_ARRAY_BUFFER, bufferID);
		/**
		 * Перемещаем в выбранную область памяти массив из ОЗУ
		 */
		glBufferData(GL_ARRAY_BUFFER, vertexData, bufferType.getOpenGLValue());
			
        glEnableVertexAttribArray(0);
	    
        /**
          *  index: Указывает местоположение, в котором шейдер ожидает эти данные.
          * 
          *  size: Задает количество компонентов для каждого атрибута вершины (от 1 до 4). 
          *  В данном случае мы передаем 3D-координаты, поэтому их должно быть 3.
          *  
          *  type: Указывает тип каждого компонента в массиве, в данном случае float.
          *  
          *  normalized: Указывает, должны ли значения быть нормализованы или нет.
          *  
          *  stride: Задает смещение в байтах между последовательными общими атрибутами вершины. 
          *  (Мы объясним это позже).
          *  
          *  offset: Задает смещение по отношению к первому компоненту в буфере.
          */
	    glVertexAttribPointer(indexVBO, dimensionSize, GL_FLOAT, false, 0, 0);

	    MemoryUtil.memFree(vertexBuffer);
	    
		return true;
	}
	
	@Override
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

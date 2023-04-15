package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL15.glBufferData;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;


/**
 * Объект вершинного буфера, который может хранить в VRAM
 * данные одного типа об одной или нескольки вершинах
 * Класс является отображением примитивного фрагмента сырой памяти VRAM/
 * хранит указатель и копию памяти VRAM
 * @author Satomi
 *
 */
public class VertexBufferObject {
	
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

	/**
	 * Тип области памяти, в которой мы хотим разместить буфер.
	 * Разные области памяти имеют разные размеры и разную скорость работы с GPU
	 * Существуют следующие типы:
	 * 		GL_STATIC_DRAW: данные либо никогда не будут изменяться, либо будут изменяться очень редко;
	 * 		GL_DYNAMIC_DRAW: данные будут меняться довольно часто;
	 * 		GL_STREAM_DRAW: данные будут меняться при каждой отрисовке.	 
	 * 
	 */
	private int bufferType = GL_STATIC_DRAW;
	
	public VertexBufferObject(float[] vertexData) {

		this.vertexData = vertexData;
		bufferID = glGenBuffers();
	}

	public VertexBufferObject(float[] vertexData, int memoryType) {
		this.vertexData = vertexData;
		this.bufferType = memoryType;
		bufferID = glGenBuffers();
	}
	
	public boolean allocMemory() {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
		
        try (MemoryStack stack = MemoryStack.stackPush()) {

	        FloatBuffer positionsBuffer = stack.callocFloat(vertexData.length);
	        positionsBuffer.put(0, vertexData);
        
			/**
			 * Указываем OpenGL, что нужно переключиться на область памяти с индексом bufferID
			 */
			glBindBuffer(GL_ARRAY_BUFFER, bufferID);
			/**
			 * Перемещаем в выбранную область памяти массив из ОЗУ
			 */
			glBufferData(GL_ARRAY_BUFFER, positionsBuffer, bufferType);
			
            glEnableVertexAttribArray(0);

	        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        }

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

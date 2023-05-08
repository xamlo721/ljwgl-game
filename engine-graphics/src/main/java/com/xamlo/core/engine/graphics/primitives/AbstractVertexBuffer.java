package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;

import com.xamlo.core.engine.graphics.api.primitives.IBufferObject;

public abstract class AbstractVertexBuffer  implements IBufferObject {

	/**
	 * Флаг, сообщающий о том, что данный буфер уже был размещён в VRAM
	 * Для такого объекта можно вызвать release, но нельзя вызвать alloc
	 */
	protected boolean isRegistred;
	
	/**
	 * Целочисленный ID области памяти (буфера) в VRAM видеокарты
	 * Устанавливается какое-то значение методом glBindBuffer
	 */
	protected int bufferID;
	
	/**
	 * Тип области памяти, в которой мы хотим разместить буфер.
	 * Разные области памяти имеют разные размеры и разную скорость работы с GPU
	 * Существуют следующие типы:
	 * 		GL_STATIC_DRAW: данные либо никогда не будут изменяться, либо будут изменяться очень редко;
	 * 		GL_DYNAMIC_DRAW: данные будут меняться довольно часто;
	 * 		GL_STREAM_DRAW: данные будут меняться при каждой отрисовке.	 
	 * 
	 */
	protected EnumMemoryType bufferType;
	
	@Override
	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, bufferID);
	}

	@Override
	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
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

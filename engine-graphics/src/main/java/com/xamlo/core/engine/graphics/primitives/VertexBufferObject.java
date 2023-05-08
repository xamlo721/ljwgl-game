package com.xamlo.core.engine.graphics.primitives;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

import com.xamlo.core.engine.graphics.api.primitives.IVertexAttribute;

import static org.lwjgl.opengl.GL15.glBufferData;


/**
 * Объект вершинного буфера, который может хранить в VRAM
 * данные одного типа об одной или нескольки вершинах
 * Класс является отображением примитивного фрагмента сырой памяти VRAM/
 * хранит указатель и копию памяти VRAM
 * @author Satomi
 *
 */
public class VertexBufferObject extends AbstractVertexBuffer {
	
	/**
	 * Копия данных, которые должны быть положены в VRAM
	 * Возможно их стоило убрать из озу совсем, но ладно
	 */
	private Vertex[] vertices;
	
	public VertexBufferObject(Vertex[] vertices, EnumMemoryType memoryType) {
		
    	if (vertices.length == 0) {
    		//TODO: Охх я вам как дааам!
    		return;
    	}
		System.out.println("create VBO with " + vertices.length + " vertices");

    	
		this.vertices = vertices;
		this.bufferType = memoryType;
		bufferID = glGenBuffers();
	}
	
	@Override
	public boolean allocMemory(int indexVBO) {
		if (isRegistred) {
			//FIXME: Наверное лучше кинуть своё исключение
			return false;
		}
		this.isRegistred = true;
	    FloatBuffer vertexData = MemoryUtil.memAllocFloat(vertices.length * vertices[0].getVertexSize());

		for (int i = 0; i < vertices.length; i++) {
			Vertex v = vertices[i];
			vertexData.put(v.getVertexBuffer());
			
		}
		vertexData.flip();
		/**
		 * Указываем OpenGL, что нужно переключиться на область памяти с индексом bufferID
		 */
		glBindBuffer(EnumBufferType.VertexBuffer.getOpenGLValue(), bufferID);
		/**
		 * Перемещаем в выбранную область памяти массив из ОЗУ
		 */
		glBufferData(EnumBufferType.VertexBuffer.getOpenGLValue(), vertexData, bufferType.getOpenGLValue());
			
	    MemoryUtil.memFree(vertexData);
	    
		Vertex v = vertices[0];
		int i = 0;
	    int offset = 0;
	    for (IVertexAttribute attr : v.getAttributes()) {
			glEnableVertexAttribArray(i);
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
	          *  
	          *  offset: Задает смещение по отношению к первому компоненту в буфере.
	          */
			glVertexAttribPointer(i, attr.getDimensionSize().value(), attr.getType().getOpenGLValue(), attr.isNormalized(), v.getVertexStride(), offset);
			
			offset += attr.getOffset();
			i++;
		}

		return true;
	}
	


}

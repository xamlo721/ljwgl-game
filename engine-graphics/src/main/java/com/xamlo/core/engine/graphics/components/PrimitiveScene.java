package com.xamlo.core.engine.graphics.components;

import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenBuffers;
import static org.lwjgl.opengl.GL30.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL30.glBindBuffer;
import static org.lwjgl.opengl.GL30.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL30.glBufferData;
import static org.lwjgl.opengl.GL30.GL_FLOAT;
import static org.lwjgl.opengl.GL30.glVertexAttribPointer;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL30.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.GL_TRIANGLES;
import static org.lwjgl.opengl.GL30.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glDrawArrays;
import static org.lwjgl.opengl.GL30.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDrawArrays;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

import org.lwjglb.engine.graph.Mesh;

import com.xamlo.core.engine.graphics.primitives.Vertex;

import ru.satomi.dc.primitive.Vec3f;

import static org.lwjgl.opengl.GL30.glDeleteShader;
import static org.lwjgl.opengl.GL30.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL30.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glPolygonMode;
import static org.lwjgl.opengl.GL20.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL20.GL_LINE;

public class PrimitiveScene implements IScene {
	

	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram;
	
    private Matrix4f projectionMatrix;
    
    private CubeExample cube;

	@Override
    public void tranformScene(Matrix4f transformMatrix) {
    	this.projectionMatrix = transformMatrix;
    }

	@Override
	public void load() {		
		
        //Рисовать рамку или заливать цветом - закомментировать, если хотим цвет
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();
		shaderProgram.bind();
		try {
			shaderProgram.createUniform("projectionMatrix");
		} catch (Exception e) {
			e.printStackTrace();
		}
		cube = new CubeExample();
		
		cube.init();
		
		
		
		shaderProgram.unbind();
        
        // clear the framebuffer
        glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		System.out.println("Primitive scene loaded");
	}

	@Override
	public void renderFrame() {
        
		shaderProgram.bind();
		cube.getMesh().bind();

	    // Draw the vertices
	    //glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
		
		//Теперь матрица преобразования обновляется каждый раз
		shaderProgram.setUniform("projectionMatrix", projectionMatrix);

		/**
		 * mode: Задает примитивы для рендеринга, в данном случае треугольники. Здесь никаких изменений.
		 * count: Указывает количество элементов, которые должны быть отрисованы.
		 * type: Указывает тип значения в данных индексов. В данном случае мы используем целые числа.
		 * indices: Задает смещение, которое необходимо применить к данным индексов для начала рендеринга.
		 */
		glDrawElements(GL_TRIANGLES, cube.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);

	    shaderProgram.unbind();

	}
	
	@Override
	public void release() {	    
		shaderProgram.cleanup();	
		cube.release();
	}
	

}
package com.xamlo.core.engine.graphics.components;

import static org.lwjgl.opengl.GL30.glCreateShader;
import static org.lwjgl.opengl.GL30.glCreateProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL30.glCompileShader;
import static org.lwjgl.opengl.GL30.glGetShaderiv;
import static org.lwjgl.opengl.GL30.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL30.glAttachShader;
import static org.lwjgl.opengl.GL30.glLinkProgram;
import static org.lwjgl.opengl.GL30.glUseProgram;

import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenBuffers;
import static org.lwjgl.opengl.GL30.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL30.glBindBuffer;
import static org.lwjgl.opengl.GL30.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL30.glBufferData;
import static org.lwjgl.opengl.GL30.GL_FLOAT;
import static org.lwjgl.opengl.GL30.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.GL_TRIANGLES;
import static org.lwjgl.opengl.GL30.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glDrawArrays;
import static org.lwjgl.opengl.GL30.glDeleteBuffers;
import static org.lwjgl.opengl.GL30.glDrawArrays;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.system.MemoryUtil;
//import org.lwjglb.engine.graph.Mesh;
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

public class PrimitiveScene implements IScene {
	
	private Mesh mesh;

	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram;

	@Override
	public void load() {		
		
		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();

    	Vertex[] vertices = new Vertex[4];
    	int i = 0;
    	vertices[i++] = new Vertex(new Vec3f(-0.5f,  0.5f, 0.0f)); //V1
    	vertices[i++] = new Vertex(new Vec3f(-0.5f, -0.5f, 0.0f)); //V2
    	vertices[i++] = new Vertex(new Vec3f( 0.5f, -0.5f, 0.0f)); //V3
    	vertices[i++] = new Vertex(new Vec3f( 0.5f,  0.5f, 0.0f)); //V4

    	int[] indices = new int[6]; 
    	indices[0] = 0;
    	indices[1] = 1;
    	indices[2] = 3;
    	indices[3] = 3;
    	indices[4] = 1;
    	indices[5] = 2;

    	
        mesh = new Mesh(vertices, indices);

        // clear the framebuffer
        glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		System.out.println("Primitive scene loaded");

	}

	@Override
	public void renderFrame() {
        
		shaderProgram.bind();
		mesh.bind();

	    // Draw the vertices
	    //glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());

		/**
		 * mode: Задает примитивы для рендеринга, в данном случае треугольники. Здесь никаких изменений.
		 * count: Указывает количество элементов, которые должны быть отрисованы.
		 * type: Указывает тип значения в данных индексов. В данном случае мы используем целые числа.
		 * indices: Задает смещение, которое необходимо применить к данным индексов для начала рендеринга.
		 */
		glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
	    // Restore state
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	    
	    shaderProgram.unbind();

	}
	
	@Override
	public void release() {	    
		shaderProgram.cleanup();	
		mesh.cleanup();
	}
	

}
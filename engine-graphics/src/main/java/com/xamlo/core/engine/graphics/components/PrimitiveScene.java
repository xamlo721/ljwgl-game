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
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class PrimitiveScene implements IScene {
	
	float[] trianglePos = new float[] {
		     0.0f,  0.5f, 0.0f,
		     -0.5f, -0.5f, 0.0f,
		      0.5f, -0.5f, 0.0f
	};
	
	private Mesh mesh;

	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram;

	private int vaoId;
	private int vboId;
		
	@Override
	public void load() {		
		
		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();

    	Vertex[] vertices = new Vertex[3];
    	
    	for (int i = 0; i < vertices.length; i ++) {
    		Vec3f coords = new Vec3f(trianglePos[i*3 + 0], trianglePos[i*3 + 1],  trianglePos[i*3 + 2]);
    		vertices[i] = new Vertex(coords);
    	}
    	
        mesh = new Mesh(vertices);

        // clear the framebuffer
        glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		System.out.println("Primitive scene loaded");

	}

	@Override
	public void renderFrame() {
        
		shaderProgram.bind();
		//System.out.println("FRAME RENDER: " + vaoId + " # " + vboId);		
		//some render code
		
//        glBindVertexArray(mesh.getVaoId());
//        glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
		
	    // Bind to the VAO
	    //glBindVertexArray(vaoId);
	    //glBindVertexArray(mesh.getVaoId());
	    //glEnableVertexAttribArray(0);
		mesh.use();

	    // Draw the vertices
	    glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());

	    // Restore state
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	    
	    shaderProgram.unbind();

	}
	
	@Override
	public void release() {
	    glDisableVertexAttribArray(0);

	    // Delete the VBO
	    glBindBuffer(GL_ARRAY_BUFFER, 0);
	    glDeleteBuffers(vboId);

	    // Delete the VAO
	    glBindVertexArray(0);
	    glDeleteVertexArrays(vaoId);
	    
		shaderProgram.cleanup();	
		//mesh.cleanup();
	}
	

}

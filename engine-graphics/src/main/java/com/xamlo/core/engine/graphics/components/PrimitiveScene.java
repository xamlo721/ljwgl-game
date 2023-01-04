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
import static org.lwjgl.opengl.GL30.glDeleteShader;
import static org.lwjgl.opengl.GL30.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL30.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;

public class PrimitiveScene implements IScene {

	
	float[] vertices = {
		    -0.5f, -0.5f, 0.0f,
		     0.5f, -0.5f, 0.0f,
		     0.0f,  0.5f, 0.0f
		};
	
	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram ;

	@Override
	public void load() {		
		
		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();
		
		
			
		System.out.println("Primitive scene loaded");
	}

	@Override
	public void renderFrame() {
		
		shaderProgram.bind();

		//some render code
		
		shaderProgram.unbind();

	}
	
	@Override
	public void release() {
		shaderProgram.cleanup();		
	}
	

}

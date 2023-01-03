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
	
	final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		
		
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		
		int[] infoLog = new int[512];
		glGetShaderiv(vertexShader, GL_COMPILE_STATUS, infoLog);
		
		if (infoLog.length > 0) {
			//glGetShaderInfoLog(vertexShader, 512, null, infoLog);

			//Наверное как-то вывести на экран ошибку
			System.out.println(infoLog.toString());
		}
		
		
		
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);

		
		int shaderProgram = glCreateProgram();
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		
//		glGetProgramiv(shaderProgram, GL_LINK_STATUS, &success);
//		if (!success) {
//			glGetProgramInfoLog(shaderProgram, 512, NULL, infoLog);
//			…
//		}
		
		
		glUseProgram(shaderProgram);
		
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
			
		System.out.println("Primitive scene loaded");
	}

	@Override
	public void renderFrame() {
		// TODO Auto-generated method stub
		
	}
	

}

package com.xamlo.core.engine.graphics.components;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.lwjgl.opengl.GL20.*;


public class ShaderProgram {
	
	private int program;
	
	public ShaderProgram() {
		program = glCreateProgram();
		
		if (program == 0) {
			System.err.println("Shader creation failed");
			System.exit(1);
		}	
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}
	
//	public void addGeometryShader(String text) {
//		addProgram(text, GL_GEOMETRY_SHADER);
//	}
	
	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
//	public void addTessellationControlShader(String text) {
//		addProgram(text, GL_TESS_CONTROL_SHADER);
//	}
//	
//	public void addTessellationEvaluationShader(String text) {
//		addProgram(text, GL_TESS_EVALUATION_SHADER);
//	}
//	
//	public void addComputeShader(String text) {
//		addProgram(text, GL_COMPUTE_SHADER);
//	}
	
	private void addProgram(String text, int type) {
		
		int shader = glCreateShader(type);
		
		if (shader == 0) {
			System.err.println(this.getClass().getName() + " Shader creation failed");
			System.exit(1);
		}	
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			System.err.println(this.getClass().getName() + " " + glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}

	
	public void compileShader() {
		
		glLinkProgram(program);

		if(glGetProgrami(program, GL_LINK_STATUS) == 0) {
			System.out.println(this.getClass().getName() + " " + glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
			System.err.println(this.getClass().getName() +  " " + glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
	}
	
	public static String loadShader(String fileName) {
		
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		
		try {
			
			shaderReader = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = shaderReader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			
			shaderReader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return shaderSource.toString();
	}
	
	public int getProgram() {
		return this.program;
	}
	
}

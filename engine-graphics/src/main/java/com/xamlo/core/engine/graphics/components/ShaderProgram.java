package com.xamlo.core.engine.graphics.components;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.lwjgl.opengl.GL45.*;

/**
 * 
 * @author Satomi
 * 
 * The constructor of the ShaderProgram creates a new program in OpenGL 
 * and provides methods to add vertex and fragment shaders. 
 * Those shaders are compiled and attached to the OpenGL program.
 *  When all shaders are attached the link method should be invoked which 
 *  links all the code and verifies that everything has been done correctly.
 *  
 *  https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content/chapter04/chapter4.html
 */
public class ShaderProgram {
	
	private int program;
	private int vertexShaderID;
	private int geometryShaderID;
	private int fragmentShaderID;
	private int tessellationControlShaderID;
	private int tesselationEvaluationShaderID;
	private int computeShaderID;

	/**
	 * Статический метод для загрузки с диска файлов с шейдерами
	 * принимает абсолютный путь к файлу и не зависит от расширения и названия файла
	 * 
	 * @param fileName 
	 * @return Текстовое предсталение шейдера
	 */
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
	
	public ShaderProgram() {
		program = glCreateProgram();
		
		if (program == 0) {
			System.err.println("Shader creation failed");
			System.exit(1);
		}	
		
		vertexShaderID = 0;
		geometryShaderID = 0;
		fragmentShaderID = 0;
		tessellationControlShaderID = 0;
		tesselationEvaluationShaderID = 0;
		computeShaderID = 0;
		System.out.println("Register Shader program id: " + program);

	}
	
	/**
	 * Привязывает текущий шейдер к контексту 
	 * (дальше рисоваться всё будет с этим шейдером)
	 */
	public void bind() {
		glUseProgram(program);
	}
	
	/**
	 * Отвязывает шейдер от текущего контекста
	 * Перед выходом из программы надо ещё вызвать cleanUP();
	 * для корректного завершения 
	 */
	public void unbind() {
		glUseProgram(0);
	}
	
	public void addVertexShader(String text) {
		vertexShaderID = addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text) {
		geometryShaderID = addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text) {
		fragmentShaderID = addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void addTessellationControlShader(String text) {
		tessellationControlShaderID = addProgram(text, GL_TESS_CONTROL_SHADER);
	}
	
	public void addTessellationEvaluationShader(String text) {
		tesselationEvaluationShaderID = addProgram(text, GL_TESS_EVALUATION_SHADER);
	}
	
	public void addComputeShader(String text) {
		computeShaderID = addProgram(text, GL_COMPUTE_SHADER);
	}
	
	/**
	 * Приватный метод для регистрации этапов конвейера 
	 * Регистрирует отдельные шейдерные программы
	 * @param text Шейдер
	 * @param type Тип шейдера
	 * @return ID зарегистрированного шейдера в openGL
	 */
	private int addProgram(String text, int type) {
		
		int shader = glCreateShader(type);
		System.out.println("Register Shader part id : " + shader);

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
		return shader;
	}

	/**
	 * Когда все этапы шейдера добавлены в конвейер
	 * шейдерная программа может быть скомпилирована
	 * (В разных источниках называется линковка)
	 * 
	 * После чего она не может быть изменена, но может быть удалена
	 * 
	 * Если нужно изменить этап конвейера, нужно создать новый ShaderProgram объект
	 * 
	 * Чистит за собой все отдельно лежащие шейдеры
	 * 
	 */
	public void compileShader() {
		
		glLinkProgram(program);

		if(glGetProgrami(program, GL_LINK_STATUS) == 0) {
			System.out.println(this.getClass().getName() + " " + glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
		
	    if (vertexShaderID != 0) {
	    	  glDetachShader(program, vertexShaderID);
		}
		if (geometryShaderID != 0) {
		      glDetachShader(program, geometryShaderID);
		}
		if (fragmentShaderID != 0) {
		      glDetachShader(program, fragmentShaderID);
		}		  
		if (tessellationControlShaderID != 0) {
		      glDetachShader(program, tessellationControlShaderID);
		}		  
		if (tesselationEvaluationShaderID != 0) {
		      glDetachShader(program, tesselationEvaluationShaderID);
		}		  
		if (computeShaderID != 0) {
		      glDetachShader(program, computeShaderID);
		}
		
		glValidateProgram(program);
		
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
			System.err.println(this.getClass().getName() +  " " + glGetProgramInfoLog(program, 1024));
			System.exit(1);
		}
		
		System.out.println("Compile Shader program id: " + program);

	}
	
	/**
	 * Полная очистка шейдера, по сути деструктор
	 * Возможно в будущем переедет в него
	 * 
	 * После вызова cleanup() объект уже невозможно использовать
	 */
    public void cleanup() {
        unbind();
        if (program != 0) {
            glDeleteProgram(program);
        }
    }
	
}

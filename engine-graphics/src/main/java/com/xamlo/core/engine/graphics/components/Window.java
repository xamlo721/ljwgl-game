package com.xamlo.core.engine.graphics.components;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.GL_FALSE;

public class Window {

	private static Window instance = null;

	private long window;
	private int width;
	private int height;
	
	public static Window getInstance() {
	    if(instance == null) {
	    	instance = new Window();
	    }
	      return instance;
	}
		
	public void create(int width, int height) {
		
		setWidth(width);
		setHeight(height);
		
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        
		//Установка минимальных версий OpenGL 3.3
		//Если требуемая минимальная версия не поддерживается на компьютере, создание контекста (и окна) завершается ошибкой
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);	
		//Установка профайла для которого создается контекст
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);	
		
		//Включение возможности изменения размера окна
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);	
		
		window = glfwCreateWindow(width, height, "base window title", 0, 0);
		
		if(window == 0) {
			//Хотя создание окон редко завершается сбоем, 
			//создание контекста зависит от правильно установленных драйверов и 
			//может завершиться сбоем даже на машинах с необходимым оборудованием
		    throw new RuntimeException("Failed to create window");
		}

		/**
		 * Создание текущего контекста
		 * 
		 * Контекст будет оставаться текущим до тех пор, 
		 * пока вы не сделаете текущим другой контекст или пока окно, 
		 * владеющее текущим контекстом, не будет уничтожено
		 */
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glfwShowWindow(window);
		
		/**
		 * Кроме того, поскольку буферы будут заменены в середине обновления экрана, что приведет к разрыву экрана.
		 * По этим причинам приложения обычно хотят установить интервал подкачки равным единице.
		 *  Можно установить более высокие значения, но обычно это не рекомендуется из-за задержки ввода, к которой это приводит.
		 */
		glfwSwapInterval(1);
	}
	
	public void setWindowTitle(String title) {
		glfwSetWindowTitle(window, title);
	}
	
	public void setWindowIcon(GLFWImage icon) {
		GLFWImage.Buffer images = GLFWImage.malloc(1);
        images.put(0, icon);
		glfwSetWindowIcon(window, images);
	}
	
	/*
	 * Когда весь кадр отрисован, буферы необходимо поменять местами друг с другом, 
	 * чтобы задний буфер стал передним буфером и наоборот.
	 */
	public void swapBuffers() {
		glfwSwapBuffers(window);
		//Пул ивентс вызывает обработки движений клавиатуры, мышки и т.д
		//Без него не будет нормально передвигаться окно
		//Вызывать надо в начале каждого цикла, но пока не знаю куда перетащить
		glfwPollEvents();
	}
	
	public void close() {
		glfwDestroyWindow(window);
	}
	
	/**
	 * Когда пользователь пытается закрыть окно, либо нажав виджет закрытия в строке заголовка, 
	 * либо используя комбинацию клавиш, такую как Alt + F4, этот флаг устанавливается в 1. 
	 * Обратите внимание, что окно на самом деле не закрыто, поэтому ожидается, 
	 * что вы будете отслеживать этот флаг и либо уничтожите окно, 
	 * либо предоставите пользователю какую-либо обратную связь.
	 * 
	 */
	public boolean isCloseRequested() {
		return glfwWindowShouldClose(window);
	}
	
	public void setWindowSize(int x, int y){
		glfwSetWindowSize(window, x, y);
		setHeight(y);
		setWidth(x);

	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public long getWindow() {
		return window;
	}

	public void setWindow(long window) {
		this.window = window;
	}
}

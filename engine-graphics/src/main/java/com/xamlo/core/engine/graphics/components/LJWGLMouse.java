package com.xamlo.core.engine.graphics.components;


import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class LJWGLMouse extends AbstractMouse {

	 
	@SuppressWarnings("unused")
	private GLFWCursorPosCallback cursorPosCallback;
	
	@SuppressWarnings("unused")
	private GLFWMouseButtonCallback mouseButtonCallback;
	
	@SuppressWarnings("unused")
	private GLFWScrollCallback scrollCallback;
	
	@SuppressWarnings("unused")
	private GLFWFramebufferSizeCallback framebufferSizeCallback;
	
	public LJWGLMouse() {
		
		
		cursorPosition = new Vector2f();
		
		glfwSetFramebufferSizeCallback(Window.getInstance().getWindow(), (framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
		    @Override
		    public void invoke(long window, int width, int height) {
		        Window.getInstance().setWindowSize(width, height);
		    }
		}));
		
		
		glfwSetMouseButtonCallback(Window.getInstance().getWindow(), (mouseButtonCallback = new GLFWMouseButtonCallback() {

            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == GLFW_PRESS){
                	if (!pushedButtons.contains(button)){
                		pushedButtons.add(button);
                		buttonsHolding.add(button);
                	}
                }
                
                if (action == GLFW_RELEASE){
                	releasedButtons.add(button);
                	buttonsHolding.remove(new Integer(button));
                }
            }
		}));
		
		glfwSetCursorPosCallback(Window.getInstance().getWindow(), (cursorPosCallback = new GLFWCursorPosCallback() {

            @Override
            public void invoke(long window, double xpos, double ypos) {
            	cursorPosition.x = ((float) xpos);
            	cursorPosition.y = ((float) ypos);
            }

		}));
		
		glfwSetScrollCallback(Window.getInstance().getWindow(), (scrollCallback = new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				setScrollOffset((float) yoffset);
			}
		}));
		
	}
	
	@Override
	public void update() {
		setScrollOffset(0);
		pushedButtons.clear();
		releasedButtons.clear();
		glfwPollEvents();
	}
	
	@Override
	public void setCursorPosition(Vector2f cursorPosition) {
		this.cursorPosition = cursorPosition;
		
		glfwSetCursorPos(Window.getInstance().getWindow(), cursorPosition.x(), cursorPosition.y());
	}
	
}

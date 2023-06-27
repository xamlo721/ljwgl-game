package com.xamlo.core.engine.graphics.components;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

public class LJWGLKeyboard extends AbstractKeyboard {
	
	@SuppressWarnings("unused")
	private GLFWKeyCallback keyCallback;
	
	
	@SuppressWarnings("unused")
	private GLFWFramebufferSizeCallback framebufferSizeCallback;
	
	
	public LJWGLKeyboard() {
		
		
		glfwSetFramebufferSizeCallback(Window.getInstance().getWindow(), (framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
		    @Override
		    public void invoke(long window, int width, int height) {
		        Window.getInstance().setWindowSize(width, height);
		    }
		}));
		
		glfwSetKeyCallback(Window.getInstance().getWindow(), (keyCallback = new GLFWKeyCallback() {

            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
            	if (action == GLFW_PRESS){
            		if (!pushedKeys.contains(key)){
            			pushedKeys.add(key);
            			keysHolding.add(key);
            		}
                }
            	
                if (action == GLFW_RELEASE){
                	keysHolding.remove(new Integer(key));
                	releasedKeys.add(key);
                }

            }
        }));
		
		
	}
	

	@Override
	public void update() {
		pushedKeys.clear();
		releasedKeys.clear();
		glfwPollEvents();
	}

}

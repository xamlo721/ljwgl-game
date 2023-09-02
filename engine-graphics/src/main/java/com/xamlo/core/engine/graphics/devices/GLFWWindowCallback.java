package com.xamlo.core.engine.graphics.devices;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

public class GLFWWindowCallback extends GLFWFramebufferSizeCallback {

    @Override
    public void invoke(long window, int width, int height) {
        LJWGLWindow.getInstance().resize(width, height);
    }

}

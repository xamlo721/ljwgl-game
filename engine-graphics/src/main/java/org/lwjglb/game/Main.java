package org.lwjglb.game;

import org.lwjglb.engine.*;

import static org.lwjgl.glfw.GLFW.*;


public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        Engine gameEng = new Engine("chapter-03");
        gameEng.start();
    }

}

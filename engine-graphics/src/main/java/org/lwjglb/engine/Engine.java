package org.lwjglb.engine;

import org.lwjglb.engine.graph.Render;

import com.xamlo.core.engine.graphics.components.Window;

public class Engine {

    public static final int TARGET_UPS = 30;
    private final Window window = Window.getInstance();
    private Render render;
    private boolean running;
    private int targetFps;
    private int targetUps;

    public Engine(String windowTitle) {
        window.create(1920/2, 1080/2);
        targetFps = 60;
        targetUps = Engine.TARGET_UPS;
        render = new Render();
        running = true;
    }

    private void cleanup() {
        render.cleanup();
        window.close();
    }


    private void run() {
        long initialTime = System.currentTimeMillis();
        float timeU = 1000.0f / targetUps;
        float timeR = targetFps > 0 ? 1000.0f / targetFps : 0;
        float deltaUpdate = 0;
        float deltaFps = 0;

        long updateTime = initialTime;
        while (running && !window.isCloseRequested()) {

            long now = System.currentTimeMillis();
            deltaUpdate += (now - initialTime) / timeU;
            deltaFps += (now - initialTime) / timeR;

            if (targetFps <= 0 || deltaFps >= 1) {
            	
            }

            if (deltaUpdate >= 1) {
                long diffTimeMillis = now - updateTime;
                updateTime = now;
                deltaUpdate--;
                
            }

            if (targetFps <= 0 || deltaFps >= 1) {
                render.render(window);
                deltaFps--;
                window.swapBuffers();
            }
            initialTime = now;
        }

        cleanup();
    }

    public void start() {
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

}

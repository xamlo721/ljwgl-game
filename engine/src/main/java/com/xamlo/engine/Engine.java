package com.xamlo.engine;

import com.xamlo.core.engine.graphics.RenderEngine;
import com.xamlo.core.engine.graphics.api.components.ICamera;
import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.components.ISceneRenderer;
import com.xamlo.core.engine.graphics.threads.RenderThread;
import com.xamlo.engine.world.World;

public class Engine {
	
	World w;
	
	private boolean isRunning;
	private RenderEngine renderingEngine;
	private RenderThread glThread;
	private ICamera camera;
	private IScene scene;


	public Engine(ICamera cam, IScene scene, ISceneRenderer sceneRenderer) {
		this.camera = cam;
		this.scene = scene;
		this.isRunning = false;
		this.renderingEngine = new RenderEngine(sceneRenderer);
	}
	
	public void init() {
        //TODO: Разумеется камера не должна находиться внутри сцены
        renderingEngine.setCamera(camera);
        renderingEngine.setScene(scene);
		glThread = new RenderThread(renderingEngine);
		
	}

	public void start() {
		glThread.start();
		this.isRunning = true;

	}
	

}

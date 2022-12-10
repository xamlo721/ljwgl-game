package com.xamlo.engine;

import com.xamlo.core.engine.graphics.RenderEngine;
import com.xamlo.core.engine.graphics.threads.RenderThread;
import com.xamlo.engine.world.World;

public class Engine {
	
	World w;
	
	private boolean isRunning;
		
	private RenderEngine renderingEngine;
	private RenderThread glThread;


	
	public void init() {

		renderingEngine = new RenderEngine();
		glThread = new RenderThread(renderingEngine);
		glThread.start();
		
	}

	public void start() {
		if(isRunning)
			return;
		
		//Поток здесь больше не виснет
		glThread.startRender();

	}
	
	

	


}
